package com.mx.sab.form;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import lombok.Data;

@Data
public class ConstanciaCuidadosMaternalesForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = 7803033373773940320L;
	private int idAgenda; 
	private ByteArrayOutputStream file;
	private String nombre;
	private String categoria;
	private String clave;
	private String adscripcion;
	private String horario;
	private String diagnostico;
	private String nombreClave;
	private String diaInicio;
	private String mesInicio;
	private String anioInicio;
	private String diaFin;
	private String mesFin;
	private String anioFin;
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public ByteArrayOutputStream getFile() {
		return file;
	}
	public void setFile(ByteArrayOutputStream file) {
		this.file = file;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getAdscripcion() {
		return adscripcion;
	}
	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getNombreClave() {
		return nombreClave;
	}
	public void setNombreClave(String nombreClave) {
		this.nombreClave = nombreClave;
	}
	public String getDiaInicio() {
		return diaInicio;
	}
	public void setDiaInicio(String diaInicio) {
		this.diaInicio = diaInicio;
	}
	public String getMesInicio() {
		return mesInicio;
	}
	public void setMesInicio(String mesInicio) {
		this.mesInicio = mesInicio;
	}
	public String getAnioInicio() {
		return anioInicio;
	}
	public void setAnioInicio(String anioInicio) {
		this.anioInicio = anioInicio;
	}
	public String getDiaFin() {
		return diaFin;
	}
	public void setDiaFin(String diaFin) {
		this.diaFin = diaFin;
	}
	public String getMesFin() {
		return mesFin;
	}
	public void setMesFin(String mesFin) {
		this.mesFin = mesFin;
	}
	public String getAnioFin() {
		return anioFin;
	}
	public void setAnioFin(String anioFin) {
		this.anioFin = anioFin;
	}	
	
}

