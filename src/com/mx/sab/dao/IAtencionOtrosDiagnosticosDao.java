package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Catcies10;
import com.mx.sab.model.Cattipodiagnostico;
import com.mx.sab.model.NotamedicaCies10;
import com.mx.sab.vo.DiagnosticoVo;

public interface IAtencionOtrosDiagnosticosDao {

	Collection<NotamedicaCies10> getDiagnosticosByIdAtencion(int idAtencion);

	Collection<Catcies10> getDiagnosticosByCodigo(String busqueda);

	Collection<Catcies10> getDiagnosticosByDescripcion(String busqueda);

	Collection<Cattipodiagnostico> getCatTipoDiagnosticos();

	Catcies10 getCatCies10ById(int idDiagnostico);

	Cattipodiagnostico getCatTipoDiagnosticoById(int idTipoDiagnostico);

	void save(NotamedicaCies10 notamedicaCies10);

	NotamedicaCies10 getNotaMedicaCies10(DiagnosticoVo diagnosticoVo);

	void update(NotamedicaCies10 notamedicaCies10);

	void delete(NotamedicaCies10 notamedicaCies10);

}
