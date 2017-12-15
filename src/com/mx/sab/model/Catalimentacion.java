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
 * Catalimentacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catalimentacion", catalog = "my_db_rrg")
public class Catalimentacion implements java.io.Serializable {

	// Fields

	private Integer alimentacionId;
	private String descripcion;
	private Set<HcPersonalnopatologicos> hcPersonalnopatologicoses = new HashSet<HcPersonalnopatologicos>(
			0);

	// Constructors

	/** default constructor */
	public Catalimentacion() {
	}

	/** minimal constructor */
	public Catalimentacion(Integer alimentacionId) {
		this.alimentacionId = alimentacionId;
	}

	/** full constructor */
	public Catalimentacion(Integer alimentacionId, String descripcion,
			Set<HcPersonalnopatologicos> hcPersonalnopatologicoses) {
		this.alimentacionId = alimentacionId;
		this.descripcion = descripcion;
		this.hcPersonalnopatologicoses = hcPersonalnopatologicoses;
	}

	// Property accessors
	@Id
	@Column(name = "AlimentacionId", unique = true, nullable = false)
	public Integer getAlimentacionId() {
		return this.alimentacionId;
	}

	public void setAlimentacionId(Integer alimentacionId) {
		this.alimentacionId = alimentacionId;
	}

	@Column(name = "Descripcion", length = 20)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catalimentacion")
	public Set<HcPersonalnopatologicos> getHcPersonalnopatologicoses() {
		return this.hcPersonalnopatologicoses;
	}

	public void setHcPersonalnopatologicoses(
			Set<HcPersonalnopatologicos> hcPersonalnopatologicoses) {
		this.hcPersonalnopatologicoses = hcPersonalnopatologicoses;
	}

}