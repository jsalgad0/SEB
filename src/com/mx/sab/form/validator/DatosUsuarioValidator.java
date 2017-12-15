package com.mx.sab.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.DatosUsuarioForm;

public class DatosUsuarioValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = DatosUsuarioForm.class.equals(clazz);
		return flag;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("Entra a valida");
		DatosUsuarioForm datosUsuarioForm = (DatosUsuarioForm) obj;
		
		if(datosUsuarioForm.getIdPreguntaAntigua() == -1){
			errors.rejectValue("idPreguntaAntigua", "datosUsuario.idPreguntaAntigua.vacio");
		}
		
		if(datosUsuarioForm.getRespuestaAntigua().trim().length() == 0){
			errors.rejectValue("respuestaAntigua", "datosUsuario.respuestaAntigua.vacio");
		}
		
		if(datosUsuarioForm.getIdPregunta() == -1){
			errors.rejectValue("idPregunta", "datosUsuario.idPregunta.vacio");
		}
		
		if(datosUsuarioForm.getRespuesta().trim().length() == 0){
			errors.rejectValue("respuesta", "datosUsuario.respuesta.vacio");
		}
		
		if(datosUsuarioForm.getClave().trim().length() == 0){
			errors.rejectValue("clave", "datosUsuario.clave.vacio");
		}
	}
}
