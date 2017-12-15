package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.RecepcionAtencionesPendientesForm;
import com.mx.sab.form.RecuperarAtencionForm;
import com.mx.sab.form.SupervisorAtencionesPendientesForm;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catcausas;
import com.mx.sab.vo.PermisoEspecialVo;
import com.mx.sab.vo.RecuperaAtencionVo;
import com.mx.sab.vo.UserInfo;

public interface IRecepcionAtencionesPendientesService {

	Collection<PermisoEspecialVo> getAtencionesPendientes(RecepcionAtencionesPendientesForm recepcionAtencionesPendientesForm, UserInfo userInfo);

	Collection<PermisoEspecialVo> getAtencionesPendientes(int page, UserInfo userInfo);
  
	RecuperarAtencionForm getAtencionById(int idAtencion);

	void valorizar(RecuperarAtencionForm recuperarAtencionForm,	UserInfo userInfo);
	void updateAtencion(RecuperarAtencionForm recuperarAtencionForm,	UserInfo userInfo);
}
