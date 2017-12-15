package com.mx.sab.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAutorizacionEspecialDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.form.SupervisorUsuariosPendientesForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catcausas;
import com.mx.sab.model.Catestatususuario;
import com.mx.sab.model.Cattipoautorizacion;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Permisoespecial;
import com.mx.sab.model.Usuariolugaratencion;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.ISupervisorUsuariosPendientesService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.PermisoEspecialUsuariosVo;
import com.mx.sab.vo.PermisoEspecialVo;
import com.mx.sab.vo.UserInfo;
import com.mx.sab.vo.UsuariosVo;

@Service
@Log4j2
public class SupervisorUsuariosPendientesServiceImpl implements ISupervisorUsuariosPendientesService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IAutorizacionEspecialDao autorizacionEspecialDao;
	
	@Override
	public Collection<UsuariosVo> getUsuariosPendientes(UserInfo userInfo) {
		//log.info("service GetUsuariosPendientes");
		Collection<Usuariolugaratencion> usuariolugaratencions = usuarioDao.getUsuariosAutorizacionPendiente(userInfo.getLugaresdeatencion().getLugarDeAtencionId());
		Collection<UsuariosVo> usuarios = new ArrayList<>();
		
		for (Usuariolugaratencion lugaresdeatencion : usuariolugaratencions) {
			UsuariosVo usuario = new UsuariosVo();
			usuario.setNombre(lugaresdeatencion.getUsuarios().getNombre());
			usuario.setApellidoPaterno(lugaresdeatencion.getUsuarios().getApellidoPaterno());
			usuario.setApellidoMaterno(lugaresdeatencion.getUsuarios().getApellidoMaterno());
			usuario.setFechaDeNacimiento(FormatUtil.getDate(lugaresdeatencion.getUsuarios().getFechaDeNacimiento()));
			usuario.setRfc(lugaresdeatencion.getUsuarios().getRfc());
			usuario.setUsuarioId(lugaresdeatencion.getUsuarios().getUsuarioId());
			usuario.setIdEstado(lugaresdeatencion.getUsuarios().getCatestados().getEstadoId());
			usuario.setSexo(lugaresdeatencion.getUsuarios().getCatsexos().getSexoId());
			usuarios.add(usuario);
		}
		
		return usuarios;
	}
	
	@Override
	public Collection<UsuariosVo> getUsuariosPendientes(int page, UserInfo userInfo) {
		
		
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		Collection<PermisoEspecialVo> permisoEspecialVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;

		Collection<Usuariolugaratencion> usuariolugaratencions = usuarioDao.getUsuariosAutorizacionPendiente(inicio, fin, userInfo.getLugaresdeatencion().getLugarDeAtencionId());
		Collection<UsuariosVo> usuarios = new ArrayList<>();
		
		for (Usuariolugaratencion lugaresdeatencion : usuariolugaratencions) {
			UsuariosVo usuario = new UsuariosVo();
			usuario.setNombre(lugaresdeatencion.getUsuarios().getNombre());
			usuario.setApellidoPaterno(lugaresdeatencion.getUsuarios().getApellidoPaterno());
			usuario.setApellidoMaterno(lugaresdeatencion.getUsuarios().getApellidoMaterno());
			usuario.setFechaDeNacimiento(FormatUtil.getDate(lugaresdeatencion.getUsuarios().getFechaDeNacimiento()));
			usuario.setRfc(lugaresdeatencion.getUsuarios().getRfc());
			usuario.setUsuarioId(lugaresdeatencion.getUsuarios().getUsuarioId());
			usuario.setIdEstado(lugaresdeatencion.getUsuarios().getCatestados().getEstadoId());
			usuario.setSexo(lugaresdeatencion.getUsuarios().getCatsexos().getSexoId());
			usuarios.add(usuario);
		}
		

		return usuarios;
	}
	
	
	
	@Override
	public void updateEstatusUsuario(SupervisorUsuariosPendientesForm supervisorForm) {
	
		Usuarios usuario  = usuarioDao.getUsuarioById(supervisorForm.getIdUsuario());
		usuario.setCatestatususuario(usuarioDao.getCatEstatusUsuarioById(2));
		usuario.setCodAutentia(supervisorForm.getRespuestaAutentia());
		usuarioDao.update(usuario);
		
	}
	
	@Override
	public Collection<Catcausas> getCatCausas() {
		return autorizacionEspecialDao.getCatCausas();
	}
	
	@Override
	public SupervisorUsuariosPendientesForm getInfoUsuario(int idUsuario) {
		Usuarios usuario = usuarioDao.getUsuarioById(idUsuario);
		
		SupervisorUsuariosPendientesForm forma = new SupervisorUsuariosPendientesForm();
		forma.setApellidoMaterno(usuario.getApellidoMaterno());
		forma.setApellidoPaterno(usuario.getApellidoPaterno());
		forma.setNombre(usuario.getNombre());
		forma.setRfc(usuario.getRfc());
		forma.setIdUsuario(usuario.getUsuarioId());
		return forma;
	}
	
	@Override
	public SupervisorUsuariosPendientesForm updateClaveUsuario(SupervisorUsuariosPendientesForm supervisorForm, UserInfo userInfo) {
		Usuarios usuario  = usuarioDao.getUsuarioById(supervisorForm.getIdUsuario());
		usuario.setCatestatususuario(usuarioDao.getCatEstatusUsuarioById(4));
		usuario.setClaveUsuario(FormatUtil.getMd5(supervisorForm.getRfc()));
		usuarioDao.update(usuario);		
		supervisorForm.setFinalizo(1);
		Usuarios usuarioAutoriza = usuarioDao.getUsuarioById(userInfo.getUsuarios().getUsuarioId());
		Usuarios usuarioId = usuarioDao.getUsuarioById(supervisorForm.getIdUsuario());
		Cattipoautorizacion tipo = autorizacionEspecialDao.getTipoAutorizacionById(2);
		Permisoespecial permiso = new Permisoespecial();
		permiso.setCatcausas(autorizacionEspecialDao.getCatCausa(supervisorForm.getIdCausa()));
		permiso.setCattipoautorizacion(tipo);		
		permiso.setUsuariosByUsuarioAutorizaId(usuarioAutoriza);
		permiso.setUsuariosByUsuarioId(usuarioId);
		permiso.setFechaAutorizacion(FormatUtil.getDate(supervisorForm.getFechaInicio()));
		permiso.setFechaInicio(FormatUtil.getDate(supervisorForm.getFechaInicio()));
		permiso.setFechaFin(FormatUtil.getDate(supervisorForm.getFechaFin()));
		permiso.setDuracion(supervisorForm.getIdTipoAutorizacion());
		permiso.setLugaresdeatencion(userInfo.getLugaresdeatencion());
		autorizacionEspecialDao.save(permiso);
		return supervisorForm;
	}
		
}
