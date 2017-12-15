package com.mx.sab.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.SignosVitalesForm;


public class SignosVitalesValidator implements Validator {
		
	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = SignosVitalesForm.class.equals(clazz);
		return flag;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("Entra a valida");
		SignosVitalesForm signosVitalesForm = (SignosVitalesForm) obj;
		
		
		if(signosVitalesForm.getPeso()==null || signosVitalesForm.getPeso().trim().length() == 0){
			errors.rejectValue("peso", "signos.peso.vacio");
		}else{
			try{
				Double.parseDouble(signosVitalesForm.getPeso());
			}catch(Exception e){
				errors.rejectValue("peso", "signos.peso.formato");
			}
		}
		
		if(signosVitalesForm.getAltura()==null || signosVitalesForm.getAltura().trim().length() == 0){
			errors.rejectValue("altura", "signos.altura.vacio");
		}else{
			try{
				Double.parseDouble(signosVitalesForm.getAltura());
			}catch(Exception e){
				errors.rejectValue("altura", "signos.altura.formato");
			}
		}
		
		if(signosVitalesForm.getTensionArterial()==null || signosVitalesForm.getTensionArterial().trim().length() == 0){
			errors.rejectValue("tensionArterial", "signos.ta.vacio");
		}
		
		if(signosVitalesForm.getTemperatura()==null || signosVitalesForm.getTemperatura().trim().length() == 0){
			errors.rejectValue("temperatura", "signos.temperatura.vacio");
		}else{
			try{
				Double.parseDouble(signosVitalesForm.getTemperatura());
			}catch(Exception e){
				errors.rejectValue("temperatura", "signos.temperatura.formato");
			}
		}
		
	}

}
