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
 * Prestacionesprestadorequivalencias entity. @author MyEclipse Persistence
 * Tools
 */
@Entity
@Table(name = "prestacionesprestadorequivalencias", catalog = "my_db_rrg")
public class Prestacionesprestadorequivalencias implements java.io.Serializable {

	// Fields

	private PrestacionesprestadorequivalenciasId id;
	private Catprestacion catprestacion;
	private Prestacionprestador prestacionprestador;

	// Constructors

	/** default constructor */
	public Prestacionesprestadorequivalencias() {
	}

	/** full constructor */
	public Prestacionesprestadorequivalencias(
			PrestacionesprestadorequivalenciasId id,
			Catprestacion catprestacion, Prestacionprestador prestacionprestador) {
		this.id = id;
		this.catprestacion = catprestacion;
		this.prestacionprestador = prestacionprestador;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "prestacionSabid", column = @Column(name = "PrestacionSABId", nullable = false)),
			@AttributeOverride(name = "prestacionPrestadorId", column = @Column(name = "PrestacionPrestadorId", nullable = false)) })
	public PrestacionesprestadorequivalenciasId getId() {
		return this.id;
	}

	public void setId(PrestacionesprestadorequivalenciasId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PrestacionSABId", nullable = false, insertable = false, updatable = false)
	public Catprestacion getCatprestacion() {
		return this.catprestacion;
	}

	public void setCatprestacion(Catprestacion catprestacion) {
		this.catprestacion = catprestacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PrestacionPrestadorId", nullable = false, insertable = false, updatable = false)
	public Prestacionprestador getPrestacionprestador() {
		return this.prestacionprestador;
	}

	public void setPrestacionprestador(Prestacionprestador prestacionprestador) {
		this.prestacionprestador = prestacionprestador;
	}

}