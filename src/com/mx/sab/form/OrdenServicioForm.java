package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrdenServicioForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = 4472624311385978256L;
	
	private String[] idsPrestaciones;
	private String ordenServicio;
	public String[] getIdsPrestaciones() {
		return idsPrestaciones;
	}
	public void setIdsPrestaciones(String[] idsPrestaciones) {
		this.idsPrestaciones = idsPrestaciones;
	}
	public String getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(String ordenServicio) {
		this.ordenServicio = ordenServicio;
	}

	
}
