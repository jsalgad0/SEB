package com.mx.sab.service;

import com.mx.sab.form.RecepcionBuscarAtencionForm;
import com.mx.sab.vo.UserInfo;

public interface IRecepcionBuscarAtencionesService {

	void getAtencionesHoy(RecepcionBuscarAtencionForm recepcionBuscarAtencionForm, UserInfo userInfo);
}
