package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.ListaPacientesForm;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.vo.AgendaVo;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.UserInfo;

public interface IListaPacientesService {

	Collection<Agenda> getListaPacientes(ListaPacientesForm listaPacientesForm,
			UserInfo userInfo);

	Collection<AgendaVo> getListaPacientes(String busquedaA, String busquedaE,
			int page, UserInfo userInfo);

	Signosvitales getSignosVitales(ListaPacientesForm listaPacientesForm);

	Collection<AutocompleteVo> getCatCies10(String busqueda);

	void save(ListaPacientesForm listaPacientesForm, UserInfo userInfo);

	void agregaDiagnosticos(ListaPacientesForm listaPacientesFormAux,
			ListaPacientesForm listaPacientesForm);

	void getPrestaciones(ListaPacientesForm listaPacientesForm);

	Collection<AutocompleteVo> getCatPrestaciones(String busqueda, int idAgenda);

	void agregaPrestaciones(ListaPacientesForm listaPacientesForm);

	void inicializaForm(ListaPacientesForm listaPacientesForm);

}
