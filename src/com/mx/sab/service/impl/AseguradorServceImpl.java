package com.mx.sab.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAseguradorDao;
import com.mx.sab.dao.IGenericDao;
import com.mx.sab.dao.IPrestacionesDao;
import com.mx.sab.form.AseguradorForm;
import com.mx.sab.model.Aseguradores;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Bitacoracargaprestacion;
import com.mx.sab.model.Catestatusarchivoprestaciones;
import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.Convenios;
import com.mx.sab.model.Detallebitacoracargaprestaciones;
import com.mx.sab.model.Prestacionasegurador;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.model.PrestacionesaseguradorequivalenciasId;
import com.mx.sab.model.Prestacionprestador;
import com.mx.sab.model.Propietarioslector;
import com.mx.sab.service.IAseguradorService;
import com.mx.sab.service.IGenericService;
import com.mx.sab.service.IRegistroLectoresService;
import com.mx.sab.vo.AseguradoresVo;
import com.mx.sab.vo.CargaArchivoPrestacionesVo;
import com.mx.sab.vo.EquivalenciasSabVo;

@Service
@Log4j2
public class AseguradorServceImpl implements IAseguradorService {
	
	@Autowired private IGenericDao genericDao;
	@Autowired private IGenericService genericService;
	@Autowired private IAseguradorDao aseguradorDao;
	@Autowired private IPrestacionesDao prestacionesDao;
	
	@Autowired
	private IRegistroLectoresService registroLectoresService;
	
