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
 * Lugaresdeatencionroles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lugaresdeatencionroles", catalog = "my_db_rrg")
public class Lugaresdeatencionroles implements java.io.Serializable {

	// Fields

	private Integer id;
	private Lugaresdeatencion lugaresdeatencion;
	private Roles roles;

	// Constructors

	/** default constructor */
	public Lugaresdeatencionroles() {
	}

	/** full constructor */
	public Lugaresdeatencionroles(Lugaresdeatencion lugaresdeatencion,
			Roles roles) {
		this.lugaresdeatencion = lugaresdeatencion;
		this.roles = roles;
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
	@JoinColumn(name = "LugarDeAtencionId")
	public Lugaresdeatencion getLugaresdeatencion() {
		return this.lugaresdeatencion;
	}

	public void setLugaresdeatencion(Lugaresdeatencion lugaresdeatencion) {
		this.lugaresdeatencion = lugaresdeatencion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RolId")
	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

}