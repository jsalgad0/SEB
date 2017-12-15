package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAutorizacionEspecialDao;
import com.mx.sab.dao.IGenericDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.enums.CatEstadosEnum;
import com.mx.sab.enums.CatEstatusUsuarioEnum;
import com.mx.sab.enums.CatModificacionUsuarioEnum;
import com.mx.sab.enums.CatTipoAutorizacionEnum;
import com.mx.sab.enums.CatVigenciaEnum;
import com.mx.sab.enums.RolesEnum;
import com.mx.sab.form.MantenimientoUsuarioNuevoForm;
import com.mx.sab.model.Catespecialidadesmedicas;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Lugaresdeatencionroles;
import com.mx.sab.model.Permisoespecial;
import com.mx.sab.model.Roles;
import com.mx.sab.model.Usuarioauditoria;
import com.mx.sab.model.Usuarioespecialidades;
import com.mx.sab.model.Usuariolugaratencion;
import com.mx.sab.model.Usuariorol;
import com.mx.sab.model.Usuarios;
import com.mx.sab.model.Usuariovigencialugaratencion;
import com.mx.sab.service.IMantenimientoUsuarioNuevoService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class MantenimientoUsuarioNuevoServiceImpl implements IMantenimientoUsuarioNuevoService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Autowired
	private IGenericDao genericDao;	
	
	@Override
	public Collection<Catespecialidadesmedicas> getEspecialidades() {
		return usuarioDao.getEspecialidades();
	}

	@Override
	public Collection<Roles> getRoles(UserInfo userInfo) {
		Collection<Lugaresdeatencionroles> lugarDeAtencionRoles = lugarAtencionDao.getLugaresAtencionRolesByIdLugarAtencion(userInfo.getLugaresdeatencion().getLugarDeAtencionId());
		Collection<Roles> roles = new ArrayList<>();
		for (Lugaresdeatencionroles lugaresdeatencionroles : lugarDeAtencionRoles) {
			if (RolesEnum.ADMINISTRADOR_LUGAR_ATENCION.getId() == Integer.parseInt(userInfo.getRol())) {
				if(lugaresdeatencionroles.getRoles().getRolId() != RolesEnum.ADMINISTRADOR.getId()){
					roles.add(lugaresdeatencionroles.getRoles());	
				}
			}else{
				roles.add(lugaresdeatencionroles.getRoles());
			}
		}
		
		return roles;
	}

	@Override
	public void verificarRfc(MantenimientoUsuarioNuevoForm mantenimientoUsuarioNuevoForm, UserInfo userInfo, Collection<Roles> roles) {
		Usuarios usuarios = usuarioDao.getUsuarioByRfc(mantenimientoUsuarioNuevoForm.getRfc());
		Collection<String> errores = new ArrayList<>();
		mantenimientoUsuarioNuevoForm.setErrores(errores);
		if(usuarios!=null){
			if (usuarios.getUsuariovigencialugaratencions()!=null) {
				Iterator<Usuariovigencialugaratencion> usuarioVigencuaLugarIterator = usuarios.getUsuariovigencialugaratencions().iterator();
				int idVigencia = 0;
				String claveInterna = "";
				while (usuarioVigencuaLugarIterator.hasNext()) {
					Usuariovigencialugaratencion usuariovigencialugaratencion = (Usuariovigencialugaratencion) usuarioVigencuaLugarIterator.next();
					idVigencia = usuariovigencialugaratencion.getCatvigencia().getVigenciaId();
					claveInterna = usuariovigencialugaratencion.getClaveInterna();
				}
				
				if (idVigencia==CatVigenciaEnum.VIGENTE.getId()) {
					if (usuarios.getCatestatususuario().getId() == CatEstatusUsuarioEnum.DECLARACION_RECHAZADA.getId()) {
						mantenimientoUsuarioNuevoForm.setError("Usuario debe aceptar Declaración de Responsabilidad. Utilice la opción para este caso");	
					}else if (usuarios.getCatestatususuario().getId() == CatEstatusUsuarioEnum.AUTORIZACION_PENDIENTE.getId()) {
						mantenimientoUsuarioNuevoForm.setError("Usuario debe ir con el Supervisor para obtener Autorización");
					}else if (usuarios.getCatestatususuario().getId() == CatEstatusUsuarioEnum.AUTORIZADO_CON_HUELLA.getId() || usuarios.getCatestatususuario().getId() == CatEstatusUsuarioEnum.AUTORIZADO_CON_CLAVE.getId()) {
						Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+userInfo.getTx_Marca());
						
						Iterator<Usuariolugaratencion> usuarioLugarAtencionIterator = usuarios.getUsuariolugaratencions().iterator();
						String usuarioLugarAtencionString = "";
						boolean mismoLugarAtencion = false;
						while (usuarioLugarAtencionIterator.hasNext()) {
							Usuariolugaratencion usuariolugaratencion = (Usuariolugaratencion) usuarioLugarAtencionIterator.next();
							if (usuariolugaratencion.getLugaresdeatencion().getLugarDeAtencionId() == lugaresdeatencion.getLugarDeAtencionId()) {
								mismoLugarAtencion = true;
								break;
							}
							usuarioLugarAtencionString = usuarioLugarAtencionString + usuariolugaratencion.getLugaresdeatencion().getDescripcion() + ", ";
						}
						
						if (mismoLugarAtencion) {
							mantenimientoUsuarioNuevoForm.setError("El Usuario ya se encuentra registrado en este lugar de atencion");
						}else{
							usuarioLugarAtencionString = usuarioLugarAtencionString.substring(0, usuarioLugarAtencionString.length()-2);
							mantenimientoUsuarioNuevoForm.setFuncionamiento(1);
							mantenimientoUsuarioNuevoForm.setRfcRegistrado(mantenimientoUsuarioNuevoForm.getRfc());
							mantenimientoUsuarioNuevoForm.setNombreRegistrado(usuarios.getNombre() + " " + usuarios.getApellidoPaterno() + " " + usuarios.getApellidoMaterno());
							mantenimientoUsuarioNuevoForm.setLugaresRegistrado(usuarioLugarAtencionString);
							mantenimientoUsuarioNuevoForm.setLugarRegistrado(lugaresdeatencion.getDescripcion());
							mantenimientoUsuarioNuevoForm.setError("El Usuario con RFC "+mantenimientoUsuarioNuevoForm.getRfc()+" y nombre "+usuarios.getNombre() + " " + usuarios.getApellidoPaterno() + " " + usuarios.getApellidoMaterno() + ",  ya está registrado en el Lugar de Atención "+usuarioLugarAtencionString+" ¿Desea registrarlo además en el Lugar de Atención "+lugaresdeatencion.getDescripcion()+"?.");
							mantenimientoUsuarioNuevoForm.setIdUsuario(usuarios.getUsuarioId());
							mantenimientoUsuarioNuevoForm.getUsuarios().setNombre(usuarios.getNombre());
							mantenimientoUsuarioNuevoForm.getUsuarios().setApellidoPaterno(usuarios.getApellidoPaterno());
							mantenimientoUsuarioNuevoForm.getUsuarios().setApellidoMaterno(usuarios.getApellidoMaterno());
							mantenimientoUsuarioNuevoForm.getUsuarios().setCurp(usuarios.getCurp());
							mantenimientoUsuarioNuevoForm.getUsuarios().setTelefono(usuarios.getTelefono());
							mantenimientoUsuarioNuevoForm.getUsuarios().setMail(usuarios.getMail());
							mantenimientoUsuarioNuevoForm.setRfc(usuarios.getRfc());
							mantenimientoUsuarioNuevoForm.setSexo(usuarios.getCatsexos().getSexoId());
							mantenimientoUsuarioNuevoForm.setFechaNacimiento(FormatUtil.getDate(usuarios.getFechaDeNacimiento()));
							mantenimientoUsuarioNuevoForm.setClaveInterna(claveInterna);
							mantenimientoUsuarioNuevoForm.getUsuarios().setRegSs(usuarios.getRegSs());
							Collection<Usuariorol> usuariorols = usuarioDao.getUsuariosRol(usuarios.getUsuarioId());
							String[] rols = new String[roles.size()];
							int i = 0;
							for (Usuariorol usuariorol : usuariorols) {
								rols[i] = "" + usuariorol.getRoles().getRolId();
								i++;
							}
							mantenimientoUsuarioNuevoForm.setRoles(rols);
							mantenimientoUsuarioNuevoForm.setIdEspecialidad1(1);
							Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = usuarios.getUsuarioespecialidadeses().iterator();
							int j = 0;
							while (usuarioEspecialidadesIterator.hasNext()) {
								Usuarioespecialidades usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
								if (j==0) {
									mantenimientoUsuarioNuevoForm.setIdEspecialidad1(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedicaId());
									mantenimientoUsuarioNuevoForm.setCedulaProfesional1(usuarioespecialidades.getCedulaEspecialidad());
									mantenimientoUsuarioNuevoForm.setIdInstitucion1(usuarioespecialidades.getInstitucionEducativaId());
								}else if (j==1) {
									mantenimientoUsuarioNuevoForm.setIdEspecialidad2(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedicaId());
									mantenimientoUsuarioNuevoForm.setCedulaProfesional2(usuarioespecialidades.getCedulaEspecialidad());
									mantenimientoUsuarioNuevoForm.setIdInstitucion2(usuarioespecialidades.getInstitucionEducativaId());
								}else if (j==2) {
									mantenimientoUsuarioNuevoForm.setIdEspecialidad2(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedicaId());
									mantenimientoUsuarioNuevoForm.setCedulaProfesional2(usuarioespecialidades.getCedulaEspecialidad());
									mantenimientoUsuarioNuevoForm.setIdInstitucion3(usuarioespecialidades.getInstitucionEducativaId());
								}
								j++;
							}
							
							String rfcAux = mantenimientoUsuarioNuevoForm.getRfc().substring(0, 10);
							String total = ""+(usuarioDao.getUsuariosCount(rfcAux)+1);
							total = FormatUtil.agregaCeros(total, 3);
							rfcAux = rfcAux + total;
							mantenimientoUsuarioNuevoForm.setRfcAux(rfcAux);	
						}
						
					}
				}else if (idVigencia==CatVigenciaEnum.NO_VIGENTE.getId()) {
					mantenimientoUsuarioNuevoForm.setError("Usuario No Vigente, diríjase al Administrador de su Lugar de Atención");	
				}else{
					mantenimientoUsuarioNuevoForm.setError("Usuario Bloqueado por intentos de acceso, diríjase al Administrador de su Lugar de Atención");	
				}
			}else{
				mantenimientoUsuarioNuevoForm.setError("Usuario No Vigente, diríjase al Administrador de su Lugar de Atención");
			}
		}else{
			//mantenimientoUsuarioNuevoForm.setError("El Usuario no existe");
		}
	}

	@Override
	public void saveUsuario(MantenimientoUsuarioNuevoForm mantenimientoUsuarioNuevoForm,UserInfo userInfo) {
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+userInfo.getTx_Marca());
		if (mantenimientoUsuarioNuevoForm.getIdUsuario()!=0) {
			Usuarios usuarios = usuarioDao.getUsuarioById(mantenimientoUsuarioNuevoForm.getIdUsuario());
			Usuariolugaratencion usuariolugaratencion = new Usuariolugaratencion();
			usuariolugaratencion.setUsuarios(usuarios);
			usuariolugaratencion.setLugaresdeatencion(lugaresdeatencion);
			usuarioDao.saveUsuarioLugarAtencion(usuariolugaratencion);
			boolean especialidadesBandera = false;
			String valorNuevo = "";
			for (String rol : mantenimientoUsuarioNuevoForm.getRoles()) {
				Usuariorol usuariorol = new Usuariorol();
				usuariorol.setLugaresdeatencion(lugaresdeatencion);
				usuariorol.setRoles(usuarioDao.getRolesId(Integer.parseInt(rol)));
				usuariorol.setUsuarios(usuarios);	
				usuarioDao.saveUsuarioRol(usuariorol);
				
				if (Integer.parseInt(rol) == RolesEnum.MEDICO.getId() || Integer.parseInt(rol) == RolesEnum.DENTISTA.getId()) {
					especialidadesBandera = true;
				}
				
				valorNuevo = valorNuevo + usuariorol.getRoles().getRolId() + ",";
			}
			
			if (especialidadesBandera) {
				usuarios.setRegSs(mantenimientoUsuarioNuevoForm.getUsuarios().getRegSs());
				usuarioDao.update(usuarios);
				
				if (mantenimientoUsuarioNuevoForm.getIdEspecialidad1()!=-1) {
					Usuarioespecialidades usuarioespecialidades = new Usuarioespecialidades();
					if (mantenimientoUsuarioNuevoForm.getIdEspecialidad1()!=0) {
						usuarioespecialidades.setCatespecialidadesmedicas(usuarioDao.getEspecialidadesId(mantenimientoUsuarioNuevoForm.getIdEspecialidad1()));
						usuarioespecialidades.setInstitucionEducativaId(mantenimientoUsuarioNuevoForm.getIdInstitucion1());
						usuarioespecialidades.setCedulaEspecialidad(mantenimientoUsuarioNuevoForm.getCedulaProfesional1());
						usuarioespecialidades.setUsuarios(usuarios);
						usuarioDao.saveUsuarioEspecialidad(usuarioespecialidades);	
					}
				}
				
				if (mantenimientoUsuarioNuevoForm.getIdEspecialidad2()!=-1) {
					if (mantenimientoUsuarioNuevoForm.getIdEspecialidad2()!=0) {
						Usuarioespecialidades usuarioespecialidades = new Usuarioespecialidades();
						usuarioespecialidades.setCatespecialidadesmedicas(usuarioDao.getEspecialidadesId(mantenimientoUsuarioNuevoForm.getIdEspecialidad2()));
						usuarioespecialidades.setInstitucionEducativaId(mantenimientoUsuarioNuevoForm.getIdInstitucion2());
						usuarioespecialidades.setCedulaEspecialidad(mantenimientoUsuarioNuevoForm.getCedulaProfesional2());
						usuarioespecialidades.setUsuarios(usuarios);
						usuarioDao.saveUsuarioEspecialidad(usuarioespecialidades);						
					}
				}
				
				if (mantenimientoUsuarioNuevoForm.getIdEspecialidad3()!=-1) {
					if (mantenimientoUsuarioNuevoForm.getIdEspecialidad3()!=0) {
						Usuarioespecialidades usuarioespecialidades = new Usuarioespecialidades();
						usuarioespecialidades.setCatespecialidadesmedicas(usuarioDao.getEspecialidadesId(mantenimientoUsuarioNuevoForm.getIdEspecialidad3()));
						usuarioespecialidades.setInstitucionEducativaId(mantenimientoUsuarioNuevoForm.getIdInstitucion3());
						usuarioespecialidades.setCedulaEspecialidad(mantenimientoUsuarioNuevoForm.getCedulaProfesional3());
						usuarioespecialidades.setUsuarios(usuarios);
						usuarioDao.saveUsuarioEspecialidad(usuarioespecialidades);	
					}
				}
			}
			
			Usuariovigencialugaratencion usuariovigencialugaratencion = new Usuariovigencialugaratencion();
			usuariovigencialugaratencion.setUsuarios(usuarios);
			usuariovigencialugaratencion.setCatvigencia(usuarioDao.getCatVigenciaById(CatVigenciaEnum.NO_VIGENTE.getId()));
			usuariovigencialugaratencion.setLugaresdeatencion(lugaresdeatencion);
			usuariovigencialugaratencion.setClaveInterna(mantenimientoUsuarioNuevoForm.getClaveInterna());
			usuarioDao.saveUsuarioVigenciaLugarAtencion(usuariovigencialugaratencion);
			
			Usuarioauditoria usuarioauditoria = new Usuarioauditoria();
			usuarioauditoria.setCatmodificacionusuario(usuarioDao.getCatModificacionUsuarioById(CatModificacionUsuarioEnum.AGREGAR_ROLES_LUGAR_DE_ATENCION.getId()));
			usuarioauditoria.setFechaModificacion(new Date());
			usuarioauditoria.setUsuariosByUsuarioId(userInfo.getUsuarios());
			usuarioauditoria.setUsuariosByUsuarioIdModificado(usuarios);
			usuarioauditoria.setValorAnterior("N/A");
			usuarioauditoria.setValorNuevo(valorNuevo.substring(0, valorNuevo.length()-1));
			usuarioDao.saveUsuarioAuditoria(usuarioauditoria);
			
			mantenimientoUsuarioNuevoForm.setTermino(true);
			
		}else{
			Usuarios usuariosAux = usuarioDao.getUsuarioByRfc(mantenimientoUsuarioNuevoForm.getRfc());
			if(usuariosAux==null){
				Usuarios usuarios = mantenimientoUsuarioNuevoForm.getUsuarios();
				usuarios.setRfc(mantenimientoUsuarioNuevoForm.getRfc());
				usuarios.setNombreUsuario(usuarios.getRfc());
				usuarios.setActivo(1);
				usuarios.setCatestados(genericDao.getEstadoById(CatEstadosEnum.DISTRITO_FEDERAL.getId()));
				usuarios.setCatestatususuario(usuarioDao.getCatEstatusUsuarioById(CatEstatusUsuarioEnum.INGRESADO.getId()));
				usuarios.setCatsexos(genericDao.getCatSexos(mantenimientoUsuarioNuevoForm.getSexo()));
				usuarios.setFechaDeNacimiento(FormatUtil.getDate(mantenimientoUsuarioNuevoForm.getFechaNacimiento()));
				usuarioDao.save(usuarios);
				
				Usuariovigencialugaratencion usuariovigencialugaratencion = new Usuariovigencialugaratencion();
				usuariovigencialugaratencion.setUsuarios(usuarios);
				usuariovigencialugaratencion.setCatvigencia(usuarioDao.getCatVigenciaById(CatVigenciaEnum.NO_VIGENTE.getId()));
				usuariovigencialugaratencion.setLugaresdeatencion(lugaresdeatencion);
				usuariovigencialugaratencion.setClaveInterna(mantenimientoUsuarioNuevoForm.getClaveInterna());
				usuarioDao.saveUsuarioVigenciaLugarAtencion(usuariovigencialugaratencion);
				
				Usuariolugaratencion usuariolugaratencion = new Usuariolugaratencion();
				usuariolugaratencion.setUsuarios(usuarios);
				usuariolugaratencion.setLugaresdeatencion(lugaresdeatencion);
				usuarioDao.saveUsuarioLugarAtencion(usuariolugaratencion);
				
				boolean especialidadesBandera = false;
				for (String rol : mantenimientoUsuarioNuevoForm.getRoles()) {
					Usuariorol usuariorol = new Usuariorol();
					usuariorol.setLugaresdeatencion(lugaresdeatencion);
					usuariorol.setRoles(usuarioDao.getRolesId(Integer.parseInt(rol)));
					usuariorol.setUsuarios(usuarios);	
					usuarioDao.saveUsuarioRol(usuariorol);
					
					if (Integer.parseInt(rol) == RolesEnum.MEDICO.getId() || Integer.parseInt(rol) == RolesEnum.DENTISTA.getId()) {
						especialidadesBandera = true;
					}
				}
				
				if (especialidadesBandera) {
					if (mantenimientoUsuarioNuevoForm.getIdEspecialidad1()!=-1) {
						Usuarioespecialidades usuarioespecialidades = new Usuarioespecialidades();
						usuarioespecialidades.setCatespecialidadesmedicas(usuarioDao.getEspecialidadesId(mantenimientoUsuarioNuevoForm.getIdEspecialidad1()));
						usuarioespecialidades.setInstitucionEducativaId(mantenimientoUsuarioNuevoForm.getIdInstitucion1());
						usuarioespecialidades.setCedulaEspecialidad(mantenimientoUsuarioNuevoForm.getCedulaProfesional1());
						usuarioespecialidades.setUsuarios(usuarios);
						usuarioDao.saveUsuarioEspecialidad(usuarioespecialidades);
					}
					
					if (mantenimientoUsuarioNuevoForm.getIdEspecialidad2()!=-1) {
						Usuarioespecialidades usuarioespecialidades = new Usuarioespecialidades();
						usuarioespecialidades.setCatespecialidadesmedicas(usuarioDao.getEspecialidadesId(mantenimientoUsuarioNuevoForm.getIdEspecialidad2()));
						usuarioespecialidades.setInstitucionEducativaId(mantenimientoUsuarioNuevoForm.getIdInstitucion2());
						usuarioespecialidades.setCedulaEspecialidad(mantenimientoUsuarioNuevoForm.getCedulaProfesional2());
						usuarioespecialidades.setUsuarios(usuarios);
						usuarioDao.saveUsuarioEspecialidad(usuarioespecialidades);
					}
					
					if (mantenimientoUsuarioNuevoForm.getIdEspecialidad3()!=-1) {
						Usuarioespecialidades usuarioespecialidades = new Usuarioespecialidades();
						usuarioespecialidades.setCatespecialidadesmedicas(usuarioDao.getEspecialidadesId(mantenimientoUsuarioNuevoForm.getIdEspecialidad3()));
						usuarioespecialidades.setInstitucionEducativaId(mantenimientoUsuarioNuevoForm.getIdInstitucion3());
						usuarioespecialidades.setCedulaEspecialidad(mantenimientoUsuarioNuevoForm.getCedulaProfesional3());
						usuarioespecialidades.setUsuarios(usuarios);
						usuarioDao.saveUsuarioEspecialidad(usuarioespecialidades);
					}
				}
				mantenimientoUsuarioNuevoForm.setMostrarDeclaracion(true);
				mantenimientoUsuarioNuevoForm.setIdUsuario(usuarios.getUsuarioId());	
			}else{
				mantenimientoUsuarioNuevoForm.setMostrarDeclaracion(false);
				mantenimientoUsuarioNuevoForm.setBanderaError(true);
				mantenimientoUsuarioNuevoForm.setError("El usuario ya se encuentra registrado");
			}
			
		}
	}

	@Override
	public void updateUsuario(MantenimientoUsuarioNuevoForm mantenimientoUsuarioNuevoForm, UserInfo userInfo) {
		Collection<String> errores = new ArrayList<>();
		mantenimientoUsuarioNuevoForm.setErrores(errores);
		try{
			Usuarios usuarios = usuarioDao.getUsuarioById(mantenimientoUsuarioNuevoForm.getIdUsuario());
			usuarios.setCatestatususuario(usuarioDao.getCatEstatusUsuarioById(mantenimientoUsuarioNuevoForm.getEstadoUsuario()));
			if (usuarios.getCatestatususuario().getId()==CatEstatusUsuarioEnum.AUTORIZADO_CON_HUELLA.getId()) {
				Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+userInfo.getTx_Marca());
				Usuariovigencialugaratencion usuariovigencialugaratencion = usuarioDao.getUsuarioVigenciaLugarAtencionByIdUsuario(usuarios.getUsuarioId(),lugaresdeatencion.getLugarDeAtencionId());
				usuariovigencialugaratencion.setCatvigencia(usuarioDao.getCatVigenciaById(CatVigenciaEnum.VIGENTE.getId()));
//				usuariovigencialugaratencion.setClaveInterna(mantenimientoUsuarioNuevoForm.getClaveInterna());
				usuarioDao.updateUsuarioVigenciaLugarAtencion(usuariovigencialugaratencion);
			}else if (usuarios.getCatestatususuario().getId()==CatEstatusUsuarioEnum.AUTORIZACION_PENDIENTE.getId()) {
//				Permisoespecial permisoespecial = new Permisoespecial();
//				permisoespecial.setUsuariosByUsuarioId(usuarios);
//				permisoespecial.setCattipoautorizacion(autorizacionEspecialDao.getTipoAutorizacionById(CatTipoAutorizacionEnum.AUTENTIA.getId()));
//				Usuarios usuariosSolicita = usuarioDao.getUsuarioById(userInfo.getUsuarios().getUsuarioId());
//				permisoespecial.setUsuariosByUsuarioSolicitaId(usuariosSolicita);
//				permisoespecial.setLugaresdeatencion(userInfo.getLugaresdeatencion());
//				permisoespecial.setFechaInicio(new Date());
//				autorizacionEspecialDao.save(permisoespecial);
			}
			usuarioDao.update(usuarios);			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
