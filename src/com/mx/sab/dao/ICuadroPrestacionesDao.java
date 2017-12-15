package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.CuadroprestacionPrestacion;
import com.mx.sab.model.Cuadroprestaciones;
import com.mx.sab.model.Prestacionasegurador;
import com.mx.sab.model.Prestacionprestador;

public interface ICuadroPrestacionesDao {

	Cuadroprestaciones saveCuadroPrestaciones(Cuadroprestaciones cuadroprestaciones);

	Catprestacion getPrestacionSabById(int numericCellValue);

	void saveCuadroPrestacionPrestacion(
			CuadroprestacionPrestacion cuadroprestacionPrestacion);

	Collection<Cuadroprestaciones> getCuadroPrestacionesByPrestadorId(
			Integer idPrestadores);

	void update(Cuadroprestaciones cuadroprestaciones);

	Cuadroprestaciones getCuadroPrestacionesByNombre(String cuadroPrestacion);

	Collection<Cuadroprestaciones> getCuadroPrestaciones();

	Collection<CuadroprestacionPrestacion> getCuadroPrestacionesPrestacionById(
			Integer idCuadroPrestacion);

	Collection<Prestacionasegurador> getPrestacionesAseguradorById(
			Integer idAseguradores);

	Collection<Prestacionprestador> getPrestacionesPrestadorById(
			Integer idPrestadores);

}
