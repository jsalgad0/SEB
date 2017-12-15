package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.RegistroLectoresForm;
import com.mx.sab.model.Lectores;
import com.mx.sab.model.Propietarioslector;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.LectoresVo;
import com.mx.sab.vo.LugarAtencionVo;
import com.mx.sab.vo.PropietarioLectorVo;

public interface IRegistroLectoresService {

	Collection<LectoresVo> getLectores(String busqueda, int page);

	Collection<Lectores> getLectores(RegistroLectoresForm registroLectoresForm);

	void save(RegistroLectoresForm registroLectoresForm);

	void update(RegistroLectoresForm registroLectoresForm);

	void getLector(RegistroLectoresForm registroLectoresForm);

	Collection<AutocompleteVo> getLugarAtencion(String busqueda);

	Collection<PropietarioLectorVo> getPropietariosLector(String busqueda);

	Collection<LugarAtencionVo> lugarAtencionByCodigo(String busqueda);

	Collection<LugarAtencionVo> lugarAtencionById(String busqueda);

	Collection<PropietarioLectorVo> propietarioLectorById(String busqueda);

	Collection<PropietarioLectorVo> propietarioLectorByPropietario(String busqueda);

	void delete(RegistroLectoresForm registroLectoresForm);

	Propietarioslector getPropietariosLectorByRfc(String rfc);

	void savePropietarioLector(Propietarioslector propietarioslector);

}