	@Override
	public void save(AseguradorForm aseguradorForm){
		//log.info("save");
		try{
			if (aseguradorDao.existeDireccionAsegurador(aseguradorForm)==null) {
				if (aseguradorDao.getAseguradoresByNombre(aseguradorForm.getAsegurador())==null) {
					if (aseguradorDao.getAseguradorByRfc(aseguradorForm.getRfc())==null) {
						Aseguradores aseguradores = new Aseguradores();
						aseguradores.setNombreRazonSocial(aseguradorForm.getAsegurador());
						aseguradores.setRfc(aseguradorForm.getRfc());
						aseguradores.setCatcolonias(genericService.getColoniaById(aseguradorForm.getIdColonia()));
						aseguradores.setCatestados(genericService.getEstadoById(aseguradorForm.getIdEstado()));
						aseguradores.setCatmunicipios(genericService.getMunicipioById(aseguradorForm.getIdMunicipio()));
						aseguradores.setCalle(aseguradorForm.getCalle());
						aseguradores.setNoExt(aseguradorForm.getNumeroExterno());
						aseguradores.setNoInt(aseguradorForm.getNumeroInterno());
						aseguradores.setCp(aseguradorForm.getCp());
						aseguradores.setNombreContacto(aseguradorForm.getNombre());
						aseguradores.setTelefono(aseguradorForm.getTelefono1());
						aseguradores.setTelefono2(aseguradorForm.getTelefono2());
						aseguradores.setCorreoElectronico(aseguradorForm.getCorreo());
						aseguradores.setActivo(1);
						
						aseguradorDao.save(aseguradores);
						
						// Carga de prestaciones
						
						Aseguradores asegurador2 = new Aseguradores();
						asegurador2 = aseguradorDao.getAseguradorByRfc(aseguradorForm.getRfc());
						Prestacionasegurador prestacionAsegurador = new Prestacionasegurador();
						int exito = 1;
						/*if(aseguradorForm.getFile().getSize() != 0){
							HSSFWorkbook workbook = new HSSFWorkbook(aseguradorForm.getFile().getInputStream());
							HSSFSheet worksheet = workbook.getSheetAt(0);
							Iterator<Row> iterator = worksheet.rowIterator();
							int index = 0;
							EquivalenciasSabVo equivalenciasSabVo = null;
							CargaArchivoPrestacionesVo cargaArchivoPrestacionesVo = new CargaArchivoPrestacionesVo();
							Collection<CargaArchivoPrestacionesVo> prestacionesVos = new ArrayList<>();
							boolean archivoSinErrores = false;
							boolean filaError = false;
							while (iterator.hasNext()) {
								Row row = (Row) iterator.next();
								if (index!=0) {
									prestacionAsegurador = new Prestacionasegurador();
									equivalenciasSabVo = new EquivalenciasSabVo();
									prestacionAsegurador.setAseguradores(asegurador2);
									HSSFCell cell = (HSSFCell) row.getCell(0);
									
									if (cell!=null) {
										
										int idSab = 0;
										
										try {
											cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
											idSab = (int) cell.getNumericCellValue();											
										} catch (Exception e) {								
											archivoSinErrores = true;
											filaError = true;
										}
										
										if(idSab != 0){
											Catprestacion prestacionSab = prestacionesDao.getCatPrestacionById(idSab);	
											if(prestacionSab == null){				
												archivoSinErrores = true;
												filaError = true;
												equivalenciasSabVo.setIdSab(0);
											}else{
												equivalenciasSabVo.setIdSab((int) cell.getNumericCellValue());			
											}
										}else{
											// Estatus para señalar que el archivo
											if(filaError == false){
												exito = 4;												
											}
										}
										
										cell = (HSSFCell) row.getCell(1);
										if(cell!=null){
											cell.setCellType(HSSFCell.CELL_TYPE_STRING);
											if(cell.getStringCellValue() !=""){
												prestacionAsegurador.setCodigo(cell.getStringCellValue());																					
											}else{
												archivoSinErrores = true;
												filaError = true;											
											}									
										}else{
											archivoSinErrores = true;
											filaError = true;
										}

										cell = (HSSFCell) row.getCell(2);
										if(cell!=null){
											cell.setCellType(HSSFCell.CELL_TYPE_STRING);
											prestacionAsegurador.setSubcodigo(cell.getStringCellValue());										
										}
										
										cell = (HSSFCell) row.getCell(3);
										if(cell!=null){
											cell.setCellType(HSSFCell.CELL_TYPE_STRING);
											if(cell.getStringCellValue()!=""){
												prestacionAsegurador.setDescripcion(cell.getStringCellValue());																					
											}else{
												archivoSinErrores = true;
												filaError = true;	
											}
										}else{
											archivoSinErrores = true;
											filaError = true;				
										}
										
										prestacionAsegurador.setFechaHoraEtl(new Timestamp(new Date().getTime()));
										cargaArchivoPrestacionesVo = new CargaArchivoPrestacionesVo();
										cargaArchivoPrestacionesVo.setPrestacionesAsegurador(prestacionAsegurador);
										cargaArchivoPrestacionesVo.setEquivalenciasSabVo(equivalenciasSabVo);
										cargaArchivoPrestacionesVo.setNumeroFila(index+1);
										cargaArchivoPrestacionesVo.setFilaError(filaError);
										if(exito != 4){
											prestacionesVos.add(cargaArchivoPrestacionesVo);											
										}
									}			
								}
								exito = 1;
								filaError = false;
								index++;
								
							}
							
							Bitacoracargaprestacion bitacora = new Bitacoracargaprestacion();
							Catestatusarchivoprestaciones estatusPrestacion = new Catestatusarchivoprestaciones();
							int prestacionesTotales = index-1;
							if(archivoSinErrores == false){
								PrestacionesaseguradorequivalenciasId prestacionesaseguradorequivalenciasId = null;
								Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = null;	
								
								//Inserta prestaciones y equivalencias
								for(CargaArchivoPrestacionesVo prestacionVo:prestacionesVos ){								
									prestacionesDao.savePrestacionesAsegurador(prestacionVo.getPrestacionesAsegurador());
									Prestacionasegurador prestacionAsegurador2 = prestacionesDao.getLastPrestacionAseguradorByIdAsegurador(asegurador2.getAseguradorId());
									prestacionesaseguradorequivalenciasId = new PrestacionesaseguradorequivalenciasId();
									prestacionesaseguradorequivalencias = new Prestacionesaseguradorequivalencias();		
									prestacionesaseguradorequivalenciasId.setPrestacionAseguradorId(prestacionAsegurador2.getPrestacionAseguradorId());
									prestacionesaseguradorequivalenciasId.setPrestacionSabid(prestacionVo.getEquivalenciasSabVo().getIdSab());
									prestacionesaseguradorequivalencias = new Prestacionesaseguradorequivalencias();
									prestacionesaseguradorequivalencias.setId(prestacionesaseguradorequivalenciasId);
									prestacionesDao.saveEquivalenciasAsegurador(prestacionesaseguradorequivalencias);	
								}								
								estatusPrestacion = prestacionesDao.getCatEstatus(1);
								bitacora.setCatestatusarchivoprestaciones(estatusPrestacion);
								bitacora.setNumPrestacionesCargadas(prestacionesTotales);
								bitacora.setFechaHoraEtl(new Timestamp(new Date().getTime()));
								bitacora.setAseguradores(asegurador2);
								prestacionesDao.saveBitacoraArchivoPrestaciones(bitacora);
								
							}else{
								exito = 2;
								estatusPrestacion = prestacionesDao.getCatEstatus(2);
								bitacora.setCatestatusarchivoprestaciones(estatusPrestacion);
								bitacora.setNumPrestacionesCargadas(prestacionesTotales);
								bitacora.setFechaHoraEtl(new Timestamp(new Date().getTime()));
								bitacora.setAseguradores(asegurador2);
								prestacionesDao.saveBitacoraArchivoPrestaciones(bitacora);
								Bitacoracargaprestacion bitacora2 = prestacionesDao.getLastBitacoraByIdAsegurador(asegurador2.getAseguradorId());
								Detallebitacoracargaprestaciones detalle = new 	Detallebitacoracargaprestaciones();
								detalle.setBitacoracargaprestacion(bitacora2);
								for(CargaArchivoPrestacionesVo prestacionVo:prestacionesVos ){
									
									if(prestacionVo.isFilaError() == true){
										detalle.setNumFila(prestacionVo.getNumeroFila());
										prestacionesDao.saveDetalleBitacora(detalle);
									}
									
								}
							
							}					
						}else{
							exito = 3;
						}*/
						//---------------------------------------------------------------------------------------------------					
						if(registroLectoresService.getPropietariosLectorByRfc(aseguradores.getRfc())==null){
							Propietarioslector propietarioslector = new Propietarioslector();
							propietarioslector.setPropietarioLector(aseguradores.getNombreRazonSocial());
							propietarioslector.setRfc(aseguradores.getRfc());
							registroLectoresService.savePropietarioLector(propietarioslector);	
						}
						
						aseguradorForm.setError("");
						
						if(exito == 1){
							aseguradorForm.setExito("El Asegurador fue creado correctamente");
						}/*else if(exito == 2){
							aseguradorForm.setExito("El Asegurador fue creado correctamente pero hubo un error en la carga de prestaciones");							
						}else if(exito == 3){
							aseguradorForm.setExito("El Asegurador fue creado correctamente sin cuadro de prestaciones");
						}else if(exito == 4){
							aseguradorForm.setExito("El Asegurador fue creado correctamente pero existen prestaciones que no se encuentran en el catálogo SAB, favor de contactar a su Administrador");
						}*/

					}else {
						aseguradorForm.setError("El Asegurador, ya está registrado");
						aseguradorForm.setExito("");
					}
				}else {
					aseguradorForm.setError("El Asegurador, ya está registrado");
					aseguradorForm.setExito("");
				}				
			}else{
				aseguradorForm.setError("No se puede repetir la direccion");
				aseguradorForm.setExito("");
			}

			
		}catch(Exception ex){
			ex.printStackTrace();
			aseguradorForm.setExito("");
			aseguradorForm.setError("Surgio un error");
		}
	} 
	
