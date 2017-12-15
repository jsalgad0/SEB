package com.mx.sab.service.impl;

import java.io.IOException;
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

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IAseguradorDao;
import com.mx.sab.dao.IConveniosDao;
import com.mx.sab.dao.IPrestacionesDao;
import com.mx.sab.dao.IPrestadoresDao;
import com.mx.sab.enums.CatTipoServicioEnum;
import com.mx.sab.form.PrestacionesForm;
import com.mx.sab.model.Aseguradores;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.ConvenioCuadroprestaciones;
import com.mx.sab.model.Convenios;
import com.mx.sab.model.CuadroprestacionPrestacion;
import com.mx.sab.model.Prestacionasegurador;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.model.PrestacionesaseguradorequivalenciasId;
import com.mx.sab.model.Prestacionesprestadorequivalencias;
import com.mx.sab.model.PrestacionesprestadorequivalenciasId;
import com.mx.sab.model.Prestacionprestador;
import com.mx.sab.model.Prestadores;
import com.mx.sab.service.IPrestacionesService;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.EquivalenciasSabVo;

@Service
@Log4j2
public class PrestacionesServieImpl implements IPrestacionesService {
	
	@Autowired
	private IPrestadoresDao prestadoresDao;
	
	@Autowired
	private IAseguradorDao aseguradorDao;
	
	@Autowired
	private IPrestacionesDao prestacionesDao;
	
	@Autowired
	private IConveniosDao conveniosDao;
	
	@Autowired
	private IAgendaDao agendaDao;

	@Override
	public void save(PrestacionesForm prestacionesForm) {
		//log.info("save");
		try{
			if (prestacionesForm.getCargaPrestaciones().equals("A")) {
				Aseguradores aseguradores = aseguradorDao.getAseguradorById(prestacionesForm.getIdAseguradores());
				prestacionesForm.setAseguradores(aseguradores);
				prestacionesAsegurador(prestacionesForm);
				for (Prestacionasegurador prestacionasegurador : prestacionesForm.getPrestacionaseguradors()) {
					prestacionesDao.savePrestacionesAsegurador(prestacionasegurador);
				}
				Collection<Prestacionasegurador> prestacionaseguradors = prestacionesDao.getPrestacionesByAsegurador(prestacionesForm.getIdAseguradores());
				PrestacionesaseguradorequivalenciasId prestacionesaseguradorequivalenciasId = null;
				Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = null;
				for (Prestacionasegurador prestacionasegurador : prestacionaseguradors) {
					for (EquivalenciasSabVo equivalenciasSabVo : prestacionesForm.getEquivalenciasSabVos()) {
						if (equivalenciasSabVo.getCodigo().trim().equals(prestacionasegurador.getCodigo().trim())) {
							prestacionesaseguradorequivalenciasId = new PrestacionesaseguradorequivalenciasId();
							prestacionesaseguradorequivalenciasId.setPrestacionAseguradorId(prestacionasegurador.getPrestacionAseguradorId());
							prestacionesaseguradorequivalenciasId.setPrestacionSabid(equivalenciasSabVo.getIdSab());
							prestacionesaseguradorequivalencias = new Prestacionesaseguradorequivalencias();
							prestacionesaseguradorequivalencias.setId(prestacionesaseguradorequivalenciasId);
							prestacionesDao.saveEquivalenciasAsegurador(prestacionesaseguradorequivalencias);
							break;
						}
					}
				}
			}else {
				Prestadores prestadores = prestadoresDao.getPrestadorById(prestacionesForm.getIdPrestadores());
				prestacionesForm.setPrestadores(prestadores);
				prestacionesPrestador(prestacionesForm);
				for (Prestacionprestador prestacionprestador : prestacionesForm.getPrestacionprestadors()) {
					prestacionesDao.savePrestacionesPrestador(prestacionprestador);
				}
				Collection<Prestacionprestador> prestacionprestadors = prestacionesDao.getPrestacionesByPrestador(prestacionesForm.getIdPrestadores());
				PrestacionesprestadorequivalenciasId prestacionesprestadorequivalenciasId = null;
				Prestacionesprestadorequivalencias prestacionesprestadorequivalencias = null;
				for (Prestacionprestador prestacionprestador : prestacionprestadors) {
					for (EquivalenciasSabVo equivalenciasSabVo : prestacionesForm.getEquivalenciasSabVos()) {
						if (equivalenciasSabVo.getCodigo().trim().equals(prestacionprestador.getCodigo().trim())) {
							prestacionesprestadorequivalenciasId = new PrestacionesprestadorequivalenciasId();
							prestacionesprestadorequivalenciasId.setPrestacionPrestadorId(prestacionprestador.getPrestacionPrestadorId());
							prestacionesprestadorequivalenciasId.setPrestacionSabid(equivalenciasSabVo.getIdSab());
							prestacionesprestadorequivalencias = new Prestacionesprestadorequivalencias();
							prestacionesprestadorequivalencias.setId(prestacionesprestadorequivalenciasId);
							prestacionesDao.saveEquivalenciasPrestador(prestacionesprestadorequivalencias);
							break;
						}
					}
				}				
			}	
		}catch(IOException e){
			e.printStackTrace();
			prestacionesForm.setBanderaError(true);
			prestacionesForm.setError("Surgio un error");
		}catch (Exception e) {
			e.printStackTrace();
			prestacionesForm.setBanderaError(true);
			prestacionesForm.setError("Surgio un error");
		}
	}

