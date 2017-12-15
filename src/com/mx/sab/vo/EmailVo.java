package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmailVo implements Serializable{

	private static final long serialVersionUID = 5739909385029238736L;
	private String correo;
	private String hostname;
	private String puerto;
	private String usuario;
	private String password;
	private String form;
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getPuerto() {
		return puerto;
	}
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	
	
	
}
