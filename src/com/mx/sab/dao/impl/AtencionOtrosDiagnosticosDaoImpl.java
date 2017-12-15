package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IAtencionOtrosDiagnosticosDao;
import com.mx.sab.model.Catcies10;
import com.mx.sab.model.Cattipodiagnostico;
import com.mx.sab.model.NotamedicaCies10;
import com.mx.sab.vo.DiagnosticoVo;

@Log4j2
@Transactional
@Service
public class AtencionOtrosDiagnosticosDaoImpl implements IAtencionOtrosDiagnosticosDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_DIAGNOSTICOS_BY_ID_ATENCION = "FROM NotamedicaCies10 WHERE notamedica.atencionmedica.atencionMedicaId = ?";
	private static String GET_DIAGNOSTICOS_BY_CODIGO = "FROM Catcies10 WHERE codigo LIKE ?";
	private static String GET_DIAGNOSTICOS_BY_DESCRIPCION = "FROM Catcies10 WHERE descripcion LIKE ?";
	private static String GET_CAT_TIPO_DIAGNOSTICOS = "FROM Cattipodiagnostico";
	private static String GET_CAT_CIES10_BY_ID = "FROM Catcies10 WHERE cie10id = ?";
	private static String GET_CAT_TIPO_DIAGNOSTICO_BY_ID = "FROM Cattipodiagnostico WHERE id = ?";
	private static String GET_NOTA_MEDICA_CIES10 = "FROM NotamedicaCies10 WHERE id.notaMedicaId = ? AND id.cie10id = ?";

	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Collection<NotamedicaCies10> getDiagnosticosByIdAtencion(int idAtencion) {
		//log.info("getDiagnosticosByIdAtencion");
		Query query = getSession().createQuery(GET_DIAGNOSTICOS_BY_ID_ATENCION);
		query.setInteger(0, idAtencion);
		return query.list();
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
	public void save(NotamedicaCies10 notamedicaCies10) {
		//log.info("save");
		getSession().save(notamedicaCies10);
	}

	@Override
	public NotamedicaCies10 getNotaMedicaCies10(DiagnosticoVo diagnosticoVo) {
		//log.info("getCatTipoDiagnosticoById");
		Query query = getSession().createQuery(GET_NOTA_MEDICA_CIES10);
		query.setInteger(0, diagnosticoVo.getIdNotaMedica());
		query.setInteger(1, diagnosticoVo.getIdDiagnostico());
		return (NotamedicaCies10) query.uniqueResult();
	}

	@Override
	public void update(NotamedicaCies10 notamedicaCies10) {
		//log.info("update");
		getSession().update(notamedicaCies10);
	}

	@Override
	public void delete(NotamedicaCies10 notamedicaCies10) {
		//log.info("delete");
		getSession().delete(notamedicaCies10);
	}
}
