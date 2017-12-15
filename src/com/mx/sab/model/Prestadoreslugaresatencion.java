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
 * Prestadoreslugaresatencion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "prestadoreslugaresatencion", catalog = "my_db_rrg")
public class Prestadoreslugaresatencion implements java.io.Serializable {

	// Fields

	private PrestadoreslugaresatencionId id;
	private Prestadores prestadores;
	private Lugaresdeatencion lugaresdeatencion;

	// Constructors

	/** default constructor */
	public Prestadoreslugaresatencion() {
	}

	/** full constructor */
	public Prestadoreslugaresatencion(PrestadoreslugaresatencionId id,
			Prestadores prestadores, Lugaresdeatencion lugaresdeatencion) {
		this.id = id;
		this.prestadores = prestadores;
		this.lugaresdeatencion = lugaresdeatencion;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "lugarDeAtencionId", column = @Column(name = "LugarDeAtencionId", nullable = false)),
			@AttributeOverride(name = "prestadorId", column = @Column(name = "PrestadorId", nullable = false)) })
	public PrestadoreslugaresatencionId getId() {
		return this.id;
	}

	public void setId(PrestadoreslugaresatencionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PrestadorId", nullable = false, insertable = false, updatable = false)
	public Prestadores getPrestadores() {
		return this.prestadores;
	}

	public void setPrestadores(Prestadores prestadores) {
		this.prestadores = prestadores;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LugarDeAtencionId", nullable = false, insertable = false, updatable = false)
	public Lugaresdeatencion getLugaresdeatencion() {
		return this.lugaresdeatencion;
	}

	public void setLugaresdeatencion(Lugaresdeatencion lugaresdeatencion) {
		this.lugaresdeatencion = lugaresdeatencion;
	}

}