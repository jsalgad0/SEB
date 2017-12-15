package com.mx.sab.dao.impl;

import java.util.Collection;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IUsuarioDao;
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

@Service
@Log4j2
@Repository("usuarioDao")
@Transactional
public class UsuariosDaoImpl implements IUsuarioDao {
	
	private static String GET_USUARIOS = "FROM Usuarios ORDER BY usuarioId";
	private static String GET_USUARIOS_BY_ID = "FROM Usuarios WHERE usuarioId = ?";
	private static String GET_USUARIOS_BY_RFC = "FROM Usuarios WHERE rfc = :rfc";
	private static String GET_MODULOS = "FROM Modulos WHERE Activo = 1";
	private static String GET_ESPECIALIDADES = "FROM Catespecialidadesmedicas ";
	private static String GET_SEXO = "SELECT * FROM Catsexos WHERE SexoId = ?";
	private static String GET_ESTADO = "SELECT * FROM Catestados WHERE EstadoId = ?";
	private static String GET_MENU = "SELECT * FROM Menu WHERE MenuId = ?";
	private static String GET_MENU_ROL = "FROM Menurol WHERE RolId = ?";
	private static String GET_ROL = "FROM Roles WHERE rolId = ?";
	private static String GET_ESPECIALIDADES_ID = "FROM Catespecialidadesmedicas WHERE especialidadMedicaId = ?";
	private static String GET_TIPO_IDENTIFICADOR = "SELECT * FROM Cattipoidentificador WHERE TipoIdentificadorId = ?";
	private static String GET_USUARIOS_BY_LUGAR_ATENCION = "FROM Usuariolugaratencion u WHERE u.lugaresdeatencion.codigoLugarAtencion = ?";
	private static String GET_CONFIGURACION = "FROM Catconfig WHERE Llave LIKE 'email%' ";
	private static String GET_USUARIOS_BUSQUEDA = "FROM Usuarios WHERE rfc LIKE ? ORDER BY usuarioId ASC";
	private static String GET_COUNT_USUARIOS = "SELECT count(*) FROM usuarios WHERE rfc like ?";
	private static String GET_USUARIOS_ROL = "FROM Usuariorol WHERE usuarioId = ?";
	private static String DELETE_USU_MENU = "DELETE FROM Usuariomenu WHERE usuarioId = ?";
	private static String DELETE_USU_ROL = "DELETE FROM Usuariorol WHERE usuarioId = ?";
	private static String DELETE_USU_LUG = "DELETE FROM Usuariolugaratencion WHERE usuarioId = ?";
	private static String DELETE_USU_ESP = "DELETE FROM Usuarioespecialidades WHERE usuarioId = ?";
	private static String DELETE_USU_IDE = "DELETE FROM Usuariotipoidentificador WHERE usuarioId = ?";
	private static String DELETE_USU_ASE = "DELETE FROM Usuarioasegurador WHERE usuarioId = ?";	
	private static String GET_USUARIO_BY_TIPO_IDENTIFICADOR = "FROM Usuariotipoidentificador WHERE cattipoidentificador.tipoIdentificadorId = ? AND valor = ?";
	private static String GET_ROLES_BY_MODULO = "FROM Roles WHERE modulos.moduloId = ?";
	private static String GET_USUARIO_BY_RFC = "FROM Usuarios WHERE rfc = ?";
	private static String GET_CAT_ESTATUS_USUARIO_BY_ID = "FROM Catestatususuario WHERE id = ?";
	private static String GET_CAT_VIGENCIA_BY_ID = "FROM Catvigencia WHERE vigenciaId = ?";
	private static String GET_CAT_MODIFICACION_USUARIO_BY_ID = "FROM Catmodificacionusuario WHERE id = ?";
	private static String GET_USUARIO_VIGENCIA_LUGAR_ATENCION_BY_ID_USUARIO = "FROM Usuariovigencialugaratencion WHERE usuarios.usuarioId = ? AND lugaresdeatencion.lugarDeAtencionId = ?";
	private static String GET_COUNT_USUARIOS_MANTENIMIENTO = "SELECT count(*) FROM usuarios AS u, usuariovigencialugaratencion AS ul WHERE u.RFC like ? AND u.Nombre like ? AND u.ApellidoPaterno like ? AND u.ApellidoMaterno like ? AND ul.LugarAtencionId = ? AND ul.UsuarioId = u.UsuarioId AND ul.VigenciaId <> 3";
	private static String GET_USUARIOS_LUGAR_ATENCION_BUSQUEDA_MANTENIMIENTO = "FROM Usuariovigencialugaratencion WHERE usuarios.rfc LIKE ? AND usuarios.nombre like ? AND usuarios.apellidoPaterno like ? AND usuarios.apellidoMaterno like ? AND lugaresdeatencion.lugarDeAtencionId = ? AND catvigencia.vigenciaId <> 3 ORDER BY usuarios.usuarioId ASC";
	private static String GET_USUARIOS_ROL_BY_LUGAR_ATENCION = "FROM Usuariorol WHERE usuarios.usuarioId = ? AND lugaresdeatencion.lugarDeAtencionId = ?";
	private static String GET_USUARIO_ESPECIALIDADES_BY_ID = "FROM Usuarioespecialidades WHERE id = ?";
	private static String DELETE_ROLES_BY_ID_LUGAR_ATENCION = "DELETE FROM Usuariorol WHERE usuarios.usuarioId = ? AND lugaresdeatencion.lugarDeAtencionId = ?";
	private static String GET_USUARIOS_ESPECIALIDADES_BY_ID_USUARIO = "FROM Usuarioespecialidades WHERE usuarios.usuarioId = ?";
	private static String GET_USUARIOS_LUGAR_ATENCION_PENDIENTE_CLAVE = "FROM Usuariolugaratencion WHERE usuarios.catestatususuario.id = 1 AND lugaresdeatencion.lugarDeAtencionId = ?";
	private static String GET_USUARIOS_LUGAR_ATENCION_DECLARACION_PENDIENTE = "FROM Usuariolugaratencion WHERE usuarios.catestatususuario.id = 3 AND lugaresdeatencion.lugarDeAtencionId = ?";
	private static String GET_USUARIOS_BLOQUEADOS = "FROM Usuariovigencialugaratencion WHERE catvigencia.vigenciaId = 3 AND lugaresdeatencion.lugarDeAtencionId = ?";
	private static String GET_USUARIOS_LUGAR_ATENCION_AUTORIZACION_PENDIENTE = "FROM Usuariolugaratencion WHERE usuarios.catestatususuario.id = 1 AND lugaresdeatencion.lugarDeAtencionId = ?";
	private static String GET_PERMISO_ESPECIAL_BY_USER_ID = "FROM Permisoespecial WHERE usuariosByUsuarioId.usuarioId = ? AND lugaresdeatencion.lugarDeAtencionId = ? ORDER BY permisoEspecialId DESC";
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public void save(Usuarios usuarios) {
		//log.info("save");
		getSession().save(usuarios);
		getSession().refresh(usuarios);
	}
	
