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
 * Detallebitacoracargaprestaciones entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "detallebitacoracargaprestaciones", catalog = "my_db_rrg")
public class Detallebitacoracargaprestaciones implements java.io.Serializable {

	// Fields

	private Integer id;
	private Bitacoracargaprestacion bitacoracargaprestacion;
	private String numFila;

	// Constructors

	/** default constructor */
	public Detallebitacoracargaprestaciones() {
	}

	/** full constructor */
	public Detallebitacoracargaprestaciones(
			Bitacoracargaprestacion bitacoracargaprestacion, String numFila) {
		this.bitacoracargaprestacion = bitacoracargaprestacion;
		this.numFila = numFila;
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
	@JoinColumn(name = "BitacoraId")
	public Bitacoracargaprestacion getBitacoracargaprestacion() {
		return this.bitacoracargaprestacion;
	}

	public void setBitacoracargaprestacion(
			Bitacoracargaprestacion bitacoracargaprestacion) {
		this.bitacoracargaprestacion = bitacoracargaprestacion;
	}

	@Column(name = "NumFila")
	public String getNumFila() {
		return this.numFila;
	}

	public void setNumFila(String numFila) {
		this.numFila = numFila;
	}

}