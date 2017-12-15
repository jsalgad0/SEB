package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IPrestacionesDao;
import com.mx.sab.model.Bitacoracargaprestacion;
import com.mx.sab.model.Catestatusarchivoprestaciones;
import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.Catvigencia;
import com.mx.sab.model.CuadroprestacionPrestacion;
import com.mx.sab.model.Detallebitacoracargaprestaciones;
import com.mx.sab.model.Prestacionasegurador;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.model.Prestacionesprestadorequivalencias;
import com.mx.sab.model.Prestacionprestador;

@Log4j2
@Transactional
@Service
public class PrestacionesDaoImpl implements IPrestacionesDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_PRESTACIONES_BY_PRESTADOR = "FROM Prestacionprestador WHERE prestadores.prestadorId = ?";
	private static String GET_PRESTACIONES_BY_ASEGURADOR = "FROM Prestacionasegurador WHERE aseguradores.aseguradorId = ?";
	private static String GET_PRESTACION_BY_CODIGO = "FROM Catprestacion WHERE codigo = ?";
	private static String GET_PRESTACION_BY_ID = "FROM Catprestacion WHERE prestacionId = ?";
	private static String GET_PRESTACION_PRESTADOR_BY_CODIGO = "FROM Prestacionprestador WHERE codigo = ?";
	private static String GET_PRESTACION_ASEGURADOR_BY_CODIGO = "FROM Prestacionasegurador WHERE codigo = ?";
	private static String GET_PRESTACION_ASEGURADOR_BY_ID = "FROM Prestacionasegurador WHERE prestacionAseguradorId = ?";
	private static String GET_PRESTACIONES_PRESTADOR_EQUIVALENCIAS = "FROM Prestacionesprestadorequivalencias WHERE id.prestacionPrestadorId = ?";
	private static String GET_CAT_PRESTACION_BY_DESCRIPCION = "FROM Catprestacion WHERE descripcion LIKE ?";
	private static String GET_PRESTACION_BY_DESCRIPCION = "FROM Prestacionprestador WHERE descripcion LIKE ? AND prestadores.prestadorId = ?";
	private static String GET_PRESTACION_EQUIVALENCIA_BY_PRESTACION_ASEGURADOR_ID = "FROM Prestacionesaseguradorequivalencias WHERE id.prestacionAseguradorId = ?";
	private static String GET_PRESTACIONES_ASEGURADOR = "SELECT prea.* FROM prestacionasegurador AS prea, prestacionesaseguradorequivalencias AS preae, cuadroprestacion_prestacion AS cup WHERE prea.AseguradorId = ?  AND prea.PrestacionAseguradorId = preae.PrestacionAseguradorId AND prea.Descripcion LIKE ? AND cup.PrestacionId = preae.PrestacionSABId AND cup.CuadroPrestacionId = ?";
//	private static String GET_PRESTACIONES_ASEGURADOR_ESTUDIOS = "SELECT prea.* FROM prestacionasegurador AS prea, prestacionesaseguradorequivalencias AS preae, cuadroprestacion_prestacion AS cup, catprestacion AS cp	WHERE prea.AseguradorId = ? AND prea.PrestacionAseguradorId = preae.PrestacionAseguradorId AND prea.Descripcion LIKE ? AND cup.PrestacionId = preae.PrestacionSABId AND cup.CuadroPrestacionId = ? AND cp.PrestacionId = cup.PrestacionId AND cp.ServicioId = ?";
//	private static String GET_PRESTACIONES_ASEGURADOR_ESTUDIOS_EXTRA = "SELECT prea.* FROM prestacionasegurador AS prea, prestacionesaseguradorequivalencias AS preae, cuadroprestacion_prestacion AS cup, catprestacion AS cp	WHERE prea.AseguradorId = ? AND prea.PrestacionAseguradorId = preae.PrestacionAseguradorId AND prea.Descripcion LIKE ? AND cup.PrestacionId = preae.PrestacionSABId AND cup.CuadroPrestacionId = ? AND cp.PrestacionId = cup.PrestacionId AND (cp.ServicioId = ? OR cp.ServicioId = ?)";
	private static String GET_PRESTACIONES_ESTUDIOS = "SELECT cp.* FROM prestacionasegurador AS prea, prestacionesaseguradorequivalencias AS preae, catprestacion AS cp WHERE prea.AseguradorId = ? AND prea.PrestacionAseguradorId = preae.PrestacionAseguradorId AND cp.PrestacionId = preae.PrestacionSABId AND cp.Descripcion LIKE ? AND prea.ServicioId = ?";
	private static String GET_PRESTACIONES_ESTUDIOS_EXTRA = "SELECT cp.* FROM prestacionasegurador AS prea, prestacionesaseguradorequivalencias AS preae, catprestacion AS cp WHERE prea.AseguradorId = ? AND prea.PrestacionAseguradorId = preae.PrestacionAseguradorId AND cp.PrestacionId = preae.PrestacionSABId AND cp.Descripcion LIKE ? AND (prea.ServicioId = ? OR prea.ServicioId = ?)";	
