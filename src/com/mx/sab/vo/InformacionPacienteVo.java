package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class InformacionPacienteVo implements Serializable{

	private static final long serialVersionUID = -7860453563547792317L;
	private int idOcupacion;
	private String nacionalidad;
	private String religion;
	private String nivelSocioeconomico;
	private String nombreResponsable;
	private String parentescoResponsable;
	private int edadResponsable;
	private String direccionResponsable;
	private String lugarResponsable;
	private String telefonoResponsable;
	public int getIdOcupacion() {
		return idOcupacion;
	}
	public void setIdOcupacion(int idOcupacion) {
		this.idOcupacion = idOcupacion;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getNivelSocioeconomico() {
		return nivelSocioeconomico;
	}
	public void setNivelSocioeconomico(String nivelSocioeconomico) {
		this.nivelSocioeconomico = nivelSocioeconomico;
	}
	public String getNombreResponsable() {
		return nombreResponsable;
	}
	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}
	public String getParentescoResponsable() {
		return parentescoResponsable;
	}
	public void setParentescoResponsable(String parentescoResponsable) {
		this.parentescoResponsable = parentescoResponsable;
	}
	public int getEdadResponsable() {
		return edadResponsable;
	}
	public void setEdadResponsable(int edadResponsable) {
		this.edadResponsable = edadResponsable;
	}
	public String getDireccionResponsable() {
		return direccionResponsable;
	}
	public void setDireccionResponsable(String direccionResponsable) {
		this.direccionResponsable = direccionResponsable;
	}
	public String getLugarResponsable() {
		return lugarResponsable;
	}
	public void setLugarResponsable(String lugarResponsable) {
		this.lugarResponsable = lugarResponsable;
	}
	public String getTelefonoResponsable() {
		return telefonoResponsable;
	}
	public void setTelefonoResponsable(String telefonoResponsable) {
		this.telefonoResponsable = telefonoResponsable;
	}
	
	
	
}
