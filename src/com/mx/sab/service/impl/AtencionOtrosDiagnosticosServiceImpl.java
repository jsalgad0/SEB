package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IAtencionOtrosDiagnosticosDao;
import com.mx.sab.form.AtencionOtrosDiagnosticosForm;
import com.mx.sab.model.Catcies10;
import com.mx.sab.model.Cattipodiagnostico;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.NotamedicaCies10;
import com.mx.sab.model.NotamedicaCies10Id;
import com.mx.sab.service.IAtencionOtrosDiagnosticosService;
import com.mx.sab.vo.DiagnosticoVo;
import com.mx.sab.vo.RecetasVo;

@Service
@Log4j2
public class AtencionOtrosDiagnosticosServiceImpl implements IAtencionOtrosDiagnosticosService {
	
	@Autowired 
	private IAtencionOtrosDiagnosticosDao atencionOtrosDiagnosticosDao;
	
	@Autowired
	private IAgendaDao agendaDao;
	
	
	@Override
	public Collection<DiagnosticoVo> getDiagnosticos(AtencionOtrosDiagnosticosForm atencionOtrosDiagnosticosForm) {
		Collection<DiagnosticoVo> diagnosticoVos = new ArrayList<>();
		atencionOtrosDiagnosticosForm.setIdTipoDiagnostico(1);
		Collection<NotamedicaCies10> notamedicaCies10s = atencionOtrosDiagnosticosDao.getDiagnosticosByIdAtencion(atencionOtrosDiagnosticosForm.getIdAtencion());
		DiagnosticoVo diagnosticoVo = null;
		for (NotamedicaCies10 notamedicaCies10 : notamedicaCies10s) {
			if (notamedicaCies10.getPrincipal()!=1) {
				diagnosticoVo = new DiagnosticoVo();
				diagnosticoVo.setDiagnostico(notamedicaCies10.getCatcies10().getDescripcion());
				diagnosticoVo.setCodigo(notamedicaCies10.getCatcies10().getCodigo());
				diagnosticoVo.setIdDiagnostico(notamedicaCies10.getCatcies10().getCie10id());
				diagnosticoVo.setIdTipoDiagnostico(notamedicaCies10.getCattipodiagnostico().getId());
				diagnosticoVos.add(diagnosticoVo);				
			}else{
				atencionOtrosDiagnosticosForm.setIdDiagnosticoPrincipal(notamedicaCies10.getCatcies10().getCie10id());
			}
		}
		return diagnosticoVos;
	}
	
	@Override
	public Collection<DiagnosticoVo> getTodosDiagnosticos(AtencionOtrosDiagnosticosForm atencionOtrosDiagnosticosForm) {
		Collection<DiagnosticoVo> diagnosticoVos = new ArrayList<>();
		atencionOtrosDiagnosticosForm.setIdTipoDiagnostico(1);
		Collection<NotamedicaCies10> notamedicaCies10s = atencionOtrosDiagnosticosDao.getDiagnosticosByIdAtencion(atencionOtrosDiagnosticosForm.getIdAtencion());
		DiagnosticoVo diagnosticoVo = null;
		for (NotamedicaCies10 notamedicaCies10 : notamedicaCies10s) {
			diagnosticoVo = new DiagnosticoVo();
			diagnosticoVo.setDiagnostico(notamedicaCies10.getCatcies10().getDescripcion());
			diagnosticoVo.setCodigo(notamedicaCies10.getCatcies10().getCodigo());
			diagnosticoVo.setIdDiagnostico(notamedicaCies10.getCatcies10().getCie10id());
			diagnosticoVo.setIdTipoDiagnostico(notamedicaCies10.getCattipodiagnostico().getId());
			diagnosticoVos.add(diagnosticoVo);				
		}
		return diagnosticoVos;
	}

