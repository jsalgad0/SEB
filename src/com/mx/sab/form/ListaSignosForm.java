package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class ListaSignosForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = -3561165326626927298L;
	private int idAgenda;
	private int idAtencion;
	private String busquedaApellido;
	private int idEstatus;
	private boolean enfermeria;
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public String getBusquedaApellido() {
		return busquedaApellido;
	}
	public void setBusquedaApellido(String busquedaApellido) {
		this.busquedaApellido = busquedaApellido;
	}
	public int getIdEstatus() {
		return idEstatus;
	}
	public void setIdEstatus(int idEstatus) {
		this.idEstatus = idEstatus;
	}
	public boolean isEnfermeria() {
		return enfermeria;
	}
	public void setEnfermeria(boolean enfermeria) {
		this.enfermeria = enfermeria;
	}
	
	
	
}
