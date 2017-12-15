package com.mx.sab.service;

import com.mx.sab.form.DatosUsuarioForm;
import com.mx.sab.vo.UserInfo;

public interface IDatosUsuarioService {

	void verificarUsuario(DatosUsuarioForm datosUsuarioForm, UserInfo userInfo);

	void actualizarDatosUsuarioClave(DatosUsuarioForm datosUsuarioForm,
			UserInfo userInfo);

	void actualizarDatosUsuarioPregunta(DatosUsuarioForm datosUsuarioForm,
			UserInfo userInfo);

}
