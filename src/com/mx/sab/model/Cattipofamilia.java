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
 * Cattipofamilia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattipofamilia", catalog = "my_db_rrg")
public class Cattipofamilia implements java.io.Serializable {

	// Fields

	private Integer tipoFamiliaId;
	private String descripcion;
	private Set<HcFasedelciclo> hcFasedelciclos = new HashSet<HcFasedelciclo>(0);

	// Constructors

	/** default constructor */
	public Cattipofamilia() {
	}

	/** minimal constructor */
	public Cattipofamilia(String descripcion) {
		this.descripcion = descripcion;
	}

	/** full constructor */
	public Cattipofamilia(String descripcion,
			Set<HcFasedelciclo> hcFasedelciclos) {
		this.descripcion = descripcion;
		this.hcFasedelciclos = hcFasedelciclos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TipoFamiliaId", unique = true, nullable = false)
	public Integer getTipoFamiliaId() {
		return this.tipoFamiliaId;
	}

	public void setTipoFamiliaId(Integer tipoFamiliaId) {
		this.tipoFamiliaId = tipoFamiliaId;
	}

	@Column(name = "Descripcion", nullable = false, length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cattipofamilia")
	public Set<HcFasedelciclo> getHcFasedelciclos() {
		return this.hcFasedelciclos;
	}

	public void setHcFasedelciclos(Set<HcFasedelciclo> hcFasedelciclos) {
		this.hcFasedelciclos = hcFasedelciclos;
	}

}