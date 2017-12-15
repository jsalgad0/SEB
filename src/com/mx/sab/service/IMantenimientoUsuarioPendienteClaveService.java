package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.model.Usuarios;
import com.mx.sab.vo.UserInfo;

public interface IMantenimientoUsuarioPendienteClaveService {

	Collection<Usuarios> getUsuarios(UserInfo userInfo);

}
