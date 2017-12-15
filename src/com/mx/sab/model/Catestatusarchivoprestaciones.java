package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Catestatusarchivoprestaciones entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catestatusarchivoprestaciones", catalog = "my_db_rrg")
public class Catestatusarchivoprestaciones implements java.io.Serializable {

	// Fields

	private Integer id;
	private String descripcion;
	private String siglas;

	// Constructors

	/** default constructor */
	public Catestatusarchivoprestaciones() {
	}

	/** minimal constructor */
	public Catestatusarchivoprestaciones(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Catestatusarchivoprestaciones(Integer id, String descripcion,
			String siglas) {
		this.id = id;
		this.descripcion = descripcion;
		this.siglas = siglas;
	}

	// Property accessors
	@Id
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Descripcion", length = 30)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "Siglas", length = 3)
	public String getSiglas() {
		return this.siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

}