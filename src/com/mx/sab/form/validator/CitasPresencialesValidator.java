package com.mx.sab.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.CitasPresencialesForm;

public class CitasPresencialesValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CitasPresencialesForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		CitasPresencialesForm citasPresencialesForm = (CitasPresencialesForm) obj;
		
		if(citasPresencialesForm.getAutocompleteVos() == null){
			errors.rejectValue("error", "citasPresenciales.prestaciones.vacio");
		}else{
			if(citasPresencialesForm.getAutocompleteVos().size() == 0){
				errors.rejectValue("error", "citasPresenciales.prestaciones.vacio");
			}
		}
		
		if (citasPresencialesForm.getIdMedico() == 0) {
			errors.rejectValue("error", "citasPresenciales.medico.vacio");
		}
	}
}
