package com.mx.sab.model.jndi;

import lombok.Data;

@Data
public class AfiliadoTipoIdentificador {

	private int afiliadoId;
	private int tipoIdentificadorId;
	private String tipoIdValor;
	public int getAfiliadoId() {
		return afiliadoId;
	}
	public void setAfiliadoId(int afiliadoId) {
		this.afiliadoId = afiliadoId;
	}
	public int getTipoIdentificadorId() {
		return tipoIdentificadorId;
	}
	public void setTipoIdentificadorId(int tipoIdentificadorId) {
		this.tipoIdentificadorId = tipoIdentificadorId;
	}
	public String getTipoIdValor() {
		return tipoIdValor;
	}
	public void setTipoIdValor(String tipoIdValor) {
		this.tipoIdValor = tipoIdValor;
	}
	
	
}
