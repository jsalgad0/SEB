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
 * AfiliadoAsegurador entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "afiliado_asegurador", catalog = "my_db_rrg")
public class AfiliadoAsegurador implements java.io.Serializable {

	// Fields

	private AfiliadoAseguradorId id;
	private Aseguradores aseguradores;
	private Afiliado afiliado;

	// Constructors

	/** default constructor */
	public AfiliadoAsegurador() {
	}

	/** full constructor */
	public AfiliadoAsegurador(AfiliadoAseguradorId id,
			Aseguradores aseguradores, Afiliado afiliado) {
		this.id = id;
		this.aseguradores = aseguradores;
		this.afiliado = afiliado;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "afiliadoId", column = @Column(name = "AfiliadoId", nullable = false)),
			@AttributeOverride(name = "aseguradorId", column = @Column(name = "AseguradorId", nullable = false)) })
	public AfiliadoAseguradorId getId() {
		return this.id;
	}

	public void setId(AfiliadoAseguradorId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AseguradorId", nullable = false, insertable = false, updatable = false)
	public Aseguradores getAseguradores() {
		return this.aseguradores;
	}

	public void setAseguradores(Aseguradores aseguradores) {
		this.aseguradores = aseguradores;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AfiliadoId", nullable = false, insertable = false, updatable = false)
	public Afiliado getAfiliado() {
		return this.afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

}