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
 * Catestados entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catestados", catalog = "my_db_rrg")
public class Catestados implements java.io.Serializable {

	// Fields

	private Integer estadoId;
	private String estado;
	private String clave;
	private Set<Aseguradores> aseguradoreses = new HashSet<Aseguradores>(0);
	private Set<Afiliado> afiliadosForEstadoDeNacimientoId = new HashSet<Afiliado>(
			0);
	private Set<Prestadores> prestadoreses = new HashSet<Prestadores>(0);
	private Set<Usuarios> usuarioses = new HashSet<Usuarios>(0);
	private Set<Personasdeconfianza> personasdeconfianzas = new HashSet<Personasdeconfianza>(
			0);
	private Set<Delegadoasegurador> delegadoaseguradors = new HashSet<Delegadoasegurador>(
			0);
	private Set<Catmunicipios> catmunicipioses = new HashSet<Catmunicipios>(0);
	private Set<Afiliado> afiliadosForEstadoId = new HashSet<Afiliado>(0);
	private Set<Lugaresdeatencion> lugaresdeatencions = new HashSet<Lugaresdeatencion>(
			0);

	// Constructors

	/** default constructor */
	public Catestados() {
	}

	/** minimal constructor */
	public Catestados(String estado) {
		this.estado = estado;
	}

	/** full constructor */
	public Catestados(String estado, String clave,
			Set<Aseguradores> aseguradoreses,
			Set<Afiliado> afiliadosForEstadoDeNacimientoId,
			Set<Prestadores> prestadoreses, Set<Usuarios> usuarioses,
			Set<Personasdeconfianza> personasdeconfianzas,
			Set<Delegadoasegurador> delegadoaseguradors,
			Set<Catmunicipios> catmunicipioses,
			Set<Afiliado> afiliadosForEstadoId,
			Set<Lugaresdeatencion> lugaresdeatencions) {
		this.estado = estado;
		this.clave = clave;
		this.aseguradoreses = aseguradoreses;
		this.afiliadosForEstadoDeNacimientoId = afiliadosForEstadoDeNacimientoId;
		this.prestadoreses = prestadoreses;
		this.usuarioses = usuarioses;
		this.personasdeconfianzas = personasdeconfianzas;
		this.delegadoaseguradors = delegadoaseguradors;
		this.catmunicipioses = catmunicipioses;
		this.afiliadosForEstadoId = afiliadosForEstadoId;
		this.lugaresdeatencions = lugaresdeatencions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "EstadoId", unique = true, nullable = false)
	public Integer getEstadoId() {
		return this.estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	@Column(name = "Estado", nullable = false, length = 100)
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "Clave", length = 3)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestados")
	public Set<Aseguradores> getAseguradoreses() {
		return this.aseguradoreses;
	}

	public void setAseguradoreses(Set<Aseguradores> aseguradoreses) {
		this.aseguradoreses = aseguradoreses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestadosByEstadoDeNacimientoId")
	public Set<Afiliado> getAfiliadosForEstadoDeNacimientoId() {
		return this.afiliadosForEstadoDeNacimientoId;
	}

	public void setAfiliadosForEstadoDeNacimientoId(
			Set<Afiliado> afiliadosForEstadoDeNacimientoId) {
		this.afiliadosForEstadoDeNacimientoId = afiliadosForEstadoDeNacimientoId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestados")
	public Set<Prestadores> getPrestadoreses() {
		return this.prestadoreses;
	}

	public void setPrestadoreses(Set<Prestadores> prestadoreses) {
		this.prestadoreses = prestadoreses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestados")
	public Set<Usuarios> getUsuarioses() {
		return this.usuarioses;
	}

	public void setUsuarioses(Set<Usuarios> usuarioses) {
		this.usuarioses = usuarioses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestados")
	public Set<Personasdeconfianza> getPersonasdeconfianzas() {
		return this.personasdeconfianzas;
	}

	public void setPersonasdeconfianzas(
			Set<Personasdeconfianza> personasdeconfianzas) {
		this.personasdeconfianzas = personasdeconfianzas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestados")
	public Set<Delegadoasegurador> getDelegadoaseguradors() {
		return this.delegadoaseguradors;
	}

	public void setDelegadoaseguradors(
			Set<Delegadoasegurador> delegadoaseguradors) {
		this.delegadoaseguradors = delegadoaseguradors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestados")
	public Set<Catmunicipios> getCatmunicipioses() {
		return this.catmunicipioses;
	}

	public void setCatmunicipioses(Set<Catmunicipios> catmunicipioses) {
		this.catmunicipioses = catmunicipioses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestadosByEstadoId")
	public Set<Afiliado> getAfiliadosForEstadoId() {
		return this.afiliadosForEstadoId;
	}

	public void setAfiliadosForEstadoId(Set<Afiliado> afiliadosForEstadoId) {
		this.afiliadosForEstadoId = afiliadosForEstadoId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestados")
	public Set<Lugaresdeatencion> getLugaresdeatencions() {
		return this.lugaresdeatencions;
	}

	public void setLugaresdeatencions(Set<Lugaresdeatencion> lugaresdeatencions) {
		this.lugaresdeatencions = lugaresdeatencions;
	}

}