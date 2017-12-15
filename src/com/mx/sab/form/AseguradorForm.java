package com.mx.sab.form;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Data;

import com.mx.sab.model.Aseguradores;

@Data
public class AseguradorForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = -9172803560563689064L;
	private int idAsegurador;
	private String asegurador;
	private int idEstado;
	private int idMunicipio;
	private int idColonia;
	private String calle;
	private String numeroExterno;
	private String numeroInterno;
	private String cp;
	private String nombre;
	private String telefono1;
	private String telefono2;
	private String correo;
	private String rfc;
	private int busqueda;
	private CommonsMultipartFile file;
	public int getIdAsegurador() {
		return idAsegurador;
	}
	public void setIdAsegurador(int idAsegurador) {
		this.idAsegurador = idAsegurador;
	}
	public String getAsegurador() {
		return asegurador;
	}
	public void setAsegurador(String asegurador) {
		this.asegurador = asegurador;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public int getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public int getIdColonia() {
		return idColonia;
	}
	public void setIdColonia(int idColonia) {
		this.idColonia = idColonia;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumeroExterno() {
		return numeroExterno;
	}
	public void setNumeroExterno(String numeroExterno) {
		this.numeroExterno = numeroExterno;
	}
	public String getNumeroInterno() {
		return numeroInterno;
	}
	public void setNumeroInterno(String numeroInterno) {
		this.numeroInterno = numeroInterno;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public int getBusqueda() {
		return busqueda;
	}
	public void setBusqueda(int busqueda) {
		this.busqueda = busqueda;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	
	
	
}