	@Override
	public Collection<Usuarios> getUsuarios() {
		//log.info("getUsuarios");
		Query query = getSession().createQuery(GET_USUARIOS);
		List<Usuarios> usuarios = query.list(); 
		return usuarios;
	}
	
	@Override
	public Collection<Modulos> getModulos() {
		//log.info("getModulos");
		Query query = getSession().createQuery(GET_MODULOS);
		List<Modulos> modulos = query.list(); 
		return modulos;
	}
	
	@Override
	public Collection<Catespecialidadesmedicas> getEspecialidades() {
		//log.info("getEspecialidades");
		Query query = getSession().createQuery(GET_ESPECIALIDADES);
		List<Catespecialidadesmedicas> especialidades = query.list(); 
		return especialidades;
	}

	@Override
	public Catsexos getSexoId(int i) {
		//log.info("getSexos");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_SEXO);
		sqlQuery.setInteger(0, i);
		sqlQuery.addEntity(Catsexos.class);
		Catsexos catsexos = (Catsexos) sqlQuery.uniqueResult(); 
		return catsexos;
	}

	@Override
	public Catestados getEstadoId(int i) {
		//log.info("getEstado");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_ESTADO);
		sqlQuery.setInteger(0, i);
		sqlQuery.addEntity(Catestados.class);
		Catestados catestados = (Catestados) sqlQuery.uniqueResult(); 
		return catestados;
	}

