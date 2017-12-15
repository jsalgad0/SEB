package com.mx.sab.vo;

import lombok.Data;

@Data
public class CatAlimentacionVo {

	private int idAlimentacion;
	private String descripcion;
	public int getIdAlimentacion() {
		return idAlimentacion;
	}
	public void setIdAlimentacion(int idAlimentacion) {
		this.idAlimentacion = idAlimentacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
