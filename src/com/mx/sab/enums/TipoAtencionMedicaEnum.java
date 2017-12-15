package com.mx.sab.enums;

import lombok.Getter;

public enum TipoAtencionMedicaEnum {
    MEDICO(1),
    DENTAL(2),
    OTRO(3);

    private @Getter int id;

    private TipoAtencionMedicaEnum(int id) {
        this.id = id;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    
}
