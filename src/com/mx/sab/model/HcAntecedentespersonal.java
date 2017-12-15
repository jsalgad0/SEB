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
 * HcAntecedentespersonal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hc_antecedentespersonal", catalog = "my_db_rrg")
public class HcAntecedentespersonal implements java.io.Serializable {

	// Fields

	private Integer atencedentesPersonalesId;
	private Historiaclinica historiaclinica;
	private Boolean diabeteMellitusPer;
	private Boolean hipertensionArterialPer;
	private Boolean neoplasiasPer;
	private Boolean cardiopatiasPer;
	private Boolean alergiasPer;
	private Boolean obesidadPer;
	private Boolean tuberculosisPer;
	private Boolean tabaquismoPer;
	private Boolean alcoholismoPer;
	private Boolean dependencuAdrogaPer;
	private Boolean dependenciaAmedicamentosPer;
	private Boolean malformacionesPer;
	private Boolean disfuncionesFamiliaresPer;
	private Boolean quirurgiasPer;
	private String otrasPer;

	// Constructors

	/** default constructor */
	public HcAntecedentespersonal() {
	}

	/** minimal constructor */
	public HcAntecedentespersonal(Historiaclinica historiaclinica) {
		this.historiaclinica = historiaclinica;
	}

	/** full constructor */
	public HcAntecedentespersonal(Historiaclinica historiaclinica,
			Boolean diabeteMellitusPer, Boolean hipertensionArterialPer,
			Boolean neoplasiasPer, Boolean cardiopatiasPer,
			Boolean alergiasPer, Boolean obesidadPer, Boolean tuberculosisPer,
			Boolean tabaquismoPer, Boolean alcoholismoPer,
			Boolean dependencuAdrogaPer, Boolean dependenciaAmedicamentosPer,
			Boolean malformacionesPer, Boolean disfuncionesFamiliaresPer,
			Boolean quirurgiasPer, String otrasPer) {
		this.historiaclinica = historiaclinica;
		this.diabeteMellitusPer = diabeteMellitusPer;
		this.hipertensionArterialPer = hipertensionArterialPer;
		this.neoplasiasPer = neoplasiasPer;
		this.cardiopatiasPer = cardiopatiasPer;
		this.alergiasPer = alergiasPer;
		this.obesidadPer = obesidadPer;
		this.tuberculosisPer = tuberculosisPer;
		this.tabaquismoPer = tabaquismoPer;
		this.alcoholismoPer = alcoholismoPer;
		this.dependencuAdrogaPer = dependencuAdrogaPer;
		this.dependenciaAmedicamentosPer = dependenciaAmedicamentosPer;
		this.malformacionesPer = malformacionesPer;
		this.disfuncionesFamiliaresPer = disfuncionesFamiliaresPer;
		this.quirurgiasPer = quirurgiasPer;
		this.otrasPer = otrasPer;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AtencedentesPersonalesId", unique = true, nullable = false)
	public Integer getAtencedentesPersonalesId() {
		return this.atencedentesPersonalesId;
	}

	public void setAtencedentesPersonalesId(Integer atencedentesPersonalesId) {
		this.atencedentesPersonalesId = atencedentesPersonalesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HistoriaClinicaId", nullable = false)
	public Historiaclinica getHistoriaclinica() {
		return this.historiaclinica;
	}

	public void setHistoriaclinica(Historiaclinica historiaclinica) {
		this.historiaclinica = historiaclinica;
	}

	@Column(name = "DiabeteMellitusPer")
	public Boolean getDiabeteMellitusPer() {
		return this.diabeteMellitusPer;
	}

	public void setDiabeteMellitusPer(Boolean diabeteMellitusPer) {
		this.diabeteMellitusPer = diabeteMellitusPer;
	}

	@Column(name = "HipertensionArterialPer")
	public Boolean getHipertensionArterialPer() {
		return this.hipertensionArterialPer;
	}

	public void setHipertensionArterialPer(Boolean hipertensionArterialPer) {
		this.hipertensionArterialPer = hipertensionArterialPer;
	}

	@Column(name = "NeoplasiasPer")
	public Boolean getNeoplasiasPer() {
		return this.neoplasiasPer;
	}

	public void setNeoplasiasPer(Boolean neoplasiasPer) {
		this.neoplasiasPer = neoplasiasPer;
	}

	@Column(name = "CardiopatiasPer")
	public Boolean getCardiopatiasPer() {
		return this.cardiopatiasPer;
	}

	public void setCardiopatiasPer(Boolean cardiopatiasPer) {
		this.cardiopatiasPer = cardiopatiasPer;
	}

	@Column(name = "AlergiasPer")
	public Boolean getAlergiasPer() {
		return this.alergiasPer;
	}

	public void setAlergiasPer(Boolean alergiasPer) {
		this.alergiasPer = alergiasPer;
	}

	@Column(name = "ObesidadPer")
	public Boolean getObesidadPer() {
		return this.obesidadPer;
	}

	public void setObesidadPer(Boolean obesidadPer) {
		this.obesidadPer = obesidadPer;
	}

	@Column(name = "TuberculosisPer")
	public Boolean getTuberculosisPer() {
		return this.tuberculosisPer;
	}

	public void setTuberculosisPer(Boolean tuberculosisPer) {
		this.tuberculosisPer = tuberculosisPer;
	}

	@Column(name = "TabaquismoPer")
	public Boolean getTabaquismoPer() {
		return this.tabaquismoPer;
	}

	public void setTabaquismoPer(Boolean tabaquismoPer) {
		this.tabaquismoPer = tabaquismoPer;
	}

	@Column(name = "AlcoholismoPer")
	public Boolean getAlcoholismoPer() {
		return this.alcoholismoPer;
	}

	public void setAlcoholismoPer(Boolean alcoholismoPer) {
		this.alcoholismoPer = alcoholismoPer;
	}

	@Column(name = "DependencuADrogaPer")
	public Boolean getDependencuAdrogaPer() {
		return this.dependencuAdrogaPer;
	}

	public void setDependencuAdrogaPer(Boolean dependencuAdrogaPer) {
		this.dependencuAdrogaPer = dependencuAdrogaPer;
	}

	@Column(name = "DependenciaAMedicamentosPer")
	public Boolean getDependenciaAmedicamentosPer() {
		return this.dependenciaAmedicamentosPer;
	}

	public void setDependenciaAmedicamentosPer(
			Boolean dependenciaAmedicamentosPer) {
		this.dependenciaAmedicamentosPer = dependenciaAmedicamentosPer;
	}

	@Column(name = "MalformacionesPer")
	public Boolean getMalformacionesPer() {
		return this.malformacionesPer;
	}

	public void setMalformacionesPer(Boolean malformacionesPer) {
		this.malformacionesPer = malformacionesPer;
	}

	@Column(name = "DisfuncionesFamiliaresPer")
	public Boolean getDisfuncionesFamiliaresPer() {
		return this.disfuncionesFamiliaresPer;
	}

	public void setDisfuncionesFamiliaresPer(Boolean disfuncionesFamiliaresPer) {
		this.disfuncionesFamiliaresPer = disfuncionesFamiliaresPer;
	}

	@Column(name = "QuirurgiasPer")
	public Boolean getQuirurgiasPer() {
		return this.quirurgiasPer;
	}

	public void setQuirurgiasPer(Boolean quirurgiasPer) {
		this.quirurgiasPer = quirurgiasPer;
	}

	@Column(name = "OtrasPer", length = 100)
	public String getOtrasPer() {
		return this.otrasPer;
	}

	public void setOtrasPer(String otrasPer) {
		this.otrasPer = otrasPer;
	}

}