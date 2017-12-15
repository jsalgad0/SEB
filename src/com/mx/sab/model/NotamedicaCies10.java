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
 * NotamedicaCies10 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notamedica_cies10", catalog = "my_db_rrg")
public class NotamedicaCies10 implements java.io.Serializable {

	// Fields

	private NotamedicaCies10Id id;
	private Notamedica notamedica;
	private Cattipodiagnostico cattipodiagnostico;
	private Catcies10 catcies10;
	private Integer principal;

	// Constructors

	/** default constructor */
	public NotamedicaCies10() {
	}

	/** minimal constructor */
	public NotamedicaCies10(NotamedicaCies10Id id, Notamedica notamedica,
			Catcies10 catcies10, Integer principal) {
		this.id = id;
		this.notamedica = notamedica;
		this.catcies10 = catcies10;
		this.principal = principal;
	}

	/** full constructor */
	public NotamedicaCies10(NotamedicaCies10Id id, Notamedica notamedica,
			Cattipodiagnostico cattipodiagnostico, Catcies10 catcies10,
			Integer principal) {
		this.id = id;
		this.notamedica = notamedica;
		this.cattipodiagnostico = cattipodiagnostico;
		this.catcies10 = catcies10;
		this.principal = principal;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "notaMedicaId", column = @Column(name = "NotaMedicaId", nullable = false)),
			@AttributeOverride(name = "cie10id", column = @Column(name = "CIE10Id", nullable = false)) })
	public NotamedicaCies10Id getId() {
		return this.id;
	}

	public void setId(NotamedicaCies10Id id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NotaMedicaId", nullable = false, insertable = false, updatable = false)
	public Notamedica getNotamedica() {
		return this.notamedica;
	}

	public void setNotamedica(Notamedica notamedica) {
		this.notamedica = notamedica;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoDiagnosticoId")
	public Cattipodiagnostico getCattipodiagnostico() {
		return this.cattipodiagnostico;
	}

	public void setCattipodiagnostico(Cattipodiagnostico cattipodiagnostico) {
		this.cattipodiagnostico = cattipodiagnostico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CIE10Id", nullable = false, insertable = false, updatable = false)
	public Catcies10 getCatcies10() {
		return this.catcies10;
	}

	public void setCatcies10(Catcies10 catcies10) {
		this.catcies10 = catcies10;
	}

	@Column(name = "Principal", nullable = false)
	public Integer getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(Integer principal) {
		this.principal = principal;
	}

}