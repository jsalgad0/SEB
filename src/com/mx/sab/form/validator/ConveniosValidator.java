package com.mx.sab.form.validator;

import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.ConveniosForm;
import com.mx.sab.util.FormatUtil;

@Log4j2
public class ConveniosValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ConveniosForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//log.info("Entra a validar");
		ConveniosForm conveniosForm = (ConveniosForm) obj;
		
		if (conveniosForm.getCodigoLugarAtencion().trim().length()==0) {
			errors.rejectValue("codigoLugarAtencion", "convenio.codigoLugarAtencion.vacio");	
		}
		
		if (conveniosForm.getLugarAtencion().trim().length()==0) {
			errors.rejectValue("lugarAtencion", "convenio.lugarAtencion.vacio");
		}
		
		if (conveniosForm.getIdAsegurador()==-1) {
			errors.rejectValue("idAsegurador", "convenio.asegurador.vacio");	
		}	
		
		if (conveniosForm.getIdPrestador()==-1) {
			errors.rejectValue("idPrestador", "convenio.prestadorRfc.vacio");	
		}	
		
		if (conveniosForm.getConvenio().trim().length()==0) {
			errors.rejectValue("convenio", "convenio.convenio.vacio");	
		}
		
		if (conveniosForm.getPrestador().trim().length()==0) {
			errors.rejectValue("prestador", "convenio.prestador.vacio");	
		}
		
		boolean error = false;
		
		if (conveniosForm.getVigenteDesde().trim().length()==0) {
			errors.rejectValue("vigenteDesde", "convenio.vigenteDesde.vacio");
			error = true;
		}
		
		if (conveniosForm.getVigenteHasta().trim().length()==0) {
			errors.rejectValue("vigenteHasta", "convenio.vigenteHasta.vacio");
			error = true;
		}
		
		if (!error) {
			String vigenteDesde = conveniosForm.getVigenteDesde();
			Date dateDesde = FormatUtil.getDate(vigenteDesde);
			String vigenteHasta = conveniosForm.getVigenteHasta();
			Date dateHasta = FormatUtil.getDate(vigenteHasta);
			Date dateActual = new Date();
			
//			if (dateDesde.before(dateActual)) {
//				errors.rejectValue("vigenteDesde", "convenio.vigenteDesde.actual");
//			}
//			
//			if (dateHasta.before(dateActual)) {
//				errors.rejectValue("vigenteDesde", "convenio.vigenteHasta.actual");
//			}
			
			if (dateDesde.after(dateHasta)) {
				errors.rejectValue("vigenteDesde", "convenio.vigencia.error");
			}	
		}
		
//		if (conveniosForm.getOpcionCuadroPrestaciones().equals("E")) {
//			if (conveniosForm.getIdCuadroPrestacion()==-1) {
//				errors.rejectValue("error", "convenio.cuadroPrestacion.vacio");	
//			}	
//		}else if (conveniosForm.getOpcionCuadroPrestaciones().equals("C")) {
//			if (conveniosForm.getFile().getSize() == 0) {
//				errors.rejectValue("error", "error.cuadroPrestacion.file");
//			}else {
//				System.out.println(conveniosForm.getFile().getOriginalFilename());
//				String extension = conveniosForm.getFile().getOriginalFilename().substring(conveniosForm.getFile().getOriginalFilename().lastIndexOf(".")+1, conveniosForm.getFile().getOriginalFilename().length());
//				if(!extension.toLowerCase().equals("xls")){
//					errors.rejectValue("error", "error.cuadroPrestacion.file.xls");	
//				}
//			}
//			
//			if(conveniosForm.getCuadroprestaciones().getCuadroPrestacion().trim().length() == 0){
//				errors.rejectValue("error", "error.cuadroPrestacion.nombre");
//			}	
//		}else {
//			if(conveniosForm.getNombrecuadroPrestacion().trim().length() == 0){
//				errors.rejectValue("error", "error.cuadroPrestacion.nombre");
//			}	
//		}
		
	}
}
