package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class RolesVo implements Serializable{

	private static final long serialVersionUID = 1182664975642100160L;
	private Integer rolesId;
	private String rol;
	private String seleccionado;
	public Integer getRolesId() {
		return rolesId;
	}
	public void setRolesId(Integer rolesId) {
		this.rolesId = rolesId;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(String seleccionado) {
		this.seleccionado = seleccionado;
	}
	
	
	
}
