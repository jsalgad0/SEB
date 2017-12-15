package com.mx.sab.service;

import com.mx.sab.form.AutorizacionEspecialForm;
import com.mx.sab.vo.UserInfo;

public interface IAutorizacionEspecialService {

	void inicializaAutorizacionEspecialForm(
			AutorizacionEspecialForm autorizacionEspecialForm, UserInfo userInfo);

	void autorizarPermisoEspecialVigencia(
			AutorizacionEspecialForm autorizacionEspecialForm, UserInfo userInfo);

	void autorizarPermisoEspecialHuella(
			AutorizacionEspecialForm autorizacionEspecialForm, UserInfo userInfo);

}
