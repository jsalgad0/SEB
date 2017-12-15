package com.mx.sab.service.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IFoliosDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.IMedicoLicenciaMedicaDao;
import com.mx.sab.dao.INotaMedicaDao;
import com.mx.sab.dao.ISignosDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.dao.impl.NotaMedicaDaoImpl;
import com.mx.sab.enums.FoliosEnum;
import com.mx.sab.form.AntecedentesFamiliaresForm;
import com.mx.sab.form.LicenciaMedicaForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.form.MedicoLicenciaMedicaForm;
import com.mx.sab.form.NotaMedicaForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catcies10;
import com.mx.sab.model.Catlicmedicacaracteres;
import com.mx.sab.model.Catlicmedicamotivos;
import com.mx.sab.model.Catlicmedicatiposservicio;
import com.mx.sab.model.Cattipodiagnostico;
import com.mx.sab.model.Cattipofamilia;
import com.mx.sab.model.Folio;
import com.mx.sab.model.HcAntecedentesfamiliares;
import com.mx.sab.model.HcFasedelciclo;
import com.mx.sab.model.Historiaclinica;
import com.mx.sab.model.Licenciamedica;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.NotamedicaCies10;
import com.mx.sab.model.NotamedicaCies10Id;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.IMedicoLicenciaMedicaService;
import com.mx.sab.service.IMedicoNotaMedicaService;
import com.mx.sab.util.CantLetras;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.CatLicMedicaCaracteresVo;
import com.mx.sab.vo.CatLicMedicaMotivosVo;
import com.mx.sab.vo.CatLicMedicaTipoServicioVo;
import com.mx.sab.vo.CatTipoFamiliaVo;
import com.mx.sab.vo.DiagnosticoVo;
import com.mx.sab.vo.LicenciaMedicaVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class MedicoLicenciaMedicaServiceImpl implements IMedicoLicenciaMedicaService {
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Autowired
	private IMedicoLicenciaMedicaDao medicoLicenciaMedicaDao;
	
	@Autowired
	private INotaMedicaDao notaMedicaDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IFoliosDao foliosDao;	
	
	@Override
	public Collection<CatLicMedicaCaracteresVo> getCatLicMedicaCaracteres() {
		Collection<Catlicmedicacaracteres> catlicmedicacaracteres = medicoLicenciaMedicaDao.getLicMedicaCaracteres();
		Collection<CatLicMedicaCaracteresVo> catLicMedicaCaracteresVos = new ArrayList<>();
		for (Catlicmedicacaracteres catlicmedicacaracter : catlicmedicacaracteres) {
			CatLicMedicaCaracteresVo catLicMedicaCaracteresVo = new CatLicMedicaCaracteresVo();
			catLicMedicaCaracteresVo.setIdLicMedicaCaracter(catlicmedicacaracter.getLicMedicaCaracterId());
			catLicMedicaCaracteresVo.setDescripcion(catlicmedicacaracter.getLicMedicaCaracter());
			catLicMedicaCaracteresVos.add(catLicMedicaCaracteresVo);
		}
		return catLicMedicaCaracteresVos;
	}
	
	@Override
	public Collection<CatLicMedicaMotivosVo> getCatLicMedicaMotivos() {
		Collection<Catlicmedicamotivos> catlicmedicamotivos = medicoLicenciaMedicaDao.getLicMedicaMotivos();
		Collection<CatLicMedicaMotivosVo> catLicMedicaMotivosVos = new ArrayList<>();
		for (Catlicmedicamotivos catlicmedicamotivo : catlicmedicamotivos) {
			CatLicMedicaMotivosVo catLicMedicaMotivosVo = new CatLicMedicaMotivosVo();
			catLicMedicaMotivosVo.setIdLicMedicaMotivo(catlicmedicamotivo.getLicMedicaMotivoId());
			catLicMedicaMotivosVo.setDescripcion(catlicmedicamotivo.getLicMedicaMotivo());
			catLicMedicaMotivosVos.add(catLicMedicaMotivosVo);
		}
		return catLicMedicaMotivosVos;
	}
	
	@Override
	public Collection<CatLicMedicaTipoServicioVo> getCatLicMedicaTipoServicio() {
		Collection<Catlicmedicatiposservicio> catlicmedicatiposservicios = medicoLicenciaMedicaDao.getLicMedicaTiposServicio();
		Collection<CatLicMedicaTipoServicioVo> catLicMedicaTipoServicioVos = new ArrayList<>();
		for (Catlicmedicatiposservicio catlicmedicatiposservicio : catlicmedicatiposservicios) {
			CatLicMedicaTipoServicioVo catLicMedicaTipoServicioVo = new CatLicMedicaTipoServicioVo();
			catLicMedicaTipoServicioVo.setIdLicMedicaTipoServicio(catlicmedicatiposservicio.getLicMedicaTipoServicioId());
			catLicMedicaTipoServicioVo.setDescripcion(catlicmedicatiposservicio.getLicMedicaTipoServicio());
			catLicMedicaTipoServicioVos.add(catLicMedicaTipoServicioVo);
		}
		return catLicMedicaTipoServicioVos;
	}	
	
	@Override
	public MedicoLicenciaMedicaForm getLicenciaMedica(MedicoAtencionPacientesForm medicoAtencionPacientesForm, UserInfo userInfo) {
	
		MedicoLicenciaMedicaForm medicoLicenciaMedicaForm = new MedicoLicenciaMedicaForm();
		Atencionmedica atencionMedica = agendaDao.getAtencionMedicaById(medicoAtencionPacientesForm.getIdAtencion());
		medicoLicenciaMedicaForm.setAtencionMedicaId(atencionMedica.getAtencionMedicaId());
		Licenciamedica licenciamedica = medicoLicenciaMedicaDao.getLicenciaMedica(atencionMedica.getAtencionMedicaId());
		Notamedica notamedica = notaMedicaDao.getNotaMedicaByIdAtencion(atencionMedica.getAtencionMedicaId());
		NotamedicaCies10 cies10 = notaMedicaDao.getNotaMedicaCies10ByIdNotaMedica(notamedica.getNotaMedicaId());
		medicoLicenciaMedicaForm.setFechaEmision(FormatUtil.getDate());
		medicoLicenciaMedicaForm.setCie10(cies10.getCatcies10().getCie10id());
		medicoLicenciaMedicaForm.setDiagnostico(cies10.getCatcies10().getDescripcion());
		if(licenciamedica != null){
			medicoLicenciaMedicaForm.setLicenciaMedicaId(licenciamedica.getLicenciaMedicaId());
			medicoLicenciaMedicaForm.setFechaEmision(FormatUtil.getDate(licenciamedica.getFechaEmision()));
			medicoLicenciaMedicaForm.setDias(licenciamedica.getNumDias()+"");
			medicoLicenciaMedicaForm.setFechaInicio(FormatUtil.getDate(licenciamedica.getFechaInicio()));
			medicoLicenciaMedicaForm.setFechaFin(FormatUtil.getDate(licenciamedica.getFechaTermino()));
			medicoLicenciaMedicaForm.setDiasLetra(licenciamedica.getLetraDias());
			medicoLicenciaMedicaForm.setIdLicMedicaMotivo(licenciamedica.getCatlicmedicamotivos().getLicMedicaMotivoId());
			medicoLicenciaMedicaForm.setIdLicMedicaCaracter(licenciamedica.getCatlicmedicacaracteres().getLicMedicaCaracterId());
			medicoLicenciaMedicaForm.setIdLicMedicaTipoServicio(licenciamedica.getCatlicmedicatiposservicio().getLicMedicaTipoServicioId());
		}else{
			medicoLicenciaMedicaForm.setIdLicMedicaMotivo(1);
			medicoLicenciaMedicaForm.setIdLicMedicaCaracter(1);
			medicoLicenciaMedicaForm.setIdLicMedicaTipoServicio(1);			
		}
		
		return medicoLicenciaMedicaForm;
	}
	
	@Override
	public LicenciaMedicaVo getLicenciaMedica(int idAtencion) {
		LicenciaMedicaVo licenciaMedicaVo = null;
		Licenciamedica licenciamedica = medicoLicenciaMedicaDao.getLicenciaMedica(idAtencion);
		if (licenciamedica!=null) {
			licenciaMedicaVo = new LicenciaMedicaVo();
			licenciaMedicaVo.setMotivo(licenciamedica.getCatlicmedicamotivos().getLicMedicaMotivo());
			licenciaMedicaVo.setCaracter(licenciamedica.getCatlicmedicacaracteres().getLicMedicaCaracter());
			licenciaMedicaVo.setFechaDesde(FormatUtil.getDate(licenciamedica.getFechaInicio()));
			licenciaMedicaVo.setFechaHasta(FormatUtil.getDate(licenciamedica.getFechaTermino()));
		}
		
		return licenciaMedicaVo;
	}	
	
	@Override
	public void guardarLicenciaMedica(MedicoLicenciaMedicaForm medicoLicenciaMedicaForm,UserInfo userInfo) {
		//log.info("guardar Licencia Medica service");
		Licenciamedica licenciamedica = new Licenciamedica();
		licenciamedica = medicoLicenciaMedicaDao.getLicenciaMedica(medicoLicenciaMedicaForm.getAtencionMedicaId());
		if(licenciamedica != null){
			//log.info("guardar Licencia Medica service update");
			licenciamedica.setFechaInicio(FormatUtil.getDate(medicoLicenciaMedicaForm.getFechaInicio()));
			licenciamedica.setFechaTermino(FormatUtil.getDate(medicoLicenciaMedicaForm.getFechaFin()));
			licenciamedica.setNumDias(Integer.parseInt(medicoLicenciaMedicaForm.getDias()));
			licenciamedica.setLetraDias(medicoLicenciaMedicaForm.getDiasLetra());
			licenciamedica.setCatlicmedicamotivos(medicoLicenciaMedicaDao.getLicMedicaMotivosById(medicoLicenciaMedicaForm.getIdLicMedicaMotivo()));
			licenciamedica.setCatlicmedicacaracteres(medicoLicenciaMedicaDao.getLicMedicaCaracteresById(medicoLicenciaMedicaForm.getIdLicMedicaCaracter()));
			licenciamedica.setCatlicmedicatiposservicio(medicoLicenciaMedicaDao.getLicMedicaTiposServicioById(medicoLicenciaMedicaForm.getIdLicMedicaTipoServicio()));
			medicoLicenciaMedicaDao.updateLicenciaMedica(licenciamedica);
		}else{
			Licenciamedica licenciamedica2 = new Licenciamedica();
			Atencionmedica atencionMedica = agendaDao.getAtencionMedicaById(medicoLicenciaMedicaForm.getAtencionMedicaId());
			licenciamedica2.setAtencionmedica(atencionMedica);
			licenciamedica2.setFechaEmision(new Date());
			Folio folio = foliosDao.getFolioById(FoliosEnum.LICENCIA_MEDICA.getId(), atencionMedica.getLugaresdeatencion().getLugarDeAtencionId());
			folio.setConsecutivo(folio.getConsecutivo()+1);
			licenciamedica2.setFolio(""+folio.getConsecutivo());
			foliosDao.updateFolio(folio);
			licenciamedica2.setFechaInicio(FormatUtil.getDate(medicoLicenciaMedicaForm.getFechaInicio()));
			licenciamedica2.setFechaTermino(FormatUtil.getDate(medicoLicenciaMedicaForm.getFechaFin()));
			licenciamedica2.setCatlicmedicamotivos(medicoLicenciaMedicaDao.getLicMedicaMotivosById(medicoLicenciaMedicaForm.getIdLicMedicaMotivo()));
			licenciamedica2.setCatlicmedicacaracteres(medicoLicenciaMedicaDao.getLicMedicaCaracteresById(medicoLicenciaMedicaForm.getIdLicMedicaCaracter()));
			licenciamedica2.setCatlicmedicatiposservicio(medicoLicenciaMedicaDao.getLicMedicaTiposServicioById(medicoLicenciaMedicaForm.getIdLicMedicaTipoServicio()));
			licenciamedica2.setNumDias(Integer.parseInt(medicoLicenciaMedicaForm.getDias()));
			licenciamedica2.setLetraDias(medicoLicenciaMedicaForm.getDiasLetra());
			medicoLicenciaMedicaDao.saveLicenciaMedica(licenciamedica2);
		}
		
	}

	@Override
	public void calcularFechaFinal(MedicoLicenciaMedicaForm medicoLicenciaMedicaForm) {
		Date fechaInicio = FormatUtil.getDate(medicoLicenciaMedicaForm.getFechaInicio());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaInicio);
		calendar.add(Calendar.DATE, Integer.parseInt(medicoLicenciaMedicaForm.getDias()));
		medicoLicenciaMedicaForm.setFechaFin(FormatUtil.getDate(calendar.getTime()));
		medicoLicenciaMedicaForm.setDiasLetra(CantLetras.convertNumberToLetter(medicoLicenciaMedicaForm.getDias()));
	}

}
