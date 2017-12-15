package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CatColoniasVo implements Serializable{

	private static final long serialVersionUID = -4553975052093478631L;
	private Integer coloniaId;
	private String colonia;
	private Integer activo;
	public Integer getColoniaId() {
		return coloniaId;
	}
	public void setColoniaId(Integer coloniaId) {
		this.coloniaId = coloniaId;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public Integer getActivo() {
		return activo;
	}
	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	
	
}
