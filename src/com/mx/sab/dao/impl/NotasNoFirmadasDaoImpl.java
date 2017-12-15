package com.mx.sab.dao.impl;

import java.util.Collection;
import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.INotasNoFirmadasDao;
import com.mx.sab.model.Catestatusfirma;
import com.mx.sab.model.Notamedica;

@Service
@Log4j2
@Transactional
public class NotasNoFirmadasDaoImpl implements INotasNoFirmadasDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_ATENCIONES_SIN_PACIENTE = "FROM Notamedica WHERE atencionmedica.fechaAsistio = ? AND atencionmedica.lugaresdeatencion.lugarDeAtencionId = ? AND atencionmedica.catestatusfirmaByEstatusFirmaPaciente.id = 4";
	private static String GET_FIRMA_PACIENTE = "SELECT firmaPaciente FROM Aseguradores WHERE AseguradorId = ?";
	private static String GET_CAT_ESTATUS_BY_ID = "FROM Catestatusfirma WHERE id = ? ";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Collection<Notamedica> getAtenciones(Date fecha,int idLugarAtencion) {
		Query query = getSession().createQuery(GET_ATENCIONES_SIN_PACIENTE);
		query.setDate(0, fecha);
		query.setInteger(1, idLugarAtencion);
		return query.list();
	}

	@Override
	public int getFirmaPaciente(int idAsegurador) {
		Query query = getSession().createQuery(GET_FIRMA_PACIENTE);
		query.setInteger(0, idAsegurador);
		int paciente = ((Number) query.uniqueResult()).intValue();
		return paciente;
	}

	@Override
	public Catestatusfirma getEstatusFirma(int idEstatusFirma) {
		//log.info("getEstatusFirmaById");
		Query query = getSession().createQuery(GET_CAT_ESTATUS_BY_ID);
		query.setInteger(0, idEstatusFirma);
		Catestatusfirma catestatusfirma = (Catestatusfirma) query.uniqueResult();
		return catestatusfirma;
	}

	

}
