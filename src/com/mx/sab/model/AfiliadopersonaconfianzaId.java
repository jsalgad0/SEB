package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AfiliadopersonaconfianzaId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AfiliadopersonaconfianzaId implements java.io.Serializable {

	// Fields

	private Integer afiliadoId;
	private Integer personaId;

	// Constructors

	/** default constructor */
	public AfiliadopersonaconfianzaId() {
	}

	/** full constructor */
	public AfiliadopersonaconfianzaId(Integer afiliadoId, Integer personaId) {
		this.afiliadoId = afiliadoId;
		this.personaId = personaId;
	}

	// Property accessors

	@Column(name = "AfiliadoId", nullable = false)
	public Integer getAfiliadoId() {
		return this.afiliadoId;
	}

	public void setAfiliadoId(Integer afiliadoId) {
		this.afiliadoId = afiliadoId;
	}

	@Column(name = "PersonaId", nullable = false)
	public Integer getPersonaId() {
		return this.personaId;
	}

	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AfiliadopersonaconfianzaId))
			return false;
		AfiliadopersonaconfianzaId castOther = (AfiliadopersonaconfianzaId) other;

		return ((this.getAfiliadoId() == castOther.getAfiliadoId()) || (this
				.getAfiliadoId() != null && castOther.getAfiliadoId() != null && this
				.getAfiliadoId().equals(castOther.getAfiliadoId())))
				&& ((this.getPersonaId() == castOther.getPersonaId()) || (this
						.getPersonaId() != null
						&& castOther.getPersonaId() != null && this
						.getPersonaId().equals(castOther.getPersonaId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAfiliadoId() == null ? 0 : this.getAfiliadoId()
						.hashCode());
		result = 37 * result
				+ (getPersonaId() == null ? 0 : this.getPersonaId().hashCode());
		return result;
	}

}