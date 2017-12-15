package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Delegadoasegurador entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "delegadoasegurador", catalog = "my_db_rrg")
public class Delegadoasegurador implements java.io.Serializable {

	// Fields

	private Integer delegacionId;
	private Catestados catestados;
	private Catmunicipios catmunicipios;
	private Integer aseguradorId;
	private String delegacion;
	private String cp;
	private Integer asentamientoId;
	private String calle;
	private String noExt;
	private String noInt;

	// Constructors

	/** default constructor */
	public Delegadoasegurador() {
	}

	/** minimal constructor */
	public Delegadoasegurador(Integer delegacionId) {
		this.delegacionId = delegacionId;
	}

	/** full constructor */
	public Delegadoasegurador(Integer delegacionId, Catestados catestados,
			Catmunicipios catmunicipios, Integer aseguradorId,
			String delegacion, String cp, Integer asentamientoId, String calle,
			String noExt, String noInt) {
		this.delegacionId = delegacionId;
		this.catestados = catestados;
		this.catmunicipios = catmunicipios;
		this.aseguradorId = aseguradorId;
		this.delegacion = delegacion;
		this.cp = cp;
		this.asentamientoId = asentamientoId;
		this.calle = calle;
		this.noExt = noExt;
		this.noInt = noInt;
	}

	// Property accessors
	@Id
	@Column(name = "DelegacionId", unique = true, nullable = false)
	public Integer getDelegacionId() {
		return this.delegacionId;
	}

	public void setDelegacionId(Integer delegacionId) {
		this.delegacionId = delegacionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstadoId")
	public Catestados getCatestados() {
		return this.catestados;
	}

	public void setCatestados(Catestados catestados) {
		this.catestados = catestados;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MunicipioId")
	public Catmunicipios getCatmunicipios() {
		return this.catmunicipios;
	}

	public void setCatmunicipios(Catmunicipios catmunicipios) {
		this.catmunicipios = catmunicipios;
	}

	@Column(name = "AseguradorId")
	public Integer getAseguradorId() {
		return this.aseguradorId;
	}

	public void setAseguradorId(Integer aseguradorId) {
		this.aseguradorId = aseguradorId;
	}

	@Column(name = "Delegacion", length = 50)
	public String getDelegacion() {
		return this.delegacion;
	}

	public void setDelegacion(String delegacion) {
		this.delegacion = delegacion;
	}

	@Column(name = "CP", length = 5)
	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@Column(name = "AsentamientoId")
	public Integer getAsentamientoId() {
		return this.asentamientoId;
	}

	public void setAsentamientoId(Integer asentamientoId) {
		this.asentamientoId = asentamientoId;
	}

	@Column(name = "Calle", length = 50)
	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	@Column(name = "NoExt", length = 10)
	public String getNoExt() {
		return this.noExt;
	}

	public void setNoExt(String noExt) {
		this.noExt = noExt;
	}

	@Column(name = "NoInt", length = 10)
	public String getNoInt() {
		return this.noInt;
	}

	public void setNoInt(String noInt) {
		this.noInt = noInt;
	}

}