//	private static String GET_PRESTACIONES_ASEGURADOR_ESTUDIOS_CODIGO = "SELECT prea.* FROM prestacionasegurador AS prea, prestacionesaseguradorequivalencias AS preae, cuadroprestacion_prestacion AS cup, catprestacion AS cp	WHERE prea.AseguradorId = ? AND prea.PrestacionAseguradorId = preae.PrestacionAseguradorId AND prea.Codigo LIKE ? AND cup.PrestacionId = preae.PrestacionSABId AND cup.CuadroPrestacionId = ? AND cp.PrestacionId = cup.PrestacionId AND cp.ServicioId = ?";
	private static String GET_PRESTACIONES_ESTUDIOS_CODIGO = "SELECT cp.* FROM prestacionasegurador AS prea, prestacionesaseguradorequivalencias AS preae, catprestacion AS cp WHERE prea.AseguradorId = ? AND prea.PrestacionAseguradorId = preae.PrestacionAseguradorId AND cp.PrestacionId = preae.PrestacionSABId AND cp.Codigo LIKE ? AND prea.ServicioId = ?";
	private static String GET_LAST_PRESTACION_BY_ASEGURADOR_ID = "FROM Prestacionasegurador WHERE aseguradores.aseguradorId = ? ORDER BY prestacionAseguradorId DESC";
	private static String GET_CAT_ESTATUS_ARCHIVO_BY_ID = "FROM Catestatusarchivoprestaciones WHERE id = ?";
	private static String GET_BITACORA_BY_ASEGURADOR_ID = "FROM Bitacoracargaprestacion WHERE aseguradores.aseguradorId = ? ORDER BY id DESC";
