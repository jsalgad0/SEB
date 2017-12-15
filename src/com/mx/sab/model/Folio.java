package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Folio entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "folio", catalog = "my_db_rrg")
public class Folio implements java.io.Serializable {

	// Fields

	private Integer id;
	private Lugaresdeatencion lugaresdeatencion;
	private Cattipofolio cattipofolio;
	private Integer consecutivo;

	// Constructors

	/** default constructor */
	public Folio() {
	}

	/** full constructor */
	public Folio(Lugaresdeatencion lugaresdeatencion,
			Cattipofolio cattipofolio, Integer consecutivo) {
		this.lugaresdeatencion = lugaresdeatencion;
		this.cattipofolio = cattipofolio;
		this.consecutivo = consecutivo;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LugarAtencionId", nullable = false)
	public Lugaresdeatencion getLugaresdeatencion() {
		return this.lugaresdeatencion;
	}

	public void setLugaresdeatencion(Lugaresdeatencion lugaresdeatencion) {
		this.lugaresdeatencion = lugaresdeatencion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoFolioId", nullable = false)
	public Cattipofolio getCattipofolio() {
		return this.cattipofolio;
	}

	public void setCattipofolio(Cattipofolio cattipofolio) {
		this.cattipofolio = cattipofolio;
	}

	@Column(name = "Consecutivo", nullable = false)
	public Integer getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}

}