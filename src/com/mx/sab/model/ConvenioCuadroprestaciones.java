package com.mx.sab.model;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ConvenioCuadroprestaciones entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "convenio_cuadroprestaciones", catalog = "my_db_rrg")
public class ConvenioCuadroprestaciones implements java.io.Serializable {

	// Fields

	private ConvenioCuadroprestacionesId id;
	private Cuadroprestaciones cuadroprestaciones;
	private Convenios convenios;

	// Constructors

	/** default constructor */
	public ConvenioCuadroprestaciones() {
	}

	/** full constructor */
	public ConvenioCuadroprestaciones(ConvenioCuadroprestacionesId id,
			Cuadroprestaciones cuadroprestaciones, Convenios convenios) {
		this.id = id;
		this.cuadroprestaciones = cuadroprestaciones;
		this.convenios = convenios;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "convenioId", column = @Column(name = "ConvenioId", nullable = false)),
			@AttributeOverride(name = "cuadroPrestacionId", column = @Column(name = "CuadroPrestacionId", nullable = false)),
			@AttributeOverride(name = "vigenteDesde", column = @Column(name = "VigenteDesde", nullable = false, length = 10)),
			@AttributeOverride(name = "vigenteHasta", column = @Column(name = "VigenteHasta", nullable = false, length = 10)) })
	public ConvenioCuadroprestacionesId getId() {
		return this.id;
	}

	public void setId(ConvenioCuadroprestacionesId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CuadroPrestacionId", nullable = false, insertable = false, updatable = false)
	public Cuadroprestaciones getCuadroprestaciones() {
		return this.cuadroprestaciones;
	}

	public void setCuadroprestaciones(Cuadroprestaciones cuadroprestaciones) {
		this.cuadroprestaciones = cuadroprestaciones;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ConvenioId", nullable = false, insertable = false, updatable = false)
	public Convenios getConvenios() {
		return this.convenios;
	}

	public void setConvenios(Convenios convenios) {
		this.convenios = convenios;
	}

}