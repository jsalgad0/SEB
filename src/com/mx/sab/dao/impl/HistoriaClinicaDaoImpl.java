package com.mx.sab.dao.impl;

import java.util.Collection;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IHistoriaClinicaDao;
import com.mx.sab.model.Catalimentacion;
import com.mx.sab.model.Cathigiene;
import com.mx.sab.model.Catmpf;
import com.mx.sab.model.Cattipofamilia;
import com.mx.sab.model.HcAntecedentesfamiliares;
import com.mx.sab.model.HcAntecedentespersonal;
import com.mx.sab.model.HcExploracionfisica;
import com.mx.sab.model.HcFasedelciclo;
import com.mx.sab.model.HcGineco;
import com.mx.sab.model.HcPersonalnopatologicos;
import com.mx.sab.model.Historiaclinica;

@Service
@Log4j2
@Transactional
public class HistoriaClinicaDaoImpl implements IHistoriaClinicaDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_ALIMENTACION = "FROM Catalimentacion ";
	private static String GET_HIGIENE = "FROM Cathigiene ";
	private static String GET_MPF = "FROM Catmpf ";
	private static String GET_FAMILIA = "FROM Cattipofamilia ";
	private static String GET_ALIMENTACION_BY_ID = "FROM Catalimentacion WHERE alimentacionId = ? ";
	private static String GET_HIGIENE_BY_ID = "FROM Cathigiene WHERE higieneId = ? ";
	private static String GET_MPF_BY_ID = "FROM Catmpf WHERE mpfId = ? ";
	private static String GET_FAMILIA_BY_ID = "FROM Cattipofamilia WHERE tipoFamiliaId = ? ";
	private static String GET_HISTORIA_CLINICA = "FROM Historiaclinica WHERE afiliadoId = ?  ";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}
	
	@Override
	public Collection<Catalimentacion> getCatAlimentacion() {
		//log.info("getAlimentacion");
		Query query = getSession().createQuery(GET_ALIMENTACION);
		List<Catalimentacion> alimentacion = query.list();
		return alimentacion;
	}

	@Override
	public Collection<Cathigiene> getCatHigiene() {
		//log.info("getHigiene");
		Query query = getSession().createQuery(GET_HIGIENE);
		List<Cathigiene> higiene = query.list();
		return higiene;
	}

	@Override
	public Collection<Catmpf> getMpf() {
		//log.info("getMpf");
		Query query = getSession().createQuery(GET_MPF);
		List<Catmpf> mpf = query.list();
		return mpf;
	}

	@Override
	public Collection<Cattipofamilia> getFamilia() {
		//log.info("getTipoFamilia");
		Query query = getSession().createQuery(GET_FAMILIA);
		List<Cattipofamilia> familia = query.list();
		return familia;
	}

	@Override
	public Historiaclinica getHistoriaClinica(int idAfilaido) {
		//log.info("getHistoriaClinicaById");
		Query query = getSession().createQuery(GET_HISTORIA_CLINICA);
		query.setInteger(0, idAfilaido);
		Historiaclinica historia = (Historiaclinica) query.uniqueResult();
		return  historia;		
	}

	@Override
	public void saveHistoria(Historiaclinica historiaclinica) {
		//log.info("save");
		getSession().save(historiaclinica);
	}

	@Override
	public void saveHcPersonales(HcAntecedentespersonal hcAntecedentespersonal) {
		//log.info("save");
		getSession().save(hcAntecedentespersonal);
	}

	@Override
	public void saveHcFamiliares(HcAntecedentesfamiliares hcAntecedentesfamiliares) {
		//log.info("save");
		getSession().save(hcAntecedentesfamiliares);
	}

	@Override
	public void saveHcNoPatologicos(HcPersonalnopatologicos hcPersonalnopatologicos) {
		//log.info("save");
		getSession().save(hcPersonalnopatologicos);
	}

	@Override
	public void saveHcFase(HcFasedelciclo hcFasedelciclo) {
		//log.info("save");
		getSession().save(hcFasedelciclo);
	}

	@Override
	public void saveHcExploracion(HcExploracionfisica hcExploracionfisica) {
		//log.info("save");
		getSession().save(hcExploracionfisica);
	}

	@Override
	public void saveGineco(HcGineco hcGineco) {
		//log.info("save");
		getSession().save(hcGineco);
	}

	@Override
	public void updateHcPersonales(HcAntecedentespersonal hcAntecedentespersonal) {
		//log.info("update");
		getSession().merge(hcAntecedentespersonal);
	}

	@Override
	public void updateHcFamiliares(HcAntecedentesfamiliares hcAntecedentesfamiliares) {
		//log.info("update");
		getSession().merge(hcAntecedentesfamiliares);
	}

	@Override
	public void updateHcNoPatologicos(HcPersonalnopatologicos hcPersonalnopatologicos) {
		//log.info("update");
		getSession().merge(hcPersonalnopatologicos);
	}

	@Override
	public void updateHcFase(HcFasedelciclo hcFasedelciclo) {
		//log.info("update");
		getSession().merge(hcFasedelciclo);
	}

	@Override
	public void updateHcExploracion(HcExploracionfisica hcExploracionfisica) {
		//log.info("update");
		getSession().merge(hcExploracionfisica);
	}

	@Override
	public void updateGineco(HcGineco hcGineco) {
		//log.info("update");
		getSession().merge(hcGineco);
	}

	@Override
	public Catalimentacion getAlimentacionById(int id) {
		//log.info("getAlimentacionById");
		Query query = getSession().createQuery(GET_ALIMENTACION_BY_ID);
		query.setInteger(0, id);
		Catalimentacion alimentacion = (Catalimentacion) query.uniqueResult();
		return  alimentacion;		
	}

	@Override
	public Cathigiene getHigieneById(int id) {
		//log.info("getHigieneById");
		Query query = getSession().createQuery(GET_HIGIENE_BY_ID);
		query.setInteger(0, id);
		Cathigiene higiene = (Cathigiene) query.uniqueResult();
		return  higiene;		
	}

	@Override
	public Catmpf getMpfById(int id) {
		//log.info("getMpfById");
		Query query = getSession().createQuery(GET_MPF_BY_ID);
		query.setInteger(0, id);
		Catmpf mpf = (Catmpf) query.uniqueResult();
		return  mpf;		
	}

	@Override
	public Cattipofamilia getFamiliaById(int id) {
		//log.info("getFamiliaById");
		Query query = getSession().createQuery(GET_FAMILIA_BY_ID);
		query.setInteger(0, id);
		Cattipofamilia familia = (Cattipofamilia) query.uniqueResult();
		return  familia;		
	}	
	
}
