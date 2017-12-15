package com.mx.sab.enums;

import lombok.Getter;

public enum CatTipoValorAseguradorEnum {

	COD_PLAN(1),
	COD_PRODUCTO(2),
	REGLAS_fARMACIA(3),
	SUMA_ASEG_FARMACIA(4),
	CUADRO_DE_MEDICAMENTOS(5),
	SESION_RECETA(6),
	FECHA_SESION_RECETA(7),
	PLAN(8),
	PRODUCTO(9),
	POLIZA(10),
	COD_PYME(11),
	PYME(12),
	COD_PARENTESCO(13),
	PARENTESCO(14);
	
	private @Getter int id;
	
	private CatTipoValorAseguradorEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}