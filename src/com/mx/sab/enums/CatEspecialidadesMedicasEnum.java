package com.mx.sab.enums;

import lombok.Getter;

public enum CatEspecialidadesMedicasEnum {
	
	MEDICINA_GENERAL(1);
	
	private @Getter int id;
	
	private CatEspecialidadesMedicasEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
