package com.mx.sab.dao.impl;

import java.util.Collection;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IGenericDao;
import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.Catcolonias;
import com.mx.sab.model.Catescolaridad;
import com.mx.sab.model.Catestadocivil;
import com.mx.sab.model.Catestados;
import com.mx.sab.model.Catmunicipios;
import com.mx.sab.model.Catocupacion;
import com.mx.sab.model.Catsexos;
import com.mx.sab.model.Cattipoafiliado;
import com.mx.sab.model.Cattipoidentificador;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Roles;

@Log4j2
@Transactional
@Service
public class GenericDaoImpl implements IGenericDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static String GET_ESTADO_CIVIL = "FROM Catestadocivil ";
	private static String GET_ESCOLARIDAD = "FROM Catescolaridad ";
	private static String GET_OCUPACION = "FROM Catocupacion ";
	private static String GET_ESTADOS = "FROM Catestados";
	//private static String GET_LUGAR_ATENCION = "SELECT * FROM lugaresDeAtencion ";
	private static String GET_LUGAR_ATENCION = "SELECT * FROM lugaresdeatencion ";
	private static String GET_TIPO_IDENTIFICADOR = "SELECT * FROM cattipoidentificador ";
	
	private static String GET_MUNICIPIOS = "SELECT * FROM catmunicipios WHERE EstadoId = ?";
	private static String GET_COLONIAS = "SELECT * FROM catcolonias WHERE MunicipioId = ?";
	private static String GET_ROLES = "SELECT * FROM roles WHERE ModuloId = ?";
	
	
	private static String GET_ESTADO_BY_ID = "FROM Catestados WHERE estadoId = ?";
	private static String GET_MUNICIPIO_BY_ID = "FROM Catmunicipios WHERE municipioId = ?";
	private static String GET_COLONIA_BY_ID = "FROM Catcolonias WHERE coloniaId = ?";
	private static String GET_ESCOLARIDAD_BY_ID = "FROM Catescolaridad WHERE escolaridadId = ? ";
	private static String GET_ESTADO_CIVIL_BY_ID = "FROM Catestadocivil WHERE estadoCivilId = ? ";
	
	private static String GET_TIPO_IDENTIFICADOR_PARA_PERSONA_CONFIANZA = "FROM Cattipoidentificador WHERE activoPersonasYacompaniantes = 1";
	
	private static String GET_TIPO_IDENTIFICADOR_BY_ID = "FROM Cattipoidentificador WHERE tipoIdentificadorId = ?";
	
	private static String GET_AFILIADO_TIPO_IDENTIFICADOR = "FROM Afiliadotipoidentificador WHERE cattipoidentificador.tipoIdentificadorId = ? AND afiliado.afiliadoId = ?";
	private static String GET_TIPO_IDENTIFICADOR_BY_ID_ASEGURADOR = "FROM Cattipoidentificador WHERE aseguradores.aseguradorId = ? OR aseguradores.aseguradorId IS NULL";
	private static String GET_CAT_SEXO_BY_ID = "FROM Catsexos WHERE sexoId = ?";
	private static String GET_CAT_TIPO_AFILIADO_BY_TIPO = "FROM Cattipoafiliado WHERE tipoAfiliado = ? AND aseguradores.aseguradorId = ?";
	private static String GET_CAT_TIPO_AFILIADO_BY_CLAVE = "FROM Cattipoafiliado WHERE tipoAfilClave = ? AND aseguradores.aseguradorId = ?";
	
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Collection<Catestados> getEstados() {
		//log.info("getEstados");
		Query query = getSession().createQuery(GET_ESTADOS);
		List<Catestados> catestados = query.list(); 
		return catestados;
	}

	@Override
	public Collection<Catmunicipios> getCatMunicipios(int i) {
		//log.info("getMunicipios");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_MUNICIPIOS);
		sqlQuery.setInteger(0, i);
		sqlQuery.addEntity(Catmunicipios.class);
		List<Catmunicipios> catmunicipios = sqlQuery.list(); 
		return catmunicipios;
	}
	
	@Override
	public Collection<Roles> getRoles(int i) {
		//log.info("getRoles");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_ROLES);
		sqlQuery.setInteger(0, i);
		sqlQuery.addEntity(Roles.class);
		List<Roles> roles = sqlQuery.list(); 
		return roles;
	}

	@Override
	public Collection<Catcolonias> getCatColonias(int i) {
		//log.info("getCatColonias");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_COLONIAS);
		sqlQuery.setInteger(0, i);
		sqlQuery.addEntity(Catcolonias.class);
		List<Catcolonias> catcolonias = sqlQuery.list(); 
		return catcolonias;
	}

	@Override
	public Catcolonias getColoniaById(int i) {
		//log.info("getColoniaById");
		Query query = getSession().createQuery(GET_COLONIA_BY_ID);
		query.setInteger(0, i);
		Catcolonias catcolonias = (Catcolonias) query.uniqueResult(); 
		return catcolonias;
	}

	@Override
	public Catestados getEstadoById(int i) {
		//log.info("getEstadoById");
		Query query = getSession().createQuery(GET_ESTADO_BY_ID);
		query.setInteger(0, i);
		Catestados catestados = (Catestados) query.uniqueResult(); 
		return catestados;
	}

	@Override
	public Catmunicipios getMunicipioById(int i) {
		//log.info("getMunicipioById");
		Query query = getSession().createQuery(GET_MUNICIPIO_BY_ID);
		query.setInteger(0, i);
		Catmunicipios catmunicipios = (Catmunicipios) query.uniqueResult(); 
		return catmunicipios;
	}
	
	@Override
	public Collection<Lugaresdeatencion> getLugarAtencion() {
		//log.info("getLugarAtencion");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_LUGAR_ATENCION);
		sqlQuery.addEntity(Lugaresdeatencion.class);
		List<Lugaresdeatencion> lugares = sqlQuery.list(); 
		return lugares;
	}
	
	@Override
	public Collection<Cattipoidentificador> getTipoIdentificador() {
		//log.info("getTipoIdentificador");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_TIPO_IDENTIFICADOR);
		sqlQuery.addEntity(Cattipoidentificador.class);
		List<Cattipoidentificador> tipoIdentificador = sqlQuery.list();  
		return tipoIdentificador;
	}

	@Override
	public Collection<Cattipoidentificador> getTipoIdentificadorPersonaConfianza() {
		//log.info("getTipoIdentificadorPersonaConfianza");
		Query query = getSession().createQuery(GET_TIPO_IDENTIFICADOR_PARA_PERSONA_CONFIANZA);
		List<Cattipoidentificador> cattipoidentificadors = query.list(); 
		return cattipoidentificadors;
	}

	@Override
	public Cattipoidentificador getCatTipoIdentificadorById(int idTipoIdentificador) {
		//log.info("getCatTipoIdentificadorById");
		Query query = getSession().createQuery(GET_TIPO_IDENTIFICADOR_BY_ID); 
		query.setInteger(0, idTipoIdentificador);
		return (Cattipoidentificador) query.uniqueResult();
	}

	@Override
	public Afiliadotipoidentificador getAfiliadoTipoIdentificador(Integer afiliadoId, int id) {
		//log.info("getAfiliadoTipoIdentificador");
		Query query = getSession().createQuery(GET_AFILIADO_TIPO_IDENTIFICADOR); 
		query.setInteger(0, id);
		query.setInteger(1, afiliadoId);
		return (Afiliadotipoidentificador) query.uniqueResult();
	}

	@Override
	public Collection<Cattipoidentificador> getTipoIdentificadorByAsegurador(int idAsegurador) {
		//log.info("getTipoIdentificadorByAsegurador");
		Query query = getSession().createQuery(GET_TIPO_IDENTIFICADOR_BY_ID_ASEGURADOR);
		query.setInteger(0, idAsegurador);
		Collection<Cattipoidentificador> cattipoidentificadors = query.list(); 
		return cattipoidentificadors;
	}

	@Override
	public Catsexos getCatSexos(int id) {
		//log.info("getCatSexos");
		Query query = getSession().createQuery(GET_CAT_SEXO_BY_ID);
		query.setInteger(0, id);
		Catsexos catsexos = (Catsexos) query.uniqueResult(); 
		return catsexos;
	}

	@Override
	public Cattipoafiliado getCatTipoAfiliadoByTipo(String parentesco, int idAsegurador) {
		//log.info("getCatTipoAfiliadoByTipo");
		Query query = getSession().createQuery(GET_CAT_TIPO_AFILIADO_BY_TIPO);
		query.setString(0, parentesco);
		query.setInteger(1, idAsegurador);
		Cattipoafiliado cattipoafiliado = (Cattipoafiliado) query.uniqueResult(); 
		return cattipoafiliado;
	}

	@Override
	public void saveAfiliadotipoIdentificar(Afiliadotipoidentificador afiliadotipoidentificador) {
		//log.info("saveAfiliadotipoIdentificar");
		getSession().save(afiliadotipoidentificador);
	}
	
	@Override
	public Collection<Catestadocivil> getEstadoCivil() {
		//log.info("getEstadoCivil");
		Query query = getSession().createQuery(GET_ESTADO_CIVIL);
		List<Catestadocivil> estados = query.list();
		return estados;
	}

	@Override
	public Collection<Catescolaridad> getEscolaridad() {
		//log.info("getEscolaridad");
		Query query = getSession().createQuery(GET_ESCOLARIDAD);
		List<Catescolaridad> escolaridad = query.list();
		return escolaridad;
	}
	
	@Override
	public Catescolaridad getEscolaridadById(int id) {
		//log.info("getCatEscolaridadById");
		Query query = getSession().createQuery(GET_ESCOLARIDAD_BY_ID);
		query.setInteger(0, id);
		Catescolaridad catescolaridad = (Catescolaridad) query.uniqueResult(); 
		return catescolaridad;
	}

	@Override
	public Catestadocivil getEstadoCivilById(int id) {
		//log.info("getCatEstadoCivilById");
		Query query = getSession().createQuery(GET_ESTADO_CIVIL_BY_ID);
		query.setInteger(0, id);
		Catestadocivil catestadocivil = (Catestadocivil) query.uniqueResult(); 
		return catestadocivil;
	}

	@Override
	public Cattipoafiliado getCatTipoAfiliadoByClave(int derechohabiente, int idAsegurador) {
		//log.info("getEscolaridad");
		Query query = getSession().createQuery(GET_CAT_TIPO_AFILIADO_BY_CLAVE);
		query.setInteger(0, derechohabiente);
		query.setInteger(1, idAsegurador);
		return (Cattipoafiliado) query.uniqueResult();
	}

	@Override
	public Collection<Catocupacion> getOcupacion() {
		//log.info("getOcupacion");
		Query query = getSession().createQuery(GET_OCUPACION);
		List<Catocupacion> ocupacion = query.list();
		return ocupacion;
	}
	
}
