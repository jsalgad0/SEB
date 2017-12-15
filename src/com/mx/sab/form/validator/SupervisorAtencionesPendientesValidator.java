package com.mx.sab.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.SupervisorAtencionesPendientesForm;

public class SupervisorAtencionesPendientesValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = SupervisorAtencionesPendientesForm.class.equals(clazz);
		return flag;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("Entra a valida");
		SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm = (SupervisorAtencionesPendientesForm) obj;
		
		if(supervisorAtencionesPendientesForm.getIdCausa() == -1){
			errors.rejectValue("idCausa", "atencionesPendientes.causa.vacio");
		}
		
		if(supervisorAtencionesPendientesForm.getIdTipoAutorizacion() == 1){
			if(supervisorAtencionesPendientesForm.getFechaFin().trim().length() == 0){
				errors.rejectValue("fechaFin", "atencionesPendientes.fechaFin.vacio");
			}
		}		
	}

}