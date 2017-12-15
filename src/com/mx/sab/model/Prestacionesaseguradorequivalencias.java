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
 * Prestacionesaseguradorequivalencias entity. @author MyEclipse Persistence
 * Tools
 */
@Entity
@Table(name = "prestacionesaseguradorequivalencias", catalog = "my_db_rrg")
public class Prestacionesaseguradorequivalencias implements
		java.io.Serializable {

	// Fields

	private PrestacionesaseguradorequivalenciasId id;
	private Catprestacion catprestacion;
	private Prestacionasegurador prestacionasegurador;

	// Constructors

	/** default constructor */
	public Prestacionesaseguradorequivalencias() {
	}

	/** full constructor */
	public Prestacionesaseguradorequivalencias(
			PrestacionesaseguradorequivalenciasId id,
			Catprestacion catprestacion,
			Prestacionasegurador prestacionasegurador) {
		this.id = id;
		this.catprestacion = catprestacion;
		this.prestacionasegurador = prestacionasegurador;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "prestacionSabid", column = @Column(name = "PrestacionSABId", nullable = false)),
			@AttributeOverride(name = "prestacionAseguradorId", column = @Column(name = "PrestacionAseguradorId", nullable = false)) })
	public PrestacionesaseguradorequivalenciasId getId() {
		return this.id;
	}

	public void setId(PrestacionesaseguradorequivalenciasId id) {
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
	@JoinColumn(name = "PrestacionAseguradorId", nullable = false, insertable = false, updatable = false)
	public Prestacionasegurador getPrestacionasegurador() {
		return this.prestacionasegurador;
	}

	public void setPrestacionasegurador(
			Prestacionasegurador prestacionasegurador) {
		this.prestacionasegurador = prestacionasegurador;
	}

}