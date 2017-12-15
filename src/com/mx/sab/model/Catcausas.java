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
 * Catcausas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catcausas", catalog = "my_db_rrg")
public class Catcausas implements java.io.Serializable {

	// Fields

	private Integer causaId;
	private String causa;
	private Integer activo;
	private Set<Permisoespecial> permisoespecials = new HashSet<Permisoespecial>(
			0);

	// Constructors

	/** default constructor */
	public Catcausas() {
	}

	/** minimal constructor */
	public Catcausas(String causa, Integer activo) {
		this.causa = causa;
		this.activo = activo;
	}

	/** full constructor */
	public Catcausas(String causa, Integer activo,
			Set<Permisoespecial> permisoespecials) {
		this.causa = causa;
		this.activo = activo;
		this.permisoespecials = permisoespecials;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CausaId", unique = true, nullable = false)
	public Integer getCausaId() {
		return this.causaId;
	}

	public void setCausaId(Integer causaId) {
		this.causaId = causaId;
	}

	@Column(name = "Causa", nullable = false, length = 150)
	public String getCausa() {
		return this.causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catcausas")
	public Set<Permisoespecial> getPermisoespecials() {
		return this.permisoespecials;
	}

	public void setPermisoespecials(Set<Permisoespecial> permisoespecials) {
		this.permisoespecials = permisoespecials;
	}

}