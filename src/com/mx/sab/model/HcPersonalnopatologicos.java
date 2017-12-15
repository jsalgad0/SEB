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
 * HcPersonalnopatologicos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hc_personalnopatologicos", catalog = "my_db_rrg")
public class HcPersonalnopatologicos implements java.io.Serializable {

	// Fields

	private Integer personalNoPatologicosId;
	private Historiaclinica historiaclinica;
	private Cathigiene cathigiene;
	private Catalimentacion catalimentacion;
	private Catescolaridad catescolaridad;
	private Catestadocivil catestadocivil;

	// Constructors

	/** default constructor */
	public HcPersonalnopatologicos() {
	}

	/** full constructor */
	public HcPersonalnopatologicos(Historiaclinica historiaclinica,
			Cathigiene cathigiene, Catalimentacion catalimentacion,
			Catescolaridad catescolaridad, Catestadocivil catestadocivil) {
		this.historiaclinica = historiaclinica;
		this.cathigiene = cathigiene;
		this.catalimentacion = catalimentacion;
		this.catescolaridad = catescolaridad;
		this.catestadocivil = catestadocivil;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PersonalNoPatologicosId", unique = true, nullable = false)
	public Integer getPersonalNoPatologicosId() {
		return this.personalNoPatologicosId;
	}

	public void setPersonalNoPatologicosId(Integer personalNoPatologicosId) {
		this.personalNoPatologicosId = personalNoPatologicosId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HistoriaClinicaId")
	public Historiaclinica getHistoriaclinica() {
		return this.historiaclinica;
	}

	public void setHistoriaclinica(Historiaclinica historiaclinica) {
		this.historiaclinica = historiaclinica;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HigieneId")
	public Cathigiene getCathigiene() {
		return this.cathigiene;
	}

	public void setCathigiene(Cathigiene cathigiene) {
		this.cathigiene = cathigiene;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AlimentacionId")
	public Catalimentacion getCatalimentacion() {
		return this.catalimentacion;
	}

	public void setCatalimentacion(Catalimentacion catalimentacion) {
		this.catalimentacion = catalimentacion;
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
	@JoinColumn(name = "EstadoCivilId")
	public Catestadocivil getCatestadocivil() {
		return this.catestadocivil;
	}

	public void setCatestadocivil(Catestadocivil catestadocivil) {
		this.catestadocivil = catestadocivil;
	}

}