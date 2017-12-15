package com.mx.sab.enums;

import lombok.Getter;

public enum CatEstatusAtencionVigenciaEnum {
	
	VIGENCIA_PENDIENTE(1),
	VIGENCIA_AUTORIZADA(2),	
	VIGENCIA_RECHAZADA(3),
	VIGENCIA_AUTORIZADA_ESPECIAL(4);
	
	private @Getter int id;
	
	private CatEstatusAtencionVigenciaEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
