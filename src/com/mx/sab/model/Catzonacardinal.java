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
 * Catzonacardinal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catzonacardinal", catalog = "my_db_rrg")
public class Catzonacardinal implements java.io.Serializable {

	// Fields

	private Integer zonaCardinalId;
	private String zonaCardinal;
	private Set<Lugaresdeatencion> lugaresdeatencions = new HashSet<Lugaresdeatencion>(
			0);

	// Constructors

	/** default constructor */
	public Catzonacardinal() {
	}

	/** minimal constructor */
	public Catzonacardinal(String zonaCardinal) {
		this.zonaCardinal = zonaCardinal;
	}

	/** full constructor */
	public Catzonacardinal(String zonaCardinal,
			Set<Lugaresdeatencion> lugaresdeatencions) {
		this.zonaCardinal = zonaCardinal;
		this.lugaresdeatencions = lugaresdeatencions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ZonaCardinalId", unique = true, nullable = false)
	public Integer getZonaCardinalId() {
		return this.zonaCardinalId;
	}

	public void setZonaCardinalId(Integer zonaCardinalId) {
		this.zonaCardinalId = zonaCardinalId;
	}

	@Column(name = "ZonaCardinal", nullable = false, length = 10)
	public String getZonaCardinal() {
		return this.zonaCardinal;
	}

	public void setZonaCardinal(String zonaCardinal) {
		this.zonaCardinal = zonaCardinal;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catzonacardinal")
	public Set<Lugaresdeatencion> getLugaresdeatencions() {
		return this.lugaresdeatencions;
	}

	public void setLugaresdeatencions(Set<Lugaresdeatencion> lugaresdeatencions) {
		this.lugaresdeatencions = lugaresdeatencions;
	}

}