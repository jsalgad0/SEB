package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.CicloVitalForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.vo.CatGrupoSanguineoVo;
import com.mx.sab.vo.CatMpfVo;
import com.mx.sab.vo.CatTipoFamiliaVo;
import com.mx.sab.vo.UserInfo;

public interface IMedicoCicloVitalService {

	void guardarCicloVital(CicloVitalForm cicloVitalForm, UserInfo userInfo);
	CicloVitalForm getCicloVital(MedicoAtencionPacientesForm medicoAtencionPacientesForm, UserInfo userInfo);
	Collection<CatTipoFamiliaVo> getCatTipoFamilia();
}
