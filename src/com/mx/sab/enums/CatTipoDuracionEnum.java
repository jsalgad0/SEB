package com.mx.sab.enums;

import lombok.Getter;

public enum CatTipoDuracionEnum {

	TEMPORAL(1),
	PERMANENTE(2);
	
	private @Getter int id;
	
	private CatTipoDuracionEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
