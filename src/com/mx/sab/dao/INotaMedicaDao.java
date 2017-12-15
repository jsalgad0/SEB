package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Catcies10;
import com.mx.sab.model.Cattipodiagnostico;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.NotamedicaCies10;

public interface INotaMedicaDao {

	
	Notamedica getNotaMedicaByIdAtencion(Integer idAtencion);
	NotamedicaCies10 getNotaMedicaCies10ByIdNotaMedica(Integer idNotaMedica);
	
	void saveNotaMedica(Notamedica notaMedica);
	void updateNotaMedica(Notamedica notaMedica);
	
	Collection<Catcies10> getDiagnosticosByCodigo(String busqueda);
	Collection<Catcies10> getDiagnosticosByDescripcion(String busqueda);
	Collection<Cattipodiagnostico> getCatTipoDiagnosticos();
	Catcies10 getCatCies10ById(int idDiagnostico);
	Cattipodiagnostico getCatTipoDiagnosticoById(int idTipoDiagnostico);
	void saveNotaMedicaCies10(NotamedicaCies10 notamedicaCies10);
	void updateNotaMedicaCies10(NotamedicaCies10 notamedicaCies10);
	void deleteNotaMedicaCies10(NotamedicaCies10 notamedicaCies10);
	Collection<NotamedicaCies10> getAllNotasMedicasCies10ByNotaMedicaId(Integer idNotaMedica);
}
