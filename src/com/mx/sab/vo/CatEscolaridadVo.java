package com.mx.sab.vo;

import lombok.Data;

@Data
public class CatEscolaridadVo {

	private int idEscolaridad;
	private String descripcion;
	public int getIdEscolaridad() {
		return idEscolaridad;
	}
	public void setIdEscolaridad(int idEscolaridad) {
		this.idEscolaridad = idEscolaridad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
