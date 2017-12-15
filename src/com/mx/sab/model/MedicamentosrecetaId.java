package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MedicamentosrecetaId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class MedicamentosrecetaId implements java.io.Serializable {

	// Fields

	private Integer recetaId;
	private Integer medicamentoId;

	// Constructors

	/** default constructor */
	public MedicamentosrecetaId() {
	}

	/** full constructor */
	public MedicamentosrecetaId(Integer recetaId, Integer medicamentoId) {
		this.recetaId = recetaId;
		this.medicamentoId = medicamentoId;
	}

	// Property accessors

	@Column(name = "RecetaId", nullable = false)
	public Integer getRecetaId() {
		return this.recetaId;
	}

	public void setRecetaId(Integer recetaId) {
		this.recetaId = recetaId;
	}

	@Column(name = "MedicamentoId", nullable = false)
	public Integer getMedicamentoId() {
		return this.medicamentoId;
	}

	public void setMedicamentoId(Integer medicamentoId) {
		this.medicamentoId = medicamentoId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MedicamentosrecetaId))
			return false;
		MedicamentosrecetaId castOther = (MedicamentosrecetaId) other;

		return ((this.getRecetaId() == castOther.getRecetaId()) || (this
				.getRecetaId() != null && castOther.getRecetaId() != null && this
				.getRecetaId().equals(castOther.getRecetaId())))
				&& ((this.getMedicamentoId() == castOther.getMedicamentoId()) || (this
						.getMedicamentoId() != null
						&& castOther.getMedicamentoId() != null && this
						.getMedicamentoId()
						.equals(castOther.getMedicamentoId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRecetaId() == null ? 0 : this.getRecetaId().hashCode());
		result = 37
				* result
				+ (getMedicamentoId() == null ? 0 : this.getMedicamentoId()
						.hashCode());
		return result;
	}

}