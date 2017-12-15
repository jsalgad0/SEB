package com.mx.sab.form.validator;

import lombok.extern.log4j.Log4j2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.LugarAtencionForm;
import com.mx.sab.form.PrestadorForm;
import com.mx.sab.util.ValidatorsUtil;

@Log4j2
public class PrestadorValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PrestadorForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		//log.info("Entra a validar");
		PrestadorForm prestadorForm = (PrestadorForm) obj;
		
		if (prestadorForm.getPrestador().trim().length()==0) {
			errors.rejectValue("prestador", "prestador.descripcion.vacio");	
		}
		
		if (prestadorForm.getIdEstado() == -1) {
			errors.rejectValue("idEstado", "prestador.estado.vacio");	
		}			

		if (prestadorForm.getIdMunicipio() == -1) {
			errors.rejectValue("idMunicipio", "prestador.municipio.vacio");	
		}			
		
		if (prestadorForm.getIdColonia() == -1) {
			errors.rejectValue("idColonia", "prestador.colonia.vacio");	
		}	
		
		if (prestadorForm.getCalle().trim().length()==0) {
			errors.rejectValue("calle", "prestador.calle.vacio");	
		}
		
		if (prestadorForm.getNumeroExterno().trim().length()==0) {
			errors.rejectValue("numeroExterno", "prestador.numeroExterno.vacio");	
		}
		
		if (prestadorForm.getNumeroInterno().trim().length()==0) {
			errors.rejectValue("numeroInterno", "prestador.numeroInterno.vacio");	
		}
		
		if (prestadorForm.getCp().trim().length()==0) {
			errors.rejectValue("cp", "prestador.cp.vacio");	
		}else {
			if (!ValidatorsUtil.validaCP(prestadorForm.getCp())) {
				errors.rejectValue("cp", "prestador.cp.formato");	
			}	
		}
		
		if (prestadorForm.getNombre().trim().length()==0) {
			errors.rejectValue("nombre", "prestador.nombre.vacio");	
		}
		
		if (prestadorForm.getTelefono1().trim().length()==0) {
			errors.rejectValue("telefono1", "prestador.telefono.vacio");	
		}else {
			if (!ValidatorsUtil.validaCelular(prestadorForm.getTelefono1())) {
				errors.rejectValue("telefono1", "prestador.telefono.formato");	
			}	
		}
		
		if (prestadorForm.getTelefono2().trim().length()!=0) {
			if (!ValidatorsUtil.validaCelular(prestadorForm.getTelefono2())) {
				errors.rejectValue("telefono2", "prestador.telefono.formato");	
			}	
		}
		
		if (prestadorForm.getCorreo().trim().length()==0) {
			errors.rejectValue("correo", "prestador.correo.vacio");
		}else {
			if (!ValidatorsUtil.validaCorreo(prestadorForm.getCorreo())) {
				errors.rejectValue("correo", "prestador.correo.formato");	
			}
		}
		
		if (prestadorForm.getRfc().trim().length()==0) {
			errors.rejectValue("rfc", "prestador.rfc.vacio");
		}else {
			if (!ValidatorsUtil.validaRFC(prestadorForm.getRfc())) {
				errors.rejectValue("rfc", "prestador.rfc.formato");	
			}
		}		
		
	}

}
