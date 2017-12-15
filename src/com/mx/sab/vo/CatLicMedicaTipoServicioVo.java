package com.mx.sab.vo;

import lombok.Data;

@Data
public class CatLicMedicaTipoServicioVo {

	private int idLicMedicaTipoServicio;
	private String descripcion;
	public int getIdLicMedicaTipoServicio() {
		return idLicMedicaTipoServicio;
	}
	public void setIdLicMedicaTipoServicio(int idLicMedicaTipoServicio) {
		this.idLicMedicaTipoServicio = idLicMedicaTipoServicio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
