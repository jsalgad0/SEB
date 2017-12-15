package com.mx.sab.service;

import com.mx.sab.form.FinalizarAtencionForm;

public interface IFinalizarAtencionService {

	void inicializarForm(FinalizarAtencionForm finalizarAtencionForm);

	void finalizar(FinalizarAtencionForm finalizarAtencionForm);

	void imprimirTodo(FinalizarAtencionForm finalizarAtencionForm);

}
