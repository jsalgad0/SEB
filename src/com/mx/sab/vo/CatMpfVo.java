package com.mx.sab.vo;

import lombok.Data;

@Data
public class CatMpfVo {

	private int idMpf;
	private String descripcion;
	public int getIdMpf() {
		return idMpf;
	}
	public void setIdMpf(int idMpf) {
		this.idMpf = idMpf;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
