package com.mx.sab.enums;

import lombok.Getter;

public enum CatEstatusUsuarioEnum {

	AUTORIZACION_PENDIENTE(1),
	AUTORIZADO_CON_ENROLAMIENTO(2),
	DECLARACION_RECHAZADA(3),
	AUTORIZADO_CON_CLAVE(4),
	INGRESADO(5),
	AUTORIZADO_CON_HUELLA(6);
	
	private @Getter int id;
	
	private CatEstatusUsuarioEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
