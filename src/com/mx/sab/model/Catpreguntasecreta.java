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
 * Catpreguntasecreta entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catpreguntasecreta", catalog = "my_db_rrg")
public class Catpreguntasecreta implements java.io.Serializable {

	// Fields

	private Integer id;
	private String descripcion;
	private Set<Usuarios> usuarioses = new HashSet<Usuarios>(0);

	// Constructors

	/** default constructor */
	public Catpreguntasecreta() {
	}

	/** minimal constructor */
	public Catpreguntasecreta(Integer id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	/** full constructor */
	public Catpreguntasecreta(Integer id, String descripcion,
			Set<Usuarios> usuarioses) {
		this.id = id;
		this.descripcion = descripcion;
		this.usuarioses = usuarioses;
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

	@Column(name = "Descripcion", nullable = false, length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catpreguntasecreta")
	public Set<Usuarios> getUsuarioses() {
		return this.usuarioses;
	}

	public void setUsuarioses(Set<Usuarios> usuarioses) {
		this.usuarioses = usuarioses;
	}

}