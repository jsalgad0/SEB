package com.mx.sab.service;

import java.util.Collection;
import java.util.List;

import com.mx.sab.form.LoginForm;
import com.mx.sab.model.Catpreguntasecreta;
import com.mx.sab.model.Menu;
import com.mx.sab.vo.UserInfo;

public interface ILoginService {

	List<Menu> getRoles(String rol);

	LoginForm validaRfc(LoginForm loginForm);

	LoginForm validaClave(LoginForm loginForm);

	Collection<Catpreguntasecreta> getPreguntas();

	void validarClave(LoginForm loginForm);

	void saveLoginLog(UserInfo userInfo);

	void validarRespuesta(LoginForm loginForm);

	void getUsuarioOlvidoClave(LoginForm loginForm);

	void validarClaveNueva(LoginForm loginForm);


}
