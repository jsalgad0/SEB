package com.mx.sab.dao.impl;
 
import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.INotaMedicaDao;
import com.mx.sab.model.Catcies10;
import com.mx.sab.model.Cattipodiagnostico;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.NotamedicaCies10;

@Log4j2
@Transactional
@Service
public class NotaMedicaDaoImpl implements INotaMedicaDao {
	
	@Autowired
	private SessionFactory sessionFactory;
		
	private static String GET_NOTA_MEDICA_BY_ID_ATENCION = "FROM Notamedica WHERE atencionmedica.atencionMedicaId = ?";
	
	private static String GET_DIAGNOSTICOS_BY_CODIGO = "FROM Catcies10 WHERE codigo LIKE ?";
	private static String GET_DIAGNOSTICOS_BY_DESCRIPCION = "FROM Catcies10 WHERE descripcion LIKE ?";
	private static String GET_CAT_TIPO_DIAGNOSTICOS = "FROM Cattipodiagnostico";
	private static String GET_CAT_CIES10_BY_ID = "FROM Catcies10 WHERE cie10id = ?";
	private static String GET_CAT_TIPO_DIAGNOSTICO_BY_ID = "FROM Cattipodiagnostico WHERE id = ?";
	private static String GET_NOTA_MEDICA_CIES10 = "FROM NotamedicaCies10 WHERE id.notaMedicaId = ? AND principal = 1";
	private static String GET_ALL_NOTAS_MEDICAS_CIES10 = "FROM NotamedicaCies10 WHERE id.notaMedicaId = ?";

	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}
	


	@Override
	public Notamedica getNotaMedicaByIdAtencion(Integer idAtencion) {
		//log.info("getNotaMedicaByIdAtencion");
		Query query = getSession().createQuery(GET_NOTA_MEDICA_BY_ID_ATENCION);
		query.setInteger(0, idAtencion);
		return (Notamedica) query.uniqueResult();
	}

	@Override
	public void saveNotaMedica(Notamedica notaMedica) {
		//log.info("saveNotaMedica");
		getSession().save(notaMedica);
		getSession().refresh(notaMedica);
	}

	@Override
	public void updateNotaMedica(Notamedica notaMedica) {
		//log.info("updateNotaMedica");
		getSession().update(notaMedica);
	}


	@Override
	public Collection<Catcies10> getDiagnosticosByCodigo(String busqueda) {
		//log.info("getDiagnosticosByCodigo");
		Query query = getSession().createQuery(GET_DIAGNOSTICOS_BY_CODIGO);
		query.setString(0, "%"+busqueda+"%");
		return query.list();
	}

	@Override
	public Collection<Catcies10> getDiagnosticosByDescripcion(String busqueda) {
		//log.info("getDiagnosticosByDescripcion");
		Query query = getSession().createQuery(GET_DIAGNOSTICOS_BY_DESCRIPCION);
		query.setString(0, "%"+busqueda+"%");
		return query.list();
	}

	@Override
	public Collection<Cattipodiagnostico> getCatTipoDiagnosticos() {
		//log.info("getCatTipoDiagnosticos");
		Query query = getSession().createQuery(GET_CAT_TIPO_DIAGNOSTICOS);
		return query.list();
	}

	@Override
	public Catcies10 getCatCies10ById(int idDiagnostico) {
		//log.info("getCatCies10ById");
		Query query = getSession().createQuery(GET_CAT_CIES10_BY_ID);
		query.setInteger(0, idDiagnostico);
		return (Catcies10) query.uniqueResult();
	}

	@Override
	public Cattipodiagnostico getCatTipoDiagnosticoById(int idTipoDiagnostico) {
		//log.info("getCatTipoDiagnosticoById");
		Query query = getSession().createQuery(GET_CAT_TIPO_DIAGNOSTICO_BY_ID);
		query.setInteger(0, idTipoDiagnostico);
		return (Cattipodiagnostico) query.uniqueResult();
	}


	@Override
	public void saveNotaMedicaCies10(NotamedicaCies10 notamedicaCies10) {
		//log.info("saveCies10");
		getSession().save(notamedicaCies10);
	}

	@Override
	public void updateNotaMedicaCies10(NotamedicaCies10 notamedicaCies10) {
		//log.info("updateCies10");
		getSession().update(notamedicaCies10);	
	}


	@Override
	public NotamedicaCies10 getNotaMedicaCies10ByIdNotaMedica(Integer idNotaMedica) {
		//log.info("getNotaMedicaByIdAtencion");
		Query query = getSession().createQuery(GET_NOTA_MEDICA_CIES10);
		query.setInteger(0, idNotaMedica);
		return (NotamedicaCies10) query.uniqueResult();
	}

	@Override
	public void deleteNotaMedicaCies10(NotamedicaCies10 notamedicaCies10) {
		//log.info("deleteNotaMedicaCies10");
		getSession().delete(notamedicaCies10);
	}



	@Override
	public Collection<NotamedicaCies10> getAllNotasMedicasCies10ByNotaMedicaId(Integer idNotaMedica) {
		//log.info("getAllNotasMedicasCies10ByNotaMedicaId DAO");
		Query query = getSession().createQuery(GET_ALL_NOTAS_MEDICAS_CIES10);
		query.setInteger(0, idNotaMedica);
		return query.list();
	}
	



}
