package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.enums.CatModificacionUsuarioEnum;
import com.mx.sab.enums.RolesEnum;
import com.mx.sab.form.MantenimientoModificarUsuarioForm;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Roles;
import com.mx.sab.model.Usuarioauditoria;
import com.mx.sab.model.Usuarioespecialidades;
import com.mx.sab.model.Usuariorol;
import com.mx.sab.model.Usuarios;
import com.mx.sab.model.Usuariovigencialugaratencion;
import com.mx.sab.service.IMantenimientoModificarUsuarioService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.UserInfo;
import com.mx.sab.vo.UsuariosVo;

@Service
@Log4j2
public class MantenimientoModificarUsuarioServiceImpl implements IMantenimientoModificarUsuarioService {
	
	@Autowired 
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Override
	public Collection<UsuariosVo> getUsuarios(MantenimientoModificarUsuarioForm mantenimientoModificarUsuarioForm, UserInfo userInfo) {
		int filas = 7;
		int paginasTotal = 0;
		int inicio = 0;
		int fin = 0;
		int pagina = 1;
		Collection<Usuariovigencialugaratencion> usuariolugaratencions = null;
		Collection<UsuariosVo> usuariosVos = new ArrayList<>();
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+userInfo.getTx_Marca());
		
		if (mantenimientoModificarUsuarioForm==null) {
			mantenimientoModificarUsuarioForm = new MantenimientoModificarUsuarioForm();
		}
		
		if (mantenimientoModificarUsuarioForm.getBusquedaRfc()==null) {
			mantenimientoModificarUsuarioForm.setBusquedaRfc("");	
		}
		
		if (mantenimientoModificarUsuarioForm.getBusquedaNombre()==null) {
			mantenimientoModificarUsuarioForm.setBusquedaNombre("");	
		}
		
		if (mantenimientoModificarUsuarioForm.getBusquedaApellidoPaterno()==null) {
			mantenimientoModificarUsuarioForm.setBusquedaApellidoPaterno("");	
		}
		
		if (mantenimientoModificarUsuarioForm.getBusquedaApellidoMaterno()==null) {
			mantenimientoModificarUsuarioForm.setBusquedaApellidoMaterno("");	
		}
		
		if (mantenimientoModificarUsuarioForm.getBusquedaRfc().equals("") && mantenimientoModificarUsuarioForm.getBusquedaNombre().equals("") && mantenimientoModificarUsuarioForm.getBusquedaApellidoMaterno().equals("") && mantenimientoModificarUsuarioForm.getBusquedaApellidoPaterno().equals("")) {
			mantenimientoModificarUsuarioForm.setSinResultados(true);
		}else{
			int totalPrestadores = usuarioDao.getUsuariosCount(mantenimientoModificarUsuarioForm.getBusquedaRfc(), mantenimientoModificarUsuarioForm.getBusquedaNombre(), mantenimientoModificarUsuarioForm.getBusquedaApellidoPaterno(), mantenimientoModificarUsuarioForm.getBusquedaApellidoMaterno(), lugaresdeatencion.getLugarDeAtencionId());
			if (totalPrestadores>0) {
				paginasTotal = totalPrestadores / filas;
				if (totalPrestadores % filas != 0) {
					paginasTotal++;
				}
				
				if (paginasTotal>7) {
					mantenimientoModificarUsuarioForm.setDisplay(7);
				}else {
					mantenimientoModificarUsuarioForm.setDisplay(paginasTotal);
				}
				
				mantenimientoModificarUsuarioForm.setCount(paginasTotal);
				inicio = (pagina-1)*7;
				fin = (pagina*7);
				
				usuariolugaratencions = usuarioDao.getUsuarioLugarAtencion(mantenimientoModificarUsuarioForm.getBusquedaRfc(), mantenimientoModificarUsuarioForm.getBusquedaNombre(), mantenimientoModificarUsuarioForm.getBusquedaApellidoPaterno(), mantenimientoModificarUsuarioForm.getBusquedaApellidoMaterno(),inicio,fin, lugaresdeatencion.getLugarDeAtencionId());
				for (Usuariovigencialugaratencion usuariolugaratencion : usuariolugaratencions) {
					UsuariosVo usuariosVo = new UsuariosVo();
					usuariosVo.setUsuarioId(usuariolugaratencion.getUsuarios().getUsuarioId());
					usuariosVo.setRfc(usuariolugaratencion.getUsuarios().getRfc());
					usuariosVo.setNombre(usuariolugaratencion.getUsuarios().getNombre() + " " + usuariolugaratencion.getUsuarios().getApellidoPaterno() + " " + usuariolugaratencion.getUsuarios().getApellidoMaterno());
					usuariosVo.setApellidoPaterno(usuariolugaratencion.getUsuarios().getApellidoPaterno());
					usuariosVo.setApellidoMaterno(usuariolugaratencion.getUsuarios().getApellidoMaterno());
					usuariosVo.setFechaDeNacimiento(FormatUtil.getDate(usuariolugaratencion.getUsuarios().getFechaDeNacimiento()));
					usuariosVo.setVigencia(usuariolugaratencion.getCatvigencia().getDescripcion());
					usuariosVos.add(usuariosVo);
					if (totalPrestadores == 1) {
						mantenimientoModificarUsuarioForm.setIdUsuario(usuariosVo.getUsuarioId());
						mantenimientoModificarUsuarioForm.setIdVigencia(usuariolugaratencion.getCatvigencia().getVigenciaId());
					}
				}
			}else {
				//log.info("No hay usuarios");
				mantenimientoModificarUsuarioForm.setSinResultados(true);
			}			
		}

