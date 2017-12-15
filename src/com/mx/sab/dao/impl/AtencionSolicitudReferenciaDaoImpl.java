package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IAtencionSolicitudReferenciaDao;
import com.mx.sab.form.SolicitudReferenciaForm;
import com.mx.sab.model.Catsolireferenciamotivos;
import com.mx.sab.model.Catsolirefrerenciapor;
import com.mx.sab.model.Solicitudreferencia;

@Log4j2
@Transactional
@Service
public class AtencionSolicitudReferenciaDaoImpl implements IAtencionSolicitudReferenciaDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_CAT_SOLI_REFERENCIA_MOTIVOS = "FROM Catsolireferenciamotivos WHERE activo = 1";
	private static String GET_CAT_SOLI_REFERENCIA_POR = "FROM Catsolirefrerenciapor WHERE activo = 1";
	private static String GET_CAT_SOLI_REFERENCIA_MOTIVOS_BY_ID = "FROM Catsolireferenciamotivos WHERE soliReferenciaMotivoId = ?";
	private static String GET_CAT_SOLI_REFERENCIA_POR_BY_ID = "FROM Catsolirefrerenciapor WHERE referenciaPorId = ?";	
	private static String GET_LAST_SOLICITUD_REFERENCIA = "FROM Solicitudreferencia ORDER BY folio DESC";
	private static String GET_SOLICITUD_REFERENCIA_BY_ID_ATENCIONMEDICA = "FROM Solicitudreferencia WHERE atencionmedica.atencionMedicaId = ?";

	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Collection<Catsolireferenciamotivos> getCatSoliReferenciaMotivos() {
		//log.info("getCatSoliReferenciaMotivos");
		Query query = getSession().createQuery(GET_CAT_SOLI_REFERENCIA_MOTIVOS);
		return query.list();
	}

	@Override
	public Collection<Catsolirefrerenciapor> getCatSoliRefrerenciaPor() {
		//log.info("getCatSoliRefrerenciaPor");
		Query query = getSession().createQuery(GET_CAT_SOLI_REFERENCIA_POR);
		return query.list();
	}

	@Override
	public void save(Solicitudreferencia solicitudreferencia) {
		//log.info("save");
		getSession().save(solicitudreferencia);
		
	}

	@Override
	public Catsolireferenciamotivos getCatSoliReferenciaMotivosById(int idCatSoliReferenciaMotivos) {
		//log.info("getCatSoliReferenciaMotivosById");
		Query query = getSession().createQuery(GET_CAT_SOLI_REFERENCIA_MOTIVOS_BY_ID);
		query.setInteger(0, idCatSoliReferenciaMotivos);
		return (Catsolireferenciamotivos) query.uniqueResult();
	}

	@Override
	public Catsolirefrerenciapor getCatSoliRefrerenciaPorById(int idCatSoliRefrerenciaPor) {
		//log.info("getCatSoliRefrerenciaPorById");
		Query query = getSession().createQuery(GET_CAT_SOLI_REFERENCIA_POR_BY_ID);
		query.setInteger(0, idCatSoliRefrerenciaPor);
		return (Catsolirefrerenciapor) query.uniqueResult();
	}

	@Override
	public Solicitudreferencia getLastSolicitudreferencia() {
		//log.info("getLastSolicitudreferencia");
		Query query = getSession().createQuery(GET_LAST_SOLICITUD_REFERENCIA);
		query.setMaxResults(1);
		return (Solicitudreferencia) query.uniqueResult();
	}

	@Override
	public Solicitudreferencia getSolicitudReferenciaByIdAtencionMedica(Integer atencionMedicaId) {
		//log.info("getCatSoliRefrerenciaPorById");
		Query query = getSession().createQuery(GET_SOLICITUD_REFERENCIA_BY_ID_ATENCIONMEDICA);
		query.setInteger(0, atencionMedicaId);
		return (Solicitudreferencia) query.uniqueResult();
	}

	@Override
	public void update(Solicitudreferencia solicitudreferencia) {
		//log.info("update");
		getSession().update(solicitudreferencia);
	}
}
