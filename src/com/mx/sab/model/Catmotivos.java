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
 * Catmotivos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catmotivos", catalog = "my_db_rrg")
public class Catmotivos implements java.io.Serializable {

	// Fields

	private Integer id;
	private String descripcion;
	private Set<Motivos> motivoses = new HashSet<Motivos>(0);

	// Constructors

	/** default constructor */
	public Catmotivos() {
	}

	/** minimal constructor */
	public Catmotivos(String descripcion) {
		this.descripcion = descripcion;
	}

	/** full constructor */
	public Catmotivos(String descripcion, Set<Motivos> motivoses) {
		this.descripcion = descripcion;
		this.motivoses = motivoses;
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

	@Column(name = "Descripcion", nullable = false, length = 200)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catmotivos")
	public Set<Motivos> getMotivoses() {
		return this.motivoses;
	}

	public void setMotivoses(Set<Motivos> motivoses) {
		this.motivoses = motivoses;
	}

}