package com.mx.sab.enums;

import lombok.Getter;

public enum CatTipoAuditoriaEnum {

	LOGIN(1),
	ENROLAMIENTO(2),
	CERTIFICACION(3),
	TERMINO_ATENCION(4),
	FARMACIA(5);
	
	private @Getter int id;
	
	private CatTipoAuditoriaEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
