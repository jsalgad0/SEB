package com.mx.sab.enums;

import lombok.Getter;

public enum TipoAfiliadoEnum {
	
	TRABAJADOR(1,"T",10,"Trabajador"),
	TRABAJADORA(2,"T",20,"Trabajadora"),
	PENSIONADO(16,"P",90,"Pensionado"),
	TITULAR(19,"T",0,"Titular"),
	TITULAR_SIN_ASEGURADOR(41,"T",0,"Titular");
	
	private @Getter int id;
	private @Getter String tipo;
	private @Getter int clave;
	private @Getter String parentesco;
	
	private TipoAfiliadoEnum(int id, String tipo, int clave, String parentesco){
		this.id = id;
		this.tipo = tipo;
		this.clave = clave;
		this.parentesco = parentesco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	
	
}
