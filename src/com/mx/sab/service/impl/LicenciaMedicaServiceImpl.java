package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.ILicenciaMedicaDao;
import com.mx.sab.form.LicenciaMedicaForm;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catlicmedicacaracteres;
import com.mx.sab.model.Catlicmedicamotivos;
import com.mx.sab.model.Catlicmedicatiposservicio;
import com.mx.sab.model.Licenciamedica;
import com.mx.sab.service.ILicenciaMedicaService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.FechaLicenciaMedica;

@Service
@Log4j2
public class LicenciaMedicaServiceImpl implements ILicenciaMedicaService{
	
	@Autowired
	private ILicenciaMedicaDao licenciaMedicaDao;
	
	@Autowired
	private IAgendaDao agendaDao;	

	@Override
	public Collection<Catlicmedicamotivos> getCatLicMedicaMotivos() {
		return licenciaMedicaDao.getCatLicMedicaMotivos();
	}

	@Override
	public Collection<Catlicmedicacaracteres> getCatLicMedicaCaracteres() {
		return licenciaMedicaDao.getCatLicMedicaCaracteres();
	}

	@Override
	public Collection<Catlicmedicatiposservicio> getCatLicMedicaTiposServicio() {
		return licenciaMedicaDao.getCatLicMedicaTiposServicio();
	}

	@Override
	public void inicializaForm(LicenciaMedicaForm licenciaMedicaForm) {
		Agenda agenda = agendaDao.getAgendaById(licenciaMedicaForm.getIdAgenda());
		if (agenda.getAtencionmedica().getFechaAtendidoMedico()!=null) {
			licenciaMedicaForm.setFinalizoAtencionMedica(true);
		}
		Licenciamedica licenciamedica = licenciaMedicaDao.getLicenciaMedicaByIdAtencionMedica(agenda.getAtencionmedica().getAtencionMedicaId());
		if (licenciamedica!=null) {
			licenciaMedicaForm.setDias(licenciamedica.getNumDias());
			licenciaMedicaForm.setDiasLetra(licenciamedica.getLetraDias());
			licenciaMedicaForm.setIdLicMedicaCaracter(licenciamedica.getCatlicmedicacaracteres().getLicMedicaCaracterId());
			licenciaMedicaForm.setIdLicMedicaMotivo(licenciamedica.getCatlicmedicamotivos().getLicMedicaMotivoId());
			licenciaMedicaForm.setIdLicMedicaTipoServicio(licenciamedica.getCatlicmedicatiposservicio().getLicMedicaTipoServicioId());
			licenciaMedicaForm.setVigenteDesde(FormatUtil.getDate(licenciamedica.getFechaInicio()));
			licenciaMedicaForm.setVigenteHasta(FormatUtil.getDate(licenciamedica.getFechaTermino()));
			licenciaMedicaForm.setEditar(true);
		}else{
			licenciaMedicaForm.setIdLicMedicaCaracter(1);
			licenciaMedicaForm.setIdLicMedicaMotivo(1);
			licenciaMedicaForm.setIdLicMedicaTipoServicio(1);	
		}
	}

	@Override
	public FechaLicenciaMedica verificaFechas(String vigenteDesde, String vigenteHasta) {
		FechaLicenciaMedica fechaLicenciaMedica = new FechaLicenciaMedica();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);
		Date fechaInicio = FormatUtil.getDate(vigenteDesde);
		Date fechaFin = FormatUtil.getDate(vigenteHasta);
		Date fechaActual = c.getTime();
		
		if (fechaInicio.before(fechaActual)) {
			fechaLicenciaMedica.setError("El periodo de la licencia inicio no puede ser menor a la fecha actual");
		}
		
		if (fechaFin.before(fechaActual)) {
			fechaLicenciaMedica.setError("El periodo de la licencia fin no puede ser menor a la fecha actual");
		}
		
		if (fechaInicio.after(fechaFin)) {
			fechaLicenciaMedica.setError("El periodo de la licencia inicio no puede ser mayor a la fecha del periodo de la licencia fin");
		}
		
		if (fechaLicenciaMedica.getError() == null) {
			long diff = fechaFin.getTime() - fechaInicio.getTime();
			long days = (24 * 60 * 60 * 1000);
			long diffdays = diff / days;
			diffdays++;
			if (diffdays<7) {
				fechaLicenciaMedica.setDias((int) diffdays);
				switch (""+diffdays) {
				case "1":
					fechaLicenciaMedica.setDiasLetra("UNO");
					break;
				case "2":
					fechaLicenciaMedica.setDiasLetra("DOS");
					break;
				case "3":
					fechaLicenciaMedica.setDiasLetra("TRES");
					break;
				case "4":
					fechaLicenciaMedica.setDiasLetra("CUATRO");
					break;
				case "5":
					fechaLicenciaMedica.setDiasLetra("CINCO");
					break;
				case "6":
					fechaLicenciaMedica.setDiasLetra("SEIS");
					break;
				case "7":
					fechaLicenciaMedica.setDiasLetra("SIETE");
					break;
				default:
					break;
				}
				
			}else {
				fechaLicenciaMedica.setError("La fecha no puede ser mayor a 7 dias");	
			}
		}
		
