package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class EquivalenciasSabVo implements Serializable{

	private static final long serialVersionUID = 7388289718530408421L;
	private int idSab;
	private String codigo;
	private String subCodigo;
	public int getIdSab() {
		return idSab;
	}
	public void setIdSab(int idSab) {
		this.idSab = idSab;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getSubCodigo() {
		return subCodigo;
	}
	public void setSubCodigo(String subCodigo) {
		this.subCodigo = subCodigo;
	}
	
	
}
