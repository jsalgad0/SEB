package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class PacientesRecibidosForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = -4978364590861252569L;	
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private int todoRecepcion;
	private int pagina;
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
	public int getTodoRecepcion() {
		return todoRecepcion;
	}
	public void setTodoRecepcion(int todoRecepcion) {
		this.todoRecepcion = todoRecepcion;
	}
	public int getPagina() {
		return pagina;
	}
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	
	
	
}
