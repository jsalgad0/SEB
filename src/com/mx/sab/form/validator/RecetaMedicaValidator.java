package com.mx.sab.form.validator;

import lombok.extern.log4j.Log4j2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.RecetaMedicaForm;

@Log4j2
public class RecetaMedicaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RecetaMedicaForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		RecetaMedicaForm recetaMedicaForm = (RecetaMedicaForm) obj;
		
		if (recetaMedicaForm.getIdViaDeAdmonMedicamento()==-1) {
			errors.rejectValue("error", "recetaMedica.viaDeAdmonMedicamento.vacio");	
		}
		
		if (recetaMedicaForm.getIdUnidadTiempoFrecuencia()==-1) {
			errors.rejectValue("error", "recetaMedica.unidadTiempoFrecuencia.vacio");	
		}
		
		if (recetaMedicaForm.getIdUnidadTiempoDuracion()==-1) {
			errors.rejectValue("error", "recetaMedica.unidadTiempoDuracion.vacio");	
		}		
		
		if (recetaMedicaForm.getIdMedicamento()==0) {
			errors.rejectValue("error", "recetaMedica.medicamento.vacio");	
		}	

		if (recetaMedicaForm.getMedicamentosreceta().getCantidadUnidades()==null) {
			errors.rejectValue("error", "recetaMedica.cantidadUnidades.vacio");	
		}
		
		if (recetaMedicaForm.getMedicamentosreceta().getUnidad().trim().length()==0) {
			errors.rejectValue("error", "recetaMedica.unidad.vacio");
		}
		
		if (recetaMedicaForm.getMedicamentosreceta().getFrecuenciaDeToma()==null) {
			errors.rejectValue("error", "recetaMedica.frecuenciaDeToma.vacio");	
		}
		
		if (recetaMedicaForm.getMedicamentosreceta().getDuracionDeToma()==null) {
			errors.rejectValue("error", "recetaMedica.duracionDeToma.vacio");	
		}
		
		if (recetaMedicaForm.getMedicamentosreceta().getObservaciones().trim().length()==0) {
			errors.rejectValue("error", "recetaMedica.observaciones.vacio");	
		}
	}
}
