package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.Catpaginasestudios;
import com.mx.sab.model.Prestacionesportomar;

public interface IEstudiosMedicosDao {

	Prestacionesportomar getPrestacionPorTomar(Integer atencionMedicaId,
			Integer prestacionId);

	void delete(Prestacionesportomar prestacionesportomar);

	Catpaginasestudios getCatPaginasEstudiosById(int i);

	void savePrestacionesPorTomar(Prestacionesportomar prestacionesportomar);

	Collection<Prestacionesportomar> getPrestacionPorTomarByEstudios(
			Integer atencionMedicaId, int idEstudios);

	void deletePrestacionesPorTomar(Prestacionesportomar prestacionesportomar);

	Prestacionesportomar getPrestacionPorTomarByOrden(String ordenServicio,
			int i);

	void updatePrestacionPorTomar(Prestacionesportomar prestacionesportomar);

}
