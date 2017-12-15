package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PrestacionesprestadorequivalenciasId entity. @author MyEclipse Persistence
 * Tools
 */
@Embeddable
public class PrestacionesprestadorequivalenciasId implements
		java.io.Serializable {

	// Fields

	private Integer prestacionSabid;
	private Integer prestacionPrestadorId;

	// Constructors

	/** default constructor */
	public PrestacionesprestadorequivalenciasId() {
	}

	/** full constructor */
	public PrestacionesprestadorequivalenciasId(Integer prestacionSabid,
			Integer prestacionPrestadorId) {
		this.prestacionSabid = prestacionSabid;
		this.prestacionPrestadorId = prestacionPrestadorId;
	}

	// Property accessors

	@Column(name = "PrestacionSABId", nullable = false)
	public Integer getPrestacionSabid() {
		return this.prestacionSabid;
	}

	public void setPrestacionSabid(Integer prestacionSabid) {
		this.prestacionSabid = prestacionSabid;
	}

	@Column(name = "PrestacionPrestadorId", nullable = false)
	public Integer getPrestacionPrestadorId() {
		return this.prestacionPrestadorId;
	}

	public void setPrestacionPrestadorId(Integer prestacionPrestadorId) {
		this.prestacionPrestadorId = prestacionPrestadorId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PrestacionesprestadorequivalenciasId))
			return false;
		PrestacionesprestadorequivalenciasId castOther = (PrestacionesprestadorequivalenciasId) other;

		return ((this.getPrestacionSabid() == castOther.getPrestacionSabid()) || (this
				.getPrestacionSabid() != null
				&& castOther.getPrestacionSabid() != null && this
				.getPrestacionSabid().equals(castOther.getPrestacionSabid())))
				&& ((this.getPrestacionPrestadorId() == castOther
						.getPrestacionPrestadorId()) || (this
						.getPrestacionPrestadorId() != null
						&& castOther.getPrestacionPrestadorId() != null && this
						.getPrestacionPrestadorId().equals(
								castOther.getPrestacionPrestadorId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPrestacionSabid() == null ? 0 : this.getPrestacionSabid()
						.hashCode());
		result = 37
				* result
				+ (getPrestacionPrestadorId() == null ? 0 : this
						.getPrestacionPrestadorId().hashCode());
		return result;
	}

}