//	private static String GET_PRESTACIONES_ASEGURADOR_ESTUDIOS_CODIGO_EXTRA = "SELECT prea.* FROM prestacionasegurador AS prea, prestacionesaseguradorequivalencias AS preae, cuadroprestacion_prestacion AS cup, catprestacion AS cp	WHERE prea.AseguradorId = ? AND prea.PrestacionAseguradorId = preae.PrestacionAseguradorId AND prea.Codigo LIKE ? AND cup.PrestacionId = preae.PrestacionSABId AND cup.CuadroPrestacionId = ? AND cp.PrestacionId = cup.PrestacionId AND (cp.ServicioId = ? OR cp.ServicioId = ?)";
	private static String GET_PRESTACIONES_ESTUDIOS_CODIGO_EXTRA = "SELECT cp.* FROM prestacionasegurador AS prea, prestacionesaseguradorequivalencias AS preae, catprestacion AS cp WHERE prea.AseguradorId = ? AND prea.PrestacionAseguradorId = preae.PrestacionAseguradorId AND cp.PrestacionId = preae.PrestacionSABId AND cp.Codigo LIKE ? AND (prea.ServicioId = ? OR prea.ServicioId = ?)";
	private static String GET_CAT_PRESTACION = "FROM Catprestacion WHERE descripcion LIKE ?";
	
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public void savePrestacionesAsegurador(Prestacionasegurador prestacionasegurador) {
		//log.info("savePrestacionesAsegurador");
		getSession().save(prestacionasegurador);
	}

	@Override
	public void savePrestacionesPrestador(Prestacionprestador prestacionprestador) {
		//log.info("savePrestacionesPrestador");
		getSession().save(prestacionprestador);		
	}

	@Override
	public Collection<Prestacionprestador> getPrestacionesByPrestador(Integer idPrestadores) {
		//log.info("getPrestacionesByPrestador");
		Query query = getSession().createQuery(GET_PRESTACIONES_BY_PRESTADOR);
		query.setInteger(0, idPrestadores);
		Collection<Prestacionprestador> prestacionprestadors = query.list();
		return prestacionprestadors;
	}

	@Override
	public Collection<Prestacionasegurador> getPrestacionesByAsegurador(Integer idAseguradores) {
		//log.info("getPrestacionesByAsegurador");
		Query query = getSession().createQuery(GET_PRESTACIONES_BY_ASEGURADOR);
		query.setInteger(0, idAseguradores);
		Collection<Prestacionasegurador> prestacionaseguradors = query.list();
		return prestacionaseguradors;
	}

	@Override
	public void saveEquivalenciasAsegurador(Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias) {
		//log.info("saveEquivalenciasAsegurador");
		getSession().save(prestacionesaseguradorequivalencias);	
		
	}

	@Override
	public void saveEquivalenciasPrestador(Prestacionesprestadorequivalencias prestacionesprestadorequivalencias) {
		//log.info("saveEquivalenciasPrestador");
		getSession().save(prestacionesprestadorequivalencias);	
	}

	@Override
	public Catprestacion getCatPrestacion(String prestacion) {
		//log.info("getCatPrestacion");
		Query query = getSession().createQuery(GET_PRESTACION_BY_CODIGO);
		query.setString(0, prestacion);
		Catprestacion catprestacion = (Catprestacion) query.uniqueResult();
		return catprestacion;
	}

	@Override
	public Prestacionprestador getPrestacionPrestador(String prestacion) {
		//log.info("getPrestacionPrestador");
		Query query = getSession().createQuery(GET_PRESTACION_PRESTADOR_BY_CODIGO);
		query.setString(0, prestacion);
		Prestacionprestador prestacionprestador = (Prestacionprestador) query.uniqueResult();
		return prestacionprestador;
	}

	@Override
	public Prestacionesprestadorequivalencias getPrestacionesPrestadorEquivalencias(Integer prestacionPrestadorId) {
		//log.info("getPrestacionesPrestadorEquivalencias");
		Query query = getSession().createQuery(GET_PRESTACIONES_PRESTADOR_EQUIVALENCIAS);
		query.setInteger(0, prestacionPrestadorId);
		Prestacionesprestadorequivalencias prestacionesprestadorequivalencias = (Prestacionesprestadorequivalencias) query.uniqueResult();
		return prestacionesprestadorequivalencias;
	}

	@Override
	public Catprestacion getCatPrestacionById(int idPrestacion) {
		//log.info("getCatPrestacionById");
		Query query = getSession().createQuery(GET_PRESTACION_BY_ID);
		query.setInteger(0, idPrestacion);
		Catprestacion catprestacion = (Catprestacion) query.uniqueResult();
		return catprestacion;
	}

	@Override
	public Collection<Catprestacion> getCatPrestacionByDescripcion(String busqueda) {
		//log.info("getCatPrestacionByDescripcion");
		Query query = getSession().createQuery(GET_CAT_PRESTACION_BY_DESCRIPCION);
		query.setString(0, "%"+busqueda.trim()+"%");
		query.setMaxResults(10);
		return query.list();
	}

	@Override
	public Collection<Prestacionprestador> getPrestacionesByDescripcion(Integer prestadorId, String busqueda) {
		//log.info("getPrestacionesByDescripcion");
		Query query = getSession().createQuery(GET_PRESTACION_BY_DESCRIPCION);
		query.setString(0, "%"+busqueda.trim()+"%");
		query.setInteger(1, prestadorId);
		query.setMaxResults(10);
		return query.list();
	}

	@Override
	public Prestacionasegurador getPrestacionAsegurador(String prestacion) {
		//log.info("getPrestacionAsegurador");
		Query query = getSession().createQuery(GET_PRESTACION_ASEGURADOR_BY_CODIGO);
		query.setString(0, prestacion);
		Prestacionasegurador prestacionasegurador = (Prestacionasegurador) query.uniqueResult();
		return prestacionasegurador;
	}

	@Override
	public Prestacionasegurador getPrestacionAseguradorById(int idPrestacion) {
		//log.info("getPrestacionAseguradorById");
		Query query = getSession().createQuery(GET_PRESTACION_ASEGURADOR_BY_ID);
		query.setInteger(0, idPrestacion);
		Prestacionasegurador prestacionasegurador = (Prestacionasegurador) query.uniqueResult();
		return prestacionasegurador;
	}
	
	@Override
	public Prestacionesaseguradorequivalencias getPrestacionEquivalenciaByPrestacionAseguradorId(int idPrestacion) {
		//log.info("getPrestacionAseguradorById");
		Query query = getSession().createQuery(GET_PRESTACION_EQUIVALENCIA_BY_PRESTACION_ASEGURADOR_ID);
		query.setInteger(0, idPrestacion);
		Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) query.uniqueResult();
		return prestacionesaseguradorequivalencias;
	}

	@Override
	public Collection<Prestacionasegurador> getPrestacionesAsegurador(Integer aseguradorId, int idCuadroPrestaciones, String busqueda) {
		//log.info("getPrestacionesAsegurador");
		SQLQuery query = getSession().createSQLQuery(GET_PRESTACIONES_ASEGURADOR);
		query.setInteger(0, aseguradorId);
		query.setString(1, "%"+busqueda+"%");
		query.setInteger(2, idCuadroPrestaciones);
		query.addEntity(Prestacionasegurador.class);
		return query.list();
	}

