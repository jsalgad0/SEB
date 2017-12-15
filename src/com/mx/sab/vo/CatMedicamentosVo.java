package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CatMedicamentosVo implements Serializable {
	
	private static final long serialVersionUID = 2866928817463437811L;
	private int idMedicamento;
	private String medicamento;
	public int getIdMedicamento() {
		return idMedicamento;
	}
	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	public String getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}
	
	
}
