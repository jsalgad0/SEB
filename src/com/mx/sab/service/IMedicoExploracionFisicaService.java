package com.mx.sab.service;

import com.mx.sab.form.ExploracionFisicaForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.vo.UserInfo;

public interface IMedicoExploracionFisicaService {

	ExploracionFisicaForm getExploracionFisica(MedicoAtencionPacientesForm medicoAtencionPacientesForm, UserInfo userInfo);
	void guardarExploracionFisica(ExploracionFisicaForm exploracionFisicaForm, UserInfo userInfo);
}
