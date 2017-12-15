package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.GinecoObstentricosForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.vo.CatGrupoSanguineoVo;
import com.mx.sab.vo.CatMpfVo;
import com.mx.sab.vo.UserInfo;

public interface IMedicoGinecoObstentricosService {

	void guardarGinecoObstentricos(GinecoObstentricosForm ginecoObstentricosForm, UserInfo userInfo);
	GinecoObstentricosForm getGinecoObstentricos(MedicoAtencionPacientesForm medicoAtencionPacientesForm, UserInfo userInfo);
	Collection<CatMpfVo> getCatMpf();
}
