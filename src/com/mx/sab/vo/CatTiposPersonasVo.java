package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CatTiposPersonasVo implements Serializable{

	private static final long serialVersionUID = 8724360656560832995L;
	private String tipoPersona;
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	
}
