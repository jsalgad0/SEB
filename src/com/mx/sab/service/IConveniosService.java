package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.ConveniosForm;
import com.mx.sab.model.Convenios;
import com.mx.sab.vo.ConveniosVo;

public interface IConveniosService {

	Collection<ConveniosVo> getConvenios(ConveniosForm conveniosForm);

	Collection<ConveniosVo> getConvenios(String busquedaP, String busquedaL, String busquedaA, int page);

	void delete(ConveniosForm conveniosForm);

	void save(ConveniosForm conveniosForm);

	String getNombre(Integer idAseguradores, Integer idLugarDeAtencion, Integer idPrestadores);

	void update(ConveniosForm conveniosForm);

}
