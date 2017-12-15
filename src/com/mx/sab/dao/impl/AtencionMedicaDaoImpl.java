package com.mx.sab.dao.impl;

import java.util.Collection;
import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IAtencionMedicaDao;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.Catestatusatencionidentidad;
import com.mx.sab.model.Catestatusatencionvigencia;
import com.mx.sab.model.Catestatusrecepcion;
import com.mx.sab.model.Catmotivos;
import com.mx.sab.model.Motivos;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.NotamedicaCies10;
import com.mx.sab.model.Prestacionesportomar;
import com.mx.sab.model.Tipoatencionmedica;

@Log4j2
@Transactional
@Service
public class AtencionMedicaDaoImpl implements IAtencionMedicaDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_AFILIADOS_PERMISOS_ESPECIALES_AFILIADOS_VIGENCIA_COUNT = "SELECT count(*) FROM atencionmedica WHERE EstatusVigenciaId = 1 AND LugarDeAtencionId = ? AND FechaAsistio = ?";
	private static String GET_AFILIADOS_PERMISOS_ESPECIALES_AFILIADOS_IDENTIDAD_COUNT = "SELECT count(*) FROM atencionmedica WHERE EstatusIdentidadId = 1 AND LugarDeAtencionId = ? AND FechaAsistio = ?";
	private static String GET_AFILIADOS_PERMISOS_ESPECIALES_AFILIADOS_VIGENCIA = "FROM Atencionmedica WHERE catestatusatencionvigencia.id = 1 AND lugaresdeatencion.lugarDeAtencionId = ? AND fechaAsistio = ?";
	private static String GET_AFILIADOS_PERMISOS_ESPECIALES_AFILIADOS_IDENTIDAD = "FROM Atencionmedica WHERE catestatusatencionidentidad.id = 1 AND lugaresdeatencion.lugarDeAtencionId = ? AND fechaAsistio = ?";
	private static String GET_ATENCION_MEDICA_BY_ID = "FROM Atencionmedica WHERE atencionMedicaId = ?";
	private static String GET_CAT_ESTATUS_ATENCION_VIGENCIA_BY_ID = "FROM Catestatusatencionvigencia WHERE id = ?";
	private static String GET_ESTATUS_ATENCION_IDENTIDAD_BY_ID = "FROM Catestatusatencionidentidad WHERE id = ?";
	private static String GET_CAT_MOTIVOS_BY_ID = "FROM Catmotivos WHERE id = ?";
	private static String GET_CAT_ESTATUS_RECEPCION_BY_ID = "FROM Catestatusrecepcion WHERE id = ?";
	private static String GET_ATENCIONES_PENDIENTES = "FROM Atencionmedica Where catestatusatencionidentidad.id != 4  AND catestatusatencionvigencia.id != 3  AND (catestatusatencionidentidad.id != 3  OR catestatusatencionvigencia.id != 2) AND catestatusfirmaByEstatusFirmaMedico.id = 2 AND lugaresdeatencion.lugarDeAtencionId = ? AND fechaAsistio = ? AND catestatusrecepcion.id = 2";
	private static String GET_ATENCIONES_PENDIENTES_COUNT = "SELECT count(*) FROM atencionmedica Where EstatusIdentidadId != 4 AND EstatusVigenciaId != 3  AND (EstatusIdentidadId != 3 OR EstatusVigenciaId != 2) AND EstatusFirmaMedico = 2 AND LugarDeAtencionId = ? AND FechaAsistio = ? AND EstatusRecepcionId = 2";
	private static String GET_PRESTACIONES_PENDIENTES_BY_ATENCION_MEDICA_ID = "FROM Atencionprestacion Where atencionMedicaId = ?";
	private static String GET_PRESTACIONES_POR_TOMAR_BY_ATENCION_MEDICA_ID = "FROM Prestacionesportomar Where atencionMedicaIdTomada = ?";
	private static String GET_ATENCION_PRESTACION_BY_PRINCIPAL = "FROM Atencionprestacion Where atencionMedicaId = ? AND principal = 1";
	private static String GET_ATENCIONES_HOY = "FROM Atencionmedica Where lugaresdeatencion.lugarDeAtencionId = ? AND fechaAsistio = ?";
	private static String GET_ATENCIONES_HOY_COUNT = "SELECT count(*) FROM atencionmedica Where LugarDeAtencionId = ? AND FechaAsistio = ?";
	private static String GET_ATENCION_MEDICA_BY_FOLIO = "FROM Atencionmedica WHERE folio LIKE ?";
	private static String GET_ATENCION_MEDICA_BY_AFILIADO_NAME = "FROM Atencionmedica WHERE fechaAsistio = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND afiliado.nombre LIKE ? AND afiliado.apellidoPaterno LIKE ? AND afiliado.apellidoMaterno LIKE ? ";
	private static String GET_NOTAS_MEDICAS_BY_ID_AFILIADO = "FROM NotamedicaCies10 WHERE notamedica.atencionmedica.afiliado.afiliadoId = ?";
	private static String GET_DIAGNOSTICOS_BY_ID_DIAGNOSTICO_COUNT = "SELECT count(*) FROM notamedica_cies10 AS noci, notamedica AS nota, atencionmedica AS aten WHERE noci.`CIE10Id` = ? AND noci.NotaMedicaId = nota.NotaMedicaId AND nota.AtencionMedicaId = aten.AtencionMedicaId AND aten.AfiliadoId = ?";
	private static String GET_DIAGNOSTICOS_BY_ID_DIAGNOSTICO = "FROM NotamedicaCies10 WHERE catcies10.cie10id = ? AND notamedica.atencionmedica.afiliado.afiliadoId = ?";
	private static String GET_NOTA_MEDICA_BY_ID_ATENCION = "FROM Notamedica WHERE atencionMedicaId = ?";
	private static String GET_TIPO_ATENCION_MEDICA_BY_ID = "FROM Tipoatencionmedica WHERE tipoAtencionMedicaId = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}
	
	@Override
	public int getAfiliadosPermisosEspecialesVigenciaCount(Integer lugarDeAtencionId) {
		//log.info("getPermisosEspecialesVigenciaCount");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_AFILIADOS_PERMISOS_ESPECIALES_AFILIADOS_VIGENCIA_COUNT);
		sqlQuery.setInteger(0, lugarDeAtencionId);
		sqlQuery.setDate(1, new Date());
		return ((Number) sqlQuery.uniqueResult()).intValue(); 
	}

	@Override
	public int getAfiliadosPermisosEspecialesIdentidadCount(Integer lugarDeAtencionId) {
		//log.info("getPermisosEspecialesIdentidadCount");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_AFILIADOS_PERMISOS_ESPECIALES_AFILIADOS_IDENTIDAD_COUNT);
		sqlQuery.setInteger(0, lugarDeAtencionId);
		sqlQuery.setDate(1, new Date());
		return ((Number) sqlQuery.uniqueResult()).intValue(); 
	}

	@Override
	public Collection<Atencionmedica> getAfiliadosPermisosEspecialesVigencia(int inicio, int fin, Integer lugarDeAtencionId) {
		//log.info("getAfiliadosPermisosEspecialesVigencia");
		Query query = getSession().createQuery(GET_AFILIADOS_PERMISOS_ESPECIALES_AFILIADOS_VIGENCIA);
		query.setInteger(0, lugarDeAtencionId);
		query.setDate(1, new Date());
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.list();	
	}

	@Override
	public Collection<Atencionmedica> getAfiliadosPermisosEspecialesIdentidad(int inicio, int fin, Integer lugarDeAtencionId) {
		//log.info("getAfiliadosPermisosEspecialesIdentidad");
		Query query = getSession().createQuery(GET_AFILIADOS_PERMISOS_ESPECIALES_AFILIADOS_IDENTIDAD);
		query.setInteger(0, lugarDeAtencionId);
		query.setDate(1, new Date());
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.list();	
	}

	@Override
	public Atencionmedica getAtencionMedicaById(int idAtencionMedica) {
		//log.info("getAtencionMedicaById");
		Query query = getSession().createQuery(GET_ATENCION_MEDICA_BY_ID);
		query.setInteger(0, idAtencionMedica);
		return (Atencionmedica) query.uniqueResult();
	}

	@Override
	public Catestatusatencionvigencia getCatEstatusAtencionVigenciaById(int id) {
		//log.info("getCatEstatusAtencionVigenciaById");
		Query query = getSession().createQuery(GET_CAT_ESTATUS_ATENCION_VIGENCIA_BY_ID);
		query.setInteger(0, id);
		return (Catestatusatencionvigencia) query.uniqueResult();
	}

	@Override
	public Catestatusatencionidentidad getCatEstatusAtencionIdentidadById(int id) {
		//log.info("getCatEstatusAtencionIdentidadById");
		Query query = getSession().createQuery(GET_ESTATUS_ATENCION_IDENTIDAD_BY_ID);
		query.setInteger(0, id);
		return (Catestatusatencionidentidad) query.uniqueResult();
	}

	@Override
	public void update(Atencionmedica atencionmedica) {
		//log.info("update");
		getSession().merge(atencionmedica);
	}

	@Override
	public int getAtencionesPendientesCount(Integer lugarDeAtencionId) {
		//log.info("getAtencionesPendientesCount");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_ATENCIONES_PENDIENTES_COUNT);
		sqlQuery.setInteger(0, lugarDeAtencionId);
		sqlQuery.setDate(1, new Date());
	  return ((Number) sqlQuery.uniqueResult()).intValue(); 
	}
	
	@Override
	public Collection<Atencionmedica> getAtencionesPendientes(int inicio, int fin, Integer lugarDeAtencionId) {
		//log.info("getAtencionesPendientes");
		Query query = getSession().createQuery(GET_ATENCIONES_PENDIENTES);
		query.setInteger(0, lugarDeAtencionId);
		query.setDate(1, new Date());
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.list();	
	}
	
	@Override
	public Collection<Atencionmedica> getAtencionesHoy(int inicio, int fin, Integer lugarDeAtencionId,Date fecha) {
		//log.info("getAtencionesHoy");
		Query query = getSession().createQuery(GET_ATENCIONES_HOY);
		query.setInteger(0, lugarDeAtencionId);
		query.setDate(1, fecha);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.list();	
	}
	
	@Override
	public int getAtencionesHoyCount(Integer lugarDeAtencionId,Date fecha) {
		//log.info("getAtencionesHoyCount");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_ATENCIONES_HOY_COUNT);
		sqlQuery.setInteger(0, lugarDeAtencionId);
		sqlQuery.setDate(1, fecha);
	  return ((Number) sqlQuery.uniqueResult()).intValue(); 
	}
	
	
	@Override
	public Catmotivos getCatMotivo(int id) {
		//log.info("getCatMotivo");
		Query query = getSession().createQuery(GET_CAT_MOTIVOS_BY_ID);
		query.setInteger(0, id);
		return (Catmotivos) query.uniqueResult();
	}

	@Override
	public void saveMotivo(Motivos motivos) {
		//log.info("saveMotivo");
		getSession().save(motivos);
	}
	
	@Override
	public Collection<Atencionprestacion> getPrestacionesPendientes(int idAtencionMedica) {
		//log.info("getPrestacionesPendientes");
		Query query = getSession().createQuery(GET_PRESTACIONES_PENDIENTES_BY_ATENCION_MEDICA_ID);
		query.setInteger(0, idAtencionMedica);
		return query.list();	
	}

	@Override
	public Collection<Prestacionesportomar> getPrestacionesPorTomar(int idAtencionMedica) {
		//log.info("getPrestacionesPorTomar");
		Query query = getSession().createQuery(GET_PRESTACIONES_POR_TOMAR_BY_ATENCION_MEDICA_ID);
		query.setInteger(0, idAtencionMedica);
		return query.list();			
	}
	
	@Override
	public void deleteAtencionPrestacion(Atencionprestacion atencionprestacion) {
		//log.info("deleteAtencionPrestacion");
		getSession().delete(atencionprestacion);
	}
	
	@Override
	public void updatePrestacionPorTomar(Prestacionesportomar prestacionportomar) {
		//log.info("update prestacion por tomar");
		getSession().merge(prestacionportomar);
	}
	
	@Override
	public Atencionprestacion getAtencionPrestacionByPrincipal(int idAtencionMedica) {
		//log.info("GET_ATENCION_PRESTACION_BY_PRINCIPAL");
		Query query = getSession().createQuery(GET_ATENCION_PRESTACION_BY_PRINCIPAL);
		query.setInteger(0, idAtencionMedica);
		return (Atencionprestacion) query.uniqueResult();
	}

	@Override
	public Catestatusrecepcion getCatEstatusRecepcion(int id) {
		//log.info("getCatMotivo");
		Query query = getSession().createQuery(GET_CAT_ESTATUS_RECEPCION_BY_ID);
		query.setInteger(0, id);
		return (Catestatusrecepcion) query.uniqueResult();
	}

	@Override
	public Atencionmedica getAtencionMedicaByFolio(String folio) {
		//log.info("getAtencionMedicaByFolio");
		Query query = getSession().createQuery(GET_ATENCION_MEDICA_BY_FOLIO);
		query.setString(0, folio);
		return (Atencionmedica) query.uniqueResult();
	}
	
	@Override
	public Collection<Atencionmedica> getAtencionMedicaByAfiliadoName(Date fecha, Integer lugarDeAtencionId, String nombre, String apellidoPaterno, String apellidoMaterno) {
		//log.info("GET_ATENCION_MEDICA_BY_AFILIADO_NAME");
		Query query = getSession().createQuery(GET_ATENCION_MEDICA_BY_AFILIADO_NAME);
		query.setDate(0, fecha);
		query.setInteger(1,lugarDeAtencionId);
		query.setString(2, "%"+nombre+"%");
		query.setString(3, "%"+apellidoPaterno+"%");
		query.setString(4, "%"+apellidoMaterno+"%");
		return query.list();	
	}

	@Override
	public Collection<NotamedicaCies10> getNotamedicaCies10ByIdAfiliado(int idAfiliado) {
		//log.info("getNotasMedicas");
		Query query = getSession().createQuery(GET_NOTAS_MEDICAS_BY_ID_AFILIADO);
		query.setInteger(0,idAfiliado);
		return query.list();
	}

	@Override
	public int getCountDiagnosticosByIdDiagnostico(int idAfiliado,int idDiagnostico) {
		//log.info("getCountDiagnosticosByIdDiagnostico");
		SQLQuery sqlQuery = getSession().createSQLQuery(GET_DIAGNOSTICOS_BY_ID_DIAGNOSTICO_COUNT);
		sqlQuery.setInteger(0, idDiagnostico);
		sqlQuery.setInteger(1, idAfiliado);
		return ((Number) sqlQuery.uniqueResult()).intValue(); 
	}

	@Override
	public Collection<NotamedicaCies10> getDiagnosticosByIdDiagnostico(int idAfiliado, int idDiagnostico, int inicio, int fin) {
		//log.info("getDiagnosticosByIdDiagnostico");
		Query query = getSession().createQuery(GET_DIAGNOSTICOS_BY_ID_DIAGNOSTICO);
		query.setInteger(0, idDiagnostico);
		query.setInteger(1, idAfiliado);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.list();
	}

	@Override
	public Notamedica getNotaMedicaByAtencionMedicaId(Integer idAtencionmedica) {
		//log.info("getNotaMedicaByAtencionMedicaId");
		Query query = getSession().createQuery(GET_NOTA_MEDICA_BY_ID_ATENCION);
		query.setInteger(0,idAtencionmedica);
		return (Notamedica) query.uniqueResult();
	}

	@Override
	public Tipoatencionmedica getTipoAtencionMedicaById(int tipoAtencionMedica) {
		//log.info("getTipoAtencionMedicaById");
		Query query = getSession().createQuery(GET_TIPO_ATENCION_MEDICA_BY_ID);
		query.setInteger(0,tipoAtencionMedica);
		return (Tipoatencionmedica) query.uniqueResult();
	}

}
