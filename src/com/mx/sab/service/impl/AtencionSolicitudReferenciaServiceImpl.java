package com.mx.sab.service.impl;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.ILicenciaMedicaDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.IAtencionSolicitudReferenciaDao;
import com.mx.sab.dao.IFoliosDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.enums.FoliosEnum;
import com.mx.sab.form.SolicitudReferenciaForm;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catsolireferenciamotivos;
import com.mx.sab.model.Catsolirefrerenciapor;
import com.mx.sab.model.Folio;
import com.mx.sab.model.Solicitudreferencia;
import com.mx.sab.service.IAtencionSolicitudReferenciaService;
import com.mx.sab.util.FormatUtil;

@Service
@Log4j2
public class AtencionSolicitudReferenciaServiceImpl implements IAtencionSolicitudReferenciaService {
	
	@Autowired
	private IAtencionSolicitudReferenciaDao solicitudReferenciaDao;
	
	@Autowired
	private ILicenciaMedicaDao licenciaMedicaDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private IFoliosDao foliosDao;	
	
	@Override
	public Collection<Catsolireferenciamotivos> getCatSoliReferenciaMotivos() {
		return solicitudReferenciaDao.getCatSoliReferenciaMotivos();
	}

	@Override
	public Collection<Catsolirefrerenciapor> getCatSoliRefrerenciaPor() {
		return solicitudReferenciaDao.getCatSoliRefrerenciaPor();
	}

	@Override
	public void inicializaSolicitudReferenciaForm(SolicitudReferenciaForm solicitudReferenciaForm) {
		Solicitudreferencia solicitudreferencia = solicitudReferenciaDao.getSolicitudReferenciaByIdAtencionMedica(solicitudReferenciaForm.getIdAtencion());
		if (solicitudreferencia!=null) {
			solicitudReferenciaForm.setMotivo(solicitudreferencia.getMotivoReferencia());
			solicitudReferenciaForm.setIdLugarAtencion(solicitudreferencia.getLugaresdeatencion().getLugarDeAtencionId());
			solicitudReferenciaForm.setIdEspecialidad(solicitudreferencia.getCatespecialidadesmedicas().getEspecialidadMedicaId());
			solicitudReferenciaForm.setIdCatSoliReferenciaMotivo(solicitudreferencia.getCatsolireferenciamotivos().getSoliReferenciaMotivoId());
			solicitudReferenciaForm.setPresentacionCaso(solicitudreferencia.getMotivoDelEnvio());
			solicitudReferenciaForm.setResultadosLaboratorio(solicitudreferencia.getResultadosLaboratorio());
		}else{
			solicitudReferenciaForm.setIdCatSoliReferenciaMotivo(1);
		}

	}

	@Override
	public void save(SolicitudReferenciaForm solicitudReferenciaForm) {
		Solicitudreferencia solicitudreferencia = solicitudReferenciaDao.getSolicitudReferenciaByIdAtencionMedica(solicitudReferenciaForm.getIdAtencion());
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(solicitudReferenciaForm.getIdAtencion());
		if(!solicitudReferenciaForm.getMotivo().trim().equals("")){
			if(!solicitudReferenciaForm.getPresentacionCaso().trim().equals("")){
				if(solicitudReferenciaForm.getIdEspecialidad()!=-1){
					if(solicitudReferenciaForm.getIdLugarAtencion()!=-1){
						if (solicitudreferencia!=null) {
							solicitudreferencia.setMotivoReferencia(solicitudReferenciaForm.getMotivo());
							solicitudreferencia.setLugaresdeatencion(lugarAtencionDao.getLugarAtencionById(solicitudReferenciaForm.getIdLugarAtencion()));
							solicitudreferencia.setCatespecialidadesmedicas(usuarioDao.getEspecialidadesId(solicitudReferenciaForm.getIdEspecialidad()));
							solicitudreferencia.setCatsolireferenciamotivos(solicitudReferenciaDao.getCatSoliReferenciaMotivosById(solicitudReferenciaForm.getIdCatSoliReferenciaMotivo()));
							solicitudreferencia.setMotivoDelEnvio(solicitudReferenciaForm.getPresentacionCaso());
							solicitudreferencia.setResultadosLaboratorio(solicitudReferenciaForm.getResultadosLaboratorio());
							solicitudreferencia.setAtencionmedica(atencionmedica);
							solicitudReferenciaDao.update(solicitudreferencia);	
						}else{
							solicitudreferencia = new Solicitudreferencia();
							solicitudreferencia.setMotivoReferencia(solicitudReferenciaForm.getMotivo());
							solicitudreferencia.setLugaresdeatencion(lugarAtencionDao.getLugarAtencionById(solicitudReferenciaForm.getIdLugarAtencion()));
							solicitudreferencia.setCatespecialidadesmedicas(usuarioDao.getEspecialidadesId(solicitudReferenciaForm.getIdEspecialidad()));
							solicitudreferencia.setCatsolireferenciamotivos(solicitudReferenciaDao.getCatSoliReferenciaMotivosById(solicitudReferenciaForm.getIdCatSoliReferenciaMotivo()));
							solicitudreferencia.setMotivoDelEnvio(solicitudReferenciaForm.getPresentacionCaso());
							solicitudreferencia.setResultadosLaboratorio(solicitudReferenciaForm.getResultadosLaboratorio());
							solicitudreferencia.setAtencionmedica(atencionmedica);
							solicitudreferencia.setFecha(new Date());
							solicitudreferencia.setHora(new Time(new Date().getTime()));
							solicitudreferencia.setPendiente(0);
							Folio folio = foliosDao.getFolioById(FoliosEnum.SOLICITUD_DE_REFERENCIA.getId(), atencionmedica.getLugaresdeatencion().getLugarDeAtencionId());
							folio.setConsecutivo(folio.getConsecutivo()+1);
							solicitudreferencia.setFolio(""+folio.getConsecutivo());
							foliosDao.updateFolio(folio);
							solicitudReferenciaDao.save(solicitudreferencia);	
						}
						solicitudReferenciaForm.setError("Se han actualizado los datos");
					}else{
						solicitudReferenciaForm.setError("Seleccione una unidad");
					}
				}else{
					solicitudReferenciaForm.setError("Seleccione una especialidad");
				}
			}else{
				solicitudReferenciaForm.setError("Debe de agregar la presentacion del caso");
			}	
		}else{
			solicitudReferenciaForm.setError("Debe de agregar el motivo de la referencia");
		}

	}

}
