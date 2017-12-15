package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class TomaSignosVo implements Serializable{

	
	private static final long serialVersionUID = -2836077374718972716L;
	private int idAtencion;
	private String nombre;
	private long edad;
	private String peso;
	private String altura;
	private String tensionArterial;
	private String fechaUltimaSomatometria;
	private Boolean guardado;
	private String fechaNacimiento;
	private String temperatura;
	private int llenado;
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getEdad() {
		return edad;
	}
	public void setEdad(long edad) {
		this.edad = edad;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getTensionArterial() {
		return tensionArterial;
	}
	public void setTensionArterial(String tensionArterial) {
		this.tensionArterial = tensionArterial;
	}
	public String getFechaUltimaSomatometria() {
		return fechaUltimaSomatometria;
	}
	public void setFechaUltimaSomatometria(String fechaUltimaSomatometria) {
		this.fechaUltimaSomatometria = fechaUltimaSomatometria;
	}
	public Boolean getGuardado() {
		return guardado;
	}
	public void setGuardado(Boolean guardado) {
		this.guardado = guardado;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}
	public int getLlenado() {
		return llenado;
	}
	public void setLlenado(int llenado) {
		this.llenado = llenado;
	}
	
	
	
	
}
