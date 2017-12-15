package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class HistorialAtencionesVo implements Serializable{

	private static final long serialVersionUID = -2684225671747144700L;
	private String fecha;
	private String lugarAtencion;
	private String prestacion;
	private int idAtencion;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getLugarAtencion() {
		return lugarAtencion;
	}
	public void setLugarAtencion(String lugarAtencion) {
		this.lugarAtencion = lugarAtencion;
	}
	public String getPrestacion() {
		return prestacion;
	}
	public void setPrestacion(String prestacion) {
		this.prestacion = prestacion;
	}
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	
	
	
	
}
