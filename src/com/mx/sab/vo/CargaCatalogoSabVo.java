package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

import com.mx.sab.model.Catprestacion;

@Data
public class CargaCatalogoSabVo implements Serializable{
	
	private static final long serialVersionUID = -6561475712880133831L;

	private Catprestacion catPrestacion;
	private int numeroFila;
	private boolean filaError;
	public Catprestacion getCatPrestacion() {
		return catPrestacion;
	}
	public void setCatPrestacion(Catprestacion catPrestacion) {
		this.catPrestacion = catPrestacion;
	}
	public int getNumeroFila() {
		return numeroFila;
	}
	public void setNumeroFila(int numeroFila) {
		this.numeroFila = numeroFila;
	}
	public boolean isFilaError() {
		return filaError;
	}
	public void setFilaError(boolean filaError) {
		this.filaError = filaError;
	}
	
	
	
	
}
