package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IEstudiosMedicosDao;
import com.mx.sab.dao.IFoliosDao;
import com.mx.sab.dao.IPrestacionesDao;
import com.mx.sab.enums.FoliosEnum;
import com.mx.sab.form.AtencionEstudiosMedicosForm;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Folio;
import com.mx.sab.model.Prestacionasegurador;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.model.Prestacionesportomar;
import com.mx.sab.service.IAtencionEstudiosMedicosService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.AutocompleteVo;

@Service
@Log4j2
public class AtencionEstudiosMedicosServiceImpl implements IAtencionEstudiosMedicosService {

	@Autowired
	private IEstudiosMedicosDao estudiosMedicosDao;
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private IPrestacionesDao prestacionesDao;
	
	@Autowired
	private IFoliosDao foliosDao;

	@Override
	public void agregaEstudioMedico(AtencionEstudiosMedicosForm atencionEstudiosMedicosForm) {
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(atencionEstudiosMedicosForm.getIdAtencion());
		
		Collection<Prestacionesportomar> prestacionesportomars = estudiosMedicosDao.getPrestacionPorTomarByEstudios(atencionmedica.getAtencionMedicaId(),atencionEstudiosMedicosForm.getIdEstudios());
		AutocompleteVo autocompleteVoAux = new AutocompleteVo();
		List<AutocompleteVo> autocompleteVosAux = new ArrayList<>();
		for (Prestacionesportomar prestacionesportomar : prestacionesportomars) {
//			Iterator<Prestacionesaseguradorequivalencias> equivalenciasIterator = prestacionesportomar.getCatprestacion().getPrestacionesaseguradorequivalenciases().iterator();
//			while(equivalenciasIterator.hasNext()){
//				Prestacionesaseguradorequivalencias equivalencia = equivalenciasIterator.next();
//				if (equivalencia.getPrestacionasegurador().getAseguradores().getAseguradorId() == atencionmedica.getAseguradores().getAseguradorId()) {
//					autocompleteVoAux = new AutocompleteVo();
//					autocompleteVoAux.setValue(""+equivalencia.getPrestacionasegurador().getPrestacionAseguradorId());
//					autocompleteVoAux.setLabel(equivalencia.getPrestacionasegurador().getDescripcion());
//					autocompleteVoAux.setCodigo(equivalencia.getPrestacionasegurador().getCodigo());
//					autocompleteVosAux.add(autocompleteVoAux);
//				}
//			}	
			autocompleteVoAux = new AutocompleteVo();
			autocompleteVoAux.setValue(""+prestacionesportomar.getCatprestacion().getPrestacionId());
			autocompleteVoAux.setLabel(prestacionesportomar.getCatprestacion().getDescripcion());
			autocompleteVoAux.setCodigo(prestacionesportomar.getCatprestacion().getCodigo());
			autocompleteVosAux.add(autocompleteVoAux);			
		}
		
		boolean continuar = true;
		if (atencionEstudiosMedicosForm.getPrestacionesAutocompleteVos()!=null) {
			for (AutocompleteVo autocompleteVo : atencionEstudiosMedicosForm.getPrestacionesAutocompleteVos()) {
				continuar = true;
				if(autocompleteVo.getValue()!=null){
					for (AutocompleteVo autocompleteVoAux2 : autocompleteVosAux) {
						if (autocompleteVoAux2.getValue().equals(autocompleteVo.getValue())) {
							autocompleteVosAux.remove(autocompleteVoAux2);
							continuar = false;
							break;
						}
					}
					
					if (continuar) {
						Prestacionesportomar prestacionesportomar = new Prestacionesportomar();
						prestacionesportomar.setAtencionmedicaByAtencionMedicaId(atencionmedica);
						prestacionesportomar.setCantidad(1);
						prestacionesportomar.setCatpaginasestudios(estudiosMedicosDao.getCatPaginasEstudiosById(atencionEstudiosMedicosForm.getIdEstudios()));
						prestacionesportomar.setOrden("");
						if (!atencionEstudiosMedicosForm.getOrden().equals("")) {
							prestacionesportomar.setOrden(atencionEstudiosMedicosForm.getOrden());	
						}
						
//						Prestacionasegurador prestacionasegurador = prestacionesDao.getPrestacionAseguradorById(Integer.parseInt((String) autocompleteVo.getValue()));
//						
//						Iterator<Prestacionesaseguradorequivalencias> prestacionesAseguradorEquivalenciasIterator = prestacionasegurador.getPrestacionesaseguradorequivalenciases().iterator();
//						while (prestacionesAseguradorEquivalenciasIterator.hasNext()) {
//							Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) prestacionesAseguradorEquivalenciasIterator.next();
//							prestacionesportomar.setCatprestacion(prestacionesaseguradorequivalencias.getCatprestacion());	
//						}
						
						prestacionesportomar.setCatprestacion(prestacionesDao.getCatPrestacionById(Integer.parseInt(autocompleteVo.getValue())));
						estudiosMedicosDao.savePrestacionesPorTomar(prestacionesportomar);
						if (atencionEstudiosMedicosForm.getOrden().equals("")) {
							String orden = "";
							Folio folio = null;
							if (atencionEstudiosMedicosForm.getIdEstudios()==1) {
								folio = foliosDao.getFolioById(FoliosEnum.ORDEN_DE_GABINETE.getId(),atencionmedica.getLugaresdeatencion().getLugarDeAtencionId());
							}else if (atencionEstudiosMedicosForm.getIdEstudios()==2) {
								folio = foliosDao.getFolioById(FoliosEnum.ORDEN_DE_EXAMENES_DE_LABORATORIO.getId(),atencionmedica.getLugaresdeatencion().getLugarDeAtencionId());
							}else if (atencionEstudiosMedicosForm.getIdEstudios()==3) {
								folio = foliosDao.getFolioById(FoliosEnum.ORDEN_DE_OTROS_SERVICIOS.getId(),atencionmedica.getLugaresdeatencion().getLugarDeAtencionId());
							}else {
								folio = foliosDao.getFolioById(FoliosEnum.CONSULTORIO.getId(),atencionmedica.getLugaresdeatencion().getLugarDeAtencionId());
							}
							folio.setConsecutivo(folio.getConsecutivo()+1);
							orden = ""+folio.getConsecutivo();
							foliosDao.updateFolio(folio);
							atencionEstudiosMedicosForm.setOrden(orden);
							prestacionesportomar.setOrden(orden);
							estudiosMedicosDao.updatePrestacionPorTomar(prestacionesportomar);
						}
					}
				}
			}	
		}

		
		for (AutocompleteVo autocompleteVoAux2 : autocompleteVosAux) {
//			Prestacionasegurador prestacionasegurador = prestacionesDao.getPrestacionAseguradorById(Integer.parseInt(autocompleteVoAux2.getValue()));
//			Iterator<Prestacionesaseguradorequivalencias> prestacionesaseguradorequivalenciasIterator = prestacionasegurador.getPrestacionesaseguradorequivalenciases().iterator();
//			while (prestacionesaseguradorequivalenciasIterator.hasNext()) {
//				Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) prestacionesaseguradorequivalenciasIterator.next();
//				Prestacionesportomar prestacionesportomar = estudiosMedicosDao.getPrestacionPorTomar(atencionmedica.getAtencionMedicaId(), prestacionesaseguradorequivalencias.getCatprestacion().getPrestacionId());
//				estudiosMedicosDao.deletePrestacionesPorTomar(prestacionesportomar);
//			}
			Prestacionesportomar prestacionesportomar = estudiosMedicosDao.getPrestacionPorTomar(atencionmedica.getAtencionMedicaId(), Integer.parseInt(autocompleteVoAux2.getValue()));
			estudiosMedicosDao.deletePrestacionesPorTomar(prestacionesportomar);
		}
	}

