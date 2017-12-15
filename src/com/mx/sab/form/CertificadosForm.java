package com.mx.sab.form;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

@Data
public class CertificadosForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = -2588745105523380823L;
	private int idAgenda;
	private int idAtencion; 
	private Collection<Integer> certificados;
	private ByteArrayOutputStream file;
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public Collection<Integer> getCertificados() {
		return certificados;
	}
	public void setCertificados(Collection<Integer> certificados) {
		this.certificados = certificados;
	}
	public ByteArrayOutputStream getFile() {
		return file;
	}
	public void setFile(ByteArrayOutputStream file) {
		this.file = file;
	}

	
}

