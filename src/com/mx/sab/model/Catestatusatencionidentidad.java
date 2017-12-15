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
 * Catestatusatencionidentidad entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catestatusatencionidentidad", catalog = "my_db_rrg")
public class Catestatusatencionidentidad implements java.io.Serializable {

	// Fields

	private Integer id;
	private String descripcion;
	private String siglas;
	private Set<Atencionmedica> atencionmedicas = new HashSet<Atencionmedica>(0);

	// Constructors

	/** default constructor */
	public Catestatusatencionidentidad() {
	}

	/** minimal constructor */
	public Catestatusatencionidentidad(String descripcion, String siglas) {
		this.descripcion = descripcion;
		this.siglas = siglas;
	}

	/** full constructor */
	public Catestatusatencionidentidad(String descripcion, String siglas,
			Set<Atencionmedica> atencionmedicas) {
		this.descripcion = descripcion;
		this.siglas = siglas;
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

	@Column(name = "Descripcion", nullable = false, length = 40)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "Siglas", nullable = false, length = 4)
	public String getSiglas() {
		return this.siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestatusatencionidentidad")
	public Set<Atencionmedica> getAtencionmedicas() {
		return this.atencionmedicas;
	}

	public void setAtencionmedicas(Set<Atencionmedica> atencionmedicas) {
		this.atencionmedicas = atencionmedicas;
	}

}