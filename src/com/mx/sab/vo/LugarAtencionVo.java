package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class LugarAtencionVo implements Serializable{

	private static final long serialVersionUID = -3454968707846855232L;
	private Integer lugarDeAtencionId;
	private Integer codigoLugarAtencion;
	private String calleyNo;
	private String descripcion;
	public Integer getLugarDeAtencionId() {
		return lugarDeAtencionId;
	}
	public void setLugarDeAtencionId(Integer lugarDeAtencionId) {
		this.lugarDeAtencionId = lugarDeAtencionId;
	}
	public Integer getCodigoLugarAtencion() {
		return codigoLugarAtencion;
	}
	public void setCodigoLugarAtencion(Integer codigoLugarAtencion) {
		this.codigoLugarAtencion = codigoLugarAtencion;
	}
	public String getCalleyNo() {
		return calleyNo;
	}
	public void setCalleyNo(String calleyNo) {
		this.calleyNo = calleyNo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
