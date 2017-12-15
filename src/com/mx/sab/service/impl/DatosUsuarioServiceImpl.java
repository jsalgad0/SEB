package com.mx.sab.service.impl;

import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.ILoginDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.enums.CatEstatusUsuarioEnum;
import com.mx.sab.form.DatosUsuarioForm;
import com.mx.sab.model.Usuarioespecialidades;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.IDatosUsuarioService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.util.ValidatorsUtil;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class DatosUsuarioServiceImpl implements IDatosUsuarioService {
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private ILoginDao loginDao;
	
	@Override
	public void verificarUsuario(DatosUsuarioForm datosUsuarioForm, UserInfo userInfo) {
		Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = userInfo.getUsuarios().getUsuarioespecialidadeses().iterator();
		String especialidades = "";
		while (usuarioEspecialidadesIterator.hasNext()) {
			Usuarioespecialidades usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
			especialidades = especialidades + usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedica() +",";
		}
		if(CatEstatusUsuarioEnum.AUTORIZADO_CON_CLAVE.getId() == userInfo.getUsuarios().getCatestatususuario().getId()){
			datosUsuarioForm.setMostrarBotones(true);
		}
		
		if (especialidades.length()>0) {
			especialidades = especialidades.substring(0, especialidades.length()-1);
		}
		datosUsuarioForm.setEspecialidades(especialidades);
	}

	@Override
	public void actualizarDatosUsuarioClave(DatosUsuarioForm datosUsuarioForm,UserInfo userInfo) {
		boolean error = false;
		if (datosUsuarioForm.getClave().trim().length()==0) {
			datosUsuarioForm.setErrorClave("Ingrese la clave");
			error = true;
		}
		
		if (datosUsuarioForm.getNuevaClave().trim().length()==0) {
			datosUsuarioForm.setErrorNuevaClave("Ingrese la nueva clave");
			error = true;
		}
		
		if (datosUsuarioForm.getNuevaClaveConfirmacion().trim().length()==0) {
			datosUsuarioForm.setErrorNuevaClaveConfirmacion("Ingrese la confirmacion de clave");
			error = true;
		}
		
		
		if (error == false) {
			if (datosUsuarioForm.getNuevaClave().equals(datosUsuarioForm.getNuevaClaveConfirmacion())) {
				Usuarios usuarios = usuarioDao.getUsuarioById(userInfo.getUsuarios().getUsuarioId());
				String clave = FormatUtil.getMd5(datosUsuarioForm.getClave());
				if (clave.equals(usuarios.getPsswd())) {
					if(ValidatorsUtil.validaNumeros(datosUsuarioForm.getNuevaClave())){
						if(ValidatorsUtil.validaLetraMayuscula(datosUsuarioForm.getNuevaClave())){
							if(ValidatorsUtil.validaLetraMinuscula(datosUsuarioForm.getNuevaClave())){
								if(ValidatorsUtil.validaLetrasRfc(datosUsuarioForm.getNuevaClave(),usuarios.getRfc())){
									if(ValidatorsUtil.validaNumerosRfc(datosUsuarioForm.getNuevaClave(),usuarios.getRfc())){
										usuarios.setPsswd(FormatUtil.getMd5(datosUsuarioForm.getNuevaClave()));
										usuarioDao.update(usuarios);
										datosUsuarioForm.setExito("Contraseña actualizada");
									}else{
										datosUsuarioForm.setErrorNuevaClave("La Contraseña no debe de llevar los números del RFC");
									}
								}else{
									datosUsuarioForm.setErrorNuevaClave("La Contraseña no debe de llevar las letras del RFC");
								}
							}else{
								datosUsuarioForm.setErrorNuevaClave("La Contraseña debe de llevar una letra minuscula");
							}
						}else{
							datosUsuarioForm.setErrorNuevaClave("La Contraseña debe de llevar una letra mayuscula");
						}
					}else{
						datosUsuarioForm.setErrorNuevaClave("La Contraseña debe de llevar un número");
					}	
				}else{
					datosUsuarioForm.setErrorClave("Contraseña incorrecta");
				}
			}else{
				datosUsuarioForm.setErrorNuevaClaveConfirmacion("Verifique que la Contraseña sea la misma");	
			}
		}
		
	}

	@Override
	public void actualizarDatosUsuarioPregunta(DatosUsuarioForm datosUsuarioForm, UserInfo userInfo) {
		Usuarios usuarios = usuarioDao.getUsuarioById(userInfo.getUsuarios().getUsuarioId());
		if (usuarios.getCatpreguntasecreta().getId() == datosUsuarioForm.getIdPreguntaAntigua()) {
			if (usuarios.getRespuestaSecreta().equals(datosUsuarioForm.getRespuestaAntigua())) {
				if (usuarios.getPsswd().equals(FormatUtil.getMd5(datosUsuarioForm.getClave()))) {
					usuarios.setCatpreguntasecreta(loginDao.getPreguntaById(datosUsuarioForm.getIdPregunta()));
					usuarios.setRespuestaSecreta(datosUsuarioForm.getRespuesta());
					usuarioDao.update(usuarios);
					datosUsuarioForm.setExito("Datos actualizados");
				}else{
					datosUsuarioForm.setErrorClave("Contraseña incorrecta");
				}
			}else{
				datosUsuarioForm.setErrorRespuestaAntigua("Respuesta incorrecta");
			}
		}else{
			datosUsuarioForm.setErrorIdPreguntaAntigua("Pregunta incorrecta");
		}
	}

}
