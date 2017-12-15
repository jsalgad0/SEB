package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Catconfig;
import com.mx.sab.model.Catespecialidadesmedicas;
import com.mx.sab.model.Catestados;
import com.mx.sab.model.Catestatususuario;
import com.mx.sab.model.Catmodificacionusuario;
import com.mx.sab.model.Catsexos;
import com.mx.sab.model.Cattipoidentificador;
import com.mx.sab.model.Catvigencia;
import com.mx.sab.model.Loginlog;
import com.mx.sab.model.Menu;
import com.mx.sab.model.Menurol;
import com.mx.sab.model.Modulos;
import com.mx.sab.model.Permisoespecial;
import com.mx.sab.model.Roles;
import com.mx.sab.model.Usuarioasegurador;
import com.mx.sab.model.Usuarioauditoria;
import com.mx.sab.model.Usuarioespecialidades;
import com.mx.sab.model.Usuariolugaratencion;
import com.mx.sab.model.Usuariomenu;
import com.mx.sab.model.Usuariorol;
import com.mx.sab.model.Usuarios;
import com.mx.sab.model.Usuariotipoidentificador;
import com.mx.sab.model.Usuariovigencialugaratencion;


public interface IUsuarioDao {

	void save(Usuarios usuarios);
	void saveUsuarioMenu(Usuariomenu usuariomenu);
	void saveUsuarioRol(Usuariorol usuariorol);
	void saveUsuarioLugarAtencion(Usuariolugaratencion usuariolugaratencion);
	void saveUsuarioEspecialidad(Usuarioespecialidades usuarioespecialidades);
	void saveUsuarioTipoIdentidicador(Usuariotipoidentificador usuariotipoidentificador);
	void saveUsuarioAsegurador(Usuarioasegurador usuarioasegurador);
	Collection<Usuarios> getUsuarios();
	Collection<Modulos> getModulos();
	Collection<Catespecialidadesmedicas> getEspecialidades();
	Catsexos getSexoId(int i);
	Catestados getEstadoId(int i);
	Menu getMenuId(int i);
	Collection<Menurol> getMenurol(int i);
	Roles getRolesId(int i);
	Catespecialidadesmedicas getEspecialidadesId(int i);
	Cattipoidentificador getTipoIdentificador(int i);
	Collection<Catconfig> getConfiguracion();
	Usuarios getValidacionRfc(String rfc);
	Collection<Usuariolugaratencion> getUsuariosByLugarAtencion(int tx_Marca);
	Collection<Usuarios> getUsuarios(String busqueda, int inicio, int fin);
	int getUsuariosCount(String busqueda);
	Usuarios getUsuarioById(int usuarioId);
	Collection<Usuariorol> getUsuariosRol(int usuarioId);
	void update(Usuarios usuarios);
	void delete(int idUsuario, String modulo, Collection<String> rol);
	Collection<Usuarios> getUsuariosMedicos(int id, Integer lugarDeAtencionId);
	Usuariotipoidentificador getUsuarioByIdentificador(int id, String cveMedico);
	Collection<Roles> getRolesByModulo(Integer moduloId);
	Usuarios getUsuarioByRfc(String rfc);
	Catestatususuario getCatEstatusUsuarioById(int id);
	Catvigencia getCatVigenciaById(int id);
	Catmodificacionusuario getCatModificacionUsuarioById(int id);
	void saveUsuarioAuditoria(Usuarioauditoria usuarioauditoria);
	void saveUsuarioVigenciaLugarAtencion(Usuariovigencialugaratencion usuariovigencialugaratencion);
	Usuariovigencialugaratencion getUsuarioVigenciaLugarAtencionByIdUsuario(Integer usuarioId, Integer idLugarAtencion);
	void updateUsuarioVigenciaLugarAtencion(Usuariovigencialugaratencion usuariovigencialugaratencion);
	int getUsuariosCount(String busquedaRfc, String busquedaNombre,String busquedaApellidoPaterno, String busquedaApellidoMaterno, int lugarAtencionId);
	Collection<Usuariovigencialugaratencion> getUsuarioLugarAtencion(String busquedaRfc, String busquedaNombre,String busquedaApellidoPaterno, String busquedaApellidoMaterno,int inicio, int fin, Integer lugarDeAtencionId);
	Collection<Usuariorol> getUsuariosRolByLugar(Integer usuarioId,Integer lugarDeAtencionId);
	Usuarioespecialidades getUsuarioEspecialidadesById(int id1);
	void removeUsuarioEspecialidades(Usuarioespecialidades usuarioespecialidades);
	void updateUsuarioEspecialidades(Usuarioespecialidades usuarioespecialidades);
	void deleteRolesByIdLugarAtencion(Integer usuarioId, Integer lugarDeAtencionId);
	Collection<Usuarioespecialidades> getUsuarioEspecialidadesByIdUsuario(Integer usuarioId);
	Collection<Usuariolugaratencion> getUsuariosPendienteClave(Integer integer);
	Collection<Usuariolugaratencion> getUsuariosDeclaracionPendiente(Integer lugarDeAtencionId);
	Collection<Usuariovigencialugaratencion> getUsuariosBloqueados(Integer lugarDeAtencionId);
	void saveLoginLog(Loginlog loginlog);
	Collection<Usuariolugaratencion> getUsuariosAutorizacionPendiente(Integer lugarDeAtencionId);
	Collection<Usuariolugaratencion> getUsuariosAutorizacionPendiente(int inicio, int fin, Integer lugarDeAtencionId);
	Permisoespecial getPermisoEspecialByIdUsuario(Integer usuarioId, Integer lugarDeAtencionId);
}
