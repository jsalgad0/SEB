package com.mx.sab.service;

import com.mx.sab.form.AtencionTerminarForm;
import com.mx.sab.vo.UserInfo;

public interface IAtencionTerminarService {

	void inicializarForm(AtencionTerminarForm atencionTerminarForm, UserInfo userInfo);

	void verificarClave(AtencionTerminarForm atencionTerminarForm, UserInfo userInfo);

	void inicializarForm(AtencionTerminarForm atencionTerminarForm, UserInfo userInfo,
			AtencionTerminarForm atencionTerminarFormAux);

}
