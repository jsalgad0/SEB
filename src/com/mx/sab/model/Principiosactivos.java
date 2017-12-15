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
 * Principiosactivos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "principiosactivos", catalog = "my_db_rrg")
public class Principiosactivos implements java.io.Serializable {

	// Fields

	private Integer principioActivoId;
	private String principioActivo;
	private Set<Catmedicamentos> catmedicamentoses = new HashSet<Catmedicamentos>(
			0);

	// Constructors

	/** default constructor */
	public Principiosactivos() {
	}

	/** full constructor */
	public Principiosactivos(String principioActivo,
			Set<Catmedicamentos> catmedicamentoses) {
		this.principioActivo = principioActivo;
		this.catmedicamentoses = catmedicamentoses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PrincipioActivoId", unique = true, nullable = false)
	public Integer getPrincipioActivoId() {
		return this.principioActivoId;
	}

	public void setPrincipioActivoId(Integer principioActivoId) {
		this.principioActivoId = principioActivoId;
	}

	@Column(name = "PrincipioActivo", length = 200)
	public String getPrincipioActivo() {
		return this.principioActivo;
	}

	public void setPrincipioActivo(String principioActivo) {
		this.principioActivo = principioActivo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "principiosactivos")
	public Set<Catmedicamentos> getCatmedicamentoses() {
		return this.catmedicamentoses;
	}

	public void setCatmedicamentoses(Set<Catmedicamentos> catmedicamentoses) {
		this.catmedicamentoses = catmedicamentoses;
	}

}