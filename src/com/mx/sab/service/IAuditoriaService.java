package com.mx.sab.service;

import com.mx.sab.model.Auditoriaautentia;

public interface IAuditoriaService {

	Auditoriaautentia agregarAuditoria(String idUsuario, String idAfiliado, String idAgenda, String tipoDato,
			String dato, String tipoAudit, String nroAudit, String ercDesc,
			String erc);

}
