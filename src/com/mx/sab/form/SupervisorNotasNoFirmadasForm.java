package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class SupervisorNotasNoFirmadasForm extends ParentForm implements Serializable{
	
	private static final long serialVersionUID = 6140076486880860658L;
	private String motivo;
	private int autorizarRechazar;
	private int idAfiliado;
	private int idAtencionMedica;
	private int tipoMotivo;
	private boolean finalizo;
	private int medicoAfiliado;
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public int getAutorizarRechazar() {
		return autorizarRechazar;
	}
	public void setAutorizarRechazar(int autorizarRechazar) {
		this.autorizarRechazar = autorizarRechazar;
	}
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
	public int getTipoMotivo() {
		return tipoMotivo;
	}
	public void setTipoMotivo(int tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}
	public boolean isFinalizo() {
		return finalizo;
	}
	public void setFinalizo(boolean finalizo) {
		this.finalizo = finalizo;
	}
	public int getMedicoAfiliado() {
		return medicoAfiliado;
	}
	public void setMedicoAfiliado(int medicoAfiliado) {
		this.medicoAfiliado = medicoAfiliado;
	}

	
	
}
