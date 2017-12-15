package com.mx.sab.ws.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class MedicamentoVo implements Serializable{

	private static final long serialVersionUID = -7231985184645686297L;
	private String label;
	private String id;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
