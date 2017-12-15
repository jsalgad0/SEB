package com.mx.sab.dao;

import com.mx.sab.model.Contrareferencia;

public interface IContrareferenciaDao {

	void save(Contrareferencia contrareferencia);

	Contrareferencia getContrareferenciaByIdSolicitud(
			Integer solicitudReferenciaId);

	Contrareferencia getContrareferenciaById(int idContrareferencia);

	void update(Contrareferencia contrareferencia);

}
