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
 * HcAntecedentesfamiliares entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hc_antecedentesfamiliares", catalog = "my_db_rrg")
public class HcAntecedentesfamiliares implements java.io.Serializable {

	// Fields

	private Integer antecedentesFamiliaresId;
	private Historiaclinica historiaclinica;
	private Boolean diabeteMellitus;
	private Boolean hipertensionArterial;
	private Boolean neoplasias;
	private Boolean cardiopatias;
	private Boolean alergias;
	private Boolean obesidad;
	private Boolean tuberculosis;
	private Boolean tabaquismo;
	private Boolean alcoholismo;
	private Boolean dependenciaAdroga;
	private Boolean dependenciaAmedicamentos;
	private Boolean malformaciones;
	private Boolean disfuncionesFamiliares;
	private Boolean quirurgias;
	private String otras;

	// Constructors

	/** default constructor */
	public HcAntecedentesfamiliares() {
	}

	/** minimal constructor */
	public HcAntecedentesfamiliares(Historiaclinica historiaclinica) {
		this.historiaclinica = historiaclinica;
	}

	/** full constructor */
	public HcAntecedentesfamiliares(Historiaclinica historiaclinica,
			Boolean diabeteMellitus, Boolean hipertensionArterial,
			Boolean neoplasias, Boolean cardiopatias, Boolean alergias,
			Boolean obesidad, Boolean tuberculosis, Boolean tabaquismo,
			Boolean alcoholismo, Boolean dependenciaAdroga,
			Boolean dependenciaAmedicamentos, Boolean malformaciones,
			Boolean disfuncionesFamiliares, Boolean quirurgias, String otras) {
		this.historiaclinica = historiaclinica;
		this.diabeteMellitus = diabeteMellitus;
		this.hipertensionArterial = hipertensionArterial;
		this.neoplasias = neoplasias;
		this.cardiopatias = cardiopatias;
		this.alergias = alergias;
		this.obesidad = obesidad;
		this.tuberculosis = tuberculosis;
		this.tabaquismo = tabaquismo;
		this.alcoholismo = alcoholismo;
		this.dependenciaAdroga = dependenciaAdroga;
		this.dependenciaAmedicamentos = dependenciaAmedicamentos;
		this.malformaciones = malformaciones;
		this.disfuncionesFamiliares = disfuncionesFamiliares;
		this.quirurgias = quirurgias;
		this.otras = otras;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AntecedentesFamiliaresId", unique = true, nullable = false)
	public Integer getAntecedentesFamiliaresId() {
		return this.antecedentesFamiliaresId;
	}

	public void setAntecedentesFamiliaresId(Integer antecedentesFamiliaresId) {
		this.antecedentesFamiliaresId = antecedentesFamiliaresId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HistoriaClinicaId", nullable = false)
	public Historiaclinica getHistoriaclinica() {
		return this.historiaclinica;
	}

	public void setHistoriaclinica(Historiaclinica historiaclinica) {
		this.historiaclinica = historiaclinica;
	}

	@Column(name = "DiabeteMellitus")
	public Boolean getDiabeteMellitus() {
		return this.diabeteMellitus;
	}

	public void setDiabeteMellitus(Boolean diabeteMellitus) {
		this.diabeteMellitus = diabeteMellitus;
	}

	@Column(name = "HipertensionArterial")
	public Boolean getHipertensionArterial() {
		return this.hipertensionArterial;
	}

	public void setHipertensionArterial(Boolean hipertensionArterial) {
		this.hipertensionArterial = hipertensionArterial;
	}

	@Column(name = "Neoplasias")
	public Boolean getNeoplasias() {
		return this.neoplasias;
	}

	public void setNeoplasias(Boolean neoplasias) {
		this.neoplasias = neoplasias;
	}

	@Column(name = "Cardiopatias")
	public Boolean getCardiopatias() {
		return this.cardiopatias;
	}

	public void setCardiopatias(Boolean cardiopatias) {
		this.cardiopatias = cardiopatias;
	}

	@Column(name = "Alergias")
	public Boolean getAlergias() {
		return this.alergias;
	}

	public void setAlergias(Boolean alergias) {
		this.alergias = alergias;
	}

	@Column(name = "Obesidad")
	public Boolean getObesidad() {
		return this.obesidad;
	}

	public void setObesidad(Boolean obesidad) {
		this.obesidad = obesidad;
	}

	@Column(name = "Tuberculosis")
	public Boolean getTuberculosis() {
		return this.tuberculosis;
	}

	public void setTuberculosis(Boolean tuberculosis) {
		this.tuberculosis = tuberculosis;
	}

	@Column(name = "Tabaquismo")
	public Boolean getTabaquismo() {
		return this.tabaquismo;
	}

	public void setTabaquismo(Boolean tabaquismo) {
		this.tabaquismo = tabaquismo;
	}

	@Column(name = "Alcoholismo")
	public Boolean getAlcoholismo() {
		return this.alcoholismo;
	}

	public void setAlcoholismo(Boolean alcoholismo) {
		this.alcoholismo = alcoholismo;
	}

	@Column(name = "DependenciaADroga")
	public Boolean getDependenciaAdroga() {
		return this.dependenciaAdroga;
	}

	public void setDependenciaAdroga(Boolean dependenciaAdroga) {
		this.dependenciaAdroga = dependenciaAdroga;
	}

	@Column(name = "DependenciaAMedicamentos")
	public Boolean getDependenciaAmedicamentos() {
		return this.dependenciaAmedicamentos;
	}

	public void setDependenciaAmedicamentos(Boolean dependenciaAmedicamentos) {
		this.dependenciaAmedicamentos = dependenciaAmedicamentos;
	}

	@Column(name = "Malformaciones")
	public Boolean getMalformaciones() {
		return this.malformaciones;
	}

	public void setMalformaciones(Boolean malformaciones) {
		this.malformaciones = malformaciones;
	}

	@Column(name = "DisfuncionesFamiliares")
	public Boolean getDisfuncionesFamiliares() {
		return this.disfuncionesFamiliares;
	}

	public void setDisfuncionesFamiliares(Boolean disfuncionesFamiliares) {
		this.disfuncionesFamiliares = disfuncionesFamiliares;
	}

	@Column(name = "Quirurgias")
	public Boolean getQuirurgias() {
		return this.quirurgias;
	}

	public void setQuirurgias(Boolean quirurgias) {
		this.quirurgias = quirurgias;
	}

	@Column(name = "Otras", length = 200)
	public String getOtras() {
		return this.otras;
	}

	public void setOtras(String otras) {
		this.otras = otras;
	}

}