	@Override
	public void saveUsuarioMenu(Usuariomenu usuariomenu) {
		//log.info("saveUsuarioMenu");
		getSession().save(usuariomenu);		
	}

	@Override
	public Menu getMenuId(int i) {
		//log.info("getMenu");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_MENU);
		sqlQuery.setInteger(0, i);
		sqlQuery.addEntity(Menu.class);
		Menu menu = (Menu) sqlQuery.uniqueResult(); 
		return menu;
	}
	
	@Override
	public Roles getRolesId(int i) {
		//log.info("getRoles");
		Query query = getSession().createQuery(GET_ROL);
		query.setInteger(0, i); 
		return (Roles) query.uniqueResult();
	}

	@Override
	public void saveUsuarioRol(Usuariorol usuariorol) {
		//log.info("save usuario rol");
		getSession().save(usuariorol);
	}
	
	@Override
	public void saveUsuarioLugarAtencion(Usuariolugaratencion usuariolugaratencion) {
		//log.info("save usuario lugar atencion");
		getSession().save(usuariolugaratencion);
	}

	@Override
	public Catespecialidadesmedicas getEspecialidadesId(int i) {
		//log.info("getEspecialidadesId");
		Query query = getSession().createQuery(GET_ESPECIALIDADES_ID);
		query.setInteger(0, i);  
		return (Catespecialidadesmedicas) query.uniqueResult();
	}

	@Override
	public Cattipoidentificador getTipoIdentificador(int i) {
		//log.info("getTipoIdentificador");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_TIPO_IDENTIFICADOR);
		sqlQuery.setInteger(0, i);
		sqlQuery.addEntity(Cattipoidentificador.class);
		Cattipoidentificador tipo = (Cattipoidentificador) sqlQuery.uniqueResult(); 
		return tipo;
	}

	@Override
	public void saveUsuarioEspecialidad(Usuarioespecialidades usuarioespecialidades) {
		//log.info("save Usuario especialidad");
		getSession().save(usuarioespecialidades);
	}

	@Override
	public void saveUsuarioTipoIdentidicador(Usuariotipoidentificador usuariotipoidentificador) {
		//log.info("save Usuario tipo identificador");
		getSession().save(usuariotipoidentificador);		
	}

	@Override
	public void saveUsuarioAsegurador(Usuarioasegurador usuarioasegurador) {
		//log.info("save Usuario asegurador");
		getSession().save(usuarioasegurador);		
	}

	@Override
	public Collection<Catconfig> getConfiguracion() {
		//log.info("getConfiguracion");
		Query query = getSession().createQuery(GET_CONFIGURACION);
		List<Catconfig> configuracion = query.list(); 
		return configuracion;
	}

	@Override
	public Usuarios getValidacionRfc(String rfc) {
		//log.info("getValidacionRfc");
		Query query = getSession().createQuery(GET_USUARIOS_BY_RFC);
		query.setString("rfc", rfc);
		Usuarios usu = (Usuarios) query.uniqueResult();
		return usu;
	}

	@Override
	public Collection<Usuariolugaratencion> getUsuariosByLugarAtencion(int tx_Marca) {
		//log.info("getUsuariosByLugarAtencion");
		Query query = getSession().createQuery(GET_USUARIOS_BY_LUGAR_ATENCION);
		query.setInteger(0, tx_Marca);
		List<Usuariolugaratencion> usuarios = query.list(); 
		return usuarios;
	}

	@Override
	public Collection<Usuarios> getUsuarios(String busqueda, int inicio, int fin) {
		//log.info("getAseguradores");
		Query query = getSession().createQuery(GET_USUARIOS_BUSQUEDA);
		query.setString(0, "%"+busqueda.trim()+"%");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		List<Usuarios> usuarios = query.list();
		return usuarios;
	}
	
