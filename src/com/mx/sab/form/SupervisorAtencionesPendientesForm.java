package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class SupervisorAtencionesPendientesForm extends ParentForm implements Serializable{
	
	private static final long serialVersionUID = 6140076486880860658L;
	private String motivo;
	private int autorizarRechazar;
	private int idAfiliado;
	private int idAtencionMedica;
	private int tipoMotivo;
	private boolean finalizo;
	private String rfc;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private int idCausa;
	private int idTipoAutorizacion;
	private String fechaInicio;
	private String fechaFin;
	private boolean actualizoEstatus;
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
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
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
	public int getIdCausa() {
		return idCausa;
	}
	public void setIdCausa(int idCausa) {
		this.idCausa = idCausa;
	}
	public int getIdTipoAutorizacion() {
		return idTipoAutorizacion;
	}
	public void setIdTipoAutorizacion(int idTipoAutorizacion) {
		this.idTipoAutorizacion = idTipoAutorizacion;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public boolean isActualizoEstatus() {
		return actualizoEstatus;
	}
	public void setActualizoEstatus(boolean actualizoEstatus) {
		this.actualizoEstatus = actualizoEstatus;
	}
	
	
	

}
