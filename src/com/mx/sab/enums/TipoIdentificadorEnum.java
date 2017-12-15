package com.mx.sab.enums;

import lombok.Getter;

public enum TipoIdentificadorEnum {

	RFC(1),
	CURP(2),
	IFE(3),
	NUMISSSTE(4),
	NUMBENEFICIARIOISSSTE(5),
	CVEMEDICO(6),
	CODEMPRESA(7),
	CODAFILIADO(8),
	CORRELATIVO(9),
	NINGUNO(10);	
	
	private @Getter int id;
	
	private TipoIdentificadorEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
