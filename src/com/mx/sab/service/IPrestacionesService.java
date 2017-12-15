package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.PrestacionesForm;
import com.mx.sab.vo.AutocompleteVo;

public interface IPrestacionesService {

	void save(PrestacionesForm prestacionesForm);

	Collection<AutocompleteVo> getPrestaciones(int idConvenio,
			String busqueda);

	Collection<AutocompleteVo> getPrestacionesEstudios(int idConvenio,
			String busqueda, int idServicio);

	Collection<AutocompleteVo> getPrestacionesSAB(int idConvenio, String busqueda);

}
