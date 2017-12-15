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
 * Usuariolugaratencion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuariolugaratencion", catalog = "my_db_rrg")
public class Usuariolugaratencion implements java.io.Serializable {

	// Fields

	private Integer id;
	private Lugaresdeatencion lugaresdeatencion;
	private Usuarios usuarios;

	// Constructors

	/** default constructor */
	public Usuariolugaratencion() {
	}

	/** full constructor */
	public Usuariolugaratencion(Lugaresdeatencion lugaresdeatencion,
			Usuarios usuarios) {
		this.lugaresdeatencion = lugaresdeatencion;
		this.usuarios = usuarios;
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
	@JoinColumn(name = "LugarDeAtencionId", nullable = false)
	public Lugaresdeatencion getLugaresdeatencion() {
		return this.lugaresdeatencion;
	}

	public void setLugaresdeatencion(Lugaresdeatencion lugaresdeatencion) {
		this.lugaresdeatencion = lugaresdeatencion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioId", nullable = false)
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

}