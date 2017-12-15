package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

@Data
public class ParentForm implements Serializable{

	private static final long serialVersionUID = -5202573451782483425L;
	private String error;
	private String exito;
	private Collection<String> errores;
	private boolean banderaError;
	private int count;
	private int display;
	private boolean editar;
	private boolean sinResultados;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getExito() {
		return exito;
	}
	public void setExito(String exito) {
		this.exito = exito;
	}
	public Collection<String> getErrores() {
		return errores;
	}
	public void setErrores(Collection<String> errores) {
		this.errores = errores;
	}
	public boolean isBanderaError() {
		return banderaError;
	}
	public void setBanderaError(boolean banderaError) {
		this.banderaError = banderaError;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getDisplay() {
		return display;
	}
	public void setDisplay(int display) {
		this.display = display;
	}
	public boolean isEditar() {
		return editar;
	}
	public void setEditar(boolean editar) {
		this.editar = editar;
	}
	public boolean isSinResultados() {
		return sinResultados;
	}
	public void setSinResultados(boolean sinResultados) {
		this.sinResultados = sinResultados;
	}
	
	
	
}
