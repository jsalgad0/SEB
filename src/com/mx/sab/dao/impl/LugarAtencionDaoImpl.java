package com.mx.sab.dao.impl;

import java.util.Collection;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.form.LugarAtencionForm;
import com.mx.sab.model.Catnivel;
import com.mx.sab.model.Cattipounidad;
import com.mx.sab.model.Catzonacardinal;
import com.mx.sab.model.Codigolugaratencion;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Lugaresdeatencionroles;
import com.mx.sab.model.Roles;

@Log4j2
@Transactional
@Service
public class LugarAtencionDaoImpl implements ILugarAtencionDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_TIPO_UNIDAD_BY_ID = "SELECT * FROM cattipounidad WHERE TiposUnidadId = ?";
	private static String GET_ZONA_CARDINAL_BY_ID = "SELECT * FROM catzonacardinal WHERE ZonaCardinalId = ?";
	private static String GET_CAT_NIVEL = "FROM Catnivel";
	private static String GET_CAT_ZONA_CARDINAL = "FROM Catzonacardinal";	
	private static String GET_CAT_TIPO_UNIDAD = "SELECT * FROM cattipounidad WHERE NivelId = ?";
	private static String GET_LUGAR_ATENCION_BY_ID = "FROM Lugaresdeatencion WHERE lugarDeAtencionId = ?";
	
	private static String GET_LUGAR_ATENCION = "FROM Lugaresdeatencion WHERE descripcion LIKE ? ORDER BY lugarDeAtencionId ASC";
	private static String GET_LUGAR_ATENCION_BY_DESCRIPCION = "FROM Lugaresdeatencion WHERE descripcion = ?";
	private static String GET_LUGAR_ATENCION_BY_CODIGO = "FROM Lugaresdeatencion WHERE codigoLugarAtencion = ? AND activo = 1 ORDER BY lugarDeAtencionId ASC";
	private static String GET_COUNT_LUGAR_ATENCION = "SELECT count(*) FROM lugaresdeatencion WHERE Descripcion like ? AND Activo = 1";
	private static String GET_ALL_LUGAR_ATENCION = "FROM Lugaresdeatencion WHERE activo = 1 ORDER BY lugarDeAtencionId ASC";
	private static String GET_LUGAR_ATENCION_BY_CLAVE = "FROM Lugaresdeatencion WHERE claveDeLaUnidad = ? AND activo = 1";
	private static String GET_CODIGO = "SELECT ClaveDeLaUnidad FROM lugaresdeatencion ORDER BY LugarDeAtencionId DESC LIMIT 1";
	private static String GET_CODIGO_LUGAR_ATENCION= "FROM Codigolugaratencion";
	private static String GET_LUGAR_ATENCION_BY_DIRECCION = "FROM Lugaresdeatencion WHERE calleyNo = ? AND numInterno = ? AND numExterno = ? AND catestados.estadoId = ? AND catmunicipios.municipioId = ? AND catcolonias.coloniaId = ? AND cp = ?";
	private static String GET_LUGAR_ATENCION_BY_CODIGOS = "FROM Lugaresdeatencion WHERE codigoLugarAtencion LIKE ? ORDER BY lugarDeAtencionId ASC";
	private static String GET_ROLES = "FROM Roles WHERE modulos.moduloId = 3 ORDER BY rolId ASC";
	private static String GET_ROL_BY_ID = "FROM Roles WHERE rolId = ?";
	private static String DELETE_LUGARES_ATENCION_ROLES_BY_ID = "DELETE FROM Lugaresdeatencionroles WHERE lugaresdeatencion.lugarDeAtencionId = ?";
	private static String GET_LUGAR_DE_ATENCION_ROLES_BY_ID_LUGAR_ATENCION = "FROM Lugaresdeatencionroles WHERE lugaresdeatencion.lugarDeAtencionId = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}
	
	@Override
	public int getLugarAtencionCount(String busqueda) {
		//log.info("getLugarAtencionCount");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_COUNT_LUGAR_ATENCION);
		sqlQuery.setString(0, "%"+busqueda.trim()+"%");
		return ((Number) sqlQuery.uniqueResult()).intValue(); 
	}

	@Override
	public Collection<Lugaresdeatencion> getLugarAtencion(String busqueda,int inicio, int fin) {
		//log.info("getLugarAtencion");
		Query query = getSession().createQuery(GET_LUGAR_ATENCION);
		query.setString(0, "%"+busqueda.trim()+"%");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		List<Lugaresdeatencion> lugaresdeatencions = query.list();
		return lugaresdeatencions;
	}

	@Override
	public void update(Lugaresdeatencion lugaresdeatencion) {
		//log.info("update");
		getSession().merge(lugaresdeatencion);
		
	}

	@Override
	public Collection<Catnivel> getCatNivel() {
		//log.info("getCatNivel");
		Query query = getSession().createQuery(GET_CAT_NIVEL);
		List<Catnivel> catnivels = query.list(); 
		return catnivels;
	}

	@Override
	public Collection<Catzonacardinal> getCatZonaCardinal() {
		//log.info("getCatZonaCardinal");
		Query query = getSession().createQuery(GET_CAT_ZONA_CARDINAL);
		List<Catzonacardinal> catzonacardinals = query.list(); 
		return catzonacardinals;
	}

	@Override
	public Cattipounidad getTipoUnidadById(int i) {
		//log.info("getTipoUnidadById");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_TIPO_UNIDAD_BY_ID);
		sqlQuery.setInteger(0, i);
		sqlQuery.addEntity(Cattipounidad.class);
		Cattipounidad cattipounidad = (Cattipounidad) sqlQuery.uniqueResult(); 
		return cattipounidad;
	}

	@Override
	public Catzonacardinal getZonaCardinalById(int i) {
		//log.info("getZonaCardinalById");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_ZONA_CARDINAL_BY_ID);
		sqlQuery.setInteger(0, i);
		sqlQuery.addEntity(Catzonacardinal.class);
		Catzonacardinal catzonacardinal = (Catzonacardinal) sqlQuery.uniqueResult(); 
		return catzonacardinal;
	}

	@Override
	public void save(Lugaresdeatencion lugaresdeatencion) {
		//log.info("save");
		getSession().save(lugaresdeatencion);
		getSession().refresh(lugaresdeatencion);
	}

	@Override
	public Collection<Cattipounidad> getCatTipoUnidad(int i) {
		//log.info("getCatTipoUnidad");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_CAT_TIPO_UNIDAD);
		sqlQuery.setInteger(0, i);
		sqlQuery.addEntity(Cattipounidad.class);
		Collection<Cattipounidad> cattipounidads = sqlQuery.list();
		return cattipounidads;
	}

	@Override
	public Lugaresdeatencion getLugarAtencionById(int id) {
		//log.info("getLugarAtencionById");
		Query query = getSession().createQuery(GET_LUGAR_ATENCION_BY_ID);
		query.setInteger(0, id);
		Lugaresdeatencion lugaresdeatencion = (Lugaresdeatencion) query.uniqueResult(); 
		return lugaresdeatencion;
	}

	@Override
	public Collection<Lugaresdeatencion> getLugarAtencionByDesc(String busqueda) {
		//log.info("getLugarAtencionByDesc");
		Query query = getSession().createQuery(GET_LUGAR_ATENCION);
		query.setString(0, "%"+busqueda.trim()+"%");
		return query.list();
	}

	@Override
	public Lugaresdeatencion getLugarAtencionByCodigo(int id) {
		//log.info("lugarAtencionByCodigo");
		Query query = getSession().createQuery(GET_LUGAR_ATENCION_BY_CODIGO);
		query.setInteger(0, id);
		return (Lugaresdeatencion) query.uniqueResult();
	}

	@Override
	public Collection<Lugaresdeatencion> getLugaresAtencion() {
		//log.info("getLugaresAtencion");
		Query query = getSession().createQuery(GET_ALL_LUGAR_ATENCION);
		List<Lugaresdeatencion> lugaresdeatencions = query.list();
		return lugaresdeatencions;
	}

	@Override
	public Lugaresdeatencion getLugarAtencionByDescripcion(String descripcion) {
		//log.info("getLugarAtencionByDescripcion");
		Query query = getSession().createQuery(GET_LUGAR_ATENCION_BY_DESCRIPCION);
		query.setString(0, descripcion);
		Lugaresdeatencion lugaresdeatencion = (Lugaresdeatencion) query.uniqueResult();
		return lugaresdeatencion;
	}

	@Override
	public Lugaresdeatencion getLugarAtencionByClave(String claveDeLaUnidad) {
		//log.info("getLugarAtencionByDescripcion");
		Query query = getSession().createQuery(GET_LUGAR_ATENCION_BY_CLAVE);
		query.setString(0, claveDeLaUnidad);
		Lugaresdeatencion lugaresdeatencion = (Lugaresdeatencion) query.uniqueResult();
		return lugaresdeatencion;
	}

	@Override
	public Object getCodigo() {
		//log.info("getCodigo");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_CODIGO);
		return sqlQuery.uniqueResult();
	}

	@Override
	public Codigolugaratencion getCodigoLugarAtencion() {
		//log.info("getCodigoLugarAtencion");
		Query query = getSession().createQuery(GET_CODIGO_LUGAR_ATENCION);
		return (Codigolugaratencion) query.uniqueResult();
	}

	@Override
	public Lugaresdeatencion existeDireccionLugarAtencion(LugarAtencionForm lugarAtencionForm) {
		//log.info("existeDireccionLugarAtencion");
		Query query = getSession().createQuery(GET_LUGAR_ATENCION_BY_DIRECCION);
		query.setString(0, lugarAtencionForm.getCalle());
		query.setString(1, lugarAtencionForm.getNumeroInterno());
		query.setString(2, lugarAtencionForm.getNumeroExterno());
		query.setInteger(3, lugarAtencionForm.getIdEstado());
		query.setInteger(4, lugarAtencionForm.getIdMunicipio());
		query.setInteger(5, lugarAtencionForm.getIdColonia());
		query.setString(6, lugarAtencionForm.getCp()); 
		return (Lugaresdeatencion) query.uniqueResult();
	}

	@Override
	public void delete(Lugaresdeatencion lugaresdeatencion) {
		//log.info("delete");
		getSession().delete(lugaresdeatencion);
	}

	@Override
	public Collection<Lugaresdeatencion> getLugarAtencionByCodigos(String busqueda) {
		//log.info("getLugarAtencionByCodigos");
		Query query = getSession().createQuery(GET_LUGAR_ATENCION_BY_CODIGOS);
		query.setString(0, "%"+busqueda.trim()+"%");
		return query.list();
	}

	@Override
	public Collection<Roles> getRoles() {
		//log.info("getRoles");
		Query query = getSession().createQuery(GET_ROLES);
		return query.list();
	}

	@Override
	public Roles getRolById(int parseInt) {
		//log.info("getLugarAtencionByCodigos");
		Query query = getSession().createQuery(GET_ROL_BY_ID);
		query.setInteger(0, parseInt);
		return (Roles) query.uniqueResult();
	}

	@Override
	public void saveLugaresAtencionRoles(Lugaresdeatencionroles lugaresdeatencionroles) {
		//log.info("saveLugaresAtencionRoles");
		getSession().save(lugaresdeatencionroles);
		
	}

	@Override
	public void deleteLugaresAtencionRoles(Integer lugarDeAtencionId) {
		Query query = getSession().createQuery(DELETE_LUGARES_ATENCION_ROLES_BY_ID);
		query.setInteger(0, lugarDeAtencionId);
		query.executeUpdate();
	}

	@Override
	public Collection<Lugaresdeatencionroles> getLugaresAtencionRolesByIdLugarAtencion(Integer lugarDeAtencionId) {
		//log.info("getLugaresAtencionRolesByIdLugarAtencion");
		Query query = getSession().createQuery(GET_LUGAR_DE_ATENCION_ROLES_BY_ID_LUGAR_ATENCION);
		query.setInteger(0, lugarDeAtencionId);
		return query.list();
	}

}
