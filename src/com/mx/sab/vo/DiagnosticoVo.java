package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class DiagnosticoVo implements Serializable {

	private static final long serialVersionUID = 1998639421313185210L;
	private int idNotaMedica;
	private int idDiagnostico;
	private int idTipoDiagnostico;
	private String codigo;
	private String diagnostico;
	public int getIdNotaMedica() {
		return idNotaMedica;
	}
	public void setIdNotaMedica(int idNotaMedica) {
		this.idNotaMedica = idNotaMedica;
	}
	public int getIdDiagnostico() {
		return idDiagnostico;
	}
	public void setIdDiagnostico(int idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}
	public int getIdTipoDiagnostico() {
		return idTipoDiagnostico;
	}
	public void setIdTipoDiagnostico(int idTipoDiagnostico) {
		this.idTipoDiagnostico = idTipoDiagnostico;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	
	

}
