package com.mx.sab.form;

import java.io.Serializable;

import com.jcraft.jsch.UserInfo;

import lombok.Data;

@Data
public class SupervisorUsuariosPendientesForm extends ParentForm implements Serializable{
	
	private static final long serialVersionUID = 6140076486880860658L;
	
	private int idUsuario;
	private String respuestaAutentia;
	private String rfc;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private int idCausa;
	private int idTipoAutorizacion;
	private String fechaInicio;
	private String fechaFin;
	private int finalizo;
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getRespuestaAutentia() {
		return respuestaAutentia;
	}
	public void setRespuestaAutentia(String respuestaAutentia) {
		this.respuestaAutentia = respuestaAutentia;
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
	public int getFinalizo() {
		return finalizo;
	}
	public void setFinalizo(int finalizo) {
		this.finalizo = finalizo;
	}
	
	
	
}
