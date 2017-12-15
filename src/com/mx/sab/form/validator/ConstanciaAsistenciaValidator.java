package com.mx.sab.form.validator;

import lombok.extern.log4j.Log4j2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.ConstanciaAsistenciaForm;
import com.mx.sab.form.ListaPacientesForm;

@Log4j2
public class ConstanciaAsistenciaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ConstanciaAsistenciaForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		ConstanciaAsistenciaForm constanciaAsistenciaForm = (ConstanciaAsistenciaForm) obj;
		if (constanciaAsistenciaForm.getDescripcion().trim().length()==0) {
			errors.rejectValue("error", "constanciaAsistencia.descripcion.vacio");
		}
		
		if (constanciaAsistenciaForm.getNombre().trim().length()==0) {
			errors.rejectValue("error", "constanciaAsistencia.nombre.vacio");
		}
		
		if (constanciaAsistenciaForm.getHoraInicio().trim().length()==0) {
			errors.rejectValue("error", "constanciaAsistencia.horaInicio.vacio");
		}
		
		if (constanciaAsistenciaForm.getHoraFin().trim().length()==0) {
			errors.rejectValue("error", "constanciaAsistencia.horaFin.vacio");
		}
	}
}
