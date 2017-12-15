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
 * Cattipodiagnostico entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattipodiagnostico", catalog = "my_db_rrg")
public class Cattipodiagnostico implements java.io.Serializable {

	// Fields

	private Integer id;
	private String descripcion;
	private Set<NotamedicaCies10> notamedicaCies10s = new HashSet<NotamedicaCies10>(
			0);

	// Constructors

	/** default constructor */
	public Cattipodiagnostico() {
	}

	/** minimal constructor */
	public Cattipodiagnostico(String descripcion) {
		this.descripcion = descripcion;
	}

	/** full constructor */
	public Cattipodiagnostico(String descripcion,
			Set<NotamedicaCies10> notamedicaCies10s) {
		this.descripcion = descripcion;
		this.notamedicaCies10s = notamedicaCies10s;
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

	@Column(name = "Descripcion", nullable = false, length = 20)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cattipodiagnostico")
	public Set<NotamedicaCies10> getNotamedicaCies10s() {
		return this.notamedicaCies10s;
	}

	public void setNotamedicaCies10s(Set<NotamedicaCies10> notamedicaCies10s) {
		this.notamedicaCies10s = notamedicaCies10s;
	}

}