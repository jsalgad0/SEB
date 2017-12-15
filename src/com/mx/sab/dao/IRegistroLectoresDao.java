package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Lectores;
import com.mx.sab.model.Propietarioslector;

public interface IRegistroLectoresDao {

	Collection<Lectores> getLectores(String busqueda, int inicio, int fin);

	int getLectoresCount(String busqueda);

	Propietarioslector getPropietariosLectorById(Integer idPropietarioLector);

	void save(Lectores lectores);

	void update(Lectores lectores);

	Lectores getLectorById(int parseInt);

	Collection<Propietarioslector> getPropietariosLectorByDesc(String busqueda);

	Propietarioslector getPropietariosLectorByPropietario(String busqueda);

	Lectores getLectorByNoSerie(String noDeSerie);

	void savePropietariosLector(Propietarioslector propietarioslector);

	Propietarioslector getPropietariosLectorByRfc(String rfcPropietarioLector);

}
