package com.mx.sab.enums;

import lombok.Getter;

public enum AseguradoresEnum {
	
	ISSSTE(10),
	MEDIACCESS(12),
	SIN_ASEGURADOR(18);
	
	private @Getter int id;
	
	private AseguradoresEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
