package com.mx.sab.model;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Roles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roles", catalog = "my_db_rrg")
public class Roles implements java.io.Serializable {

	// Fields

	private Integer rolId;
	private Modulos modulos;
	private String rol;
	private Set<Lugaresdeatencionroles> lugaresdeatencionroleses = new HashSet<Lugaresdeatencionroles>(
			0);
	private Set<Usuariorol> usuariorols = new HashSet<Usuariorol>(0);
	private Set<Menurol> menurols = new HashSet<Menurol>(0);

	// Constructors

	/** default constructor */
	public Roles() {
	}

	/** minimal constructor */
	public Roles(Modulos modulos) {
		this.modulos = modulos;
	}

	/** full constructor */
	public Roles(Modulos modulos, String rol,
			Set<Lugaresdeatencionroles> lugaresdeatencionroleses,
			Set<Usuariorol> usuariorols, Set<Menurol> menurols) {
		this.modulos = modulos;
		this.rol = rol;
		this.lugaresdeatencionroleses = lugaresdeatencionroleses;
		this.usuariorols = usuariorols;
		this.menurols = menurols;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RolId", unique = true, nullable = false)
	public Integer getRolId() {
		return this.rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ModuloId", nullable = false)
	public Modulos getModulos() {
		return this.modulos;
	}

	public void setModulos(Modulos modulos) {
		this.modulos = modulos;
	}

	@Column(name = "Rol", length = 45)
	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<Lugaresdeatencionroles> getLugaresdeatencionroleses() {
		return this.lugaresdeatencionroleses;
	}

	public void setLugaresdeatencionroleses(
			Set<Lugaresdeatencionroles> lugaresdeatencionroleses) {
		this.lugaresdeatencionroleses = lugaresdeatencionroleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<Usuariorol> getUsuariorols() {
		return this.usuariorols;
	}

	public void setUsuariorols(Set<Usuariorol> usuariorols) {
		this.usuariorols = usuariorols;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<Menurol> getMenurols() {
		return this.menurols;
	}

	public void setMenurols(Set<Menurol> menurols) {
		this.menurols = menurols;
	}

}