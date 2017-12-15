package com.mx.sab.model;
// default package

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Afiliadopersonaconfianza entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "afiliadopersonaconfianza", catalog = "my_db_rrg")
public class Afiliadopersonaconfianza implements java.io.Serializable {

	// Fields

	private AfiliadopersonaconfianzaId id;
	private Personasdeconfianza personasdeconfianza;
	private Afiliado afiliado;
	private Date fechaRegistro;
	private Integer esAcompaniante;

	// Constructors

	/** default constructor */
	public Afiliadopersonaconfianza() {
	}

	/** full constructor */
	public Afiliadopersonaconfianza(AfiliadopersonaconfianzaId id,
			Personasdeconfianza personasdeconfianza, Afiliado afiliado,
			Date fechaRegistro, Integer esAcompaniante) {
		this.id = id;
		this.personasdeconfianza = personasdeconfianza;
		this.afiliado = afiliado;
		this.fechaRegistro = fechaRegistro;
		this.esAcompaniante = esAcompaniante;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "afiliadoId", column = @Column(name = "AfiliadoId", nullable = false)),
			@AttributeOverride(name = "personaId", column = @Column(name = "PersonaId", nullable = false)) })
	public AfiliadopersonaconfianzaId getId() {
		return this.id;
	}

	public void setId(AfiliadopersonaconfianzaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PersonaId", nullable = false, insertable = false, updatable = false)
	public Personasdeconfianza getPersonasdeconfianza() {
		return this.personasdeconfianza;
	}

	public void setPersonasdeconfianza(Personasdeconfianza personasdeconfianza) {
		this.personasdeconfianza = personasdeconfianza;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AfiliadoId", nullable = false, insertable = false, updatable = false)
	public Afiliado getAfiliado() {
		return this.afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaRegistro", nullable = false, length = 10)
	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Column(name = "EsAcompaniante", nullable = false)
	public Integer getEsAcompaniante() {
		return this.esAcompaniante;
	}

	public void setEsAcompaniante(Integer esAcompaniante) {
		this.esAcompaniante = esAcompaniante;
	}

}