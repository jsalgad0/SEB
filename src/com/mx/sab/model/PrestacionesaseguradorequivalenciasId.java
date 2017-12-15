package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PrestacionesaseguradorequivalenciasId entity. @author MyEclipse Persistence
 * Tools
 */
@Embeddable
public class PrestacionesaseguradorequivalenciasId implements
		java.io.Serializable {

	// Fields

	private Integer prestacionSabid;
	private Integer prestacionAseguradorId;

	// Constructors

	/** default constructor */
	public PrestacionesaseguradorequivalenciasId() {
	}

	/** full constructor */
	public PrestacionesaseguradorequivalenciasId(Integer prestacionSabid,
			Integer prestacionAseguradorId) {
		this.prestacionSabid = prestacionSabid;
		this.prestacionAseguradorId = prestacionAseguradorId;
	}

	// Property accessors

	@Column(name = "PrestacionSABId", nullable = false)
	public Integer getPrestacionSabid() {
		return this.prestacionSabid;
	}

	public void setPrestacionSabid(Integer prestacionSabid) {
		this.prestacionSabid = prestacionSabid;
	}

	@Column(name = "PrestacionAseguradorId", nullable = false)
	public Integer getPrestacionAseguradorId() {
		return this.prestacionAseguradorId;
	}

	public void setPrestacionAseguradorId(Integer prestacionAseguradorId) {
		this.prestacionAseguradorId = prestacionAseguradorId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PrestacionesaseguradorequivalenciasId))
			return false;
		PrestacionesaseguradorequivalenciasId castOther = (PrestacionesaseguradorequivalenciasId) other;

		return ((this.getPrestacionSabid() == castOther.getPrestacionSabid()) || (this
				.getPrestacionSabid() != null
				&& castOther.getPrestacionSabid() != null && this
				.getPrestacionSabid().equals(castOther.getPrestacionSabid())))
				&& ((this.getPrestacionAseguradorId() == castOther
						.getPrestacionAseguradorId()) || (this
						.getPrestacionAseguradorId() != null
						&& castOther.getPrestacionAseguradorId() != null && this
						.getPrestacionAseguradorId().equals(
								castOther.getPrestacionAseguradorId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPrestacionSabid() == null ? 0 : this.getPrestacionSabid()
						.hashCode());
		result = 37
				* result
				+ (getPrestacionAseguradorId() == null ? 0 : this
						.getPrestacionAseguradorId().hashCode());
		return result;
	}

}