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
 * Catescolaridad entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catescolaridad", catalog = "my_db_rrg")
public class Catescolaridad implements java.io.Serializable {

	// Fields

	private Integer escolaridadId;
	private String descripcion;
	private Set<HcPersonalnopatologicos> hcPersonalnopatologicoses = new HashSet<HcPersonalnopatologicos>(
			0);
	private Set<Afiliadodemografico> afiliadodemograficos = new HashSet<Afiliadodemografico>(
			0);

	// Constructors

	/** default constructor */
	public Catescolaridad() {
	}

	/** full constructor */
	public Catescolaridad(String descripcion,
			Set<HcPersonalnopatologicos> hcPersonalnopatologicoses,
			Set<Afiliadodemografico> afiliadodemograficos) {
		this.descripcion = descripcion;
		this.hcPersonalnopatologicoses = hcPersonalnopatologicoses;
		this.afiliadodemograficos = afiliadodemograficos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "EscolaridadId", unique = true, nullable = false)
	public Integer getEscolaridadId() {
		return this.escolaridadId;
	}

	public void setEscolaridadId(Integer escolaridadId) {
		this.escolaridadId = escolaridadId;
	}

	@Column(name = "Descripcion", length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catescolaridad")
	public Set<HcPersonalnopatologicos> getHcPersonalnopatologicoses() {
		return this.hcPersonalnopatologicoses;
	}

	public void setHcPersonalnopatologicoses(
			Set<HcPersonalnopatologicos> hcPersonalnopatologicoses) {
		this.hcPersonalnopatologicoses = hcPersonalnopatologicoses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catescolaridad")
	public Set<Afiliadodemografico> getAfiliadodemograficos() {
		return this.afiliadodemograficos;
	}

	public void setAfiliadodemograficos(
			Set<Afiliadodemografico> afiliadodemograficos) {
		this.afiliadodemograficos = afiliadodemograficos;
	}

}