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
 * Catsexos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catsexos", catalog = "my_db_rrg")
public class Catsexos implements java.io.Serializable {

	// Fields

	private Integer sexoId;
	private String sexo;
	private Set<Usuarios> usuarioses = new HashSet<Usuarios>(0);
	private Set<Afiliado> afiliados = new HashSet<Afiliado>(0);
	private Set<Personasdeconfianza> personasdeconfianzas = new HashSet<Personasdeconfianza>(
			0);

	// Constructors

	/** default constructor */
	public Catsexos() {
	}

	/** full constructor */
	public Catsexos(String sexo, Set<Usuarios> usuarioses,
			Set<Afiliado> afiliados,
			Set<Personasdeconfianza> personasdeconfianzas) {
		this.sexo = sexo;
		this.usuarioses = usuarioses;
		this.afiliados = afiliados;
		this.personasdeconfianzas = personasdeconfianzas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SexoId", unique = true, nullable = false)
	public Integer getSexoId() {
		return this.sexoId;
	}

	public void setSexoId(Integer sexoId) {
		this.sexoId = sexoId;
	}

	@Column(name = "Sexo", length = 9)
	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catsexos")
	public Set<Usuarios> getUsuarioses() {
		return this.usuarioses;
	}

	public void setUsuarioses(Set<Usuarios> usuarioses) {
		this.usuarioses = usuarioses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catsexos")
	public Set<Afiliado> getAfiliados() {
		return this.afiliados;
	}

	public void setAfiliados(Set<Afiliado> afiliados) {
		this.afiliados = afiliados;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catsexos")
	public Set<Personasdeconfianza> getPersonasdeconfianzas() {
		return this.personasdeconfianzas;
	}

	public void setPersonasdeconfianzas(
			Set<Personasdeconfianza> personasdeconfianzas) {
		this.personasdeconfianzas = personasdeconfianzas;
	}

}