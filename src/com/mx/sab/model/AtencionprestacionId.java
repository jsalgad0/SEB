package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AtencionprestacionId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AtencionprestacionId implements java.io.Serializable {

	// Fields

	private Integer atencionMedicaId;
	private Integer prestacionId;

	// Constructors

	/** default constructor */
	public AtencionprestacionId() {
	}

	/** full constructor */
	public AtencionprestacionId(Integer atencionMedicaId, Integer prestacionId) {
		this.atencionMedicaId = atencionMedicaId;
		this.prestacionId = prestacionId;
	}

	// Property accessors

	@Column(name = "AtencionMedicaId", nullable = false)
	public Integer getAtencionMedicaId() {
		return this.atencionMedicaId;
	}

	public void setAtencionMedicaId(Integer atencionMedicaId) {
		this.atencionMedicaId = atencionMedicaId;
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
		if (!(other instanceof AtencionprestacionId))
			return false;
		AtencionprestacionId castOther = (AtencionprestacionId) other;

		return ((this.getAtencionMedicaId() == castOther.getAtencionMedicaId()) || (this
				.getAtencionMedicaId() != null
				&& castOther.getAtencionMedicaId() != null && this
				.getAtencionMedicaId().equals(castOther.getAtencionMedicaId())))
				&& ((this.getPrestacionId() == castOther.getPrestacionId()) || (this
						.getPrestacionId() != null
						&& castOther.getPrestacionId() != null && this
						.getPrestacionId().equals(castOther.getPrestacionId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAtencionMedicaId() == null ? 0 : this
						.getAtencionMedicaId().hashCode());
		result = 37
				* result
				+ (getPrestacionId() == null ? 0 : this.getPrestacionId()
						.hashCode());
		return result;
	}

}