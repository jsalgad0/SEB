package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CuadroprestacionPrestacionId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class CuadroprestacionPrestacionId implements java.io.Serializable {

	// Fields

	private Integer cuadroPrestacionId;
	private Integer prestacionId;

	// Constructors

	/** default constructor */
	public CuadroprestacionPrestacionId() {
	}

	/** full constructor */
	public CuadroprestacionPrestacionId(Integer cuadroPrestacionId,
			Integer prestacionId) {
		this.cuadroPrestacionId = cuadroPrestacionId;
		this.prestacionId = prestacionId;
	}

	// Property accessors

	@Column(name = "CuadroPrestacionId", nullable = false)
	public Integer getCuadroPrestacionId() {
		return this.cuadroPrestacionId;
	}

	public void setCuadroPrestacionId(Integer cuadroPrestacionId) {
		this.cuadroPrestacionId = cuadroPrestacionId;
	}

	@Column(name = "PrestacionId", nullable = false)
	public Integer getPrestacionId() {
		return this.prestacionId;
	}

	public void setPrestacionId(Integer prestacionId) {
		this.prestacionId = prestacionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CuadroprestacionPrestacionId))
			return false;
		CuadroprestacionPrestacionId castOther = (CuadroprestacionPrestacionId) other;

		return ((this.getCuadroPrestacionId() == castOther
				.getCuadroPrestacionId()) || (this.getCuadroPrestacionId() != null
				&& castOther.getCuadroPrestacionId() != null && this
				.getCuadroPrestacionId().equals(
						castOther.getCuadroPrestacionId())))
				&& ((this.getPrestacionId() == castOther.getPrestacionId()) || (this
						.getPrestacionId() != null
						&& castOther.getPrestacionId() != null && this
						.getPrestacionId().equals(castOther.getPrestacionId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCuadroPrestacionId() == null ? 0 : this
						.getCuadroPrestacionId().hashCode());
		result = 37
				* result
				+ (getPrestacionId() == null ? 0 : this.getPrestacionId()
						.hashCode());
		return result;
	}

}