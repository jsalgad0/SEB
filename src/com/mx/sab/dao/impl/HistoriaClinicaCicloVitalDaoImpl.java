package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IHistoriaClinicaCicloVitalDao;
import com.mx.sab.dao.IHistoriaClinicaPersonalesNoPatologicosDao;
import com.mx.sab.model.Afiliadodemografico;
import com.mx.sab.model.Catalimentacion;
import com.mx.sab.model.Catescolaridad;
import com.mx.sab.model.Catestadocivil;
import com.mx.sab.model.Catgrupossanguineos;
import com.mx.sab.model.Cathigiene;
import com.mx.sab.model.Catmpf;
import com.mx.sab.model.Cattipofamilia;
import com.mx.sab.model.HcFasedelciclo;
import com.mx.sab.model.HcGineco;
import com.mx.sab.model.HcPersonalnopatologicos;
import com.mx.sab.model.Historiaclinica;

@Log4j2
@Transactional
@Service
public class HistoriaClinicaCicloVitalDaoImpl implements IHistoriaClinicaCicloVitalDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_FASE_BY_ID_HISTORIA_CLINICA = "FROM HcFasedelciclo WHERE historiaclinica.historiaClinicaId = ?";
	private static String GET_CAT_TIPO_FAMILIA = "FROM Cattipofamilia";
	private static String GET_CAT_TIPO_FAMILIA_BY_ID = "FROM Cattipofamilia where tipofamiliaid = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Collection<Cattipofamilia> getTipoFamilia() {
		//log.info("getTipoFamilia");
		Query query = getSession().createQuery(GET_CAT_TIPO_FAMILIA);
		return query.list();
	}

	@Override
	public Cattipofamilia getCatTipoFamiliaById(int idTipoFamilia) {
		//log.info("getCatTipoFamiliaById");
		Query query = getSession().createQuery(GET_CAT_TIPO_FAMILIA_BY_ID);
		query.setInteger(0, idTipoFamilia);
		return (Cattipofamilia) query.uniqueResult();
	}

	@Override
	public HcFasedelciclo getFasedelciclo(Integer historiaMedicaId) {
		//log.info("getFasedelciclo");
		Query query = getSession().createQuery(GET_FASE_BY_ID_HISTORIA_CLINICA);
		query.setInteger(0, historiaMedicaId);
		return (HcFasedelciclo) query.uniqueResult();
		
	}

	@Override
	public void saveFasedelciclo(HcFasedelciclo hcFasedelciclo) {
		//log.info("saveFasedelciclo");
		getSession().save(hcFasedelciclo);
		getSession().refresh(hcFasedelciclo);
	}

	@Override
	public void updateFasedelciclo(HcFasedelciclo hcFasedelciclo) {
		//log.info("updateFasedelciclo");
		getSession().merge(hcFasedelciclo);
	}

	

}
