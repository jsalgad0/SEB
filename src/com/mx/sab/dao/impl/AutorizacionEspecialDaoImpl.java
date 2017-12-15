package com.mx.sab.dao.impl;

import java.util.Collection;
import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IAutorizacionEspecialDao;
import com.mx.sab.model.Catcausas;
import com.mx.sab.model.Cattipoautorizacion;
import com.mx.sab.model.Permisoespecial;

@Log4j2
@Transactional
@Service
public class AutorizacionEspecialDaoImpl implements IAutorizacionEspecialDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_CAT_CAUSAS = "FROM Catcausas";
	private static String GET_CAT_CAUSA = "FROM Catcausas WHERE causaId=?";
	private static String GET_PERMISOS_ESPECIALES_AFILIADOS = "FROM Permisoespecial WHERE lugaresdeatencion.lugarDeAtencionId = ? AND fechaInicio = ? AND usuariosByUsuarioAutorizaId.usuarioId IS NULL AND usuariosByUsuarioId.usuarioId IS NULL";
	private static String GET_PERMISOS_ESPECIALES_AFILIADOS_COUNT = "SELECT count(*) FROM permisoespecial WHERE LugarDeAtencionId = ? AND FechaInicio = ? AND UsuarioAutorizaId IS NULL AND UsuarioId IS NULL";	
	private static String GET_PERMISO_ESPECIAL = "FROM Permisoespecialafiliado WHERE id.permisoEspecialAfiliadoId = ?";
	private static String GET_CAT_TIPO_AUTORIZACION_BY_ID = "FROM Cattipoautorizacion WHERE tipoAutorizacionId = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Collection<Permisoespecial> getPermisosEspecialesAfiliados(int inicio, int fin, Integer lugarDeAtencionId) {
		//log.info("getPermisosEspecialesAfiliados");
		Query query = getSession().createQuery(GET_PERMISOS_ESPECIALES_AFILIADOS);
		query.setInteger(0, lugarDeAtencionId);
		query.setDate(1, new Date());
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.list();		 
	}

//	@Override
//	public Collection<Permisoespecialafiliado> getAutorizacionesPendientes(Integer lugarDeAtencionId) {
//		//log.info("getAutorizacionesPendientes");
//		Query query = getSession().createQuery(GET_PERMISOS_ESPECIALES_AFILIADOS);
//		query.setInteger(0, lugarDeAtencionId);
//		query.setDate(1, new Date());
//		return query.list(); 
//	}
	
	@Override
	public Collection<Catcausas> getCatCausas() {
		//log.info("getCatCausas");
		Query query = getSession().createQuery(GET_CAT_CAUSAS);
		return query.list();
	}

//	@Override
//	public Permisoespecialafiliado getPermisoEspecial(int idPermisoEspecial) {
//		//log.info("getPermisoEspecial");
//		Query query = getSession().createQuery(GET_PERMISO_ESPECIAL);
//		query.setInteger(0, idPermisoEspecial);
//		return (Permisoespecialafiliado) query.uniqueResult();
//	}

	@Override
	public Catcausas getCatCausa(int id) {
		//log.info("getCatCausa");
		Query query = getSession().createQuery(GET_CAT_CAUSA);
		query.setInteger(0, id);
		return (Catcausas) query.uniqueResult();
	}

//	@Override
//	public void update(Permisoespecialafiliado permisoespecialafiliado) {
//		//log.info("getCatCausa");
//		getSession().update(permisoespecialafiliado);
//	}

	@Override
	public void save(Permisoespecial permisoespecial) {
		//log.info("getCatCausa");
		getSession().save(permisoespecial);
	}

	@Override
	public Cattipoautorizacion getTipoAutorizacionById(int id) {
		//log.info("getTipoAutorizacionById");
		Query query = getSession().createQuery(GET_CAT_TIPO_AUTORIZACION_BY_ID);
		query.setInteger(0, id);
		return (Cattipoautorizacion) query.uniqueResult();
	}

	@Override
	public int getPermisosEspecialesAfiliadosCount(Integer lugarDeAtencionId) {
		//log.info("getPermisosEspecialesAfiliadosCount");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_PERMISOS_ESPECIALES_AFILIADOS_COUNT);
		sqlQuery.setInteger(0, lugarDeAtencionId);
		sqlQuery.setDate(1, new Date());
		return ((Number) sqlQuery.uniqueResult()).intValue(); 
	}
}
