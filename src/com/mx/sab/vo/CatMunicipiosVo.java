package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CatMunicipiosVo implements Serializable{

	private static final long serialVersionUID = -8561997002707153840L;
	private Integer municipioId;
	private String municipio;
	private Integer activo;
	public Integer getMunicipioId() {
		return municipioId;
	}
	public void setMunicipioId(Integer municipioId) {
		this.municipioId = municipioId;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public Integer getActivo() {
		return activo;
	}
	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	
	
}
