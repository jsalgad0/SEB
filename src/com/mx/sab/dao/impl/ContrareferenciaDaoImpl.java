package com.mx.sab.dao.impl;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IContrareferenciaDao;
import com.mx.sab.model.Contrareferencia;

@Log4j2
@Transactional
@Service
public class ContrareferenciaDaoImpl implements IContrareferenciaDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_CONTRAREFERENCIA_BY_SOLICITUD = "FROM Contrareferencia WHERE solicitudreferencia.solicitudReferenciaId = ?";
	private static String GET_CONTRAREFERENCIA_BY_ID = "FROM Contrareferencia WHERE contrareferenciaId = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public void save(Contrareferencia contrareferencia) {
		//log.info("save");
		getSession().save(contrareferencia);
	}

	@Override
	public Contrareferencia getContrareferenciaByIdSolicitud(Integer solicitudReferenciaId) {
		//log.info("getCatCies10");
		Query query = getSession().createQuery(GET_CONTRAREFERENCIA_BY_SOLICITUD);
		query.setInteger(0,solicitudReferenciaId);
		return (Contrareferencia) query.uniqueResult();
	}

	@Override
	public Contrareferencia getContrareferenciaById(int idContrareferencia) {
		//log.info("getContrareferenciaById");
		Query query = getSession().createQuery(GET_CONTRAREFERENCIA_BY_ID);
		query.setInteger(0,idContrareferencia);
		return (Contrareferencia) query.uniqueResult();
	}

	@Override
	public void update(Contrareferencia contrareferencia) {
		//log.info("update");
		getSession().update(contrareferencia);
		
	}
}