	private void prestacionesPrestador(PrestacionesForm prestacionesForm) throws IOException {
		Collection<Prestacionprestador> prestacionprestadors = new ArrayList<>();
		Collection<EquivalenciasSabVo> equivalenciasSabVos = new ArrayList<>();
		HSSFWorkbook workbook = new HSSFWorkbook(prestacionesForm.getFile().getInputStream());
		HSSFSheet worksheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = worksheet.rowIterator();
		int index = 0;
		EquivalenciasSabVo equivalenciasSabVo = null;
		Prestacionprestador prestacionprestador = null;
		boolean ignorar = false;
		while (iterator.hasNext()) {
			Row row = (Row) iterator.next();
			if (index!=0) {
				prestacionprestador = new Prestacionprestador();
				equivalenciasSabVo = new EquivalenciasSabVo();
				
				HSSFCell cell = (HSSFCell) row.getCell(0);
				if (cell!=null) {
					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					equivalenciasSabVo.setIdSab((int) cell.getNumericCellValue());	
				}			
				cell = (HSSFCell) row.getCell(5);
				if (cell!=null) {
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					prestacionprestador.setCodigo(cell.getStringCellValue());
					equivalenciasSabVo.setCodigo(cell.getStringCellValue());
				}else {
					ignorar = true;
				}	
				
				if (!ignorar) {
					cell = (HSSFCell) row.getCell(6);
					if (cell!=null) {
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						prestacionprestador.setSubcodigo(cell.getStringCellValue());	
					}	
					cell = (HSSFCell) row.getCell(7);
					if (cell!=null) {
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						prestacionprestador.setDescripcion(cell.getStringCellValue());	
					}
					prestacionprestador.setFechaHoraEtl(new Timestamp(new Date().getTime()));
					prestacionprestador.setPrestadores(prestacionesForm.getPrestadores());
					prestacionprestadors.add(prestacionprestador);
					equivalenciasSabVos.add(equivalenciasSabVo);	
				}
				
			}
			index++;
			ignorar = false;
		}
		prestacionesForm.setPrestacionprestadors(prestacionprestadors);
		prestacionesForm.setEquivalenciasSabVos(equivalenciasSabVos);
	}

	private void prestacionesAsegurador(PrestacionesForm prestacionesForm) throws IOException {
		Collection<Prestacionasegurador> prestacionaseguradors = new ArrayList<>();
		Collection<EquivalenciasSabVo> equivalenciasSabVos = new ArrayList<>();
		HSSFWorkbook workbook = new HSSFWorkbook(prestacionesForm.getFile().getInputStream());
		HSSFSheet worksheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = worksheet.rowIterator();
		int index = 0;
		EquivalenciasSabVo equivalenciasSabVo = null;
		Prestacionasegurador prestacionasegurador = null;
		boolean ignorar = false;
		while (iterator.hasNext()) {
			Row row = (Row) iterator.next();
			if (index!=0) {
				prestacionasegurador = new Prestacionasegurador();
				equivalenciasSabVo = new EquivalenciasSabVo();
				HSSFCell cell = (HSSFCell) row.getCell(0);
				if (cell!=null) {
					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					equivalenciasSabVo.setIdSab((int) cell.getNumericCellValue());	
				}			
				cell = (HSSFCell) row.getCell(5);
				if (cell!=null) {
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					prestacionasegurador.setCodigo(cell.getStringCellValue());
					equivalenciasSabVo.setCodigo(cell.getStringCellValue());
				}else {
					ignorar = true;
				}	
				
				if (!ignorar) {
					cell = (HSSFCell) row.getCell(6);
					if (cell!=null) {
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						prestacionasegurador.setSubcodigo(cell.getStringCellValue());	
					}	
					cell = (HSSFCell) row.getCell(7);
					if (cell!=null) {
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						prestacionasegurador.setDescripcion(cell.getStringCellValue());	
					}
					prestacionasegurador.setFechaHoraEtl(new Timestamp(new Date().getTime()));
					prestacionasegurador.setAseguradores(prestacionesForm.getAseguradores());
					prestacionaseguradors.add(prestacionasegurador);
					equivalenciasSabVos.add(equivalenciasSabVo);	
				}
			}
			index++;
			ignorar = false;
		}
		prestacionesForm.setPrestacionaseguradors(prestacionaseguradors);
		prestacionesForm.setEquivalenciasSabVos(equivalenciasSabVos);
	}

