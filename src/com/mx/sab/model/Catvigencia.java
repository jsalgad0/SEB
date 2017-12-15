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
 * Catvigencia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catvigencia", catalog = "my_db_rrg")
public class Catvigencia implements java.io.Serializable {

	// Fields

	private Integer vigenciaId;
	private String descripcion;
	private Set<Usuariovigencialugaratencion> usuariovigencialugaratencions = new HashSet<Usuariovigencialugaratencion>(
			0);

	// Constructors

	/** default constructor */
	public Catvigencia() {
	}

	/** minimal constructor */
	public Catvigencia(Integer vigenciaId, String descripcion) {
		this.vigenciaId = vigenciaId;
		this.descripcion = descripcion;
	}

	/** full constructor */
	public Catvigencia(Integer vigenciaId, String descripcion,
			Set<Usuariovigencialugaratencion> usuariovigencialugaratencions) {
		this.vigenciaId = vigenciaId;
		this.descripcion = descripcion;
		this.usuariovigencialugaratencions = usuariovigencialugaratencions;
	}

	// Property accessors
	@Id
	@Column(name = "VigenciaId", unique = true, nullable = false)
	public Integer getVigenciaId() {
		return this.vigenciaId;
	}

	public void setVigenciaId(Integer vigenciaId) {
		this.vigenciaId = vigenciaId;
	}

	@Column(name = "Descripcion", nullable = false, length = 50)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catvigencia")
	public Set<Usuariovigencialugaratencion> getUsuariovigencialugaratencions() {
		return this.usuariovigencialugaratencions;
	}

	public void setUsuariovigencialugaratencions(
			Set<Usuariovigencialugaratencion> usuariovigencialugaratencions) {
		this.usuariovigencialugaratencions = usuariovigencialugaratencions;
	}

}