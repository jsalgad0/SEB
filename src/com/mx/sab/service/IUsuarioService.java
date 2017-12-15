package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.UsuarioForm;
import com.mx.sab.model.Catespecialidadesmedicas;
import com.mx.sab.model.Modulos;
import com.mx.sab.model.Usuarios;
import com.mx.sab.vo.AseguradoresVo;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.RolesVo;
import com.mx.sab.vo.UsuariosVo;


public interface IUsuarioService {

	Collection<Modulos> getModulos();
	Collection<Catespecialidadesmedicas> getEspecialidades();
	void save(UsuarioForm usuarioForm) throws Exception;
	Collection<Usuarios> getUsuarios(UsuarioForm usuarioForm);
	void getUsuarioForm(UsuarioForm usuarioForm) throws Exception;
	Usuarios getUsuarioById(int idUsuario);
	Collection<UsuariosVo> getUsuarios(String busqueda, int page);
	Collection<RolesVo> getRolesUsuario(String id, String idUsuario);
	void update(UsuarioForm usuarioForm)throws Exception;
	Collection<AutocompleteVo> aseguradores(String busqueda);
	Collection<AseguradoresVo> aseguradoresById(String busqueda);
	void delete(UsuarioForm usuarioForm);
	
}
