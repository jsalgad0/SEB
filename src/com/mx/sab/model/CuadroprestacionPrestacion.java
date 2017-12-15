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
 * CuadroprestacionPrestacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cuadroprestacion_prestacion", catalog = "my_db_rrg")
public class CuadroprestacionPrestacion implements java.io.Serializable {

	// Fields

	private CuadroprestacionPrestacionId id;
	private Cuadroprestaciones cuadroprestaciones;
	private Catprestacion catprestacion;
	private Double valorPrestacion;
	private Double honorariosAnestesista;
	private Double honorariosMedico;
	private Integer activo;

	// Constructors

	/** default constructor */
	public CuadroprestacionPrestacion() {
	}

	/** minimal constructor */
	public CuadroprestacionPrestacion(CuadroprestacionPrestacionId id,
			Cuadroprestaciones cuadroprestaciones, Catprestacion catprestacion,
			Integer activo) {
		this.id = id;
		this.cuadroprestaciones = cuadroprestaciones;
		this.catprestacion = catprestacion;
		this.activo = activo;
	}

	/** full constructor */
	public CuadroprestacionPrestacion(CuadroprestacionPrestacionId id,
			Cuadroprestaciones cuadroprestaciones, Catprestacion catprestacion,
			Double valorPrestacion, Double honorariosAnestesista,
			Double honorariosMedico, Integer activo) {
		this.id = id;
		this.cuadroprestaciones = cuadroprestaciones;
		this.catprestacion = catprestacion;
		this.valorPrestacion = valorPrestacion;
		this.honorariosAnestesista = honorariosAnestesista;
		this.honorariosMedico = honorariosMedico;
		this.activo = activo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "cuadroPrestacionId", column = @Column(name = "CuadroPrestacionId", nullable = false)),
			@AttributeOverride(name = "prestacionId", column = @Column(name = "PrestacionId", nullable = false)) })
	public CuadroprestacionPrestacionId getId() {
		return this.id;
	}

	public void setId(CuadroprestacionPrestacionId id) {
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
	@JoinColumn(name = "PrestacionId", nullable = false, insertable = false, updatable = false)
	public Catprestacion getCatprestacion() {
		return this.catprestacion;
	}

	public void setCatprestacion(Catprestacion catprestacion) {
		this.catprestacion = catprestacion;
	}

	@Column(name = "ValorPrestacion", precision = 15)
	public Double getValorPrestacion() {
		return this.valorPrestacion;
	}

	public void setValorPrestacion(Double valorPrestacion) {
		this.valorPrestacion = valorPrestacion;
	}

	@Column(name = "HonorariosAnestesista", precision = 15)
	public Double getHonorariosAnestesista() {
		return this.honorariosAnestesista;
	}

	public void setHonorariosAnestesista(Double honorariosAnestesista) {
		this.honorariosAnestesista = honorariosAnestesista;
	}

	@Column(name = "HonorariosMedico", precision = 15)
	public Double getHonorariosMedico() {
		return this.honorariosMedico;
	}

	public void setHonorariosMedico(Double honorariosMedico) {
		this.honorariosMedico = honorariosMedico;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

}