	@Override
	public Collection<DiagnosticoVo> getDiagnosticos(String busqueda) {
		Collection<DiagnosticoVo> diagnosticoVos = new ArrayList<>();
		DiagnosticoVo diagnosticoVo = null;
		Collection<Catcies10> catcies10sCodigo = atencionOtrosDiagnosticosDao.getDiagnosticosByCodigo(busqueda);
		Collection<Catcies10> catcies10sDescripcion = atencionOtrosDiagnosticosDao.getDiagnosticosByDescripcion(busqueda);
		
		for (Catcies10 catcies10 : catcies10sDescripcion) {
			diagnosticoVo = new DiagnosticoVo();
			diagnosticoVo.setDiagnostico(catcies10.getDescripcion());
			diagnosticoVo.setCodigo(catcies10.getCodigo());
			diagnosticoVo.setIdDiagnostico(catcies10.getCie10id());
			diagnosticoVos.add(diagnosticoVo);
		}
		
		for (Catcies10 catcies10 : catcies10sCodigo) {
			diagnosticoVo = new DiagnosticoVo();
			diagnosticoVo.setDiagnostico(catcies10.getDescripcion());
			diagnosticoVo.setCodigo(catcies10.getCodigo());
			diagnosticoVo.setIdDiagnostico(catcies10.getCie10id());
			diagnosticoVos.add(diagnosticoVo);			
		}
		
		if (diagnosticoVos.size()==0) {
			diagnosticoVo = new DiagnosticoVo();
			diagnosticoVo.setDiagnostico("No hay diagnosticos coincidentes");
			diagnosticoVo.setIdDiagnostico(-1);
			diagnosticoVos.add(diagnosticoVo);
		}
		return diagnosticoVos;
	}

	@Override
	public Collection<Cattipodiagnostico> getCatTipoDiagnosticos() {
		return atencionOtrosDiagnosticosDao.getCatTipoDiagnosticos();
	}

	@Override
	public void saveDiagnosticos(AtencionOtrosDiagnosticosForm atencionOtrosDiagnosticosForm, Collection<DiagnosticoVo> diagnosticoVos) {
		boolean diagnosticoSeleccionado = false;
		Notamedica notamedica = agendaDao.getNotaMedicaByIdAtencion(atencionOtrosDiagnosticosForm.getIdAtencion());
		for (DiagnosticoVo diagnosticoVo : atencionOtrosDiagnosticosForm.getDiagnosticoVos()) {
			if (diagnosticoVo!=null) {
				diagnosticoSeleccionado = false;
				Iterator<DiagnosticoVo> diagnosticoVoIterator = diagnosticoVos.iterator();
				while (diagnosticoVoIterator.hasNext()) {
					DiagnosticoVo diagnosticoVoAux = (DiagnosticoVo) diagnosticoVoIterator.next();
					if (diagnosticoVo.getIdDiagnostico() == diagnosticoVoAux.getIdDiagnostico()) {
						diagnosticoSeleccionado = true;
						diagnosticoVoIterator.remove();
						if (diagnosticoVo.getIdTipoDiagnostico()==diagnosticoVoAux.getIdTipoDiagnostico()) {
							break;
						}else{
							diagnosticoVoAux.setIdNotaMedica(notamedica.getNotaMedicaId());
							NotamedicaCies10 notamedicaCies10 = atencionOtrosDiagnosticosDao.getNotaMedicaCies10(diagnosticoVoAux);
							notamedicaCies10.setCattipodiagnostico(atencionOtrosDiagnosticosDao.getCatTipoDiagnosticoById(diagnosticoVo.getIdTipoDiagnostico()));
							atencionOtrosDiagnosticosDao.update(notamedicaCies10);
							break;
						}
					}
				}
				if (!diagnosticoSeleccionado) {
					NotamedicaCies10 notamedicaCies10 = new NotamedicaCies10();
					notamedicaCies10.setCatcies10(atencionOtrosDiagnosticosDao.getCatCies10ById(diagnosticoVo.getIdDiagnostico()));
					notamedicaCies10.setCattipodiagnostico(atencionOtrosDiagnosticosDao.getCatTipoDiagnosticoById(diagnosticoVo.getIdTipoDiagnostico()));
					notamedicaCies10.setNotamedica(notamedica);
					notamedicaCies10.setPrincipal(0);
					NotamedicaCies10Id notamedicaCies10Id = new NotamedicaCies10Id();
					notamedicaCies10Id.setCie10id(notamedicaCies10.getCatcies10().getCie10id());
					notamedicaCies10Id.setNotaMedicaId(notamedica.getNotaMedicaId());
					notamedicaCies10.setId(notamedicaCies10Id);
					atencionOtrosDiagnosticosDao.save(notamedicaCies10);
				}
			}
		}
		
		for (DiagnosticoVo diagnosticoVoAux : diagnosticoVos) {
			diagnosticoVoAux.setIdNotaMedica(notamedica.getNotaMedicaId());
			NotamedicaCies10 notamedicaCies10 = atencionOtrosDiagnosticosDao.getNotaMedicaCies10(diagnosticoVoAux);
			atencionOtrosDiagnosticosDao.delete(notamedicaCies10);
		}
	}

	
}
