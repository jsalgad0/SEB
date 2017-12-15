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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Catsignosvitalesadicionales entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catsignosvitalesadicionales", catalog = "my_db_rrg")
public class Catsignosvitalesadicionales implements java.io.Serializable {

	// Fields

	private Integer catSignosVitalesAdicionalesId;
	private Aseguradores aseguradores;
	private String descripcion;
	private Set<Signosvitalesadicionales> signosvitalesadicionaleses = new HashSet<Signosvitalesadicionales>(
			0);

	// Constructors

	/** default constructor */
	public Catsignosvitalesadicionales() {
	}

	/** minimal constructor */
	public Catsignosvitalesadicionales(String descripcion) {
		this.descripcion = descripcion;
	}

	/** full constructor */
	public Catsignosvitalesadicionales(Aseguradores aseguradores,
			String descripcion,
			Set<Signosvitalesadicionales> signosvitalesadicionaleses) {
		this.aseguradores = aseguradores;
		this.descripcion = descripcion;
		this.signosvitalesadicionaleses = signosvitalesadicionaleses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CatSignosVitalesAdicionalesId", unique = true, nullable = false)
	public Integer getCatSignosVitalesAdicionalesId() {
		return this.catSignosVitalesAdicionalesId;
	}

	public void setCatSignosVitalesAdicionalesId(
			Integer catSignosVitalesAdicionalesId) {
		this.catSignosVitalesAdicionalesId = catSignosVitalesAdicionalesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AseguradorId")
	public Aseguradores getAseguradores() {
		return this.aseguradores;
	}

	public void setAseguradores(Aseguradores aseguradores) {
		this.aseguradores = aseguradores;
	}

	@Column(name = "Descripcion", nullable = false, length = 10)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catsignosvitalesadicionales")
	public Set<Signosvitalesadicionales> getSignosvitalesadicionaleses() {
		return this.signosvitalesadicionaleses;
	}

	public void setSignosvitalesadicionaleses(
			Set<Signosvitalesadicionales> signosvitalesadicionaleses) {
		this.signosvitalesadicionaleses = signosvitalesadicionaleses;
	}

}