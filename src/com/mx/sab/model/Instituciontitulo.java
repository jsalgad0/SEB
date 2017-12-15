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
 * Instituciontitulo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "instituciontitulo", catalog = "my_db_rrg")
public class Instituciontitulo implements java.io.Serializable {

	// Fields

	private Integer institucionTituloId;
	private String institucionTitulo;
	private String siglasInstitucionTitulo;
	private String rvoe;
	private Set<Prestadormedico> prestadormedicos = new HashSet<Prestadormedico>(
			0);

	// Constructors

	/** default constructor */
	public Instituciontitulo() {
	}

	/** minimal constructor */
	public Instituciontitulo(Integer institucionTituloId) {
		this.institucionTituloId = institucionTituloId;
	}

	/** full constructor */
	public Instituciontitulo(Integer institucionTituloId,
			String institucionTitulo, String siglasInstitucionTitulo,
			String rvoe, Set<Prestadormedico> prestadormedicos) {
		this.institucionTituloId = institucionTituloId;
		this.institucionTitulo = institucionTitulo;
		this.siglasInstitucionTitulo = siglasInstitucionTitulo;
		this.rvoe = rvoe;
		this.prestadormedicos = prestadormedicos;
	}

	// Property accessors
	@Id
	@Column(name = "InstitucionTituloId", unique = true, nullable = false)
	public Integer getInstitucionTituloId() {
		return this.institucionTituloId;
	}

	public void setInstitucionTituloId(Integer institucionTituloId) {
		this.institucionTituloId = institucionTituloId;
	}

	@Column(name = "InstitucionTitulo", length = 200)
	public String getInstitucionTitulo() {
		return this.institucionTitulo;
	}

	public void setInstitucionTitulo(String institucionTitulo) {
		this.institucionTitulo = institucionTitulo;
	}

	@Column(name = "SiglasInstitucionTitulo", length = 20)
	public String getSiglasInstitucionTitulo() {
		return this.siglasInstitucionTitulo;
	}

	public void setSiglasInstitucionTitulo(String siglasInstitucionTitulo) {
		this.siglasInstitucionTitulo = siglasInstitucionTitulo;
	}

	@Column(name = "RVOE", length = 20)
	public String getRvoe() {
		return this.rvoe;
	}

	public void setRvoe(String rvoe) {
		this.rvoe = rvoe;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "instituciontitulo")
	public Set<Prestadormedico> getPrestadormedicos() {
		return this.prestadormedicos;
	}

	public void setPrestadormedicos(Set<Prestadormedico> prestadormedicos) {
		this.prestadormedicos = prestadormedicos;
	}

}