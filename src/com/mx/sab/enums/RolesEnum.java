package com.mx.sab.enums;

import lombok.Getter;

public enum RolesEnum {

	ADMINISTRADOR_SAB(5),
	CONVENIOS(6),
	PRESTACIONES(7),
	LECTORES(8),
	USUARIOS(9),
	ADMINISTRADOR_ASEGURADOR(10),
	OPERADOR(11),
	ADMINISTRADOR(12),
	RECEPCIONISTA(13),
	MEDICO(14),
	ENFERMERIA(15),
	SUPERVISOR(16),
	ADMINISTRADOR_FARMACIA(17),
	DESPACHADOR_MEDICAMENTOS(18),
	ADMINISTRADOR_LUGAR_ATENCION(19),
	DENTISTA(20);
	
	private @Getter int id;
	
	private RolesEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
