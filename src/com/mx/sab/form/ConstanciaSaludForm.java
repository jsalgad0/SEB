package com.mx.sab.form;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import lombok.Data;

@Data
public class ConstanciaSaludForm extends ParentForm implements Serializable{
	
	private static final long serialVersionUID = 1971270854644873464L;
	private int idAgenda; 
	private ByteArrayOutputStream file;
	private String medico;
	private String nombre;
	private String descripcion;
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
	public String getMedico() {
		return medico;
	}
	public void setMedico(String medico) {
		this.medico = medico;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	

}