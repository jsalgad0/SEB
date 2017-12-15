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
import com.mx.sab.dao.ICargaCatalogoSabDao;
import com.mx.sab.dao.IGenericDao;
import com.mx.sab.dao.IPrestacionesDao;
import com.mx.sab.form.CargaCatalogoSabForm;
import com.mx.sab.model.Aseguradores;
import com.mx.sab.model.Bitacoracargaprestacion;
import com.mx.sab.model.Catestatusarchivoprestaciones;
import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.Detallebitacoracargaprestaciones;
import com.mx.sab.service.ICargaCatalogoSabService;
import com.mx.sab.service.IGenericService;
import com.mx.sab.service.IRegistroLectoresService;
import com.mx.sab.vo.CargaCatalogoSabVo;

@Service
@Log4j2
public class CargaCatalogoSabServceImpl implements ICargaCatalogoSabService {
	
	@Autowired private IGenericDao genericDao;
	@Autowired private IGenericService genericService;
	@Autowired private IAseguradorDao aseguradorDao;
	@Autowired private IPrestacionesDao prestacionesDao;
	@Autowired private ICargaCatalogoSabDao cargaCatalogoSabDao;
	
	@Autowired
	private IRegistroLectoresService registroLectoresService;
	
	@Override
	public void save(CargaCatalogoSabForm cargaCatalogoSabForm){
		//log.info("save");
		try{

						HSSFWorkbook workbook = new HSSFWorkbook(cargaCatalogoSabForm.getFile().getInputStream());
						HSSFSheet worksheet = workbook.getSheetAt(0);
						Iterator<Row> iterator = worksheet.rowIterator();
						Catprestacion catprestacion = null;
						CargaCatalogoSabVo cargaCatalogoSabVo = null;
						Collection<CargaCatalogoSabVo> cargaVos = new ArrayList<>();
						Collection<String> filasError = new ArrayList<>();
						int index = 0;
						
						boolean archivoSinErrores = false;
						boolean filaError = false;
						while (iterator.hasNext()) {
							Row row = (Row) iterator.next();
							if (index!=0) {
								HSSFCell cell = (HSSFCell) row.getCell(0);
								catprestacion = new Catprestacion();

									if(cell!=null){
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										if(cell.getStringCellValue() !=""){
											catprestacion.setCodigo(cell.getStringCellValue());																					
										}else{
											archivoSinErrores = true;
											filaError = true;											
										}									
									}else{
										archivoSinErrores = true;
										filaError = true;
									}

									cell = (HSSFCell) row.getCell(1);
									if(cell!=null){
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										catprestacion.setSubcodigo(cell.getStringCellValue());										
									}
									
									cell = (HSSFCell) row.getCell(2);
									if(cell!=null){
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										if(cell.getStringCellValue()!=""){
											catprestacion.setDescripcion(cell.getStringCellValue());																					
										}else{
											archivoSinErrores = true;
											filaError = true;	
										}
									}else{
										archivoSinErrores = true;
										filaError = true;				
									}

									int idServicio = 0;
									cell = (HSSFCell) row.getCell(3);
									
									try {

										if(cell!=null){
											cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
											idServicio = (int) cell.getNumericCellValue();
										}
																					
									} catch (Exception e) {								
										archivoSinErrores = true;
										filaError = true;
									}
										
									if(idServicio != 0){
									   catprestacion.setServicioId(idServicio);
									}else{
										archivoSinErrores = true;
										filaError = true;
									}
										
									cell = (HSSFCell) row.getCell(4);
									if(cell!=null){
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										if(cell.getStringCellValue()!=""){
											catprestacion.setNivelI(cell.getStringCellValue());																					
										}else{
											archivoSinErrores = true;
											filaError = true;	
										}
									}else{
										archivoSinErrores = true;
										filaError = true;				
									}
									
									
									cell = (HSSFCell) row.getCell(5);
									if(cell!=null){
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										catprestacion.setNivelIi(cell.getStringCellValue());										
									}
									
									cell = (HSSFCell) row.getCell(6);
									if(cell!=null){
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										catprestacion.setNivelIii(cell.getStringCellValue());										
									}
									
									cell = (HSSFCell) row.getCell(7);
									if(cell!=null){
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										catprestacion.setNivelIv(cell.getStringCellValue());										
									}
									
									cell = (HSSFCell) row.getCell(8);
									if(cell!=null){
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										catprestacion.setNivelV(cell.getStringCellValue());										
									}
									
									cell = (HSSFCell) row.getCell(9);
									if(cell!=null){
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										catprestacion.setEspecialidad1(cell.getStringCellValue());										
									}
									
									cell = (HSSFCell) row.getCell(10);
									if(cell!=null){
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										catprestacion.setEspecialidad2(cell.getStringCellValue());										
									}
									
									cell = (HSSFCell) row.getCell(11);
									if(cell!=null){
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										catprestacion.setEspecialidad3(cell.getStringCellValue());										
									}
									
									cell = (HSSFCell) row.getCell(12);
									if(cell!=null){
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										catprestacion.setEspecialidad4(cell.getStringCellValue());										
									}
									
									cell = (HSSFCell) row.getCell(13);
									if(cell!=null){
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										catprestacion.setEspecialidad5(cell.getStringCellValue());										
									}
									
									cell = (HSSFCell) row.getCell(14);
									if(cell!=null){
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										catprestacion.setEspecialidad6(cell.getStringCellValue());										
									}
									
									if(cargaVos.size() > 0){
										for(CargaCatalogoSabVo cargaSab : cargaVos ){
											if(cargaSab.getCatPrestacion().getCodigo() == catprestacion.getCodigo()){
												archivoSinErrores = true;
												filaError = true;					
											}
										}																			
									}
									
									if(filaError == true){
										int fila = index + 1;
										filasError.add(String.valueOf(fila));
									}						
										catprestacion.setFechaHoraEtl(new Timestamp(new Date().getTime()));
										cargaCatalogoSabVo = new CargaCatalogoSabVo();
										cargaCatalogoSabVo.setCatPrestacion(catprestacion);
										cargaCatalogoSabVo.setNumeroFila(index+1);
										cargaCatalogoSabVo.setFilaError(filaError);
										cargaVos.add(cargaCatalogoSabVo);	
							}
							filaError = false;
							index++;
						}
						
						int prestacionesTotales = index-1;
						Catestatusarchivoprestaciones estatusPrestacion = new Catestatusarchivoprestaciones();
						Bitacoracargaprestacion bitacora = new Bitacoracargaprestacion();
						Aseguradores asegurador = aseguradorDao.getAseguradorById(1000);
						if(archivoSinErrores == false){
							cargaCatalogoSabForm.setExito("El archivo se ha cargado correctamente");
							cargaCatalogoSabForm.setError("");
							
							for(CargaCatalogoSabVo prestacionSab : cargaVos){
								cargaCatalogoSabDao.save(prestacionSab.getCatPrestacion());								
							}
							
							estatusPrestacion = prestacionesDao.getCatEstatus(1);
							bitacora.setCatestatusarchivoprestaciones(estatusPrestacion);
							bitacora.setNumPrestacionesCargadas(prestacionesTotales);
							bitacora.setFechaHoraEtl(new Timestamp(new Date().getTime()));
							bitacora.setAseguradores(asegurador);
							prestacionesDao.saveBitacoraArchivoPrestaciones(bitacora);
							
						}else{
							cargaCatalogoSabForm.setExito("");
							cargaCatalogoSabForm.setError("Hubo un error en la carga de prestaciones");		
							
							estatusPrestacion = prestacionesDao.getCatEstatus(2);
							bitacora.setCatestatusarchivoprestaciones(estatusPrestacion);
							bitacora.setNumPrestacionesCargadas(prestacionesTotales);
							bitacora.setFechaHoraEtl(new Timestamp(new Date().getTime()));
							bitacora.setAseguradores(asegurador);
							prestacionesDao.saveBitacoraArchivoPrestaciones(bitacora);
							
							Bitacoracargaprestacion bitacora2 = prestacionesDao.getLastBitacoraByIdAsegurador(asegurador.getAseguradorId());
							Detallebitacoracargaprestaciones detalle = new 	Detallebitacoracargaprestaciones();
							detalle.setBitacoracargaprestacion(bitacora2);
							String errores = "";
							for(String fila: filasError ){
								if(errores == ""){
									errores = fila;
								}else if(errores.length() <= 252){
									errores = errores + "," + fila; 
								}
							}
							detalle.setNumFila(errores);
							prestacionesDao.saveDetalleBitacora(detalle);
							
						}
	
		}catch(Exception ex){
			ex.printStackTrace();
			cargaCatalogoSabForm.setExito("");
			cargaCatalogoSabForm.setError("Surgio un error");
		}
	}

	
}
