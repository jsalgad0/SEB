package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Catdependenciatrabajo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catdependenciatrabajo", catalog = "my_db_rrg")
public class Catdependenciatrabajo implements java.io.Serializable {

	// Fields

	private Integer dependenciaId;
	private String dependencia;

	// Constructors

	/** default constructor */
	public Catdependenciatrabajo() {
	}

	/** full constructor */
	public Catdependenciatrabajo(String dependencia) {
		this.dependencia = dependencia;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DependenciaId", unique = true, nullable = false)
	public Integer getDependenciaId() {
		return this.dependenciaId;
	}

	public void setDependenciaId(Integer dependenciaId) {
		this.dependenciaId = dependenciaId;
	}

	@Column(name = "Dependencia", length = 100)
	public String getDependencia() {
		return this.dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

}