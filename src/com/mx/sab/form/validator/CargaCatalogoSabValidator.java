package com.mx.sab.form.validator;

import lombok.extern.log4j.Log4j2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.CargaCatalogoSabForm;

@Log4j2
public class CargaCatalogoSabValidator implements Validator {
		
	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = CargaCatalogoSabForm.class.equals(clazz);
		return flag;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		CargaCatalogoSabForm cargaCatalogoSabForm = (CargaCatalogoSabForm) obj;
		
	if (cargaCatalogoSabForm.getFile().getSize() != 0 ) {
			System.out.println(cargaCatalogoSabForm.getFile().getOriginalFilename());
			String extension = cargaCatalogoSabForm.getFile().getOriginalFilename().substring(cargaCatalogoSabForm.getFile().getOriginalFilename().lastIndexOf(".")+1, cargaCatalogoSabForm.getFile().getOriginalFilename().length());
			if(extension.toLowerCase().equals("xls")){
				if(cargaCatalogoSabForm.getFile().getSize() < 26000){
					errors.rejectValue("file", "asegurador.archivo.tamanio");
				}
			}else{
				errors.rejectValue("file", "asegurador.archivo.formato");
			}

		}else{
			errors.rejectValue("file", "asegurador.archivo.vacio");
		}
	}

}
