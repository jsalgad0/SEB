package com.mx.sab.enums;

import lombok.Getter;

public enum CatVigenciaEnum {

	VIGENTE(1),
	NO_VIGENTE(2),
	BLOQUEADO(3);
	
	private @Getter int id;
	
	private CatVigenciaEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
