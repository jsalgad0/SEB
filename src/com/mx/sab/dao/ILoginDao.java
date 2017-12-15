package com.mx.sab.dao;

import java.util.Collection;
import java.util.List;

import com.mx.sab.model.Catpreguntasecreta;
import com.mx.sab.model.Roles;
import com.mx.sab.model.Usuarios;

public interface ILoginDao {

	Usuarios getUsuarioByRfc(String rfc);

	List<Roles> getRoles(int parseInt);

	Collection<Catpreguntasecreta> getPreguntas();

	Catpreguntasecreta getPreguntaById(int i);

}