	@Override
	public void inicializaEstudiosMedicosForm(AtencionEstudiosMedicosForm atencionEstudiosMedicosForm) {
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(atencionEstudiosMedicosForm.getIdAtencion());
		Collection<Prestacionesportomar> prestacionesportomars = estudiosMedicosDao.getPrestacionPorTomarByEstudios(atencionmedica.getAtencionMedicaId(),atencionEstudiosMedicosForm.getIdEstudios());
		AutocompleteVo autocompleteVo = new AutocompleteVo();
		List<AutocompleteVo> autocompleteVos = new ArrayList<>();
		for (Prestacionesportomar prestacionesportomar : prestacionesportomars) {
//			Iterator<Prestacionesaseguradorequivalencias> equivalenciasIterator = prestacionesportomar.getCatprestacion().getPrestacionesaseguradorequivalenciases().iterator();
//			while(equivalenciasIterator.hasNext()){
//				Prestacionesaseguradorequivalencias equivalencia = equivalenciasIterator.next();
//				if (equivalencia.getPrestacionasegurador().getAseguradores().getAseguradorId() == atencionmedica.getAseguradores().getAseguradorId()) {
//					autocompleteVo = new AutocompleteVo();
//					autocompleteVo.setValue(""+equivalencia.getPrestacionasegurador().getPrestacionAseguradorId());
//					autocompleteVo.setLabel(equivalencia.getPrestacionasegurador().getDescripcion());
//					autocompleteVo.setCodigo(equivalencia.getPrestacionasegurador().getCodigo());
//					autocompleteVos.add(autocompleteVo);
//					atencionEstudiosMedicosForm.setOrden(prestacionesportomar.getOrden());
//				}
//			}
			autocompleteVo = new AutocompleteVo();
			autocompleteVo.setValue(""+prestacionesportomar.getCatprestacion().getPrestacionId());
			autocompleteVo.setLabel(prestacionesportomar.getCatprestacion().getDescripcion());
			autocompleteVo.setCodigo(prestacionesportomar.getCatprestacion().getCodigo());
			autocompleteVos.add(autocompleteVo);
			atencionEstudiosMedicosForm.setOrden(prestacionesportomar.getOrden());
		}
		atencionEstudiosMedicosForm.setPrestacionesAutocompleteVos(autocompleteVos);
	}
	
