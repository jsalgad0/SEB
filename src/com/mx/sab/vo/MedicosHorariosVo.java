package com.mx.sab.vo;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

@Data
public class MedicosHorariosVo implements Serializable {

	private static final long serialVersionUID = 724115889442908414L;

	private int idUsuario;
	private String nombre;
	private Collection<HorariosVo> horariosVos;
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Collection<HorariosVo> getHorariosVos() {
		return horariosVos;
	}
	public void setHorariosVos(Collection<HorariosVo> horariosVos) {
		this.horariosVos = horariosVos;
	}
	
	
}
