package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CatTipoUnidadVo implements Serializable{

	private static final long serialVersionUID = -2989477097188279398L;
	private Integer tiposUnidadId;
	private String unidad;
	public Integer getTiposUnidadId() {
		return tiposUnidadId;
	}
	public void setTiposUnidadId(Integer tiposUnidadId) {
		this.tiposUnidadId = tiposUnidadId;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	
	
}
