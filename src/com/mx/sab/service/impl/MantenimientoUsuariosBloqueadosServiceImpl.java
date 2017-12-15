package com.mx.sab.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.enums.CatVigenciaEnum;
import com.mx.sab.form.MantenimientoUsuarioBloqueadoForm;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Roles;
import com.mx.sab.model.Usuarioespecialidades;
import com.mx.sab.model.Usuariorol;
import com.mx.sab.model.Usuarios;
import com.mx.sab.model.Usuariovigencialugaratencion;
import com.mx.sab.service.IMantenimientoUsuariosBloqueadosService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class MantenimientoUsuariosBloqueadosServiceImpl implements IMantenimientoUsuariosBloqueadosService {

	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	public Collection<Usuarios> getUsuarios(UserInfo userInfo) {
		//log.info("get usuarios");
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+userInfo.getTx_Marca());
		Collection<Usuariovigencialugaratencion> usuariovigencialugaratencions = usuarioDao.getUsuariosBloqueados(lugaresdeatencion.getLugarDeAtencionId());
		Collection<Usuarios> usuarios = new ArrayList<>();
		for (Usuariovigencialugaratencion usuariovigencialugaratencion : usuariovigencialugaratencions) {
			usuarios.add(usuariovigencialugaratencion.getUsuarios());
		}
		return usuarios;
	}

	@Override
	public void getUsuario(MantenimientoUsuarioBloqueadoForm mantenimientoUsuarioBloqueadoForm, Collection<Roles> roles, UserInfo userInfo) {
		
		mantenimientoUsuarioBloqueadoForm.setIdUsuarioAdministrador(userInfo.getUsuarios().getUsuarioId());
		mantenimientoUsuarioBloqueadoForm.setRfcAdministrador(userInfo.getUsuarios().getRfc());
		mantenimientoUsuarioBloqueadoForm.setNombreAdministrador(userInfo.getUsuarios().getNombre());
		mantenimientoUsuarioBloqueadoForm.setApellidoMaternoAdministrador(userInfo.getUsuarios().getApellidoMaterno());
		mantenimientoUsuarioBloqueadoForm.setApellidoPaternoAdministrador(userInfo.getUsuarios().getApellidoPaterno());
		mantenimientoUsuarioBloqueadoForm.setSexoAdministrador(userInfo.getUsuarios().getCatsexos().getSexoId());
		mantenimientoUsuarioBloqueadoForm.setFechaNacimientoAdministrador(FormatUtil.getDate(userInfo.getUsuarios().getFechaDeNacimiento()));
		
		Usuarios usuarios = usuarioDao.getUsuarioById(mantenimientoUsuarioBloqueadoForm.getIdUsuario());
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+userInfo.getTx_Marca());
		mantenimientoUsuarioBloqueadoForm.setIdUsuario(usuarios.getUsuarioId());
		mantenimientoUsuarioBloqueadoForm.setApellidoMaterno(usuarios.getApellidoMaterno());
		mantenimientoUsuarioBloqueadoForm.setApellidoPaterno(usuarios.getApellidoPaterno());
		mantenimientoUsuarioBloqueadoForm.setCurp(usuarios.getCurp());
		mantenimientoUsuarioBloqueadoForm.setFechaNacimiento(FormatUtil.getDate(usuarios.getFechaDeNacimiento()));
		mantenimientoUsuarioBloqueadoForm.setNombre(usuarios.getNombre());
		mantenimientoUsuarioBloqueadoForm.setRfc(usuarios.getRfc());
		mantenimientoUsuarioBloqueadoForm.setSexo(usuarios.getCatsexos().getSexoId());
		mantenimientoUsuarioBloqueadoForm.setTelefono(usuarios.getTelefono());
		mantenimientoUsuarioBloqueadoForm.setMail(usuarios.getMail());
		mantenimientoUsuarioBloqueadoForm.setRegSs(usuarios.getRegSs());
		
		Collection<Usuariorol> usuariorols = usuarioDao.getUsuariosRolByLugar(usuarios.getUsuarioId(),lugaresdeatencion.getLugarDeAtencionId());
		String[] rols = new String[roles.size()];
		int i = 0;
		for (Usuariorol usuariorol : usuariorols) {
			rols[i] = "" + usuariorol.getRoles().getRolId();
			i++;
		}
		mantenimientoUsuarioBloqueadoForm.setRoles(rols);
		
		Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = usuarios.getUsuarioespecialidadeses().iterator();
		i = 0;
		
		mantenimientoUsuarioBloqueadoForm.setIdEspecialidad1(-1);
		mantenimientoUsuarioBloqueadoForm.setIdInstitucion1("");
		mantenimientoUsuarioBloqueadoForm.setCedulaProfesional1("");
		mantenimientoUsuarioBloqueadoForm.setIdEspecialidad2(-1);
		mantenimientoUsuarioBloqueadoForm.setIdInstitucion2("");
		mantenimientoUsuarioBloqueadoForm.setCedulaProfesional2("");				
		mantenimientoUsuarioBloqueadoForm.setIdEspecialidad3(-1);
		mantenimientoUsuarioBloqueadoForm.setIdInstitucion3("");
		mantenimientoUsuarioBloqueadoForm.setCedulaProfesional3("");	
		
		while (usuarioEspecialidadesIterator.hasNext()) {
			Usuarioespecialidades usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
			if (i==0) {
				mantenimientoUsuarioBloqueadoForm.setIdEspecialidad1(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedicaId());
				mantenimientoUsuarioBloqueadoForm.setIdInstitucion1(usuarioespecialidades.getInstitucionEducativaId());
				mantenimientoUsuarioBloqueadoForm.setCedulaProfesional1(usuarioespecialidades.getCedulaEspecialidad());
			}else if (i==1) {
				mantenimientoUsuarioBloqueadoForm.setIdEspecialidad2(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedicaId());
				mantenimientoUsuarioBloqueadoForm.setIdInstitucion2(usuarioespecialidades.getInstitucionEducativaId());
				mantenimientoUsuarioBloqueadoForm.setCedulaProfesional2(usuarioespecialidades.getCedulaEspecialidad());				
			}else if (i==2) {
				mantenimientoUsuarioBloqueadoForm.setIdEspecialidad3(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedicaId());
				mantenimientoUsuarioBloqueadoForm.setIdInstitucion3(usuarioespecialidades.getInstitucionEducativaId());
				mantenimientoUsuarioBloqueadoForm.setCedulaProfesional3(usuarioespecialidades.getCedulaEspecialidad());				
			}
			i++;
		}
		
		Iterator<Usuariovigencialugaratencion> usuarioVigenciaLugarIterator = usuarios.getUsuariovigencialugaratencions().iterator();
		while (usuarioVigenciaLugarIterator.hasNext()) {
			Usuariovigencialugaratencion usuariovigencialugaratencion = (Usuariovigencialugaratencion) usuarioVigenciaLugarIterator.next();
			if (usuariovigencialugaratencion.getLugaresdeatencion().getLugarDeAtencionId() == lugaresdeatencion.getLugarDeAtencionId()) {
				mantenimientoUsuarioBloqueadoForm.setClaveInterna(usuariovigencialugaratencion.getClaveInterna());
				mantenimientoUsuarioBloqueadoForm.setVigencia(usuariovigencialugaratencion.getCatvigencia().getVigenciaId());
			}
		}
		
		System.out.println(mantenimientoUsuarioBloqueadoForm.getMail());
	}

	@Override
	public void getActualizarUsuario(MantenimientoUsuarioBloqueadoForm mantenimientoUsuarioBloqueadoForm,UserInfo userInfo) {
		Usuarios usuarios = usuarioDao.getUsuarioById(mantenimientoUsuarioBloqueadoForm.getIdUsuario());
		
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+userInfo.getTx_Marca());
		Iterator<Usuariovigencialugaratencion> usuarioVigenciaLugarAtencionIterator = usuarios.getUsuariovigencialugaratencions().iterator();
		while (usuarioVigenciaLugarAtencionIterator.hasNext()) {
			Usuariovigencialugaratencion usuariovigencialugaratencion = (Usuariovigencialugaratencion) usuarioVigenciaLugarAtencionIterator.next();
			if (usuariovigencialugaratencion.getLugaresdeatencion().getLugarDeAtencionId() == lugaresdeatencion.getLugarDeAtencionId()) {
				usuariovigencialugaratencion.setCatvigencia(usuarioDao.getCatVigenciaById(CatVigenciaEnum.VIGENTE.getId()));
				usuarioDao.updateUsuarioVigenciaLugarAtencion(usuariovigencialugaratencion);
			}
		}
		
		String clave = FormatUtil.getMd5(usuarios.getRfc()+FormatUtil.getDateClave()).substring(0, 6);
		
		usuarios.setMail(mantenimientoUsuarioBloqueadoForm.getMail());
		usuarios.setPsswd(FormatUtil.getMd5(clave));
		
		Calendar c = Calendar.getInstance(); 
		c.add(Calendar.HOUR, 6);
		usuarios.setFechaPsswdTemp(new Timestamp(c.getTime().getTime()));
		usuarios.setIntentosClave(0);
		
		usuarioDao.update(usuarios);
		
		final String username = "identisa@gmail.com";
		final String password = "identisa.";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
	    String from = "identisa@gmail.com";
	    
		Session session = Session.getInstance(props, 
					new javax.mail.Authenticator() { 
							protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
				  });
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(usuarios.getMail()));
			message.setSubject("Clave temporal");
			message.setText("Sr. Usuario "+usuarios.getNombre() +" "+ usuarios.getApellidoPaterno() +" "+ usuarios.getApellidoMaterno()+": para poder acceder al sistema SAB usted debe utilizar la siguiente clave provisoria: "+clave+". Esta clave tiene duración de 6 horas a partir del acceso al sistema. Una vez haya ingresado, proceda a cambiarla pulsando el ícono que aparece junto a su nombre en la parte superior de la pantalla. Una vez desplegada la ventana, seleccione la opción Cambiar Clave");
			Transport.send(message);
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}
		mantenimientoUsuarioBloqueadoForm.setTermino(true);
	}

}
