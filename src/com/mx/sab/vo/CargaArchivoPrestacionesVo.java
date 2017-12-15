package com.mx.sab.vo;

import java.io.Serializable;
import java.util.Collection;

import com.mx.sab.model.Prestacionasegurador;

import lombok.Data;

@Data
public class CargaArchivoPrestacionesVo implements Serializable{
	
	private static final long serialVersionUID = -6561475712880133831L;

	private Prestacionasegurador prestacionesAsegurador;
	private EquivalenciasSabVo equivalenciasSabVo;
	private int numeroFila;
	private boolean filaError;
	public Prestacionasegurador getPrestacionesAsegurador() {
		return prestacionesAsegurador;
	}
	public void setPrestacionesAsegurador(Prestacionasegurador prestacionesAsegurador) {
		this.prestacionesAsegurador = prestacionesAsegurador;
	}
	public EquivalenciasSabVo getEquivalenciasSabVo() {
		return equivalenciasSabVo;
	}
	public void setEquivalenciasSabVo(EquivalenciasSabVo equivalenciasSabVo) {
		this.equivalenciasSabVo = equivalenciasSabVo;
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
