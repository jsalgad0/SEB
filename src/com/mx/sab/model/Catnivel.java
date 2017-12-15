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
 * Catnivel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catnivel", catalog = "my_db_rrg")
public class Catnivel implements java.io.Serializable {

	// Fields

	private Integer nivelId;
	private String nivel;
	private Set<Cattipounidad> cattipounidads = new HashSet<Cattipounidad>(0);

	// Constructors

	/** default constructor */
	public Catnivel() {
	}

	/** minimal constructor */
	public Catnivel(String nivel) {
		this.nivel = nivel;
	}

	/** full constructor */
	public Catnivel(String nivel, Set<Cattipounidad> cattipounidads) {
		this.nivel = nivel;
		this.cattipounidads = cattipounidads;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "NivelId", unique = true, nullable = false)
	public Integer getNivelId() {
		return this.nivelId;
	}

	public void setNivelId(Integer nivelId) {
		this.nivelId = nivelId;
	}

	@Column(name = "Nivel", nullable = false, length = 100)
	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catnivel")
	public Set<Cattipounidad> getCattipounidads() {
		return this.cattipounidads;
	}

	public void setCattipounidads(Set<Cattipounidad> cattipounidads) {
		this.cattipounidads = cattipounidads;
	}

}