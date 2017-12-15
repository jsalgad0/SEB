package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class AutocompleteVo implements Serializable{

	private static final long serialVersionUID = 2955050801550912713L;
	private String value;
	private String label;
	private int cantidad;
	private String codigo;
	
	private String valor;
	private String aporte;
	private String copago;
	private String ordenServicio;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getAporte() {
		return aporte;
	}
	public void setAporte(String aporte) {
		this.aporte = aporte;
	}
	public String getCopago() {
		return copago;
	}
	public void setCopago(String copago) {
		this.copago = copago;
	}
	public String getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(String ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	
	
	
}
