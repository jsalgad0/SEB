package com.mx.sab.vo;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

@Data
public class TipoIdentificadorVo implements Serializable{

	private static final long serialVersionUID = -2356205702659425532L;
	private Collection<String> id;
	private Collection<String> valor;
	public Collection<String> getId() {
		return id;
	}
	public void setId(Collection<String> id) {
		this.id = id;
	}
	public Collection<String> getValor() {
		return valor;
	}
	public void setValor(Collection<String> valor) {
		this.valor = valor;
	}
	
	
	
}
