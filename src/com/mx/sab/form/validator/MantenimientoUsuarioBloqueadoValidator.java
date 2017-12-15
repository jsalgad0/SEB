package com.mx.sab.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.enums.CatVigenciaEnum;
import com.mx.sab.form.MantenimientoUsuarioBloqueadoForm;

public class MantenimientoUsuarioBloqueadoValidator implements Validator {
		
	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = MantenimientoUsuarioBloqueadoForm.class.equals(clazz);
		return flag;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("Entra a valida");
		MantenimientoUsuarioBloqueadoForm mantenimientoUsuarioBloqueadoForm = (MantenimientoUsuarioBloqueadoForm) obj;
		
//		if(mantenimientoUsuarioBloqueadoForm.getVigencia() == CatVigenciaEnum.BLOQUEADO.getId()){
//			errors.rejectValue("vigencia", "mantenimientoUsuarioBloqueado.vigencia.bloqueado");
//		}
		
		if(mantenimientoUsuarioBloqueadoForm.getMail().trim().length() == 0){
			errors.rejectValue("mail", "mantenimientoUsuarioBloqueado.mail.vacio");
		}
	}
}
