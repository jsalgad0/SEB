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

import com.mx.sab.dao.ISignosDao;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catsignosvitalesadicionales;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.model.Signosvitalesadicionales;

@Service
@Log4j2
@Transactional
public class SignosDaoImpl implements ISignosDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_ATENCIONES = "FROM Atencionmedica a WHERE a.fechaAsistio = ? AND a.lugaresdeatencion.codigoLugarAtencion = ? AND a.afiliado.apellidoPaterno LIKE ? ";
	private static String GET_ATENCIONES_ATENDIDOS = "FROM Atencionmedica a WHERE a.fechaAsistio = ? AND a.lugaresdeatencion.codigoLugarAtencion = ? AND a.fechaAtendidoEnfermeria IS NOT NULL AND a.afiliado.apellidoPaterno LIKE ? ";
	private static String GET_ATENCIONES_POR_ATENDER = "FROM Atencionmedica a WHERE a.fechaAsistio = ? AND a.lugaresdeatencion.codigoLugarAtencion = ? AND a.fechaAtendidoEnfermeria IS NULL AND a.afiliado.apellidoPaterno LIKE ? ";
	private static String GET_ATENCIONES_BY_ID = "FROM Atencionmedica WHERE atencionMedicaId = ? ";
	private static String GET_SIGNOS_FOLIO = "SELECT MAX(signosVitalesId) AS folio FROM Signosvitales ";
	private static String GET_SIGNOS_BY_AFILIADO = "FROM Signosvitales WHERE afiliadoId = ? ";
	private static String GET_SIGNOS_BY_ATENCION = "FROM Signosvitales WHERE atencionMedicaId = ? ";
	private static String GET_ATENCIONES_COUNT = "SELECT COUNT(*) FROM Atencionmedica a WHERE a.fechaAsistio = ? AND a.lugaresdeatencion.codigoLugarAtencion = ? AND a.afiliado.apellidoPaterno LIKE ? ";
	private static String GET_ATENCIONES_ATENDIDOS_COUNT = "SELECT COUNT(*) FROM Atencionmedica a WHERE a.fechaAsistio = ? AND a.lugaresdeatencion.codigoLugarAtencion = ? AND a.fechaAtendidoEnfermeria IS NOT NULL AND a.afiliado.apellidoPaterno LIKE ? ";
	private static String GET_ATENCIONES_POR_ATENDER_COUNT = "SELECT COUNT(*) FROM Atencionmedica a WHERE a.fechaAsistio = ? AND a.lugaresdeatencion.codigoLugarAtencion = ? AND a.fechaAtendidoEnfermeria IS NULL AND a.afiliado.apellidoPaterno LIKE ? ";
	private static String GET_ULTIMOS_SIGNOS = "SELECT MAX(SignosVitalesId) FROM signosvitales WHERE AfiliadoId = ? ";
	private static String GET_SIGNOS_BY_ID = "FROM Signosvitales WHERE signosVitalesId = ? ";
	private static String GET_SIGNOS_VITALES_ADICIONALES_BY_ID = "FROM Signosvitalesadicionales WHERE id.signosVitalesId= ?";
	private static String GET_CAT_SIGNOS_VITALES_ADICIONALES_BY_ID_ASEGURADOR = "FROM Catsignosvitalesadicionales WHERE aseguradores.aseguradorId = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Collection<Atencionmedica> getAtenciones(String fecha, int idLugarAtencion, String busquedaApellido, int inicio, int fin) {
		//log.info("getAtencionesByDesc");
		Query query = getSession().createQuery(GET_ATENCIONES);
		query.setString(0, fecha);
		query.setInteger(1, idLugarAtencion);
		query.setString(2,"%"+busquedaApellido.trim()+"%");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.list();
	}
	
	@Override
	public Collection<Atencionmedica> getAtencionesAtendidos(String fecha, int idLugarAtencion, String busquedaApellido, int inicio, int fin) {
		//log.info("getAtencionesAtendidosByDesc");
		Query query = getSession().createQuery(GET_ATENCIONES_ATENDIDOS);
		query.setString(0, fecha);
		query.setInteger(1, idLugarAtencion);
		query.setString(2,"%"+busquedaApellido.trim()+"%");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		List<Atencionmedica> atenciones = query.list();
		return atenciones;
	}
	
	@Override
	public Collection<Atencionmedica> getAtencionesPorAtender(String fecha, int idLugarAtencion, String busquedaApellido, int inicio, int fin) {
		//log.info("getAtencionesPorAtenderByDesc");
		Query query = getSession().createQuery(GET_ATENCIONES_POR_ATENDER);
		query.setString(0, fecha);
		query.setInteger(1, idLugarAtencion);
		query.setString(2,"%"+busquedaApellido.trim()+"%");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		List<Atencionmedica> atenciones = query.list();
		return atenciones;
	}

	@Override
	public Atencionmedica getAtencionMedicaById(int idAtencion) {
		//log.info("getAtencionById");
		Query query = getSession().createQuery(GET_ATENCIONES_BY_ID);
		query.setInteger(0, idAtencion);
		Atencionmedica atencion = (Atencionmedica) query.uniqueResult();
		return atencion;
	}

	@Override
	public void save(Signosvitales signosvitales) {
		//log.info("save");
		getSession().save(signosvitales);
		getSession().refresh(signosvitales);
	}

	@Override
	public long getFolio() {
		//log.info("folio");
		SQLQuery query = getSession().createSQLQuery(GET_SIGNOS_FOLIO);
		long number = ((Number) query.uniqueResult()).intValue();
		return  (Long) number;
	}

	@Override
	public Signosvitales getAtencionAnterior(int idAfiliado) {
		//log.info("atencion");
		SQLQuery query = getSession().createSQLQuery(GET_SIGNOS_BY_AFILIADO);
		query.setInteger(0, idAfiliado);
		Signosvitales signos = (Signosvitales) query.uniqueResult();
		return  signos;		
	}

	@Override
	public Signosvitales getSignosByAtencion(int idAtencion) {
		//log.info("getSignosByIdAtencion");
		Query query = getSession().createQuery(GET_SIGNOS_BY_ATENCION);
		query.setInteger(0, idAtencion);
		Signosvitales signos = (Signosvitales) query.uniqueResult();
		return signos;
	}

	@Override
	public int getAtencionesCount(String fecha, int idLugarAtencion, String busquedaApellido) {
		//log.info("getAtencionesCount");
		Query query = getSession().createQuery(GET_ATENCIONES_COUNT);
		query.setString(0,fecha);
		query.setInteger(1, idLugarAtencion);
		query.setString(2,"%"+busquedaApellido.trim()+"%");
		return ((Number) query.uniqueResult()).intValue(); 
	}
	
	@Override
	public int getAtencionesAtendidosCount(String fecha, int idLugarAtencion, String busquedaApellido) {
		//log.info("getAtencionesAtendidosCount");
		Query query = getSession().createQuery(GET_ATENCIONES_ATENDIDOS_COUNT);
		query.setString(0,fecha);
		query.setInteger(1, idLugarAtencion);
		query.setString(2,"%"+busquedaApellido.trim()+"%");
		return ((Number) query.uniqueResult()).intValue(); 
	}
	
	@Override
	public int getAtencionesPorAtenderCount(String fecha, int idLugarAtencion, String busquedaApellido) {
		//log.info("getAtencionesPorAtenderCount");
		Query query = getSession().createQuery(GET_ATENCIONES_POR_ATENDER_COUNT);
		query.setString(0,fecha);
		query.setInteger(1, idLugarAtencion);
		query.setString(2,"%"+busquedaApellido.trim()+"%");
		return ((Number) query.uniqueResult()).intValue(); 
	}

	@Override
	public void update(Signosvitales signosvitales) {
		getSession().merge(signosvitales);
	}

	@Override
	public Object getUltimosSignos(int idAfiliado){
		SQLQuery query = getSession().createSQLQuery(GET_ULTIMOS_SIGNOS);
		query.setInteger(0, idAfiliado);
		return query.uniqueResult();
	}

	@Override
	public Signosvitales getSignosById(int idSignos) {
		Query query = getSession().createQuery(GET_SIGNOS_BY_ID);
		query.setInteger(0, idSignos);
		Signosvitales signos = (Signosvitales) query.uniqueResult();
		return signos;
	}

	@Override
	public Collection<Signosvitalesadicionales> getSignosVitalesAdicionales(Integer signosVitalesId) {
		Query query = getSession().createQuery(GET_SIGNOS_VITALES_ADICIONALES_BY_ID);
		query.setInteger(0, signosVitalesId);
		return query.list();
	}

	@Override
	public Collection<Catsignosvitalesadicionales> getCatSignosVitalesAdicionalesByIdAsegurador(Integer idAsegurador) {
		Query query = getSession().createQuery(GET_CAT_SIGNOS_VITALES_ADICIONALES_BY_ID_ASEGURADOR);
		query.setInteger(0, idAsegurador);
		return query.list();
	}

	@Override
	public void updateSignosVitalesAdicionales(Signosvitalesadicionales signosvitalesadicional) {
		//log.info("updateSignosVitalesAdicionales");
		getSession().update(signosvitalesadicional);
	}

	@Override
	public void saveSignosVitalesAdicionales(Signosvitalesadicionales signosvitalesadicional) {
		//log.info("saveSignosVitalesAdicionales");
		getSession().save(signosvitalesadicional);
	}

	@Override
	public void deleteSignosVitalesAdicionales(Signosvitalesadicionales signosvitalesadicional) {
		//log.info("deleteSignosVitalesAdicionales");
		getSession().delete(signosvitalesadicional);
		
	}
	

}
