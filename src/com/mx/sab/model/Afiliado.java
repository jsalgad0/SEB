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
 * Afiliado entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "afiliado", catalog = "my_db_rrg")
public class Afiliado implements java.io.Serializable {

	// Fields

	private Integer afiliadoId;
	private Cattipoafiliado cattipoafiliado;
	private Catcolonias catcolonias;
	private Catsexos catsexos;
	private Catestados catestadosByEstadoId;
	private Catestados catestadosByEstadoDeNacimientoId;
	private Catmunicipios catmunicipios;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String mail;
	private Date fechaDeNacimiento;
	private String cp;
	private String calle;
	private String noExterior;
	private String noInterior;
	private Date fechaAlta;
	private Integer activo;
	private String telefono1;
	private String telefono2;
	private Set<Afiliadoadicionales> afiliadoadicionaleses = new HashSet<Afiliadoadicionales>(
			0);
	private Set<Afiliadotipoidentificador> afiliadotipoidentificadors = new HashSet<Afiliadotipoidentificador>(
			0);
	private Set<Historiaclinica> historiaclinicas = new HashSet<Historiaclinica>(
			0);
	private Set<Afiliadopersonaconfianza> afiliadopersonaconfianzas = new HashSet<Afiliadopersonaconfianza>(
			0);
	private Set<Atencionmedica> atencionmedicas = new HashSet<Atencionmedica>(0);
	private Set<Signosvitales> signosvitaleses = new HashSet<Signosvitales>(0);
	private Set<Agenda> agendas = new HashSet<Agenda>(0);
	private Set<AfiliadoAsegurador> afiliadoAseguradors = new HashSet<AfiliadoAsegurador>(
			0);
	private Set<Permisoespecial> permisoespecials = new HashSet<Permisoespecial>(
			0);
	private Set<Afiliadodemografico> afiliadodemograficos = new HashSet<Afiliadodemografico>(
			0);

	// Constructors

	/** default constructor */
	public Afiliado() {
	}

	/** minimal constructor */
	public Afiliado(Catsexos catsexos, Catestados catestadosByEstadoId,
			String nombre, String apellidoPaterno, String apellidoMaterno,
			Date fechaDeNacimiento, Date fechaAlta, Integer activo) {
		this.catsexos = catsexos;
		this.catestadosByEstadoId = catestadosByEstadoId;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.fechaAlta = fechaAlta;
		this.activo = activo;
	}

