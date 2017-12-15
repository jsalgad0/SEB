package com.mx.sab.vo;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import lombok.Data;

@Data
public class AgendaVo implements Serializable{

	private static final long serialVersionUID = 1754339648641246890L;
	private Integer agendaId;
	private AfiliadoVo afiliado;
	private String consultorio;
	private Date fechaCita;
	private Time horaCita;
	private Integer asistio;
	private AtencionMedicaVo atencionMedica;
	public Integer getAgendaId() {
		return agendaId;
	}
	public void setAgendaId(Integer agendaId) {
		this.agendaId = agendaId;
	}
	public AfiliadoVo getAfiliado() {
		return afiliado;
	}
	public void setAfiliado(AfiliadoVo afiliado) {
		this.afiliado = afiliado;
	}
	public String getConsultorio() {
		return consultorio;
	}
	public void setConsultorio(String consultorio) {
		this.consultorio = consultorio;
	}
	public Date getFechaCita() {
		return fechaCita;
	}
	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}
	public Time getHoraCita() {
		return horaCita;
	}
	public void setHoraCita(Time horaCita) {
		this.horaCita = horaCita;
	}
	public Integer getAsistio() {
		return asistio;
	}
	public void setAsistio(Integer asistio) {
		this.asistio = asistio;
	}
	public AtencionMedicaVo getAtencionMedica() {
		return atencionMedica;
	}
	public void setAtencionMedica(AtencionMedicaVo atencionMedica) {
		this.atencionMedica = atencionMedica;
	}
	
	
	
	
}
