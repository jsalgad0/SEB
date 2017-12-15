package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Caterroresarchivoprestaciones entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "caterroresarchivoprestaciones", catalog = "my_db_rrg")
public class Caterroresarchivoprestaciones implements java.io.Serializable {

	// Fields

	private Integer id;
	private String descripcion;

	// Constructors

	/** default constructor */
	public Caterroresarchivoprestaciones() {
	}

	/** minimal constructor */
	public Caterroresarchivoprestaciones(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Caterroresarchivoprestaciones(Integer id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
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

	@Column(name = "Descripcion", length = 60)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}