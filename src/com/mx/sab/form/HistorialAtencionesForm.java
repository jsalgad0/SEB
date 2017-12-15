package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class HistorialAtencionesForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = -675661923396987247L;
	private int idAtencion;
	private int idAfiliado;
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public int getIdAfiliado() {
		return idAfiliado;
	}
	public void setIdAfiliado(int idAfiliado) {
		this.idAfiliado = idAfiliado;
	}
	
	
}
