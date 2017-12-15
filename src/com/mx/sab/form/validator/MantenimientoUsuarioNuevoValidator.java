package com.mx.sab.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.enums.RolesEnum;
import com.mx.sab.form.MantenimientoUsuarioNuevoForm;
import com.mx.sab.util.ValidatorsUtil;

public class MantenimientoUsuarioNuevoValidator implements Validator {
		
	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = MantenimientoUsuarioNuevoForm.class.equals(clazz);
		return flag;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("Entra a valida");
		MantenimientoUsuarioNuevoForm mantenimientoUsuarioNuevoForm = (MantenimientoUsuarioNuevoForm) obj;
		
		if(mantenimientoUsuarioNuevoForm.getUsuarios().getNombre().trim().length() == 0){
			errors.rejectValue("usuarios.nombre", "mantenimientoUsuarioNuevo.nombre.vacio");
		}
		
		if(mantenimientoUsuarioNuevoForm.getUsuarios().getApellidoPaterno().trim().length() == 0){
			errors.rejectValue("usuarios.apellidoPaterno", "mantenimientoUsuarioNuevo.apellidoPaterno.vacio");
		}
		
		if(mantenimientoUsuarioNuevoForm.getUsuarios().getApellidoMaterno().trim().length() == 0){
			errors.rejectValue("usuarios.apellidoMaterno", "mantenimientoUsuarioNuevo.apellidoMaterno.vacio");
		}
		
		if(mantenimientoUsuarioNuevoForm.getFechaNacimiento().trim().length() == 0){
			errors.rejectValue("fechaNacimiento", "mantenimientoUsuarioNuevo.fechaNacimiento.vacio");
		}
		
		if (mantenimientoUsuarioNuevoForm.getRfc().trim().length()==0) {
			errors.rejectValue("rfc", "mantenimientoUsuarioNuevo.rfc.vacio");	
		}else {
			if (!ValidatorsUtil.validaRFC(mantenimientoUsuarioNuevoForm.getRfc())) {
				errors.rejectValue("rfc", "mantenimientoUsuarioNuevo.rfc.formato");	
			}	
		}
		
		if (mantenimientoUsuarioNuevoForm.getUsuarios().getCurp().trim().length()>0) {
			if (!ValidatorsUtil.validaCURP(mantenimientoUsuarioNuevoForm.getUsuarios().getCurp())) {
				errors.rejectValue("usuarios.curp", "mantenimientoUsuarioNuevo.curp.formato");	
			}	
		}
		
		boolean campoObligatorio = false;
		
		
		if (mantenimientoUsuarioNuevoForm.getUsuarios().getTelefono().trim().length()==0) {
			if (mantenimientoUsuarioNuevoForm.getUsuarios().getMail().trim().length()==0) {
				errors.rejectValue("usuarios.telefono", "mantenimientoUsuarioNuevo.telefono.vacio");		
			}
		}else {
			if (!ValidatorsUtil.validaCelular(mantenimientoUsuarioNuevoForm.getUsuarios().getTelefono())) {
				errors.rejectValue("usuarios.telefono", "mantenimientoUsuarioNuevo.telefono.formato");
				campoObligatorio = true;
			}else{
				campoObligatorio = true;
			}	
		}
		
		if (mantenimientoUsuarioNuevoForm.getUsuarios().getMail().trim().length()==0) {
			if (!campoObligatorio) {
				errors.rejectValue("usuarios.mail", "mantenimientoUsuarioNuevo.mail.vacio");	
			}
		}else {
			if (!ValidatorsUtil.validaCorreo(mantenimientoUsuarioNuevoForm.getUsuarios().getMail())) {
				errors.rejectValue("usuarios.mail", "mantenimientoUsuarioNuevo.mail.formato");	
			}	
		}
		
		boolean validarEspecialidad = false;
		
