package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Bitacoracargaprestacion;
import com.mx.sab.model.Catestatusarchivoprestaciones;
import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.Detallebitacoracargaprestaciones;
import com.mx.sab.model.Prestacionasegurador;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.model.Prestacionesprestadorequivalencias;
import com.mx.sab.model.Prestacionprestador;

public interface IPrestacionesDao {

	void savePrestacionesAsegurador(Prestacionasegurador prestacionasegurador);

	void savePrestacionesPrestador(Prestacionprestador prestacionprestador);

	Collection<Prestacionprestador> getPrestacionesByPrestador(
			Integer idPrestadores);

	Collection<Prestacionasegurador> getPrestacionesByAsegurador(
			Integer idAseguradores);

	void saveEquivalenciasAsegurador(
			Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias);

	void saveEquivalenciasPrestador(
			Prestacionesprestadorequivalencias prestacionesprestadorequivalencias);

	void saveBitacoraArchivoPrestaciones(
			Bitacoracargaprestacion bitacoracargaprestacion);
	
	void saveDetalleBitacora(
			Detallebitacoracargaprestaciones detalle);

	Catprestacion getCatPrestacion(String prestacion);

	Prestacionprestador getPrestacionPrestador(String prestacion);
	
	Prestacionasegurador getPrestacionAsegurador(String prestacion);

	Prestacionesprestadorequivalencias getPrestacionesPrestadorEquivalencias(
			Integer prestacionPrestadorId);

	Catprestacion getCatPrestacionById(int idPrestacion);

	Collection<Catprestacion> getCatPrestacionByDescripcion(String busqueda);

	Collection<Prestacionprestador> getPrestacionesByDescripcion(
			Integer prestadorId, String busqueda);

	Prestacionasegurador getPrestacionAseguradorById(int parseInt);

	Prestacionesaseguradorequivalencias getPrestacionEquivalenciaByPrestacionAseguradorId(int idPrestacion);

	Collection<Prestacionasegurador> getPrestacionesAsegurador(Integer aseguradorId,
			int idCuadroPrestaciones, String busqueda);

	Collection<Prestacionasegurador> getPrestacionesAseguradorEstudios(
			Integer aseguradorId, int idCuadroPrestaciones, String busqueda,
			int idServicio, int idServicioExtra);

	Collection<Prestacionasegurador> getPrestacionesAseguradorEstudiosCodigo(
			Integer aseguradorId, int idCuadroPrestaciones, String busqueda,
			int idServicio, int idServicioExtra);
	
	Collection<Catprestacion> getPrestacionesEstudios(
			Integer aseguradorId, String busqueda,
			int idServicio, int idServicioExtra);

	Collection<Catprestacion> getPrestacionesEstudiosCodigo(
			Integer aseguradorId, String busqueda,
			int idServicio, int idServicioExtra);	

	Collection<Catprestacion> getPrestacionesSAB(String busqueda);
	
	Prestacionasegurador getLastPrestacionAseguradorByIdAsegurador(int idAsegurador);
	
	Catestatusarchivoprestaciones getCatEstatus(int estatusId);
	
	Bitacoracargaprestacion getLastBitacoraByIdAsegurador(int idAsegurador);
	
	
	

}
