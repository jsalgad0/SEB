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
 * Usuariorol entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuariorol", catalog = "my_db_rrg")
public class Usuariorol implements java.io.Serializable {

	// Fields

	private Integer usuarioRolId;
	private Lugaresdeatencion lugaresdeatencion;
	private Usuarios usuarios;
	private Roles roles;

	// Constructors

	/** default constructor */
	public Usuariorol() {
	}

	/** minimal constructor */
	public Usuariorol(Usuarios usuarios, Roles roles) {
		this.usuarios = usuarios;
		this.roles = roles;
	}

	/** full constructor */
	public Usuariorol(Lugaresdeatencion lugaresdeatencion, Usuarios usuarios,
			Roles roles) {
		this.lugaresdeatencion = lugaresdeatencion;
		this.usuarios = usuarios;
		this.roles = roles;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "UsuarioRolId", unique = true, nullable = false)
	public Integer getUsuarioRolId() {
		return this.usuarioRolId;
	}

	public void setUsuarioRolId(Integer usuarioRolId) {
		this.usuarioRolId = usuarioRolId;
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
	@JoinColumn(name = "UsuarioId", nullable = false)
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RolId", nullable = false)
	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

}