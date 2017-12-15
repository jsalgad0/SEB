package com.mx.sab.model;
// default package

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Personasdeconfianza entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "personasdeconfianza", catalog = "my_db_rrg")
public class Personasdeconfianza implements java.io.Serializable {

	// Fields

	private Integer personaId;
	private Cattipoidentificador cattipoidentificador;
	private Catsexos catsexos;
	private Catestados catestados;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String valorTipoIdentificador;
	private Date fechaNacimiento;
	private String auditoriaAutentiaAcompaniante;
	private String observaciones;
	private Integer activo;
	private Set<Atencionmedica> atencionmedicas = new HashSet<Atencionmedica>(0);
	private Set<Afiliadopersonaconfianza> afiliadopersonaconfianzas = new HashSet<Afiliadopersonaconfianza>(
			0);

	// Constructors

	/** default constructor */
	public Personasdeconfianza() {
	}

	/** minimal constructor */
	public Personasdeconfianza(String nombre, String apellidoPaterno,
			String apellidoMaterno, Integer activo) {
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.activo = activo;
	}

	/** full constructor */
	public Personasdeconfianza(Cattipoidentificador cattipoidentificador,
			Catsexos catsexos, Catestados catestados, String nombre,
			String apellidoPaterno, String apellidoMaterno,
			String valorTipoIdentificador, Date fechaNacimiento,
			String auditoriaAutentiaAcompaniante, String observaciones,
			Integer activo, Set<Atencionmedica> atencionmedicas,
			Set<Afiliadopersonaconfianza> afiliadopersonaconfianzas) {
		this.cattipoidentificador = cattipoidentificador;
		this.catsexos = catsexos;
		this.catestados = catestados;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.valorTipoIdentificador = valorTipoIdentificador;
		this.fechaNacimiento = fechaNacimiento;
		this.auditoriaAutentiaAcompaniante = auditoriaAutentiaAcompaniante;
		this.observaciones = observaciones;
		this.activo = activo;
		this.atencionmedicas = atencionmedicas;
		this.afiliadopersonaconfianzas = afiliadopersonaconfianzas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PersonaId", unique = true, nullable = false)
	public Integer getPersonaId() {
		return this.personaId;
	}

	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoIdentificador")
	public Cattipoidentificador getCattipoidentificador() {
		return this.cattipoidentificador;
	}

	public void setCattipoidentificador(
			Cattipoidentificador cattipoidentificador) {
		this.cattipoidentificador = cattipoidentificador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SexoId")
	public Catsexos getCatsexos() {
		return this.catsexos;
	}

	public void setCatsexos(Catsexos catsexos) {
		this.catsexos = catsexos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstadoNacimientoId")
	public Catestados getCatestados() {
		return this.catestados;
	}

	public void setCatestados(Catestados catestados) {
		this.catestados = catestados;
	}

	@Column(name = "Nombre", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "ApellidoPaterno", nullable = false, length = 100)
	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	@Column(name = "ApellidoMaterno", nullable = false, length = 100)
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	@Column(name = "ValorTipoIdentificador")
	public String getValorTipoIdentificador() {
		return this.valorTipoIdentificador;
	}

	public void setValorTipoIdentificador(String valorTipoIdentificador) {
		this.valorTipoIdentificador = valorTipoIdentificador;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaNacimiento", length = 10)
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Column(name = "AuditoriaAutentiaAcompaniante")
	public String getAuditoriaAutentiaAcompaniante() {
		return this.auditoriaAutentiaAcompaniante;
	}

	public void setAuditoriaAutentiaAcompaniante(
			String auditoriaAutentiaAcompaniante) {
		this.auditoriaAutentiaAcompaniante = auditoriaAutentiaAcompaniante;
	}

	@Column(name = "Observaciones", length = 2000)
	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "personasdeconfianza")
	public Set<Atencionmedica> getAtencionmedicas() {
		return this.atencionmedicas;
	}

	public void setAtencionmedicas(Set<Atencionmedica> atencionmedicas) {
		this.atencionmedicas = atencionmedicas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "personasdeconfianza")
	public Set<Afiliadopersonaconfianza> getAfiliadopersonaconfianzas() {
		return this.afiliadopersonaconfianzas;
	}

	public void setAfiliadopersonaconfianzas(
			Set<Afiliadopersonaconfianza> afiliadopersonaconfianzas) {
		this.afiliadopersonaconfianzas = afiliadopersonaconfianzas;
	}

}