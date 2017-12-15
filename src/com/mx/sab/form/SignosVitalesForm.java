package com.mx.sab.form;

import java.io.Serializable;
import java.util.List;

import com.mx.sab.vo.SignosVitalesAdicionalesVo;

import lombok.Data;

@Data
public class SignosVitalesForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = 1281176269109211614L;
	private int idAtencion;
	private int primeraVez;
	private String peso;
	private String altura;
	private String tensionArterial;
	private String temperatura;
	private String indiceMasaCoporal;
	private String frecuenciaRespiratoria;
	private String frecuenciaCardiaca;
	private String glucosa;
	private String cintura;
	private String saturacionOxigeno;
	private int tipoSangre;
	private String observaciones;
	private boolean enfermeria;
	private List<SignosVitalesAdicionalesVo> signosVitalesAdicionalesVos;
	private int idPositivoNegativo;
	private int idTipoSangre;
	private int idDemografico;
	private int notaMedicaLlenada;
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public int getPrimeraVez() {
		return primeraVez;
	}
	public void setPrimeraVez(int primeraVez) {
		this.primeraVez = primeraVez;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getTensionArterial() {
		return tensionArterial;
	}
	public void setTensionArterial(String tensionArterial) {
		this.tensionArterial = tensionArterial;
	}
	public String getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}
	public String getIndiceMasaCoporal() {
		return indiceMasaCoporal;
	}
	public void setIndiceMasaCoporal(String indiceMasaCoporal) {
		this.indiceMasaCoporal = indiceMasaCoporal;
	}
	public String getFrecuenciaRespiratoria() {
		return frecuenciaRespiratoria;
	}
	public void setFrecuenciaRespiratoria(String frecuenciaRespiratoria) {
		this.frecuenciaRespiratoria = frecuenciaRespiratoria;
	}
	public String getFrecuenciaCardiaca() {
		return frecuenciaCardiaca;
	}
	public void setFrecuenciaCardiaca(String frecuenciaCardiaca) {
		this.frecuenciaCardiaca = frecuenciaCardiaca;
	}
	public String getGlucosa() {
		return glucosa;
	}
	public void setGlucosa(String glucosa) {
		this.glucosa = glucosa;
	}
	public String getCintura() {
		return cintura;
	}
	public void setCintura(String cintura) {
		this.cintura = cintura;
	}
	public String getSaturacionOxigeno() {
		return saturacionOxigeno;
	}
	public void setSaturacionOxigeno(String saturacionOxigeno) {
		this.saturacionOxigeno = saturacionOxigeno;
	}
	public int getTipoSangre() {
		return tipoSangre;
	}
	public void setTipoSangre(int tipoSangre) {
		this.tipoSangre = tipoSangre;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public boolean isEnfermeria() {
		return enfermeria;
	}
	public void setEnfermeria(boolean enfermeria) {
		this.enfermeria = enfermeria;
	}
	public List<SignosVitalesAdicionalesVo> getSignosVitalesAdicionalesVos() {
		return signosVitalesAdicionalesVos;
	}
	public void setSignosVitalesAdicionalesVos(List<SignosVitalesAdicionalesVo> signosVitalesAdicionalesVos) {
		this.signosVitalesAdicionalesVos = signosVitalesAdicionalesVos;
	}
	public int getIdPositivoNegativo() {
		return idPositivoNegativo;
	}
	public void setIdPositivoNegativo(int idPositivoNegativo) {
		this.idPositivoNegativo = idPositivoNegativo;
	}
	public int getIdTipoSangre() {
		return idTipoSangre;
	}
	public void setIdTipoSangre(int idTipoSangre) {
		this.idTipoSangre = idTipoSangre;
	}
	public int getIdDemografico() {
		return idDemografico;
	}
	public void setIdDemografico(int idDemografico) {
		this.idDemografico = idDemografico;
	}
	public int getNotaMedicaLlenada() {
		return notaMedicaLlenada;
	}
	public void setNotaMedicaLlenada(int notaMedicaLlenada) {
		this.notaMedicaLlenada = notaMedicaLlenada;
	}
	
	
	
	
}
