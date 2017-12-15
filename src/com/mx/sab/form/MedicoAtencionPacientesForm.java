package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class MedicoAtencionPacientesForm extends ParentForm implements Serializable{
	
	private static final long serialVersionUID = 1497146176776036024L;
	
	private int idAfiliado;
	private String busquedaApellidoPaterno;
	private String busquedaFecha;
	private int busquedaidEstatus;
	private int idAtencion;
	public int getIdAfiliado() {
		return idAfiliado;
	}
	public void setIdAfiliado(int idAfiliado) {
		this.idAfiliado = idAfiliado;
	}
	public String getBusquedaApellidoPaterno() {
		return busquedaApellidoPaterno;
	}
	public void setBusquedaApellidoPaterno(String busquedaApellidoPaterno) {
		this.busquedaApellidoPaterno = busquedaApellidoPaterno;
	}
	public String getBusquedaFecha() {
		return busquedaFecha;
	}
	public void setBusquedaFecha(String busquedaFecha) {
		this.busquedaFecha = busquedaFecha;
	}
	public int getBusquedaidEstatus() {
		return busquedaidEstatus;
	}
	public void setBusquedaidEstatus(int busquedaidEstatus) {
		this.busquedaidEstatus = busquedaidEstatus;
	}
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	
	
	
}
