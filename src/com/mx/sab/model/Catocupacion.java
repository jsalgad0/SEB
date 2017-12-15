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
 * Catocupacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catocupacion", catalog = "my_db_rrg")
public class Catocupacion implements java.io.Serializable {

	// Fields

	private Integer ocupacionId;
	private String descripcion;
	private Set<Afiliadodemografico> afiliadodemograficos = new HashSet<Afiliadodemografico>(
			0);

	// Constructors

	/** default constructor */
	public Catocupacion() {
	}

	/** full constructor */
	public Catocupacion(String descripcion,
			Set<Afiliadodemografico> afiliadodemograficos) {
		this.descripcion = descripcion;
		this.afiliadodemograficos = afiliadodemograficos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "OcupacionId", unique = true, nullable = false)
	public Integer getOcupacionId() {
		return this.ocupacionId;
	}

	public void setOcupacionId(Integer ocupacionId) {
		this.ocupacionId = ocupacionId;
	}

	@Column(name = "Descripcion", length = 150)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catocupacion")
	public Set<Afiliadodemografico> getAfiliadodemograficos() {
		return this.afiliadodemograficos;
	}

	public void setAfiliadodemograficos(
			Set<Afiliadodemografico> afiliadodemograficos) {
		this.afiliadodemograficos = afiliadodemograficos;
	}

}