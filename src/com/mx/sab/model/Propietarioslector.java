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
 * Propietarioslector entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "propietarioslector", catalog = "my_db_rrg")
public class Propietarioslector implements java.io.Serializable {

	// Fields

	private Integer idPropietarioLector;
	private String propietarioLector;
	private String rfc;
	private Set<Lectores> lectoreses = new HashSet<Lectores>(0);

	// Constructors

	/** default constructor */
	public Propietarioslector() {
	}

	/** full constructor */
	public Propietarioslector(String propietarioLector, String rfc,
			Set<Lectores> lectoreses) {
		this.propietarioLector = propietarioLector;
		this.rfc = rfc;
		this.lectoreses = lectoreses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idPropietarioLector", unique = true, nullable = false)
	public Integer getIdPropietarioLector() {
		return this.idPropietarioLector;
	}

	public void setIdPropietarioLector(Integer idPropietarioLector) {
		this.idPropietarioLector = idPropietarioLector;
	}

	@Column(name = "PropietarioLector", length = 45)
	public String getPropietarioLector() {
		return this.propietarioLector;
	}

	public void setPropietarioLector(String propietarioLector) {
		this.propietarioLector = propietarioLector;
	}

	@Column(name = "RFC", length = 45)
	public String getRfc() {
		return this.rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "propietarioslector")
	public Set<Lectores> getLectoreses() {
		return this.lectoreses;
	}

	public void setLectoreses(Set<Lectores> lectoreses) {
		this.lectoreses = lectoreses;
	}

}