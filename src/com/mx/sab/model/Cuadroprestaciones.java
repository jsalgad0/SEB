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
 * Cuadroprestaciones entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cuadroprestaciones", catalog = "my_db_rrg")
public class Cuadroprestaciones implements java.io.Serializable {

	// Fields

	private Integer cuadroPrestacionId;
	private String cuadroPrestacion;
	private Integer activo;
	private Set<CuadroprestacionPrestacion> cuadroprestacionPrestacions = new HashSet<CuadroprestacionPrestacion>(
			0);
	private Set<ConvenioCuadroprestaciones> convenioCuadroprestacioneses = new HashSet<ConvenioCuadroprestaciones>(
			0);

	// Constructors

	/** default constructor */
	public Cuadroprestaciones() {
	}

	/** minimal constructor */
	public Cuadroprestaciones(String cuadroPrestacion, Integer activo) {
		this.cuadroPrestacion = cuadroPrestacion;
		this.activo = activo;
	}

	/** full constructor */
	public Cuadroprestaciones(String cuadroPrestacion, Integer activo,
			Set<CuadroprestacionPrestacion> cuadroprestacionPrestacions,
			Set<ConvenioCuadroprestaciones> convenioCuadroprestacioneses) {
		this.cuadroPrestacion = cuadroPrestacion;
		this.activo = activo;
		this.cuadroprestacionPrestacions = cuadroprestacionPrestacions;
		this.convenioCuadroprestacioneses = convenioCuadroprestacioneses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CuadroPrestacionId", unique = true, nullable = false)
	public Integer getCuadroPrestacionId() {
		return this.cuadroPrestacionId;
	}

	public void setCuadroPrestacionId(Integer cuadroPrestacionId) {
		this.cuadroPrestacionId = cuadroPrestacionId;
	}

	@Column(name = "CuadroPrestacion", nullable = false, length = 80)
	public String getCuadroPrestacion() {
		return this.cuadroPrestacion;
	}

	public void setCuadroPrestacion(String cuadroPrestacion) {
		this.cuadroPrestacion = cuadroPrestacion;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cuadroprestaciones")
	public Set<CuadroprestacionPrestacion> getCuadroprestacionPrestacions() {
		return this.cuadroprestacionPrestacions;
	}

	public void setCuadroprestacionPrestacions(
			Set<CuadroprestacionPrestacion> cuadroprestacionPrestacions) {
		this.cuadroprestacionPrestacions = cuadroprestacionPrestacions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cuadroprestaciones")
	public Set<ConvenioCuadroprestaciones> getConvenioCuadroprestacioneses() {
		return this.convenioCuadroprestacioneses;
	}

	public void setConvenioCuadroprestacioneses(
			Set<ConvenioCuadroprestaciones> convenioCuadroprestacioneses) {
		this.convenioCuadroprestacioneses = convenioCuadroprestacioneses;
	}

}