	@Override
	public int getUsuariosCount(String busqueda) {
		//log.info("getUsuariosCount");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_COUNT_USUARIOS);
		sqlQuery.setString(0, "%"+busqueda.trim()+"%");
		return ((Number) sqlQuery.uniqueResult()).intValue(); 
	}

	@Override
	public Usuarios getUsuarioById(int usuarioId) {
		//log.info("getUsuarioById");
		Query query = getSession().createQuery(GET_USUARIOS_BY_ID);
		query.setInteger(0, usuarioId);
		Usuarios usuarios = (Usuarios) query.uniqueResult(); 
		return usuarios;
	}

	@Override
	public Collection<Usuariorol> getUsuariosRol(int usuarioId) {
		//log.info("getUsuarioRol");
		Query query = getSession().createQuery(GET_USUARIOS_ROL);
		query.setInteger(0, usuarioId);
		List<Usuariorol> usuarioRol = query.list(); 
		return usuarioRol;
	}
	
	@Override
	public void update(Usuarios usuarios) {
		//log.info("update");
		getSession().merge(usuarios);
		getSession().refresh(usuarios);
	}

	@Override
	public void delete(int idUsuario, String modulo, Collection<String> rol) {
		Query query = getSession().createQuery(DELETE_USU_MENU);
		query.setInteger(0, idUsuario);
		query.executeUpdate();
		Query query2 = getSession().createQuery(DELETE_USU_ROL);
		query2.setInteger(0, idUsuario);
		query2.executeUpdate();
		Query query6 = getSession().createQuery(DELETE_USU_ASE);
		query6.setInteger(0, idUsuario);
		query6.executeUpdate();
		Query query4 = getSession().createQuery(DELETE_USU_ESP);
		query4.setInteger(0, idUsuario);
		query4.executeUpdate();
		Query query5 = getSession().createQuery(DELETE_USU_IDE);
		query5.setInteger(0, idUsuario);
		query5.executeUpdate();
		Query query7 = getSession().createQuery(DELETE_USU_LUG);
		query7.setInteger(0, idUsuario);
		query7.executeUpdate();
	}
	
	@Override
	public Collection<Menurol> getMenurol(int rolId) {
		//log.info("getUsuarioRol");
		Query query = getSession().createQuery(GET_MENU_ROL);
		query.setInteger(0, rolId);
		List<Menurol> menurol = query.list(); 
		return menurol;
	}

	@Override
	public Collection<Usuarios> getUsuariosMedicos(int id, Integer lugarDeAtencionId) {
		SQLQuery query = getSession().createSQLQuery("SELECT * FROM usuarios WHERE UsuarioId IN (SELECT UsuarioId FROM usuariorol WHERE RolId = ?) AND UsuarioId IN (SELECT UsuarioId FROM usuariolugaratencion WHERE LugarDeAtencionId = ?)");
		query.addEntity(Usuarios.class);
		query.setInteger(0, id);
		query.setInteger(1, lugarDeAtencionId);
		return query.list();
	}

	@Override
	public Usuariotipoidentificador getUsuarioByIdentificador(int id, String cveMedico) {
		//log.info("getUsuarioRol");
		Query query = getSession().createQuery(GET_USUARIO_BY_TIPO_IDENTIFICADOR);
		query.setInteger(0, id);
		query.setString(1, cveMedico); 
		return (Usuariotipoidentificador) query.uniqueResult();
	}

	@Override
	public Collection<Roles> getRolesByModulo(Integer moduloId) {
		//log.info("getRolesByModulo");
		Query query = getSession().createQuery(GET_ROLES_BY_MODULO);
		query.setInteger(0, moduloId); 
		return query.list();
	}

	@Override
	public Usuarios getUsuarioByRfc(String rfc) {
		//log.info("getRolesByModulo");
		Query query = getSession().createQuery(GET_USUARIO_BY_RFC);
		query.setString(0, rfc); 
		return (Usuarios) query.uniqueResult();
	}

	@Override
	public Catestatususuario getCatEstatusUsuarioById(int id) {
		//log.info("getCatEstatusUsuarioById");
		Query query = getSession().createQuery(GET_CAT_ESTATUS_USUARIO_BY_ID);
		query.setInteger(0, id); 
		return (Catestatususuario) query.uniqueResult();
	}

