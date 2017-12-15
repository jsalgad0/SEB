package com.mx.sab.form.validator;

import lombok.extern.log4j.Log4j2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.LugarAtencionForm;
import com.mx.sab.util.ValidatorsUtil;

@Log4j2
public class LugarAtencionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LugarAtencionForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		LugarAtencionForm lugarAtencionForm = (LugarAtencionForm) obj;
		
		if (lugarAtencionForm.getLugarAtencion().trim().length()==0) {
			errors.rejectValue("lugarAtencion", "lugarAtencion.descripcion.vacio");	
		}
		
		if (lugarAtencionForm.getIdEstado() == -1) {
			errors.rejectValue("idEstado", "lugarAtencion.estado.vacio");	
		}			

		if (lugarAtencionForm.getIdMunicipio() == -1) {
			errors.rejectValue("idMunicipio", "lugarAtencion.municipio.vacio");	
		}			
		
		if (lugarAtencionForm.getIdColonia() == -1) {
			errors.rejectValue("idColonia", "lugarAtencion.colonia.vacio");	
		}	
		
		if (lugarAtencionForm.getCalle().trim().length()==0) {
			errors.rejectValue("calle", "lugarAtencion.direccion.vacio");	
		}
		
		if (lugarAtencionForm.getNumeroExterno().trim().length()==0) {
			errors.rejectValue("numeroExterno", "lugarAtencion.numeroExterno.vacio");	
		}
		
		if (lugarAtencionForm.getNumeroInterno().trim().length()==0) {
			errors.rejectValue("numeroInterno", "lugarAtencion.numeroInterno.vacio");	
		}
		
		if (lugarAtencionForm.getCp().trim().length()==0) {
			errors.rejectValue("cp", "lugarAtencion.cp.vacio");	
		}else {
			if (!ValidatorsUtil.validaCP(lugarAtencionForm.getCp())) {
				errors.rejectValue("cp", "lugarAtencion.cp.formato");	
			}	
		}
		
		if (lugarAtencionForm.getNombre().trim().length()==0) {
			errors.rejectValue("nombre", "lugarAtencion.nombre.vacio");	
		}
		
		if (lugarAtencionForm.getTelefono1().trim().length()==0) {
			errors.rejectValue("telefono1", "lugarAtencion.telefono.vacio");	
		}else {
			if (!ValidatorsUtil.validaCelular(lugarAtencionForm.getTelefono1())) {
				errors.rejectValue("telefono1", "lugarAtencion.telefono.formato");	
			}	
		}
		
		if (lugarAtencionForm.getTelefono2().trim().length()!=0) {
			if (!ValidatorsUtil.validaCelular(lugarAtencionForm.getTelefono2())) {
				errors.rejectValue("telefono2", "lugarAtencion.telefono.formato");	
			}	
		}
		
		if (lugarAtencionForm.getCorreoLugarAtencion().trim().length()==0) {
			errors.rejectValue("correoLugarAtencion", "lugarAtencion.correo.vacio");
		}else {
			if (!ValidatorsUtil.validaCorreo(lugarAtencionForm.getCorreoLugarAtencion())) {
				errors.rejectValue("correoLugarAtencion", "lugarAtencion.correo.formato");	
			}
		}
		
	}
}
