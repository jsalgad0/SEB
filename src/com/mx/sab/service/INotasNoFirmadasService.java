package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.SupervisorNotasNoFirmadasForm;
import com.mx.sab.vo.NotasNoFirmadasVo;
import com.mx.sab.vo.UserInfo;

public interface INotasNoFirmadasService {

	Collection<NotasNoFirmadasVo> getNotas(int idLugarAtencion);
	SupervisorNotasNoFirmadasForm inicializarNotasNoFirmadasForm(int idAtencionMedica, int tipoMotivo, int idAfiliado, int autorizarRechazar, int medicoAfiliado);
	void actualizarAtencionMedica(SupervisorNotasNoFirmadasForm supervisorNotasNoFirmadasForm, UserInfo userInfo);
	
}
