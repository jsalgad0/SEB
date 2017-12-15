package com.mx.sab.enums;

import lombok.Getter;

public enum CatCausasEnum {

	VIGENCIA(1),
	HUELLAS(2),
	QUEMADURA(3),
	FRACTURA(4),
	DEMENCIA(5),
	AMPUTACION(6),
	OTRO(7),
	MAYOR_DE_65(8);
	
	private @Getter int id;
	
	private CatCausasEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
