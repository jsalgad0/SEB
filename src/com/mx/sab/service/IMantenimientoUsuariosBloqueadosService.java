package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.MantenimientoUsuarioBloqueadoForm;
import com.mx.sab.model.Roles;
import com.mx.sab.model.Usuarios;
import com.mx.sab.vo.UserInfo;

public interface IMantenimientoUsuariosBloqueadosService {

	Collection<Usuarios> getUsuarios(UserInfo userInfo);

	void getUsuario(MantenimientoUsuarioBloqueadoForm mantenimientoUsuarioBloqueadoForm, Collection<Roles> roles, UserInfo userInfo);

	void getActualizarUsuario(MantenimientoUsuarioBloqueadoForm mantenimientoUsuarioBloqueadoForm,UserInfo userInfo);

}
