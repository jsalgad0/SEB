package com.mx.sab.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.AseguradorForm;
import com.mx.sab.form.PrestadorForm;
import com.mx.sab.util.ValidatorsUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AseguradorValidator implements Validator {
		
	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = AseguradorForm.class.equals(clazz);
		return flag;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		AseguradorForm aseguradorForm = (AseguradorForm) obj;
		
		if (aseguradorForm.getAsegurador().trim().length()==0) {
			errors.rejectValue("asegurador", "asegurador.descripcion.vacio");	
		}
		
		if (aseguradorForm.getIdEstado() == -1) {
			errors.rejectValue("idEstado", "asegurador.estado.vacio");	
		}			

		if (aseguradorForm.getIdMunicipio() == -1) {
			errors.rejectValue("idMunicipio", "asegurador.municipio.vacio");	
		}			
		
		if (aseguradorForm.getIdColonia() == -1) {
			errors.rejectValue("idColonia", "asegurador.colonia.vacio");	
		}	
		
		if (aseguradorForm.getCalle().trim().length()==0) {
			errors.rejectValue("calle", "asegurador.calle.vacio");	
		}
		
		if (aseguradorForm.getNumeroExterno().trim().length()==0) {
			errors.rejectValue("numeroExterno", "asegurador.numeroExterno.vacio");	
		}
		
		if (aseguradorForm.getNumeroInterno().trim().length()==0) {
			errors.rejectValue("numeroInterno", "asegurador.numeroInterno.vacio");	
		}
		
		if (aseguradorForm.getCp().trim().length()==0) {
			errors.rejectValue("cp", "asegurador.cp.vacio");	
		}else {
			if (!ValidatorsUtil.validaCP(aseguradorForm.getCp())) {
				errors.rejectValue("cp", "asegurador.cp.formato");	
			}	
		}
		
		if (aseguradorForm.getNombre().trim().length()==0) {
			errors.rejectValue("nombre", "asegurador.nombre.vacio");	
		}
		
		if (aseguradorForm.getTelefono1().trim().length()==0) {
			errors.rejectValue("telefono1", "asegurador.telefono.vacio");	
		}else {
			if (!ValidatorsUtil.validaCelular(aseguradorForm.getTelefono1())) {
				errors.rejectValue("telefono1", "asegurador.telefono.formato");	
			}	
		}
		
		if (aseguradorForm.getTelefono2().trim().length()!=0) {
			if (!ValidatorsUtil.validaCelular(aseguradorForm.getTelefono2())) {
				errors.rejectValue("telefono2", "asegurador.telefono.formato");	
			}	
		}
		
		if (aseguradorForm.getCorreo().trim().length()==0) {
			errors.rejectValue("correo", "asegurador.correo.vacio");
		}else {
			if (!ValidatorsUtil.validaCorreo(aseguradorForm.getCorreo())) {
				errors.rejectValue("correo", "asegurador.correo.formato");	
			}
		}
		
		if (aseguradorForm.getRfc().trim().length()==0) {
			errors.rejectValue("rfc", "asegurador.rfc.vacio");
		}else {
			if (!ValidatorsUtil.validaRFC(aseguradorForm.getRfc())) {
				errors.rejectValue("rfc", "asegurador.rfc.formato");	
			}
		}	
			
	/*	if (aseguradorForm.getFile().getSize() != 0 ) {
			System.out.println(aseguradorForm.getFile().getOriginalFilename());
			String extension = aseguradorForm.getFile().getOriginalFilename().substring(aseguradorForm.getFile().getOriginalFilename().lastIndexOf(".")+1, aseguradorForm.getFile().getOriginalFilename().length());
			if(extension.toLowerCase().equals("xls")){
				if(aseguradorForm.getFile().getSize() < 26000){
					errors.rejectValue("file", "asegurador.archivo.tamanio");
				}
			}else{
				errors.rejectValue("file", "asegurador.archivo.formato");
			}

		}else{
			errors.rejectValue("file", "asegurador.archivo.vacio");
		}
		*/
		
		
	}

}
