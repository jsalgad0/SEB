package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cattiposervicio entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattiposervicio", catalog = "my_db_rrg")
public class Cattiposervicio implements java.io.Serializable {

	// Fields

	private Integer servicioId;
	private String descripcion;

	// Constructors

	/** default constructor */
	public Cattiposervicio() {
	}

	/** full constructor */
	public Cattiposervicio(String descripcion) {
		this.descripcion = descripcion;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ServicioId", unique = true, nullable = false)
	public Integer getServicioId() {
		return this.servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	@Column(name = "Descripcion", length = 50)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}