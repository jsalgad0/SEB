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

import com.mx.sab.dao.IInformacionPacienteDao;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Afiliadoadicionales;
import com.mx.sab.model.Afiliadodemografico;
import com.mx.sab.model.Catcolonias;
import com.mx.sab.model.Catestados;
import com.mx.sab.model.Catgrupossanguineos;
import com.mx.sab.model.Catmunicipios;
import com.mx.sab.model.Catocupacion;
import com.mx.sab.model.Catsexos;

@Service
@Log4j2
@Transactional
public class InformacionPacienteDaoImpl implements IInformacionPacienteDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_OCUPACION = "FROM Catocupacion ";
	private static String GET_GRUPO_SANGUINEO = "FROM Catgrupossanguineos ";
	private static String GET_GRUPO_SANGUINEO_BY_ID = "FROM Catgrupossanguineos WHERE grupoSanguineoId = ? ";
	private static String GET_OCUPACION_BY_ID = "FROM Catocupacion WHERE ocupacionId = ? ";
	private static String GET_SEXO_BY_ID = "FROM Catsexos WHERE sexoId = ? ";
	private static String GET_ESTADO_BY_ID = "FROM Catestados WHERE estadoId = ? ";
	private static String GET_MUNICIPIO_BY_ID = "FROM Catmunicipios WHERE municipioId = ? ";
	private static String GET_COLONIA_BY_ID = "FROM Catcolonias WHERE coloniaId = ? ";
	private static String GET_DEMOGRAFICO = "FROM Afiliadodemografico WHERE afiliado.afiliadoId = ?";
	private static String GET_ADICIONAL = "FROM Afiliadoadicionales WHERE afiliado.afiliadoId = ?";
	private static String GET_AFILIADO_BY_ID = "FROM Afiliado WHERE afiliadoId = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Collection<Catocupacion> getOcupacion() {
		//log.info("getOcupacion");
		Query query = getSession().createQuery(GET_OCUPACION);
		List<Catocupacion> ocupacion = query.list();
		return ocupacion;
	}

	@Override
	public Collection<Catgrupossanguineos> getGrupoSanguineo() {
		//log.info("getGrupoSanguineo");
		Query query = getSession().createQuery(GET_GRUPO_SANGUINEO);
		List<Catgrupossanguineos> grupoSanguineo = query.list();
		return grupoSanguineo;
	}

	@Override
	public void saveDemografico(Afiliadodemografico afiliadodemografico) {
		//log.info("saveDemografico");
		getSession().save(afiliadodemografico);
	}

	@Override
	public void saveAdicional(Afiliadoadicionales afiliadoadicionales) {
		//log.info("saveAdicional");
		getSession().save(afiliadoadicionales);
	}

	@Override
	public Catgrupossanguineos getGrupoSanguineoById(int id) {
		//log.info("getCatGrupoSanguineoById");
		Query query = getSession().createQuery(GET_GRUPO_SANGUINEO_BY_ID);
		query.setInteger(0, id);
		Catgrupossanguineos catgrupossanguineos = (Catgrupossanguineos) query.uniqueResult(); 
		return catgrupossanguineos;
	}

	@Override
	public Catocupacion getOcupacionById(int id) {
		//log.info("getCatOcupacionById");
		Query query = getSession().createQuery(GET_OCUPACION_BY_ID);
		query.setInteger(0, id);
		Catocupacion catocupacion = (Catocupacion) query.uniqueResult(); 
		return catocupacion;
	}

	@Override
	public void updateAfiliado(Afiliado afiliado) {
		//log.info("update");
		getSession().merge(afiliado);
	}

	@Override
	public void updateDemografico(Afiliadodemografico afiliadodemografico) {
		//log.info("update");
		getSession().merge(afiliadodemografico);
	}

	@Override
	public void updateAdicional(Afiliadoadicionales afiliadoadicionales) {
		//log.info("update");
		getSession().merge(afiliadoadicionales);
	}

	@Override
	public Afiliadodemografico getDemografico(Afiliado afiliado) {
		//log.info("getAfiliadoDemografico");
		Query query = getSession().createQuery(GET_DEMOGRAFICO);
		query.setInteger(0, afiliado.getAfiliadoId());
		return (Afiliadodemografico) query.uniqueResult();
	}

	@Override
	public Afiliadoadicionales getAdicionales(Afiliado afiliado) {
		//log.info("getAfiliadoAdicionales");
		Query query = getSession().createQuery(GET_ADICIONAL);
		query.setInteger(0, afiliado.getAfiliadoId());
		return (Afiliadoadicionales) query.uniqueResult();
	}

	@Override
	public Afiliado getAfiliadoById(int idAfiliado) {
		Query query = getSession().createQuery(GET_AFILIADO_BY_ID);
		query.setInteger(0, idAfiliado);
		return (Afiliado) query.uniqueResult();
	}

	@Override
	public Catsexos getSexo(int id) {
		//log.info("getCatSexoById");
		Query query = getSession().createQuery(GET_SEXO_BY_ID);
		query.setInteger(0, id);
		Catsexos catsexos = (Catsexos) query.uniqueResult(); 
		return catsexos;
	}

	@Override
	public Catestados getEstadosById(int id) {
		//log.info("getCatEstadosById");
		Query query = getSession().createQuery(GET_ESTADO_BY_ID);
		query.setInteger(0, id);
		Catestados catestados = (Catestados) query.uniqueResult(); 
		return catestados;
	}

	@Override
	public Catmunicipios getMunicipiosById(int id) {
		//log.info("getCatMunicipioById");
		Query query = getSession().createQuery(GET_MUNICIPIO_BY_ID);
		query.setInteger(0, id);
		Catmunicipios catmunicipios = (Catmunicipios) query.uniqueResult(); 
		return catmunicipios;
	}

	@Override
	public Catcolonias getColoniasById(int id) {
		//log.info("getCatColoniasById");
		Query query = getSession().createQuery(GET_COLONIA_BY_ID);
		query.setInteger(0, id);
		Catcolonias catcolonias = (Catcolonias) query.uniqueResult(); 
		return catcolonias;
	}
	
}
