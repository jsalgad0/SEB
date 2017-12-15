package com.mx.sab.enums;

import lombok.Getter;

public enum CatMotivosEnum {
	
	VIGENCIA(1),
	IDENTIDAD_MEDICO(2),
	IDENTIDAD_PACIENTE(3),
	IDENTIDAD(4);
	
	private @Getter int id;
	
	private CatMotivosEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
