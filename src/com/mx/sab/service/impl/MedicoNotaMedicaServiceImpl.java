package com.mx.sab.service.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IFoliosDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.INotaMedicaDao;
import com.mx.sab.dao.ISignosDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.enums.FoliosEnum;
import com.mx.sab.form.AntecedentesFamiliaresForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.form.NotaMedicaForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catcies10;
import com.mx.sab.model.Cattipodiagnostico;
import com.mx.sab.model.Folio;
import com.mx.sab.model.HcAntecedentesfamiliares;
import com.mx.sab.model.Historiaclinica;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.NotamedicaCies10;
import com.mx.sab.model.NotamedicaCies10Id;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.IMedicoNotaMedicaService;
import com.mx.sab.vo.DiagnosticoVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class MedicoNotaMedicaServiceImpl implements IMedicoNotaMedicaService {
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Autowired
	private ISignosDao signosDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private INotaMedicaDao notaMedicaDao;
	
	@Autowired
	private IFoliosDao foliosDao;
	
	@Override
	public NotaMedicaForm getNotaMedica(MedicoAtencionPacientesForm medicoAtencionPacientesForm, UserInfo userInfo) {
	
	NotaMedicaForm notaMedicaForm = new NotaMedicaForm();
	Atencionmedica atencionMedica = agendaDao.getAtencionMedicaById(medicoAtencionPacientesForm.getIdAtencion());
	notaMedicaForm.setAtencionMedicaId(atencionMedica.getAtencionMedicaId());
	notaMedicaForm.setIdTipoDiagnostico(1);
	Notamedica notaMedica = notaMedicaDao.getNotaMedicaByIdAtencion(atencionMedica.getAtencionMedicaId());
	if(notaMedica != null){	
		notaMedicaForm.setNotaMedicaId(notaMedica.getNotaMedicaId());
		notaMedicaForm.setMotivoConsulta(notaMedica.getAntecedentes());
		notaMedicaForm.setExploracionFisica(notaMedica.getSintomas());
		notaMedicaForm.setPlanASeguir(notaMedica.getPlanAseguir());
		NotamedicaCies10 cies10 = notaMedicaDao.getNotaMedicaCies10ByIdNotaMedica(notaMedica.getNotaMedicaId());
		notaMedicaForm.setDiagnosticoPrincipalId(cies10.getCatcies10().getCie10id());
		notaMedicaForm.setDiagnosticoPrincipalDescripcion(cies10.getCatcies10().getDescripcion());
		notaMedicaForm.setDiagnostico(cies10.getCatcies10().getDescripcion());
		notaMedicaForm.setIdTipoDiagnostico(cies10.getCattipodiagnostico().getId());
	}
	
	return notaMedicaForm;
	}
	
	@Override
	public void guardarNotaMedica(NotaMedicaForm notaMedicaForm,UserInfo userInfo) {
		//log.info("guardarNota Medica service");
		Notamedica notaMedica = notaMedicaDao.getNotaMedicaByIdAtencion(notaMedicaForm.getAtencionMedicaId());
		if(notaMedica != null){			
			notaMedica.setAntecedentes(notaMedicaForm.getMotivoConsulta());
			notaMedica.setSintomas(notaMedicaForm.getExploracionFisica());
			notaMedica.setPlanAseguir(notaMedicaForm.getPlanASeguir());
			notaMedicaDao.updateNotaMedica(notaMedica);
			
			NotamedicaCies10 cies10 = notaMedicaDao.getNotaMedicaCies10ByIdNotaMedica(notaMedica.getNotaMedicaId());
			
			if(cies10.getCatcies10().getCie10id() != notaMedicaForm.getDiagnosticoPrincipalId()){
				notaMedicaDao.deleteNotaMedicaCies10(cies10);
				Collection<NotamedicaCies10> notasMedicasCies10 = notaMedicaDao.getAllNotasMedicasCies10ByNotaMedicaId(notaMedica.getNotaMedicaId());
				
				boolean flag = false;
				if(notasMedicasCies10.size() > 0 ){
					for(NotamedicaCies10 notaCies10:notasMedicasCies10 ){
						if(notaCies10.getCatcies10().getCie10id() == notaMedicaForm.getDiagnosticoPrincipalId()){
							flag = true;
							Cattipodiagnostico tipoDiagnostico = notaMedicaDao.getCatTipoDiagnosticoById(notaMedicaForm.getIdTipoDiagnostico());
							notaCies10.setCattipodiagnostico(tipoDiagnostico);
							notaCies10.setPrincipal(1);
							notaMedicaDao.updateNotaMedicaCies10(notaCies10);
						}				
					}	
				}
				
				
				if(flag == false){				
					NotamedicaCies10 notamedicaCies10 = new NotamedicaCies10();
					Catcies10 catCies10 = notaMedicaDao.getCatCies10ById(notaMedicaForm.getDiagnosticoPrincipalId());
					Cattipodiagnostico tipoDiagnostico = notaMedicaDao.getCatTipoDiagnosticoById(notaMedicaForm.getIdTipoDiagnostico());
					notamedicaCies10.setCatcies10(catCies10);
					notamedicaCies10.setCattipodiagnostico(tipoDiagnostico);
					notamedicaCies10.setNotamedica(notaMedica);
					notamedicaCies10.setPrincipal(1);			
					NotamedicaCies10Id notamedicaCies10Id = new NotamedicaCies10Id();
					notamedicaCies10Id.setCie10id(notamedicaCies10.getCatcies10().getCie10id());
					notamedicaCies10Id.setNotaMedicaId(notaMedica.getNotaMedicaId());
					notamedicaCies10.setId(notamedicaCies10Id);
					notaMedicaDao.saveNotaMedicaCies10(notamedicaCies10);
				}	
			}else{
				if(cies10.getCattipodiagnostico().getId() != notaMedicaForm.getIdTipoDiagnostico()){
					Cattipodiagnostico tipoDiagnostico = notaMedicaDao.getCatTipoDiagnosticoById(notaMedicaForm.getIdTipoDiagnostico());
					cies10.setCattipodiagnostico(tipoDiagnostico);
					notaMedicaDao.updateNotaMedicaCies10(cies10);
				}
			}
		}else{
			Usuarios usuario = usuarioDao.getUsuarioById(userInfo.getUsuarios().getUsuarioId());
			notaMedica = new Notamedica();
			notaMedica.setUsuarios(usuario);
			Atencionmedica atencionMedica = agendaDao.getAtencionMedicaById(notaMedicaForm.getAtencionMedicaId());
			notaMedica.setAtencionmedica(atencionMedica);
			notaMedica.setAntecedentes(notaMedicaForm.getMotivoConsulta());
			notaMedica.setSintomas(notaMedicaForm.getExploracionFisica());
			notaMedica.setPlanAseguir(notaMedicaForm.getPlanASeguir());
			Folio folio = foliosDao.getFolioById(FoliosEnum.NOTA_MEDICA.getId(), atencionMedica.getLugaresdeatencion().getLugarDeAtencionId());
			folio.setConsecutivo(folio.getConsecutivo()+1);
			notaMedica.setFolio(""+folio.getConsecutivo());
			foliosDao.updateFolio(folio);
			notaMedicaDao.saveNotaMedica(notaMedica);
			notaMedicaForm.setNotaMedicaId(notaMedica.getNotaMedicaId());
			Notamedica notaMedica2 = notaMedicaDao.getNotaMedicaByIdAtencion(notaMedicaForm.getAtencionMedicaId());		
			NotamedicaCies10 notamedicaCies10 = new NotamedicaCies10();
			Catcies10 catCies10 = notaMedicaDao.getCatCies10ById(notaMedicaForm.getDiagnosticoPrincipalId());
			Cattipodiagnostico tipoDiagnostico = notaMedicaDao.getCatTipoDiagnosticoById(notaMedicaForm.getIdTipoDiagnostico());
			notamedicaCies10.setCatcies10(catCies10);
			notamedicaCies10.setCattipodiagnostico(tipoDiagnostico);
			notamedicaCies10.setNotamedica(notaMedica2);
			notamedicaCies10.setPrincipal(1);			
			NotamedicaCies10Id notamedicaCies10Id = new NotamedicaCies10Id();
			notamedicaCies10Id.setCie10id(notamedicaCies10.getCatcies10().getCie10id());
			notamedicaCies10Id.setNotaMedicaId(notaMedica2.getNotaMedicaId());
			notamedicaCies10.setId(notamedicaCies10Id);
			
			notaMedicaDao.saveNotaMedicaCies10(notamedicaCies10);			
		}
		
	}

	@Override
	public Collection<Cattipodiagnostico> getCatTipoDiagnosticos() {
		return notaMedicaDao.getCatTipoDiagnosticos();
	}
	
	@Override
	public Collection<DiagnosticoVo> getDiagnosticos(String busqueda) {
		Collection<DiagnosticoVo> diagnosticoVos = new ArrayList<>();
		DiagnosticoVo diagnosticoVo = null;
		Collection<Catcies10> catcies10sCodigo = notaMedicaDao.getDiagnosticosByCodigo(busqueda);
		Collection<Catcies10> catcies10sDescripcion = notaMedicaDao.getDiagnosticosByDescripcion(busqueda);
		
		for (Catcies10 catcies10 : catcies10sDescripcion) {
			diagnosticoVo = new DiagnosticoVo();
			diagnosticoVo.setDiagnostico(catcies10.getDescripcion());
			diagnosticoVo.setCodigo(catcies10.getCodigo());
			diagnosticoVo.setIdDiagnostico(catcies10.getCie10id());
			diagnosticoVos.add(diagnosticoVo);
		}
		
		for (Catcies10 catcies10 : catcies10sCodigo) {
			diagnosticoVo = new DiagnosticoVo();
			diagnosticoVo.setDiagnostico(catcies10.getCodigo() +" "+catcies10.getDescripcion());
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
}
