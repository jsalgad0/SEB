package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Auditoriaautentia;
import com.mx.sab.model.Loginlog;

public interface IAuditoriaDao {

	void save(Auditoriaautentia auditoriaautentia);

	void update(Auditoriaautentia auditoriaautentia);

	Auditoriaautentia getAuditoriAutentiaById(int idAuditoria);

	Collection<Loginlog> getLoginByUsuarioId(Integer usuarioId);

}
