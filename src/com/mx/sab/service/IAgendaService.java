package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.AgendaForm;
import com.mx.sab.form.AtencionMedicaForm;
import com.mx.sab.form.PersonaConfianzaForm;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Personasdeconfianza;
import com.mx.sab.model.Usuarios;
import com.mx.sab.vo.AgendaVo;
import com.mx.sab.vo.UserInfo;

public interface IAgendaService {

	Collection<Agenda> getAgenda(AgendaForm agendaForm, UserInfo userInfo);

	Collection<AgendaVo> getAgenda(String busquedaM, String busquedaA,
			String busquedaF, String busquedaE, int page, UserInfo userInfo);

	Collection<Usuarios> getMedicos(UserInfo userInfo);

	void save(PersonaConfianzaForm personaConfianzaForm);

	Collection<Personasdeconfianza> getPersonasConfianza(PersonaConfianzaForm personaConfianzaForm);

	void inicializaAtencionMedicaForm(AtencionMedicaForm atencionMedicaForm);

	void save(AtencionMedicaForm atencionMedicaForm, UserInfo userInfo);

	void hola();

}
