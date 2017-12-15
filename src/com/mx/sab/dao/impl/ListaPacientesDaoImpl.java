package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IListaPacientesDao;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.Catcies10;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.NotamedicaCies10;
import com.mx.sab.model.Signosvitales;

@Log4j2
@Transactional
@Service
public class ListaPacientesDaoImpl implements IListaPacientesDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_SIGNOS_VITALES_BY_ID_ATENCION_MEDICA = "FROM Signosvitales WHERE atencionmedica.atencionMedicaId = ?";
	private static String GET_CAT_CIES_10 = "FROM Catcies10 WHERE descripcion LIKE ?";
	private static String GET_ATENCION_PRESTACION_BY_ID_PRESTACION = "FROM Atencionprestacion WHERE atencionmedica.atencionMedicaId = ? AND catprestacion.prestacionId = ?";
	private static String GET_ATENCION_PRESTACION = "FROM Atencionprestacion WHERE atencionmedica.atencionMedicaId = ?";
	private static String GET_NOTA_MEDICA_BY_ATENCION_MEDICA_ID = "FROM Notamedica WHERE atencionmedica.atencionMedicaId = ?";
	private static String GET_CAT_CIES_10_BY_ID = "FROM Catcies10 WHERE cie10id = ?";
	private static String GET_NOTA_MEDICA_CIES_10_BY_ID_NOTA_MEDICA = "FROM NotamedicaCies10 WHERE notamedica.notaMedicaId = ?";
	private static String GET_NOTA_MEDICA_BY_ID = "FROM Notamedica WHERE notaMedicaId = ?";
	private static String GET_NOTA_MEDICA_BY_ID_NOTA_MEDICA_PRINCIPAL = "FROM NotamedicaCies10 WHERE notamedica.notaMedicaId = ? AND principal = 1";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Signosvitales getSignosVitalesByIdAtencionMedica(int idAgenda) {
		//log.info("getSignosVitales");
		Query query = getSession().createQuery(GET_SIGNOS_VITALES_BY_ID_ATENCION_MEDICA);
		query.setInteger(0, idAgenda);
		return (Signosvitales) query.uniqueResult(); 
	}

	@Override
	public Collection<Catcies10> getCatCies10(String busqueda) {
		//log.info("getCatCies10");
		Query query = getSession().createQuery(GET_CAT_CIES_10);
		query.setString(0, "%"+busqueda.trim()+"%");
		query.setMaxResults(10);
		return query.list();
	}

	@Override
	public void save(Notamedica notamedica) {
		//log.info("save");
		getSession().save(notamedica);
		getSession().refresh(notamedica);
	}

	@Override
	public void saveCies10(NotamedicaCies10 notamedicaCies10) {
		//log.info("saveCies10");
		getSession().save(notamedicaCies10);
	}

	@Override
	public Atencionprestacion getAtencionPrestacion(Integer atencionmedicaId, Integer key) {
		//log.info("getAtencionPrestacion");
		Query query = getSession().createQuery(GET_ATENCION_PRESTACION_BY_ID_PRESTACION);
		query.setInteger(0, atencionmedicaId);
		query.setInteger(1, key);
		return (Atencionprestacion) query.uniqueResult();
	}
	
	@Override
	public Collection<Atencionprestacion> getAtencionPrestaciones(Integer atencionmedicaId) {
		//log.info("getAtencionPrestaciones");
		Query query = getSession().createQuery(GET_ATENCION_PRESTACION);
		query.setInteger(0, atencionmedicaId);
		return query.list();
	}	

	@Override
	public void delete(Atencionprestacion atencionprestacion) {
		//log.info("delete");
		getSession().delete(atencionprestacion);
	}

	@Override
	public void saveAtencionPrestacion(Atencionprestacion atencionprestacion) {
		//log.info("saveAtencionPrestacion");
		getSession().save(atencionprestacion);
		
	}

	@Override
	public Notamedica getNotaMedicaByAtencionMedicaId(Integer atencionMedicaId) {
		//log.info("getAtencionPrestacion");
		Query query = getSession().createQuery(GET_NOTA_MEDICA_BY_ATENCION_MEDICA_ID);
		query.setInteger(0, atencionMedicaId);
		return (Notamedica) query.uniqueResult();
	}

	@Override
	public Catcies10 getCatCies10ById(int idDiagnostico) {
		//log.info("getCatCies10ById");
		Query query = getSession().createQuery(GET_CAT_CIES_10_BY_ID);
		query.setInteger(0, idDiagnostico);
		return (Catcies10) query.uniqueResult();
	}

	@Override
	public Collection<NotamedicaCies10> getNotaMedicaCies10ByIdNotaMedica(Integer notaMedicaId) {
		//log.info("getNotaMedicaCies10ByIdNotaMedica");
		Query query = getSession().createQuery(GET_NOTA_MEDICA_CIES_10_BY_ID_NOTA_MEDICA);
		query.setInteger(0, notaMedicaId);
		return query.list();
	}

	@Override
	public Notamedica getNotaMedicaById(Integer notaMedicaId) {
		//log.info("getNotaMedicaById");
		Query query = getSession().createQuery(GET_NOTA_MEDICA_BY_ID);
		query.setInteger(0, notaMedicaId);
		return (Notamedica) query.uniqueResult();
	}

	@Override
	public NotamedicaCies10 getNotaMedicaCies10ByIdNotaMedicaPrincipal(Integer notaMedicaId) {
		//log.info("getNotaMedicaCies10ByIdNotaMedicaPrincipal");
		Query query = getSession().createQuery(GET_NOTA_MEDICA_BY_ID_NOTA_MEDICA_PRINCIPAL);
		query.setInteger(0, notaMedicaId);
		return (NotamedicaCies10) query.uniqueResult();
	}

	@Override
	public void update(Notamedica notamedica) {
		//log.info("update");
		getSession().update(notamedica);
	}

	@Override
	public void updateCies10(NotamedicaCies10 notamedicaCies10) {
		//log.info("updateCies10");
		getSession().update(notamedicaCies10);
	}

	@Override
	public void deleteCies10(NotamedicaCies10 notamedicaCies10) {
		//log.info("deleteCies10");
		getSession().delete(notamedicaCies10);
	}
}
