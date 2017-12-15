package com.mx.sab.dao.impl;

import java.util.Collection;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.ILoginDao;
import com.mx.sab.model.Catpreguntasecreta;
import com.mx.sab.model.Roles;
import com.mx.sab.model.Usuarios;

@Log4j2
@Repository
@Transactional
public class LoginDaoImpl implements ILoginDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_USUARIO_BY_RFC = "FROM Usuarios where rfc = ?";
	private static String GET_MENUS_BY_ROL = "FROM Roles where rolId = ?";	
	private static String GET_CAT_PREGUNTAS = "FROM Catpreguntasecreta";
	private static String GET_CAT_PREGUNTAS_BY_ID = "FROM Catpreguntasecreta WHERE id = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}	

	@Override
	public Usuarios getUsuarioByRfc(String rfc) {
		//log.info("getUsuarioByRfc");
		Query query = getSession().createQuery(GET_USUARIO_BY_RFC);
		query.setParameter(0, rfc);
		return (Usuarios) query.uniqueResult();
	}

	@Override
	public List<Roles> getRoles(int rol) {
		//log.info("getMenus");
		Query query = getSession().createQuery(GET_MENUS_BY_ROL);
		query.setParameter(0, rol);
		List<Roles> roles = query.list();
		return roles;
	}

	@Override
	public Collection<Catpreguntasecreta> getPreguntas() {
		//log.info("getPreguntas");
		Query query = getSession().createQuery(GET_CAT_PREGUNTAS);
		return query.list();
	}

	@Override
	public Catpreguntasecreta getPreguntaById(int i) {
		//log.info("getPreguntaById");
		Query query = getSession().createQuery(GET_CAT_PREGUNTAS_BY_ID);
		query.setInteger(0,i);
		return (Catpreguntasecreta) query.uniqueResult();
	}	

}
