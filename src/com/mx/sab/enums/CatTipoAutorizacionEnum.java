package com.mx.sab.enums;

import lombok.Getter;

public enum CatTipoAutorizacionEnum {

	VIGENCIA(1),
	AUTENTIA(2);
	
	private @Getter int id;
	
	private CatTipoAutorizacionEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
