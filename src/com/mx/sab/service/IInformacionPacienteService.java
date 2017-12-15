package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.InformacionPacienteForm;
import com.mx.sab.model.Catgrupossanguineos;
import com.mx.sab.model.Catocupacion;

public interface IInformacionPacienteService {

	InformacionPacienteForm getForm(InformacionPacienteForm form);
	Collection<Catocupacion> getOcupacion();
	Collection<Catgrupossanguineos> getGrupoSanguineo();
	void save(InformacionPacienteForm form);
	
}
