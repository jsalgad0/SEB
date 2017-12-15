package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.AntecedentesPersonalesForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.form.NotaMedicaForm;
import com.mx.sab.model.Cattipodiagnostico;
import com.mx.sab.vo.DiagnosticoVo;
import com.mx.sab.vo.UserInfo;

public interface IMedicoNotaMedicaService {


	NotaMedicaForm getNotaMedica(MedicoAtencionPacientesForm medicoAtencionPacientesForm, UserInfo userInfo);
	void guardarNotaMedica(NotaMedicaForm notaMedicaForm, UserInfo userInfo);
	Collection<Cattipodiagnostico> getCatTipoDiagnosticos();
	Collection<DiagnosticoVo> getDiagnosticos(String busqueda);
}
