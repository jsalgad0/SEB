package com.mx.sab.form.validator;

import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.PersonaConfianzaForm;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.util.ValidatorsUtil;

@Log4j2
public class PersonaConfianzaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PersonaConfianzaForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		PersonaConfianzaForm personaConfianzaForm = (PersonaConfianzaForm) obj;
		if (personaConfianzaForm.getIdTipoIdentificador() == -1) {
			errors.rejectValue("idTipoIdentificador", "personaConfianza.tipoIdentificador.vacio");	
		}	
		
		if (personaConfianzaForm.getIdTipoIdentificador() == 1) {
			if (personaConfianzaForm.getPersonasdeconfianza().getValorTipoIdentificador().trim().length()==0) {
				errors.rejectValue("personasdeconfianza.valorTipoIdentificador", "personaConfianza.identificador.vacio");	
			}else {
				if (!ValidatorsUtil.validaRFC(personaConfianzaForm.getPersonasdeconfianza().getValorTipoIdentificador())) {
					errors.rejectValue("personasdeconfianza.valorTipoIdentificador", "personaConfianza.identificador.formato");	
				}	
			}	
		}else if (personaConfianzaForm.getIdTipoIdentificador() == 2) {
			if (personaConfianzaForm.getPersonasdeconfianza().getValorTipoIdentificador().trim().length()==0) {
				errors.rejectValue("personasdeconfianza.valorTipoIdentificador", "personaConfianza.identificador.vacio");	
			}else {
				if (!ValidatorsUtil.validaCURP(personaConfianzaForm.getPersonasdeconfianza().getValorTipoIdentificador())) {
					errors.rejectValue("personasdeconfianza.valorTipoIdentificador", "personaConfianza.identificador.formato");	
				}	
			}	
		}else if (personaConfianzaForm.getIdTipoIdentificador() == 3) {
			if (personaConfianzaForm.getPersonasdeconfianza().getValorTipoIdentificador().trim().length()==0) {
				errors.rejectValue("personasdeconfianza.valorTipoIdentificador", "personaConfianza.identificador.vacio");	
			}else {
				if (!ValidatorsUtil.validaIFE(personaConfianzaForm.getPersonasdeconfianza().getValorTipoIdentificador())) {
					errors.rejectValue("personasdeconfianza.valorTipoIdentificador", "personaConfianza.identificador.formato");	
				}	
			}	
		}			
		
		if (personaConfianzaForm.getPersonasdeconfianza().getNombre().trim().length()==0) {
			errors.rejectValue("personasdeconfianza.nombre", "personaConfianza.nombre.vacio");	
		}
		
		if (personaConfianzaForm.getPersonasdeconfianza().getApellidoPaterno().trim().length()==0) {
			errors.rejectValue("personasdeconfianza.apellidoPaterno", "personaConfianza.apellidoP.vacio");	
		}
		
		if (personaConfianzaForm.getFechaNacimiento().trim().length()==0){
			errors.rejectValue("fechaNacimiento", "personaConfianza.fechaNacimiento.vacio");
		}else{
			Date date = FormatUtil.getDate(personaConfianzaForm.getFechaNacimiento());
			if(FormatUtil.getEdad(date.getTime()) < 18){
				errors.rejectValue("fechaNacimiento", "personaConfianza.fechaNacimiento.menorEdad");	
			}
		}
		
	}
}
