package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Data;

import com.mx.sab.model.Convenios;
import com.mx.sab.model.CuadroprestacionPrestacion;
import com.mx.sab.model.Cuadroprestaciones;

@Data
public class ConveniosForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = -2465506235736057345L;
	private int idConvenio;
	private int idPrestador;
	private int idAsegurador;
	private int idLugarDeAtencion;
	private String codigoLugarAtencion;
	private String lugarAtencion;
	private String prestador;
	private String vigenteDesde;
	private String vigenteHasta;
	private String convenio;
	private String identificadorConvenio;
	private int busqueda;

	private CommonsMultipartFile file;

	public int getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(int idConvenio) {
		this.idConvenio = idConvenio;
	}

	public int getIdPrestador() {
		return idPrestador;
	}

	public void setIdPrestador(int idPrestador) {
		this.idPrestador = idPrestador;
	}

	public int getIdAsegurador() {
		return idAsegurador;
	}

	public void setIdAsegurador(int idAsegurador) {
		this.idAsegurador = idAsegurador;
	}

	public int getIdLugarDeAtencion() {
		return idLugarDeAtencion;
	}

	public void setIdLugarDeAtencion(int idLugarDeAtencion) {
		this.idLugarDeAtencion = idLugarDeAtencion;
	}

	public String getCodigoLugarAtencion() {
		return codigoLugarAtencion;
	}

	public void setCodigoLugarAtencion(String codigoLugarAtencion) {
		this.codigoLugarAtencion = codigoLugarAtencion;
	}

	public String getLugarAtencion() {
		return lugarAtencion;
	}

	public void setLugarAtencion(String lugarAtencion) {
		this.lugarAtencion = lugarAtencion;
	}

	public String getPrestador() {
		return prestador;
	}

	public void setPrestador(String prestador) {
		this.prestador = prestador;
	}

	public String getVigenteDesde() {
		return vigenteDesde;
	}

	public void setVigenteDesde(String vigenteDesde) {
		this.vigenteDesde = vigenteDesde;
	}

	public String getVigenteHasta() {
		return vigenteHasta;
	}

	public void setVigenteHasta(String vigenteHasta) {
		this.vigenteHasta = vigenteHasta;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public String getIdentificadorConvenio() {
		return identificadorConvenio;
	}

	public void setIdentificadorConvenio(String identificadorConvenio) {
		this.identificadorConvenio = identificadorConvenio;
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
