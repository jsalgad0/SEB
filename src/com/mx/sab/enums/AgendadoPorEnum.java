package com.mx.sab.enums;

import lombok.Getter;

public enum AgendadoPorEnum {

	WA(1),
	WC(2),
	CC(3),
	WP(4),
	WT(5),
	WK(6);
	
	private @Getter int id;
	
	private AgendadoPorEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
