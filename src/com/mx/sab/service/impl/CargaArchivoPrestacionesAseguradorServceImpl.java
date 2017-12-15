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
import com.mx.sab.form.CargaPrestacionesAseguradorForm;
import com.mx.sab.model.Aseguradores;
import com.mx.sab.model.Bitacoracargaprestacion;
import com.mx.sab.model.Catestatusarchivoprestaciones;
import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.Detallebitacoracargaprestaciones;
import com.mx.sab.model.Prestacionasegurador;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.model.PrestacionesaseguradorequivalenciasId;
import com.mx.sab.service.ICargaArchivoPrestacionesAseguradorService;
import com.mx.sab.service.IGenericService;
import com.mx.sab.service.IRegistroLectoresService;
import com.mx.sab.vo.CargaArchivoPrestacionesVo;
import com.mx.sab.vo.EquivalenciasSabVo;

@Service
@Log4j2
public class CargaArchivoPrestacionesAseguradorServceImpl implements ICargaArchivoPrestacionesAseguradorService {
	
	@Autowired private IGenericDao genericDao;
	@Autowired private IGenericService genericService;
	@Autowired private IAseguradorDao aseguradorDao;
	@Autowired private IPrestacionesDao prestacionesDao;
	
	@Autowired
	private IRegistroLectoresService registroLectoresService;
	
	@Override
	public void save(CargaPrestacionesAseguradorForm cargaPrestacionesAseguradorForm){
		//log.info("save");
		try{
			Aseguradores asegurador2 = new Aseguradores();
			asegurador2 = aseguradorDao.getAseguradorByRfc(cargaPrestacionesAseguradorForm.getRfc());

					if (asegurador2 != null) {
						
						Prestacionasegurador prestacionAsegurador = new Prestacionasegurador();
						int exito = 1;

						HSSFWorkbook workbook = new HSSFWorkbook(cargaPrestacionesAseguradorForm.getFile().getInputStream());
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
									
									if(prestacionesVos.size() > 0 ){
										for(CargaArchivoPrestacionesVo prestacion :prestacionesVos){
											if(prestacion.getEquivalenciasSabVo().getIdSab() == equivalenciasSabVo.getIdSab()){
												//log.info(prestacion.getEquivalenciasSabVo().getIdSab() + " <<<coleccion");
												//log.info(equivalenciasSabVo.getIdSab() + " <<<<individual");
												archivoSinErrores = true;
												filaError = true;	
											}
										}
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
							cargaPrestacionesAseguradorForm.setError("");
							
							
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
									//detalle.setNumFila(prestacionVo.getNumeroFila());
									prestacionesDao.saveDetalleBitacora(detalle);
								}
								
							}
						
						}	
						
						if(exito == 1){
							cargaPrestacionesAseguradorForm.setExito("El archivo se ha cargado correctamente");
						}else if(exito == 2){
							cargaPrestacionesAseguradorForm.setError("Hubo un error en la carga de prestaciones");							
						}else if(exito == 4){
							cargaPrestacionesAseguradorForm.setExito("El Asegurador fue creado correctamente pero existen prestaciones que no se encuentran en el catálogo SAB, favor de contactar a su Administrador");
						}			
						
					}	
			
		}catch(Exception ex){
			ex.printStackTrace();
			cargaPrestacionesAseguradorForm.setExito("");
			cargaPrestacionesAseguradorForm.setError("Surgio un error");
		}
	} 
	
}
