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
 * Catestatusrecetas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catestatusrecetas", catalog = "my_db_rrg")
public class Catestatusrecetas implements java.io.Serializable {

	// Fields

	private Integer estatusRecetasId;
	private String estatusReceta;
	private Set<Recetas> recetases = new HashSet<Recetas>(0);

	// Constructors

	/** default constructor */
	public Catestatusrecetas() {
	}

	/** minimal constructor */
	public Catestatusrecetas(String estatusReceta) {
		this.estatusReceta = estatusReceta;
	}

	/** full constructor */
	public Catestatusrecetas(String estatusReceta, Set<Recetas> recetases) {
		this.estatusReceta = estatusReceta;
		this.recetases = recetases;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "EstatusRecetasId", unique = true, nullable = false)
	public Integer getEstatusRecetasId() {
		return this.estatusRecetasId;
	}

	public void setEstatusRecetasId(Integer estatusRecetasId) {
		this.estatusRecetasId = estatusRecetasId;
	}

	@Column(name = "EstatusReceta", nullable = false, length = 50)
	public String getEstatusReceta() {
		return this.estatusReceta;
	}

	public void setEstatusReceta(String estatusReceta) {
		this.estatusReceta = estatusReceta;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestatusrecetas")
	public Set<Recetas> getRecetases() {
		return this.recetases;
	}

	public void setRecetases(Set<Recetas> recetases) {
		this.recetases = recetases;
	}

}