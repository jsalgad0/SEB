package com.mx.sab.dao;

import com.mx.sab.model.Folio;

public interface IFoliosDao {

	Folio getFolioById(int id, Integer integer);

	void updateFolio(Folio folio);
}
