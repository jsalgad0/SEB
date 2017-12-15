package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PrestadoreslugaresatencionId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class PrestadoreslugaresatencionId implements java.io.Serializable {

	// Fields

	private Integer lugarDeAtencionId;
	private Integer prestadorId;

	// Constructors

	/** default constructor */
	public PrestadoreslugaresatencionId() {
	}

	/** full constructor */
	public PrestadoreslugaresatencionId(Integer lugarDeAtencionId,
			Integer prestadorId) {
		this.lugarDeAtencionId = lugarDeAtencionId;
		this.prestadorId = prestadorId;
	}

	// Property accessors

	@Column(name = "LugarDeAtencionId", nullable = false)
	public Integer getLugarDeAtencionId() {
		return this.lugarDeAtencionId;
	}

	public void setLugarDeAtencionId(Integer lugarDeAtencionId) {
		this.lugarDeAtencionId = lugarDeAtencionId;
	}

	@Column(name = "PrestadorId", nullable = false)
	public Integer getPrestadorId() {
		return this.prestadorId;
	}

	public void setPrestadorId(Integer prestadorId) {
		this.prestadorId = prestadorId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PrestadoreslugaresatencionId))
			return false;
		PrestadoreslugaresatencionId castOther = (PrestadoreslugaresatencionId) other;

		return ((this.getLugarDeAtencionId() == castOther
				.getLugarDeAtencionId()) || (this.getLugarDeAtencionId() != null
				&& castOther.getLugarDeAtencionId() != null && this
				.getLugarDeAtencionId()
				.equals(castOther.getLugarDeAtencionId())))
				&& ((this.getPrestadorId() == castOther.getPrestadorId()) || (this
						.getPrestadorId() != null
						&& castOther.getPrestadorId() != null && this
						.getPrestadorId().equals(castOther.getPrestadorId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getLugarDeAtencionId() == null ? 0 : this
						.getLugarDeAtencionId().hashCode());
		result = 37
				* result
				+ (getPrestadorId() == null ? 0 : this.getPrestadorId()
						.hashCode());
		return result;
	}

}