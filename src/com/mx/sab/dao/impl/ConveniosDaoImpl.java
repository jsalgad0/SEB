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

import com.mx.sab.dao.IConveniosDao;
import com.mx.sab.form.ConveniosForm;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.ConvenioCuadroprestaciones;
import com.mx.sab.model.Convenios;

@Log4j2
@Transactional
@Service
public class ConveniosDaoImpl implements IConveniosDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_CONVENIOS = "FROM Convenios c WHERE c.prestadores.nombreRazonSocial like ? and c.lugaresdeatencion.descripcion like ? and c.aseguradores.nombreRazonSocial like ? and c.activo = 1";
	private static String GET_COUNT_CONVENIOS = "SELECT COUNT(c.convenioId) FROM Convenios c WHERE c.prestadores.nombreRazonSocial like ? and c.lugaresdeatencion.descripcion like ? and c.aseguradores.nombreRazonSocial like ? and c.activo = 1";
	private static String GET_CONVENIOS_BY_ID = "FROM Convenios WHERE convenioId = ?";
	private static String GET_COUNT_CONVENIOS_BY_NOMBRE = "SELECT COUNT(c.convenioId) FROM Convenios c WHERE c.convenio like ?";
	private static String GET_LAST_CORRELATIVO = "FROM Convenios ORDER BY correlativo DESC";
	private static String GET_CONVENIOS_BY_AGENDA = "FROM Convenios c WHERE c.prestadores.prestadorId = ? AND c.aseguradores.aseguradorId = ? AND c.lugaresdeatencion.lugarDeAtencionId = ? AND c.activo = 1";
	private static String GET_CONVENIOS_BY_IDS = "FROM Convenios c WHERE c.aseguradores.aseguradorId = ? AND c.prestadores.prestadorId = ? AND c.lugaresdeatencion.codigoLugarAtencion = ? AND c.activo = 1";
	private static String GET_CONVENIO_BY_CARACTERISTICAS = "FROM Convenios WHERE lugaresdeatencion.lugarDeAtencionId = ? AND aseguradores.aseguradorId = ? AND prestadores.prestadorId = ? AND convenio = ? AND identificador = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}
	
	@Override
	public int getConveniosCount(String busquedaP, String busquedaL, String busquedaA) {
		//log.info("getConveniosCount");
		Query query = getSession().createQuery(GET_COUNT_CONVENIOS);
		query.setString(0, "%"+busquedaP.trim()+"%");
		query.setString(1, "%"+busquedaL.trim()+"%");
		query.setString(2, "%"+busquedaA.trim()+"%");
		return ((Number) query.uniqueResult()).intValue(); 
	}

	@Override
	public Collection<Convenios> getConvenios(String busquedaP, String busquedaL, String busquedaA, int inicio, int fin) {
		//log.info("getConvenios");
		Query query = getSession().createQuery(GET_CONVENIOS);
		query.setString(0, "%"+busquedaP.trim()+"%");
		query.setString(1, "%"+busquedaL.trim()+"%");
		query.setString(2, "%"+busquedaA.trim()+"%");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		List<Convenios> convenios = query.list();
		return convenios;
	}

	@Override
	public Convenios getConveniosById(Integer id) {
		//log.info("getConveniosById");
		Query query = getSession().createQuery(GET_CONVENIOS_BY_ID);
		query.setInteger(0, id);
		Convenios convenios = (Convenios) query.uniqueResult(); 
		return convenios;
	}

	@Override
	public void update(Convenios convenios) {
		//log.info("update");
		getSession().update(convenios);
	}

	@Override
	public Convenios save(Convenios convenios) {
		//log.info("save");
		getSession().save(convenios);
		getSession().refresh(convenios);
		return convenios;
	}

	@Override
	public int getConveniosCount(String acronimo) {
		//log.info("getConveniosCount");
		Query query = getSession().createQuery(GET_COUNT_CONVENIOS_BY_NOMBRE);
		query.setString(0, "%"+acronimo.trim()+"%");
		return ((Number) query.uniqueResult()).intValue(); 
	}

	@Override
	public Convenios getLastConvenio() {
		//log.info("getLastCorrelativo");
		Query query = getSession().createQuery(GET_LAST_CORRELATIVO);
		query.setMaxResults(1);
		Convenios convenios = (Convenios) query.uniqueResult();
		return convenios;
	}

	@Override
	public void saveConvenioCuadroPrestaciones(ConvenioCuadroprestaciones convenioCuadroprestaciones) {
		//log.info("saveConvenioCuadroPrestaciones");
		getSession().save(convenioCuadroprestaciones);
	}

	@Override
	public Collection<Convenios> getConveniosByAgenda(Agenda agenda) {
		Query query = getSession().createQuery(GET_CONVENIOS_BY_AGENDA);
		//log.info("agenda.getPrestadores().getPrestadorId() ="+agenda.getPrestadores().getPrestadorId());
		//log.info("agenda.getAseguradores().getAseguradorId() ="+agenda.getAseguradores().getAseguradorId());
		//log.info("agenda.getLugaresdeatencion().getLugarDeAtencionId() ="+agenda.getLugaresdeatencion().getLugarDeAtencionId());
		query.setInteger(0, agenda.getPrestadores().getPrestadorId());
		query.setInteger(1, agenda.getAseguradores().getAseguradorId());
		query.setInteger(2, agenda.getLugaresdeatencion().getLugarDeAtencionId());
		Collection<Convenios> convenios = query.list();
		return convenios;
	}

	@Override
	public Collection<Convenios> getConveniosByIds(int idAsegurador, int idPrestador, int tx_Marca) {
		Query query = getSession().createQuery(GET_CONVENIOS_BY_IDS);
		query.setInteger(0, idAsegurador);
		query.setInteger(1, idPrestador);
		query.setInteger(2, tx_Marca);
		Collection<Convenios> convenios = query.list();
		return convenios;
	}

	@Override
	public void deleteConvenioById(Convenios convenios) {
		//log.info("deleteConvenioById");
		getSession().delete(convenios);
	}

	@Override
	public Convenios existeConvenio(ConveniosForm conveniosForm) {
		Query query = getSession().createQuery(GET_CONVENIO_BY_CARACTERISTICAS);
		query.setInteger(0, conveniosForm.getIdLugarDeAtencion());
		query.setInteger(1, conveniosForm.getIdAsegurador());
		query.setInteger(2, conveniosForm.getIdPrestador());
		query.setString(3, conveniosForm.getConvenio());
		query.setString(4, conveniosForm.getIdentificadorConvenio());
		return (Convenios) query.uniqueResult();
	}

	@Override
	public Collection<Convenios> getConvenios(ConveniosForm conveniosForm) {
		String queryS = "FROM Convenios WHERE ";
		
		if (conveniosForm.getIdLugarDeAtencion()!=0) {
			queryS = queryS + "lugaresdeatencion.lugarDeAtencionId = ? AND ";
		}
		if (conveniosForm.getIdAsegurador()!=-1) {
			queryS = queryS + "aseguradores.aseguradorId = ? AND ";
		}
		if (conveniosForm.getIdPrestador()!=-1) {
			queryS = queryS + "prestadores.prestadorId = ? AND ";
		}
		queryS = queryS.substring(0, queryS.length()-4);
		Query query = getSession().createQuery(queryS);
		
		if (conveniosForm.getIdLugarDeAtencion()!=0) {
			query.setInteger(0, conveniosForm.getIdLugarDeAtencion());
		}
		if (conveniosForm.getIdAsegurador()!=-1) {
			if (conveniosForm.getIdLugarDeAtencion()!=0) {
				query.setInteger(1, conveniosForm.getIdAsegurador());	
			}else{
				query.setInteger(0, conveniosForm.getIdAsegurador());
			}
			
		}
		if (conveniosForm.getIdPrestador()!=-1) {
			if (conveniosForm.getIdLugarDeAtencion()!=0) {
				if (conveniosForm.getIdAsegurador()!=-1) {
					query.setInteger(2, conveniosForm.getIdPrestador());		
				}else{
					query.setInteger(1, conveniosForm.getIdPrestador());
				}		
			}else{
				if (conveniosForm.getIdAsegurador()!=-1) {
					query.setInteger(1, conveniosForm.getIdPrestador());
				}else{
					query.setInteger(0, conveniosForm.getIdPrestador());
				}
			}
			
		}
		return query.list();	
	}

	@Override
	public void delete(Convenios convenios) {
		//log.info("delete");
		getSession().delete(convenios);
	}

}
