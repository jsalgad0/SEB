package com.mx.sab.service;

import java.util.Collection;
import java.util.List;

import com.mx.sab.form.AtencionRecetaForm;
import com.mx.sab.model.Catunidadesdetiempo;
import com.mx.sab.model.Catviasdeadminmedicamento;
import com.mx.sab.vo.CatMedicamentosVo;
import com.mx.sab.vo.RecetasVo;

public interface IAtencionRecetaService {

	void inicializaAtencionRecetaForm(AtencionRecetaForm atencionRecetaForm);

	Collection<Catviasdeadminmedicamento> getCatViaAdminMedicamento();

	Collection<Catunidadesdetiempo> getCatUnidadesTiempoMenorDia();

	Collection<Catunidadesdetiempo> getCatUnidadesTiempoMayorDia();

	Collection<CatMedicamentosVo> medicamentos(String busqueda, int idAtencion);

	void saveReceta(AtencionRecetaForm atencionRecetaForm,
			Collection<RecetasVo> recetasVos);
	
	List<RecetasVo> getRecetas(int idAtencion);

}
