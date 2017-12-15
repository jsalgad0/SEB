package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;

import com.mx.sab.model.Catcolonias;
import com.mx.sab.model.Catestados;
import com.mx.sab.model.Catmunicipios;
import com.mx.sab.model.Cattipoprestador;
import com.mx.sab.model.Cattipospersonas;
import com.mx.sab.model.Prestadores;

import lombok.Data;

@Data
public class PrestadorForm extends ParentForm implements Serializable {
	
	private static final long serialVersionUID = -5304473645189836908L;
	private int idPrestador;
	private String prestador;
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

	public int getIdPrestador() {
		return idPrestador;
	}

	public void setIdPrestador(int idPrestador) {
		this.idPrestador = idPrestador;
	}

	public String getPrestador() {
		return prestador;
	}

	public void setPrestador(String prestador) {
		this.prestador = prestador;
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
	
	
	
	
}
