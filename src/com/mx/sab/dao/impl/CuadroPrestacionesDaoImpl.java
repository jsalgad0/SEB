package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.ICuadroPrestacionesDao;
import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.CuadroprestacionPrestacion;
import com.mx.sab.model.Cuadroprestaciones;
import com.mx.sab.model.Prestacionasegurador;
import com.mx.sab.model.Prestacionprestador;

@Log4j2
@Transactional
@Service
public class CuadroPrestacionesDaoImpl implements ICuadroPrestacionesDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_PRESTACIONES_BY_ID = "FROM Catprestacion WHERE prestacionId = ?";
	private static String GET_CUADRO_PRESTACIONES_BY_ID_PRESTADOR = "FROM Cuadroprestaciones WHERE prestadores.prestadorId = ?";
	private static String GET_CUADRO_PRESTACIONES_BY_NOMBRE = "FROM Cuadroprestaciones WHERE cuadroPrestacion = ?";
	private static String GET_CUADRO_PRESTACIONES = "FROM Cuadroprestaciones";
	private static String GET_CUADRO_PRESTACION_PRESTACIONES_BY_ID = "FROM CuadroprestacionPrestacion WHERE cuadroprestaciones.cuadroPrestacionId = ?";
	private static String GET_PRESTACIONES_ASEGURADOR_BY_ID = "FROM Prestacionasegurador WHERE aseguradores.aseguradorId = ?";
	private static String GET_PRESTACIONES_PRESTADOR_BY_ID = "FROM Prestacionprestador WHERE prestadores.prestadorId = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Cuadroprestaciones saveCuadroPrestaciones(Cuadroprestaciones cuadroprestaciones) {
		getSession().save(cuadroprestaciones);
		getSession().refresh(cuadroprestaciones);
		return cuadroprestaciones;
		
	}

	@Override
	public Catprestacion getPrestacionSabById(int id) {
		//log.info("getPrestacionSabById");
		Query query = getSession().createQuery(GET_PRESTACIONES_BY_ID);
		query.setInteger(0, id);
		Catprestacion catprestacion = (Catprestacion) query.uniqueResult();
		return catprestacion;
	}

	@Override
	public void saveCuadroPrestacionPrestacion(CuadroprestacionPrestacion cuadroprestacionPrestacion) {
		getSession().save(cuadroprestacionPrestacion);
	}

	@Override
	public Collection<Cuadroprestaciones> getCuadroPrestacionesByPrestadorId(Integer idPrestadores) {
		//log.info("getCuadroPrestacionesByPrestadorId");
		Query query = getSession().createQuery(GET_CUADRO_PRESTACIONES_BY_ID_PRESTADOR);
		query.setInteger(0, idPrestadores);
		Collection<Cuadroprestaciones> cuadroprestaciones = query.list();
		return cuadroprestaciones;
	}

	@Override
	public void update(Cuadroprestaciones cuadroprestaciones) {
		//log.info("update");
		getSession().merge(cuadroprestaciones);
	}

	@Override
	public Cuadroprestaciones getCuadroPrestacionesByNombre(String cuadroPrestacion) {
		//log.info("getCuadroPrestacionesById");
		Query query = getSession().createQuery(GET_CUADRO_PRESTACIONES_BY_NOMBRE);
		query.setString(0, cuadroPrestacion);
		Cuadroprestaciones cuadroprestaciones = (Cuadroprestaciones) query.uniqueResult(); 
		return cuadroprestaciones;
	}

	@Override
	public Collection<Cuadroprestaciones> getCuadroPrestaciones() {
		//log.info("getCuadroPrestacionesById");
		Query query = getSession().createQuery(GET_CUADRO_PRESTACIONES);
		return query.list();
	}

	@Override
	public Collection<CuadroprestacionPrestacion> getCuadroPrestacionesPrestacionById(Integer idCuadroPrestacion) {
		//log.info("getCuadroPrestacionesPrestacionById");
		Query query = getSession().createQuery(GET_CUADRO_PRESTACION_PRESTACIONES_BY_ID);
		query.setInteger(0, idCuadroPrestacion);
		return query.list();
	}

	@Override
	public Collection<Prestacionasegurador> getPrestacionesAseguradorById(Integer idAseguradores) {
		//log.info("getPrestacionesAseguradorById");
		Query query = getSession().createQuery(GET_PRESTACIONES_ASEGURADOR_BY_ID);
		query.setInteger(0, idAseguradores);
		return query.list();
	}

	@Override
	public Collection<Prestacionprestador> getPrestacionesPrestadorById(Integer idPrestadores) {
		//log.info("getPrestacionesPrestadorById");
		Query query = getSession().createQuery(GET_PRESTACIONES_PRESTADOR_BY_ID);
		query.setInteger(0, idPrestadores);
		return query.list();
	}
}
