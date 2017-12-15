package com.mx.sab.service;

import com.mx.sab.form.AntecedentesFamiliaresForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.vo.UserInfo;

public interface IMedicoAntecedentesFamiliaresService {

	AntecedentesFamiliaresForm getAntecedentesFamiliares(MedicoAtencionPacientesForm medicoAtencionPacientesForm, UserInfo userInfo);
	void guardarAntecedentesFamiliares(AntecedentesFamiliaresForm antecedentesFamiliaresForm, UserInfo userInfo);
}