	@Override
	public Collection<AutocompleteVo> getPrestaciones(int idConvenio,String busqueda) {
		Convenios convenios = conveniosDao.getConveniosById(idConvenio);
		int idCuadroPrestaciones = 0;
		Iterator<ConvenioCuadroprestaciones> convenioCuadroPrestacionesIterator = convenios.getConvenioCuadroprestacioneses().iterator();
		while (convenioCuadroPrestacionesIterator.hasNext()) {
			ConvenioCuadroprestaciones convenioCuadroprestaciones = (ConvenioCuadroprestaciones) convenioCuadroPrestacionesIterator.next();
			idCuadroPrestaciones = convenioCuadroprestaciones.getId().getCuadroPrestacionId();
		}
		
		Collection<Prestacionasegurador> prestacionaseguradors = prestacionesDao.getPrestacionesAsegurador(convenios.getAseguradores().getAseguradorId(), idCuadroPrestaciones, busqueda);
		Collection<AutocompleteVo> autocompleteVos = new ArrayList<>();
		AutocompleteVo autocompleteVo = null;
		
		for (Prestacionasegurador prestacionasegurador : prestacionaseguradors) {
			autocompleteVo = new AutocompleteVo();
			autocompleteVo.setLabel(prestacionasegurador.getCodigo() + " / "+prestacionasegurador.getDescripcion());
			autocompleteVo.setValue("" +prestacionasegurador.getPrestacionAseguradorId());
			autocompleteVos.add(autocompleteVo);
		}
		
		if (autocompleteVos.size()==0) {
			autocompleteVo = new AutocompleteVo();
			autocompleteVo.setLabel("No hay prestaciones coincidentes");
			autocompleteVo.setValue("-1");
			autocompleteVos.add(autocompleteVo);
		}
		
		return autocompleteVos;
	}
	
