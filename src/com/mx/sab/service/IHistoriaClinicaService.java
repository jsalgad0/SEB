package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.HistoriaClincaForm;
import com.mx.sab.model.Catalimentacion;
import com.mx.sab.model.Cathigiene;
import com.mx.sab.model.Catmpf;
import com.mx.sab.model.Cattipofamilia;
import com.mx.sab.vo.UserInfo;

public interface IHistoriaClinicaService {

	Collection<Catalimentacion> getAlimentacion();
	Collection<Cathigiene> getHigiene();
	Collection<Catmpf> getMpf();
	Collection<Cattipofamilia> getFamilia();
	HistoriaClincaForm getHistoria(HistoriaClincaForm historiaClincaForm);
	void saveHistoria(HistoriaClincaForm historiaClincaForm, UserInfo userInfo);
	
}
