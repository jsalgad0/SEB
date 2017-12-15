package com.mx.sab.form;

import java.io.Serializable;


import lombok.Data;

@Data
public class MedicoLicenciaMedicaForm extends ParentForm implements Serializable{

	
	private static final long serialVersionUID = -5384127399273481319L;
	private int atencionMedicaId;
	private int licenciaMedicaId;
	private String fechaEmision;
	private String folio;
	private int cie10;
	private String diagnostico;
	private String fechaInicio;
	private String fechaFin;
	private int idLicMedicaMotivo;
	private int idLicMedicaCaracter;
	private int idLicMedicaTipoServicio;
	private String dias;
	private String diasLetra;
	public int getAtencionMedicaId() {
		return atencionMedicaId;
	}
	public void setAtencionMedicaId(int atencionMedicaId) {
		this.atencionMedicaId = atencionMedicaId;
	}
	public int getLicenciaMedicaId() {
		return licenciaMedicaId;
	}
	public void setLicenciaMedicaId(int licenciaMedicaId) {
		this.licenciaMedicaId = licenciaMedicaId;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public int getCie10() {
		return cie10;
	}
	public void setCie10(int cie10) {
		this.cie10 = cie10;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getIdLicMedicaMotivo() {
		return idLicMedicaMotivo;
	}
	public void setIdLicMedicaMotivo(int idLicMedicaMotivo) {
		this.idLicMedicaMotivo = idLicMedicaMotivo;
	}
	public int getIdLicMedicaCaracter() {
		return idLicMedicaCaracter;
	}
	public void setIdLicMedicaCaracter(int idLicMedicaCaracter) {
		this.idLicMedicaCaracter = idLicMedicaCaracter;
	}
	public int getIdLicMedicaTipoServicio() {
		return idLicMedicaTipoServicio;
	}
	public void setIdLicMedicaTipoServicio(int idLicMedicaTipoServicio) {
		this.idLicMedicaTipoServicio = idLicMedicaTipoServicio;
	}
	public String getDias() {
		return dias;
	}
	public void setDias(String dias) {
		this.dias = dias;
	}
	public String getDiasLetra() {
		return diasLetra;
	}
	public void setDiasLetra(String diasLetra) {
		this.diasLetra = diasLetra;
	}
	
	

}
