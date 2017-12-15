package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IAtencionMedicaDao;
import com.mx.sab.dao.IAutorizacionEspecialDao;
import com.mx.sab.enums.CatCausasEnum;
import com.mx.sab.enums.CatEstatusAtencionIdentidadEnum;
import com.mx.sab.enums.CatEstatusAtencionVigenciaEnum;
import com.mx.sab.enums.CatMotivosEnum;
import com.mx.sab.enums.CatTipoAutorizacionEnum;
import com.mx.sab.enums.CatTipoDuracionEnum;
import com.mx.sab.enums.TipoIdentificadorEnum;
import com.mx.sab.form.SupervisorAtencionesPendientesForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catcausas;
import com.mx.sab.model.Motivos;
import com.mx.sab.model.Permisoespecial;
import com.mx.sab.service.ISupervisorAtencionesPendientesService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.PermisoEspecialVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class SupervisorAtencionesPendientesServiceImpl implements ISupervisorAtencionesPendientesService {

	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private IAtencionMedicaDao atencionMedicaDao;
	
	@Autowired
	private IAutorizacionEspecialDao autorizacionEspecialDao;
	

	@Override
	public Collection<PermisoEspecialVo> getAtencionesPendientes(SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm, UserInfo userInfo) {
		//log.info("notas");
		int filas = 7;
		int paginasTotal = 0;
		int inicio = 0;
		int fin = 0;
		int pagina = 1;
		Collection<Atencionmedica> atencionmedicasVigencia = null;
		Collection<Atencionmedica> atencionmedicasIdentidad = null;
		Collection<PermisoEspecialVo> permisoEspecialVos = new ArrayList<>();
		
		int totalPermisosEspecialesVigencia = atencionMedicaDao.getAfiliadosPermisosEspecialesVigenciaCount(userInfo.getLugaresdeatencion().getLugarDeAtencionId());
		int totalPermisosEspecialesIdentidad = atencionMedicaDao.getAfiliadosPermisosEspecialesIdentidadCount(userInfo.getLugaresdeatencion().getLugarDeAtencionId());
		int totalPermisosEspeciales = totalPermisosEspecialesIdentidad + totalPermisosEspecialesVigencia;
		if (totalPermisosEspeciales>0) {
			paginasTotal = totalPermisosEspeciales / filas;
			if (totalPermisosEspeciales % filas != 0) {
				paginasTotal++;
			}
			
			if (paginasTotal>7) {
				supervisorAtencionesPendientesForm.setDisplay(7);
			}else {
				supervisorAtencionesPendientesForm.setDisplay(paginasTotal);
			}
			
			supervisorAtencionesPendientesForm.setCount(paginasTotal);
			inicio = (pagina-1)*7;
			fin = (pagina*7);
			
			atencionmedicasVigencia = atencionMedicaDao.getAfiliadosPermisosEspecialesVigencia(inicio,fin, userInfo.getLugaresdeatencion().getLugarDeAtencionId());
			atencionmedicasIdentidad = atencionMedicaDao.getAfiliadosPermisosEspecialesIdentidad(inicio,fin, userInfo.getLugaresdeatencion().getLugarDeAtencionId());
			
			for (Atencionmedica atencionmedica : atencionmedicasVigencia) {
				Afiliado afiliado = atencionmedica.getAfiliado();
				PermisoEspecialVo permisoEspecialVo = new PermisoEspecialVo();
				permisoEspecialVo.setIdAfiliado(afiliado.getAfiliadoId());
				permisoEspecialVo.setIdAtencionMedica(atencionmedica.getAtencionMedicaId());
				permisoEspecialVo.setNombre(afiliado.getNombre() +" "+ afiliado.getApellidoPaterno() +" "+ afiliado.getApellidoMaterno());
				permisoEspecialVo.setMotivo("Paciente no vigente");
				permisoEspecialVo.setTipoMotivo(1);
				permisoEspecialVos.add(permisoEspecialVo);
			}
			
			for (Atencionmedica atencionmedica : atencionmedicasIdentidad) {
				Afiliado afiliado = atencionmedica.getAfiliado();
				PermisoEspecialVo permisoEspecialVo = new PermisoEspecialVo();
				permisoEspecialVo.setIdAfiliado(afiliado.getAfiliadoId());
				permisoEspecialVo.setIdAtencionMedica(atencionmedica.getAtencionMedicaId());
				permisoEspecialVo.setNombre(afiliado.getNombre() +" "+ afiliado.getApellidoPaterno() +" "+ afiliado.getApellidoMaterno());
				permisoEspecialVo.setMotivo("Huella no verificada");
				permisoEspecialVo.setTipoMotivo(2);
				permisoEspecialVos.add(permisoEspecialVo);
			}
		}
		
		return permisoEspecialVos;
	}

	@Override
	public Collection<PermisoEspecialVo> getAtencionesPendientes(int page, UserInfo userInfo) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		Collection<PermisoEspecialVo> permisoEspecialVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;
		Collection<Atencionmedica> atencionmedicasVigencia = atencionMedicaDao.getAfiliadosPermisosEspecialesVigencia(inicio,fin, userInfo.getLugaresdeatencion().getLugarDeAtencionId());
		for (Atencionmedica atencionmedica : atencionmedicasVigencia) {
			Afiliado afiliado = atencionmedica.getAfiliado();
			PermisoEspecialVo permisoEspecialVo = new PermisoEspecialVo();
			permisoEspecialVo.setIdAfiliado(afiliado.getAfiliadoId());
			permisoEspecialVo.setIdAtencionMedica(atencionmedica.getAtencionMedicaId());
			permisoEspecialVo.setNombre(afiliado.getNombre() +" "+ afiliado.getApellidoPaterno() +" "+ afiliado.getApellidoMaterno());
			permisoEspecialVo.setMotivo("Paciente no vigente");
			permisoEspecialVo.setTipoMotivo(1);
			permisoEspecialVos.add(permisoEspecialVo);
		}
		
		Collection<Atencionmedica> atencionmedicasIdentidad = atencionMedicaDao.getAfiliadosPermisosEspecialesIdentidad(inicio,fin, userInfo.getLugaresdeatencion().getLugarDeAtencionId());
		for (Atencionmedica atencionmedica : atencionmedicasIdentidad) {
			Afiliado afiliado = atencionmedica.getAfiliado();
			PermisoEspecialVo permisoEspecialVo = new PermisoEspecialVo();
			permisoEspecialVo.setIdAfiliado(afiliado.getAfiliadoId());
			permisoEspecialVo.setIdAtencionMedica(atencionmedica.getAtencionMedicaId());
			permisoEspecialVo.setNombre(afiliado.getNombre() +" "+ afiliado.getApellidoPaterno() +" "+ afiliado.getApellidoMaterno());
			permisoEspecialVo.setMotivo("Huella no verificada");
			permisoEspecialVo.setTipoMotivo(2);
			permisoEspecialVos.add(permisoEspecialVo);
		}
		return permisoEspecialVos;
	}

	@Override
	public SupervisorAtencionesPendientesForm inicializarAtencionesPendientesForm(int idAtencionMedica, int tipoMotivo, int idAfiliado, int autorizarRechazar) {
		SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm = new SupervisorAtencionesPendientesForm();
		supervisorAtencionesPendientesForm.setIdAtencionMedica(idAtencionMedica);
		supervisorAtencionesPendientesForm.setIdAfiliado(idAfiliado);
		supervisorAtencionesPendientesForm.setAutorizarRechazar(autorizarRechazar);
		supervisorAtencionesPendientesForm.setTipoMotivo(tipoMotivo);
		return supervisorAtencionesPendientesForm;
	}

	@Override
	public void actualizarAtencionMedica(SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm, UserInfo userInfo) {
		if (supervisorAtencionesPendientesForm.getMotivo().trim().length()>0) {
			Atencionmedica atencionmedica = atencionMedicaDao.getAtencionMedicaById(supervisorAtencionesPendientesForm.getIdAtencionMedica());
			Motivos motivos = new Motivos();
			if (supervisorAtencionesPendientesForm.getAutorizarRechazar() == 0 && supervisorAtencionesPendientesForm.getTipoMotivo() == 1) {
				atencionmedica.setCatestatusatencionvigencia(atencionMedicaDao.getCatEstatusAtencionVigenciaById(CatEstatusAtencionVigenciaEnum.VIGENCIA_RECHAZADA.getId()));
				motivos.setCatmotivos(atencionMedicaDao.getCatMotivo(CatMotivosEnum.VIGENCIA.getId()));
			}else if (supervisorAtencionesPendientesForm.getAutorizarRechazar() == 1 && supervisorAtencionesPendientesForm.getTipoMotivo() == 1) {
				Permisoespecial permisoespecial = new Permisoespecial();
				permisoespecial.setCatcausas(autorizacionEspecialDao.getCatCausa(CatCausasEnum.VIGENCIA.getId()));
				permisoespecial.setAfiliado(atencionmedica.getAfiliado());
				permisoespecial.setAseguradores(atencionmedica.getAseguradores());
				permisoespecial.setDuracion(CatTipoDuracionEnum.TEMPORAL.getId());
				permisoespecial.setFechaInicio(new Date());
				permisoespecial.setFechaFin(new Date());
				permisoespecial.setCattipoautorizacion(autorizacionEspecialDao.getTipoAutorizacionById(CatTipoAutorizacionEnum.VIGENCIA.getId()));
				permisoespecial.setUsuariosByUsuarioAutorizaId(userInfo.getUsuarios());
				permisoespecial.setUsuariosByUsuarioSolicitaId(atencionmedica.getUsuariosByUsuarioRecibioId());
				permisoespecial.setFechaAutorizacion(new Date());
				permisoespecial.setLugaresdeatencion(userInfo.getLugaresdeatencion());
				autorizacionEspecialDao.save(permisoespecial);
				atencionmedica.setCatestatusatencionvigencia(atencionMedicaDao.getCatEstatusAtencionVigenciaById(CatEstatusAtencionVigenciaEnum.VIGENCIA_AUTORIZADA_ESPECIAL.getId()));
				motivos.setCatmotivos(atencionMedicaDao.getCatMotivo(CatMotivosEnum.VIGENCIA.getId()));
			}else if (supervisorAtencionesPendientesForm.getTipoMotivo() == 2) {
				atencionmedica.setCatestatusatencionidentidad(atencionMedicaDao.getCatEstatusAtencionIdentidadById(CatEstatusAtencionIdentidadEnum.IDENTIFICACION_RECHAZADA.getId()));
				motivos.setCatmotivos(atencionMedicaDao.getCatMotivo(CatMotivosEnum.IDENTIDAD.getId()));
			}
			//log.info(atencionmedica.getCatestatusatencionidentidad().getId() +" IIIIIIIIIIIIIIIIII");	
			//log.info(atencionmedica.getCatestatusatencionvigencia().getId() +" VVVVVVVVVVVVVVVVVVV");
			atencionMedicaDao.update(atencionmedica);
			motivos.setAtencionmedica(atencionmedica);
			motivos.setMotivo(supervisorAtencionesPendientesForm.getMotivo());
			atencionMedicaDao.saveMotivo(motivos);
			supervisorAtencionesPendientesForm.setFinalizo(true);
		}else {
			supervisorAtencionesPendientesForm.setError("Ingrese el motivo");
		}
		
		
	}

	@Override
	public void inicializarAtencionesPendientesFormIdentidad(SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm) {
		Atencionmedica atencionmedica = atencionMedicaDao.getAtencionMedicaById(supervisorAtencionesPendientesForm.getIdAtencionMedica());
		supervisorAtencionesPendientesForm.setNombre(atencionmedica.getAfiliado().getNombre());
		supervisorAtencionesPendientesForm.setApellidoPaterno(atencionmedica.getAfiliado().getApellidoPaterno());
		supervisorAtencionesPendientesForm.setApellidoMaterno(atencionmedica.getAfiliado().getApellidoMaterno());
		Iterator<Afiliadotipoidentificador> afiliadoTipoIdentificadorIterator = atencionmedica.getAfiliado().getAfiliadotipoidentificadors().iterator();
		while (afiliadoTipoIdentificadorIterator.hasNext()) {
			Afiliadotipoidentificador afiliadotipoidentificador = (Afiliadotipoidentificador) afiliadoTipoIdentificadorIterator.next();
			if (afiliadotipoidentificador.getCattipoidentificador().getTipoIdentificadorId() == TipoIdentificadorEnum.RFC.getId()) {
				supervisorAtencionesPendientesForm.setRfc(afiliadotipoidentificador.getTipoIdValor());
				break;
			}
		}
		supervisorAtencionesPendientesForm.setFechaInicio(FormatUtil.getDate());
		supervisorAtencionesPendientesForm.setIdTipoAutorizacion(1);
	}

	@Override
	public Collection<Catcausas> getCatCausas() {
		return autorizacionEspecialDao.getCatCausas();
	}

	@Override
	public void actualizarAtencionMedicaIdentidad(SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm,UserInfo userInfo) {
		Atencionmedica atencionmedica = atencionMedicaDao.getAtencionMedicaById(supervisorAtencionesPendientesForm.getIdAtencionMedica());
		Permisoespecial permisoespecial = new Permisoespecial();
		permisoespecial.setCatcausas(autorizacionEspecialDao.getCatCausa(supervisorAtencionesPendientesForm.getIdCausa()));
		permisoespecial.setAfiliado(atencionmedica.getAfiliado());
		permisoespecial.setAseguradores(atencionmedica.getAseguradores());
		permisoespecial.setFechaInicio(new Date());
		if (supervisorAtencionesPendientesForm.getIdTipoAutorizacion() == CatTipoDuracionEnum.TEMPORAL.getId()) {
			permisoespecial.setDuracion(CatTipoDuracionEnum.TEMPORAL.getId());
			permisoespecial.setFechaFin(FormatUtil.getDate(supervisorAtencionesPendientesForm.getFechaFin()));
		}else{
			permisoespecial.setDuracion(CatTipoDuracionEnum.PERMANENTE.getId());
			Calendar c = Calendar.getInstance(); 
			c.setTime(new Date()); 
			c.add(Calendar.YEAR, 100);
			permisoespecial.setFechaFin(c.getTime());
		}
		permisoespecial.setCattipoautorizacion(autorizacionEspecialDao.getTipoAutorizacionById(CatTipoAutorizacionEnum.AUTENTIA.getId()));
		permisoespecial.setUsuariosByUsuarioAutorizaId(userInfo.getUsuarios());
		permisoespecial.setUsuariosByUsuarioSolicitaId(atencionmedica.getUsuariosByUsuarioRecibioId());
		permisoespecial.setFechaAutorizacion(new Date());
		permisoespecial.setLugaresdeatencion(userInfo.getLugaresdeatencion());
		autorizacionEspecialDao.save(permisoespecial);
		atencionmedica.setCatestatusatencionidentidad(atencionMedicaDao.getCatEstatusAtencionIdentidadById(CatEstatusAtencionIdentidadEnum.IDENTIFICACION_AUTORIZADA_ESPECIAL.getId()));
		atencionMedicaDao.update(atencionmedica);
		
		supervisorAtencionesPendientesForm.setFinalizo(true);
	}
	
}
