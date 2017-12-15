package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IHistorialAtencionesDao;
import com.mx.sab.model.Atencionmedica;

@Log4j2
@Transactional
@Service
public class HistorialAtencionesDaoImpl implements IHistorialAtencionesDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_HISTORIAL_ATENCIONES = "FROM Atencionmedica WHERE afiliadoId = ? AND catestatusfirmaByEstatusFirmaMedico.id IN (1,3,5)";
	private static String GET_HISTORIAL_ATENCIONES_COUNT = "SELECT COUNT(*) FROM atencionmedica WHERE AfiliadoId = ? AND EstatusFirmaMedico IN (1,3,5)";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}	

	@Override
	public int getHistorialCount(int idAfiliado) {
		//log.info("getHistorialCount");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_HISTORIAL_ATENCIONES_COUNT);
		sqlQuery.setInteger(0, idAfiliado);
		return ((Number) sqlQuery.uniqueResult()).intValue(); 
	}

	@Override
	public Collection<Atencionmedica> getHistorial(int idAfiliado, int inicio, int fin) {
		//log.info("getHistorial");
		Query query = getSession().createQuery(GET_HISTORIAL_ATENCIONES);
		query.setInteger(0, idAfiliado);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.list();
	}

}
