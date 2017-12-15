package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cattipovalorasegurador entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattipovalorasegurador", catalog = "my_db_rrg")
public class Cattipovalorasegurador implements java.io.Serializable {

	// Fields

	private Integer tipoValorAseguradorId;
	private String tipoValor;
	private Integer aseguradorId;
	private Integer activo;

	// Constructors

	/** default constructor */
	public Cattipovalorasegurador() {
	}

	/** minimal constructor */
	public Cattipovalorasegurador(String tipoValor, Integer activo) {
		this.tipoValor = tipoValor;
		this.activo = activo;
	}

	/** full constructor */
	public Cattipovalorasegurador(String tipoValor, Integer aseguradorId,
			Integer activo) {
		this.tipoValor = tipoValor;
		this.aseguradorId = aseguradorId;
		this.activo = activo;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TipoValorAseguradorId", unique = true, nullable = false)
	public Integer getTipoValorAseguradorId() {
		return this.tipoValorAseguradorId;
	}

	public void setTipoValorAseguradorId(Integer tipoValorAseguradorId) {
		this.tipoValorAseguradorId = tipoValorAseguradorId;
	}

	@Column(name = "TipoValor", nullable = false, length = 40)
	public String getTipoValor() {
		return this.tipoValor;
	}

	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	@Column(name = "AseguradorId")
	public Integer getAseguradorId() {
		return this.aseguradorId;
	}

	public void setAseguradorId(Integer aseguradorId) {
		this.aseguradorId = aseguradorId;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

}