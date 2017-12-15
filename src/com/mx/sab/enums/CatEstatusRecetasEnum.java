package com.mx.sab.enums;

import lombok.Getter;

public enum CatEstatusRecetasEnum {
    PENDIENTE(1),
    PARCIAL(2),
    ENTREGADA(3);

    private @Getter int id;

    private CatEstatusRecetasEnum(int id) {
        this.id = id;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    
}
