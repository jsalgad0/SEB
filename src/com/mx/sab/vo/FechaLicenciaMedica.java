package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class FechaLicenciaMedica implements Serializable{

	private static final long serialVersionUID = -1611004268414356980L;
	private int dias;
	private String diasLetra;
	private String error;
	public int getDias() {
		return dias;
	}
	public void setDias(int dias) {
		this.dias = dias;
	}
	public String getDiasLetra() {
		return diasLetra;
	}
	public void setDiasLetra(String diasLetra) {
		this.diasLetra = diasLetra;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
}
