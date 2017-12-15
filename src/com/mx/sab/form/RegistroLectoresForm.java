package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

import com.mx.sab.model.Lectores;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Propietarioslector;

@Data
public class RegistroLectoresForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = 851212613095869921L;

	private Integer codigoLugarAtencion;
	private Integer codigoLugarAtencionAux;
	private Integer idLugarAtencion;
	private Integer idPropietarioLector;	
	private String rfcPropietarioLector;
	private String propietarioLector;
	private String lugarAtencion;
	private String noDeSerieInterno;
	private String noDeSerie;
	private String noDeSerieAux;
	private String idLector;
	
	private String busqueda;

	public Integer getCodigoLugarAtencion() {
		return codigoLugarAtencion;
	}

	public void setCodigoLugarAtencion(Integer codigoLugarAtencion) {
		this.codigoLugarAtencion = codigoLugarAtencion;
	}

	public Integer getCodigoLugarAtencionAux() {
		return codigoLugarAtencionAux;
	}

	public void setCodigoLugarAtencionAux(Integer codigoLugarAtencionAux) {
		this.codigoLugarAtencionAux = codigoLugarAtencionAux;
	}

	public Integer getIdLugarAtencion() {
		return idLugarAtencion;
	}

	public void setIdLugarAtencion(Integer idLugarAtencion) {
		this.idLugarAtencion = idLugarAtencion;
	}

	public Integer getIdPropietarioLector() {
		return idPropietarioLector;
	}

	public void setIdPropietarioLector(Integer idPropietarioLector) {
		this.idPropietarioLector = idPropietarioLector;
	}

	public String getRfcPropietarioLector() {
		return rfcPropietarioLector;
	}

	public void setRfcPropietarioLector(String rfcPropietarioLector) {
		this.rfcPropietarioLector = rfcPropietarioLector;
	}

	public String getPropietarioLector() {
		return propietarioLector;
	}

	public void setPropietarioLector(String propietarioLector) {
		this.propietarioLector = propietarioLector;
	}

	public String getLugarAtencion() {
		return lugarAtencion;
	}

	public void setLugarAtencion(String lugarAtencion) {
		this.lugarAtencion = lugarAtencion;
	}

	public String getNoDeSerieInterno() {
		return noDeSerieInterno;
	}

	public void setNoDeSerieInterno(String noDeSerieInterno) {
		this.noDeSerieInterno = noDeSerieInterno;
	}

	public String getNoDeSerie() {
		return noDeSerie;
	}

	public void setNoDeSerie(String noDeSerie) {
		this.noDeSerie = noDeSerie;
	}

	public String getNoDeSerieAux() {
		return noDeSerieAux;
	}

	public void setNoDeSerieAux(String noDeSerieAux) {
		this.noDeSerieAux = noDeSerieAux;
	}

	public String getIdLector() {
		return idLector;
	}

	public void setIdLector(String idLector) {
		this.idLector = idLector;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
	
	
	
}
