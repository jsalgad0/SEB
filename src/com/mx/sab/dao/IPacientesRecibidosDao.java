package com.mx.sab.dao;

import java.util.Collection;
import java.util.Date;

import com.mx.sab.model.Atencionmedica;

public interface IPacientesRecibidosDao {

	Collection<Atencionmedica> getAtenciones(Date fecha, int lugarAtencionId, int limitIni, int limitFin, String nombre, String apellidoPaterno, String apellidoMaterno);
	int getAtencionesCount(Date fecha, int lugarAtencionId, String nombre, String apellidoPaterno, String apellidoMaterno);
	Collection<Atencionmedica> getAtencionesByRecep(Date fecha, int idUsuario, int lugarAtencionId, int limitIni, int limitFin, String nombre, String apellidoPaterno, String apellidoMaterno);
	int getAtencionesCountByRecep(Date fecha, int idUsuario, int lugarAtencionId, String nombre, String apellidoPaterno, String apellidoMaterno);

}
