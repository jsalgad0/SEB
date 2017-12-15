package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

import com.mx.sab.vo.TomaSignosVo;

@Data
public class NotaMedicaForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = -816066458751698712L;
	private int atencionMedicaId;
	private int notaMedicaId;
	private String motivoConsulta;
	private String exploracionFisica;
	private int diagnosticoPrincipalId;
	private String diagnosticoPrincipalDescripcion;
	private int idTipoDiagnostico;
	private String planASeguir;
	private String diagnostico;
	public int getAtencionMedicaId() {
		return atencionMedicaId;
	}
	public void setAtencionMedicaId(int atencionMedicaId) {
		this.atencionMedicaId = atencionMedicaId;
	}
	public int getNotaMedicaId() {
		return notaMedicaId;
	}
	public void setNotaMedicaId(int notaMedicaId) {
		this.notaMedicaId = notaMedicaId;
	}
	public String getMotivoConsulta() {
		return motivoConsulta;
	}
	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}
	public String getExploracionFisica() {
		return exploracionFisica;
	}
	public void setExploracionFisica(String exploracionFisica) {
		this.exploracionFisica = exploracionFisica;
	}
	public int getDiagnosticoPrincipalId() {
		return diagnosticoPrincipalId;
	}
	public void setDiagnosticoPrincipalId(int diagnosticoPrincipalId) {
		this.diagnosticoPrincipalId = diagnosticoPrincipalId;
	}
	public String getDiagnosticoPrincipalDescripcion() {
		return diagnosticoPrincipalDescripcion;
	}
	public void setDiagnosticoPrincipalDescripcion(String diagnosticoPrincipalDescripcion) {
		this.diagnosticoPrincipalDescripcion = diagnosticoPrincipalDescripcion;
	}
	public int getIdTipoDiagnostico() {
		return idTipoDiagnostico;
	}
	public void setIdTipoDiagnostico(int idTipoDiagnostico) {
		this.idTipoDiagnostico = idTipoDiagnostico;
	}
	public String getPlanASeguir() {
		return planASeguir;
	}
	public void setPlanASeguir(String planASeguir) {
		this.planASeguir = planASeguir;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	
	
}
