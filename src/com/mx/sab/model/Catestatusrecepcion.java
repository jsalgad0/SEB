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
 * Catestatusrecepcion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catestatusrecepcion", catalog = "my_db_rrg")
public class Catestatusrecepcion implements java.io.Serializable {

	// Fields

	private Integer id;
	private String descripcion;
	private Set<Atencionmedica> atencionmedicas = new HashSet<Atencionmedica>(0);

	// Constructors

	/** default constructor */
	public Catestatusrecepcion() {
	}

	/** minimal constructor */
	public Catestatusrecepcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/** full constructor */
	public Catestatusrecepcion(String descripcion,
			Set<Atencionmedica> atencionmedicas) {
		this.descripcion = descripcion;
		this.atencionmedicas = atencionmedicas;
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

	@Column(name = "Descripcion", nullable = false, length = 25)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestatusrecepcion")
	public Set<Atencionmedica> getAtencionmedicas() {
		return this.atencionmedicas;
	}

	public void setAtencionmedicas(Set<Atencionmedica> atencionmedicas) {
		this.atencionmedicas = atencionmedicas;
	}

}