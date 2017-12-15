package com.mx.sab.vo;

import lombok.Data;

@Data
public class CatLicMedicaCaracteresVo {

	private int idLicMedicaCaracter;
	private String descripcion;
	public int getIdLicMedicaCaracter() {
		return idLicMedicaCaracter;
	}
	public void setIdLicMedicaCaracter(int idLicMedicaCaracter) {
		this.idLicMedicaCaracter = idLicMedicaCaracter;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
