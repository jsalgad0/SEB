package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * HcFasedelciclo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hc_fasedelciclo", catalog = "my_db_rrg")
public class HcFasedelciclo implements java.io.Serializable {

	// Fields

	private Integer faseDelCicloId;
	private Historiaclinica historiaclinica;
	private Cattipofamilia cattipofamilia;
	private Boolean matrimonio;
	private Boolean expansion;
	private Boolean dispersion;
	private Boolean independencia;
	private Boolean retiroMuerte;

	// Constructors

	/** default constructor */
	public HcFasedelciclo() {
	}

	/** minimal constructor */
	public HcFasedelciclo(Historiaclinica historiaclinica) {
		this.historiaclinica = historiaclinica;
	}

	/** full constructor */
	public HcFasedelciclo(Historiaclinica historiaclinica,
			Cattipofamilia cattipofamilia, Boolean matrimonio,
			Boolean expansion, Boolean dispersion, Boolean independencia,
			Boolean retiroMuerte) {
		this.historiaclinica = historiaclinica;
		this.cattipofamilia = cattipofamilia;
		this.matrimonio = matrimonio;
		this.expansion = expansion;
		this.dispersion = dispersion;
		this.independencia = independencia;
		this.retiroMuerte = retiroMuerte;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "FaseDelCicloId", unique = true, nullable = false)
	public Integer getFaseDelCicloId() {
		return this.faseDelCicloId;
	}

	public void setFaseDelCicloId(Integer faseDelCicloId) {
		this.faseDelCicloId = faseDelCicloId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HistoriaClinicaId", nullable = false)
	public Historiaclinica getHistoriaclinica() {
		return this.historiaclinica;
	}

	public void setHistoriaclinica(Historiaclinica historiaclinica) {
		this.historiaclinica = historiaclinica;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoFamiliaId")
	public Cattipofamilia getCattipofamilia() {
		return this.cattipofamilia;
	}

	public void setCattipofamilia(Cattipofamilia cattipofamilia) {
		this.cattipofamilia = cattipofamilia;
	}

	@Column(name = "Matrimonio")
	public Boolean getMatrimonio() {
		return this.matrimonio;
	}

	public void setMatrimonio(Boolean matrimonio) {
		this.matrimonio = matrimonio;
	}

	@Column(name = "Expansion")
	public Boolean getExpansion() {
		return this.expansion;
	}

	public void setExpansion(Boolean expansion) {
		this.expansion = expansion;
	}

	@Column(name = "Dispersion")
	public Boolean getDispersion() {
		return this.dispersion;
	}

	public void setDispersion(Boolean dispersion) {
		this.dispersion = dispersion;
	}

	@Column(name = "Independencia")
	public Boolean getIndependencia() {
		return this.independencia;
	}

	public void setIndependencia(Boolean independencia) {
		this.independencia = independencia;
	}

	@Column(name = "RetiroMuerte")
	public Boolean getRetiroMuerte() {
		return this.retiroMuerte;
	}

	public void setRetiroMuerte(Boolean retiroMuerte) {
		this.retiroMuerte = retiroMuerte;
	}

}