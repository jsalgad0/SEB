package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.ILicenciaMedicaDao;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Catlicmedicacaracteres;
import com.mx.sab.model.Catlicmedicamotivos;
import com.mx.sab.model.Catlicmedicatiposservicio;
import com.mx.sab.model.Licenciamedica;

@Log4j2
@Transactional
@Service
public class LicenciaMedicaDaoImpl implements ILicenciaMedicaDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_CAT_LIC_MEDICA_MOTIVOS = "FROM Catlicmedicamotivos";
	private static String GET_CAT_LIC_MEDICA_CARACTERES = "FROM Catlicmedicacaracteres";
	private static String GET_CAT_LIC_MEDICA_TIPOS_SERVICIOS = "FROM Catlicmedicatiposservicio";
	private static String GET_CAT_LIC_MEDICA_TIPOS_SERVICIOS_BY_ID = "FROM Catlicmedicatiposservicio WHERE licMedicaTipoServicioId = ?";
	private static String GET_CAT_LIC_MEDICA_MOTIVOS_BY_ID = "FROM Catlicmedicamotivos WHERE licMedicaMotivoId = ?";
	private static String GET_CAT_LIC_MEDICA_CARACTERES_BY_ID = "FROM Catlicmedicacaracteres WHERE licMedicaCaracterId = ?";
	private static String GET_ATENCIONES_MEDICAS = "FROM Agenda WHERE afiliado.afiliadoId = ? AND atencionmedica.atencionMedicaId IS NOT NULL";
	private static String GET_LICENCIAS_MEDICAS = "FROM Licenciamedica WHERE atencionmedica.atencionMedicaId IN :atencionMedicaIds";
	private static String GET_LICENCIAS_MEDICAS_FOLIO = "FROM Licenciamedica ORDER BY folio DESC";
	private static String GET_LICENCIAS_MEDICAS_FOLIO_BY_ID = "FROM Licenciamedica WHERE atencionmedica.atencionMedicaId = ? ORDER BY folio DESC";
	private static String GET_LICENCIA_MEDICA_BY_ID_ATENCION_MEDICA = "FROM Licenciamedica WHERE atencionmedica.atencionMedicaId = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}
	
	@Override
	public Collection<Catlicmedicamotivos> getCatLicMedicaMotivos() {
		//log.info("getConveniosCount");
		Query query = getSession().createQuery(GET_CAT_LIC_MEDICA_MOTIVOS);
		return query.list(); 
	}

	@Override
	public Collection<Catlicmedicacaracteres> getCatLicMedicaCaracteres() {
		//log.info("getCatLicMedicaCaracteres");
		Query query = getSession().createQuery(GET_CAT_LIC_MEDICA_CARACTERES);
		return query.list();
	}

	@Override
	public Collection<Catlicmedicatiposservicio> getCatLicMedicaTiposServicio() {
		//log.info("getCatLicMedicaTiposServicio");
		Query query = getSession().createQuery(GET_CAT_LIC_MEDICA_TIPOS_SERVICIOS);
		return query.list();
	}

	@Override
	public Catlicmedicamotivos getCatLicMedicaMotivosById(int idLicMedicaMotivo) {
		//log.info("getCatLicMedicaTiposServicioById");
		Query query = getSession().createQuery(GET_CAT_LIC_MEDICA_MOTIVOS_BY_ID);
		query.setParameter(0, idLicMedicaMotivo);
		return (Catlicmedicamotivos) query.uniqueResult();
	}

	@Override
	public Catlicmedicacaracteres getCatLicMedicaCaracteresById(int idLicMedicaCaracter) {
		//log.info("getCatLicMedicaTiposServicioById");
		Query query = getSession().createQuery(GET_CAT_LIC_MEDICA_CARACTERES_BY_ID);
		query.setParameter(0, idLicMedicaCaracter);
		return (Catlicmedicacaracteres) query.uniqueResult();
	}

	@Override
	public Catlicmedicatiposservicio getCatLicMedicaTiposServicioById(int idLicMedicaTipoServicio) {
		//log.info("getCatLicMedicaTiposServicioById");
		Query query = getSession().createQuery(GET_CAT_LIC_MEDICA_TIPOS_SERVICIOS_BY_ID);
		query.setParameter(0, idLicMedicaTipoServicio);
		return (Catlicmedicatiposservicio) query.uniqueResult();
	}

	@Override
	public void save(Licenciamedica licenciamedica) {
		//log.info("save");
		getSession().save(licenciamedica);
	}

	@Override
	public Collection<Agenda> getAtencionesMedicas(Integer afiliadoId) {
		//log.info("getAtencionesMedicas");
		Query query = getSession().createQuery(GET_ATENCIONES_MEDICAS);
		query.setParameter(0, afiliadoId);
		return query.list();
	}

	@Override
	public Collection<Licenciamedica> getLicenciasMedicas(Collection<Integer> atencionMedicaIds) {
		//log.info("getLicenciasMedicas");
		Query query = getSession().createQuery(GET_LICENCIAS_MEDICAS);
		query.setParameterList("atencionMedicaIds", atencionMedicaIds);
		return query.list();
	}

	@Override
	public Licenciamedica getLicenciaMedicaFolio() {
		//log.info("getLicenciaMedicaFolio");
		Query query = getSession().createQuery(GET_LICENCIAS_MEDICAS_FOLIO);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return (Licenciamedica) query.uniqueResult();
	}

	@Override
	public Licenciamedica getLicenciaMedicaByIdAgenda(int idAgenda) {
		//log.info("getLicenciaMedicaByIdAgenda");
		Query query = getSession().createQuery(GET_LICENCIAS_MEDICAS_FOLIO_BY_ID);
		query.setParameter(0, idAgenda);
		return (Licenciamedica) query.uniqueResult();
	}

	@Override
	public Licenciamedica getLicenciaMedicaByIdAtencionMedica(Integer atencionMedicaId) {
		//log.info("getLicenciaMedicaByIdAtencionMedica");
		Query query = getSession().createQuery(GET_LICENCIA_MEDICA_BY_ID_ATENCION_MEDICA);
		query.setParameter(0, atencionMedicaId);
		return (Licenciamedica) query.uniqueResult();
	}

	@Override
	public void update(Licenciamedica licenciamedica) {
		//log.info("update");
		getSession().update(licenciamedica);
	}

}
