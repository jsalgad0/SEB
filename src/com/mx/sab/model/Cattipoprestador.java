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
 * Cattipoprestador entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattipoprestador", catalog = "my_db_rrg")
public class Cattipoprestador implements java.io.Serializable {

	// Fields

	private Integer tipoPrestadorId;
	private String tipoPrestador;
	private Set<Prestadores> prestadoreses = new HashSet<Prestadores>(0);

	// Constructors

	/** default constructor */
	public Cattipoprestador() {
	}

	/** full constructor */
	public Cattipoprestador(String tipoPrestador, Set<Prestadores> prestadoreses) {
		this.tipoPrestador = tipoPrestador;
		this.prestadoreses = prestadoreses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TipoPrestadorId", unique = true, nullable = false)
	public Integer getTipoPrestadorId() {
		return this.tipoPrestadorId;
	}

	public void setTipoPrestadorId(Integer tipoPrestadorId) {
		this.tipoPrestadorId = tipoPrestadorId;
	}

	@Column(name = "TipoPrestador", length = 50)
	public String getTipoPrestador() {
		return this.tipoPrestador;
	}

	public void setTipoPrestador(String tipoPrestador) {
		this.tipoPrestador = tipoPrestador;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cattipoprestador")
	public Set<Prestadores> getPrestadoreses() {
		return this.prestadoreses;
	}

	public void setPrestadoreses(Set<Prestadores> prestadoreses) {
		this.prestadoreses = prestadoreses;
	}

}