		if (mantenimientoUsuarioNuevoForm.getRoles().length==0) {
			errors.rejectValue("roles", "mantenimientoUsuarioNuevo.roles.vacio");
		}else{
			for (String elemento : mantenimientoUsuarioNuevoForm.getRoles()) {
				if (elemento.equals(""+RolesEnum.MEDICO.getId()) || elemento.equals(""+RolesEnum.DENTISTA.getId())) {
					validarEspecialidad = true;
				}
			}	
		}
		
		if (validarEspecialidad && mantenimientoUsuarioNuevoForm.isValidarEspecialidades()) {
			
			if(mantenimientoUsuarioNuevoForm.getUsuarios().getRegSs().trim().length() == 0){
				errors.rejectValue("usuarios.regSs", "mantenimientoUsuarioNuevo.regss.vacio");
			}
			
			boolean seleccionoUno = false;
			
			String[] cedulas = new String[3];
			
			if (mantenimientoUsuarioNuevoForm.getIdEspecialidad1()!=-1) {
				seleccionoUno = true;
				if(mantenimientoUsuarioNuevoForm.getCedulaProfesional1().trim().length() == 0){
					errors.rejectValue("cedulaProfesional1", "mantenimientoUsuarioNuevo.cedulaProfesional1.vacio");
				}else{
					cedulas[0] = mantenimientoUsuarioNuevoForm.getCedulaProfesional1();
				}	
				
				if (mantenimientoUsuarioNuevoForm.getIdInstitucion1().trim().length() == 0) {
					errors.rejectValue("idInstitucion1", "mantenimientoUsuarioNuevo.idInstitucion1.vacio");
				}	
			}
			
			if (mantenimientoUsuarioNuevoForm.getIdEspecialidad2()!=-1) {
				seleccionoUno = true;
				if(mantenimientoUsuarioNuevoForm.getCedulaProfesional2().trim().length() == 0){
					errors.rejectValue("cedulaProfesional2", "mantenimientoUsuarioNuevo.cedulaProfesional2.vacio");
				}else{
					for (String cedula : cedulas) {
						if (cedula!=null) {
							if (cedula.equals(mantenimientoUsuarioNuevoForm.getCedulaProfesional2())) {
								errors.rejectValue("cedulaProfesional2", "mantenimientoUsuarioNuevo.cedulaProfesional2.duplicado");
								break;
							}	
						}
						
					}
					cedulas[1] = mantenimientoUsuarioNuevoForm.getCedulaProfesional2();
				}
				
				if (mantenimientoUsuarioNuevoForm.getIdInstitucion2().trim().length() == 0) {
					errors.rejectValue("idInstitucion2", "mantenimientoUsuarioNuevo.idInstitucion2.vacio");
				}	
			}
			
			if (mantenimientoUsuarioNuevoForm.getIdEspecialidad3()!=-1) {
				seleccionoUno = true;
				if(mantenimientoUsuarioNuevoForm.getCedulaProfesional3().trim().length() == 0){
					errors.rejectValue("cedulaProfesional3", "mantenimientoUsuarioNuevo.cedulaProfesional3.vacio");
				}else{
					for (String cedula : cedulas) {
						if (cedula!=null) {
							if (cedula.equals(mantenimientoUsuarioNuevoForm.getCedulaProfesional3())) {
								errors.rejectValue("cedulaProfesional3", "mantenimientoUsuarioNuevo.cedulaProfesional3.duplicado");	
								break;
							}
						}
					}
					cedulas[2] = mantenimientoUsuarioNuevoForm.getCedulaProfesional3();
				}	
				
				if (mantenimientoUsuarioNuevoForm.getIdInstitucion3().trim().length() == 0) {
					errors.rejectValue("idInstitucion3", "mantenimientoUsuarioNuevo.idInstitucion3.vacio");
				}	
			}
			
			if (!seleccionoUno) {
				errors.rejectValue("idEspecialidad1", "mantenimientoUsuarioNuevo.idEspecialidad1.vacio");
			}	
		}
		
	}
}
