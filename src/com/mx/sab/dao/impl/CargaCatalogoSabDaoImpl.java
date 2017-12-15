package com.mx.sab.dao.impl;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.ICargaCatalogoSabDao;
import com.mx.sab.model.Catprestacion;

@Service
@Log4j2
@Transactional
public class CargaCatalogoSabDaoImpl implements ICargaCatalogoSabDao {
	
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
	public void save(Catprestacion catprestacion) {
		//log.info("save");
		getSession().save(catprestacion);
	}

}
