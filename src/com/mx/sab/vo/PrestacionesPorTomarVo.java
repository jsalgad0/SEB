package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class PrestacionesPorTomarVo implements Serializable{
	
	private static final long serialVersionUID = -6561475712880133831L;

	private int idPrestacion;
	private String prestacion;
	private String orden;
	private int idAtencionMedicaPorTomar;
	private int cantidad;
	private String descripcion;
	private String fecha;
	private String ordenServicio;
	public int getIdPrestacion() {
		return idPrestacion;
	}
	public void setIdPrestacion(int idPrestacion) {
		this.idPrestacion = idPrestacion;
	}
	public String getPrestacion() {
		return prestacion;
	}
	public void setPrestacion(String prestacion) {
		this.prestacion = prestacion;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public int getIdAtencionMedicaPorTomar() {
		return idAtencionMedicaPorTomar;
	}
	public void setIdAtencionMedicaPorTomar(int idAtencionMedicaPorTomar) {
		this.idAtencionMedicaPorTomar = idAtencionMedicaPorTomar;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(String ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	
	
	
	
}
