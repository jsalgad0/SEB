package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cattipoauditoria entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattipoauditoria", catalog = "my_db_rrg")
public class Cattipoauditoria implements java.io.Serializable {

	// Fields

	private Integer tipoAuditoriaId;
	private String descripcion;

	// Constructors

	/** default constructor */
	public Cattipoauditoria() {
	}

	/** full constructor */
	public Cattipoauditoria(String descripcion) {
		this.descripcion = descripcion;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TipoAuditoriaId", unique = true, nullable = false)
	public Integer getTipoAuditoriaId() {
		return this.tipoAuditoriaId;
	}

	public void setTipoAuditoriaId(Integer tipoAuditoriaId) {
		this.tipoAuditoriaId = tipoAuditoriaId;
	}

	@Column(name = "Descripcion", length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}