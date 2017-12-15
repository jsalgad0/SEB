package com.mx.sab.vo;

import lombok.Data;

@Data
public class CatHigieneVo {

	private int idHigiene;
	private String descripcion;
	public int getIdHigiene() {
		return idHigiene;
	}
	public void setIdHigiene(int idHigiene) {
		this.idHigiene = idHigiene;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
