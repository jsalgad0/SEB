package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.SupervisorUsuariosPendientesForm;
import com.mx.sab.model.Catcausas;
import com.mx.sab.vo.UserInfo;
import com.mx.sab.vo.UsuariosVo;

public interface ISupervisorUsuariosPendientesService {

	Collection<UsuariosVo> getUsuariosPendientes(UserInfo userInfo);
	Collection<UsuariosVo> getUsuariosPendientes(int page, UserInfo userInfo);
	void updateEstatusUsuario(SupervisorUsuariosPendientesForm supervisorUsuariosPendientesForm);
	Collection<Catcausas> getCatCausas();
	SupervisorUsuariosPendientesForm getInfoUsuario(int idUsuario);
	SupervisorUsuariosPendientesForm updateClaveUsuario(SupervisorUsuariosPendientesForm supervisorUsuariosPendientesForm, UserInfo userInfo);
}
