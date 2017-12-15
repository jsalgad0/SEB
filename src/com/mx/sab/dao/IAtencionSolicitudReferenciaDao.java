package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Catsolireferenciamotivos;
import com.mx.sab.model.Catsolirefrerenciapor;
import com.mx.sab.model.Solicitudreferencia;

public interface IAtencionSolicitudReferenciaDao {

	Collection<Catsolireferenciamotivos> getCatSoliReferenciaMotivos();

	Collection<Catsolirefrerenciapor> getCatSoliRefrerenciaPor();

	void save(Solicitudreferencia solicitudreferencia);

	Catsolireferenciamotivos getCatSoliReferenciaMotivosById(
			int idCatSoliReferenciaMotivos);

	Catsolirefrerenciapor getCatSoliRefrerenciaPorById(
			int idCatSoliRefrerenciaPor);

	Solicitudreferencia getLastSolicitudreferencia();

	Solicitudreferencia getSolicitudReferenciaByIdAtencionMedica(
			Integer atencionMedicaId);

	void update(Solicitudreferencia solicitudreferencia);

}
