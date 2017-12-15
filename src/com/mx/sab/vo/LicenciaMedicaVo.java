package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class LicenciaMedicaVo implements Serializable{

	private static final long serialVersionUID = -3352601767093518520L;

	private String motivo;
	private String caracter;
	private String fechaDesde;
	private String fechaHasta;
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getCaracter() {
		return caracter;
	}
	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	
}
