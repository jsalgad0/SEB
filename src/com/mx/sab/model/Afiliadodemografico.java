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
 * Afiliadodemografico entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "afiliadodemografico", catalog = "my_db_rrg")
public class Afiliadodemografico implements java.io.Serializable {

	// Fields

	private Integer demograficoId;
	private Catgrupossanguineos catgrupossanguineos;
	private Catescolaridad catescolaridad;
	private Catocupacion catocupacion;
	private Catestadocivil catestadocivil;
	private Afiliado afiliado;
	private String nacionalidad;
	private Integer rhpositivo;
	private String nivelSocEco;
	private String religion;
	private Integer inmigrante;
	private Integer indigena;
	private String etnia;
	private Integer hablaEs;

	// Constructors

	/** default constructor */
	public Afiliadodemografico() {
	}

	/** minimal constructor */
	public Afiliadodemografico(Afiliado afiliado, Integer hablaEs) {
		this.afiliado = afiliado;
		this.hablaEs = hablaEs;
	}

	/** full constructor */
	public Afiliadodemografico(Catgrupossanguineos catgrupossanguineos,
			Catescolaridad catescolaridad, Catocupacion catocupacion,
			Catestadocivil catestadocivil, Afiliado afiliado,
			String nacionalidad, Integer rhpositivo, String nivelSocEco,
			String religion, Integer inmigrante, Integer indigena,
			String etnia, Integer hablaEs) {
		this.catgrupossanguineos = catgrupossanguineos;
		this.catescolaridad = catescolaridad;
		this.catocupacion = catocupacion;
		this.catestadocivil = catestadocivil;
		this.afiliado = afiliado;
		this.nacionalidad = nacionalidad;
		this.rhpositivo = rhpositivo;
		this.nivelSocEco = nivelSocEco;
		this.religion = religion;
		this.inmigrante = inmigrante;
		this.indigena = indigena;
		this.etnia = etnia;
		this.hablaEs = hablaEs;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DemograficoId", unique = true, nullable = false)
	public Integer getDemograficoId() {
		return this.demograficoId;
	}

	public void setDemograficoId(Integer demograficoId) {
		this.demograficoId = demograficoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GrupoSanguineoId")
	public Catgrupossanguineos getCatgrupossanguineos() {
		return this.catgrupossanguineos;
	}

	public void setCatgrupossanguineos(Catgrupossanguineos catgrupossanguineos) {
		this.catgrupossanguineos = catgrupossanguineos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EscolaridadId")
	public Catescolaridad getCatescolaridad() {
		return this.catescolaridad;
	}

	public void setCatescolaridad(Catescolaridad catescolaridad) {
		this.catescolaridad = catescolaridad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OcupacionId")
	public Catocupacion getCatocupacion() {
		return this.catocupacion;
	}

	public void setCatocupacion(Catocupacion catocupacion) {
		this.catocupacion = catocupacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstadoCivilId")
	public Catestadocivil getCatestadocivil() {
		return this.catestadocivil;
	}

	public void setCatestadocivil(Catestadocivil catestadocivil) {
		this.catestadocivil = catestadocivil;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AfiliadoId", nullable = false)
	public Afiliado getAfiliado() {
		return this.afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	@Column(name = "Nacionalidad", length = 50)
	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Column(name = "RHPositivo")
	public Integer getRhpositivo() {
		return this.rhpositivo;
	}

	public void setRhpositivo(Integer rhpositivo) {
		this.rhpositivo = rhpositivo;
	}

	@Column(name = "NivelSocEco", length = 50)
	public String getNivelSocEco() {
		return this.nivelSocEco;
	}

	public void setNivelSocEco(String nivelSocEco) {
		this.nivelSocEco = nivelSocEco;
	}

	@Column(name = "Religion", length = 50)
	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	@Column(name = "Inmigrante")
	public Integer getInmigrante() {
		return this.inmigrante;
	}

	public void setInmigrante(Integer inmigrante) {
		this.inmigrante = inmigrante;
	}

	@Column(name = "Indigena")
	public Integer getIndigena() {
		return this.indigena;
	}

	public void setIndigena(Integer indigena) {
		this.indigena = indigena;
	}

	@Column(name = "Etnia", length = 50)
	public String getEtnia() {
		return this.etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = etnia;
	}

	@Column(name = "HablaEs", nullable = false)
	public Integer getHablaEs() {
		return this.hablaEs;
	}

	public void setHablaEs(Integer hablaEs) {
		this.hablaEs = hablaEs;
	}

}