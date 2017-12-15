package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IHistoriaClinicaPersonalesNoPatologicosDao;
import com.mx.sab.model.Afiliadodemografico;
import com.mx.sab.model.Catalimentacion;
import com.mx.sab.model.Catescolaridad;
import com.mx.sab.model.Catestadocivil;
import com.mx.sab.model.Catgrupossanguineos;
import com.mx.sab.model.Cathigiene;
import com.mx.sab.model.HcPersonalnopatologicos;
import com.mx.sab.model.Historiaclinica;

@Log4j2
@Transactional
@Service
public class HistoriaClinicaPersonalesNoPatologicosDaoImpl implements IHistoriaClinicaPersonalesNoPatologicosDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_CAT_GRUPOS_SANGUINEOS = "FROM Catgrupossanguineos";
	private static String GET_CAT_ESCOLARIDAD = "FROM Catescolaridad";
	private static String GET_CAT_ALIMENTACION = "FROM Catalimentacion";
	private static String GET_CAT_HIGIENE = "FROM Cathigiene";
	private static String GET_CAT_ESTADO_CIVIL = "FROM Catestadocivil";
	private static String GET_HISTORIA_CLINICA_BY_ID_AFILIADO = "FROM Historiaclinica WHERE afiliado.afiliadoId = ?";
	private static String GET_AFILIADO_DEMOGRAFICO_BY_ID_AFILIADO = "FROM Afiliadodemografico WHERE afiliado.afiliadoId = ?";
	private static String GET_CAT_ALIMENTACION_BY_ID = "FROM Catalimentacion WHERE alimentacionId = ?";
	private static String GET_CAT_ESCOLARIDAD_BY_ID = "FROM Catescolaridad WHERE escolaridadId = ?";
	private static String GET_CAT_HIGIENE_BY_ID = "FROM Cathigiene WHERE higieneId = ?";
	private static String GET_CAT_ESTADO_CIVIL_BY_ID = "FROM Catestadocivil WHERE estadoCivilId = ?";
	private static String GET_CAT_GRUPO_SANGUINEO_BY_ID = "FROM Catgrupossanguineos WHERE grupoSanguineoId = ?";
	private static String GET_PERSONAL_NO_PATOLOGICOS_BY_ID = "FROM HcPersonalnopatologicos WHERE personalNoPatologicosId = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Collection<Catgrupossanguineos> getTipoSangre() {
		//log.info("getTipoSangre");
		Query query = getSession().createQuery(GET_CAT_GRUPOS_SANGUINEOS);
		return query.list();
	}

	@Override
	public Collection<Catescolaridad> getEscolaridades() {
		//log.info("getEscolaridades");
		Query query = getSession().createQuery(GET_CAT_ESCOLARIDAD);
		return query.list();
	}

	@Override
	public Collection<Catalimentacion> getAlimentaciones() {
		//log.info("getAlimentaciones");
		Query query = getSession().createQuery(GET_CAT_ALIMENTACION);
		return query.list();
	}

	@Override
	public Collection<Cathigiene> getHigienePersonales() {
		//log.info("getHigienePersonales");
		Query query = getSession().createQuery(GET_CAT_HIGIENE);
		return query.list();
	}

	@Override
	public Collection<Catestadocivil> getEstadosCiviles() {
		//log.info("getEstadosCiviles");
		Query query = getSession().createQuery(GET_CAT_ESTADO_CIVIL);
		return query.list();
	}

	@Override
	public Historiaclinica getHistoriaClinicaByIdAfiliado(int idAfiliado) {
		//log.info("getHistoriaClinicaByIdAfiliado");
		Query query = getSession().createQuery(GET_HISTORIA_CLINICA_BY_ID_AFILIADO);
		query.setInteger(0, idAfiliado);
		return (Historiaclinica) query.uniqueResult();
	}

	@Override
	public Afiliadodemografico getAfiliadoDemograficoByIdAfiliado(int idAfiliado) {
		//log.info("getAfiliadoDemograficoByIdAfiliado");
		Query query = getSession().createQuery(GET_AFILIADO_DEMOGRAFICO_BY_ID_AFILIADO);
		query.setInteger(0, idAfiliado);
		return (Afiliadodemografico) query.uniqueResult();
	}

	@Override
	public Catalimentacion getCatAlimentacionById(int idAlimentacion) {
		//log.info("getCatAlimentacionById");
		Query query = getSession().createQuery(GET_CAT_ALIMENTACION_BY_ID);
		query.setInteger(0, idAlimentacion);
		return (Catalimentacion) query.uniqueResult();
	}

	@Override
	public Catescolaridad getCatEscolaridadById(int idEscolaridad) {
		//log.info("getCatEscolaridadById");
		Query query = getSession().createQuery(GET_CAT_ESCOLARIDAD_BY_ID);
		query.setInteger(0, idEscolaridad);
		return (Catescolaridad) query.uniqueResult();
	}

	@Override
	public Cathigiene getCatHigieneById(int idHigienePersonal) {
		//log.info("getCatHigieneById");
		Query query = getSession().createQuery(GET_CAT_HIGIENE_BY_ID);
		query.setInteger(0, idHigienePersonal);
		return (Cathigiene) query.uniqueResult();
	}

	@Override
	public Catestadocivil getCatEstadoCivilById(int idEstadoCivil) {
		//log.info("getCatEstadoCivilById");
		Query query = getSession().createQuery(GET_CAT_ESTADO_CIVIL_BY_ID);
		query.setInteger(0, idEstadoCivil);
		return (Catestadocivil) query.uniqueResult();
	}

	@Override
	public void updateHcPersonalnopatologicos(HcPersonalnopatologicos hcPersonalnopatologicos) {
		getSession().merge(hcPersonalnopatologicos);
		getSession().refresh(hcPersonalnopatologicos);
	}

	@Override
	public void saveHistoriaClinica(Historiaclinica historiaclinica) {
		getSession().save(historiaclinica);
		getSession().refresh(historiaclinica);
		
	}

	@Override
	public void saveHcPersonalesNoPatologicos(HcPersonalnopatologicos hcPersonalnopatologicos) {
		getSession().save(hcPersonalnopatologicos);
		getSession().refresh(hcPersonalnopatologicos);
	}

	@Override
	public Catgrupossanguineos getCatGrupoSanguineo(int idTipoSangre) {
		//log.info("getCatGrupoSanguineo");
		Query query = getSession().createQuery(GET_CAT_GRUPO_SANGUINEO_BY_ID);
		query.setInteger(0, idTipoSangre);
		return (Catgrupossanguineos) query.uniqueResult();
	}

	@Override
	public void updateAfiliadoDemografico(Afiliadodemografico afiliadodemografico) {
		getSession().merge(afiliadodemografico);
		getSession().refresh(afiliadodemografico);
	}

	@Override
	public void saveAfiliadoDemografico(Afiliadodemografico afiliadodemografico) {
		getSession().save(afiliadodemografico);
		getSession().refresh(afiliadodemografico);
	}

	@Override
	public HcPersonalnopatologicos getHcPersonalNoPatologicosById(int idPersonalNoPatologicos) {
		//log.info("getCatGrupoSanguineo");
		Query query = getSession().createQuery(GET_PERSONAL_NO_PATOLOGICOS_BY_ID);
		query.setInteger(0, idPersonalNoPatologicos);
		return (HcPersonalnopatologicos) query.uniqueResult();		
	}

}
