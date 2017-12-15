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
 * Catestatusfirma entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catestatusfirma", catalog = "my_db_rrg")
public class Catestatusfirma implements java.io.Serializable {

	// Fields

	private Integer id;
	private String descripcion;
	private Set<Atencionmedica> atencionmedicasForEstatusFirmaMedico = new HashSet<Atencionmedica>(
			0);
	private Set<Atencionmedica> atencionmedicasForEstatusFirmaPaciente = new HashSet<Atencionmedica>(
			0);

	// Constructors

	/** default constructor */
	public Catestatusfirma() {
	}

	/** minimal constructor */
	public Catestatusfirma(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Catestatusfirma(Integer id, String descripcion,
			Set<Atencionmedica> atencionmedicasForEstatusFirmaMedico,
			Set<Atencionmedica> atencionmedicasForEstatusFirmaPaciente) {
		this.id = id;
		this.descripcion = descripcion;
		this.atencionmedicasForEstatusFirmaMedico = atencionmedicasForEstatusFirmaMedico;
		this.atencionmedicasForEstatusFirmaPaciente = atencionmedicasForEstatusFirmaPaciente;
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

	@Column(name = "Descripcion", length = 50)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestatusfirmaByEstatusFirmaMedico")
	public Set<Atencionmedica> getAtencionmedicasForEstatusFirmaMedico() {
		return this.atencionmedicasForEstatusFirmaMedico;
	}

	public void setAtencionmedicasForEstatusFirmaMedico(
			Set<Atencionmedica> atencionmedicasForEstatusFirmaMedico) {
		this.atencionmedicasForEstatusFirmaMedico = atencionmedicasForEstatusFirmaMedico;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestatusfirmaByEstatusFirmaPaciente")
	public Set<Atencionmedica> getAtencionmedicasForEstatusFirmaPaciente() {
		return this.atencionmedicasForEstatusFirmaPaciente;
	}

	public void setAtencionmedicasForEstatusFirmaPaciente(
			Set<Atencionmedica> atencionmedicasForEstatusFirmaPaciente) {
		this.atencionmedicasForEstatusFirmaPaciente = atencionmedicasForEstatusFirmaPaciente;
	}

}