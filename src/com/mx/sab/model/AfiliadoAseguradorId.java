package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AfiliadoAseguradorId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AfiliadoAseguradorId implements java.io.Serializable {

	// Fields

	private Integer afiliadoId;
	private Integer aseguradorId;

	// Constructors

	/** default constructor */
	public AfiliadoAseguradorId() {
	}

	/** full constructor */
	public AfiliadoAseguradorId(Integer afiliadoId, Integer aseguradorId) {
		this.afiliadoId = afiliadoId;
		this.aseguradorId = aseguradorId;
	}

	// Property accessors

	@Column(name = "AfiliadoId", nullable = false)
	public Integer getAfiliadoId() {
		return this.afiliadoId;
	}

	public void setAfiliadoId(Integer afiliadoId) {
		this.afiliadoId = afiliadoId;
	}

	@Column(name = "AseguradorId", nullable = false)
	public Integer getAseguradorId() {
		return this.aseguradorId;
	}

	public void setAseguradorId(Integer aseguradorId) {
		this.aseguradorId = aseguradorId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AfiliadoAseguradorId))
			return false;
		AfiliadoAseguradorId castOther = (AfiliadoAseguradorId) other;

		return ((this.getAfiliadoId() == castOther.getAfiliadoId()) || (this
				.getAfiliadoId() != null && castOther.getAfiliadoId() != null && this
				.getAfiliadoId().equals(castOther.getAfiliadoId())))
				&& ((this.getAseguradorId() == castOther.getAseguradorId()) || (this
						.getAseguradorId() != null
						&& castOther.getAseguradorId() != null && this
						.getAseguradorId().equals(castOther.getAseguradorId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAfiliadoId() == null ? 0 : this.getAfiliadoId()
						.hashCode());
		result = 37
				* result
				+ (getAseguradorId() == null ? 0 : this.getAseguradorId()
						.hashCode());
		return result;
	}

}