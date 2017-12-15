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

import com.mx.sab.dao.IRegistroLectoresDao;
import com.mx.sab.model.Lectores;
import com.mx.sab.model.Propietarioslector;

@Log4j2
@Transactional
@Service
public class RegistroLectoresDaoImpl implements IRegistroLectoresDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_PROPIETARIOS_BY_ID = "SELECT * FROM propietarioslector WHERE idPropietarioLector = ?";
	private static String GET_LECTOR_BY_ID = "SELECT * FROM lectores WHERE LectorId = ?";
	private static String GET_LECTORES = "FROM Lectores WHERE noDeSerie LIKE ? ORDER BY lectorId ASC";	
	private static String GET_COUNT_LECTORES = "SELECT count(*) FROM lectores WHERE noDeSerie like ?";
	private static String GET_PROPIETARIO_LECTOR = "FROM Propietarioslector WHERE rfc LIKE ? ORDER BY idPropietarioLector ASC";
	private static String GET_PROPIETARIO_LECTOR_BY_PROPIETARIO = "FROM Propietarioslector WHERE propietarioLector = ?";
	private static String GET_LECTOR_BY_NO_SERIE = "FROM Lectores where noDeSerie = ?";
	private static String GET_PROPIETARIO_BY_RFC = "FROM Propietarioslector where rfc = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}	
	
	@Override
	public Collection<Lectores> getLectores(String busqueda, int inicio, int fin) {
		//log.info("getLectores");
		Query query = getSession().createQuery(GET_LECTORES);
		query.setString(0, "%"+busqueda.trim()+"%");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		List<Lectores> lectores = query.list();
		return lectores;
	}

	@Override
	public int getLectoresCount(String busqueda) {
		//log.info("getLectoresCount");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_COUNT_LECTORES);
		sqlQuery.setString(0, "%"+busqueda.trim()+"%");
		return ((Number) sqlQuery.uniqueResult()).intValue(); 
	}

	@Override
	public Propietarioslector getPropietariosLectorById(Integer id) {
		//log.info("getPropietariosLectorById");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_PROPIETARIOS_BY_ID);
		sqlQuery.setInteger(0, id);
		sqlQuery.addEntity(Propietarioslector.class);
		Propietarioslector propietarioslector = (Propietarioslector) sqlQuery.uniqueResult(); 
		return propietarioslector;
	}

	@Override
	public void save(Lectores lectores) {
		//log.info("save");
		getSession().save(lectores);
	}

	@Override
	public void update(Lectores lectores) {
		//log.info("update");
		getSession().merge(lectores);
	}

	@Override
	public Lectores getLectorById(int id) {
		//log.info("getLectorById");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_LECTOR_BY_ID);
		sqlQuery.setInteger(0, id);
		sqlQuery.addEntity(Lectores.class);
		Lectores lector = (Lectores) sqlQuery.uniqueResult(); 
		return lector;
	}

	@Override
	public Collection<Propietarioslector> getPropietariosLectorByDesc(String busqueda) {
		//log.info("getPropietariosLectorByDesc");
		Query query = getSession().createQuery(GET_PROPIETARIO_LECTOR);
		query.setString(0, "%"+busqueda.trim()+"%");
		return query.list();
	}

	@Override
	public Propietarioslector getPropietariosLectorByPropietario(String busqueda) {
		//log.info("getPropietariosLectorByDesc");
		Query query = getSession().createQuery(GET_PROPIETARIO_LECTOR_BY_PROPIETARIO);
		query.setString(0,busqueda);
		return (Propietarioslector) query.uniqueResult();
	}

	@Override
	public Lectores getLectorByNoSerie(String noDeSerie) {
		//log.info("existeNoSerie");
		Query query = getSession().createQuery(GET_LECTOR_BY_NO_SERIE);
		query.setString(0, noDeSerie);
		return (Lectores) query.uniqueResult();
	}

	@Override
	public void savePropietariosLector(Propietarioslector propietarioslector) {
		//log.info("save");
		getSession().save(propietarioslector);
	}

	@Override
	public Propietarioslector getPropietariosLectorByRfc(String rfcPropietarioLector) {
		//log.info("getPropietariosLectorByRfc");
		Query query = getSession().createQuery(GET_PROPIETARIO_BY_RFC);
		query.setString(0, rfcPropietarioLector);
		return (Propietarioslector) query.uniqueResult();
	}

}
