package com.mx.sab.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.InformacionPacienteForm;

public class InformacionPacienteValidator implements Validator {
		
	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = InformacionPacienteForm.class.equals(clazz);
		return flag;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("Entra a valida");
		InformacionPacienteForm informacionPacienteForm = (InformacionPacienteForm) obj;
		if(informacionPacienteForm.getIdEstado()<0){
			errors.rejectValue("error", "informacion.paciente.estado.vacio");			
		}
	}

}
