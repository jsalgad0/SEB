package com.mx.sab.vo;

import lombok.Data;

@Data
public class CatGrupoSanguineoVo {

	private int idSangre;
	private String descripcion;
	public int getIdSangre() {
		return idSangre;
	}
	public void setIdSangre(int idSangre) {
		this.idSangre = idSangre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
