package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mx.sab.model.CuadroprestacionPrestacion;
import com.mx.sab.model.Cuadroprestaciones;
import com.mx.sab.model.Prestadores;

import lombok.Data;

@Data
public class CuadroPrestacionesForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = 1193282386668212298L;
	private Integer idPrestadores;
	private Integer idCuadroPrestacion;
	private Prestadores prestadores;
	private CommonsMultipartFile file;
	private String busqueda;
	private String busquedaP;
	
	private Cuadroprestaciones cuadroprestaciones;
	private Collection<CuadroprestacionPrestacion> cuadroprestacionPrestacions;
	public Integer getIdPrestadores() {
		return idPrestadores;
	}
	public void setIdPrestadores(Integer idPrestadores) {
		this.idPrestadores = idPrestadores;
	}
	public Integer getIdCuadroPrestacion() {
		return idCuadroPrestacion;
	}
	public void setIdCuadroPrestacion(Integer idCuadroPrestacion) {
		this.idCuadroPrestacion = idCuadroPrestacion;
	}
	public Prestadores getPrestadores() {
		return prestadores;
	}
	public void setPrestadores(Prestadores prestadores) {
		this.prestadores = prestadores;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	public String getBusqueda() {
		return busqueda;
	}
	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
	public String getBusquedaP() {
		return busquedaP;
	}
	public void setBusquedaP(String busquedaP) {
		this.busquedaP = busquedaP;
	}
	public Cuadroprestaciones getCuadroprestaciones() {
		return cuadroprestaciones;
	}
	public void setCuadroprestaciones(Cuadroprestaciones cuadroprestaciones) {
		this.cuadroprestaciones = cuadroprestaciones;
	}
	public Collection<CuadroprestacionPrestacion> getCuadroprestacionPrestacions() {
		return cuadroprestacionPrestacions;
	}
	public void setCuadroprestacionPrestacions(Collection<CuadroprestacionPrestacion> cuadroprestacionPrestacions) {
		this.cuadroprestacionPrestacions = cuadroprestacionPrestacions;
	}
	
	
}
