package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IMedicoLicenciaMedicaDao;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.form.MedicoLicenciaMedicaForm;
import com.mx.sab.model.Catlicmedicacaracteres;
import com.mx.sab.model.Catlicmedicamotivos;
import com.mx.sab.model.Catlicmedicatiposservicio;
import com.mx.sab.model.HcFasedelciclo;
import com.mx.sab.model.Licenciamedica;
import com.mx.sab.model.NotamedicaCies10;
import com.mx.sab.vo.CatLicMedicaCaracteresVo;
import com.mx.sab.vo.CatLicMedicaMotivosVo;
import com.mx.sab.vo.CatLicMedicaTipoServicioVo;
import com.mx.sab.vo.UserInfo;


@Log4j2
@Transactional
@Service
public class MedicoLicenciaMedicaDaoImpl implements IMedicoLicenciaMedicaDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_LICENCIA_MEDICA_BY_ID_ATENCION = "FROM Licenciamedica WHERE atencionMedicaId = ?";
	private static String GET_CAT_LIC_MEDICA_CARACTERES = "FROM Catlicmedicacaracteres";
	private static String GET_CAT_LIC_MEDICA_CARACTERES_BY_ID = "FROM Catlicmedicacaracteres where licmedicacaracterid = ?";
	private static String GET_CAT_LIC_MEDICA_MOTIVOS = "FROM Catlicmedicamotivos";
	private static String GET_CAT_LIC_MEDICA_MOTIVOS_BY_ID = "FROM Catlicmedicamotivos where licmedicamotivoid = ?";
	private static String GET_CAT_LIC_MEDICA_TIPOS_SERVICIO = "FROM Catlicmedicatiposservicio";
	private static String GET_CAT_LIC_MEDICA_TIPOS_SERVICIO_BY_ID = "FROM Catlicmedicatiposservicio where licmedicatiposervicioid = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Collection<Catlicmedicacaracteres> getLicMedicaCaracteres() {
		//log.info("getLicMedicaCaracteres");
		Query query = getSession().createQuery(GET_CAT_LIC_MEDICA_CARACTERES);
		return query.list();
	}

	@Override
	public Catlicmedicacaracteres getLicMedicaCaracteresById(int idLicMedicaCaracter) {
		//log.info("getLicMedicaCaracteresById");
		Query query = getSession().createQuery(GET_CAT_LIC_MEDICA_CARACTERES_BY_ID);
		query.setInteger(0, idLicMedicaCaracter);
		return (Catlicmedicacaracteres) query.uniqueResult();
	}
	
	@Override
	public Collection<Catlicmedicamotivos> getLicMedicaMotivos() {
		//log.info("getLicMedicaMotivos");
		Query query = getSession().createQuery(GET_CAT_LIC_MEDICA_MOTIVOS);
		return query.list();
	}

	@Override
	public Catlicmedicamotivos getLicMedicaMotivosById(int idLicMedicaMotivo) {
		//log.info("getLicMedicaMotivosById");
		Query query = getSession().createQuery(GET_CAT_LIC_MEDICA_MOTIVOS_BY_ID);
		query.setInteger(0, idLicMedicaMotivo);
		return (Catlicmedicamotivos) query.uniqueResult();
	}
	
	@Override
	public Collection<Catlicmedicatiposservicio> getLicMedicaTiposServicio() {
		//log.info("getLicMedicaTiposServicio");
		Query query = getSession().createQuery(GET_CAT_LIC_MEDICA_TIPOS_SERVICIO);
		return query.list();
	}

	@Override
	public Catlicmedicatiposservicio getLicMedicaTiposServicioById(int idLicMedicaTipoServicio) {
		//log.info("getLicMedicaTiposServicioById");
		Query query = getSession().createQuery(GET_CAT_LIC_MEDICA_TIPOS_SERVICIO_BY_ID);
		query.setInteger(0, idLicMedicaTipoServicio);
		return (Catlicmedicatiposservicio) query.uniqueResult();
	}

	@Override
	public Licenciamedica getLicenciaMedica(Integer atencionMedicaId) {
		//log.info("getLicenciaMedica");
		Query query = getSession().createQuery(GET_LICENCIA_MEDICA_BY_ID_ATENCION);
		query.setInteger(0, atencionMedicaId);
		return (Licenciamedica) query.uniqueResult();
		
	}

	@Override
	public void saveLicenciaMedica(Licenciamedica licenciamedica) {
		//log.info("saveLicenciaMedica");
		getSession().save(licenciamedica);
		getSession().refresh(licenciamedica);
	}

	@Override
	public void updateLicenciaMedica(Licenciamedica licenciamedica) {
		//log.info("updateLicenciaMedica");
		getSession().merge(licenciamedica);
	}
	

}
