package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Catestatusrecetas;
import com.mx.sab.model.Catmedicamentos;
import com.mx.sab.model.Catunidadesdetiempo;
import com.mx.sab.model.Catviasdeadminmedicamento;
import com.mx.sab.model.Medicamentosreceta;
import com.mx.sab.model.Recetas;

public interface IRecetaMedicaDao {

	Collection<Catviasdeadminmedicamento> getCatViasDeAdiminMedicamento();

	Collection<Catunidadesdetiempo> getCatUnidadesDeTiempoMenorDia();

	Collection<Catunidadesdetiempo> getCatUnidadesDeTiempoMayorDia();

	Collection<Catmedicamentos> getCatMedicamentos(String busqueda);

	Catmedicamentos getCatMedicamentosById(int idMedicamento);

	Catunidadesdetiempo getCatUnidadesDeTiempoMayorDiaById(
			int idUnidadTiempoDuracion);

	Catunidadesdetiempo getCatUnidadesDeTiempoMenorDiaById(
			int idUnidadTiempoFrecuencia);

	Catviasdeadminmedicamento getCatViasDeAdiminMedicamentoById(
			int idViaDeAdmonMedicamento);

	void save(Medicamentosreceta medicamentosreceta);

	Catestatusrecetas getCatEstatusRecetasById(int id);

	void saveReceta(Recetas recetas);

	Collection<Catmedicamentos> getCatMedicamentos(Collection<String> claves);

	Collection<Recetas> getRecetasByAtencionMedica(Integer AtencionMedicaId);

	void delete(Recetas recetas);

	Recetas getRecetaMedicaById(int idReceta);

	void update(Medicamentosreceta medicamentosreceta);

}
