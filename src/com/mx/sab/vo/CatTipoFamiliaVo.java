package com.mx.sab.vo;

import lombok.Data;

@Data
public class CatTipoFamiliaVo {

	private int idTipoFamilia;
	private String descripcion;
	public int getIdTipoFamilia() {
		return idTipoFamilia;
	}
	public void setIdTipoFamilia(int idTipoFamilia) {
		this.idTipoFamilia = idTipoFamilia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
