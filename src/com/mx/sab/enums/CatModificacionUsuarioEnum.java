package com.mx.sab.enums;

import lombok.Getter;

public enum CatModificacionUsuarioEnum {
	
	CAMBIO_DE_NOMBRE(1),
	CAMBIO_DE_APELLIDO_PATERNO(2),
	CAMBIO_DE_APELLIDO_MATERNO(3),
	CAMBIO_DE_FECHA_DE_NACIMIENTO(4),
	CAMBIO_DE_CURP(5),
	CAMBIO_DE_NUMERO_TELEFONICO(6),
	CAMBIO_DE_EMAIL(7),
	CAMBIO_DE_SEXO(8),
	CAMBIO_DE_ROL(9),
	CAMBIO_DE_VIGENCIA(10),
	AGREGAR_ROLES_LUGAR_DE_ATENCION(11),
	ELIMINAR_ROLES_LUGAR_DE_ATENCION(12),
	AGREGAR_ESPECIALIDAD_CEDULA_INSTITUCION(13),
	ELIMINAR_ESPECIALIDAD_CEDULA_INSTITUCION(14),
	CAMBIO_ESPECIALIDAD_CEDULA_INSTITUCION(15),
	CAMBIO_CLAVE_INTERNA(16),
	CAMBIO_REGSS(17);
	
	private @Getter int id;
	
	private CatModificacionUsuarioEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}