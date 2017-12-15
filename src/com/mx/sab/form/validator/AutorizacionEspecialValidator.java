package com.mx.sab.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.AutorizacionEspecialForm;
import com.mx.sab.util.FormatUtil;

public class AutorizacionEspecialValidator implements Validator {
		
	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = AutorizacionEspecialForm.class.equals(clazz);
		return flag;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("Entra a valida");
		AutorizacionEspecialForm autorizacionEspecialForm = (AutorizacionEspecialForm) obj;
		
		if (autorizacionEspecialForm.getIdTipoVigencia() == -1) {
			errors.rejectValue("error", "autorizacionEspecial.vigencia.vacio");	
		}	
		
		if (autorizacionEspecialForm.getIdCatCausa() == -1) {
			errors.rejectValue("error", "autorizacionEspecial.causa.vacio");	
		}	
		
		if (autorizacionEspecialForm.getIdTipoVigencia() == 1) {
			if (autorizacionEspecialForm.getFechaFin().trim().length()==0) {
				errors.rejectValue("error", "autorizacionEspecial.fechaFin.vacio");	
			}else{
				try{
					FormatUtil.getDate(autorizacionEspecialForm.getFechaFin().trim());
				}catch(Exception ex){
					errors.rejectValue("error", "autorizacionEspecial.fechaFin.formato");
					ex.printStackTrace();
				}
			}	
		}
		
		
	}
}
