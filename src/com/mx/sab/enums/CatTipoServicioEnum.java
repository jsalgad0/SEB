package com.mx.sab.enums;

import lombok.Getter;

public enum CatTipoServicioEnum {

	GABINETE(1),
	LABORATORIO(2),
	OTROS(3),
	CONSULTORIO(4);
	
	private @Getter int id;
	
	private CatTipoServicioEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
