package com.mx.sab.vo;

import lombok.Data;

@Data
public class PermisoEspecialVo {

	private int idAfiliado;
	private int idAtencionMedica;
	private String nombre;
	private String motivo;
	private int tipoMotivo;
	private boolean autorizado;
	public int getIdAfiliado() {
		return idAfiliado;
	}
	public void setIdAfiliado(int idAfiliado) {
		this.idAfiliado = idAfiliado;
	}
	public int getIdAtencionMedica() {
		return idAtencionMedica;
	}
	public void setIdAtencionMedica(int idAtencionMedica) {
		this.idAtencionMedica = idAtencionMedica;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public int getTipoMotivo() {
		return tipoMotivo;
	}
	public void setTipoMotivo(int tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}
	public boolean isAutorizado() {
		return autorizado;
	}
	public void setAutorizado(boolean autorizado) {
		this.autorizado = autorizado;
	}
	
	
	
}
