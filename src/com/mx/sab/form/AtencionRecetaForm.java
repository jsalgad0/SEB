package com.mx.sab.form;

import java.io.Serializable;
import java.util.List;

import com.mx.sab.vo.RecetasVo;

import lombok.Data;

@Data
public class AtencionRecetaForm extends ParentForm implements Serializable {
	
	private static final long serialVersionUID = -9119716438741683726L;
	private int idAtencion;
	private int idMedicamento;
	private String medicamento;
	private int idUnidad;
	private String unidadDescripcion;
	private String unidad;
	private int idDurante;
	private String duranteDescripcion;
	private String durante;
	private int idCada;
	private String cadaDescripcion;
	private String cada;	
	private String observaciones;
	private String dosisNo;
	private String dosis;
	private List<RecetasVo> recetasVos;
	
	private boolean resurtible;
	private String recetaCada;
	private String recetaDurante;
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
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
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
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
	public String getDurante() {
		return durante;
	}
	public void setDurante(String durante) {
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
	public String getCada() {
		return cada;
	}
	public void setCada(String cada) {
		this.cada = cada;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
	public List<RecetasVo> getRecetasVos() {
		return recetasVos;
	}
	public void setRecetasVos(List<RecetasVo> recetasVos) {
		this.recetasVos = recetasVos;
	}
	public boolean isResurtible() {
		return resurtible;
	}
	public void setResurtible(boolean resurtible) {
		this.resurtible = resurtible;
	}
	public String getRecetaCada() {
		return recetaCada;
	}
	public void setRecetaCada(String recetaCada) {
		this.recetaCada = recetaCada;
	}
	public String getRecetaDurante() {
		return recetaDurante;
	}
	public void setRecetaDurante(String recetaDurante) {
		this.recetaDurante = recetaDurante;
	}
	
	
	
}
