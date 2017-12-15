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

import com.mx.sab.dao.IAseguradorDao;
import com.mx.sab.form.AseguradorForm;
import com.mx.sab.model.Aseguradores;
import com.mx.sab.model.Prestacionasegurador;
import com.mx.sab.model.Prestadores;

@Service
@Log4j2
@Transactional
public class AseguradorDaoImpl implements IAseguradorDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_ASEGURADORES_BY_ID = "FROM Aseguradores WHERE aseguradorId = ?";
	private static String GET_ALL_ASEGURADORES = "FROM Aseguradores WHERE activo = 1 ORDER BY aseguradorId ASC";
	private static String GET_ASEGURADORES_SIN_PRESTACIONES = "FROM Aseguradores WHERE activo = 1 ORDER BY aseguradorId ASC";
	private static String GET_ASEGURADORES = "FROM Aseguradores WHERE nombreRazonSocial LIKE ? ORDER BY aseguradorId ASC";
	private static String GET_ASEGURADORES_BY_RFC = "FROM Aseguradores WHERE rfc = :rfc";
	private static String GET_COUNT_ASEGURADORES = "SELECT count(*) FROM aseguradores WHERE NombreRazonSocial like ? AND Activo = 1";
	private static String GET_ASEGURADOR_BY_DIRECCION = "FROM Aseguradores WHERE calle = ? AND noInt = ? AND noExt = ? AND catestados.estadoId = ? AND catmunicipios.municipioId = ? AND catcolonias.coloniaId = ? AND cp = ?";
	private static String GET_ASEGURADOR_BY_NOMBRE = "FROM Aseguradores WHERE nombreRazonSocial = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public void save(Aseguradores aseguradores) {
		//log.info("save");
		getSession().save(aseguradores);
	}

	@Override
	public Aseguradores getAseguradorById(Integer id) {
		//log.info("getAseguradorById");
		Query query = getSession().createQuery(GET_ASEGURADORES_BY_ID);
		query.setInteger(0, id);
		Aseguradores aseguradores = (Aseguradores) query.uniqueResult(); 
		return aseguradores;
	}

	@Override
	public Collection<Aseguradores> getAseguradores() {
		//log.info("getAseguradores");
		Query query = getSession().createQuery(GET_ALL_ASEGURADORES);
		List<Aseguradores> aseguradores = query.list();
		return aseguradores;
	} 	

	@Override
	public Collection<Aseguradores> getAseguradoresSinPrestaciones() {
		//log.info("getAseguradores");
		Query query = getSession().createQuery(GET_ASEGURADORES_SIN_PRESTACIONES);
		List<Aseguradores> aseguradores = query.list();
		return aseguradores;
	}
	
	@Override
	public Collection<Aseguradores> getAseguradoresByDesc(String busqueda) {
		//log.info("getAseguradoresByDesc");
		Query query = getSession().createQuery(GET_ASEGURADORES);
		query.setString(0, "%"+busqueda.trim()+"%");
		return query.list();
	}

	@Override
	public Aseguradores getAseguradorByRfc(String rfc){
		//log.info("getAseguradorByRfc");
		Query query = getSession().createQuery(GET_ASEGURADORES_BY_RFC);
		query.setParameter("rfc", rfc);
		return (Aseguradores) query.uniqueResult();
	}

	@Override
	public void update(Aseguradores aseguradores) {
		//log.info("update");
		getSession().merge(aseguradores);
	}
	
	@Override
	public int getAseguradoresCount(String busqueda) {
		//log.info("getAseguradoresCount");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_COUNT_ASEGURADORES);
		sqlQuery.setString(0, "%"+busqueda.trim()+"%");
		return ((Number) sqlQuery.uniqueResult()).intValue(); 
	}
	
	@Override
	public Collection<Aseguradores> getAseguradores(String busqueda, int inicio, int fin) {
		//log.info("getAseguradores");
		Query query = getSession().createQuery(GET_ASEGURADORES);
		query.setString(0, "%"+busqueda.trim()+"%");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		List<Aseguradores> aseguradores = query.list();
		return aseguradores;
	}

	@Override
	public Aseguradores existeDireccionAsegurador(AseguradorForm aseguradorForm) {
		//log.info("existeDireccionAsegurador");
		Query query = getSession().createQuery(GET_ASEGURADOR_BY_DIRECCION);
		query.setString(0, aseguradorForm.getCalle());
		query.setString(1, aseguradorForm.getNumeroInterno());
		query.setString(2, aseguradorForm.getNumeroExterno());
		query.setInteger(3, aseguradorForm.getIdEstado());
		query.setInteger(4, aseguradorForm.getIdMunicipio());
		query.setInteger(5, aseguradorForm.getIdColonia());
		query.setString(6, aseguradorForm.getCp()); 
		return (Aseguradores) query.uniqueResult();
	}

	@Override
	public Aseguradores getAseguradoresByNombre(String asegurador) {
		//log.info("getAseguradoresByNombre");
		Query query = getSession().createQuery(GET_ASEGURADOR_BY_NOMBRE);
		query.setString(0, asegurador);
		Aseguradores aseguradores = (Aseguradores) query.uniqueResult(); 
		return aseguradores;
	}

	@Override
	public void delete(Aseguradores aseguradores) {
		getSession().delete(aseguradores);
	}
}
