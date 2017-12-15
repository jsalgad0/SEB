package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.vo.AfiliadoVo;
import com.mx.sab.vo.UserInfo;

public interface IMedicoAtencionPacientesService {

	Collection<AfiliadoVo> getAfiliados(MedicoAtencionPacientesForm medicoAtencionPacientesForm,UserInfo userInfo);

	Collection<AfiliadoVo> getAfiliados(String busquedaApellidoPaterno, String fecha, int idEstatus, int page, UserInfo userInfo);

}
