package com.mx.sab.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.mx.sab.form.SupervisorUsuariosPendientesForm;

public class SupervisorUsuariosPendientesValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = SupervisorUsuariosPendientesForm.class.equals(clazz);
		return flag;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("Entra a valida");
		SupervisorUsuariosPendientesForm supervisorUsuariosPendientesForm = (SupervisorUsuariosPendientesForm) obj;
		
		
		
		if(supervisorUsuariosPendientesForm.getIdCausa() == -1){
			errors.rejectValue("idCausa", "usuariosPendientes.causa.vacio");
		}
		
		if(supervisorUsuariosPendientesForm.getIdTipoAutorizacion() == 0){
			errors.rejectValue("idTipoAutorizacion", "usuariosPendientes.tipoAutorizacion.vacio");
		}
		
		if(supervisorUsuariosPendientesForm.getIdTipoAutorizacion() == 1){
			if(supervisorUsuariosPendientesForm.getFechaFin().trim().length() == 0){
				errors.rejectValue("fechaFin", "usuariosPendientes.fechaFin.vacio");
			}
		}		
	}

}