	@Override
	public Collection<Aseguradores> getAseguradores() {
		Collection<Aseguradores> aseguradores = aseguradorDao.getAseguradores();
		return aseguradores;
	}

	@Override
	public Collection<Aseguradores> getAseguradoresSinPrestaciones() {
		Collection<Aseguradores> aseguradores = aseguradorDao.getAseguradoresSinPrestaciones();
		Collection<Aseguradores> aseguradoresSinPrestaciones = new ArrayList<>();
		for (Aseguradores asegurador : aseguradores) {
			if (asegurador.getPrestacionaseguradors().size()==0) {
				aseguradoresSinPrestaciones.add(asegurador);
			}
		}
		return aseguradoresSinPrestaciones;
	} 
	
	@Override
	public void getAsegurador(AseguradorForm aseguradorForm) {
		aseguradorForm.setExito("");
		aseguradorForm.setError("");
		if (aseguradorForm.getRfc().trim().length()!=0 || aseguradorForm.getIdAsegurador()!=0) {
			Aseguradores aseguradores = null;
			if (aseguradorForm.getBusqueda()==0){
				aseguradores = aseguradorDao.getAseguradorByRfc(aseguradorForm.getRfc());
			}else{
				aseguradores = aseguradorDao.getAseguradorById(aseguradorForm.getIdAsegurador());	
			}
			aseguradorForm.setBusqueda(0);
			if (aseguradores!=null) {
				aseguradorForm.setAsegurador(aseguradores.getNombreRazonSocial());
				aseguradorForm.setIdAsegurador(aseguradores.getAseguradorId());
				aseguradorForm.setCalle(aseguradores.getCalle());
				aseguradorForm.setNumeroExterno(aseguradores.getNoExt());
				aseguradorForm.setNumeroInterno(aseguradores.getNoInt());
				aseguradorForm.setIdColonia(aseguradores.getCatcolonias().getColoniaId());
				aseguradorForm.setIdEstado(aseguradores.getCatestados().getEstadoId());
				aseguradorForm.setIdMunicipio(aseguradores.getCatmunicipios().getMunicipioId());
				aseguradorForm.setCp(aseguradores.getCp());
				aseguradorForm.setNombre(aseguradores.getNombreContacto());
				aseguradorForm.setTelefono1(aseguradores.getTelefono());
				aseguradorForm.setTelefono2(aseguradores.getTelefono2());
				aseguradorForm.setCorreo(aseguradores.getCorreoElectronico());
				aseguradorForm.setRfc(aseguradores.getRfc());
				aseguradorForm.setEditar(true);
				aseguradorForm.setError("");	
			}else{
				aseguradorForm.setError("Asegurador no registrado en el sistema");
			}
		}else{
			aseguradorForm.setError("Ingrese el RFC o el Nombre");
		}
	}
	
