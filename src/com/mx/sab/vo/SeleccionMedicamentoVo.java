package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class SeleccionMedicamentoVo implements Serializable{

	private static final long serialVersionUID = 789552798278548512L;
	private boolean errorWs;
	public boolean isErrorWs() {
		return errorWs;
	}
	public void setErrorWs(boolean errorWs) {
		this.errorWs = errorWs;
	}
	
	
}
