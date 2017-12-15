package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.SupervisorAtencionesPendientesForm;
import com.mx.sab.model.Catcausas;
import com.mx.sab.vo.PermisoEspecialVo;
import com.mx.sab.vo.UserInfo;

public interface ISupervisorAtencionesPendientesService {

	Collection<PermisoEspecialVo> getAtencionesPendientes(SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm, UserInfo userInfo);

	Collection<PermisoEspecialVo> getAtencionesPendientes(int page, UserInfo userInfo);

	SupervisorAtencionesPendientesForm inicializarAtencionesPendientesForm(
			int idAtencionMedica, int idAfiliado, int autorizarRechazar, int autorizarRechazar2);

	void actualizarAtencionMedica(SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm, UserInfo userInfo);

	void inicializarAtencionesPendientesFormIdentidad(
			SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm);

	Collection<Catcausas> getCatCausas();

	void actualizarAtencionMedicaIdentidad(SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm,UserInfo userInfo);

}
