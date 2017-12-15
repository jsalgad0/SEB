package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class AtencionMedicaAuxVo implements Serializable{

	private static final long serialVersionUID = 8792367654507636959L;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String fechaDeNacimiento;
	private String idIdentificador;
	private String descripcionIdentificador;
	private String fechaDeAtencion;
	private String folio;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public String getIdIdentificador() {
		return idIdentificador;
	}
	public void setIdIdentificador(String idIdentificador) {
		this.idIdentificador = idIdentificador;
	}
	public String getDescripcionIdentificador() {
		return descripcionIdentificador;
	}
	public void setDescripcionIdentificador(String descripcionIdentificador) {
		this.descripcionIdentificador = descripcionIdentificador;
	}
	public String getFechaDeAtencion() {
		return fechaDeAtencion;
	}
	public void setFechaDeAtencion(String fechaDeAtencion) {
		this.fechaDeAtencion = fechaDeAtencion;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	
}
