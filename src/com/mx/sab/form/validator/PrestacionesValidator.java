package com.mx.sab.form.validator;

import lombok.extern.log4j.Log4j2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.PrestacionesForm;

@Log4j2
public class PrestacionesValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return PrestacionesForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		PrestacionesForm prestacionesForm = (PrestacionesForm) obj;
		
		if (prestacionesForm.getFile().getSize() == 0) {
			errors.rejectValue("error", "error.prestacion.file");
		}else {
			System.out.println(prestacionesForm.getFile().getOriginalFilename());
			String extension = prestacionesForm.getFile().getOriginalFilename().substring(prestacionesForm.getFile().getOriginalFilename().lastIndexOf(".")+1, prestacionesForm.getFile().getOriginalFilename().length());
			if(!extension.toLowerCase().equals("xls")){
				errors.rejectValue("error", "error.prestacion.file.xls");	
			}
		}
		
		if (prestacionesForm.getCargaPrestaciones().equals("A")) {
			if(prestacionesForm.getIdAseguradores() == -1){
				errors.rejectValue("error", "error.prestacion.asegurador");
			}	
		}else {
			if(prestacionesForm.getIdPrestadores() == -1){
				errors.rejectValue("error", "error.prestacion.prestador");
			}	
		}
		
	}
}
