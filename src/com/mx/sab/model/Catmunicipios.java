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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Catmunicipios entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catmunicipios", catalog = "my_db_rrg")
public class Catmunicipios implements java.io.Serializable {

	// Fields

	private Integer municipioId;
	private Catestados catestados;
	private String municipio;
	private Integer activo;
	private Set<Delegadoasegurador> delegadoaseguradors = new HashSet<Delegadoasegurador>(
			0);
	private Set<Aseguradores> aseguradoreses = new HashSet<Aseguradores>(0);
	private Set<Afiliado> afiliados = new HashSet<Afiliado>(0);
	private Set<Catcolonias> catcoloniases = new HashSet<Catcolonias>(0);
	private Set<Lugaresdeatencion> lugaresdeatencions = new HashSet<Lugaresdeatencion>(
			0);
	private Set<Prestadores> prestadoreses = new HashSet<Prestadores>(0);

	// Constructors

	/** default constructor */
	public Catmunicipios() {
	}

	/** minimal constructor */
	public Catmunicipios(Catestados catestados, String municipio, Integer activo) {
		this.catestados = catestados;
		this.municipio = municipio;
		this.activo = activo;
	}

	/** full constructor */
	public Catmunicipios(Catestados catestados, String municipio,
			Integer activo, Set<Delegadoasegurador> delegadoaseguradors,
			Set<Aseguradores> aseguradoreses, Set<Afiliado> afiliados,
			Set<Catcolonias> catcoloniases,
			Set<Lugaresdeatencion> lugaresdeatencions,
			Set<Prestadores> prestadoreses) {
		this.catestados = catestados;
		this.municipio = municipio;
		this.activo = activo;
		this.delegadoaseguradors = delegadoaseguradors;
		this.aseguradoreses = aseguradoreses;
		this.afiliados = afiliados;
		this.catcoloniases = catcoloniases;
		this.lugaresdeatencions = lugaresdeatencions;
		this.prestadoreses = prestadoreses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "MunicipioId", unique = true, nullable = false)
	public Integer getMunicipioId() {
		return this.municipioId;
	}

	public void setMunicipioId(Integer municipioId) {
		this.municipioId = municipioId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstadoId", nullable = false)
	public Catestados getCatestados() {
		return this.catestados;
	}

	public void setCatestados(Catestados catestados) {
		this.catestados = catestados;
	}

	@Column(name = "Municipio", nullable = false, length = 150)
	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catmunicipios")
	public Set<Delegadoasegurador> getDelegadoaseguradors() {
		return this.delegadoaseguradors;
	}

	public void setDelegadoaseguradors(
			Set<Delegadoasegurador> delegadoaseguradors) {
		this.delegadoaseguradors = delegadoaseguradors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catmunicipios")
	public Set<Aseguradores> getAseguradoreses() {
		return this.aseguradoreses;
	}

	public void setAseguradoreses(Set<Aseguradores> aseguradoreses) {
		this.aseguradoreses = aseguradoreses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catmunicipios")
	public Set<Afiliado> getAfiliados() {
		return this.afiliados;
	}

	public void setAfiliados(Set<Afiliado> afiliados) {
		this.afiliados = afiliados;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catmunicipios")
	public Set<Catcolonias> getCatcoloniases() {
		return this.catcoloniases;
	}

	public void setCatcoloniases(Set<Catcolonias> catcoloniases) {
		this.catcoloniases = catcoloniases;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catmunicipios")
	public Set<Lugaresdeatencion> getLugaresdeatencions() {
		return this.lugaresdeatencions;
	}

	public void setLugaresdeatencions(Set<Lugaresdeatencion> lugaresdeatencions) {
		this.lugaresdeatencions = lugaresdeatencions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catmunicipios")
	public Set<Prestadores> getPrestadoreses() {
		return this.prestadoreses;
	}

	public void setPrestadoreses(Set<Prestadores> prestadoreses) {
		this.prestadoreses = prestadoreses;
	}

}