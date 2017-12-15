package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.form.ConveniosForm;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.ConvenioCuadroprestaciones;
import com.mx.sab.model.Convenios;

public interface IConveniosDao {

	int getConveniosCount(String busquedaP, String busquedaL, String busquedaA);

	Collection<Convenios> getConvenios(String busquedaP, String busquedaL, String busquedaA, int inicio, int fin);

	Convenios getConveniosById(Integer idConvenio);

	void update(Convenios convenios);

	Convenios save(Convenios convenios);

	int getConveniosCount(String acronimo);

	Convenios getLastConvenio();

	void saveConvenioCuadroPrestaciones(
			ConvenioCuadroprestaciones convenioCuadroprestaciones);

	Collection<Convenios> getConveniosByAgenda(Agenda agenda);

	Collection<Convenios> getConveniosByIds(int idAsegurador, int idPrestador,
			int tx_Marca);

	void deleteConvenioById(Convenios convenios);

	Convenios existeConvenio(ConveniosForm conveniosForm);

	Collection<Convenios> getConvenios(ConveniosForm conveniosForm);

	void delete(Convenios convenios);

}
