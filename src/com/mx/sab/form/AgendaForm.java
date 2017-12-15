package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class AgendaForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = -8551300871010580666L;
	private int idAgenda;
	private String busquedaM;
	private String busquedaA;
	private String busquedaF;
	private String busquedaE;
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public String getBusquedaM() {
		return busquedaM;
	}
	public void setBusquedaM(String busquedaM) {
		this.busquedaM = busquedaM;
	}
	public String getBusquedaA() {
		return busquedaA;
	}
	public void setBusquedaA(String busquedaA) {
		this.busquedaA = busquedaA;
	}
	public String getBusquedaF() {
		return busquedaF;
	}
	public void setBusquedaF(String busquedaF) {
		this.busquedaF = busquedaF;
	}
	public String getBusquedaE() {
		return busquedaE;
	}
	public void setBusquedaE(String busquedaE) {
		this.busquedaE = busquedaE;
	}
	
	
	
}
