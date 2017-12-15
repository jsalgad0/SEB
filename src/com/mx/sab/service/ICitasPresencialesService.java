package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.AtencionMedicaForm;
import com.mx.sab.form.CitasPresencialesForm;
import com.mx.sab.model.Aseguradores;
import com.mx.sab.vo.ConveniosVo;
import com.mx.sab.vo.PrestadoresVo;
import com.mx.sab.vo.UserInfo;

public interface ICitasPresencialesService {

	void inicializaAtencionMedicaForm(AtencionMedicaForm atencionMedicaForm);

	void save(AtencionMedicaForm atencionMedicaForm, UserInfo userInfo);

	void inicializaCitasPresencialesForm(
			CitasPresencialesForm citasPresencialesForm, UserInfo userInfo);

	Collection<PrestadoresVo> getPrestadoresByAseguradorLugar(int id,
			Collection<Aseguradores> aseguradores);

	Collection<ConveniosVo> getConvenios(int idAsegurador, int idPrestador,
			UserInfo userInfo);

	void getAfiliados(CitasPresencialesForm citasPresencialesForm);

	void isVigente(CitasPresencialesForm citasPresencialesForm, UserInfo userInfo);

	void save(CitasPresencialesForm citasPresencialesForm, UserInfo userInfo);

	void nuevoAfiliado(CitasPresencialesForm citasPresencialesForm,
			UserInfo userInfo);

	void verificaAgenda(CitasPresencialesForm citasPresencialesForm);

	void autorizacionEspecial(CitasPresencialesForm citasPresencialesForm, UserInfo userInfo);

	void verificaOrdenServicio(CitasPresencialesForm citasPresencialesForm);

	void valorizar(CitasPresencialesForm citasPresencialesForm, UserInfo userInfo);
	
	void guardar(CitasPresencialesForm citasPresencialesForm, UserInfo userInfo);

}
