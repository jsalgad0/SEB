package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class PrestadoresVo implements Serializable{

	private static final long serialVersionUID = -960489553912668538L;
	private Integer prestadorId;
	private String nombreRazonSocial;
	private String rfc;
	private CatTiposPersonasVo cattipospersonas;
	public Integer getPrestadorId() {
		return prestadorId;
	}
	public void setPrestadorId(Integer prestadorId) {
		this.prestadorId = prestadorId;
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
	public CatTiposPersonasVo getCattipospersonas() {
		return cattipospersonas;
	}
	public void setCattipospersonas(CatTiposPersonasVo cattipospersonas) {
		this.cattipospersonas = cattipospersonas;
	}
	
	
}
