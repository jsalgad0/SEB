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
 * Catsolirefrerenciapor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catsolirefrerenciapor", catalog = "my_db_rrg")
public class Catsolirefrerenciapor implements java.io.Serializable {

	// Fields

	private Integer referenciaPorId;
	private String descripcion;
	private Integer activo;
	private Set<Solicitudreferencia> solicitudreferencias = new HashSet<Solicitudreferencia>(
			0);

	// Constructors

	/** default constructor */
	public Catsolirefrerenciapor() {
	}

	/** minimal constructor */
	public Catsolirefrerenciapor(String descripcion, Integer activo) {
		this.descripcion = descripcion;
		this.activo = activo;
	}

	/** full constructor */
	public Catsolirefrerenciapor(String descripcion, Integer activo,
			Set<Solicitudreferencia> solicitudreferencias) {
		this.descripcion = descripcion;
		this.activo = activo;
		this.solicitudreferencias = solicitudreferencias;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ReferenciaPorId", unique = true, nullable = false)
	public Integer getReferenciaPorId() {
		return this.referenciaPorId;
	}

	public void setReferenciaPorId(Integer referenciaPorId) {
		this.referenciaPorId = referenciaPorId;
	}

	@Column(name = "Descripcion", nullable = false, length = 150)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catsolirefrerenciapor")
	public Set<Solicitudreferencia> getSolicitudreferencias() {
		return this.solicitudreferencias;
	}

	public void setSolicitudreferencias(
			Set<Solicitudreferencia> solicitudreferencias) {
		this.solicitudreferencias = solicitudreferencias;
	}

}