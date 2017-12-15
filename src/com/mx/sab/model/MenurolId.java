package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MenurolId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class MenurolId implements java.io.Serializable {

	// Fields

	private Integer rolId;
	private Integer menuId;
	private Integer prestadorId;

	// Constructors

	/** default constructor */
	public MenurolId() {
	}

	/** full constructor */
	public MenurolId(Integer rolId, Integer menuId, Integer prestadorId) {
		this.rolId = rolId;
		this.menuId = menuId;
		this.prestadorId = prestadorId;
	}

	// Property accessors

	@Column(name = "RolId", nullable = false)
	public Integer getRolId() {
		return this.rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	@Column(name = "MenuId", nullable = false)
	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Column(name = "PrestadorId", nullable = false)
	public Integer getPrestadorId() {
		return this.prestadorId;
	}

	public void setPrestadorId(Integer prestadorId) {
		this.prestadorId = prestadorId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MenurolId))
			return false;
		MenurolId castOther = (MenurolId) other;

		return ((this.getRolId() == castOther.getRolId()) || (this.getRolId() != null
				&& castOther.getRolId() != null && this.getRolId().equals(
				castOther.getRolId())))
				&& ((this.getMenuId() == castOther.getMenuId()) || (this
						.getMenuId() != null && castOther.getMenuId() != null && this
						.getMenuId().equals(castOther.getMenuId())))
				&& ((this.getPrestadorId() == castOther.getPrestadorId()) || (this
						.getPrestadorId() != null
						&& castOther.getPrestadorId() != null && this
						.getPrestadorId().equals(castOther.getPrestadorId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRolId() == null ? 0 : this.getRolId().hashCode());
		result = 37 * result
				+ (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		result = 37
				* result
				+ (getPrestadorId() == null ? 0 : this.getPrestadorId()
						.hashCode());
		return result;
	}

}