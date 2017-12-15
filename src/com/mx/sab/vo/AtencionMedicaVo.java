package com.mx.sab.vo;

import java.io.Serializable;
import java.sql.Time;

import lombok.Data;

@Data
public class AtencionMedicaVo implements Serializable{

	private static final long serialVersionUID = 8792367654507636959L;
	private Time horaAsistio;
	public Time getHoraAsistio() {
		return horaAsistio;
	}
	public void setHoraAsistio(Time horaAsistio) {
		this.horaAsistio = horaAsistio;
	}
	
	
}
