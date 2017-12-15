package com.mx.sab.vo;

import java.io.Serializable;
import java.sql.Time;

import lombok.Data;

@Data
public class ListaSignosVo implements Serializable{

	private static final long serialVersionUID = -3114900862991024205L;
	private int idAtencion;
	private String nombrePaciente;
	private int estatusCita;
	private String horaCita;
	private String fechaCita;
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public String getNombrePaciente() {
		return nombrePaciente;
	}
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
	public int getEstatusCita() {
		return estatusCita;
	}
	public void setEstatusCita(int estatusCita) {
		this.estatusCita = estatusCita;
	}
	public String getHoraCita() {
		return horaCita;
	}
	public void setHoraCita(String horaCita) {
		this.horaCita = horaCita;
	}
	public String getFechaCita() {
		return fechaCita;
	}
	public void setFechaCita(String fechaCita) {
		this.fechaCita = fechaCita;
	}
	
	
}
