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
 * Catcolonias entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catcolonias", catalog = "my_db_rrg")
public class Catcolonias implements java.io.Serializable {

	// Fields

	private Integer coloniaId;
	private Catmunicipios catmunicipios;
	private String colonia;
	private Integer activo;
	private Set<Afiliado> afiliados = new HashSet<Afiliado>(0);
	private Set<Aseguradores> aseguradoreses = new HashSet<Aseguradores>(0);
	private Set<Prestadores> prestadoreses = new HashSet<Prestadores>(0);
	private Set<Lugaresdeatencion> lugaresdeatencions = new HashSet<Lugaresdeatencion>(
			0);

	// Constructors

	/** default constructor */
	public Catcolonias() {
	}

	/** minimal constructor */
	public Catcolonias(Catmunicipios catmunicipios, String colonia,
			Integer activo) {
		this.catmunicipios = catmunicipios;
		this.colonia = colonia;
		this.activo = activo;
	}

	/** full constructor */
	public Catcolonias(Catmunicipios catmunicipios, String colonia,
			Integer activo, Set<Afiliado> afiliados,
			Set<Aseguradores> aseguradoreses, Set<Prestadores> prestadoreses,
			Set<Lugaresdeatencion> lugaresdeatencions) {
		this.catmunicipios = catmunicipios;
		this.colonia = colonia;
		this.activo = activo;
		this.afiliados = afiliados;
		this.aseguradoreses = aseguradoreses;
		this.prestadoreses = prestadoreses;
		this.lugaresdeatencions = lugaresdeatencions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ColoniaId", unique = true, nullable = false)
	public Integer getColoniaId() {
		return this.coloniaId;
	}

	public void setColoniaId(Integer coloniaId) {
		this.coloniaId = coloniaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MunicipioId", nullable = false)
	public Catmunicipios getCatmunicipios() {
		return this.catmunicipios;
	}

	public void setCatmunicipios(Catmunicipios catmunicipios) {
		this.catmunicipios = catmunicipios;
	}

	@Column(name = "Colonia", nullable = false, length = 150)
	public String getColonia() {
		return this.colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catcolonias")
	public Set<Afiliado> getAfiliados() {
		return this.afiliados;
	}

	public void setAfiliados(Set<Afiliado> afiliados) {
		this.afiliados = afiliados;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catcolonias")
	public Set<Aseguradores> getAseguradoreses() {
		return this.aseguradoreses;
	}

	public void setAseguradoreses(Set<Aseguradores> aseguradoreses) {
		this.aseguradoreses = aseguradoreses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catcolonias")
	public Set<Prestadores> getPrestadoreses() {
		return this.prestadoreses;
	}

	public void setPrestadoreses(Set<Prestadores> prestadoreses) {
		this.prestadoreses = prestadoreses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catcolonias")
	public Set<Lugaresdeatencion> getLugaresdeatencions() {
		return this.lugaresdeatencions;
	}

	public void setLugaresdeatencions(Set<Lugaresdeatencion> lugaresdeatencions) {
		this.lugaresdeatencions = lugaresdeatencions;
	}

}