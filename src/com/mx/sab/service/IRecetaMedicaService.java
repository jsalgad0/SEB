package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.RecetaMedicaForm;
import com.mx.sab.model.Catunidadesdetiempo;
import com.mx.sab.model.Catviasdeadminmedicamento;
import com.mx.sab.model.Medicamentosreceta;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.SeleccionMedicamentoVo;

public interface IRecetaMedicaService {

	Collection<Catviasdeadminmedicamento> getCatViasDeAdiminMedicamento();

	Collection<Catunidadesdetiempo> getCatUnidadesDeTiempoMenorDia();

	Collection<Catunidadesdetiempo> getCatUnidadesDeTiempoMayorDia();

	Collection<AutocompleteVo> getCatMedicamentos(String busqueda, RecetaMedicaForm recetaMedicaForm);

	void save(RecetaMedicaForm recetaMedicaForm);

	void inicializaForm(RecetaMedicaForm recetaMedicaForm);

	SeleccionMedicamentoVo getSeleccionMedicamentos(RecetaMedicaForm recetaMedicaForm);

	Collection<Medicamentosreceta> getMedicamentoRecetas(RecetaMedicaForm recetaMedicaForm);

	void deleteMedicamentoRecetas(RecetaMedicaForm recetaMedicaForm);

	void inicializaMedicamentoRecetas(RecetaMedicaForm recetaMedicaForm);

	void update(RecetaMedicaForm recetaMedicaForm);

}
