package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.LugarAtencionForm;
import com.mx.sab.model.Catnivel;
import com.mx.sab.model.Catzonacardinal;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Roles;
import com.mx.sab.vo.CatTipoUnidadVo;
import com.mx.sab.vo.LugarAtencionVo;

public interface ILugarAtencionService {

	void delete(LugarAtencionForm lugarAtencionForm);

	void save(LugarAtencionForm lugarAtencionForm);

	void update(LugarAtencionForm lugarAtencionForm);

	Collection<CatTipoUnidadVo> getCatTipoUnidad(String idTipoUnidad);

	void getLugarAtencion(LugarAtencionForm lugarAtencionForm);

	Collection<Lugaresdeatencion> getLugaresAtencion();

	Lugaresdeatencion getLugaresAtencionByClave(int tx_Marca);

	Collection<LugarAtencionVo> getLugaresAtencionByDescripcion(String busqueda);

	Collection<LugarAtencionVo> getLugaresAtencionByCodigo(String busqueda);

	Collection<Roles> getRoles();

	void verificarRolesLugarAtencion(LugarAtencionForm lugarAtencionForm);

}
