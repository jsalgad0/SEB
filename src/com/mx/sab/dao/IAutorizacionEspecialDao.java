package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Catcausas;
import com.mx.sab.model.Cattipoautorizacion;
import com.mx.sab.model.Permisoespecial;

public interface IAutorizacionEspecialDao {


	Collection<Catcausas> getCatCausas();

//	Permisoespecialafiliado getPermisoEspecial(int idPermisoEspecial);

	Catcausas getCatCausa(int id);

//	void update(Permisoespecialafiliado permisoespecialafiliado);

	void save(Permisoespecial permisoespecial);

	Cattipoautorizacion getTipoAutorizacionById(int id);

	int getPermisosEspecialesAfiliadosCount(Integer lugarDeAtencionId);

	Collection<Permisoespecial> getPermisosEspecialesAfiliados(int inicio, int fin, Integer lugarDeAtencionId);
	
//	Collection<Permisoespecialafiliado> getAutorizacionesPendientes(Integer lugarDeAtencionId);

}
