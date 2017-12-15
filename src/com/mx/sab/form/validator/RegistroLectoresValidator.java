package com.mx.sab.form.validator;

import lombok.extern.log4j.Log4j2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.RegistroLectoresForm;

@Log4j2
public class RegistroLectoresValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RegistroLectoresForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		RegistroLectoresForm registroLectoresForm = (RegistroLectoresForm) obj;
		
		if (registroLectoresForm.getNoDeSerie().trim().length()==0) {
			errors.rejectValue("noDeSerie", "registroLectores.noSerie.vacio");
		}
		
		if (registroLectoresForm.getCodigoLugarAtencion()==null) {
			errors.rejectValue("codigoLugarAtencion", "registroLectores.codigoLugarAtencion.vacio");
		}		
		
		if (registroLectoresForm.getLugarAtencion().trim().length()==0) {
			errors.rejectValue("lugarAtencion", "registroLectores.lugarAtencion.vacio");
		}
		
		if (registroLectoresForm.getRfcPropietarioLector().trim().length()==0) {
			errors.rejectValue("rfcPropietarioLector", "registroLectores.rfc.vacio");	
		}	
		
		if (registroLectoresForm.getPropietarioLector().trim().length()==0) {
			errors.rejectValue("propietarioLector", "registroLectores.propietarioLector.vacio");	
		}
	}
}
