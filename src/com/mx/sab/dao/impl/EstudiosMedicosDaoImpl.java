package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IEstudiosMedicosDao;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.Catpaginasestudios;
import com.mx.sab.model.Prestacionesportomar;

@Log4j2
@Transactional
@Service
public class EstudiosMedicosDaoImpl implements IEstudiosMedicosDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_PRESTACION_POR_TOMAR = "FROM Prestacionesportomar WHERE atencionmedicaByAtencionMedicaId.atencionMedicaId = ? AND catprestacion.prestacionId = ?";
	private static String GET_CAT_PAGINAS_ESTUDIOS_BY_ID = "FROM Catpaginasestudios WHERE idPagina = ?";
	private static String GET_PRESTACION_POR_TOMAR_BY_ESTUDIOS = "FROM Prestacionesportomar WHERE atencionmedicaByAtencionMedicaId.atencionMedicaId = ? AND catpaginasestudios.idPagina = ?";
	private static String GET_PRESTACION_POR_TOMAR_BY_ORDEN = "FROM Prestacionesportomar WHERE catprestacion.prestacionId = ? AND orden = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Prestacionesportomar getPrestacionPorTomar(Integer atencionMedicaId, Integer prestacionId) {
		//log.info("getPrestacionPorTomar");
		Query query = getSession().createQuery(GET_PRESTACION_POR_TOMAR);
		query.setInteger(0, atencionMedicaId);
		query.setInteger(1, prestacionId);
		return (Prestacionesportomar) query.uniqueResult();
	}

	@Override
	public void delete(Prestacionesportomar prestacionesportomar) {
		//log.info("delete");
		getSession().delete(prestacionesportomar);
	}

	@Override
	public Catpaginasestudios getCatPaginasEstudiosById(int id) {
		//log.info("getCatPaginasEstudiosById");
		Query query = getSession().createQuery(GET_CAT_PAGINAS_ESTUDIOS_BY_ID);
		query.setInteger(0, id);
		return (Catpaginasestudios) query.uniqueResult();
	}

	@Override
	public void savePrestacionesPorTomar(Prestacionesportomar prestacionesportomar) {
		//log.info("savePrestacionesPorTomar");
		getSession().save(prestacionesportomar);
		getSession().refresh(prestacionesportomar);
		
	}

	@Override
	public Collection<Prestacionesportomar> getPrestacionPorTomarByEstudios(Integer atencionMedicaId, int idEstudios) {
		//log.info("getPrestacionPorTomarByEstudios");
		Query query = getSession().createQuery(GET_PRESTACION_POR_TOMAR_BY_ESTUDIOS);
		query.setInteger(0, atencionMedicaId);
		query.setInteger(1, idEstudios);
		return query.list();
	}

	@Override
	public void deletePrestacionesPorTomar(Prestacionesportomar prestacionesportomar) {
		//log.info("deletePrestacionesPorTomar");
		getSession().delete(prestacionesportomar);
		
	}

	@Override
	public Prestacionesportomar getPrestacionPorTomarByOrden(String ordenServicio, int i) {
		//log.info("getPrestacionPorTomarByOrden");
		Query query = getSession().createQuery(GET_PRESTACION_POR_TOMAR_BY_ORDEN);
		query.setInteger(0, i);
		query.setString(1, ordenServicio);
		return (Prestacionesportomar) query.uniqueResult();
	}

	@Override
	public void updatePrestacionPorTomar(Prestacionesportomar prestacionesportomar) {
		//log.info("updatePrestacionPorTomar");
		getSession().update(prestacionesportomar);
		
	}
}
