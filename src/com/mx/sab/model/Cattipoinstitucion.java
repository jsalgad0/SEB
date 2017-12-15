package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cattipoinstitucion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattipoinstitucion", catalog = "my_db_rrg")
public class Cattipoinstitucion implements java.io.Serializable {

	// Fields

	private Integer idTipoInstitucion;
	private String nombre;

	// Constructors

	/** default constructor */
	public Cattipoinstitucion() {
	}

	/** minimal constructor */
	public Cattipoinstitucion(Integer idTipoInstitucion) {
		this.idTipoInstitucion = idTipoInstitucion;
	}

	/** full constructor */
	public Cattipoinstitucion(Integer idTipoInstitucion, String nombre) {
		this.idTipoInstitucion = idTipoInstitucion;
		this.nombre = nombre;
	}

	// Property accessors
	@Id
	@Column(name = "idTipoInstitucion", unique = true, nullable = false)
	public Integer getIdTipoInstitucion() {
		return this.idTipoInstitucion;
	}

	public void setIdTipoInstitucion(Integer idTipoInstitucion) {
		this.idTipoInstitucion = idTipoInstitucion;
	}

	@Column(name = "nombre", length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}