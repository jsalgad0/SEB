package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class AseguradoresVo implements Serializable{

	private static final long serialVersionUID = -3447833267105327850L;
	private Integer aseguradorId;
	private String nombreRazonSocial;
	private String rfc;
	public Integer getAseguradorId() {
		return aseguradorId;
	}
	public void setAseguradorId(Integer aseguradorId) {
		this.aseguradorId = aseguradorId;
	}
	public String getNombreRazonSocial() {
		return nombreRazonSocial;
	}
	public void setNombreRazonSocial(String nombreRazonSocial) {
		this.nombreRazonSocial = nombreRazonSocial;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	
}
