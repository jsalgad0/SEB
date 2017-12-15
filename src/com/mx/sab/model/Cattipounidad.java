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
 * Cattipounidad entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattipounidad", catalog = "my_db_rrg")
public class Cattipounidad implements java.io.Serializable {

	// Fields

	private Integer tiposUnidadId;
	private Catnivel catnivel;
	private String unidad;
	private Set<Lugaresdeatencion> lugaresdeatencions = new HashSet<Lugaresdeatencion>(
			0);

	// Constructors

	/** default constructor */
	public Cattipounidad() {
	}

	/** minimal constructor */
	public Cattipounidad(String unidad) {
		this.unidad = unidad;
	}

	/** full constructor */
	public Cattipounidad(Catnivel catnivel, String unidad,
			Set<Lugaresdeatencion> lugaresdeatencions) {
		this.catnivel = catnivel;
		this.unidad = unidad;
		this.lugaresdeatencions = lugaresdeatencions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TiposUnidadId", unique = true, nullable = false)
	public Integer getTiposUnidadId() {
		return this.tiposUnidadId;
	}

	public void setTiposUnidadId(Integer tiposUnidadId) {
		this.tiposUnidadId = tiposUnidadId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NivelId")
	public Catnivel getCatnivel() {
		return this.catnivel;
	}

	public void setCatnivel(Catnivel catnivel) {
		this.catnivel = catnivel;
	}

	@Column(name = "Unidad", nullable = false, length = 100)
	public String getUnidad() {
		return this.unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cattipounidad")
	public Set<Lugaresdeatencion> getLugaresdeatencions() {
		return this.lugaresdeatencions;
	}

	public void setLugaresdeatencions(Set<Lugaresdeatencion> lugaresdeatencions) {
		this.lugaresdeatencions = lugaresdeatencions;
	}

}