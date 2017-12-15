package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.PacientesRecibidosForm;
import com.mx.sab.vo.PacientesRecibidosVo;
import com.mx.sab.vo.UserInfo;


public interface IPacientesRecibidosService {

	Collection<PacientesRecibidosVo> getLista(UserInfo userInfo, PacientesRecibidosForm pacientesRecibidosForm);
	Collection<PacientesRecibidosVo> getListaPaginador(UserInfo userInfo, int page, String nombre, String apellidoPaterno, String apellidoMaterno, int todos);

}
