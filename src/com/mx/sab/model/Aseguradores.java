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
 * Aseguradores entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "aseguradores", catalog = "my_db_rrg")
public class Aseguradores implements java.io.Serializable {

	// Fields

	private Integer aseguradorId;
	private Catcolonias catcolonias;
	private Catestados catestados;
	private Catmunicipios catmunicipios;
	private String nombreRazonSocial;
	private String rfc;
	private String correoElectronico;
	private String cp;
	private String calle;
	private String noExt;
	private String noInt;
	private String telefono;
	private Integer activo;
	private Integer autorizacionEspecial;
	private Integer firmaPaciente;
	private Integer recetaResurtible;
	private Integer acompaniante;
	private Integer funcionPacienteNoEnLista;
	private String nombreContacto;
	private String telefono2;
	private Set<Permisoespecial> permisoespecials = new HashSet<Permisoespecial>(
			0);
	private Set<Convenios> convenioses = new HashSet<Convenios>(0);
	private Set<AfiliadoAsegurador> afiliadoAseguradors = new HashSet<AfiliadoAsegurador>(
			0);
	private Set<Prestacionasegurador> prestacionaseguradors = new HashSet<Prestacionasegurador>(
			0);
	private Set<Catsignosvitalesadicionales> catsignosvitalesadicionaleses = new HashSet<Catsignosvitalesadicionales>(
			0);
	private Set<Agenda> agendas = new HashSet<Agenda>(0);
	private Set<Usuarioasegurador> usuarioaseguradors = new HashSet<Usuarioasegurador>(
			0);
	private Set<Cattipoidentificador> cattipoidentificadors = new HashSet<Cattipoidentificador>(
			0);
	private Set<Atencionmedica> atencionmedicas = new HashSet<Atencionmedica>(0);
	private Set<Cattipoafiliado> cattipoafiliados = new HashSet<Cattipoafiliado>(
			0);

	// Constructors

	/** default constructor */
	public Aseguradores() {
	}

