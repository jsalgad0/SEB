package com.mx.sab.form.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mx.sab.form.UsuarioForm;

public class UsuarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		boolean flag = UsuarioForm.class.equals(clazz);
		return flag;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("Entra a valida");
		UsuarioForm usuarioForm = (UsuarioForm) obj;
		if (usuarioForm.getUsuario().getNombre() == null
				|| usuarioForm.getUsuario().getNombre().trim().length() == 0) {
			errors.rejectValue("error", "usuario.nombre.vacio");
		}
		if (usuarioForm.getUsuario().getApellidoPaterno() == null
				|| usuarioForm.getUsuario().getApellidoPaterno().trim()
						.length() == 0) {
			errors.rejectValue("error", "usuario.apellidoP.vacio");
		}
		if (usuarioForm.getUsuario().getApellidoMaterno() == null
				|| usuarioForm.getUsuario().getApellidoMaterno().trim()
						.length() == 0) {
			errors.rejectValue("error", "usuario.apellidoM.vacio");
		}
		if (usuarioForm.getUsuario().getMail() == null
				|| usuarioForm.getUsuario().getMail().trim().length() == 0) {
			errors.rejectValue("error", "usuario.mail.vacio");
		}
		if (usuarioForm.getIdSexo() == null
				|| usuarioForm.getIdSexo().length() == 0) {
			errors.rejectValue("error", "usuario.sexo.vacio");
		}
		if (usuarioForm.getFechaDeNacimiento() == null) {
			errors.rejectValue("error", "usuario.fechaNacimiento.vacio");
		}else{
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				formatter.parse(usuarioForm.getFechaDeNacimiento());
			} catch (ParseException e) {
				errors.rejectValue("error", "usuario.fechaNacimiento.formato");
			}
		}
		if (usuarioForm.getIdEstado().equals("-1")) {
			errors.rejectValue("error", "usuario.estado.vacio");
		}
		if (usuarioForm.getRoles() == null
				|| usuarioForm.getRoles().size() == 0) {
			errors.rejectValue("error", "usuario.roles.vacio");
		}
		if (usuarioForm.getIdModulo().equals("-1")) {
			errors.rejectValue("error", "usuario.modulo.vacio");
		} else {
			if (usuarioForm.getIdModulo().equals("2")) {
				if (usuarioForm.getAdminInstitucion()==null || usuarioForm.getAdminInstitucion().trim().length()==0) {
					errors.rejectValue("error", "usuario.admin.inst.vacio");
				}
			} else if (usuarioForm.getIdModulo().equals("3")) {
				
				if(usuarioForm.getIdLugarAtencion()==null || usuarioForm.getIdLugarAtencion().size()==0){
					errors.rejectValue("error", "usuario.lugar.atencion.vacio");
				}
				if(usuarioForm.getRoles().contains("14")){
					if(usuarioForm.getEspecialiadesVo()==null){
						errors.rejectValue("error", "usuario.especialidades.vacio");
					}else{
						if(usuarioForm.getEspecialiadesVo().getId()==null || usuarioForm.getEspecialiadesVo().getId().size()==0){
							errors.rejectValue("error", "usuario.especialidades.vacio");
						}else{
							boolean cont = false;
							for (String mat : usuarioForm.getEspecialiadesVo().getMatricula()) {
								if(mat==null || mat.trim().length()==0){
									cont = true;
								}
							}
							if(cont){
								errors.rejectValue("error", "usuario.especialidades.matricula.vacio");
							}
						}
					}
					if(usuarioForm.getTipoIdentificadorVo()==null){
						errors.rejectValue("error", "usuario.tipo.identificador.vacio");
					}else{
						if(usuarioForm.getTipoIdentificadorVo().getId()==null || usuarioForm.getTipoIdentificadorVo().getId().size()==0){
							errors.rejectValue("error", "usuario.tipo.identificador.vacio");
						}else{
							boolean cont = false;
							for (String mat : usuarioForm.getTipoIdentificadorVo().getValor()) {
								if(mat==null || mat.trim().length()==0){
									cont = true;
								}
							}
							if(cont){
								errors.rejectValue("error", "usuario.tipo.identificador.valor.vacio");
							}
						}
					}			
				}
			}else if (usuarioForm.getIdModulo().equals("4")) {
				if(usuarioForm.getIdLugarAtencion()==null || usuarioForm.getIdLugarAtencion().size()==0){
					errors.rejectValue("error", "usuario.lugar.atencion.vacio");
				}
			}
		}
	}
}
