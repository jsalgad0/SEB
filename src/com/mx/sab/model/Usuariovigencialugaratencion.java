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
 * Usuariovigencialugaratencion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuariovigencialugaratencion", catalog = "my_db_rrg")
public class Usuariovigencialugaratencion implements java.io.Serializable {

	// Fields

	private Integer id;
	private Lugaresdeatencion lugaresdeatencion;
	private Catvigencia catvigencia;
	private Usuarios usuarios;
	private String claveInterna;

	// Constructors

	/** default constructor */
	public Usuariovigencialugaratencion() {
	}

	/** full constructor */
	public Usuariovigencialugaratencion(Lugaresdeatencion lugaresdeatencion,
			Catvigencia catvigencia, Usuarios usuarios, String claveInterna) {
		this.lugaresdeatencion = lugaresdeatencion;
		this.catvigencia = catvigencia;
		this.usuarios = usuarios;
		this.claveInterna = claveInterna;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LugarAtencionId")
	public Lugaresdeatencion getLugaresdeatencion() {
		return this.lugaresdeatencion;
	}

	public void setLugaresdeatencion(Lugaresdeatencion lugaresdeatencion) {
		this.lugaresdeatencion = lugaresdeatencion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VigenciaId")
	public Catvigencia getCatvigencia() {
		return this.catvigencia;
	}

	public void setCatvigencia(Catvigencia catvigencia) {
		this.catvigencia = catvigencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioId")
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@Column(name = "ClaveInterna", length = 200)
	public String getClaveInterna() {
		return this.claveInterna;
	}

	public void setClaveInterna(String claveInterna) {
		this.claveInterna = claveInterna;
	}

}