package com.mx.sab.form.validator;

import lombok.extern.log4j.Log4j2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.CargaPrestacionesAseguradorForm;

@Log4j2
public class CargaArchivoPrestacionesAseguradorValidator implements Validator {
		
	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = CargaPrestacionesAseguradorForm.class.equals(clazz);
		return flag;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		CargaPrestacionesAseguradorForm cargaPrestacionesAseguradorForm = (CargaPrestacionesAseguradorForm) obj;
		
	if (cargaPrestacionesAseguradorForm.getFile().getSize() != 0 ) {
			System.out.println(cargaPrestacionesAseguradorForm.getFile().getOriginalFilename());
			String extension = cargaPrestacionesAseguradorForm.getFile().getOriginalFilename().substring(cargaPrestacionesAseguradorForm.getFile().getOriginalFilename().lastIndexOf(".")+1, cargaPrestacionesAseguradorForm.getFile().getOriginalFilename().length());
			if(extension.toLowerCase().equals("xls")){
				if(cargaPrestacionesAseguradorForm.getFile().getSize() < 26000){
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
