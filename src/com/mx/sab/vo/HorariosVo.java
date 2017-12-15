package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class HorariosVo implements Serializable{

	private static final long serialVersionUID = -8026382307322190298L;
	private String horario;
	private String id;
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	
}
