package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IAuditoriaDao;
import com.mx.sab.model.Auditoriaautentia;
import com.mx.sab.model.Catespecialidadesmedicas;
import com.mx.sab.model.Loginlog;

@Log4j2
@Transactional
@Service
public class AuditoriaDaoImpl implements IAuditoriaDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_AUDITORIA_AUTENTIA_BY_ID = "FROM Auditoriaautentia WHERE auditoriaId = ?";
	private static String GET_LOGIN_BY_USUARIO_ID = "FROM Loginlog WHERE usuarioId = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public void save(Auditoriaautentia auditoriaautentia) {
		//log.info("save");
		getSession().save(auditoriaautentia);
		getSession().refresh(auditoriaautentia);
	}

	@Override
	public void update(Auditoriaautentia auditoriaautentia) {
		//log.info("update");
		getSession().update(auditoriaautentia);
		
	}

	@Override
	public Auditoriaautentia getAuditoriAutentiaById(int idAuditoria) {
		//log.info("getAuditoriAutentiaById");
		Query query = getSession().createQuery(GET_AUDITORIA_AUTENTIA_BY_ID);
		query.setInteger(0, idAuditoria);  
		return (Auditoriaautentia) query.uniqueResult();
	}

	@Override
	public Collection<Loginlog> getLoginByUsuarioId(Integer usuarioId) {
		//log.info("getLoginByUsuarioId");
		Query query = getSession().createQuery(GET_LOGIN_BY_USUARIO_ID);
		query.setInteger(0, usuarioId);  
		return query.list();
	}

}
