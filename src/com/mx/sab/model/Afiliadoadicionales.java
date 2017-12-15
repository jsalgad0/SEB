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
 * Afiliadoadicionales entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "afiliadoadicionales", catalog = "my_db_rrg")
public class Afiliadoadicionales implements java.io.Serializable {

	// Fields

	private Integer afiliadoAdicionalesId;
	private Afiliado afiliado;
	private String nombreResponsable;
	private Integer edadResponsable;
	private String parentescoResponsable;
	private String direccionResponsable;
	private String lugarResponsable;
	private String telefonoResponsable;

	// Constructors

	/** default constructor */
	public Afiliadoadicionales() {
	}

	/** minimal constructor */
	public Afiliadoadicionales(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	/** full constructor */
	public Afiliadoadicionales(Afiliado afiliado, String nombreResponsable,
			Integer edadResponsable, String parentescoResponsable,
			String direccionResponsable, String lugarResponsable,
			String telefonoResponsable) {
		this.afiliado = afiliado;
		this.nombreResponsable = nombreResponsable;
		this.edadResponsable = edadResponsable;
		this.parentescoResponsable = parentescoResponsable;
		this.direccionResponsable = direccionResponsable;
		this.lugarResponsable = lugarResponsable;
		this.telefonoResponsable = telefonoResponsable;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AfiliadoAdicionalesId", unique = true, nullable = false)
	public Integer getAfiliadoAdicionalesId() {
		return this.afiliadoAdicionalesId;
	}

	public void setAfiliadoAdicionalesId(Integer afiliadoAdicionalesId) {
		this.afiliadoAdicionalesId = afiliadoAdicionalesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AfiliadoId", nullable = false)
	public Afiliado getAfiliado() {
		return this.afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	@Column(name = "NombreResponsable", length = 100)
	public String getNombreResponsable() {
		return this.nombreResponsable;
	}

	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	@Column(name = "EdadResponsable")
	public Integer getEdadResponsable() {
		return this.edadResponsable;
	}

	public void setEdadResponsable(Integer edadResponsable) {
		this.edadResponsable = edadResponsable;
	}

	@Column(name = "ParentescoResponsable", length = 50)
	public String getParentescoResponsable() {
		return this.parentescoResponsable;
	}

	public void setParentescoResponsable(String parentescoResponsable) {
		this.parentescoResponsable = parentescoResponsable;
	}

	@Column(name = "DireccionResponsable", length = 100)
	public String getDireccionResponsable() {
		return this.direccionResponsable;
	}

	public void setDireccionResponsable(String direccionResponsable) {
		this.direccionResponsable = direccionResponsable;
	}

	@Column(name = "LugarResponsable", length = 100)
	public String getLugarResponsable() {
		return this.lugarResponsable;
	}

	public void setLugarResponsable(String lugarResponsable) {
		this.lugarResponsable = lugarResponsable;
	}

	@Column(name = "TelefonoResponsable", length = 20)
	public String getTelefonoResponsable() {
		return this.telefonoResponsable;
	}

	public void setTelefonoResponsable(String telefonoResponsable) {
		this.telefonoResponsable = telefonoResponsable;
	}

}