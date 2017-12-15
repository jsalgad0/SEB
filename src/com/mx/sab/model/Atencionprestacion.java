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
 * Atencionprestacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "atencionprestacion", catalog = "my_db_rrg")
public class Atencionprestacion implements java.io.Serializable {

	// Fields

	private AtencionprestacionId id;
	private Catprestacion catprestacion;
	private Atencionmedica atencionmedica;
	private Float valorPrestacionConvenio;
	private Float aporteAsegurador;
	private Float copagoAfiliado;
	private Integer principal;
	private Integer cantidad;

	// Constructors

	/** default constructor */
	public Atencionprestacion() {
	}

	/** minimal constructor */
	public Atencionprestacion(AtencionprestacionId id,
			Catprestacion catprestacion, Atencionmedica atencionmedica) {
		this.id = id;
		this.catprestacion = catprestacion;
		this.atencionmedica = atencionmedica;
	}

	/** full constructor */
	public Atencionprestacion(AtencionprestacionId id,
			Catprestacion catprestacion, Atencionmedica atencionmedica,
			Float valorPrestacionConvenio, Float aporteAsegurador,
			Float copagoAfiliado, Integer principal, Integer cantidad) {
		this.id = id;
		this.catprestacion = catprestacion;
		this.atencionmedica = atencionmedica;
		this.valorPrestacionConvenio = valorPrestacionConvenio;
		this.aporteAsegurador = aporteAsegurador;
		this.copagoAfiliado = copagoAfiliado;
		this.principal = principal;
		this.cantidad = cantidad;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "atencionMedicaId", column = @Column(name = "AtencionMedicaId", nullable = false)),
			@AttributeOverride(name = "prestacionId", column = @Column(name = "PrestacionId", nullable = false)) })
	public AtencionprestacionId getId() {
		return this.id;
	}

	public void setId(AtencionprestacionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PrestacionId", nullable = false, insertable = false, updatable = false)
	public Catprestacion getCatprestacion() {
		return this.catprestacion;
	}

	public void setCatprestacion(Catprestacion catprestacion) {
		this.catprestacion = catprestacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AtencionMedicaId", nullable = false, insertable = false, updatable = false)
	public Atencionmedica getAtencionmedica() {
		return this.atencionmedica;
	}

	public void setAtencionmedica(Atencionmedica atencionmedica) {
		this.atencionmedica = atencionmedica;
	}

	@Column(name = "ValorPrestacionConvenio", precision = 12, scale = 0)
	public Float getValorPrestacionConvenio() {
		return this.valorPrestacionConvenio;
	}

	public void setValorPrestacionConvenio(Float valorPrestacionConvenio) {
		this.valorPrestacionConvenio = valorPrestacionConvenio;
	}

	@Column(name = "AporteAsegurador", precision = 12, scale = 0)
	public Float getAporteAsegurador() {
		return this.aporteAsegurador;
	}

	public void setAporteAsegurador(Float aporteAsegurador) {
		this.aporteAsegurador = aporteAsegurador;
	}

	@Column(name = "CopagoAfiliado", precision = 12, scale = 0)
	public Float getCopagoAfiliado() {
		return this.copagoAfiliado;
	}

	public void setCopagoAfiliado(Float copagoAfiliado) {
		this.copagoAfiliado = copagoAfiliado;
	}

	@Column(name = "Principal")
	public Integer getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(Integer principal) {
		this.principal = principal;
	}

	@Column(name = "Cantidad")
	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}