package com.mx.sab.form.validator;

import lombok.extern.log4j.Log4j2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.ContrareferenciaForm;

@Log4j2
public class ContrareferenciaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ContrareferenciaForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		ContrareferenciaForm contrareferenciaForm = (ContrareferenciaForm) obj;
		
		if(contrareferenciaForm.getFecha().trim().length()==0){
			errors.rejectValue("error", "contrareferencia.fecha.vacio");
		}
		
		if(contrareferenciaForm.getContrareferencia().getMotivoContrareferencia().trim().length()==0){
			errors.rejectValue("error", "contrareferencia.motivo.vacio");
		}
		
		if(contrareferenciaForm.getContrareferencia().getNumInterConsultas()==null){
			errors.rejectValue("error", "contrareferencia.numInterConsultas.vacio");
		}
		
		if(contrareferenciaForm.getContrareferencia().getNumConsultas()==null){
			errors.rejectValue("error", "contrareferencia.numConsultas.vacio");
		}
		
		if(contrareferenciaForm.getIdDiagnostico() == 0){
			errors.rejectValue("error", "contrareferencia.diagnostico.vacio");
		}
		
		if(contrareferenciaForm.getContrareferencia().getResultadosValoracion().trim().length()==0){
			errors.rejectValue("error", "contrareferencia.resultado.vacio");
		}
		
		if(contrareferenciaForm.getContrareferencia().getIndicaciones().trim().length()==0){
			errors.rejectValue("error", "contrareferencia.indicaciones.vacio");
		}

	}
}
