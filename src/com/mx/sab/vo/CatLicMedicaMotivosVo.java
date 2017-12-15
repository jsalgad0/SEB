package com.mx.sab.vo;

import lombok.Data;

@Data
public class CatLicMedicaMotivosVo {

	private int idLicMedicaMotivo;
	private String descripcion;
	public int getIdLicMedicaMotivo() {
		return idLicMedicaMotivo;
	}
	public void setIdLicMedicaMotivo(int idLicMedicaMotivo) {
		this.idLicMedicaMotivo = idLicMedicaMotivo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
