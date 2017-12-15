package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.HistoriaClinicaPersonalesNoPatologicosForm;
import com.mx.sab.vo.CatAlimentacionVo;
import com.mx.sab.vo.CatEscolaridadVo;
import com.mx.sab.vo.CatEstadoCivilVo;
import com.mx.sab.vo.CatGrupoSanguineoVo;
import com.mx.sab.vo.CatHigieneVo;
import com.mx.sab.vo.UserInfo;

public interface IHistoriaClinicaPersonalesNoPatologicosService {

	void inicializarForm(HistoriaClinicaPersonalesNoPatologicosForm historiaClinicaPersonalesNoPatologicosForm);

	Collection<CatGrupoSanguineoVo> getTipoSangre();

	Collection<CatEscolaridadVo> getEscolaridades();

	Collection<CatAlimentacionVo> getAlimentaciones();

	Collection<CatHigieneVo> getHigienePersonales();

	Collection<CatEstadoCivilVo> getEstadosCiviles();

	void saveHistoriaClinicaPersonalesNoPatologicos(HistoriaClinicaPersonalesNoPatologicosForm historiaClinicaPersonalesNoPatologicosForm, UserInfo userInfo);

}
