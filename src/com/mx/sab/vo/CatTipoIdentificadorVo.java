package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CatTipoIdentificadorVo implements Serializable{

	private static final long serialVersionUID = 8101788758802361004L;
	private Integer tipoIdentificadorId;
	private String tipoIdentificador;
	public Integer getTipoIdentificadorId() {
		return tipoIdentificadorId;
	}
	public void setTipoIdentificadorId(Integer tipoIdentificadorId) {
		this.tipoIdentificadorId = tipoIdentificadorId;
	}
	public String getTipoIdentificador() {
		return tipoIdentificador;
	}
	public void setTipoIdentificador(String tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
	}
	
	
}