	@Override
	public Collection<AutocompleteVo> getEstudios(int idAtencion, int idEstudios) {
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(idAtencion);
		Collection<Prestacionesportomar> prestacionesportomars = estudiosMedicosDao.getPrestacionPorTomarByEstudios(atencionmedica.getAtencionMedicaId(), idEstudios);
		AutocompleteVo autocompleteVo = new AutocompleteVo();
		List<AutocompleteVo> autocompleteVos = new ArrayList<>();
		for (Prestacionesportomar prestacionesportomar : prestacionesportomars) {
//			Iterator<Prestacionesaseguradorequivalencias> equivalenciasIterator = prestacionesportomar.getCatprestacion().getPrestacionesaseguradorequivalenciases().iterator();
//			while(equivalenciasIterator.hasNext()){
//				Prestacionesaseguradorequivalencias equivalencia = equivalenciasIterator.next();
//				if (equivalencia.getPrestacionasegurador().getAseguradores().getAseguradorId() == atencionmedica.getAseguradores().getAseguradorId()) {
//					autocompleteVo = new AutocompleteVo();
//					autocompleteVo.setValue(""+equivalencia.getPrestacionasegurador().getPrestacionAseguradorId());
//					autocompleteVo.setLabel(equivalencia.getPrestacionasegurador().getDescripcion());
//					autocompleteVo.setCodigo(equivalencia.getPrestacionasegurador().getCodigo());
//					autocompleteVos.add(autocompleteVo);
//				}
//			}
			
			autocompleteVo = new AutocompleteVo();
			autocompleteVo.setValue(""+prestacionesportomar.getCatprestacion().getPrestacionId());
			autocompleteVo.setLabel(prestacionesportomar.getCatprestacion().getDescripcion());
			autocompleteVo.setCodigo(prestacionesportomar.getCatprestacion().getCodigo());
			autocompleteVos.add(autocompleteVo);			
		}
		return autocompleteVos;
	}

	@Override
	public Collection<AutocompleteVo> getEstudiosAsegurador(int idAtencion, int idEstudios) {
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(idAtencion);
		Collection<Prestacionesportomar> prestacionesportomars = estudiosMedicosDao.getPrestacionPorTomarByEstudios(atencionmedica.getAtencionMedicaId(), idEstudios);
		AutocompleteVo autocompleteVo = new AutocompleteVo();
		List<AutocompleteVo> autocompleteVos = new ArrayList<>();
		for (Prestacionesportomar prestacionesportomar : prestacionesportomars) {
			Iterator<Prestacionesaseguradorequivalencias> equivalenciasIterator = prestacionesportomar.getCatprestacion().getPrestacionesaseguradorequivalenciases().iterator();
			while(equivalenciasIterator.hasNext()){
				Prestacionesaseguradorequivalencias equivalencia = equivalenciasIterator.next();
				if (equivalencia.getPrestacionasegurador().getAseguradores().getAseguradorId() == atencionmedica.getAseguradores().getAseguradorId()) {
					autocompleteVo = new AutocompleteVo();
					autocompleteVo.setValue(""+equivalencia.getPrestacionasegurador().getPrestacionAseguradorId());
					autocompleteVo.setLabel(equivalencia.getPrestacionasegurador().getDescripcion());
					autocompleteVo.setCodigo(equivalencia.getPrestacionasegurador().getCodigo());
					autocompleteVos.add(autocompleteVo);
				}
			}			
		}
		return autocompleteVos;
	}	
}
