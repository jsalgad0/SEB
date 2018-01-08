package com.mx.sab.service.impl;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAuditoriaDao;
import com.mx.sab.dao.ILoginDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.enums.CatEstatusUsuarioEnum;
import com.mx.sab.enums.CatVigenciaEnum;
import com.mx.sab.form.LoginForm;
import com.mx.sab.model.Catpreguntasecreta;
import com.mx.sab.model.Loginlog;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Menu;
import com.mx.sab.model.Menurol;
import com.mx.sab.model.Permisoespecial;
import com.mx.sab.model.Roles;
import com.mx.sab.model.Usuariorol;
import com.mx.sab.model.Usuarios;
import com.mx.sab.model.Usuariovigencialugaratencion;
import com.mx.sab.service.ILoginService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.util.ValidatorsUtil;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private ILoginDao loginDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Autowired
	private IAuditoriaDao auditoriaDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public LoginForm validaRfc(LoginForm loginForm){
		//log.info("validaRfc");
		loginForm.setError("");
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(loginForm.getTx_Marca());
		if (lugaresdeatencion == null) {
			loginForm.setError("El lugar de atención no está vigente, no se pueden conectar usuarios");
			loginForm.setBanderaError(true);
		}else{
			Usuarios usuarios = loginDao.getUsuarioByRfc(loginForm.getRfc());
			
			if (usuarios!=null) {
				Iterator<Usuariovigencialugaratencion> usuarioVigenciaLugarAtencionIterator = usuarios.getUsuariovigencialugaratencions().iterator();
				boolean administrador = false;
				while (usuarioVigenciaLugarAtencionIterator.hasNext()) {
					Usuariovigencialugaratencion usuariovigencialugaratencion = (Usuariovigencialugaratencion) usuarioVigenciaLugarAtencionIterator.next();
					if (usuariovigencialugaratencion.getLugaresdeatencion().getCodigoLugarAtencion() == 100) {
						administrador = true;
					}
				}				
				loginForm.setAutenticacionHuella(true);
				if (usuarios.getUsuariorols().size()>1) {
					Iterator<Usuariorol> usuarioRolIterator = usuarios.getUsuariorols().iterator();
					Collection<Usuariorol> usuariorols = new ArrayList<>();
					while (usuarioRolIterator.hasNext()) {
						Usuariorol usuariorol = (Usuariorol) usuarioRolIterator.next();
						if (usuariorol.getLugaresdeatencion().getLugarDeAtencionId() == lugaresdeatencion.getLugarDeAtencionId()) {
							usuariorols.add(usuariorol);
						}else if (administrador) {
							usuariorols.add(usuariorol);
						}
					}
					loginForm.setUsuariorols(usuariorols);
					loginForm.setMuestraRoles(true);
				}
				loginForm.setUsuarios(usuarios);
				
				int vigencia = -1;
				usuarioVigenciaLugarAtencionIterator = usuarios.getUsuariovigencialugaratencions().iterator();
				while (usuarioVigenciaLugarAtencionIterator.hasNext()) {
					Usuariovigencialugaratencion usuariovigencialugaratencion = (Usuariovigencialugaratencion) usuarioVigenciaLugarAtencionIterator.next();
					if (usuariovigencialugaratencion.getLugaresdeatencion().getLugarDeAtencionId() == lugaresdeatencion.getLugarDeAtencionId()) {
						vigencia = usuariovigencialugaratencion.getCatvigencia().getVigenciaId();	
					}else if (administrador) {
						vigencia = 1;
					}
				}
				if(vigencia != -1){
					if(usuarios.getCatestatususuario().getId() == CatEstatusUsuarioEnum.AUTORIZADO_CON_HUELLA.getId() || usuarios.getCatestatususuario().getId() == CatEstatusUsuarioEnum.AUTORIZADO_CON_CLAVE.getId()){
						if (vigencia == CatVigenciaEnum.NO_VIGENTE.getId()) {
							loginForm.setError("Usuario no vigente");
							loginForm.setBanderaError(true);						
						}else if (vigencia == CatVigenciaEnum.BLOQUEADO.getId()) {
							loginForm.setError("Usuario Bloqueado por intentos de acceso");
							loginForm.setBanderaError(true);
						}else if (vigencia == CatVigenciaEnum.VIGENTE.getId() && usuarios.getCatestatususuario().getId() == CatEstatusUsuarioEnum.AUTORIZADO_CON_HUELLA.getId()) {
							loginForm.setValidarHuella(true);
							loginForm.setIdUsuario(usuarios.getUsuarioId());
							loginForm.setNombre(usuarios.getNombre());
							loginForm.setApellidoPaterno(usuarios.getApellidoPaterno());
							loginForm.setApellidoMaterno(usuarios.getApellidoMaterno());
							loginForm.setSexo(usuarios.getCatsexos().getSexoId());
							loginForm.setFechaNacimiento(FormatUtil.getDate(usuarios.getFechaDeNacimiento()));
						}else if (vigencia == CatVigenciaEnum.VIGENTE.getId() && usuarios.getCatestatususuario().getId() == CatEstatusUsuarioEnum.AUTORIZADO_CON_CLAVE.getId()) {
							Permisoespecial permisoEspecial = usuarioDao.getPermisoEspecialByIdUsuario(usuarios.getUsuarioId(), lugaresdeatencion.getLugarDeAtencionId());
							Date fechaHoy = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				        	try {
				        		fechaHoy = sdf.parse(FormatUtil.getDate());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(fechaHoy.compareTo(permisoEspecial.getFechaFin())> 0){
								Usuarios usuario  = usuarioDao.getUsuarioById(usuarios.getUsuarioId());
								usuario.setCatestatususuario(usuarioDao.getCatEstatusUsuarioById(1));
								usuarioDao.update(usuario);	
								loginForm.setError("Usuario requiere autorización de uso de clave, consulte al Supervisor");
								loginForm.setBanderaError(true);
							}else{
								loginForm.setValidarClave(true);
								loginForm.setIdUsuario(usuarios.getUsuarioId());
								loginForm.setNombre(usuarios.getNombre());
								loginForm.setApellidoPaterno(usuarios.getApellidoPaterno());
								loginForm.setApellidoMaterno(usuarios.getApellidoMaterno());
								loginForm.setSexo(usuarios.getCatsexos().getSexoId());
								loginForm.setFechaNacimiento(FormatUtil.getDate(usuarios.getFechaDeNacimiento()));	
							}
							
												
						}
					}else if (usuarios.getCatestatususuario().getId() == CatEstatusUsuarioEnum.DECLARACION_RECHAZADA.getId()) {
						loginForm.setError("Usuario requiere aceptar Declaración de Responsabilidad, consulte al Administrador");
						loginForm.setBanderaError(true);
					}else if (usuarios.getCatestatususuario().getId() == CatEstatusUsuarioEnum.AUTORIZACION_PENDIENTE.getId()) {
						loginForm.setError("Usuario requiere autorización de uso de clave, consulte al Supervisor");
						loginForm.setBanderaError(true);
					}
				}else{
					loginForm.setError("Usuario no registrado en este lugar de atención");
					loginForm.setBanderaError(true);
				}
			}else{
				loginForm.setError("Usuario no registrado");
				loginForm.setBanderaError(true);
			}
		}
		return loginForm;
	}

	@Override
	public List<Menu> getRoles(String rol) {
		//log.info("getRoles");
		List<Menu> menus = new ArrayList<>();
		List<Roles> roles = loginDao.getRoles(Integer.parseInt(rol));
		Roles rolUnico = roles.get(0);
		Iterator<Menurol> menuRolIterator = rolUnico.getMenurols().iterator();
		while (menuRolIterator.hasNext()) {
			Menurol menurol = menuRolIterator.next();
			menus.add(menurol.getMenu());
		}
		return menus;
	}

	@Override
	public LoginForm validaClave(LoginForm loginForm) {
		loginForm.setErrorClave("");
		String rfcMd5 = FormatUtil.getMd5(loginForm.getRfc());
		if (FormatUtil.getMd5(loginForm.getClave()).equals(rfcMd5)) {
			Collection<Loginlog> loginlogs = auditoriaDao.getLoginByUsuarioId(loginForm.getUsuarios().getUsuarioId());
			if (loginlogs.size()==0) {
				loginForm.setIngresarClave(true);
			}else{
				loginForm.setErrorClave("Clave no puede ser igual al RFC");
			}
		}else{
			//Usuarios usuarios = usuarioDao.getUsuarioById(loginForm.getIdUsuario());
			Usuarios usuarios = usuarioDao.getUsuarioById(loginForm.getUsuarios().getUsuarioId());
			String clave = FormatUtil.getMd5(loginForm.getClave());
			if (clave.equals(usuarios.getPsswd())) {
				if (usuarios.getFechaPsswdTemp()!=null) {
					Date dateVigencia = new Date(usuarios.getFechaPsswdTemp().getTime());
					if(FormatUtil.getHoras(dateVigencia.getTime())<=6){
						loginForm.setClaveExitosa(true);
						usuarios.setFechaPsswdTemp(null);
					}else{
						loginForm.setErrorClave("Su clave ha expirado");	
					}	
				}else{
					loginForm.setClaveExitosa(true);
					usuarios.setFechaPsswdTemp(null);
				}
				if (loginForm.isClaveExitosa()){
					usuarios.setFechaPsswdTemp(null);
					usuarios.setIntentosClave(0);
				}
				usuarioDao.update(usuarios);
			}else{
				int intentosClave = 0;
				if(usuarios.getIntentosClave()!=null){
					intentosClave = usuarios.getIntentosClave(); 
				}
				usuarios.setIntentosClave(intentosClave+1);
				if (usuarios.getIntentosClave() >= 5) {
					loginForm.setErrorClave("Demasiados intentos, favor comuníquese con el Administrador del sistema en su Lugar de Atención");
					loginForm.setIrLogin(true);
					Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(loginForm.getTx_Marca());
					Iterator<Usuariovigencialugaratencion> usuIterator = usuarios.getUsuariovigencialugaratencions().iterator();
					while (usuIterator.hasNext()) {
						Usuariovigencialugaratencion usuariovigencialugaratencion = (Usuariovigencialugaratencion) usuIterator.next();
						if (usuariovigencialugaratencion.getLugaresdeatencion().getLugarDeAtencionId() == lugaresdeatencion.getLugarDeAtencionId()) {
							usuariovigencialugaratencion.setCatvigencia(usuarioDao.getCatVigenciaById(CatVigenciaEnum.BLOQUEADO.getId()));
							usuarioDao.updateUsuarioVigenciaLugarAtencion(usuariovigencialugaratencion);	
							break;
						}
					}
				}else{
					loginForm.setErrorClave("Clave no corresponde");		
				}
				usuarioDao.update(usuarios);
			}
			
		}
		return loginForm;
	}

	@Override
	public Collection<Catpreguntasecreta> getPreguntas() {
		Collection<Catpreguntasecreta> catpreguntasecretas = loginDao.getPreguntas();
		return catpreguntasecretas;
	}

	@Override
	public void validarClave(LoginForm loginForm) {
		boolean error = false;
		if (loginForm.getIdPregunta()==-1) {
			loginForm.setErrorPregunta("Seleccione una pregunta");
			error = true;
		}
		
		if (loginForm.getRespuesta().trim().length()==0) {
			loginForm.setErrorRespuesta("Ingrese la respuesta");
			error = true;
		}
		
		if (loginForm.getIngresaClave().trim().length()==0) {
			loginForm.setErrorClave("Ingrese la contraseña");
			error = true;
		}
		
		if (loginForm.getConfirmaClave().trim().length()==0) {
			loginForm.setErrorConfirmacionClave("Ingrese la confirmacion");
			error = true;
		}
		
		if (error == false) {
			if (loginForm.getIngresaClave().equals(loginForm.getConfirmaClave())) {
				if(ValidatorsUtil.validaNumeros(loginForm.getIngresaClave())){
					if(ValidatorsUtil.validaLetraMayuscula(loginForm.getIngresaClave())){
						if(ValidatorsUtil.validaLetraMinuscula(loginForm.getIngresaClave())){
							if(ValidatorsUtil.validaLetrasRfc(loginForm.getIngresaClave(),loginForm.getRfc())){
								if(ValidatorsUtil.validaNumerosRfc(loginForm.getIngresaClave(),loginForm.getRfc())){
									Usuarios usuarios = usuarioDao.getUsuarioById(loginForm.getIdUsuario());
									Catpreguntasecreta catpreguntasecreta = loginDao.getPreguntaById(loginForm.getIdPregunta());
									usuarios.setCatpreguntasecreta(catpreguntasecreta);
									usuarios.setRespuestaSecreta(loginForm.getRespuesta());
									usuarios.setPsswd(FormatUtil.getMd5(loginForm.getIngresaClave()));
									usuarioDao.update(usuarios);
									usuarios = usuarioDao.getUsuarioById(loginForm.getIdUsuario());
									Loginlog loginlog = new Loginlog();
									loginlog.setAuditoriaAutentia("Evadio la Huella");	
									loginlog.setFecha(new Date());
									loginlog.setHora(new Time(loginlog.getFecha().getTime()));
									loginlog.setUsuarios(usuarios);
									loginlog.setConAutorizacionEspecial(0);
									loginlog.setSinHuella(0);
									usuarioDao.saveLoginLog(loginlog);
									loginForm.setTerminoIngresarClave(true);
								}else{
									loginForm.setErrorClave("La Contraseña no debe de llevar los números del RFC");
								}
							}else{
								loginForm.setErrorClave("La Contraseña no debe de llevar las letras del RFC");
							}
						}else{
							loginForm.setErrorClave("La Contraseña debe de llevar una letra minuscula");
						}
					}else{
						loginForm.setErrorClave("La Contraseña debe de llevar una letra mayuscula");
					}
				}else{
					loginForm.setErrorClave("La Contraseña debe de llevar un número");
				}
			}else{
				loginForm.setErrorConfirmacionClave("Verifique que la Contraseña sea la misma");	
			}
		}
	}

	@Override
	public void saveLoginLog(UserInfo userInfo) {
		Loginlog loginlog = new Loginlog();
		if (userInfo.getTx_Serie().equals("")) {
			loginlog.setAuditoriaAutentia("Evadio la Huella");	
		}else{
			loginlog.setAuditoriaAutentia(userInfo.getTx_Serie());
		}
		loginlog.setFecha(new Date());
		loginlog.setHora(new Time(loginlog.getFecha().getTime()));
		loginlog.setUsuarios(userInfo.getUsuarios());
		loginlog.setConAutorizacionEspecial(0);
		loginlog.setSinHuella(0);
		usuarioDao.saveLoginLog(loginlog);
	}

	@Override
	public void validarRespuesta(LoginForm loginForm) {
		Usuarios usuarios = usuarioDao.getUsuarioById(loginForm.getIdUsuario());
		if (usuarios.getRespuestaSecreta().equals(loginForm.getRespuesta())) {
			loginForm.setRespuestaExitosa(true);
			usuarios.setIntentosRespuesta(0);
		}else{
			int intentosRespuesta = 0;
			if(usuarios.getIntentosRespuesta()!=null){
				intentosRespuesta = usuarios.getIntentosRespuesta(); 
			}
			usuarios.setIntentosRespuesta(intentosRespuesta+1);
			if (usuarios.getIntentosRespuesta() >= 5) {
				loginForm.setErrorRespuesta("Demasiados intentos, favor comuníquese con el Administrador del sistema en su Lugar de Atención");
				loginForm.setIrLogin(true);
				Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(loginForm.getTx_Marca());
				Iterator<Usuariovigencialugaratencion> usuIterator = usuarios.getUsuariovigencialugaratencions().iterator();
				while (usuIterator.hasNext()) {
					Usuariovigencialugaratencion usuariovigencialugaratencion = (Usuariovigencialugaratencion) usuIterator.next();
					if (usuariovigencialugaratencion.getLugaresdeatencion().getLugarDeAtencionId() == lugaresdeatencion.getLugarDeAtencionId()) {
						usuariovigencialugaratencion.setCatvigencia(usuarioDao.getCatVigenciaById(CatVigenciaEnum.BLOQUEADO.getId()));
						usuarioDao.updateUsuarioVigenciaLugarAtencion(usuariovigencialugaratencion);	
						break;
					}
				}
			}else{
				loginForm.setErrorRespuesta("Respuesta no corresponde");	
			}
			usuarioDao.update(usuarios);
		}
	}

	@Override
	public void getUsuarioOlvidoClave(LoginForm loginForm) {
		Usuarios usuarios = usuarioDao.getUsuarioById(loginForm.getIdUsuario());
		loginForm.setRfc(usuarios.getRfc());
		loginForm.setPregunta(usuarios.getCatpreguntasecreta().getDescripcion());
	}

	@Override
	public void validarClaveNueva(LoginForm loginForm) {
		boolean error = false;
		
		if (loginForm.getIngresaClave().trim().length()==0) {
			loginForm.setErrorClave("Ingrese la contraseña");
			error = true;
		}
		
		if (loginForm.getConfirmaClave().trim().length()==0) {
			loginForm.setErrorConfirmacionClave("Ingrese la confirmacion");
			error = true;
		}
		
		if (error == false) {
			if (loginForm.getIngresaClave().equals(loginForm.getConfirmaClave())) {
				if(ValidatorsUtil.validaNumeros(loginForm.getIngresaClave())){
					if(ValidatorsUtil.validaLetraMayuscula(loginForm.getIngresaClave())){
						if(ValidatorsUtil.validaLetraMinuscula(loginForm.getIngresaClave())){
							if(ValidatorsUtil.validaLetrasRfc(loginForm.getIngresaClave(),loginForm.getRfc())){
								if(ValidatorsUtil.validaNumerosRfc(loginForm.getIngresaClave(),loginForm.getRfc())){
									Usuarios usuarios = usuarioDao.getUsuarioById(loginForm.getIdUsuario());
									usuarios.setPsswd(FormatUtil.getMd5(loginForm.getIngresaClave()));
									usuarioDao.update(usuarios);
									usuarios = usuarioDao.getUsuarioById(loginForm.getIdUsuario());
									loginForm.setTerminoIngresarClave(true);
								}else{
									loginForm.setErrorClave("La Contraseña no debe de llevar los números del RFC");
								}
							}else{
								loginForm.setErrorClave("La Contraseña no debe de llevar las letras del RFC");
							}
						}else{
							loginForm.setErrorClave("La Contraseña debe de llevar una letra minuscula");
						}
					}else{
						loginForm.setErrorClave("La Contraseña debe de llevar una letra mayuscula");
					}
				}else{
					loginForm.setErrorClave("La Contraseña debe de llevar un número");
				}
			}else{
				loginForm.setErrorConfirmacionClave("Verifique que la Contraseña sea la misma");	
			}
		}
	}
}
