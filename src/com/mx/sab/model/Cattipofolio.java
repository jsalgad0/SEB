package com.mx.sab.model;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cattipofolio entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattipofolio", catalog = "my_db_rrg")
public class Cattipofolio implements java.io.Serializable {

	// Fields

	private Integer id;
	private String descripcion;
	private String siglas;
	private Set<Folio> folios = new HashSet<Folio>(0);

	// Constructors

	/** default constructor */
	public Cattipofolio() {
	}

	/** minimal constructor */
	public Cattipofolio(String descripcion, String siglas) {
		this.descripcion = descripcion;
		this.siglas = siglas;
	}

	/** full constructor */
	public Cattipofolio(String descripcion, String siglas, Set<Folio> folios) {
		this.descripcion = descripcion;
		this.siglas = siglas;
		this.folios = folios;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Descripcion", nullable = false, length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "Siglas", nullable = false, length = 10)
	public String getSiglas() {
		return this.siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cattipofolio")
	public Set<Folio> getFolios() {
		return this.folios;
	}

	public void setFolios(Set<Folio> folios) {
		this.folios = folios;
	}

}