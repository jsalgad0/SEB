package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class SignosVitalesAdicionalesVo implements Serializable{

	private static final long serialVersionUID = -6147747866337832245L;
	private int idServicioAdicional;
	private String valor;
	private String descripcion;
	public int getIdServicioAdicional() {
		return idServicioAdicional;
	}
	public void setIdServicioAdicional(int idServicioAdicional) {
		this.idServicioAdicional = idServicioAdicional;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
