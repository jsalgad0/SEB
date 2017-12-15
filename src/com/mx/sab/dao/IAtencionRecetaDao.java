package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Catestatusrecetas;
import com.mx.sab.model.Catmedicamentos;
import com.mx.sab.model.Catunidadesdetiempo;
import com.mx.sab.model.Catviasdeadminmedicamento;
import com.mx.sab.model.Medicamentosreceta;
import com.mx.sab.model.Recetas;

public interface IAtencionRecetaDao {

	Collection<Recetas> getRecetasByIdAtencion(int idAtencion);

	Collection<Catviasdeadminmedicamento> getCatViaAdminMedicamento();

	Collection<Catunidadesdetiempo> getCatUnidadesTiempoMenorDia();

	Collection<Catunidadesdetiempo> getCatUnidadesTiempoMayorDia();

	Collection<Catmedicamentos> getCatMedicamentos(String busqueda, Integer IdAsegurador);

	Catestatusrecetas getCatEstatusReceta(int id);

	void saveReceta(Recetas recetas);

	void saveMedicamentoReceta(Medicamentosreceta medicamentosreceta);

	Recetas getRecetasById(int idReceta);

	void deleteReceta(Recetas recetas);

	Catviasdeadminmedicamento getCatViaAdminMedicamentoById(int idUnidad);

	Catmedicamentos getCatMedicamentoById(int idMedicamento);

	Catunidadesdetiempo getCatUnidadesDeTiempoById(int idDurante);

	void deleteMedicamentoReceta(Medicamentosreceta medicamentosreceta);

	Medicamentosreceta getMedicamentoRecetaById(int idReceta);

	void updateReceta(Recetas recetas);

}
