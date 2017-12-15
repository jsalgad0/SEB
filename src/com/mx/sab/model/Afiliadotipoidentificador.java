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
 * Afiliadotipoidentificador entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "afiliadotipoidentificador", catalog = "my_db_rrg")
public class Afiliadotipoidentificador implements java.io.Serializable {

	// Fields

	private AfiliadotipoidentificadorId id;
	private Cattipoidentificador cattipoidentificador;
	private Afiliado afiliado;
	private String tipoIdValor;

	// Constructors

	/** default constructor */
	public Afiliadotipoidentificador() {
	}

	/** full constructor */
	public Afiliadotipoidentificador(AfiliadotipoidentificadorId id,
			Cattipoidentificador cattipoidentificador, Afiliado afiliado,
			String tipoIdValor) {
		this.id = id;
		this.cattipoidentificador = cattipoidentificador;
		this.afiliado = afiliado;
		this.tipoIdValor = tipoIdValor;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "afiliadoId", column = @Column(name = "AfiliadoId", nullable = false)),
			@AttributeOverride(name = "tipoIdentificadorId", column = @Column(name = "TipoIdentificadorId", nullable = false)) })
	public AfiliadotipoidentificadorId getId() {
		return this.id;
	}

	public void setId(AfiliadotipoidentificadorId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoIdentificadorId", nullable = false, insertable = false, updatable = false)
	public Cattipoidentificador getCattipoidentificador() {
		return this.cattipoidentificador;
	}

	public void setCattipoidentificador(
			Cattipoidentificador cattipoidentificador) {
		this.cattipoidentificador = cattipoidentificador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AfiliadoId", nullable = false, insertable = false, updatable = false)
	public Afiliado getAfiliado() {
		return this.afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	@Column(name = "TipoIdValor", nullable = false, length = 100)
	public String getTipoIdValor() {
		return this.tipoIdValor;
	}

	public void setTipoIdValor(String tipoIdValor) {
		this.tipoIdValor = tipoIdValor;
	}

}