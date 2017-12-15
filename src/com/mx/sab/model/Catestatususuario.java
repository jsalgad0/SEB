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
 * Catestatususuario entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catestatususuario", catalog = "my_db_rrg")
public class Catestatususuario implements java.io.Serializable {

	// Fields

	private Integer id;
	private String descripcion;
	private Set<Usuarios> usuarioses = new HashSet<Usuarios>(0);

	// Constructors

	/** default constructor */
	public Catestatususuario() {
	}

	/** full constructor */
	public Catestatususuario(String descripcion, Set<Usuarios> usuarioses) {
		this.descripcion = descripcion;
		this.usuarioses = usuarioses;
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

	@Column(name = "Descripcion", length = 50)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestatususuario")
	public Set<Usuarios> getUsuarioses() {
		return this.usuarioses;
	}

	public void setUsuarioses(Set<Usuarios> usuarioses) {
		this.usuarioses = usuarioses;
	}

}