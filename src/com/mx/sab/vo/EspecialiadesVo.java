package com.mx.sab.vo;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

@Data
public class EspecialiadesVo implements Serializable{

	private static final long serialVersionUID = -2356205702659425532L;
	private Collection<String> id;
	private Collection<String> matricula;
	public Collection<String> getId() {
		return id;
	}
	public void setId(Collection<String> id) {
		this.id = id;
	}
	public Collection<String> getMatricula() {
		return matricula;
	}
	public void setMatricula(Collection<String> matricula) {
		this.matricula = matricula;
	}
	
	
	
}
