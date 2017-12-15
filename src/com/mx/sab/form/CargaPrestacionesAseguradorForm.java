package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Data
public class CargaPrestacionesAseguradorForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = -9172803560563689064L;
	private String rfc;
	private CommonsMultipartFile file;
	private String error;
	private String exito;
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
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
	
	
	
	
}
