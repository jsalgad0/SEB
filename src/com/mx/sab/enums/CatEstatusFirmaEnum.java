package com.mx.sab.enums;

import lombok.Getter;

public enum CatEstatusFirmaEnum {

	IDENTIDAD_AUTORIZADA(1),
	IDENTIDAD_PENDIENTE(2),
	IDENTIDAD_CON_AUTORIZACION_ESPECIAL(3),
	IDENTIDAD_ERRONEA(4),
	IDENTIDAD_AUTORIZADA_POR_SUPERVISOR(5);
	
	private @Getter int id;
	
	private CatEstatusFirmaEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
