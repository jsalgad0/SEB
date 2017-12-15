package com.mx.sab.model;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Catmpf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catmpf", catalog = "my_db_rrg")
public class Catmpf implements java.io.Serializable {

	// Fields

	private Integer mpfId;
	private String descripcion;
	private Set<HcGineco> hcGinecos = new HashSet<HcGineco>(0);

	// Constructors

	/** default constructor */
	public Catmpf() {
	}

	/** minimal constructor */
	public Catmpf(Integer mpfId) {
		this.mpfId = mpfId;
	}

	/** full constructor */
	public Catmpf(Integer mpfId, String descripcion, Set<HcGineco> hcGinecos) {
		this.mpfId = mpfId;
		this.descripcion = descripcion;
		this.hcGinecos = hcGinecos;
	}

	// Property accessors
	@Id
	@Column(name = "MpfId", unique = true, nullable = false)
	public Integer getMpfId() {
		return this.mpfId;
	}

	public void setMpfId(Integer mpfId) {
		this.mpfId = mpfId;
	}

	@Column(name = "descripcion", length = 20)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catmpf")
	public Set<HcGineco> getHcGinecos() {
		return this.hcGinecos;
	}

	public void setHcGinecos(Set<HcGineco> hcGinecos) {
		this.hcGinecos = hcGinecos;
	}

}