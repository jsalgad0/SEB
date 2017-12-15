package com.mx.sab.enums;

import lombok.Getter;

public enum CatEstatusAtencionIdentidadEnum {

	IDENTIFICACION_PENDIENTE(1),
	IDENTIFICACION_AUTORIZADA_ESPECIAL(2),
	IDENTIFICACION_AUTORIZADA(3),
	IDENTIFICACION_RECHAZADA(4);
	
	private @Getter int id;
	
	private CatEstatusAtencionIdentidadEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
