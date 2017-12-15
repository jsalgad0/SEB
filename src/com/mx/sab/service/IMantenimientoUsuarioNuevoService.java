package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.MantenimientoUsuarioNuevoForm;
import com.mx.sab.model.Catespecialidadesmedicas;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Roles;
import com.mx.sab.vo.UserInfo;

public interface IMantenimientoUsuarioNuevoService {

	Collection<Catespecialidadesmedicas> getEspecialidades();

	Collection<Roles> getRoles(UserInfo userInfo);

	void verificarRfc(MantenimientoUsuarioNuevoForm mantenimientoUsuarioNuevoForm, UserInfo userInfo, Collection<Roles> roles);

	void saveUsuario(MantenimientoUsuarioNuevoForm mantenimientoUsuarioNuevoForm,UserInfo userInfo);

	void updateUsuario(MantenimientoUsuarioNuevoForm mantenimientoUsuarioNuevoForm, UserInfo userInfo);


}
