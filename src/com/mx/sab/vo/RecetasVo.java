package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class RecetasVo implements Serializable {
	
	private static final long serialVersionUID = 6530689179075469017L;
	private int idReceta;
	private int idMedicamento;
	private String medicamento;
	private int idUnidad;
	private String unidadDescripcion;
	private int unidad;
	private int idDurante;
	private String duranteDescripcion;
	private int durante;
	private int idCada;
	private String cadaDescripcion;
	private int cada;	
	private String dosisNo;
	private String dosis;	
	private String observaciones;
	public int getIdReceta() {
		return idReceta;
	}
	public void setIdReceta(int idReceta) {
		this.idReceta = idReceta;
	}
	public int getIdMedicamento() {
		return idMedicamento;
	}
	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	public String getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}
	public int getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(int idUnidad) {
		this.idUnidad = idUnidad;
	}
	public String getUnidadDescripcion() {
		return unidadDescripcion;
	}
	public void setUnidadDescripcion(String unidadDescripcion) {
		this.unidadDescripcion = unidadDescripcion;
	}
	public int getUnidad() {
		return unidad;
	}
	public void setUnidad(int unidad) {
		this.unidad = unidad;
	}
	public int getIdDurante() {
		return idDurante;
	}
	public void setIdDurante(int idDurante) {
		this.idDurante = idDurante;
	}
	public String getDuranteDescripcion() {
		return duranteDescripcion;
	}
	public void setDuranteDescripcion(String duranteDescripcion) {
		this.duranteDescripcion = duranteDescripcion;
	}
	public int getDurante() {
		return durante;
	}
	public void setDurante(int durante) {
		this.durante = durante;
	}
	public int getIdCada() {
		return idCada;
	}
	public void setIdCada(int idCada) {
		this.idCada = idCada;
	}
	public String getCadaDescripcion() {
		return cadaDescripcion;
	}
	public void setCadaDescripcion(String cadaDescripcion) {
		this.cadaDescripcion = cadaDescripcion;
	}
	public int getCada() {
		return cada;
	}
	public void setCada(int cada) {
		this.cada = cada;
	}
	public String getDosisNo() {
		return dosisNo;
	}
	public void setDosisNo(String dosisNo) {
		this.dosisNo = dosisNo;
	}
	public String getDosis() {
		return dosis;
	}
	public void setDosis(String dosis) {
		this.dosis = dosis;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	
}
