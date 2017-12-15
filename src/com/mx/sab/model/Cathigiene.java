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
 * Cathigiene entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cathigiene", catalog = "my_db_rrg")
public class Cathigiene implements java.io.Serializable {

	// Fields

	private Integer higieneId;
	private String descripcion;
	private Set<HcPersonalnopatologicos> hcPersonalnopatologicoses = new HashSet<HcPersonalnopatologicos>(
			0);

	// Constructors

	/** default constructor */
	public Cathigiene() {
	}

	/** minimal constructor */
	public Cathigiene(Integer higieneId) {
		this.higieneId = higieneId;
	}

	/** full constructor */
	public Cathigiene(Integer higieneId, String descripcion,
			Set<HcPersonalnopatologicos> hcPersonalnopatologicoses) {
		this.higieneId = higieneId;
		this.descripcion = descripcion;
		this.hcPersonalnopatologicoses = hcPersonalnopatologicoses;
	}

	// Property accessors
	@Id
	@Column(name = "HigieneId", unique = true, nullable = false)
	public Integer getHigieneId() {
		return this.higieneId;
	}

	public void setHigieneId(Integer higieneId) {
		this.higieneId = higieneId;
	}

	@Column(name = "Descripcion", length = 20)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cathigiene")
	public Set<HcPersonalnopatologicos> getHcPersonalnopatologicoses() {
		return this.hcPersonalnopatologicoses;
	}

	public void setHcPersonalnopatologicoses(
			Set<HcPersonalnopatologicos> hcPersonalnopatologicoses) {
		this.hcPersonalnopatologicoses = hcPersonalnopatologicoses;
	}

}