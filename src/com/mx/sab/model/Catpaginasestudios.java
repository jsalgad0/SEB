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
 * Catpaginasestudios entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catpaginasestudios", catalog = "my_db_rrg")
public class Catpaginasestudios implements java.io.Serializable {

	// Fields

	private Integer idPagina;
	private String descripcion;
	private Set<Prestacionesportomar> prestacionesportomars = new HashSet<Prestacionesportomar>(
			0);

	// Constructors

	/** default constructor */
	public Catpaginasestudios() {
	}

	/** minimal constructor */
	public Catpaginasestudios(String descripcion) {
		this.descripcion = descripcion;
	}

	/** full constructor */
	public Catpaginasestudios(String descripcion,
			Set<Prestacionesportomar> prestacionesportomars) {
		this.descripcion = descripcion;
		this.prestacionesportomars = prestacionesportomars;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdPagina", unique = true, nullable = false)
	public Integer getIdPagina() {
		return this.idPagina;
	}

	public void setIdPagina(Integer idPagina) {
		this.idPagina = idPagina;
	}

	@Column(name = "Descripcion", nullable = false, length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catpaginasestudios")
	public Set<Prestacionesportomar> getPrestacionesportomars() {
		return this.prestacionesportomars;
	}

	public void setPrestacionesportomars(
			Set<Prestacionesportomar> prestacionesportomars) {
		this.prestacionesportomars = prestacionesportomars;
	}

}