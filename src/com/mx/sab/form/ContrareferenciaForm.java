package com.mx.sab.form;

import java.io.Serializable;

import com.mx.sab.model.Contrareferencia;

import lombok.Data;

@Data
public class ContrareferenciaForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = 6886035047754627338L;
	private int idAgenda;
	private Contrareferencia contrareferencia;
	private String fecha;
	private String lugarAtencion;
	private int idDiagnostico;
	private String diagnostico;
	private boolean finalizoAtencionMedica;
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public Contrareferencia getContrareferencia() {
		return contrareferencia;
	}
	public void setContrareferencia(Contrareferencia contrareferencia) {
		this.contrareferencia = contrareferencia;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getLugarAtencion() {
		return lugarAtencion;
	}
	public void setLugarAtencion(String lugarAtencion) {
		this.lugarAtencion = lugarAtencion;
	}
	public int getIdDiagnostico() {
		return idDiagnostico;
	}
	public void setIdDiagnostico(int idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public boolean isFinalizoAtencionMedica() {
		return finalizoAtencionMedica;
	}
	public void setFinalizoAtencionMedica(boolean finalizoAtencionMedica) {
		this.finalizoAtencionMedica = finalizoAtencionMedica;
	}
	
	
}
