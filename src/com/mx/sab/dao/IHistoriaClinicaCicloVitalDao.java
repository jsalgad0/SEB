package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Cattipofamilia;
import com.mx.sab.model.HcFasedelciclo;

public interface IHistoriaClinicaCicloVitalDao {

	HcFasedelciclo getFasedelciclo(Integer historiaMedicaId);
	void saveFasedelciclo(HcFasedelciclo hcFasedelciclo);
	void updateFasedelciclo(HcFasedelciclo hcFasedelciclo);
	Collection<Cattipofamilia> getTipoFamilia();
	Cattipofamilia getCatTipoFamiliaById(int idTipoFamilia);

}
