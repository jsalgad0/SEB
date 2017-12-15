package com.mx.sab.service;

import java.io.IOException;
import java.util.Collection;

import com.mx.sab.form.ConveniosForm;
import com.mx.sab.model.CuadroprestacionPrestacion;
import com.mx.sab.model.Cuadroprestaciones;

public interface ICuadroPrestacionesService {

	Collection<Cuadroprestaciones> getCuadroPrestaciones();

	void cuadroPrestacionesPrestador(ConveniosForm conveniosForm) throws IOException;

	Collection<CuadroprestacionPrestacion> generaCuadroPrestacionPrestacion(
			Integer id, Collection<Integer> prestacionesAsegurador,
			Collection<Integer> prestacionesPrestador);


}
