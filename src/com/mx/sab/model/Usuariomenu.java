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
 * Usuariomenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuariomenu", catalog = "my_db_rrg")
public class Usuariomenu implements java.io.Serializable {

	// Fields

	private Integer id;
	private Menu menu;
	private Usuarios usuarios;

	// Constructors

	/** default constructor */
	public Usuariomenu() {
	}

	/** full constructor */
	public Usuariomenu(Menu menu, Usuarios usuarios) {
		this.menu = menu;
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
	@JoinColumn(name = "MenuId")
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioId")
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

}