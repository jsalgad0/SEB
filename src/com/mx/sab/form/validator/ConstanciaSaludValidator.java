package com.mx.sab.form.validator;

import lombok.extern.log4j.Log4j2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.ConstanciaSaludForm;

@Log4j2
public class ConstanciaSaludValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ConstanciaSaludForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		ConstanciaSaludForm constanciaSaludForm = (ConstanciaSaludForm) obj;
		if (constanciaSaludForm.getDescripcion().trim().length()==0) {
			errors.rejectValue("error", "constanciaSalud.descripcion.vacio");
		}
		
		if (constanciaSaludForm.getMedico().trim().length()==0) {
			errors.rejectValue("error", "constanciaSalud.medico.vacio");
		}
	}
}