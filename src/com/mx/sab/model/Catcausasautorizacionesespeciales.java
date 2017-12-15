package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Catcausasautorizacionesespeciales entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catcausasautorizacionesespeciales", catalog = "my_db_rrg")
public class Catcausasautorizacionesespeciales implements java.io.Serializable {

	// Fields

	private Integer causaId;
	private String causa;
	private Integer activo;

	// Constructors

	/** default constructor */
	public Catcausasautorizacionesespeciales() {
	}

	/** full constructor */
	public Catcausasautorizacionesespeciales(String causa, Integer activo) {
		this.causa = causa;
		this.activo = activo;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CausaId", unique = true, nullable = false)
	public Integer getCausaId() {
		return this.causaId;
	}

	public void setCausaId(Integer causaId) {
		this.causaId = causaId;
	}

	@Column(name = "Causa", nullable = false, length = 150)
	public String getCausa() {
		return this.causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

}