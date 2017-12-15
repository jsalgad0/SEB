package com.mx.sab.dao;

import java.util.Collection;
import java.util.Date;

import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.Catestatusatencionidentidad;
import com.mx.sab.model.Catestatusatencionvigencia;
import com.mx.sab.model.Catestatusrecepcion;
import com.mx.sab.model.Catmotivos;
import com.mx.sab.model.Motivos;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.NotamedicaCies10;
import com.mx.sab.model.Prestacionesportomar;
import com.mx.sab.model.Tipoatencionmedica;

public interface IAtencionMedicaDao {

	int getAfiliadosPermisosEspecialesVigenciaCount(Integer lugarDeAtencionId);

	int getAfiliadosPermisosEspecialesIdentidadCount(Integer lugarDeAtencionId);

	Collection<Atencionmedica> getAfiliadosPermisosEspecialesVigencia(int inicio, int fin, Integer lugarDeAtencionId);

	Collection<Atencionmedica> getAfiliadosPermisosEspecialesIdentidad(int inicio, int fin, Integer lugarDeAtencionId);

	Atencionmedica getAtencionMedicaById(int idAtencionMedica);

	Catestatusatencionvigencia getCatEstatusAtencionVigenciaById(int id);

	Catestatusatencionidentidad getCatEstatusAtencionIdentidadById(int id);

	void update(Atencionmedica atencionmedica);


	int getAtencionesPendientesCount(Integer lugarDeAtencionId);

	Collection<Atencionmedica> getAtencionesPendientes(int inicio, int fin, Integer lugarDeAtencionId);
	
	int getAtencionesHoyCount(Integer lugarDeAtencionId,Date fecha);
	Collection<Atencionmedica> getAtencionesHoy(int inicio, int fin, Integer lugarDeAtencionId,Date fecha);
	
	Atencionmedica getAtencionMedicaByFolio(String folio);

	Catmotivos getCatMotivo(int id);

	void saveMotivo(Motivos motivos);

	Collection<Atencionprestacion> getPrestacionesPendientes(int idAtencionMedica);
	
	Collection<Prestacionesportomar> getPrestacionesPorTomar(int idAtencionMedica);
	
	void deleteAtencionPrestacion(Atencionprestacion atencionprestacionAux);
	void updatePrestacionPorTomar(Prestacionesportomar prestacionportomar);

	Atencionprestacion getAtencionPrestacionByPrincipal(int idAtencionMedica);
	Catestatusrecepcion getCatEstatusRecepcion(int id);

	Collection<Atencionmedica> getAtencionMedicaByAfiliadoName(Date fecha,Integer lugarDeAtencionId, String nombre, String apellidoPaterno, String apellidoMaterno);

	Collection<NotamedicaCies10> getNotamedicaCies10ByIdAfiliado(int idAfiliado);

	int getCountDiagnosticosByIdDiagnostico(
			int idAfiliado, int idDiagnostico);

	Collection<NotamedicaCies10> getDiagnosticosByIdDiagnostico(int idAfiliado,
			int idDiagnostico, int inicio, int fin);

	Notamedica getNotaMedicaByAtencionMedicaId(Integer idAtencionmedica);

	Tipoatencionmedica getTipoAtencionMedicaById(int tipoAtencionMedica);
}
