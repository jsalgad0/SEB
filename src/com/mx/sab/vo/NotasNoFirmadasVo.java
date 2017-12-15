package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class NotasNoFirmadasVo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String paciente;
	private String medico;
	private int idNota;
	private int idAfiliado;
	private int medicoAfiliado;
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getMedico() {
		return medico;
	}
	public void setMedico(String medico) {
		this.medico = medico;
	}
	public int getIdNota() {
		return idNota;
	}
	public void setIdNota(int idNota) {
		this.idNota = idNota;
	}
	public int getIdAfiliado() {
		return idAfiliado;
	}
	public void setIdAfiliado(int idAfiliado) {
		this.idAfiliado = idAfiliado;
	}
	public int getMedicoAfiliado() {
		return medicoAfiliado;
	}
	public void setMedicoAfiliado(int medicoAfiliado) {
		this.medicoAfiliado = medicoAfiliado;
	}
	
	
	
	
}
