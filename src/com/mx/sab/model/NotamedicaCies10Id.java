package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * NotamedicaCies10Id entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class NotamedicaCies10Id implements java.io.Serializable {

	// Fields

	private Integer notaMedicaId;
	private Integer cie10id;

	// Constructors

	/** default constructor */
	public NotamedicaCies10Id() {
	}

	/** full constructor */
	public NotamedicaCies10Id(Integer notaMedicaId, Integer cie10id) {
		this.notaMedicaId = notaMedicaId;
		this.cie10id = cie10id;
	}

	// Property accessors

	@Column(name = "NotaMedicaId", nullable = false)
	public Integer getNotaMedicaId() {
		return this.notaMedicaId;
	}

	public void setNotaMedicaId(Integer notaMedicaId) {
		this.notaMedicaId = notaMedicaId;
	}

	@Column(name = "CIE10Id", nullable = false)
	public Integer getCie10id() {
		return this.cie10id;
	}

	public void setCie10id(Integer cie10id) {
		this.cie10id = cie10id;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof NotamedicaCies10Id))
			return false;
		NotamedicaCies10Id castOther = (NotamedicaCies10Id) other;

		return ((this.getNotaMedicaId() == castOther.getNotaMedicaId()) || (this
				.getNotaMedicaId() != null
				&& castOther.getNotaMedicaId() != null && this
				.getNotaMedicaId().equals(castOther.getNotaMedicaId())))
				&& ((this.getCie10id() == castOther.getCie10id()) || (this
						.getCie10id() != null && castOther.getCie10id() != null && this
						.getCie10id().equals(castOther.getCie10id())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getNotaMedicaId() == null ? 0 : this.getNotaMedicaId()
						.hashCode());
		result = 37 * result
				+ (getCie10id() == null ? 0 : this.getCie10id().hashCode());
		return result;
	}

}