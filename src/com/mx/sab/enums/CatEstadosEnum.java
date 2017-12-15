package com.mx.sab.enums;

import lombok.Getter;

public enum CatEstadosEnum {

	AGUASCALIENTES(1),
	BAJA_CALIFORNIA(2),
	BAJA_CALIFORNIA_SUR(3),
	CAMPECHE(4),
	CHIAPAS(5),
	CHIHUAHUA(6),
	COAHUILA(7),
	COLIMA(8),
	DISTRITO_FEDERAL(9),
	DURANGO(10),
	ESTADO_MEXICO(11),
	GUANAJUATO(12),
	GUERRERO(13),
	HIDALGO(14),
	JALISCO(15),
	MICHOACAN(16),
	MORELOS(17),
	NAYARIT(18),
	NUEVO_LEON(19),
	OAXACA(20),
	PUEBLA(21),
	QUERETARO(22),
	QUINTANA_ROO(23),
	SAN_LUIS_POTOSI(24),
	SINALOA(25),
	SONORA(26),
	TABASCO(27),
	TAMAULIPAS(28),
	TLAXCALA(29),
	VERACRUZ(30),
	YUCATAN(31),
	ZACATECAS(32),
	EXTRANJERO(33);
	
	private @Getter int id;
	
	private CatEstadosEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
