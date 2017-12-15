package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Afiliado;
import com.mx.sab.model.AfiliadoAsegurador;
import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Cattipoafiliado;
import com.mx.sab.model.Cattipoidentificador;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Prestadores;
import com.mx.sab.model.Usuarios;
import com.mx.sab.model.jndi.AfiliadoTipoIdentificador;

public interface ICargaAgendaDao {

	Collection<AfiliadoTipoIdentificador> getAfiliadoTipoIdentificadorIsssteJNDI(
			int parseInt);

	Collection<AfiliadoTipoIdentificador> getAfiliadoTipoIdentificadorIdJNDI(
			int afiliadoId);

	Cattipoafiliado getCatTipoAfiliadoByClaveJNDI(int derechohabiente,
			int idAsegurador);

	Afiliado saveAfiliadoJNDI(Afiliado afiliado);

	void saveAfiliadoAseguradorJNDI(AfiliadoAsegurador afiliadoAsegurador);

	void saveAfiliadotipoIdentificarJNDI(
			Afiliadotipoidentificador afiliadotipoidentificador);

	Cattipoidentificador getCatTipoIdentificadorByIdJNDI(int parseInt);

	Lugaresdeatencion getLugarAtencionByClaveJNDI(String claveInst);

	Prestadores getPrestadoresByRfcJNDI(String prestador);

	Usuarios getUsuarioByIdentificadorJNDI(int id, String cveMedico);

	Object existeAgendaJNDI(Agenda agenda);

	Object existeAgendaByConsultorioJNDI(Agenda agenda);

	Object existeAgendaByUsuarioJNDI(Agenda agenda);

	void saveAgendaJNDI(Agenda agenda);


}