	@Override
	public Catvigencia getCatVigenciaById(int id) {
		//log.info("getCatVigencia");
		Query query = getSession().createQuery(GET_CAT_VIGENCIA_BY_ID);
		query.setInteger(0, id); 
		return (Catvigencia) query.uniqueResult();
	}

	@Override
	public Catmodificacionusuario getCatModificacionUsuarioById(int id) {
		//log.info("getCatModificacionUsuarioById");
		Query query = getSession().createQuery(GET_CAT_MODIFICACION_USUARIO_BY_ID);
		query.setInteger(0, id); 
		return (Catmodificacionusuario) query.uniqueResult();	
	}

	@Override
	public void saveUsuarioAuditoria(Usuarioauditoria usuarioauditoria) {
		//log.info("saveUsuarioAuditoria");
		getSession().save(usuarioauditoria);	
	}

	@Override
	public void saveUsuarioVigenciaLugarAtencion(Usuariovigencialugaratencion usuariovigencialugaratencion) {
		//log.info("saveUsuarioVigenciaLugarAtencion");
		getSession().save(usuariovigencialugaratencion);			
	}

	@Override
	public Usuariovigencialugaratencion getUsuarioVigenciaLugarAtencionByIdUsuario(Integer usuarioId, Integer idLugarAtencion) {
		//log.info("getUsuarioVigenciaLugarAtencionByIdUsuario");
		Query query = getSession().createQuery(GET_USUARIO_VIGENCIA_LUGAR_ATENCION_BY_ID_USUARIO);
		query.setInteger(0, usuarioId);
		query.setInteger(1, idLugarAtencion);
		return (Usuariovigencialugaratencion) query.uniqueResult();	
	}

	@Override
	public void updateUsuarioVigenciaLugarAtencion(Usuariovigencialugaratencion usuariovigencialugaratencion) {
		//log.info("updateUsuarioVigenciaLugarAtencion");
		getSession().merge(usuariovigencialugaratencion);		
	}

	@Override
	public int getUsuariosCount(String busquedaRfc, String busquedaNombre, String busquedaApellidoPaterno, String busquedaApellidoMaterno, int lugarAtencionId) {
		//log.info("getUsuariosCount");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_COUNT_USUARIOS_MANTENIMIENTO);
		sqlQuery.setString(0, "%"+busquedaRfc.trim()+"%");
		sqlQuery.setString(1, "%"+busquedaNombre.trim()+"%");
		sqlQuery.setString(2, "%"+busquedaApellidoPaterno.trim()+"%");
		sqlQuery.setString(3, "%"+busquedaApellidoMaterno.trim()+"%");
		sqlQuery.setInteger(4, lugarAtencionId);
		return ((Number) sqlQuery.uniqueResult()).intValue(); 
	}

	@Override
	public Collection<Usuariovigencialugaratencion> getUsuarioLugarAtencion(String busquedaRfc, String busquedaNombre,String busquedaApellidoPaterno, String busquedaApellidoMaterno,int inicio, int fin, Integer lugarDeAtencionId) {
		//log.info("getUsuarios");
		Query query = getSession().createQuery(GET_USUARIOS_LUGAR_ATENCION_BUSQUEDA_MANTENIMIENTO);
		query.setString(0, "%"+busquedaRfc.trim()+"%");
		query.setString(1, "%"+busquedaNombre.trim()+"%");
		query.setString(2, "%"+busquedaApellidoPaterno.trim()+"%");
		query.setString(3, "%"+busquedaApellidoMaterno.trim()+"%");
		query.setInteger(4, lugarDeAtencionId);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.list();
	}

	@Override
	public Collection<Usuariorol> getUsuariosRolByLugar(Integer usuarioId, Integer lugarDeAtencionId) {
		//log.info("getUsuarios");
		Query query = getSession().createQuery(GET_USUARIOS_ROL_BY_LUGAR_ATENCION);
		query.setInteger(0, usuarioId);
		query.setInteger(1, lugarDeAtencionId);
		return query.list();	
	}

