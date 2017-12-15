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
 * Usuarioasegurador entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuarioasegurador", catalog = "my_db_rrg")
public class Usuarioasegurador implements java.io.Serializable {

	// Fields

	private Integer id;
	private Aseguradores aseguradores;
	private Usuarios usuarios;

	// Constructors

	/** default constructor */
	public Usuarioasegurador() {
	}

	/** full constructor */
	public Usuarioasegurador(Aseguradores aseguradores, Usuarios usuarios) {
		this.aseguradores = aseguradores;
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
	@JoinColumn(name = "AseguradorId", nullable = false)
	public Aseguradores getAseguradores() {
		return this.aseguradores;
	}

	public void setAseguradores(Aseguradores aseguradores) {
		this.aseguradores = aseguradores;
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