	@Override
	public Collection<AutocompleteVo> getPrestacionesEstudios(int idAtencion, String busqueda, int idServicio) {
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(idAtencion);
		int idCuadroPrestaciones = 0;
//		Iterator<ConvenioCuadroprestaciones> convenioCuadroPrestacionesIterator = atencionmedica.getConvenios().getConvenioCuadroprestacioneses().iterator();
//		while (convenioCuadroPrestacionesIterator.hasNext()) {
//			ConvenioCuadroprestaciones convenioCuadroprestaciones = (ConvenioCuadroprestaciones) convenioCuadroPrestacionesIterator.next();
//			idCuadroPrestaciones = convenioCuadroprestaciones.getId().getCuadroPrestacionId();
//		}

		Collection<Catprestacion> prestacionaseguradors = null;
		Collection<Catprestacion> prestacionaseguradorsAux = null;
		if (idServicio == CatTipoServicioEnum.OTROS.getId()) {
			prestacionaseguradors = prestacionesDao.getPrestacionesEstudios(atencionmedica.getConvenios().getAseguradores().getAseguradorId(), busqueda, idServicio, CatTipoServicioEnum.CONSULTORIO.getId());
			prestacionaseguradorsAux = prestacionesDao.getPrestacionesEstudiosCodigo(atencionmedica.getConvenios().getAseguradores().getAseguradorId(), busqueda, idServicio, CatTipoServicioEnum.CONSULTORIO.getId());
		}else{
			prestacionaseguradors = prestacionesDao.getPrestacionesEstudios(atencionmedica.getConvenios().getAseguradores().getAseguradorId(), busqueda, idServicio, 0);
			prestacionaseguradorsAux = prestacionesDao.getPrestacionesEstudiosCodigo(atencionmedica.getConvenios().getAseguradores().getAseguradorId(), busqueda, idServicio, 0);			
		}

		Collection<AutocompleteVo> autocompleteVos = new ArrayList<>();
		AutocompleteVo autocompleteVo = null;
		
		for (Catprestacion prestacionasegurador : prestacionaseguradors) {
//			Iterator<Prestacionesaseguradorequivalencias> prestacionesAseguradorEquivalenciasIterator = prestacionasegurador.getPrestacionesaseguradorequivalenciases().iterator();
//			while (prestacionesAseguradorEquivalenciasIterator.hasNext()) {
//				Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) prestacionesAseguradorEquivalenciasIterator.next();
//				autocompleteVo = new AutocompleteVo();
//				autocompleteVo.setLabel(prestacionesaseguradorequivalencias.getCatprestacion().getCodigo() + " / "+prestacionesaseguradorequivalencias.getCatprestacion().getDescripcion());
//				autocompleteVo.setValue("" +prestacionesaseguradorequivalencias.getCatprestacion().getPrestacionId());
//				autocompleteVo.setCodigo(prestacionesaseguradorequivalencias.getCatprestacion().getCodigo());
//				autocompleteVos.add(autocompleteVo);				
//			}
			autocompleteVo = new AutocompleteVo();
			autocompleteVo.setLabel(prestacionasegurador.getCodigo() + " / "+prestacionasegurador.getDescripcion());
			autocompleteVo.setValue("" +prestacionasegurador.getPrestacionId());
			autocompleteVo.setCodigo(prestacionasegurador.getCodigo());
			autocompleteVos.add(autocompleteVo);			

		}
		
		for (Catprestacion prestacionasegurador : prestacionaseguradorsAux) {
//			Iterator<Prestacionesaseguradorequivalencias> prestacionesAseguradorEquivalenciasIterator = prestacionasegurador.getPrestacionesaseguradorequivalenciases().iterator();
//			while (prestacionesAseguradorEquivalenciasIterator.hasNext()) {
//				Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) prestacionesAseguradorEquivalenciasIterator.next();
//				autocompleteVo = new AutocompleteVo();
//				autocompleteVo.setLabel(prestacionesaseguradorequivalencias.getCatprestacion().getCodigo() + " / "+prestacionesaseguradorequivalencias.getCatprestacion().getDescripcion());
//				autocompleteVo.setValue("" +prestacionesaseguradorequivalencias.getCatprestacion().getPrestacionId());
//				autocompleteVo.setCodigo(prestacionesaseguradorequivalencias.getCatprestacion().getCodigo());
//				autocompleteVos.add(autocompleteVo);				
//			}
			autocompleteVo = new AutocompleteVo();
			autocompleteVo.setLabel(prestacionasegurador.getCodigo() + " / "+prestacionasegurador.getDescripcion());
			autocompleteVo.setValue("" +prestacionasegurador.getPrestacionId());
			autocompleteVo.setCodigo(prestacionasegurador.getCodigo());
			autocompleteVos.add(autocompleteVo);
		}		
		
		if (autocompleteVos.size()==0) {
			autocompleteVo = new AutocompleteVo();
			autocompleteVo.setLabel("No hay prestaciones coincidentes");
			autocompleteVo.setValue("-1");
			autocompleteVos.add(autocompleteVo);
		}
		
		return autocompleteVos;
	}

	@Override
	public Collection<AutocompleteVo> getPrestacionesSAB(int idConvenio, String busqueda) {		
		Collection<Catprestacion> catprestacions = prestacionesDao.getPrestacionesSAB(busqueda);
		Collection<AutocompleteVo> autocompleteVos = new ArrayList<>();
		AutocompleteVo autocompleteVo = null;
		
		for (Catprestacion catprestacion : catprestacions) {
			autocompleteVo = new AutocompleteVo();
			autocompleteVo.setLabel(catprestacion.getCodigo() + " / "+catprestacion.getDescripcion());
			autocompleteVo.setValue("" +catprestacion.getPrestacionId());
			autocompleteVos.add(autocompleteVo);
		}
		
		if (autocompleteVos.size()==0) {
			autocompleteVo = new AutocompleteVo();
			autocompleteVo.setLabel("No hay prestaciones coincidentes");
			autocompleteVo.setValue("-1");
			autocompleteVos.add(autocompleteVo);
		}
		
		return autocompleteVos;
	}	

}