	@Override
	public Usuarioespecialidades getUsuarioEspecialidadesById(int id1) {
		//log.info("getUsuarioEspecialidadesById");
		Query query = getSession().createQuery(GET_USUARIO_ESPECIALIDADES_BY_ID);
		query.setInteger(0, id1);
		return (Usuarioespecialidades) query.uniqueResult();
	}

	@Override
	public void removeUsuarioEspecialidades(Usuarioespecialidades usuarioespecialidades) {
		//log.info("removeUsuarioEspecialidades");
		getSession().delete(usuarioespecialidades);
	}

	@Override
	public void updateUsuarioEspecialidades(Usuarioespecialidades usuarioespecialidades) {
		//log.info("updateUsuarioEspecialidades");
		getSession().merge(usuarioespecialidades);
	}

	@Override
	public void deleteRolesByIdLugarAtencion(Integer usuarioId,Integer lugarDeAtencionId) {
		//log.info("deleteRolesByLugarAtencion");
		Query query = getSession().createQuery(DELETE_ROLES_BY_ID_LUGAR_ATENCION);
		query.setInteger(0, usuarioId);
		query.setInteger(1, lugarDeAtencionId);
		query.executeUpdate();
	}

	@Override
	public Collection<Usuarioespecialidades> getUsuarioEspecialidadesByIdUsuario(Integer usuarioId) {
		//log.info("getUsuarioEspecialidadesByIdUsuario");
		Query query = getSession().createQuery(GET_USUARIOS_ESPECIALIDADES_BY_ID_USUARIO);
		query.setInteger(0, usuarioId);
		return query.list();
	}

	@Override
	public Collection<Usuariolugaratencion> getUsuariosPendienteClave(Integer idLugarAtencion) {
		//log.info("getUsuariosPendienteClave");
		Query query = getSession().createQuery(GET_USUARIOS_LUGAR_ATENCION_PENDIENTE_CLAVE);
		query.setInteger(0, idLugarAtencion);
		return query.list();
	}

	@Override
	public Collection<Usuariolugaratencion> getUsuariosDeclaracionPendiente(Integer lugarDeAtencionId) {
		//log.info("getUsuariosDeclaracionPendiente");
		Query query = getSession().createQuery(GET_USUARIOS_LUGAR_ATENCION_DECLARACION_PENDIENTE);
		query.setInteger(0, lugarDeAtencionId);
		return query.list();
	}

	@Override
	public Collection<Usuariovigencialugaratencion> getUsuariosBloqueados(Integer lugarDeAtencionId) {
		//log.info("getUsuariosBloqueados");
		Query query = getSession().createQuery(GET_USUARIOS_BLOQUEADOS);
		query.setInteger(0, lugarDeAtencionId);
		return query.list();
	}

	@Override
	public void saveLoginLog(Loginlog loginlog) {
		//log.info("saveLoginLog");
		getSession().save(loginlog);	
	}

	@Override
	public Collection<Usuariolugaratencion> getUsuariosAutorizacionPendiente(Integer lugarDeAtencionId) {
		//log.info("DAO getUsuariosAutorizacion");
		Query query = getSession().createQuery(GET_USUARIOS_LUGAR_ATENCION_AUTORIZACION_PENDIENTE);
		query.setInteger(0, lugarDeAtencionId);
		return query.list();
	}
	
	@Override
	public Collection<Usuariolugaratencion> getUsuariosAutorizacionPendiente(int inicio, int fin, Integer lugarDeAtencionId) {
		//log.info("DAO getUsuariosAutorizacion");
		Query query = getSession().createQuery(GET_USUARIOS_LUGAR_ATENCION_AUTORIZACION_PENDIENTE);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		query.setInteger(0, lugarDeAtencionId);
		return query.list();
	}
	
	@Override
	public Permisoespecial getPermisoEspecialByIdUsuario(Integer usuarioId, Integer lugarDeAtencionId) {
		//log.info("DAO getUsuarioPermisoEspecial");
		Query query = getSession().createQuery(GET_PERMISO_ESPECIAL_BY_USER_ID);
		query.setInteger(0, usuarioId);
		query.setInteger(1, lugarDeAtencionId);
		query.setMaxResults(1);
		return (Permisoespecial)query.uniqueResult();
	}
	
}
