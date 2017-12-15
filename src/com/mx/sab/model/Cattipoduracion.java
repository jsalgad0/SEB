package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cattipoduracion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattipoduracion", catalog = "my_db_rrg")
public class Cattipoduracion implements java.io.Serializable {

	// Fields

	private Integer tipoDuracionId;
	private String descripcion;

	// Constructors

	/** default constructor */
	public Cattipoduracion() {
	}

	/** full constructor */
	public Cattipoduracion(String descripcion) {
		this.descripcion = descripcion;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TipoDuracionId", unique = true, nullable = false)
	public Integer getTipoDuracionId() {
		return this.tipoDuracionId;
	}

	public void setTipoDuracionId(Integer tipoDuracionId) {
		this.tipoDuracionId = tipoDuracionId;
	}

	@Column(name = "Descripcion", length = 25)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}