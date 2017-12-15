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

import com.mx.sab.dao.IPacientesRecibidosDao;
import com.mx.sab.model.Atencionmedica;

@Log4j2
@Transactional
@Service
public class PacientesRecibidosDaoImpl implements IPacientesRecibidosDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_PACIENTES_RECIBIDOS = "FROM Atencionmedica WHERE fechaAsistio = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND afiliado.nombre LIKE ? AND afiliado.apellidoPaterno LIKE ? AND afiliado.apellidoMaterno LIKE ? ";
	private static String GET_PACIENTES_RECIBIDOS_COUNT = "SELECT count(*) FROM Atencionmedica WHERE fechaAsistio = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND afiliado.nombre LIKE ? AND afiliado.apellidoPaterno LIKE ? AND afiliado.apellidoMaterno LIKE ? ";
	private static String GET_PACIENTES_RECIBIDOS_BY_RECEP = "FROM Atencionmedica WHERE fechaAsistio = ? AND usuariosByUsuarioRecibioId.usuarioId = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND afiliado.nombre LIKE ? AND afiliado.apellidoPaterno LIKE ? AND afiliado.apellidoMaterno LIKE ? ";
	private static String GET_PACIENTES_RECIBIDOS_COUNT_BY_RECEP = "SELECT count(*) FROM Atencionmedica WHERE fechaAsistio = ? AND usuariosByUsuarioRecibioId.usuarioId = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND afiliado.nombre LIKE ? AND afiliado.apellidoPaterno LIKE ? AND afiliado.apellidoMaterno LIKE ? ";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	
	@Override
	public Collection<Atencionmedica> getAtenciones(Date fecha, int lugarAtencionId, int limitIni, int limitFin, String nombre, String apellidoPaterno, String apellidoMaterno) {
		//log.info("getatenciones");
		Query query = getSession().createQuery(GET_PACIENTES_RECIBIDOS);
		query.setDate(0, fecha);
		query.setInteger(1, lugarAtencionId);
		query.setString(2, "%"+nombre+"%");
		query.setString(3, "%"+apellidoPaterno+"%");
		query.setString(4, "%"+apellidoMaterno+"%");
		query.setFirstResult(limitIni);
		query.setMaxResults(limitFin);
		return query.list();
	}
	
	@Override
	public Collection<Atencionmedica> getAtencionesByRecep(Date fecha, int idUsuario, int lugarAtencionId, int limitIni, int limitFin, String nombre, String apellidoPaterno, String apellidoMaterno) {
		//log.info("getatenciones");
		Query query = getSession().createQuery(GET_PACIENTES_RECIBIDOS_BY_RECEP);
		query.setDate(0, fecha);
		query.setInteger(1, idUsuario);
		query.setInteger(2, lugarAtencionId);
		query.setString(3, "%"+nombre+"%");
		query.setString(4, "%"+apellidoPaterno+"%");
		query.setString(5, "%"+apellidoMaterno+"%");
		query.setFirstResult(limitIni);
		query.setMaxResults(limitFin);
		return query.list();
	}


	@Override
	public int getAtencionesCount(Date fecha, int lugarAtencionId, String nombre, String apellidoPaterno, String apellidoMaterno) {
		Query query = getSession().createQuery(GET_PACIENTES_RECIBIDOS_COUNT);
		query.setDate(0, fecha);
		query.setInteger(1, lugarAtencionId);
		query.setString(2, "%"+nombre+"%");
		query.setString(3, "%"+apellidoPaterno+"%");
		query.setString(4, "%"+apellidoMaterno+"%");
		return ((Number) query.uniqueResult()).intValue();
	}
	
	@Override
	public int getAtencionesCountByRecep(Date fecha, int idUsuario, int lugarAtencionId, String nombre, String apellidoPaterno, String apellidoMaterno) {
		Query query = getSession().createQuery(GET_PACIENTES_RECIBIDOS_COUNT_BY_RECEP);
		query.setDate(0, fecha);
		query.setInteger(1, idUsuario);
		query.setInteger(2, lugarAtencionId);
		query.setString(3, "%"+nombre+"%");
		query.setString(4, "%"+apellidoPaterno+"%");
		query.setString(5, "%"+apellidoMaterno+"%");
		return ((Number) query.uniqueResult()).intValue();
	}
	
	
}
