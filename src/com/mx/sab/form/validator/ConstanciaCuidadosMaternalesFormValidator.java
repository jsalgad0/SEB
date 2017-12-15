package com.mx.sab.form.validator;

import java.util.GregorianCalendar;

import lombok.extern.log4j.Log4j2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.ConstanciaCuidadosMaternalesForm;

@Log4j2
public class ConstanciaCuidadosMaternalesFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ConstanciaCuidadosMaternalesForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		ConstanciaCuidadosMaternalesForm constanciaCuidadosMaternalesForm = (ConstanciaCuidadosMaternalesForm) obj;
		
		boolean error = false;
		
		if (constanciaCuidadosMaternalesForm.getNombre().trim().length()==0) {
			errors.rejectValue("error", "constanciaCuidadosMaternalesForm.nombre.vacio");
			error = true;
		}

		if (constanciaCuidadosMaternalesForm.getCategoria().trim().length()==0) {
			errors.rejectValue("error", "constanciaCuidadosMaternalesForm.categoria.vacio");
			error = true;
		}

		if (constanciaCuidadosMaternalesForm.getClave().trim().length()==0) {
			errors.rejectValue("error", "constanciaCuidadosMaternalesForm.clave.vacio");
			error = true;
		}

		if (constanciaCuidadosMaternalesForm.getAdscripcion().trim().length()==0) {
			errors.rejectValue("error", "constanciaCuidadosMaternalesForm.adscripcion.vacio");
			error = true;
		}

		if (constanciaCuidadosMaternalesForm.getHorario().trim().length()==0) {
			errors.rejectValue("error", "constanciaCuidadosMaternalesForm.horario.vacio");
			error = true;
		}

		if (constanciaCuidadosMaternalesForm.getDiagnostico().trim().length()==0) {
			errors.rejectValue("error", "constanciaCuidadosMaternalesForm.diagnostico.vacio");
			error = true;
		}

		if (constanciaCuidadosMaternalesForm.getNombreClave().trim().length()==0) {
			errors.rejectValue("error", "constanciaCuidadosMaternalesForm.nombreClave.vacio");
			error = true;
		}

		if (constanciaCuidadosMaternalesForm.getDiaInicio().trim().length()==0) {
			errors.rejectValue("error", "constanciaCuidadosMaternalesForm.diaInicio.vacio");
			error = true;
		}

		if (constanciaCuidadosMaternalesForm.getMesInicio().trim().length()==0) {
			errors.rejectValue("error", "constanciaCuidadosMaternalesForm.mesInicio.vacio");
			error = true;
		}

		if (constanciaCuidadosMaternalesForm.getAnioInicio().trim().length()==0) {
			errors.rejectValue("error", "constanciaCuidadosMaternalesForm.anioInicio.vacio");
			error = true;
		}

		if (constanciaCuidadosMaternalesForm.getDiaFin().trim().length()==0) {
			errors.rejectValue("error", "constanciaCuidadosMaternalesForm.diaFin.vacio");
			error = true;
		}
		
		if (constanciaCuidadosMaternalesForm.getMesFin().trim().length()==0) {
			errors.rejectValue("error", "constanciaCuidadosMaternalesForm.mesFin.vacio");
			error = true;
		}		
		
		if (constanciaCuidadosMaternalesForm.getAnioFin().trim().length()==0) {
			errors.rejectValue("error", "constanciaCuidadosMaternalesForm.anioFin.vacio");
			error = true;
		}				
		
		if (!error) {
			try {
				GregorianCalendar calendarInicio = new GregorianCalendar(Integer.parseInt(constanciaCuidadosMaternalesForm.getAnioInicio()), Integer.parseInt(constanciaCuidadosMaternalesForm.getMesInicio()), Integer.parseInt(constanciaCuidadosMaternalesForm.getDiaInicio()));
				GregorianCalendar calendarFin = new GregorianCalendar(Integer.parseInt(constanciaCuidadosMaternalesForm.getAnioFin()), Integer.parseInt(constanciaCuidadosMaternalesForm.getMesFin()), Integer.parseInt(constanciaCuidadosMaternalesForm.getDiaFin()));
				long diff = calendarFin.getTimeInMillis() - calendarInicio.getTimeInMillis();
				long days = (24 * 60 * 60 * 1000);
				long diffdays = diff / days;
				diffdays++;
				if (!(diffdays<7)) {
					errors.rejectValue("error", "constanciaCuidadosMaternalesForm.fechas.diferencia");
				}
			} catch (Exception e) {
				errors.rejectValue("error", "constanciaCuidadosMaternalesForm.fechas.incorrectas");
			}
			
		}
	}
}