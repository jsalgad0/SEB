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
 * Cattipospersonas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattipospersonas", catalog = "my_db_rrg")
public class Cattipospersonas implements java.io.Serializable {

	// Fields

	private Integer tipoPersonaId;
	private String tipoPersona;
	private Set<Prestadores> prestadoreses = new HashSet<Prestadores>(0);

	// Constructors

	/** default constructor */
	public Cattipospersonas() {
	}

	/** full constructor */
	public Cattipospersonas(String tipoPersona, Set<Prestadores> prestadoreses) {
		this.tipoPersona = tipoPersona;
		this.prestadoreses = prestadoreses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TipoPersonaId", unique = true, nullable = false)
	public Integer getTipoPersonaId() {
		return this.tipoPersonaId;
	}

	public void setTipoPersonaId(Integer tipoPersonaId) {
		this.tipoPersonaId = tipoPersonaId;
	}

	@Column(name = "TipoPersona", length = 15)
	public String getTipoPersona() {
		return this.tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cattipospersonas")
	public Set<Prestadores> getPrestadoreses() {
		return this.prestadoreses;
	}

	public void setPrestadoreses(Set<Prestadores> prestadoreses) {
		this.prestadoreses = prestadoreses;
	}

}