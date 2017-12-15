package com.mx.sab.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PacientesRecibidosVo implements Serializable{

	private static final long serialVersionUID = -3435633786716149268L;
	private String paciente;
	private Date horaLlegada;
	private Date horaCita;
	private String medico;
	private String especialidad;
	private int estado;
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public Date getHoraLlegada() {
		return horaLlegada;
	}
	public void setHoraLlegada(Date horaLlegada) {
		this.horaLlegada = horaLlegada;
	}
	public Date getHoraCita() {
		return horaCita;
	}
	public void setHoraCita(Date horaCita) {
		this.horaCita = horaCita;
	}
	public String getMedico() {
		return medico;
	}
	public void setMedico(String medico) {
		this.medico = medico;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	
}
