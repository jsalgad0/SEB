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
 * Modulos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "modulos", catalog = "my_db_rrg")
public class Modulos implements java.io.Serializable {

	// Fields

	private Integer moduloId;
	private String modulo;
	private Integer activo;
	private Set<Roles> roleses = new HashSet<Roles>(0);

	// Constructors

	/** default constructor */
	public Modulos() {
	}

	/** minimal constructor */
	public Modulos(Integer activo) {
		this.activo = activo;
	}

	/** full constructor */
	public Modulos(String modulo, Integer activo, Set<Roles> roleses) {
		this.modulo = modulo;
		this.activo = activo;
		this.roleses = roleses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ModuloId", unique = true, nullable = false)
	public Integer getModuloId() {
		return this.moduloId;
	}

	public void setModuloId(Integer moduloId) {
		this.moduloId = moduloId;
	}

	@Column(name = "Modulo", length = 45)
	public String getModulo() {
		return this.modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "modulos")
	public Set<Roles> getRoleses() {
		return this.roleses;
	}

	public void setRoleses(Set<Roles> roleses) {
		this.roleses = roleses;
	}

}