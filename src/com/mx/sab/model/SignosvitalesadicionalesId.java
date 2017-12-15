package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SignosvitalesadicionalesId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SignosvitalesadicionalesId implements java.io.Serializable {

	// Fields

	private Integer signosVitalesId;
	private Integer catSignosVitalesAdicionalesId;
	private String valor;

	// Constructors

	/** default constructor */
	public SignosvitalesadicionalesId() {
	}

	/** full constructor */
	public SignosvitalesadicionalesId(Integer signosVitalesId,
			Integer catSignosVitalesAdicionalesId, String valor) {
		this.signosVitalesId = signosVitalesId;
		this.catSignosVitalesAdicionalesId = catSignosVitalesAdicionalesId;
		this.valor = valor;
	}

	// Property accessors

	@Column(name = "SignosVitalesId", nullable = false)
	public Integer getSignosVitalesId() {
		return this.signosVitalesId;
	}

	public void setSignosVitalesId(Integer signosVitalesId) {
		this.signosVitalesId = signosVitalesId;
	}

	@Column(name = "CatSignosVitalesAdicionalesId", nullable = false)
	public Integer getCatSignosVitalesAdicionalesId() {
		return this.catSignosVitalesAdicionalesId;
	}

	public void setCatSignosVitalesAdicionalesId(
			Integer catSignosVitalesAdicionalesId) {
		this.catSignosVitalesAdicionalesId = catSignosVitalesAdicionalesId;
	}

	@Column(name = "Valor", nullable = false, length = 10)
	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SignosvitalesadicionalesId))
			return false;
		SignosvitalesadicionalesId castOther = (SignosvitalesadicionalesId) other;

		return ((this.getSignosVitalesId() == castOther.getSignosVitalesId()) || (this
				.getSignosVitalesId() != null
				&& castOther.getSignosVitalesId() != null && this
				.getSignosVitalesId().equals(castOther.getSignosVitalesId())))
				&& ((this.getCatSignosVitalesAdicionalesId() == castOther
						.getCatSignosVitalesAdicionalesId()) || (this
						.getCatSignosVitalesAdicionalesId() != null
						&& castOther.getCatSignosVitalesAdicionalesId() != null && this
						.getCatSignosVitalesAdicionalesId().equals(
								castOther.getCatSignosVitalesAdicionalesId())))
				&& ((this.getValor() == castOther.getValor()) || (this
						.getValor() != null && castOther.getValor() != null && this
						.getValor().equals(castOther.getValor())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getSignosVitalesId() == null ? 0 : this.getSignosVitalesId()
						.hashCode());
		result = 37
				* result
				+ (getCatSignosVitalesAdicionalesId() == null ? 0 : this
						.getCatSignosVitalesAdicionalesId().hashCode());
		result = 37 * result
				+ (getValor() == null ? 0 : this.getValor().hashCode());
		return result;
	}

}