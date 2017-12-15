package com.mx.sab.model;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Catformasfarmaceuticas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catformasfarmaceuticas", catalog = "my_db_rrg")
public class Catformasfarmaceuticas implements java.io.Serializable {

	// Fields

	private Integer formaFarmaceuticaId;
	private String formaFarmaceutica;
	private Set<Catmedicamentos> catmedicamentoses = new HashSet<Catmedicamentos>(
			0);

	// Constructors

	/** default constructor */
	public Catformasfarmaceuticas() {
	}

	/** minimal constructor */
	public Catformasfarmaceuticas(Integer formaFarmaceuticaId) {
		this.formaFarmaceuticaId = formaFarmaceuticaId;
	}

	/** full constructor */
	public Catformasfarmaceuticas(Integer formaFarmaceuticaId,
			String formaFarmaceutica, Set<Catmedicamentos> catmedicamentoses) {
		this.formaFarmaceuticaId = formaFarmaceuticaId;
		this.formaFarmaceutica = formaFarmaceutica;
		this.catmedicamentoses = catmedicamentoses;
	}

	// Property accessors
	@Id
	@Column(name = "FormaFarmaceuticaId", unique = true, nullable = false)
	public Integer getFormaFarmaceuticaId() {
		return this.formaFarmaceuticaId;
	}

	public void setFormaFarmaceuticaId(Integer formaFarmaceuticaId) {
		this.formaFarmaceuticaId = formaFarmaceuticaId;
	}

	@Column(name = "FormaFarmaceutica")
	public String getFormaFarmaceutica() {
		return this.formaFarmaceutica;
	}

	public void setFormaFarmaceutica(String formaFarmaceutica) {
		this.formaFarmaceutica = formaFarmaceutica;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catformasfarmaceuticas")
	public Set<Catmedicamentos> getCatmedicamentoses() {
		return this.catmedicamentoses;
	}

	public void setCatmedicamentoses(Set<Catmedicamentos> catmedicamentoses) {
		this.catmedicamentoses = catmedicamentoses;
	}

}