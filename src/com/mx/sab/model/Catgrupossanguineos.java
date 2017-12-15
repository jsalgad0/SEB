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
 * Catgrupossanguineos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catgrupossanguineos", catalog = "my_db_rrg")
public class Catgrupossanguineos implements java.io.Serializable {

	// Fields

	private Integer grupoSanguineoId;
	private String grupoSanguineo;
	private Set<Afiliadodemografico> afiliadodemograficos = new HashSet<Afiliadodemografico>(
			0);

	// Constructors

	/** default constructor */
	public Catgrupossanguineos() {
	}

	/** minimal constructor */
	public Catgrupossanguineos(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	/** full constructor */
	public Catgrupossanguineos(String grupoSanguineo,
			Set<Afiliadodemografico> afiliadodemograficos) {
		this.grupoSanguineo = grupoSanguineo;
		this.afiliadodemograficos = afiliadodemograficos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "GrupoSanguineoId", unique = true, nullable = false)
	public Integer getGrupoSanguineoId() {
		return this.grupoSanguineoId;
	}

	public void setGrupoSanguineoId(Integer grupoSanguineoId) {
		this.grupoSanguineoId = grupoSanguineoId;
	}

	@Column(name = "GrupoSanguineo", nullable = false, length = 2)
	public String getGrupoSanguineo() {
		return this.grupoSanguineo;
	}

	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catgrupossanguineos")
	public Set<Afiliadodemografico> getAfiliadodemograficos() {
		return this.afiliadodemograficos;
	}

	public void setAfiliadodemograficos(
			Set<Afiliadodemografico> afiliadodemograficos) {
		this.afiliadodemograficos = afiliadodemograficos;
	}

}