//	@Override
//	public Collection<Prestacionasegurador> getPrestacionesAseguradorEstudios(Integer aseguradorId, int idCuadroPrestaciones, String busqueda, int idServicio, int idServicioExtra) {
//		//log.info("getPrestacionesAseguradorEstudios");
//		SQLQuery query = null;
//		if (idServicioExtra!=0) {
//			query = getSession().createSQLQuery(GET_PRESTACIONES_ASEGURADOR_ESTUDIOS_EXTRA);	
//		}else {
//			query = getSession().createSQLQuery(GET_PRESTACIONES_ASEGURADOR_ESTUDIOS);
//		}		
//		query.setInteger(0, aseguradorId);
//		query.setString(1, "%"+busqueda+"%");
//		query.setInteger(2, idCuadroPrestaciones);
//		query.setInteger(3, idServicio);
//		if (idServicioExtra!=0) {
//			query.setInteger(4, idServicioExtra);	
//		}
//		query.addEntity(Prestacionasegurador.class);
//		return query.list();
//	}
//
//	@Override
//	public Collection<Prestacionasegurador> getPrestacionesAseguradorEstudiosCodigo(Integer aseguradorId, int idCuadroPrestaciones, String busqueda, int idServicio, int idServicioExtra) {
//		//log.info("getPrestacionesAseguradorEstudiosCodigo");
//		SQLQuery query = null;
//		if (idServicioExtra!=0) {
//			query = getSession().createSQLQuery(GET_PRESTACIONES_ASEGURADOR_ESTUDIOS_CODIGO_EXTRA);	
//		}else {
//			query = getSession().createSQLQuery(GET_PRESTACIONES_ASEGURADOR_ESTUDIOS_CODIGO);
//		}
//		query.setInteger(0, aseguradorId);
//		query.setString(1, "%"+busqueda+"%");
//		query.setInteger(2, idCuadroPrestaciones);
//		query.setInteger(3, idServicio);
//		if (idServicioExtra!=0) {
//			query.setInteger(4, idServicioExtra);	
//		}		
//		query.addEntity(Prestacionasegurador.class);
//		return query.list();
//	}
	
	@Override
	public Collection<Catprestacion> getPrestacionesEstudios(Integer aseguradorId, String busqueda, int idServicio, int idServicioExtra) {
		//log.info("getPrestacionesEstudios");
		SQLQuery query = null;
		if (idServicioExtra!=0) {
			query = getSession().createSQLQuery(GET_PRESTACIONES_ESTUDIOS_EXTRA);	
		}else {
			query = getSession().createSQLQuery(GET_PRESTACIONES_ESTUDIOS);
		}		
		query.setInteger(0, aseguradorId);
		query.setString(1, "%"+busqueda+"%");
		query.setInteger(2, idServicio);
		if (idServicioExtra!=0) {
			query.setInteger(3, idServicioExtra);	
		}
		query.addEntity(Catprestacion.class);
		return query.list();
	}

	@Override
	public Collection<Catprestacion> getPrestacionesEstudiosCodigo(Integer aseguradorId, String busqueda, int idServicio, int idServicioExtra) {
		//log.info("getPrestacionesEstudiosCodigo");
		SQLQuery query = null;
		if (idServicioExtra!=0) {
			query = getSession().createSQLQuery(GET_PRESTACIONES_ESTUDIOS_CODIGO_EXTRA);	
		}else {
			query = getSession().createSQLQuery(GET_PRESTACIONES_ESTUDIOS_CODIGO);
		}
		query.setInteger(0, aseguradorId);
		query.setString(1, "%"+busqueda+"%");
		query.setInteger(2, idServicio);
		if (idServicioExtra!=0) {
			query.setInteger(3, idServicioExtra);	
		}		
		query.addEntity(Catprestacion.class);
		return query.list();
	}	

	@Override
	public Prestacionasegurador getLastPrestacionAseguradorByIdAsegurador(int idAsegurador) {
		//log.info("getLastPrestacionByAseguradorId");
		Query query = getSession().createQuery(GET_LAST_PRESTACION_BY_ASEGURADOR_ID);
		query.setInteger(0, idAsegurador);
		query.setMaxResults(1);
		Prestacionasegurador prestacionasegurador = (Prestacionasegurador) query.uniqueResult();
		return prestacionasegurador;
	}

	@Override
	public Catestatusarchivoprestaciones getCatEstatus(int estatusId) {
		//log.info("getCatVigencia");
		Query query = getSession().createQuery(GET_CAT_ESTATUS_ARCHIVO_BY_ID);
		query.setInteger(0, estatusId); 
		return (Catestatusarchivoprestaciones) query.uniqueResult();
		}

	@Override
	public void saveBitacoraArchivoPrestaciones(Bitacoracargaprestacion bitacoracargaprestacion) {
		//log.info("saveBitacora");
		getSession().save(bitacoracargaprestacion);		
	}

	@Override
	public Bitacoracargaprestacion getLastBitacoraByIdAsegurador(int idAsegurador) {
		//log.info("get ultima Bitacora");
		Query query = getSession().createQuery(GET_BITACORA_BY_ASEGURADOR_ID);
		query.setInteger(0, idAsegurador);
		query.setMaxResults(1);
		Bitacoracargaprestacion bitacora = (Bitacoracargaprestacion) query.uniqueResult();
		
		return bitacora;
	}

	@Override
	public Collection<Catprestacion> getPrestacionesSAB(String busqueda) {
		//log.info("getPrestacionesSAB");
		Query query = getSession().createQuery(GET_CAT_PRESTACION);
		query.setString(0, "%"+busqueda.trim()+"%");
		query.setMaxResults(20);
		return query.list();
	}
	


	@Override
	public void saveDetalleBitacora(Detallebitacoracargaprestaciones detalle) {
		//log.info("saveDetalleBitacora");
		getSession().save(detalle);		
	}

	@Override
	public Collection<Prestacionasegurador> getPrestacionesAseguradorEstudios(Integer aseguradorId,
			int idCuadroPrestaciones, String busqueda, int idServicio, int idServicioExtra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Prestacionasegurador> getPrestacionesAseguradorEstudiosCodigo(Integer aseguradorId,
			int idCuadroPrestaciones, String busqueda, int idServicio, int idServicioExtra) {
		// TODO Auto-generated method stub
		return null;
	}

}
