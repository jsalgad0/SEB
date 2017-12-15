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
 * Cattipoidentificador entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattipoidentificador", catalog = "my_db_rrg")
public class Cattipoidentificador implements java.io.Serializable {

	// Fields

	private Integer tipoIdentificadorId;
	private Aseguradores aseguradores;
	private String tipoIdentificador;
	private Integer activo;
	private Integer activoPersonasYacompaniantes;
	private Integer activoEnAdmin;
	private Set<Afiliadotipoidentificador> afiliadotipoidentificadors = new HashSet<Afiliadotipoidentificador>(
			0);
	private Set<Agenda> agendas = new HashSet<Agenda>(0);
	private Set<Usuariotipoidentificador> usuariotipoidentificadors = new HashSet<Usuariotipoidentificador>(
			0);
	private Set<Atencionmedica> atencionmedicas = new HashSet<Atencionmedica>(0);
	private Set<Personasdeconfianza> personasdeconfianzas = new HashSet<Personasdeconfianza>(
			0);

	// Constructors

	/** default constructor */
	public Cattipoidentificador() {
	}

	/** minimal constructor */
	public Cattipoidentificador(String tipoIdentificador, Integer activo,
			Integer activoPersonasYacompaniantes, Integer activoEnAdmin) {
		this.tipoIdentificador = tipoIdentificador;
		this.activo = activo;
		this.activoPersonasYacompaniantes = activoPersonasYacompaniantes;
		this.activoEnAdmin = activoEnAdmin;
	}

	/** full constructor */
	public Cattipoidentificador(Aseguradores aseguradores,
			String tipoIdentificador, Integer activo,
			Integer activoPersonasYacompaniantes, Integer activoEnAdmin,
			Set<Afiliadotipoidentificador> afiliadotipoidentificadors,
			Set<Agenda> agendas,
			Set<Usuariotipoidentificador> usuariotipoidentificadors,
			Set<Atencionmedica> atencionmedicas,
			Set<Personasdeconfianza> personasdeconfianzas) {
		this.aseguradores = aseguradores;
		this.tipoIdentificador = tipoIdentificador;
		this.activo = activo;
		this.activoPersonasYacompaniantes = activoPersonasYacompaniantes;
		this.activoEnAdmin = activoEnAdmin;
		this.afiliadotipoidentificadors = afiliadotipoidentificadors;
		this.agendas = agendas;
		this.usuariotipoidentificadors = usuariotipoidentificadors;
		this.atencionmedicas = atencionmedicas;
		this.personasdeconfianzas = personasdeconfianzas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TipoIdentificadorId", unique = true, nullable = false)
	public Integer getTipoIdentificadorId() {
		return this.tipoIdentificadorId;
	}

	public void setTipoIdentificadorId(Integer tipoIdentificadorId) {
		this.tipoIdentificadorId = tipoIdentificadorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AseguradorId")
	public Aseguradores getAseguradores() {
		return this.aseguradores;
	}

	public void setAseguradores(Aseguradores aseguradores) {
		this.aseguradores = aseguradores;
	}

	@Column(name = "TipoIdentificador", nullable = false, length = 40)
	public String getTipoIdentificador() {
		return this.tipoIdentificador;
	}

	public void setTipoIdentificador(String tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@Column(name = "ActivoPersonasYAcompaniantes", nullable = false)
	public Integer getActivoPersonasYacompaniantes() {
		return this.activoPersonasYacompaniantes;
	}

	public void setActivoPersonasYacompaniantes(
			Integer activoPersonasYacompaniantes) {
		this.activoPersonasYacompaniantes = activoPersonasYacompaniantes;
	}

	@Column(name = "ActivoEnAdmin", nullable = false)
	public Integer getActivoEnAdmin() {
		return this.activoEnAdmin;
	}

	public void setActivoEnAdmin(Integer activoEnAdmin) {
		this.activoEnAdmin = activoEnAdmin;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cattipoidentificador")
	public Set<Afiliadotipoidentificador> getAfiliadotipoidentificadors() {
		return this.afiliadotipoidentificadors;
	}

	public void setAfiliadotipoidentificadors(
			Set<Afiliadotipoidentificador> afiliadotipoidentificadors) {
		this.afiliadotipoidentificadors = afiliadotipoidentificadors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cattipoidentificador")
	public Set<Agenda> getAgendas() {
		return this.agendas;
	}

	public void setAgendas(Set<Agenda> agendas) {
		this.agendas = agendas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cattipoidentificador")
	public Set<Usuariotipoidentificador> getUsuariotipoidentificadors() {
		return this.usuariotipoidentificadors;
	}

	public void setUsuariotipoidentificadors(
			Set<Usuariotipoidentificador> usuariotipoidentificadors) {
		this.usuariotipoidentificadors = usuariotipoidentificadors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cattipoidentificador")
	public Set<Atencionmedica> getAtencionmedicas() {
		return this.atencionmedicas;
	}

	public void setAtencionmedicas(Set<Atencionmedica> atencionmedicas) {
		this.atencionmedicas = atencionmedicas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cattipoidentificador")
	public Set<Personasdeconfianza> getPersonasdeconfianzas() {
		return this.personasdeconfianzas;
	}

	public void setPersonasdeconfianzas(
			Set<Personasdeconfianza> personasdeconfianzas) {
		this.personasdeconfianzas = personasdeconfianzas;
	}

}