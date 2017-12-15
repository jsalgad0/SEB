package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class HistorialPorDiagnosticoVo implements Serializable{

	private static final long serialVersionUID = -7784353009421201224L;
	
	private int idDiagnostico;
	private int idAtencion;
	private String fecha;
	private String diagnostico;
	private String lugarAtencion;
	private String prestacion;
	public int getIdDiagnostico() {
		return idDiagnostico;
	}
	public void setIdDiagnostico(int idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getLugarAtencion() {
		return lugarAtencion;
	}
	public void setLugarAtencion(String lugarAtencion) {
		this.lugarAtencion = lugarAtencion;
	}
	public String getPrestacion() {
		return prestacion;
	}
	public void setPrestacion(String prestacion) {
		this.prestacion = prestacion;
	}
	
	
}
