package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IListaMenoresDao;
import com.mx.sab.model.Afiliado;

@Log4j2
@Transactional
@Service
public class ListaMenoresDaoImpl implements IListaMenoresDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_AFILIADOS_MENOR = "FROM Afiliado WHERE YEAR(CURDATE())-YEAR(fechaDeNacimiento) < 7";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Collection<Afiliado> getAfiliados() {
		//log.info("getListaAfiliadosMenor");
		Query query = getSession().createQuery(GET_AFILIADOS_MENOR);
		query.setMaxResults(10);
		return query.list();
	}

}
