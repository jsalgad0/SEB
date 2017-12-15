package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class AfiliadoVo implements Serializable{

	private static final long serialVersionUID = -6579651639783620800L;
	private Integer afiliadoId;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String hora;
	private boolean atendido;
	private Integer atencionId;
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
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public boolean isAtendido() {
		return atendido;
	}
	public void setAtendido(boolean atendido) {
		this.atendido = atendido;
	}
	public Integer getAtencionId() {
		return atencionId;
	}
	public void setAtencionId(Integer atencionId) {
		this.atencionId = atencionId;
	}
	
	
	
	
}
