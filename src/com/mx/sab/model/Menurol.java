package com.mx.sab.model;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Menurol entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "menurol", catalog = "my_db_rrg")
public class Menurol implements java.io.Serializable {

	// Fields

	private MenurolId id;
	private Prestadores prestadores;
	private Menu menu;
	private Roles roles;

	// Constructors

	/** default constructor */
	public Menurol() {
	}

	/** full constructor */
	public Menurol(MenurolId id, Prestadores prestadores, Menu menu, Roles roles) {
		this.id = id;
		this.prestadores = prestadores;
		this.menu = menu;
		this.roles = roles;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "rolId", column = @Column(name = "RolId", nullable = false)),
			@AttributeOverride(name = "menuId", column = @Column(name = "MenuId", nullable = false)),
			@AttributeOverride(name = "prestadorId", column = @Column(name = "PrestadorId", nullable = false)) })
	public MenurolId getId() {
		return this.id;
	}

	public void setId(MenurolId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PrestadorId", nullable = false, insertable = false, updatable = false)
	public Prestadores getPrestadores() {
		return this.prestadores;
	}

	public void setPrestadores(Prestadores prestadores) {
		this.prestadores = prestadores;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MenuId", nullable = false, insertable = false, updatable = false)
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RolId", nullable = false, insertable = false, updatable = false)
	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

}