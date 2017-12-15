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

import com.mx.sab.dao.IPrestadoresDao;
import com.mx.sab.form.PrestadorForm;
import com.mx.sab.model.Cattipoprestador;
import com.mx.sab.model.Cattipospersonas;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Prestadores;

@Log4j2
@Transactional
@Service
public class PrestadoresDaoImpl implements IPrestadoresDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_TIPO_PERSONA_BY_ID = "SELECT * FROM catTiposPersonas WHERE TipoPersonaId = ?";
	private static String GET_TIPO_PRESTADOR_BY_ID = "SELECT * FROM catTipoPrestador WHERE TipoPrestadorId = ?";
	private static String GET_CAT_TIPOS_PERSONAS = "FROM Cattipospersonas";
	private static String GET_CAT_TIPOS_PRESTADOR = "FROM Cattipoprestador";	
	private static String GET_PRESTADOR_BY_ID = "FROM Prestadores WHERE prestadorId = ?";
	
	private static String GET_PRESTADORES = "FROM Prestadores WHERE nombreRazonSocial LIKE ? AND activo = 1 ORDER BY prestadorId ASC";
	private static String GET_COUNT_PRESTADORES = "SELECT count(*) FROM prestadores WHERE NombreRazonSocial like ? AND Activo = 1";
	private static String GET_ALL_PRESTADORES = "FROM Prestadores WHERE activo = 1 ORDER BY prestadorId ASC";
	private static String GET_PRESTADORES_SIN_PRESTACIONES = "FROM Prestadores WHERE activo = 1 AND usarTablaPrestacionesSab = 0 ORDER BY prestadorId ASC";
	
	private static String GET_PRESTADOR_BY_RFC = "FROM Prestadores WHERE rfc = ?";
	private static String GET_PRESTADOR_BY_NOMBRE = "FROM Prestadores WHERE nombreRazonSocial = ?";
	private static String GET_PRESTADOR_BY_NOMBRE_BUSQUEDA = "FROM Prestadores WHERE nombreRazonSocial LIKE ? ORDER BY prestadorId ASC";
	
	private static String GET_PRESTADOR_BY_DIRECCION = "FROM Prestadores WHERE calle = ? AND noInt = ? AND noExt = ? AND catestados.estadoId = ? AND catmunicipios.municipioId = ? AND catcolonias.coloniaId = ? AND cp = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}	

	@Override
	public void save(Prestadores prestadores) {
		//log.info("save");
		getSession().save(prestadores);
	}
	
	@Override
	public Cattipoprestador getTipoPrestadorById(int i) {
		//log.info("getTipoPrestadorById");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_TIPO_PRESTADOR_BY_ID);
		sqlQuery.setInteger(0, i);
		sqlQuery.addEntity(Cattipoprestador.class);
		Cattipoprestador cattipoprestador = (Cattipoprestador) sqlQuery.uniqueResult(); 
		return cattipoprestador;
	}

	@Override
	public Cattipospersonas getTipoPersonaById(int i) {
		//log.info("getTipoPersonaById");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_TIPO_PERSONA_BY_ID);
		sqlQuery.setInteger(0, i);
		sqlQuery.addEntity(Cattipospersonas.class);
		Cattipospersonas cattipospersonas = (Cattipospersonas) sqlQuery.uniqueResult(); 
		return cattipospersonas;
	}
	
	@Override
	public Collection<Cattipospersonas> getCatTiposPersonas() {
		//log.info("getCatTiposPersonas");
		Query query = getSession().createQuery(GET_CAT_TIPOS_PERSONAS);
		List<Cattipospersonas> cattipospersonas = query.list(); 
		return cattipospersonas;
	}

	@Override
	public Collection<Cattipoprestador> getCatTipoPrestador() {
		//log.info("getCatTipoPrestador");
		Query query = getSession().createQuery(GET_CAT_TIPOS_PRESTADOR);
		List<Cattipoprestador> cattipoprestadores = query.list(); 
		return cattipoprestadores;
	}	

	@Override
	public Collection<Prestadores> getPrestadores(String busqueda, int inicio, int fin) {
		//log.info("getPrestadores");
		Query query = getSession().createQuery(GET_PRESTADORES);
		query.setString(0, "%"+busqueda.trim()+"%");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		List<Prestadores> prestadores = query.list();
		return prestadores;
	}

	@Override
	public void delete(Prestadores prestador) {
		getSession().delete(prestador);
		
	}

	@Override
	public Prestadores getPrestadorById(int id) {
		//log.info("getPrestadorById");
		Query query = getSession().createQuery(GET_PRESTADOR_BY_ID);
		query.setInteger(0, id);
		Prestadores prestador = (Prestadores) query.uniqueResult(); 
		return prestador;
	}

	@Override
	public void update(Prestadores prestadores) {
		//log.info("update");
		getSession().merge(prestadores);
	}

	@Override
	public int getPrestadoresCount(String busqueda) {
		//log.info("getPrestadoresCount");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_COUNT_PRESTADORES);
		sqlQuery.setString(0, "%"+busqueda.trim()+"%");
		return ((Number) sqlQuery.uniqueResult()).intValue(); 
	}

	@Override
	public Collection<Prestadores> getPrestadores() {
		//log.info("getPrestadores");
		Query query = getSession().createQuery(GET_ALL_PRESTADORES);
		List<Prestadores> prestadores = query.list();
		return prestadores;
	}

	@Override
	public Collection<Prestadores> getPrestadoresSinPrestaciones() {
		//log.info("getPrestadoresSinPrestaciones");
		Query query = getSession().createQuery(GET_PRESTADORES_SIN_PRESTACIONES);
		List<Prestadores> prestadores = query.list();
		return prestadores;
	}

	@Override
	public Prestadores getPrestadoresByNombre(String nombreRazonSocial) {
		//log.info("getPrestadoresByNombre");
		Query query = getSession().createQuery(GET_PRESTADOR_BY_NOMBRE);
		query.setString(0, nombreRazonSocial);
		Prestadores prestador = (Prestadores) query.uniqueResult(); 
		return prestador;
	}

	@Override
	public Prestadores getPrestadoresByRfc(String rfc) {
		//log.info("getPrestadoresByRfc");
		Query query = getSession().createQuery(GET_PRESTADOR_BY_RFC);
		query.setString(0, rfc);
		Prestadores prestador = (Prestadores) query.uniqueResult(); 
		return prestador;
	}

	@Override
	public Prestadores existeDireccionPrestador(PrestadorForm prestadorForm) {
		//log.info("existeDireccionPrestador");
		Query query = getSession().createQuery(GET_PRESTADOR_BY_DIRECCION);
		query.setString(0, prestadorForm.getCalle());
		query.setString(1, prestadorForm.getNumeroInterno());
		query.setString(2, prestadorForm.getNumeroExterno());
		query.setInteger(3, prestadorForm.getIdEstado());
		query.setInteger(4, prestadorForm.getIdMunicipio());
		query.setInteger(5, prestadorForm.getIdColonia());
		query.setString(6, prestadorForm.getCp()); 
		return (Prestadores) query.uniqueResult();
	}

	@Override
	public Collection<Prestadores> getPrestadoresByNombreBusqueda(String busqueda) {
		//log.info("getLugarAtencionByDesc");
		Query query = getSession().createQuery(GET_PRESTADOR_BY_NOMBRE_BUSQUEDA);
		query.setString(0, "%"+busqueda.trim()+"%");
		return query.list();
	}


}
