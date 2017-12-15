package com.mx.sab.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AfiliadoMenorVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer afiliadoId;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Date fechaDeNacimiento;
	public Integer getAfiliadoId() {
		return afiliadoId;
	}
	public void setAfiliadoId(Integer afiliadoId) {
		this.afiliadoId = afiliadoId;
	}
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
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	
	
}
