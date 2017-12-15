package com.mx.sab.model;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ConvenioCuadroprestacionesId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ConvenioCuadroprestacionesId implements java.io.Serializable {

	// Fields

	private Integer convenioId;
	private Integer cuadroPrestacionId;
	private Date vigenteDesde;
	private Date vigenteHasta;

	// Constructors

	/** default constructor */
	public ConvenioCuadroprestacionesId() {
	}

	/** full constructor */
	public ConvenioCuadroprestacionesId(Integer convenioId,
			Integer cuadroPrestacionId, Date vigenteDesde, Date vigenteHasta) {
		this.convenioId = convenioId;
		this.cuadroPrestacionId = cuadroPrestacionId;
		this.vigenteDesde = vigenteDesde;
		this.vigenteHasta = vigenteHasta;
	}

	// Property accessors

	@Column(name = "ConvenioId", nullable = false)
	public Integer getConvenioId() {
		return this.convenioId;
	}

	public void setConvenioId(Integer convenioId) {
		this.convenioId = convenioId;
	}

	@Column(name = "CuadroPrestacionId", nullable = false)
	public Integer getCuadroPrestacionId() {
		return this.cuadroPrestacionId;
	}

	public void setCuadroPrestacionId(Integer cuadroPrestacionId) {
		this.cuadroPrestacionId = cuadroPrestacionId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VigenteDesde", nullable = false, length = 10)
	public Date getVigenteDesde() {
		return this.vigenteDesde;
	}

	public void setVigenteDesde(Date vigenteDesde) {
		this.vigenteDesde = vigenteDesde;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VigenteHasta", nullable = false, length = 10)
	public Date getVigenteHasta() {
		return this.vigenteHasta;
	}

	public void setVigenteHasta(Date vigenteHasta) {
		this.vigenteHasta = vigenteHasta;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ConvenioCuadroprestacionesId))
			return false;
		ConvenioCuadroprestacionesId castOther = (ConvenioCuadroprestacionesId) other;

		return ((this.getConvenioId() == castOther.getConvenioId()) || (this
				.getConvenioId() != null && castOther.getConvenioId() != null && this
				.getConvenioId().equals(castOther.getConvenioId())))
				&& ((this.getCuadroPrestacionId() == castOther
						.getCuadroPrestacionId()) || (this
						.getCuadroPrestacionId() != null
						&& castOther.getCuadroPrestacionId() != null && this
						.getCuadroPrestacionId().equals(
								castOther.getCuadroPrestacionId())))
				&& ((this.getVigenteDesde() == castOther.getVigenteDesde()) || (this
						.getVigenteDesde() != null
						&& castOther.getVigenteDesde() != null && this
						.getVigenteDesde().equals(castOther.getVigenteDesde())))
				&& ((this.getVigenteHasta() == castOther.getVigenteHasta()) || (this
						.getVigenteHasta() != null
						&& castOther.getVigenteHasta() != null && this
						.getVigenteHasta().equals(castOther.getVigenteHasta())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getConvenioId() == null ? 0 : this.getConvenioId()
						.hashCode());
		result = 37
				* result
				+ (getCuadroPrestacionId() == null ? 0 : this
						.getCuadroPrestacionId().hashCode());
		result = 37
				* result
				+ (getVigenteDesde() == null ? 0 : this.getVigenteDesde()
						.hashCode());
		result = 37
				* result
				+ (getVigenteHasta() == null ? 0 : this.getVigenteHasta()
						.hashCode());
		return result;
	}

}