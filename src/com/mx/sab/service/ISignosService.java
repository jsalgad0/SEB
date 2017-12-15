package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.ListaSignosForm;
import com.mx.sab.form.SignosVitalesForm;
import com.mx.sab.vo.ListaSignosVo;
import com.mx.sab.vo.TomaSignosVo;
import com.mx.sab.vo.UserInfo;

public interface ISignosService {

	Collection<ListaSignosVo> getAtenciones(ListaSignosForm listaSignosForm, UserInfo userInfo);
	
	String getNombre(SignosVitalesForm signosVitalesForm);
	
	TomaSignosVo getTomaSignos(int idAtencion);
	
	void save(SignosVitalesForm signosVitalesForm, UserInfo userInfo) throws Exception;
	
	Collection<ListaSignosVo> getLista(int page, UserInfo userInfo);
	
	SignosVitalesForm getSignosVitales(ListaSignosForm listaSignosForm);
	
	void update(SignosVitalesForm signosVitalesForm, UserInfo userInfo);
	
	void getAtencionTomaSignos(SignosVitalesForm signosVitalesForm);

	void saveAtencionSignosVitales(SignosVitalesForm signosVitalesForm,
			UserInfo userInfo);
}