	/** full constructor */
	public Afiliado(Cattipoafiliado cattipoafiliado, Catcolonias catcolonias,
			Catsexos catsexos, Catestados catestadosByEstadoId,
			Catestados catestadosByEstadoDeNacimientoId,
			Catmunicipios catmunicipios, String nombre, String apellidoPaterno,
			String apellidoMaterno, String mail, Date fechaDeNacimiento,
			String cp, String calle, String noExterior, String noInterior,
			Date fechaAlta, Integer activo, String telefono1, String telefono2,
			Set<Afiliadoadicionales> afiliadoadicionaleses,
			Set<Afiliadotipoidentificador> afiliadotipoidentificadors,
			Set<Historiaclinica> historiaclinicas,
			Set<Afiliadopersonaconfianza> afiliadopersonaconfianzas,
			Set<Atencionmedica> atencionmedicas,
			Set<Signosvitales> signosvitaleses, Set<Agenda> agendas,
			Set<AfiliadoAsegurador> afiliadoAseguradors,
			Set<Permisoespecial> permisoespecials,
			Set<Afiliadodemografico> afiliadodemograficos) {
		this.cattipoafiliado = cattipoafiliado;
		this.catcolonias = catcolonias;
		this.catsexos = catsexos;
		this.catestadosByEstadoId = catestadosByEstadoId;
		this.catestadosByEstadoDeNacimientoId = catestadosByEstadoDeNacimientoId;
		this.catmunicipios = catmunicipios;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.mail = mail;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.cp = cp;
		this.calle = calle;
		this.noExterior = noExterior;
		this.noInterior = noInterior;
		this.fechaAlta = fechaAlta;
		this.activo = activo;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.afiliadoadicionaleses = afiliadoadicionaleses;
		this.afiliadotipoidentificadors = afiliadotipoidentificadors;
		this.historiaclinicas = historiaclinicas;
		this.afiliadopersonaconfianzas = afiliadopersonaconfianzas;
		this.atencionmedicas = atencionmedicas;
		this.signosvitaleses = signosvitaleses;
		this.agendas = agendas;
		this.afiliadoAseguradors = afiliadoAseguradors;
		this.permisoespecials = permisoespecials;
		this.afiliadodemograficos = afiliadodemograficos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AfiliadoId", unique = true, nullable = false)
	public Integer getAfiliadoId() {
		return this.afiliadoId;
	}

	public void setAfiliadoId(Integer afiliadoId) {
		this.afiliadoId = afiliadoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoAfiliadoId")
	public Cattipoafiliado getCattipoafiliado() {
		return this.cattipoafiliado;
	}

	public void setCattipoafiliado(Cattipoafiliado cattipoafiliado) {
		this.cattipoafiliado = cattipoafiliado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Colonia")
	public Catcolonias getCatcolonias() {
		return this.catcolonias;
	}

	public void setCatcolonias(Catcolonias catcolonias) {
		this.catcolonias = catcolonias;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SexoId", nullable = false)
	public Catsexos getCatsexos() {
		return this.catsexos;
	}

	public void setCatsexos(Catsexos catsexos) {
		this.catsexos = catsexos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstadoId", nullable = false)
	public Catestados getCatestadosByEstadoId() {
		return this.catestadosByEstadoId;
	}

	public void setCatestadosByEstadoId(Catestados catestadosByEstadoId) {
		this.catestadosByEstadoId = catestadosByEstadoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstadoDeNacimientoId")
	public Catestados getCatestadosByEstadoDeNacimientoId() {
		return this.catestadosByEstadoDeNacimientoId;
	}

	public void setCatestadosByEstadoDeNacimientoId(
			Catestados catestadosByEstadoDeNacimientoId) {
		this.catestadosByEstadoDeNacimientoId = catestadosByEstadoDeNacimientoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MunicipioId")
	public Catmunicipios getCatmunicipios() {
		return this.catmunicipios;
	}

	public void setCatmunicipios(Catmunicipios catmunicipios) {
		this.catmunicipios = catmunicipios;
	}

	@Column(name = "Nombre", nullable = false, length = 60)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "ApellidoPaterno", nullable = false, length = 60)
	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	@Column(name = "ApellidoMaterno", nullable = false, length = 60)
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	@Column(name = "Mail", length = 50)
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaDeNacimiento", nullable = false, length = 10)
	public Date getFechaDeNacimiento() {
		return this.fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	@Column(name = "CP", length = 11)
	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@Column(name = "Calle", length = 50)
	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	@Column(name = "NoExterior", length = 15)
	public String getNoExterior() {
		return this.noExterior;
	}

	public void setNoExterior(String noExterior) {
		this.noExterior = noExterior;
	}

	@Column(name = "NoInterior", length = 15)
	public String getNoInterior() {
		return this.noInterior;
	}

	public void setNoInterior(String noInterior) {
		this.noInterior = noInterior;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaAlta", nullable = false, length = 10)
	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@Column(name = "Telefono1", length = 15)
	public String getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	@Column(name = "Telefono2", length = 15)
	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "afiliado")
	public Set<Afiliadoadicionales> getAfiliadoadicionaleses() {
		return this.afiliadoadicionaleses;
	}

	public void setAfiliadoadicionaleses(
			Set<Afiliadoadicionales> afiliadoadicionaleses) {
		this.afiliadoadicionaleses = afiliadoadicionaleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "afiliado")
	public Set<Afiliadotipoidentificador> getAfiliadotipoidentificadors() {
		return this.afiliadotipoidentificadors;
	}

	public void setAfiliadotipoidentificadors(
			Set<Afiliadotipoidentificador> afiliadotipoidentificadors) {
		this.afiliadotipoidentificadors = afiliadotipoidentificadors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "afiliado")
	public Set<Historiaclinica> getHistoriaclinicas() {
		return this.historiaclinicas;
	}

	public void setHistoriaclinicas(Set<Historiaclinica> historiaclinicas) {
		this.historiaclinicas = historiaclinicas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "afiliado")
	public Set<Afiliadopersonaconfianza> getAfiliadopersonaconfianzas() {
		return this.afiliadopersonaconfianzas;
	}

	public void setAfiliadopersonaconfianzas(
			Set<Afiliadopersonaconfianza> afiliadopersonaconfianzas) {
		this.afiliadopersonaconfianzas = afiliadopersonaconfianzas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "afiliado")
	public Set<Atencionmedica> getAtencionmedicas() {
		return this.atencionmedicas;
	}

	public void setAtencionmedicas(Set<Atencionmedica> atencionmedicas) {
		this.atencionmedicas = atencionmedicas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "afiliado")
	public Set<Signosvitales> getSignosvitaleses() {
		return this.signosvitaleses;
	}

	public void setSignosvitaleses(Set<Signosvitales> signosvitaleses) {
		this.signosvitaleses = signosvitaleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "afiliado")
	public Set<Agenda> getAgendas() {
		return this.agendas;
	}

	public void setAgendas(Set<Agenda> agendas) {
		this.agendas = agendas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "afiliado")
	public Set<AfiliadoAsegurador> getAfiliadoAseguradors() {
		return this.afiliadoAseguradors;
	}

	public void setAfiliadoAseguradors(
			Set<AfiliadoAsegurador> afiliadoAseguradors) {
		this.afiliadoAseguradors = afiliadoAseguradors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "afiliado")
	public Set<Permisoespecial> getPermisoespecials() {
		return this.permisoespecials;
	}

	public void setPermisoespecials(Set<Permisoespecial> permisoespecials) {
		this.permisoespecials = permisoespecials;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "afiliado")
	public Set<Afiliadodemografico> getAfiliadodemograficos() {
		return this.afiliadodemograficos;
	}

	public void setAfiliadodemograficos(
			Set<Afiliadodemografico> afiliadodemograficos) {
		this.afiliadodemograficos = afiliadodemograficos;
	}

}