		return fechaLicenciaMedica;
	}

	@Override
	public void save(LicenciaMedicaForm licenciaMedicaForm) {
		Licenciamedica licenciamedica = new Licenciamedica();
		Agenda agenda = agendaDao.getAgendaById(licenciaMedicaForm.getIdAgenda());
		if (licenciaMedicaForm.isEditar()) {
			licenciamedica = licenciaMedicaDao.getLicenciaMedicaByIdAtencionMedica(agenda.getAtencionmedica().getAtencionMedicaId());
			licenciamedica.setCatlicmedicamotivos(licenciaMedicaDao.getCatLicMedicaMotivosById(licenciaMedicaForm.getIdLicMedicaMotivo()));
			licenciamedica.setCatlicmedicacaracteres(licenciaMedicaDao.getCatLicMedicaCaracteresById(licenciaMedicaForm.getIdLicMedicaCaracter()));
			licenciamedica.setCatlicmedicatiposservicio(licenciaMedicaDao.getCatLicMedicaTiposServicioById(licenciaMedicaForm.getIdLicMedicaTipoServicio()));
			licenciamedica.setFechaInicio(FormatUtil.getDate(licenciaMedicaForm.getVigenteDesde()));
			licenciamedica.setFechaTermino(FormatUtil.getDate(licenciaMedicaForm.getVigenteHasta()));
			licenciamedica.setAtencionmedica(agenda.getAtencionmedica());
			licenciamedica.setNumDias(licenciaMedicaForm.getDias());
			licenciamedica.setLetraDias(licenciaMedicaForm.getDiasLetra());			
			licenciaMedicaDao.update(licenciamedica);
		}else {
			licenciamedica.setCatlicmedicamotivos(licenciaMedicaDao.getCatLicMedicaMotivosById(licenciaMedicaForm.getIdLicMedicaMotivo()));
			licenciamedica.setCatlicmedicacaracteres(licenciaMedicaDao.getCatLicMedicaCaracteresById(licenciaMedicaForm.getIdLicMedicaCaracter()));
			licenciamedica.setCatlicmedicatiposservicio(licenciaMedicaDao.getCatLicMedicaTiposServicioById(licenciaMedicaForm.getIdLicMedicaTipoServicio()));
			licenciamedica.setFechaEmision(new Date());
			licenciamedica.setFechaInicio(FormatUtil.getDate(licenciaMedicaForm.getVigenteDesde()));
			licenciamedica.setFechaTermino(FormatUtil.getDate(licenciaMedicaForm.getVigenteHasta()));
			licenciamedica.setAtencionmedica(agenda.getAtencionmedica());
			
			Licenciamedica licenciamedicaAux = licenciaMedicaDao.getLicenciaMedicaFolio();
			int folio = 1;
			if (licenciamedicaAux!=null) {
				folio = Integer.parseInt(licenciamedicaAux.getFolio());
				folio++;
			}
			String folioResponse = FormatUtil.agregaCeros(""+folio, 5);
			
			licenciamedica.setFolio(folioResponse);
			licenciamedica.setNumDias(licenciaMedicaForm.getDias());
			licenciamedica.setLetraDias(licenciaMedicaForm.getDiasLetra());
			licenciaMedicaDao.save(licenciamedica);	
		}
		
		
	}

	@Override
	public void getLicenciasMedicas(LicenciaMedicaForm licenciaMedicaForm) {
		Agenda agenda = agendaDao.getAgendaById(licenciaMedicaForm.getIdAgenda());
		Collection<Agenda> agendas = licenciaMedicaDao.getAtencionesMedicas(agenda.getAfiliado().getAfiliadoId());
		Collection<Integer> atencionMedicaIds = new ArrayList<>();
		for (Agenda agendaAux : agendas) {
			atencionMedicaIds.add(agendaAux.getAtencionmedica().getAtencionMedicaId());
		}
		
		Collection<Licenciamedica> licenciamedicas = licenciaMedicaDao.getLicenciasMedicas(atencionMedicaIds);
		licenciaMedicaForm.setLicenciamedicas(licenciamedicas);
	}

}
