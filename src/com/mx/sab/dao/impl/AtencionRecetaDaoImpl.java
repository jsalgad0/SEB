package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IAtencionRecetaDao;
import com.mx.sab.model.Catestatusrecetas;
import com.mx.sab.model.Catmedicamentos;
import com.mx.sab.model.Catunidadesdetiempo;
import com.mx.sab.model.Catviasdeadminmedicamento;
import com.mx.sab.model.Medicamentosreceta;
import com.mx.sab.model.Recetas;

@Log4j2
@Transactional
@Service
public class AtencionRecetaDaoImpl implements IAtencionRecetaDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_RECETAS_BY_ID_ATENCION = "FROM Recetas WHERE atencionmedica.atencionMedicaId = ?";
	private static String GET_CAT_VIA_ADMIN_MEDICAMENTO = "FROM Catviasdeadminmedicamento";
	private static String GET_CAT_UNIDADES_TIEMPO_MENOR_DIA = "FROM Catunidadesdetiempo WHERE esMenorAunDia = 1 AND activo = 1";
	private static String GET_CAT_UNIDADES_TIEMPO_MAYOR_DIA = "FROM Catunidadesdetiempo WHERE esMenorAunDia = 0 AND activo = 1";
	private static String GET_CAT_MEDICAMENTOS = "FROM Catmedicamentos WHERE medicamento LIKE ? AND aseguradores.aseguradorId = ?";
	private static String GET_CAT_ESTATUS_RECETA_BY_ID = "FROM Catestatusrecetas WHERE estatusRecetasId = ?";
	private static String GET_RECETA_BY_ID = "FROM Recetas WHERE recetaId = ?";
	private static String GET_CAT_VIA_ADMIN_MEDICAMENTO_BY_ID = "FROM Catviasdeadminmedicamento WHERE viaDeAdmonMedicamentoId = ?";
	private static String GET_CAT_MEDICAMENTOS_BY_ID = "FROM Catmedicamentos WHERE medicamentoId = ?";
	private static String GET_CAT_UNIDADES_DE_TIEMPO_BY_ID = "FROM Catunidadesdetiempo WHERE unidadTiempoId = ?";
	private static String GET_MEDICAMENTO_RECETA_BY_ID = "FROM Medicamentosreceta WHERE id.recetaId = ?";
	

	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Collection<Recetas> getRecetasByIdAtencion(int idAtencion) {
		//log.info("getRecetasByIdAtencion");
		Query query = getSession().createQuery(GET_RECETAS_BY_ID_ATENCION);
		query.setInteger(0, idAtencion);
		return query.list();
	}

	@Override
	public Collection<Catviasdeadminmedicamento> getCatViaAdminMedicamento() {
		//log.info("getCatViaAdminMedicamento");
		Query query = getSession().createQuery(GET_CAT_VIA_ADMIN_MEDICAMENTO);
		return query.list();
	}

	@Override
	public Collection<Catunidadesdetiempo> getCatUnidadesTiempoMenorDia() {
		//log.info("getCatUnidadesTiempoMenorDia");
		Query query = getSession().createQuery(GET_CAT_UNIDADES_TIEMPO_MENOR_DIA);
		return query.list();
	}

	@Override
	public Collection<Catunidadesdetiempo> getCatUnidadesTiempoMayorDia() {
		//log.info("getCatUnidadesTiempoMayorDia");
		Query query = getSession().createQuery(GET_CAT_UNIDADES_TIEMPO_MAYOR_DIA);
		return query.list();
	}

	@Override
	public Collection<Catmedicamentos> getCatMedicamentos(String busqueda, Integer IdAsegurador) {
		//log.info("getCatMedicamentos");
		Query query = getSession().createQuery(GET_CAT_MEDICAMENTOS);
		query.setString(0, "%"+busqueda+"%");
		query.setInteger(1, IdAsegurador);
		return query.list();
	}

	@Override
	public Catestatusrecetas getCatEstatusReceta(int id) {
		//log.info("getCatEstatusReceta");
		Query query = getSession().createQuery(GET_CAT_ESTATUS_RECETA_BY_ID);
		query.setInteger(0, id);
		return (Catestatusrecetas) query.uniqueResult();
	}

	@Override
	public void saveReceta(Recetas recetas) {
		//log.info("saveReceta");
		getSession().save(recetas);
		getSession().refresh(recetas);
	}

	@Override
	public void saveMedicamentoReceta(Medicamentosreceta medicamentosreceta) {
		//log.info("saveMedicamentoReceta");
		getSession().save(medicamentosreceta);
	}

	@Override
	public Recetas getRecetasById(int idReceta) {
		//log.info("getRecetasById");
		Query query = getSession().createQuery(GET_RECETA_BY_ID);
		query.setInteger(0, idReceta);
		return (Recetas) query.uniqueResult();
	}

	@Override
	public void deleteReceta(Recetas recetas) {
		//log.info("deleteReceta");
		getSession().delete(recetas);
	}

	@Override
	public Catviasdeadminmedicamento getCatViaAdminMedicamentoById(int idUnidad) {
		//log.info("getCatViaAdminMedicamentoById");
		Query query = getSession().createQuery(GET_CAT_VIA_ADMIN_MEDICAMENTO_BY_ID);
		query.setInteger(0, idUnidad);
		return (Catviasdeadminmedicamento) query.uniqueResult();
	}

	@Override
	public Catmedicamentos getCatMedicamentoById(int idMedicamento) {
		//log.info("getCatMedicamentoById");
		Query query = getSession().createQuery(GET_CAT_MEDICAMENTOS_BY_ID);
		query.setInteger(0, idMedicamento);
		return (Catmedicamentos) query.uniqueResult();
	}

	@Override
	public Catunidadesdetiempo getCatUnidadesDeTiempoById(int idDurante) {
		//log.info("getCatUnidadesDeTiempoById");
		Query query = getSession().createQuery(GET_CAT_UNIDADES_DE_TIEMPO_BY_ID);
		query.setInteger(0, idDurante);
		return (Catunidadesdetiempo) query.uniqueResult();
	}

	@Override
	public void deleteMedicamentoReceta(Medicamentosreceta medicamentosreceta) {
		//log.info("deleteMedicamentoReceta");
		getSession().delete(medicamentosreceta);
	}

	@Override
	public Medicamentosreceta getMedicamentoRecetaById(int idReceta) {
		//log.info("getMedicamentoRecetaById");
		Query query = getSession().createQuery(GET_MEDICAMENTO_RECETA_BY_ID);
		query.setInteger(0, idReceta);
		return (Medicamentosreceta) query.uniqueResult();
	}

	@Override
	public void updateReceta(Recetas recetas) {
		//log.info("updateReceta");
		getSession().update(recetas);
	}
}
