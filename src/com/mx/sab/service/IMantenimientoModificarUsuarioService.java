package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.MantenimientoModificarUsuarioForm;
import com.mx.sab.model.Roles;
import com.mx.sab.vo.UserInfo;
import com.mx.sab.vo.UsuariosVo;

public interface IMantenimientoModificarUsuarioService {

	Collection<UsuariosVo> getUsuarios(MantenimientoModificarUsuarioForm mantenimientoModificarUsuarioForm, UserInfo userInfo);

	Collection<UsuariosVo> getUsuarios(String busquedaRfc, String busquedaNombre, String busquedaApellidoPaterno, String busquedaApellidoMaterno, int page, UserInfo userInfo);

	void getUsuario(MantenimientoModificarUsuarioForm mantenimientoModificarUsuarioForm, Collection<Roles> roles, UserInfo userInfo);

	void updateUsuario(MantenimientoModificarUsuarioForm mantenimientoModificarUsuarioForm, MantenimientoModificarUsuarioForm mantenimientoModificarUsuarioFormAux, UserInfo userInfo);

}
