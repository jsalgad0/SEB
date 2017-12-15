package com.mx.sab.form;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import lombok.Data;

@Data
public class ConstanciaAsistenciaForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = 5828859938311367433L;
	private int idAgenda; 
	private ByteArrayOutputStream file;
	private String descripcion;
	private String horaInicio;
	private String horaFin;
	private String nombre;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
