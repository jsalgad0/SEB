package com.mx.sab.service.impl;

import java.sql.Time;
import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IContrareferenciaDao;
import com.mx.sab.dao.IListaPacientesDao;
import com.mx.sab.dao.IAtencionSolicitudReferenciaDao;
import com.mx.sab.form.ContrareferenciaForm;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Contrareferencia;
import com.mx.sab.model.Solicitudreferencia;
import com.mx.sab.service.IContrareferenciaService;
import com.mx.sab.util.FormatUtil;

@Service
@Log4j2
public class ContrareferenciaServiceImpl implements IContrareferenciaService {

	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private IContrareferenciaDao contrareferenciaDao;
	
	@Autowired
	private IAtencionSolicitudReferenciaDao solicitudReferenciaDao;
	
	@Autowired
	private IListaPacientesDao listaPacientesDao;

	@Override
	public void inicializaContrareferenciaForm(ContrareferenciaForm contrareferenciaForm) {
		Agenda agenda = agendaDao.getAgendaById(contrareferenciaForm.getIdAgenda());
		if (agenda.getAtencionmedica().getFechaAtendidoMedico()!=null) {
			contrareferenciaForm.setFinalizoAtencionMedica(true);
		}		
		Solicitudreferencia solicitudreferencia = solicitudReferenciaDao.getSolicitudReferenciaByIdAtencionMedica(agenda.getAtencionmedica().getAtencionMedicaId());
		contrareferenciaForm.setLugarAtencion(solicitudreferencia.getLugaresdeatencion().getDescripcion());
		Contrareferencia contrareferencia = contrareferenciaDao.getContrareferenciaByIdSolicitud(solicitudreferencia.getSolicitudReferenciaId());
		if (contrareferencia!=null) {
			contrareferenciaForm.setContrareferencia(contrareferencia);
			contrareferenciaForm.setFecha(FormatUtil.getDate(contrareferencia.getFechaContrareferencia()));
			contrareferenciaForm.setEditar(true);
			contrareferenciaForm.setDiagnostico(contrareferencia.getCatcies10().getDescripcion());
			contrareferenciaForm.setIdDiagnostico(contrareferencia.getCatcies10().getCie10id());
		}
	}

	@Override
	public void save(ContrareferenciaForm contrareferenciaForm) {
		Agenda agenda = agendaDao.getAgendaById(contrareferenciaForm.getIdAgenda());
		Solicitudreferencia solicitudreferencia = solicitudReferenciaDao.getSolicitudReferenciaByIdAtencionMedica(agenda.getAtencionmedica().getAtencionMedicaId());
		
		if (contrareferenciaForm.isEditar()) {
			Contrareferencia contrareferencia = contrareferenciaDao.getContrareferenciaById(contrareferenciaForm.getContrareferencia().getContrareferenciaId());
			contrareferencia.setFechaContrareferencia(FormatUtil.getDate(contrareferenciaForm.getFecha()));
			contrareferencia.setFecha(new Date());
			contrareferencia.setHora(new Time(new Date().getTime()));
			contrareferencia.setCatcies10(listaPacientesDao.getCatCies10ById(contrareferenciaForm.getIdDiagnostico()));
			contrareferencia.setMotivoContrareferencia(contrareferenciaForm.getContrareferencia().getMotivoContrareferencia());
			contrareferencia.setNumInterConsultas(contrareferenciaForm.getContrareferencia().getNumInterConsultas());
			contrareferencia.setNumConsultas(contrareferenciaForm.getContrareferencia().getNumConsultas());
			contrareferencia.setResultadosValoracion(contrareferenciaForm.getContrareferencia().getResultadosValoracion());
			contrareferencia.setIndicaciones(contrareferenciaForm.getContrareferencia().getIndicaciones());
			contrareferenciaDao.update(contrareferencia);			
		}else {
			contrareferenciaForm.getContrareferencia().setFechaContrareferencia(FormatUtil.getDate(contrareferenciaForm.getFecha()));
			contrareferenciaForm.getContrareferencia().setFecha(new Date());
			contrareferenciaForm.getContrareferencia().setHora(new Time(new Date().getTime()));
			contrareferenciaForm.getContrareferencia().setCatcies10(listaPacientesDao.getCatCies10ById(contrareferenciaForm.getIdDiagnostico()));
			contrareferenciaForm.getContrareferencia().setSolicitudreferencia(solicitudreferencia);
			contrareferenciaDao.save(contrareferenciaForm.getContrareferencia());	
		}
		
	}
}