		return usuariosVos;
	}

	@Override
	public Collection<UsuariosVo> getUsuarios(String busquedaRfc, String busquedaNombre, String busquedaApellidoPaterno, String busquedaApellidoMaterno, int page, UserInfo userInfo) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		Collection<UsuariosVo> usuariosVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+userInfo.getTx_Marca());
		Collection<Usuariovigencialugaratencion> usuariolugaratencions = usuarioDao.getUsuarioLugarAtencion(busquedaRfc, busquedaNombre, busquedaApellidoPaterno, busquedaApellidoMaterno,inicio,fin, lugaresdeatencion.getLugarDeAtencionId());
		for (Usuariovigencialugaratencion usuariolugaratencion : usuariolugaratencions) {
			UsuariosVo usuariosVo = new UsuariosVo();
			usuariosVo.setUsuarioId(usuariolugaratencion.getUsuarios().getUsuarioId());
			usuariosVo.setRfc(usuariolugaratencion.getUsuarios().getRfc());
			usuariosVo.setNombre(usuariolugaratencion.getUsuarios().getNombre() + " " + usuariolugaratencion.getUsuarios().getApellidoPaterno() + " " + usuariolugaratencion.getUsuarios().getApellidoMaterno());
			usuariosVo.setApellidoPaterno(usuariolugaratencion.getUsuarios().getApellidoPaterno());
			usuariosVo.setApellidoMaterno(usuariolugaratencion.getUsuarios().getApellidoMaterno());
			usuariosVo.setFechaDeNacimiento(FormatUtil.getDate(usuariolugaratencion.getUsuarios().getFechaDeNacimiento()));
			usuariosVo.setVigencia(usuariolugaratencion.getCatvigencia().getDescripcion());
			usuariosVos.add(usuariosVo);
		}
		
		return usuariosVos;
	}

	@Override
	public void getUsuario(MantenimientoModificarUsuarioForm mantenimientoModificarUsuarioForm, Collection<Roles> roles, UserInfo userInfo) {
		
		mantenimientoModificarUsuarioForm.setIdUsuarioAdministrador(userInfo.getUsuarios().getUsuarioId());
		mantenimientoModificarUsuarioForm.setRfcAdministrador(userInfo.getUsuarios().getRfc());
		mantenimientoModificarUsuarioForm.setNombreAdministrador(userInfo.getUsuarios().getNombre());
		mantenimientoModificarUsuarioForm.setApellidoMaternoAdministrador(userInfo.getUsuarios().getApellidoMaterno());
		mantenimientoModificarUsuarioForm.setApellidoPaternoAdministrador(userInfo.getUsuarios().getApellidoPaterno());
		mantenimientoModificarUsuarioForm.setSexoAdministrador(userInfo.getUsuarios().getCatsexos().getSexoId());
		mantenimientoModificarUsuarioForm.setFechaNacimientoAdministrador(FormatUtil.getDate(userInfo.getUsuarios().getFechaDeNacimiento()));
		
		Usuarios usuarios = usuarioDao.getUsuarioById(mantenimientoModificarUsuarioForm.getIdUsuario());
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+userInfo.getTx_Marca());
		mantenimientoModificarUsuarioForm.setIdUsuario(usuarios.getUsuarioId());
		mantenimientoModificarUsuarioForm.setApellidoMaterno(usuarios.getApellidoMaterno());
		mantenimientoModificarUsuarioForm.setApellidoPaterno(usuarios.getApellidoPaterno());
		mantenimientoModificarUsuarioForm.setCurp(usuarios.getCurp());
		mantenimientoModificarUsuarioForm.setFechaNacimiento(FormatUtil.getDate(usuarios.getFechaDeNacimiento()));
		mantenimientoModificarUsuarioForm.setNombre(usuarios.getNombre());
		mantenimientoModificarUsuarioForm.setRfc(usuarios.getRfc());
		mantenimientoModificarUsuarioForm.setSexo(usuarios.getCatsexos().getSexoId());
		mantenimientoModificarUsuarioForm.setTelefono(usuarios.getTelefono());
		mantenimientoModificarUsuarioForm.setMail(usuarios.getMail());
		mantenimientoModificarUsuarioForm.setRegSs(usuarios.getRegSs());
		
		Collection<Usuariorol> usuariorols = usuarioDao.getUsuariosRolByLugar(usuarios.getUsuarioId(),lugaresdeatencion.getLugarDeAtencionId());
		//String[] rols = new String[roles.size()];
		String[] rols = new String[usuariorols.size()];
		int i = 0;
		for (Usuariorol usuariorol : usuariorols) {
			rols[i] = "" + usuariorol.getRoles().getRolId();
			i++;
		}
		mantenimientoModificarUsuarioForm.setRoles(rols);
		
		Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = usuarios.getUsuarioespecialidadeses().iterator();
		i = 0;
		
		mantenimientoModificarUsuarioForm.setIdEspecialidad1(1);
		mantenimientoModificarUsuarioForm.setIdInstitucion1("");
		mantenimientoModificarUsuarioForm.setCedulaProfesional1("");
		mantenimientoModificarUsuarioForm.setIdEspecialidad2(-1);
		mantenimientoModificarUsuarioForm.setIdInstitucion2("");
		mantenimientoModificarUsuarioForm.setCedulaProfesional2("");				
		mantenimientoModificarUsuarioForm.setIdEspecialidad3(-1);
		mantenimientoModificarUsuarioForm.setIdInstitucion3("");
		mantenimientoModificarUsuarioForm.setCedulaProfesional3("");	
		
		while (usuarioEspecialidadesIterator.hasNext()) {
			Usuarioespecialidades usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
			if (i==0) {
				mantenimientoModificarUsuarioForm.setId1(usuarioespecialidades.getId());
				mantenimientoModificarUsuarioForm.setIdEspecialidad1(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedicaId());
				mantenimientoModificarUsuarioForm.setIdInstitucion1(usuarioespecialidades.getInstitucionEducativaId());
				mantenimientoModificarUsuarioForm.setCedulaProfesional1(usuarioespecialidades.getCedulaEspecialidad());
			}else if (i==1) {
				mantenimientoModificarUsuarioForm.setId2(usuarioespecialidades.getId());
				mantenimientoModificarUsuarioForm.setIdEspecialidad2(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedicaId());
				mantenimientoModificarUsuarioForm.setIdInstitucion2(usuarioespecialidades.getInstitucionEducativaId());
				mantenimientoModificarUsuarioForm.setCedulaProfesional2(usuarioespecialidades.getCedulaEspecialidad());				
			}else if (i==2) {
				mantenimientoModificarUsuarioForm.setId3(usuarioespecialidades.getId());
				mantenimientoModificarUsuarioForm.setIdEspecialidad3(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedicaId());
				mantenimientoModificarUsuarioForm.setIdInstitucion3(usuarioespecialidades.getInstitucionEducativaId());
				mantenimientoModificarUsuarioForm.setCedulaProfesional3(usuarioespecialidades.getCedulaEspecialidad());				
			}
			i++;
		}
		
		Iterator<Usuariovigencialugaratencion> usuarioVigenciaLugarIterator = usuarios.getUsuariovigencialugaratencions().iterator();
		while (usuarioVigenciaLugarIterator.hasNext()) {
			Usuariovigencialugaratencion usuariovigencialugaratencion = (Usuariovigencialugaratencion) usuarioVigenciaLugarIterator.next();
			if (usuariovigencialugaratencion.getLugaresdeatencion().getLugarDeAtencionId() == lugaresdeatencion.getLugarDeAtencionId()) {
				mantenimientoModificarUsuarioForm.setClaveInterna(usuariovigencialugaratencion.getClaveInterna());
				mantenimientoModificarUsuarioForm.setVigencia(usuariovigencialugaratencion.getCatvigencia().getVigenciaId());
				mantenimientoModificarUsuarioForm.setVigenciaAux(usuariovigencialugaratencion.getCatvigencia().getVigenciaId());
			}
		}
	}

	@Override
	public void updateUsuario(MantenimientoModificarUsuarioForm mantenimientoModificarUsuarioForm, MantenimientoModificarUsuarioForm mantenimientoModificarUsuarioFormAux, UserInfo userInfo) {
		boolean cambioUsuario = false;
		boolean cambioUsuarioVigenciaLugarAtencion = false;
		
		Usuarios usuarios = usuarioDao.getUsuarioById(mantenimientoModificarUsuarioForm.getIdUsuario());
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+userInfo.getTx_Marca());
		Usuariovigencialugaratencion usuariovigencialugaratencion = usuarioDao.getUsuarioVigenciaLugarAtencionByIdUsuario(usuarios.getUsuarioId(),lugaresdeatencion.getLugarDeAtencionId());
		
		if (mantenimientoModificarUsuarioForm.isModificarVigencia()==false) {
			if(!mantenimientoModificarUsuarioFormAux.getApellidoMaterno().equals(mantenimientoModificarUsuarioForm.getApellidoMaterno())){
				cambioUsuario = true;
				usuarios.setApellidoMaterno(mantenimientoModificarUsuarioForm.getApellidoMaterno());
				Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
				usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
				usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
				usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.CAMBIO_DE_APELLIDO_MATERNO.getId()));
				usuarioauditoria.setFechaModificacion(new Date());
				usuarioauditoria.setValorAnterior(mantenimientoModificarUsuarioFormAux.getApellidoMaterno());
				usuarioauditoria.setValorNuevo(mantenimientoModificarUsuarioForm.getApellidoMaterno());
				usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
			}
			
			if(!mantenimientoModificarUsuarioFormAux.getApellidoPaterno().equals(mantenimientoModificarUsuarioForm.getApellidoPaterno())){
				cambioUsuario = true;
				usuarios.setApellidoPaterno(mantenimientoModificarUsuarioForm.getApellidoPaterno());
				Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
				usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
				usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
				usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.CAMBIO_DE_APELLIDO_PATERNO.getId()));
				usuarioauditoria.setFechaModificacion(new Date());
				usuarioauditoria.setValorAnterior(mantenimientoModificarUsuarioFormAux.getApellidoPaterno());
				usuarioauditoria.setValorNuevo(mantenimientoModificarUsuarioForm.getApellidoPaterno());
				usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
			}
			
			if(!mantenimientoModificarUsuarioFormAux.getFechaNacimiento().equals(mantenimientoModificarUsuarioForm.getFechaNacimiento())){
				cambioUsuario = true;
				usuarios.setFechaDeNacimiento(FormatUtil.getDate(mantenimientoModificarUsuarioForm.getFechaNacimiento()));
				Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
				usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
				usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
				usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.CAMBIO_DE_FECHA_DE_NACIMIENTO.getId()));
				usuarioauditoria.setFechaModificacion(new Date());
				usuarioauditoria.setValorAnterior(mantenimientoModificarUsuarioFormAux.getFechaNacimiento());
				usuarioauditoria.setValorNuevo(mantenimientoModificarUsuarioForm.getFechaNacimiento());
				usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
			}
			
			if(!mantenimientoModificarUsuarioFormAux.getNombre().equals(mantenimientoModificarUsuarioForm.getNombre())){
				cambioUsuario = true;
				usuarios.setNombre(mantenimientoModificarUsuarioForm.getNombre());
				Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
				usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
				usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
				usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.CAMBIO_DE_NOMBRE.getId()));
				usuarioauditoria.setFechaModificacion(new Date());
				usuarioauditoria.setValorAnterior(mantenimientoModificarUsuarioFormAux.getNombre());
				usuarioauditoria.setValorNuevo(mantenimientoModificarUsuarioForm.getNombre());
				usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
			}
			
			if(!mantenimientoModificarUsuarioFormAux.getTelefono().equals(mantenimientoModificarUsuarioForm.getTelefono())){
				cambioUsuario = true;
				usuarios.setTelefono(mantenimientoModificarUsuarioForm.getTelefono());
				Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
				usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
				usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
				usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.CAMBIO_DE_NUMERO_TELEFONICO.getId()));
				usuarioauditoria.setFechaModificacion(new Date());
				usuarioauditoria.setValorAnterior(mantenimientoModificarUsuarioFormAux.getTelefono());
				usuarioauditoria.setValorNuevo(mantenimientoModificarUsuarioForm.getTelefono());
				usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
			}
			
			if(mantenimientoModificarUsuarioFormAux.getSexo() != mantenimientoModificarUsuarioForm.getSexo()){
				cambioUsuario = true;
				usuarios.setCatsexos(usuarioDao.getSexoId(mantenimientoModificarUsuarioForm.getSexo()));
				Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
				usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
				usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
				usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.CAMBIO_DE_SEXO.getId()));
				usuarioauditoria.setFechaModificacion(new Date());
				usuarioauditoria.setValorAnterior(""+mantenimientoModificarUsuarioFormAux.getSexo());
				usuarioauditoria.setValorNuevo(""+mantenimientoModificarUsuarioForm.getSexo());
				usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
			}
			
			if(mantenimientoModificarUsuarioFormAux.getVigencia() != mantenimientoModificarUsuarioForm.getVigencia()){
				cambioUsuarioVigenciaLugarAtencion = true;
				usuariovigencialugaratencion.setCatvigencia(usuarioDao.getCatVigenciaById(mantenimientoModificarUsuarioForm.getVigencia()));
				Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
				usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
				usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
				usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.CAMBIO_DE_VIGENCIA.getId()));
				usuarioauditoria.setFechaModificacion(new Date());
				usuarioauditoria.setValorAnterior(""+mantenimientoModificarUsuarioFormAux.getVigencia());
				usuarioauditoria.setValorNuevo(""+mantenimientoModificarUsuarioForm.getVigencia());
				usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
			}
			
			if(!mantenimientoModificarUsuarioFormAux.getCurp().equals(mantenimientoModificarUsuarioForm.getCurp())){
				cambioUsuario = true;
				usuarios.setCurp(mantenimientoModificarUsuarioForm.getCurp());
				Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
				usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
				usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
				usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.CAMBIO_DE_CURP.getId()));
				usuarioauditoria.setFechaModificacion(new Date());
				usuarioauditoria.setValorAnterior(""+mantenimientoModificarUsuarioFormAux.getCurp());
				usuarioauditoria.setValorNuevo(""+mantenimientoModificarUsuarioForm.getCurp());
				usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
			}		
			
			String roles = "";
			String rolesAux = "";
			
			int rolesPrevios = 0;
			int rolesNuevos = 0;
			boolean validarEspecialidades = false;
			for (String rol : mantenimientoModificarUsuarioForm.getRoles()) {
				int rolI = Integer.parseInt(rol);
				if (rolI == RolesEnum.MEDICO.getId() || rolI == RolesEnum.DENTISTA.getId()) {
					validarEspecialidades = true;
				}
				rolesNuevos++;
				roles = roles + rol + ",";
			}
			
			for (String rol : mantenimientoModificarUsuarioFormAux.getRoles()) {
				if (rol!=null) {
					rolesPrevios++;
					rolesAux = rolesAux + rol + ",";	
				}
			}
			
			if (!roles.equals(rolesAux)) {
				usuarioDao.deleteRolesByIdLugarAtencion(usuarios.getUsuarioId(), lugaresdeatencion.getLugarDeAtencionId());
				for (String rol : mantenimientoModificarUsuarioForm.getRoles()) {
					Usuariorol usuariorol = new Usuariorol();
					usuariorol.setLugaresdeatencion(lugaresdeatencion);
					usuariorol.setRoles(usuarioDao.getRolesId(Integer.parseInt(rol)));
					usuariorol.setUsuarios(usuarios);	
					usuarioDao.saveUsuarioRol(usuariorol);
				}
				
				Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
				usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
				usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
				if (rolesPrevios>rolesNuevos) {
					usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.ELIMINAR_ROLES_LUGAR_DE_ATENCION.getId()));
				}else{
					usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.AGREGAR_ROLES_LUGAR_DE_ATENCION.getId()));	
				}			
				usuarioauditoria.setFechaModificacion(new Date());
				usuarioauditoria.setValorAnterior(rolesAux);
				usuarioauditoria.setValorNuevo(roles);
				usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
			}
			
			if (cambioUsuario) {
				usuarioDao.update(usuarios);
			}
			
			if (validarEspecialidades) {
				
				if(!mantenimientoModificarUsuarioFormAux.getClaveInterna().equals(mantenimientoModificarUsuarioForm.getClaveInterna())){
					cambioUsuarioVigenciaLugarAtencion = true;
					usuariovigencialugaratencion.setClaveInterna(mantenimientoModificarUsuarioForm.getClaveInterna());
					Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
					usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
					usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
					usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.CAMBIO_CLAVE_INTERNA.getId()));
					usuarioauditoria.setFechaModificacion(new Date());
					usuarioauditoria.setValorAnterior(""+mantenimientoModificarUsuarioFormAux.getClaveInterna());
					usuarioauditoria.setValorNuevo(""+mantenimientoModificarUsuarioForm.getClaveInterna());
					usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
				}
				
				if(!mantenimientoModificarUsuarioFormAux.getRegSs().equals(mantenimientoModificarUsuarioForm.getRegSs())){
					usuarios.setRegSs(mantenimientoModificarUsuarioForm.getClaveInterna());
					Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
					usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
					usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
					usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.CAMBIO_REGSS.getId()));
					usuarioauditoria.setFechaModificacion(new Date());
					usuarioauditoria.setValorAnterior(""+mantenimientoModificarUsuarioFormAux.getRegSs());
					usuarioauditoria.setValorNuevo(""+mantenimientoModificarUsuarioForm.getRegSs());
					usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
					usuarioDao.update(usuarios);
				}				
				
				boolean modificoCedulaProfesional = false;
				boolean modificoIdEspecialidad = false;
				boolean modificoIdInstitucion = false;
				
				if(!mantenimientoModificarUsuarioFormAux.getCedulaProfesional1().equals(mantenimientoModificarUsuarioForm.getCedulaProfesional1())){
					modificoCedulaProfesional = true;
				}
				if (mantenimientoModificarUsuarioFormAux.getIdEspecialidad1()!=mantenimientoModificarUsuarioForm.getIdEspecialidad1()) {
					modificoIdEspecialidad = true;
				}
				if (mantenimientoModificarUsuarioFormAux.getIdInstitucion1()!=mantenimientoModificarUsuarioForm.getIdInstitucion1()) {
					modificoIdInstitucion = true;
				}
				
				if (modificoCedulaProfesional || modificoIdEspecialidad || modificoIdInstitucion) {
					if (mantenimientoModificarUsuarioFormAux.getId1()!=0) {
						Usuarioespecialidades usuarioespecialidades = usuarioDao.getUsuarioEspecialidadesById(mantenimientoModificarUsuarioFormAux.getId1());
						if (mantenimientoModificarUsuarioForm.getIdEspecialidad1()==-1) {
							usuarioDao.removeUsuarioEspecialidades(usuarioespecialidades);
							Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
							usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
							usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
							usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.ELIMINAR_ESPECIALIDAD_CEDULA_INSTITUCION.getId()));
							usuarioauditoria.setFechaModificacion(new Date());
							usuarioauditoria.setValorAnterior(mantenimientoModificarUsuarioFormAux.getIdEspecialidad1() + "," + mantenimientoModificarUsuarioFormAux.getCedulaProfesional1() + "," + mantenimientoModificarUsuarioFormAux.getIdInstitucion1());
							usuarioauditoria.setValorNuevo(mantenimientoModificarUsuarioForm.getIdEspecialidad1() + "," + mantenimientoModificarUsuarioForm.getCedulaProfesional1() + "," + mantenimientoModificarUsuarioForm.getIdInstitucion1());
							usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
						}else{
							usuarioespecialidades.setCatespecialidadesmedicas(usuarioDao.getEspecialidadesId(mantenimientoModificarUsuarioForm.getIdEspecialidad1()));
							usuarioespecialidades.setInstitucionEducativaId(mantenimientoModificarUsuarioForm.getIdInstitucion1());
							usuarioespecialidades.setCedulaEspecialidad(mantenimientoModificarUsuarioForm.getCedulaProfesional1());
							usuarioDao.updateUsuarioEspecialidades(usuarioespecialidades);
							Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
							usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
							usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
							usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.CAMBIO_ESPECIALIDAD_CEDULA_INSTITUCION.getId()));
							usuarioauditoria.setFechaModificacion(new Date());
							usuarioauditoria.setValorAnterior(mantenimientoModificarUsuarioFormAux.getIdEspecialidad1() + "," + mantenimientoModificarUsuarioFormAux.getCedulaProfesional1() + "," + mantenimientoModificarUsuarioFormAux.getIdInstitucion1());
							usuarioauditoria.setValorNuevo(mantenimientoModificarUsuarioForm.getIdEspecialidad1() + "," + mantenimientoModificarUsuarioForm.getCedulaProfesional1() + "," + mantenimientoModificarUsuarioForm.getIdInstitucion1());
							usuarioDao.saveUsuarioAuditoria(usuarioauditoria);						
						}
					}else{
						Usuarioespecialidades usuarioespecialidades = new Usuarioespecialidades();
						usuarioespecialidades.setCatespecialidadesmedicas(usuarioDao.getEspecialidadesId(mantenimientoModificarUsuarioForm.getIdEspecialidad1()));
						usuarioespecialidades.setInstitucionEducativaId(mantenimientoModificarUsuarioForm.getIdInstitucion1());
						usuarioespecialidades.setCedulaEspecialidad(mantenimientoModificarUsuarioForm.getCedulaProfesional1());
						usuarioespecialidades.setUsuarios(usuarios);
						usuarioDao.saveUsuarioEspecialidad(usuarioespecialidades);
						Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
						usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
						usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
						usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.AGREGAR_ESPECIALIDAD_CEDULA_INSTITUCION.getId()));
						usuarioauditoria.setFechaModificacion(new Date());
						usuarioauditoria.setValorAnterior(mantenimientoModificarUsuarioFormAux.getIdEspecialidad1() + "," + mantenimientoModificarUsuarioFormAux.getCedulaProfesional1() + "," + mantenimientoModificarUsuarioFormAux.getIdInstitucion1());
						usuarioauditoria.setValorNuevo(mantenimientoModificarUsuarioForm.getIdEspecialidad1() + "," + mantenimientoModificarUsuarioForm.getCedulaProfesional1() + "," + mantenimientoModificarUsuarioForm.getIdInstitucion1());
						usuarioDao.saveUsuarioAuditoria(usuarioauditoria);					
					}
				}
				
				modificoCedulaProfesional = false;
				modificoIdEspecialidad = false;
				modificoIdInstitucion = false;
				
				if(!mantenimientoModificarUsuarioFormAux.getCedulaProfesional2().equals(mantenimientoModificarUsuarioForm.getCedulaProfesional2())){
					modificoCedulaProfesional = true;
				}
				if (mantenimientoModificarUsuarioFormAux.getIdEspecialidad2()!=mantenimientoModificarUsuarioForm.getIdEspecialidad2()) {
					modificoIdEspecialidad = true;
				}
				if (mantenimientoModificarUsuarioFormAux.getIdInstitucion2()!=mantenimientoModificarUsuarioForm.getIdInstitucion2()) {
					modificoIdInstitucion = true;
				}
				
				if (modificoCedulaProfesional || modificoIdEspecialidad || modificoIdInstitucion) {
					if (mantenimientoModificarUsuarioFormAux.getId2()!=0) {
						Usuarioespecialidades usuarioespecialidades = usuarioDao.getUsuarioEspecialidadesById(mantenimientoModificarUsuarioFormAux.getId2());
						if (mantenimientoModificarUsuarioForm.getIdEspecialidad2()==-1) {
							usuarioDao.removeUsuarioEspecialidades(usuarioespecialidades);
							Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
							usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
							usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
							usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.ELIMINAR_ESPECIALIDAD_CEDULA_INSTITUCION.getId()));
							usuarioauditoria.setFechaModificacion(new Date());
							usuarioauditoria.setValorAnterior(mantenimientoModificarUsuarioFormAux.getIdEspecialidad2() + "," + mantenimientoModificarUsuarioFormAux.getCedulaProfesional2() + "," + mantenimientoModificarUsuarioFormAux.getIdInstitucion2());
							usuarioauditoria.setValorNuevo(mantenimientoModificarUsuarioForm.getIdEspecialidad2() + "," + mantenimientoModificarUsuarioForm.getCedulaProfesional2() + "," + mantenimientoModificarUsuarioForm.getIdInstitucion2());
							usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
						}else{
							usuarioespecialidades.setCatespecialidadesmedicas(usuarioDao.getEspecialidadesId(mantenimientoModificarUsuarioForm.getIdEspecialidad2()));
							usuarioespecialidades.setInstitucionEducativaId(mantenimientoModificarUsuarioForm.getIdInstitucion2());
							usuarioespecialidades.setCedulaEspecialidad(mantenimientoModificarUsuarioForm.getCedulaProfesional2());
							usuarioDao.updateUsuarioEspecialidades(usuarioespecialidades);
							Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
							usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
							usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
							usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.CAMBIO_ESPECIALIDAD_CEDULA_INSTITUCION.getId()));
							usuarioauditoria.setFechaModificacion(new Date());
							usuarioauditoria.setValorAnterior(mantenimientoModificarUsuarioFormAux.getIdEspecialidad2() + "," + mantenimientoModificarUsuarioFormAux.getCedulaProfesional2() + "," + mantenimientoModificarUsuarioFormAux.getIdInstitucion2());
							usuarioauditoria.setValorNuevo(mantenimientoModificarUsuarioForm.getIdEspecialidad2() + "," + mantenimientoModificarUsuarioForm.getCedulaProfesional2() + "," + mantenimientoModificarUsuarioForm.getIdInstitucion2());
							usuarioDao.saveUsuarioAuditoria(usuarioauditoria);							
						}
					}else{
						Usuarioespecialidades usuarioespecialidades = new Usuarioespecialidades();
						usuarioespecialidades.setCatespecialidadesmedicas(usuarioDao.getEspecialidadesId(mantenimientoModificarUsuarioForm.getIdEspecialidad2()));
						usuarioespecialidades.setInstitucionEducativaId(mantenimientoModificarUsuarioForm.getIdInstitucion2());
						usuarioespecialidades.setCedulaEspecialidad(mantenimientoModificarUsuarioForm.getCedulaProfesional2());
						usuarioespecialidades.setUsuarios(usuarios);
						usuarioDao.saveUsuarioEspecialidad(usuarioespecialidades);	
						Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
						usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
						usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
						usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.AGREGAR_ESPECIALIDAD_CEDULA_INSTITUCION.getId()));
						usuarioauditoria.setFechaModificacion(new Date());
						usuarioauditoria.setValorAnterior(mantenimientoModificarUsuarioFormAux.getIdEspecialidad2() + "," + mantenimientoModificarUsuarioFormAux.getCedulaProfesional2() + "," + mantenimientoModificarUsuarioFormAux.getIdInstitucion2());
						usuarioauditoria.setValorNuevo(mantenimientoModificarUsuarioForm.getIdEspecialidad2() + "," + mantenimientoModificarUsuarioForm.getCedulaProfesional2() + "," + mantenimientoModificarUsuarioForm.getIdInstitucion2());
						usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
					}
				}	
				
				modificoCedulaProfesional = false;
				modificoIdEspecialidad = false;
				modificoIdInstitucion = false;
				
				if(!mantenimientoModificarUsuarioFormAux.getCedulaProfesional3().equals(mantenimientoModificarUsuarioForm.getCedulaProfesional3())){
					modificoCedulaProfesional = true;
				}
				if (mantenimientoModificarUsuarioFormAux.getIdEspecialidad3()!=mantenimientoModificarUsuarioForm.getIdEspecialidad3()) {
					modificoIdEspecialidad = true;
				}
				if (mantenimientoModificarUsuarioFormAux.getIdInstitucion3()!=mantenimientoModificarUsuarioForm.getIdInstitucion3()) {
					modificoIdInstitucion = true;
				}
				
				if (modificoCedulaProfesional || modificoIdEspecialidad || modificoIdInstitucion) {
					if (mantenimientoModificarUsuarioFormAux.getId3()!=0) {
						Usuarioespecialidades usuarioespecialidades = usuarioDao.getUsuarioEspecialidadesById(mantenimientoModificarUsuarioFormAux.getId3());
						if (mantenimientoModificarUsuarioForm.getIdEspecialidad3()==-1) {
							usuarioDao.removeUsuarioEspecialidades(usuarioespecialidades);
							Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
							usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
							usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
							usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.ELIMINAR_ESPECIALIDAD_CEDULA_INSTITUCION.getId()));
							usuarioauditoria.setFechaModificacion(new Date());
							usuarioauditoria.setValorAnterior(mantenimientoModificarUsuarioFormAux.getIdEspecialidad3() + "," + mantenimientoModificarUsuarioFormAux.getCedulaProfesional3() + "," + mantenimientoModificarUsuarioFormAux.getIdInstitucion3());
							usuarioauditoria.setValorNuevo(mantenimientoModificarUsuarioForm.getIdEspecialidad3() + "," + mantenimientoModificarUsuarioForm.getCedulaProfesional3() + "," + mantenimientoModificarUsuarioForm.getIdInstitucion3());
							usuarioDao.saveUsuarioAuditoria(usuarioauditoria);						
						}else{
							usuarioespecialidades.setCatespecialidadesmedicas(usuarioDao.getEspecialidadesId(mantenimientoModificarUsuarioForm.getIdEspecialidad3()));
							usuarioespecialidades.setInstitucionEducativaId(mantenimientoModificarUsuarioForm.getIdInstitucion3());
							usuarioespecialidades.setCedulaEspecialidad(mantenimientoModificarUsuarioForm.getCedulaProfesional3());
							usuarioDao.updateUsuarioEspecialidades(usuarioespecialidades);
							Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
							usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
							usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
							usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.CAMBIO_ESPECIALIDAD_CEDULA_INSTITUCION.getId()));
							usuarioauditoria.setFechaModificacion(new Date());
							usuarioauditoria.setValorAnterior(mantenimientoModificarUsuarioFormAux.getIdEspecialidad3() + "," + mantenimientoModificarUsuarioFormAux.getCedulaProfesional3() + "," + mantenimientoModificarUsuarioFormAux.getIdInstitucion3());
							usuarioauditoria.setValorNuevo(mantenimientoModificarUsuarioForm.getIdEspecialidad3() + "," + mantenimientoModificarUsuarioForm.getCedulaProfesional3() + "," + mantenimientoModificarUsuarioForm.getIdInstitucion3());
							usuarioDao.saveUsuarioAuditoria(usuarioauditoria);							
						}
					}else{
						Usuarioespecialidades usuarioespecialidades = new Usuarioespecialidades();
						usuarioespecialidades.setCatespecialidadesmedicas(usuarioDao.getEspecialidadesId(mantenimientoModificarUsuarioForm.getIdEspecialidad3()));
						usuarioespecialidades.setInstitucionEducativaId(mantenimientoModificarUsuarioForm.getIdInstitucion3());
						usuarioespecialidades.setCedulaEspecialidad(mantenimientoModificarUsuarioForm.getCedulaProfesional3());
						usuarioespecialidades.setUsuarios(usuarios);
						usuarioDao.saveUsuarioEspecialidad(usuarioespecialidades);
						Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
						usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
						usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
						usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.AGREGAR_ESPECIALIDAD_CEDULA_INSTITUCION.getId()));
						usuarioauditoria.setFechaModificacion(new Date());
						usuarioauditoria.setValorAnterior(mantenimientoModificarUsuarioFormAux.getIdEspecialidad3() + "," + mantenimientoModificarUsuarioFormAux.getCedulaProfesional3() + "," + mantenimientoModificarUsuarioFormAux.getIdInstitucion3());
						usuarioauditoria.setValorNuevo(mantenimientoModificarUsuarioForm.getIdEspecialidad3() + "," + mantenimientoModificarUsuarioForm.getCedulaProfesional3() + "," + mantenimientoModificarUsuarioForm.getIdInstitucion3());
						usuarioDao.saveUsuarioAuditoria(usuarioauditoria);					
					}
				}			
			}
			
			if (cambioUsuarioVigenciaLugarAtencion) {
				usuarioDao.updateUsuarioVigenciaLugarAtencion(usuariovigencialugaratencion);
			}
			
			if (validarEspecialidades == false) {
				Collection<Usuariorol> usuariorols = usuarioDao.getUsuariosRol(usuarios.getUsuarioId());
				boolean borrarEspecialidades = true;
				for (Usuariorol usuariorol : usuariorols) {
					if (usuariorol.getRoles().getRolId() == RolesEnum.MEDICO.getId() || usuariorol.getRoles().getRolId() == RolesEnum.DENTISTA.getId()) {
						borrarEspecialidades = false;
					}
				}	
				if (borrarEspecialidades) {
					Collection<Usuarioespecialidades> usuarioespecialidades = usuarioDao.getUsuarioEspecialidadesByIdUsuario(usuarios.getUsuarioId());
					for (Usuarioespecialidades usuarioespecialidad : usuarioespecialidades) {
						Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
						usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
						usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
						usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.AGREGAR_ESPECIALIDAD_CEDULA_INSTITUCION.getId()));
						usuarioauditoria.setFechaModificacion(new Date());
						usuarioauditoria.setValorAnterior(usuarioespecialidad.getCatespecialidadesmedicas().getEspecialidadMedicaId() + "," + usuarioespecialidad.getCedulaEspecialidad() + "," + usuarioespecialidad.getInstitucionEducativaId());
						usuarioauditoria.setValorNuevo(-1 + ",," + -1);
						usuarioDao.saveUsuarioAuditoria(usuarioauditoria);					
					}
				}
			}
		}else{
			if(mantenimientoModificarUsuarioFormAux.getVigencia() != mantenimientoModificarUsuarioForm.getVigencia()){
				cambioUsuarioVigenciaLugarAtencion = true;
				usuariovigencialugaratencion.setCatvigencia(usuarioDao.getCatVigenciaById(mantenimientoModificarUsuarioForm.getVigencia()));
				Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
				usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
				usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
				usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.CAMBIO_DE_VIGENCIA.getId()));
				usuarioauditoria.setFechaModificacion(new Date());
				usuarioauditoria.setValorAnterior(""+mantenimientoModificarUsuarioFormAux.getVigencia());
				usuarioauditoria.setValorNuevo(""+mantenimientoModificarUsuarioForm.getVigencia());
				usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
			}
			
			if (cambioUsuarioVigenciaLugarAtencion) {
				usuarioDao.updateUsuarioVigenciaLugarAtencion(usuariovigencialugaratencion);
			}
		}
	}

}
