package com.mx.sab.model;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Afiliadotipovalorasegurador entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "afiliadotipovalorasegurador", catalog = "my_db_rrg")
public class Afiliadotipovalorasegurador implements java.io.Serializable {

	// Fields

	private AfiliadotipovaloraseguradorId id;

	// Constructors

	/** default constructor */
	public Afiliadotipovalorasegurador() {
	}

	/** full constructor */
	public Afiliadotipovalorasegurador(AfiliadotipovaloraseguradorId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "afiliadoId", column = @Column(name = "AfiliadoId", nullable = false)),
			@AttributeOverride(name = "tipoValorAseguradorId", column = @Column(name = "TipoValorAseguradorId", nullable = false)),
			@AttributeOverride(name = "tipoIdValor", column = @Column(name = "TipoIdValor", nullable = false, length = 100)) })
	public AfiliadotipovaloraseguradorId getId() {
		return this.id;
	}

	public void setId(AfiliadotipovaloraseguradorId id) {
		this.id = id;
	}

}