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
 * Catmodificacionusuario entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catmodificacionusuario", catalog = "my_db_rrg")
public class Catmodificacionusuario implements java.io.Serializable {

	// Fields

	private Integer id;
	private String descripcion;
	private Set<Usuarioauditoria> usuarioauditorias = new HashSet<Usuarioauditoria>(
			0);

	// Constructors

	/** default constructor */
	public Catmodificacionusuario() {
	}

	/** full constructor */
	public Catmodificacionusuario(String descripcion,
			Set<Usuarioauditoria> usuarioauditorias) {
		this.descripcion = descripcion;
		this.usuarioauditorias = usuarioauditorias;
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

	@Column(name = "Descripcion", length = 200)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catmodificacionusuario")
	public Set<Usuarioauditoria> getUsuarioauditorias() {
		return this.usuarioauditorias;
	}

	public void setUsuarioauditorias(Set<Usuarioauditoria> usuarioauditorias) {
		this.usuarioauditorias = usuarioauditorias;
	}

}