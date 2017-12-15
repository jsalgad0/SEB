package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AfiliadotipoidentificadorId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AfiliadotipoidentificadorId implements java.io.Serializable {

	// Fields

	private Integer afiliadoId;
	private Integer tipoIdentificadorId;

	// Constructors

	/** default constructor */
	public AfiliadotipoidentificadorId() {
	}

	/** full constructor */
	public AfiliadotipoidentificadorId(Integer afiliadoId,
			Integer tipoIdentificadorId) {
		this.afiliadoId = afiliadoId;
		this.tipoIdentificadorId = tipoIdentificadorId;
	}

	// Property accessors

	@Column(name = "AfiliadoId", nullable = false)
	public Integer getAfiliadoId() {
		return this.afiliadoId;
	}

	public void setAfiliadoId(Integer afiliadoId) {
		this.afiliadoId = afiliadoId;
	}

	@Column(name = "TipoIdentificadorId", nullable = false)
	public Integer getTipoIdentificadorId() {
		return this.tipoIdentificadorId;
	}

	public void setTipoIdentificadorId(Integer tipoIdentificadorId) {
		this.tipoIdentificadorId = tipoIdentificadorId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AfiliadotipoidentificadorId))
			return false;
		AfiliadotipoidentificadorId castOther = (AfiliadotipoidentificadorId) other;

		return ((this.getAfiliadoId() == castOther.getAfiliadoId()) || (this
				.getAfiliadoId() != null && castOther.getAfiliadoId() != null && this
				.getAfiliadoId().equals(castOther.getAfiliadoId())))
				&& ((this.getTipoIdentificadorId() == castOther
						.getTipoIdentificadorId()) || (this
						.getTipoIdentificadorId() != null
						&& castOther.getTipoIdentificadorId() != null && this
						.getTipoIdentificadorId().equals(
								castOther.getTipoIdentificadorId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAfiliadoId() == null ? 0 : this.getAfiliadoId()
						.hashCode());
		result = 37
				* result
				+ (getTipoIdentificadorId() == null ? 0 : this
						.getTipoIdentificadorId().hashCode());
		return result;
	}

}