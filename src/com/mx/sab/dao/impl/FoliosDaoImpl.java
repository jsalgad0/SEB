package com.mx.sab.dao.impl;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IFoliosDao;
import com.mx.sab.model.Folio;

@Log4j2
@Transactional
@Service
public class FoliosDaoImpl implements IFoliosDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_FOLIO_BY_ID = "FROM Folio WHERE cattipofolio.id=? AND lugaresdeatencion.lugarDeAtencionId = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public void updateFolio(Folio folio) {
		//log.info("updateFolio");
		getSession().update(folio);
	}

	@Override
	public Folio getFolioById(int id, Integer idLugarAtencion) {
		//log.info("getFolioById");
		Query query = getSession().createQuery(GET_FOLIO_BY_ID);
		query.setInteger(0, id);
		query.setInteger(1, idLugarAtencion);
		return (Folio) query.uniqueResult();
	}
}
