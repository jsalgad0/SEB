package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.form.LugarAtencionForm;
import com.mx.sab.model.Catnivel;
import com.mx.sab.model.Cattipounidad;
import com.mx.sab.model.Catzonacardinal;
import com.mx.sab.model.Codigolugaratencion;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Lugaresdeatencionroles;
import com.mx.sab.model.Roles;

public interface ILugarAtencionDao {

	int getLugarAtencionCount(String busqueda);

	Collection<Lugaresdeatencion> getLugarAtencion(String busqueda, int inicio, int fin);

	void update(Lugaresdeatencion lugaresdeatencion);

	Collection<Catnivel> getCatNivel();

	Collection<Catzonacardinal> getCatZonaCardinal();

	Cattipounidad getTipoUnidadById(int parseInt);

	Catzonacardinal getZonaCardinalById(int parseInt);

	void save(Lugaresdeatencion lugaresdeatencion);

	Collection<Cattipounidad> getCatTipoUnidad(int parseInt);

	Lugaresdeatencion getLugarAtencionById(int parseInt);

	Collection<Lugaresdeatencion> getLugarAtencionByDesc(String busqueda);

	Lugaresdeatencion getLugarAtencionByCodigo(int i);

	Collection<Lugaresdeatencion> getLugaresAtencion();

	Lugaresdeatencion getLugarAtencionByDescripcion(String descripcion);

	Lugaresdeatencion getLugarAtencionByClave(String claveDeLaUnidad);

	Object getCodigo();

	Codigolugaratencion getCodigoLugarAtencion();

	Lugaresdeatencion existeDireccionLugarAtencion(LugarAtencionForm lugarAtencionForm);

	void delete(Lugaresdeatencion lugaresdeatencion);

	Collection<Lugaresdeatencion> getLugarAtencionByCodigos(String busqueda);

	Collection<Roles> getRoles();

	Roles getRolById(int parseInt);

	void saveLugaresAtencionRoles(Lugaresdeatencionroles lugaresdeatencionroles);

	void deleteLugaresAtencionRoles(Integer lugarDeAtencionId);

	Collection<Lugaresdeatencionroles> getLugaresAtencionRolesByIdLugarAtencion(Integer lugarDeAtencionId);

}
