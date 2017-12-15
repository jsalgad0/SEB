package com.mx.sab.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.InformacionPacienteForm;
import com.mx.sab.form.LicenciaMedicaForm;

public class LicenciaMedicaValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = LicenciaMedicaForm.class.equals(clazz);
		return flag;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("Entra a valida");
		LicenciaMedicaForm licenciaMedicaForm = (LicenciaMedicaForm) obj;
		
		if (licenciaMedicaForm.getDiasLetra().trim().length()==0) {
			errors.rejectValue("error", "licenciaMedica.fechas.vacio");
		}
	}
}
