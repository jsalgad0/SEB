package com.mx.sab.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.enums.RolesEnum;
import com.mx.sab.form.MantenimientoModificarUsuarioForm;
import com.mx.sab.util.ValidatorsUtil;

public class MantenimientoModificarUsuarioValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = MantenimientoModificarUsuarioForm.class.equals(clazz);
		return flag;
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("Entra a valida");
		MantenimientoModificarUsuarioForm mantenimientoUsuarioNuevoForm = (MantenimientoModificarUsuarioForm) obj;
		
		if(mantenimientoUsuarioNuevoForm.isModificarVigencia()==false){
			if(mantenimientoUsuarioNuevoForm.getNombre().trim().length() == 0){
				errors.rejectValue("nombre", "mantenimientoUsuarioNuevo.nombre.vacio");
			}
			
			if(mantenimientoUsuarioNuevoForm.getApellidoPaterno().trim().length() == 0){
				errors.rejectValue("apellidoPaterno", "mantenimientoUsuarioNuevo.apellidoPaterno.vacio");
			}
			
			if(mantenimientoUsuarioNuevoForm.getApellidoMaterno().trim().length() == 0){
				errors.rejectValue("apellidoMaterno", "mantenimientoUsuarioNuevo.apellidoMaterno.vacio");
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
			
			if (mantenimientoUsuarioNuevoForm.getCurp().trim().length()>0) {
				if (!ValidatorsUtil.validaCURP(mantenimientoUsuarioNuevoForm.getCurp())) {
					errors.rejectValue("curp", "mantenimientoUsuarioNuevo.curp.formato");	
				}	
			}
			
			boolean campoObligatorio = false;
			
			
			if (mantenimientoUsuarioNuevoForm.getTelefono().trim().length()==0) {
				if (mantenimientoUsuarioNuevoForm.getMail().trim().length()==0) {
					errors.rejectValue("telefono", "mantenimientoUsuarioNuevo.telefono.vacio");		
				}
			}else {
				if (!ValidatorsUtil.validaCelular(mantenimientoUsuarioNuevoForm.getTelefono())) {
					errors.rejectValue("telefono", "mantenimientoUsuarioNuevo.telefono.formato");
					campoObligatorio = true;
				}else{
					campoObligatorio = true;
				}	
			}
			
			if (mantenimientoUsuarioNuevoForm.getMail().trim().length()==0) {
				if (!campoObligatorio) {
					errors.rejectValue("mail", "mantenimientoUsuarioNuevo.mail.vacio");	
				}
			}else {
				if (!ValidatorsUtil.validaCorreo(mantenimientoUsuarioNuevoForm.getMail())) {
					errors.rejectValue("mail", "mantenimientoUsuarioNuevo.mail.formato");	
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
			
			if (validarEspecialidad) {
				
				if(mantenimientoUsuarioNuevoForm.getClaveInterna().trim().length() == 0){
					errors.rejectValue("claveInterna", "mantenimientoUsuarioNuevo.claveInterna.vacio");
				}
				
				if(mantenimientoUsuarioNuevoForm.getRegSs().trim().length() == 0){
					errors.rejectValue("regSs", "mantenimientoUsuarioNuevo.regss.vacio");
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
					errors.rejectValue("idEspecialidad1", "mantenimientoUsuarioNuevo.especialidad.vacio");
				}	
			}
		}
	}
}