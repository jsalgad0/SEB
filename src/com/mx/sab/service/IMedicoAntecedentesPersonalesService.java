package com.mx.sab.service;

import com.mx.sab.form.AntecedentesPersonalesForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.vo.UserInfo;

public interface IMedicoAntecedentesPersonalesService {

	AntecedentesPersonalesForm getAntecedentesPersonales(MedicoAtencionPacientesForm medicoAtencionPacientesForm, UserInfo userInfo);
	void guardarAntecedentesPersonales(AntecedentesPersonalesForm antecedentesPersonalesForm, UserInfo userInfo);
}
