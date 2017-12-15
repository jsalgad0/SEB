package com.mx.sab.dao.impl;

import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IRecetaMedicaDao;
import com.mx.sab.model.Catestatusrecetas;
import com.mx.sab.model.Catmedicamentos;
import com.mx.sab.model.Catunidadesdetiempo;
import com.mx.sab.model.Catviasdeadminmedicamento;
import com.mx.sab.model.Medicamentosreceta;
import com.mx.sab.model.Recetas;

@Log4j2
@Transactional
@Service
public class RecetaMedicaDaoImpl implements IRecetaMedicaDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_CAT_VIAS_DE_ADMIN_MEDICAMENTO = "FROM Catviasdeadminmedicamento";
	private static String GET_CAT_UNIDADES_DE_TIEMPO_MENOR_DIA = "FROM Catunidadesdetiempo WHERE esMenorAunDia = 1";
	private static String GET_CAT_UNIDADES_DE_TIEMPO_MAYOR_DIA = "FROM Catunidadesdetiempo WHERE esMenorAunDia = 0";
	private static String GET_CAT_MEDICAMENTOS = "FROM Catmedicamentos WHERE medicamento like ?";
	private static String GET_CAT_MEDICAMENTOS_BY_ID = "FROM Catmedicamentos WHERE medicamentoId = ?";
	private static String GET_CAT_UNIDADES_DE_TIEMPO_BY_ID = "FROM Catunidadesdetiempo WHERE unidadTiempoId = ?";
	private static String GET_CAT_VIAS_DE_ADMIN_MEDICAMENTO_BY_ID = "FROM Catviasdeadminmedicamento WHERE viaDeAdmonMedicamentoId = ?";
	private static String GET_CAT_ESTATUS_RECETAS_BY_ID = "FROM Catestatusrecetas WHERE estatusRecetasId = ?";
	private static String GET_CAT_MEDICAMENTOS_LIST = "FROM Catmedicamentos WHERE clave in :claves";
	private static String GET_MEDICAMENTO_RECETAS_BY_ID = "FROM Recetas WHERE atencionmedica.atencionMedicaId = ?";
	private static String GET_RECETA_BY_ID = "FROM Recetas WHERE recetaId = ?";
	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}

	@Override
	public Collection<Catviasdeadminmedicamento> getCatViasDeAdiminMedicamento() {
		//log.info("getCatViasDeAdiminMedicamento");
		Query query = getSession().createQuery(GET_CAT_VIAS_DE_ADMIN_MEDICAMENTO);
		return query.list();
	}

	@Override
	public Collection<Catunidadesdetiempo> getCatUnidadesDeTiempoMenorDia() {
		//log.info("getCatUnidadesDeTiempoMenorDia");
		Query query = getSession().createQuery(GET_CAT_UNIDADES_DE_TIEMPO_MENOR_DIA);
		return query.list();
	}

	@Override
	public Collection<Catunidadesdetiempo> getCatUnidadesDeTiempoMayorDia() {
		//log.info("getCatUnidadesDeTiempoMayorDia");
		Query query = getSession().createQuery(GET_CAT_UNIDADES_DE_TIEMPO_MAYOR_DIA);
		return query.list();
	}

	@Override
	public Collection<Catmedicamentos> getCatMedicamentos(String busqueda) {
		//log.info("getCatMedicamentos");
		Query query = getSession().createQuery(GET_CAT_MEDICAMENTOS);
		query.setString(0, "%"+busqueda.trim()+"%");
		query.setMaxResults(10);
		return query.list();
	}

	@Override
	public Catmedicamentos getCatMedicamentosById(int idMedicamento) {
		//log.info("getCatMedicamentosById");
		Query query = getSession().createQuery(GET_CAT_MEDICAMENTOS_BY_ID);
		query.setInteger(0, idMedicamento);
		return (Catmedicamentos) query.uniqueResult();
	}

	@Override
	public Catunidadesdetiempo getCatUnidadesDeTiempoMayorDiaById(int idUnidadTiempoDuracion) {
		//log.info("getCatUnidadesDeTiempoMayorDiaById");
		Query query = getSession().createQuery(GET_CAT_UNIDADES_DE_TIEMPO_BY_ID);
		query.setInteger(0, idUnidadTiempoDuracion);
		return (Catunidadesdetiempo) query.uniqueResult();
	}

	@Override
	public Catunidadesdetiempo getCatUnidadesDeTiempoMenorDiaById(int idUnidadTiempoFrecuencia) {
		//log.info("getCatUnidadesDeTiempoMayorDiaById");
		Query query = getSession().createQuery(GET_CAT_UNIDADES_DE_TIEMPO_BY_ID);
		query.setInteger(0, idUnidadTiempoFrecuencia);
		return (Catunidadesdetiempo) query.uniqueResult();
	}

	@Override
	public Catviasdeadminmedicamento getCatViasDeAdiminMedicamentoById(int idViaDeAdmonMedicamento) {
		//log.info("getCatViasDeAdiminMedicamentoById");
		Query query = getSession().createQuery(GET_CAT_VIAS_DE_ADMIN_MEDICAMENTO_BY_ID);
		query.setInteger(0, idViaDeAdmonMedicamento);
		return (Catviasdeadminmedicamento) query.uniqueResult();
	}

	@Override
	public void save(Medicamentosreceta medicamentosreceta) {
		//log.info("getCatViasDeAdiminMedicamentoById");
		getSession().save(medicamentosreceta);
	}

	@Override
	public Catestatusrecetas getCatEstatusRecetasById(int id) {
		//log.info("getCatEstatusRecetasById");
		Query query = getSession().createQuery(GET_CAT_ESTATUS_RECETAS_BY_ID);
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
	public Collection<Catmedicamentos> getCatMedicamentos(Collection<String> claves) {
		//log.info("getCatEstatusRecetasById");
		Query query = getSession().createQuery(GET_CAT_MEDICAMENTOS_LIST);
		query.setParameterList("claves", claves);
		return query.list();
	}

	@Override
	public Collection<Recetas> getRecetasByAtencionMedica(Integer agendaId) {
		//log.info("getRecetasByAtencionMedica");
		Query query = getSession().createQuery(GET_MEDICAMENTO_RECETAS_BY_ID);
		query.setInteger(0, agendaId);
		return query.list();
	}

	@Override
	public void delete(Recetas recetas) {
		//log.info("delete");
		getSession().delete(recetas);
	}

	@Override
	public Recetas getRecetaMedicaById(int idReceta) {
		//log.info("getRecetaMedicaById");
		Query query = getSession().createQuery(GET_RECETA_BY_ID);
		query.setInteger(0, idReceta);
		return (Recetas) query.uniqueResult();
	}

	@Override
	public void update(Medicamentosreceta medicamentosreceta) {
		//log.info("update");
		getSession().merge(medicamentosreceta);
	}

}
