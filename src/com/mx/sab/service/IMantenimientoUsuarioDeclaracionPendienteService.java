package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.model.Usuarios;
import com.mx.sab.vo.UserInfo;
import com.mx.sab.vo.UsuariosVo;

public interface IMantenimientoUsuarioDeclaracionPendienteService {

	Collection<UsuariosVo> getUsuarios(UserInfo userInfo);

	Usuarios getUsuario(int idUsuario);

}
