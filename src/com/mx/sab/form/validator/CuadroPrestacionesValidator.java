package com.mx.sab.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.CuadroPrestacionesForm;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CuadroPrestacionesValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return CuadroPrestacionesForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		CuadroPrestacionesForm cuadroPrestacionesForm = (CuadroPrestacionesForm) obj;
		
		if (cuadroPrestacionesForm.getFile().getSize() == 0) {
			errors.rejectValue("error", "error.cuadroPrestacion.file");
		}else {
			System.out.println(cuadroPrestacionesForm.getFile().getOriginalFilename());
			String extension = cuadroPrestacionesForm.getFile().getOriginalFilename().substring(cuadroPrestacionesForm.getFile().getOriginalFilename().lastIndexOf(".")+1, cuadroPrestacionesForm.getFile().getOriginalFilename().length());
			if(!extension.toLowerCase().equals("xls")){
				errors.rejectValue("error", "error.cuadroPrestacion.file.xls");	
			}
		}
		
		if(cuadroPrestacionesForm.getIdPrestadores() == -1){
			errors.rejectValue("error", "error.cuadroPrestacion.prestador");
		}	
		
		if(cuadroPrestacionesForm.getCuadroprestaciones().getCuadroPrestacion().trim().length() == 0){
			errors.rejectValue("error", "error.cuadroPrestacion.nombre");
		}	
		
	}
}
