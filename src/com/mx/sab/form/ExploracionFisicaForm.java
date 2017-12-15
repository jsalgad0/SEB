package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

import com.mx.sab.vo.TomaSignosVo;

@Data
public class ExploracionFisicaForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = -816066458751698712L;
	private int afiliadoId;
	private TomaSignosVo tomaSignosVo;
	private int idHistoriaClinica;
	private int idExploracionFisica;
	private boolean cabeza, cuello, torax, abdomen, extremidades, sistemaNervioso, sistemaCardio, aparatoDigestivo, sistemaMusculo;
	private String otrasEF;
	public int getAfiliadoId() {
		return afiliadoId;
	}
	public void setAfiliadoId(int afiliadoId) {
		this.afiliadoId = afiliadoId;
	}
	public TomaSignosVo getTomaSignosVo() {
		return tomaSignosVo;
	}
	public void setTomaSignosVo(TomaSignosVo tomaSignosVo) {
		this.tomaSignosVo = tomaSignosVo;
	}
	public int getIdHistoriaClinica() {
		return idHistoriaClinica;
	}
	public void setIdHistoriaClinica(int idHistoriaClinica) {
		this.idHistoriaClinica = idHistoriaClinica;
	}
	public int getIdExploracionFisica() {
		return idExploracionFisica;
	}
	public void setIdExploracionFisica(int idExploracionFisica) {
		this.idExploracionFisica = idExploracionFisica;
	}
	public boolean isCabeza() {
		return cabeza;
	}
	public void setCabeza(boolean cabeza) {
		this.cabeza = cabeza;
	}
	public boolean isCuello() {
		return cuello;
	}
	public void setCuello(boolean cuello) {
		this.cuello = cuello;
	}
	public boolean isTorax() {
		return torax;
	}
	public void setTorax(boolean torax) {
		this.torax = torax;
	}
	public boolean isAbdomen() {
		return abdomen;
	}
	public void setAbdomen(boolean abdomen) {
		this.abdomen = abdomen;
	}
	public boolean isExtremidades() {
		return extremidades;
	}
	public void setExtremidades(boolean extremidades) {
		this.extremidades = extremidades;
	}
	public boolean isSistemaNervioso() {
		return sistemaNervioso;
	}
	public void setSistemaNervioso(boolean sistemaNervioso) {
		this.sistemaNervioso = sistemaNervioso;
	}
	public boolean isSistemaCardio() {
		return sistemaCardio;
	}
	public void setSistemaCardio(boolean sistemaCardio) {
		this.sistemaCardio = sistemaCardio;
	}
	public boolean isAparatoDigestivo() {
		return aparatoDigestivo;
	}
	public void setAparatoDigestivo(boolean aparatoDigestivo) {
		this.aparatoDigestivo = aparatoDigestivo;
	}
	public boolean isSistemaMusculo() {
		return sistemaMusculo;
	}
	public void setSistemaMusculo(boolean sistemaMusculo) {
		this.sistemaMusculo = sistemaMusculo;
	}
	public String getOtrasEF() {
		return otrasEF;
	}
	public void setOtrasEF(String otrasEF) {
		this.otrasEF = otrasEF;
	}
	
	
	
}
