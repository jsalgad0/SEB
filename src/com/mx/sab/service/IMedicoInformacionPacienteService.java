package com.mx.sab.service;

import com.mx.sab.form.InformacionPacienteForm;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.vo.InformacionPacienteVo;
import com.mx.sab.vo.TomaSignosVo;

public interface IMedicoInformacionPacienteService {

	Atencionmedica getAtencion(int idAtencion);
	TomaSignosVo getSignos(int idAtencion);
	InformacionPacienteVo getInformacionPaciente(int idAtencion);
	void updateInformacionPaciente(InformacionPacienteForm informacionPacienteForm);
	
}