	/** full constructor */
	public Aseguradores(Catcolonias catcolonias, Catestados catestados,
			Catmunicipios catmunicipios, String nombreRazonSocial, String rfc,
			String correoElectronico, String cp, String calle, String noExt,
			String noInt, String telefono, Integer activo,
			Integer autorizacionEspecial, Integer firmaPaciente,
			Integer recetaResurtible, Integer acompaniante,
			Integer funcionPacienteNoEnLista, String nombreContacto,
			String telefono2, Set<Permisoespecial> permisoespecials,
			Set<Convenios> convenioses,
			Set<AfiliadoAsegurador> afiliadoAseguradors,
			Set<Prestacionasegurador> prestacionaseguradors,
			Set<Catsignosvitalesadicionales> catsignosvitalesadicionaleses,
			Set<Agenda> agendas, Set<Usuarioasegurador> usuarioaseguradors,
			Set<Cattipoidentificador> cattipoidentificadors,
			Set<Atencionmedica> atencionmedicas,
			Set<Cattipoafiliado> cattipoafiliados) {
		this.catcolonias = catcolonias;
		this.catestados = catestados;
		this.catmunicipios = catmunicipios;
		this.nombreRazonSocial = nombreRazonSocial;
		this.rfc = rfc;
		this.correoElectronico = correoElectronico;
		this.cp = cp;
		this.calle = calle;
		this.noExt = noExt;
		this.noInt = noInt;
		this.telefono = telefono;
		this.activo = activo;
		this.autorizacionEspecial = autorizacionEspecial;
		this.firmaPaciente = firmaPaciente;
		this.recetaResurtible = recetaResurtible;
		this.acompaniante = acompaniante;
		this.funcionPacienteNoEnLista = funcionPacienteNoEnLista;
		this.nombreContacto = nombreContacto;
		this.telefono2 = telefono2;
		this.permisoespecials = permisoespecials;
		this.convenioses = convenioses;
		this.afiliadoAseguradors = afiliadoAseguradors;
		this.prestacionaseguradors = prestacionaseguradors;
		this.catsignosvitalesadicionaleses = catsignosvitalesadicionaleses;
		this.agendas = agendas;
		this.usuarioaseguradors = usuarioaseguradors;
		this.cattipoidentificadors = cattipoidentificadors;
		this.atencionmedicas = atencionmedicas;
		this.cattipoafiliados = cattipoafiliados;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AseguradorId", unique = true, nullable = false)
	public Integer getAseguradorId() {
		return this.aseguradorId;
	}

	public void setAseguradorId(Integer aseguradorId) {
		this.aseguradorId = aseguradorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColoniaId")
	public Catcolonias getCatcolonias() {
		return this.catcolonias;
	}

	public void setCatcolonias(Catcolonias catcolonias) {
		this.catcolonias = catcolonias;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstadoId")
	public Catestados getCatestados() {
		return this.catestados;
	}

	public void setCatestados(Catestados catestados) {
		this.catestados = catestados;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MunicipioId")
	public Catmunicipios getCatmunicipios() {
		return this.catmunicipios;
	}

	public void setCatmunicipios(Catmunicipios catmunicipios) {
		this.catmunicipios = catmunicipios;
	}

	@Column(name = "NombreRazonSocial", length = 100)
	public String getNombreRazonSocial() {
		return this.nombreRazonSocial;
	}

	public void setNombreRazonSocial(String nombreRazonSocial) {
		this.nombreRazonSocial = nombreRazonSocial;
	}

	@Column(name = "RFC", length = 13)
	public String getRfc() {
		return this.rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	@Column(name = "CorreoElectronico", length = 200)
	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	@Column(name = "CP", length = 5)
	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@Column(name = "Calle", length = 100)
	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	@Column(name = "NoExt", length = 50)
	public String getNoExt() {
		return this.noExt;
	}

	public void setNoExt(String noExt) {
		this.noExt = noExt;
	}

	@Column(name = "NoInt", length = 50)
	public String getNoInt() {
		return this.noInt;
	}

	public void setNoInt(String noInt) {
		this.noInt = noInt;
	}

	@Column(name = "Telefono", length = 50)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "Activo")
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@Column(name = "AutorizacionEspecial")
	public Integer getAutorizacionEspecial() {
		return this.autorizacionEspecial;
	}

	public void setAutorizacionEspecial(Integer autorizacionEspecial) {
		this.autorizacionEspecial = autorizacionEspecial;
	}

	@Column(name = "FirmaPaciente")
	public Integer getFirmaPaciente() {
		return this.firmaPaciente;
	}

	public void setFirmaPaciente(Integer firmaPaciente) {
		this.firmaPaciente = firmaPaciente;
	}

	@Column(name = "RecetaResurtible")
	public Integer getRecetaResurtible() {
		return this.recetaResurtible;
	}

	public void setRecetaResurtible(Integer recetaResurtible) {
		this.recetaResurtible = recetaResurtible;
	}

	@Column(name = "Acompaniante")
	public Integer getAcompaniante() {
		return this.acompaniante;
	}

	public void setAcompaniante(Integer acompaniante) {
		this.acompaniante = acompaniante;
	}

	@Column(name = "FuncionPacienteNoEnLista")
	public Integer getFuncionPacienteNoEnLista() {
		return this.funcionPacienteNoEnLista;
	}

	public void setFuncionPacienteNoEnLista(Integer funcionPacienteNoEnLista) {
		this.funcionPacienteNoEnLista = funcionPacienteNoEnLista;
	}

	@Column(name = "NombreContacto", length = 100)
	public String getNombreContacto() {
		return this.nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	@Column(name = "Telefono2", length = 10)
	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "aseguradores")
	public Set<Permisoespecial> getPermisoespecials() {
		return this.permisoespecials;
	}

	public void setPermisoespecials(Set<Permisoespecial> permisoespecials) {
		this.permisoespecials = permisoespecials;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "aseguradores")
	public Set<Convenios> getConvenioses() {
		return this.convenioses;
	}

	public void setConvenioses(Set<Convenios> convenioses) {
		this.convenioses = convenioses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "aseguradores")
	public Set<AfiliadoAsegurador> getAfiliadoAseguradors() {
		return this.afiliadoAseguradors;
	}

	public void setAfiliadoAseguradors(
			Set<AfiliadoAsegurador> afiliadoAseguradors) {
		this.afiliadoAseguradors = afiliadoAseguradors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "aseguradores")
	public Set<Prestacionasegurador> getPrestacionaseguradors() {
		return this.prestacionaseguradors;
	}

	public void setPrestacionaseguradors(
			Set<Prestacionasegurador> prestacionaseguradors) {
		this.prestacionaseguradors = prestacionaseguradors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "aseguradores")
	public Set<Catsignosvitalesadicionales> getCatsignosvitalesadicionaleses() {
		return this.catsignosvitalesadicionaleses;
	}

	public void setCatsignosvitalesadicionaleses(
			Set<Catsignosvitalesadicionales> catsignosvitalesadicionaleses) {
		this.catsignosvitalesadicionaleses = catsignosvitalesadicionaleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "aseguradores")
	public Set<Agenda> getAgendas() {
		return this.agendas;
	}

	public void setAgendas(Set<Agenda> agendas) {
		this.agendas = agendas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "aseguradores")
	public Set<Usuarioasegurador> getUsuarioaseguradors() {
		return this.usuarioaseguradors;
	}

	public void setUsuarioaseguradors(Set<Usuarioasegurador> usuarioaseguradors) {
		this.usuarioaseguradors = usuarioaseguradors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "aseguradores")
	public Set<Cattipoidentificador> getCattipoidentificadors() {
		return this.cattipoidentificadors;
	}

	public void setCattipoidentificadors(
			Set<Cattipoidentificador> cattipoidentificadors) {
		this.cattipoidentificadors = cattipoidentificadors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "aseguradores")
	public Set<Atencionmedica> getAtencionmedicas() {
		return this.atencionmedicas;
	}

	public void setAtencionmedicas(Set<Atencionmedica> atencionmedicas) {
		this.atencionmedicas = atencionmedicas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "aseguradores")
	public Set<Cattipoafiliado> getCattipoafiliados() {
		return this.cattipoafiliados;
	}

	public void setCattipoafiliados(Set<Cattipoafiliado> cattipoafiliados) {
		this.cattipoafiliados = cattipoafiliados;
	}

}