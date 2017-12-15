package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Atencionmedica;

public interface IHistorialAtencionesDao {

	int getHistorialCount(int idAfiliado);
	Collection<Atencionmedica> getHistorial(int idAfiliado, int inicio, int fin);
}
