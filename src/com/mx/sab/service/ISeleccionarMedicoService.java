package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.SeleccionarMedicoForm;
import com.mx.sab.model.Usuarios;
import com.mx.sab.vo.UserInfo;
import com.mx.sab.vo.UsuariosVo;

public interface ISeleccionarMedicoService {

	void inicializarSeleccionarMedicoForm(SeleccionarMedicoForm seleccionarMedicoForm, UserInfo userInfo);

	Collection<UsuariosVo> getMedicos(String idTiempo,
			Collection<Usuarios> usuarios);

	UsuariosVo getMedicoInfo(int idMedico, Collection<Usuarios> usuarios);

}
