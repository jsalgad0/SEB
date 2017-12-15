package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.Catcies10;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.NotamedicaCies10;
import com.mx.sab.model.Signosvitales;

public interface IListaPacientesDao {

	Signosvitales getSignosVitalesByIdAtencionMedica(int idAgenda);

	Collection<Catcies10> getCatCies10(String busqueda);

	void save(Notamedica notamedica);

	void saveCies10(NotamedicaCies10 notamedicaCies10);

	Atencionprestacion getAtencionPrestacion(Integer integer,
			Integer key);
	
	Collection<Atencionprestacion> getAtencionPrestaciones(Integer integer);	

	void delete(Atencionprestacion atencionprestacion);

	void saveAtencionPrestacion(Atencionprestacion atencionprestacion);

	Notamedica getNotaMedicaByAtencionMedicaId(Integer atencionMedicaId);

	Catcies10 getCatCies10ById(int idDiagnostico);

	Collection<NotamedicaCies10> getNotaMedicaCies10ByIdNotaMedica(Integer notaMedicaId);

	Notamedica getNotaMedicaById(Integer notaMedicaId);

	NotamedicaCies10 getNotaMedicaCies10ByIdNotaMedicaPrincipal(
			Integer notaMedicaId);

	void update(Notamedica notamedica);

	void updateCies10(NotamedicaCies10 notamedicaCies10);

	void deleteCies10(NotamedicaCies10 notamedicaCies10);

}
