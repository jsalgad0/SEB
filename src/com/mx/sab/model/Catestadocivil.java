package com.mx.sab.model;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Catestadocivil entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catestadocivil", catalog = "my_db_rrg")
public class Catestadocivil implements java.io.Serializable {

	// Fields

	private Integer estadoCivilId;
	private String descripcion;
	private Set<HcPersonalnopatologicos> hcPersonalnopatologicoses = new HashSet<HcPersonalnopatologicos>(
			0);
	private Set<Afiliadodemografico> afiliadodemograficos = new HashSet<Afiliadodemografico>(
			0);

	// Constructors

	/** default constructor */
	public Catestadocivil() {
	}

	/** minimal constructor */
	public Catestadocivil(String descripcion) {
		this.descripcion = descripcion;
	}

	/** full constructor */
	public Catestadocivil(String descripcion,
			Set<HcPersonalnopatologicos> hcPersonalnopatologicoses,
			Set<Afiliadodemografico> afiliadodemograficos) {
		this.descripcion = descripcion;
		this.hcPersonalnopatologicoses = hcPersonalnopatologicoses;
		this.afiliadodemograficos = afiliadodemograficos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "EstadoCivilId", unique = true, nullable = false)
	public Integer getEstadoCivilId() {
		return this.estadoCivilId;
	}

	public void setEstadoCivilId(Integer estadoCivilId) {
		this.estadoCivilId = estadoCivilId;
	}

	@Column(name = "Descripcion", nullable = false, length = 50)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestadocivil")
	public Set<HcPersonalnopatologicos> getHcPersonalnopatologicoses() {
		return this.hcPersonalnopatologicoses;
	}

	public void setHcPersonalnopatologicoses(
			Set<HcPersonalnopatologicos> hcPersonalnopatologicoses) {
		this.hcPersonalnopatologicoses = hcPersonalnopatologicoses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestadocivil")
	public Set<Afiliadodemografico> getAfiliadodemograficos() {
		return this.afiliadodemograficos;
	}

	public void setAfiliadodemograficos(
			Set<Afiliadodemografico> afiliadodemograficos) {
		this.afiliadodemograficos = afiliadodemograficos;
	}

}