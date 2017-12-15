package com.mx.sab.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.HistoriaClincaForm;


public class HistoriaClinicaValidator implements Validator {
		
	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = HistoriaClincaForm.class.equals(clazz);
		return flag;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("Entra a valida");
		HistoriaClincaForm historiaClincaForm = (HistoriaClincaForm) obj;
		System.out.println(historiaClincaForm);
	}

}