	@Override
	public void update(AseguradorForm aseguradorForm){
		boolean actualizar = false;
		try{
			if (aseguradorForm.isEditar()) {
				Aseguradores aseguradores = aseguradorDao.getAseguradorById(aseguradorForm.getIdAsegurador());
				Aseguradores aseguradoresAux = aseguradorDao.existeDireccionAsegurador(aseguradorForm);
				if (aseguradoresAux == null) {
					actualizar = true;
				}else {
					if (aseguradoresAux.getAseguradorId() == aseguradores.getAseguradorId()) {
						actualizar = true;
					}else{
						aseguradorForm.setError("No se puede repetir la direccion");
						aseguradorForm.setExito("");	
					}
				}
				if (actualizar) {
					aseguradores.setNombreRazonSocial(aseguradorForm.getAsegurador());
					aseguradores.setRfc(aseguradorForm.getRfc());
					aseguradores.setCatcolonias(genericService.getColoniaById(aseguradorForm.getIdColonia()));
					aseguradores.setCatestados(genericService.getEstadoById(aseguradorForm.getIdEstado()));
					aseguradores.setCatmunicipios(genericService.getMunicipioById(aseguradorForm.getIdMunicipio()));
					aseguradores.setCalle(aseguradorForm.getCalle());
					aseguradores.setNoExt(aseguradorForm.getNumeroExterno());
					aseguradores.setNoInt(aseguradorForm.getNumeroInterno());
					aseguradores.setCp(aseguradorForm.getCp());
					aseguradores.setNombreContacto(aseguradorForm.getNombre());
					aseguradores.setTelefono(aseguradorForm.getTelefono1());
					aseguradores.setTelefono2(aseguradorForm.getTelefono2());
					aseguradores.setCorreoElectronico(aseguradorForm.getCorreo());
					aseguradores.setActivo(1);
					
					aseguradorDao.update(aseguradores);
					aseguradorForm.setExito("Se actualizo correctamente el Asegurador");					
				}
			}else{
				aseguradorForm.setError("Debe de buscar un Asegurador");
				aseguradorForm.setExito("");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			aseguradorForm.setError("Surgio un error");
			aseguradorForm.setExito("");
		}
	}
	
	@Override
	public void delete(AseguradorForm aseguradorForm) {
		aseguradorForm.setExito("");
		aseguradorForm.setError("");
		Aseguradores aseguradores = aseguradorDao.getAseguradorById(aseguradorForm.getIdAsegurador());
		if (aseguradores!=null) {
			Iterator<Convenios> iteratorConvenios = aseguradores.getConvenioses().iterator();
			if (iteratorConvenios.hasNext()) {
				aseguradorForm.setExito("");
				aseguradorForm.setError("Hay Convenios vigentes para este Asegurador, no se puede Eliminar");
			}else{
				Iterator<Atencionmedica> iteratorAtencionMedica = aseguradores.getAtencionmedicas().iterator();
				if (iteratorAtencionMedica.hasNext()) {
					aseguradorForm.setExito("");
					aseguradorForm.setError("Hay Atenciones médicas registradas para este Asegurador, no se puede Eliminar");			
				}else{
					aseguradorDao.delete(aseguradores);
					aseguradorForm.setExito("Se ha eliminado correctamente el Asegurador");
					aseguradorForm.setError("");
					aseguradorForm.setEditar(false);
					aseguradorForm.setBusqueda(0);
					aseguradorForm.setIdAsegurador(0);
					aseguradorForm.setRfc("");
					aseguradorForm.setAsegurador("");
					aseguradorForm.setCalle("");
					aseguradorForm.setNumeroExterno("");
					aseguradorForm.setNumeroInterno("");
					aseguradorForm.setIdEstado(-1);
					aseguradorForm.setIdColonia(-1);
					aseguradorForm.setIdMunicipio(-1);
					aseguradorForm.setCp("");
					aseguradorForm.setNombre("");
					aseguradorForm.setTelefono1("");
					aseguradorForm.setTelefono2("");
					aseguradorForm.setCorreo("");	
				}	
			}	
		}else{
			aseguradorForm.setError("Debe de buscar un Asegurador");
			aseguradorForm.setExito("");
		}
	}
	
	@Override
	public Collection<Aseguradores> getAseguradores(AseguradorForm aseguradorForm) {
//		int filas = 7;
//		int paginasTotal = 0;
//		int inicio = 0;
//		int fin = 0;
//		int pagina = 1;
		Collection<Aseguradores> aseguradores = null;
//		
//		if (aseguradorForm==null) {
//			aseguradorForm = new AseguradorForm();
//		}
//		
//		if (aseguradorForm.getBusqueda()==null) {
//			aseguradorForm.setBusqueda("");	
//		}
//		int totalAseguradores = aseguradorDao.getAseguradoresCount(aseguradorForm.getBusqueda());
//		if (totalAseguradores>0) {
//			paginasTotal = totalAseguradores / filas;
//			if (totalAseguradores % filas != 0) {
//				paginasTotal++;
//			}
//			
//			if (paginasTotal>7) {
//				aseguradorForm.setDisplay(7);
//			}else {
//				aseguradorForm.setDisplay(paginasTotal);
//			}
//			
//			aseguradorForm.setCount(paginasTotal);
//			inicio = (pagina-1)*7;
//			fin = 7;
//			
//			aseguradores = aseguradorDao.getAseguradores(aseguradorForm.getBusqueda(),inicio,fin);
//		}else {
//			//log.info("No hay aseguradores");
//		}
//	
		return aseguradores;
	}

	@Override
	public Collection<AseguradoresVo> getAseguradoresByDescripcion(String busqueda) {
		Collection<Aseguradores> aseguradores = aseguradorDao.getAseguradoresByDesc(busqueda);
		Collection<AseguradoresVo> aseguradoresVos = new ArrayList<>();
		AseguradoresVo aseguradoresVo = null;
		for (Aseguradores asegurador : aseguradores) {
			aseguradoresVo = new AseguradoresVo();
			aseguradoresVo.setNombreRazonSocial(asegurador.getNombreRazonSocial());
			aseguradoresVo.setAseguradorId(asegurador.getAseguradorId());
			aseguradoresVo.setRfc(asegurador.getRfc());
			aseguradoresVos.add(aseguradoresVo);
		}
		if (aseguradoresVos.size()==0) {
			aseguradoresVo = new AseguradoresVo();
			aseguradoresVo.setNombreRazonSocial("No hay aseguradores coincidentes");
			aseguradoresVo.setAseguradorId(-1);
			aseguradoresVos.add(aseguradoresVo);
		}
		return aseguradoresVos;
	}
	
}
