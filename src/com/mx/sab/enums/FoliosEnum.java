package com.mx.sab.enums;

import lombok.Getter;

public enum FoliosEnum {

	FOLIO_DE_ATENCION(1),
	RECETA_MEDICA(2),
	ORDEN_DE_EXAMENES_DE_LABORATORIO(3),
	ORDEN_DE_GABINETE(4),
	ORDEN_DE_OTROS_SERVICIOS(5),
	LICENCIA_MEDICA(6),
	SOLICITUD_DE_REFERENCIA(7),
	CERTIFICADO_DE_ASISTENCIA(8),
	CONSULTORIO(9),
	NOTA_MEDICA(10);
	
	private @Getter int id;
	
	private FoliosEnum(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
