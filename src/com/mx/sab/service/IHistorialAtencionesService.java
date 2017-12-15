package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.HistorialAtencionesForm;
import com.mx.sab.vo.HistorialAtencionesVo;

public interface IHistorialAtencionesService {

	Collection<HistorialAtencionesVo> getHistorial(HistorialAtencionesForm historialAtencionesForm);

	Collection<HistorialAtencionesVo> getHistorial(int idAfiliado, int page);

}
