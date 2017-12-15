package com.mx.sab.enums;

import lombok.Getter;

public enum CatSexoEnum {

    MASCULINO("M",1),
    FEMENINO("F",2);

    private @Getter String name;
    private @Getter int id;

    private CatSexoEnum(String name, int id) {
        this.name = name;
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    

}
