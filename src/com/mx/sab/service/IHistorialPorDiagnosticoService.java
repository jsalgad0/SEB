package com.mx.sab.service;

import java.util.Collection;
import java.util.List;

import com.mx.sab.form.HistorialPorDiagnosticoForm;
import com.mx.sab.vo.HistorialPorDiagnosticoVo;

public interface IHistorialPorDiagnosticoService {

	Collection<HistorialPorDiagnosticoVo> getDiagnosticos(HistorialPorDiagnosticoForm historialPorDiagnosticoForm);

	Collection<HistorialPorDiagnosticoVo> getDiagnosticos(
			List<HistorialPorDiagnosticoVo> historialPorDiagnosticoVosAux,
			int page);

	Collection<HistorialPorDiagnosticoVo> getDiagnosticosDetallados(
			HistorialPorDiagnosticoForm historialPorDiagnosticoForm);

	Collection<HistorialPorDiagnosticoVo> getDiagnosticos(int idAfiliado,
			int idDiagnostico, int page);

}
