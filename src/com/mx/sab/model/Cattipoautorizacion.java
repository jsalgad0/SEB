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
 * Cattipoautorizacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattipoautorizacion", catalog = "my_db_rrg")
public class Cattipoautorizacion implements java.io.Serializable {

	// Fields

	private Integer tipoAutorizacionId;
	private String descripcion;
	private Set<Permisoespecial> permisoespecials = new HashSet<Permisoespecial>(
			0);

	// Constructors

	/** default constructor */
	public Cattipoautorizacion() {
	}

	/** minimal constructor */
	public Cattipoautorizacion(String descripcion) {
		this.descripcion = descripcion;
	}

	/** full constructor */
	public Cattipoautorizacion(String descripcion,
			Set<Permisoespecial> permisoespecials) {
		this.descripcion = descripcion;
		this.permisoespecials = permisoespecials;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TipoAutorizacionId", unique = true, nullable = false)
	public Integer getTipoAutorizacionId() {
		return this.tipoAutorizacionId;
	}

	public void setTipoAutorizacionId(Integer tipoAutorizacionId) {
		this.tipoAutorizacionId = tipoAutorizacionId;
	}

	@Column(name = "Descripcion", nullable = false, length = 25)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cattipoautorizacion")
	public Set<Permisoespecial> getPermisoespecials() {
		return this.permisoespecials;
	}

	public void setPermisoespecials(Set<Permisoespecial> permisoespecials) {
		this.permisoespecials = permisoespecials;
	}

}