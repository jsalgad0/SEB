package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.AtencionOtrosDiagnosticosForm;
import com.mx.sab.model.Cattipodiagnostico;
import com.mx.sab.vo.DiagnosticoVo;

public interface IAtencionOtrosDiagnosticosService {

	Collection<DiagnosticoVo> getDiagnosticos(
			AtencionOtrosDiagnosticosForm atencionOtrosDiagnosticosForm);
	
	Collection<DiagnosticoVo> getTodosDiagnosticos(
			AtencionOtrosDiagnosticosForm atencionOtrosDiagnosticosForm);	

	Collection<DiagnosticoVo> getDiagnosticos(String busqueda);

	Collection<Cattipodiagnostico> getCatTipoDiagnosticos();

	void saveDiagnosticos(
			AtencionOtrosDiagnosticosForm atencionOtrosDiagnosticosForm,
			Collection<DiagnosticoVo> diagnosticoVos);

}
