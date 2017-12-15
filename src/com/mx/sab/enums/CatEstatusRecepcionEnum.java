package com.mx.sab.enums;

import lombok.Getter;

public enum CatEstatusRecepcionEnum {
	
	AUTORIZADO(1),
	PENDIENTE(2),
	MODIFICADO(3);
	
	private @Getter int id;
	
	private CatEstatusRecepcionEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
