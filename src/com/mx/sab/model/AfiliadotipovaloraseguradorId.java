package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AfiliadotipovaloraseguradorId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AfiliadotipovaloraseguradorId implements java.io.Serializable {

	// Fields

	private Integer afiliadoId;
	private Integer tipoValorAseguradorId;
	private String tipoIdValor;

	// Constructors

	/** default constructor */
	public AfiliadotipovaloraseguradorId() {
	}

	/** full constructor */
	public AfiliadotipovaloraseguradorId(Integer afiliadoId,
			Integer tipoValorAseguradorId, String tipoIdValor) {
		this.afiliadoId = afiliadoId;
		this.tipoValorAseguradorId = tipoValorAseguradorId;
		this.tipoIdValor = tipoIdValor;
	}

	// Property accessors

	@Column(name = "AfiliadoId", nullable = false)
	public Integer getAfiliadoId() {
		return this.afiliadoId;
	}

	public void setAfiliadoId(Integer afiliadoId) {
		this.afiliadoId = afiliadoId;
	}

	@Column(name = "TipoValorAseguradorId", nullable = false)
	public Integer getTipoValorAseguradorId() {
		return this.tipoValorAseguradorId;
	}

	public void setTipoValorAseguradorId(Integer tipoValorAseguradorId) {
		this.tipoValorAseguradorId = tipoValorAseguradorId;
	}

	@Column(name = "TipoIdValor", nullable = false, length = 100)
	public String getTipoIdValor() {
		return this.tipoIdValor;
	}

	public void setTipoIdValor(String tipoIdValor) {
		this.tipoIdValor = tipoIdValor;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AfiliadotipovaloraseguradorId))
			return false;
		AfiliadotipovaloraseguradorId castOther = (AfiliadotipovaloraseguradorId) other;

		return ((this.getAfiliadoId() == castOther.getAfiliadoId()) || (this
				.getAfiliadoId() != null && castOther.getAfiliadoId() != null && this
				.getAfiliadoId().equals(castOther.getAfiliadoId())))
				&& ((this.getTipoValorAseguradorId() == castOther
						.getTipoValorAseguradorId()) || (this
						.getTipoValorAseguradorId() != null
						&& castOther.getTipoValorAseguradorId() != null && this
						.getTipoValorAseguradorId().equals(
								castOther.getTipoValorAseguradorId())))
				&& ((this.getTipoIdValor() == castOther.getTipoIdValor()) || (this
						.getTipoIdValor() != null
						&& castOther.getTipoIdValor() != null && this
						.getTipoIdValor().equals(castOther.getTipoIdValor())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAfiliadoId() == null ? 0 : this.getAfiliadoId()
						.hashCode());
		result = 37
				* result
				+ (getTipoValorAseguradorId() == null ? 0 : this
						.getTipoValorAseguradorId().hashCode());
		result = 37
				* result
				+ (getTipoIdValor() == null ? 0 : this.getTipoIdValor()
						.hashCode());
		return result;
	}

}