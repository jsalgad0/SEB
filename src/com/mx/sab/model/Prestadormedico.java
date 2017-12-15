package com.mx.sab.model;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Prestadormedico entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "prestadormedico", catalog = "my_db_rrg")
public class Prestadormedico implements java.io.Serializable {

	// Fields

	private Integer prestadorId;
	private Prestadores prestadores;
	private Instituciontitulo instituciontitulo;
	private String cedula;
	private String clave;
	private Set<Medicoespecialidadmedica> medicoespecialidadmedicas = new HashSet<Medicoespecialidadmedica>(
			0);

	// Constructors

	/** default constructor */
	public Prestadormedico() {
	}

	/** minimal constructor */
	public Prestadormedico(Integer prestadorId, Prestadores prestadores) {
		this.prestadorId = prestadorId;
		this.prestadores = prestadores;
	}

	/** full constructor */
	public Prestadormedico(Integer prestadorId, Prestadores prestadores,
			Instituciontitulo instituciontitulo, String cedula, String clave,
			Set<Medicoespecialidadmedica> medicoespecialidadmedicas) {
		this.prestadorId = prestadorId;
		this.prestadores = prestadores;
		this.instituciontitulo = instituciontitulo;
		this.cedula = cedula;
		this.clave = clave;
		this.medicoespecialidadmedicas = medicoespecialidadmedicas;
	}

	// Property accessors
	@Id
	@Column(name = "PrestadorId", unique = true, nullable = false)
	public Integer getPrestadorId() {
		return this.prestadorId;
	}

	public void setPrestadorId(Integer prestadorId) {
		this.prestadorId = prestadorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PrestadorId", unique = true, nullable = false, insertable = false, updatable = false)
	public Prestadores getPrestadores() {
		return this.prestadores;
	}

	public void setPrestadores(Prestadores prestadores) {
		this.prestadores = prestadores;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "InstitucionTitulo")
	public Instituciontitulo getInstituciontitulo() {
		return this.instituciontitulo;
	}

	public void setInstituciontitulo(Instituciontitulo instituciontitulo) {
		this.instituciontitulo = instituciontitulo;
	}

	@Column(name = "Cedula", length = 15)
	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	@Column(name = "Clave", length = 15)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prestadormedico")
	public Set<Medicoespecialidadmedica> getMedicoespecialidadmedicas() {
		return this.medicoespecialidadmedicas;
	}

	public void setMedicoespecialidadmedicas(
			Set<Medicoespecialidadmedica> medicoespecialidadmedicas) {
		this.medicoespecialidadmedicas = medicoespecialidadmedicas;
	}

}