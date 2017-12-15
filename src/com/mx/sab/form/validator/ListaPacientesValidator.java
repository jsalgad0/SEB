package com.mx.sab.form.validator;

import lombok.extern.log4j.Log4j2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.ListaPacientesForm;

@Log4j2
public class ListaPacientesValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ListaPacientesForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		ListaPacientesForm listaPacientesForm = (ListaPacientesForm) obj;
		if (listaPacientesForm.getNotamedica().getAntecedentes().trim().length()==0) {
			errors.rejectValue("error", "atencionMedica.antecedentes.vacio");
		}
		
		if (listaPacientesForm.getNotamedica().getSintomas().trim().length()==0) {
			errors.rejectValue("error", "atencionMedica.sintomas.vacio");
		}
		
		if (listaPacientesForm.getNotamedica().getObservaciones().trim().length()==0) {
			errors.rejectValue("error", "atencionMedica.observaciones.vacio");
		}		
		
		if (listaPacientesForm.getNotamedica().getPlanAseguir().trim().length()==0) {
			errors.rejectValue("error", "atencionMedica.planAseguir.vacio");
		}			
		
		if (listaPacientesForm.getIdDiagnostico()==0) {
			errors.rejectValue("error", "atencionMedica.diagnostico.vacio");	
		}
	}
}
