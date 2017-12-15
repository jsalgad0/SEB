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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Menu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "menu", catalog = "my_db_rrg")
public class Menu implements java.io.Serializable {

	// Fields

	private Integer menuId;
	private String menu;
	private String imagen;
	private String url;
	private String rol;
	private Set<Menurol> menurols = new HashSet<Menurol>(0);
	private Set<Usuariomenu> usuariomenus = new HashSet<Usuariomenu>(0);

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** full constructor */
	public Menu(String menu, String imagen, String url, String rol,
			Set<Menurol> menurols, Set<Usuariomenu> usuariomenus) {
		this.menu = menu;
		this.imagen = imagen;
		this.url = url;
		this.rol = rol;
		this.menurols = menurols;
		this.usuariomenus = usuariomenus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "MenuId", unique = true, nullable = false)
	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Column(name = "Menu", length = 100)
	public String getMenu() {
		return this.menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	@Column(name = "Imagen", length = 100)
	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Column(name = "Url", length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "Rol", length = 100)
	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<Menurol> getMenurols() {
		return this.menurols;
	}

	public void setMenurols(Set<Menurol> menurols) {
		this.menurols = menurols;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<Usuariomenu> getUsuariomenus() {
		return this.usuariomenus;
	}

	public void setUsuariomenus(Set<Usuariomenu> usuariomenus) {
		this.usuariomenus = usuariomenus;
	}

}