package com.mx.sab.enums;

import lombok.Getter;

public enum EstatusCitasEnum {

	A(1),
	B(2),
	C(3),
	D(4),
	P(9),
	I(10);
	
	private @Getter int id;
	
	private EstatusCitasEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
