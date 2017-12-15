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
 * Prestadores entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "prestadores", catalog = "my_db_rrg")
public class Prestadores implements java.io.Serializable {

	// Fields

	private Integer prestadorId;
	private Catcolonias catcolonias;
	private Cattipoprestador cattipoprestador;
	private Catestados catestados;
	private Catmunicipios catmunicipios;
	private Cattipospersonas cattipospersonas;
	private String nombreRazonSocial;
	private String rfc;
	private String correoElectronico;
	private Integer usarTablaPrestacionesSab;
	private String cp;
	private String calle;
	private String noExt;
	private String noInt;
	private String telefono;
	private Integer activo;
	private String nombreContacto;
	private String telefono2;
	private Set<Menurol> menurols = new HashSet<Menurol>(0);
	private Set<Prestacionprestador> prestacionprestadors = new HashSet<Prestacionprestador>(
			0);
	private Set<Agenda> agendas = new HashSet<Agenda>(0);
	private Set<Prestadormedico> prestadormedicos = new HashSet<Prestadormedico>(
			0);
	private Set<Documentosprestador> documentosprestadors = new HashSet<Documentosprestador>(
			0);
	private Set<Atencionmedica> atencionmedicas = new HashSet<Atencionmedica>(0);
	private Set<Convenios> convenioses = new HashSet<Convenios>(0);
	private Set<Prestadoreslugaresatencion> prestadoreslugaresatencions = new HashSet<Prestadoreslugaresatencion>(
			0);

	// Constructors

	/** default constructor */
	public Prestadores() {
	}

	/** minimal constructor */
	public Prestadores(Integer usarTablaPrestacionesSab) {
		this.usarTablaPrestacionesSab = usarTablaPrestacionesSab;
	}

	/** full constructor */
	public Prestadores(Catcolonias catcolonias,
			Cattipoprestador cattipoprestador, Catestados catestados,
			Catmunicipios catmunicipios, Cattipospersonas cattipospersonas,
			String nombreRazonSocial, String rfc, String correoElectronico,
			Integer usarTablaPrestacionesSab, String cp, String calle,
			String noExt, String noInt, String telefono, Integer activo,
			String nombreContacto, String telefono2, Set<Menurol> menurols,
			Set<Prestacionprestador> prestacionprestadors, Set<Agenda> agendas,
			Set<Prestadormedico> prestadormedicos,
			Set<Documentosprestador> documentosprestadors,
			Set<Atencionmedica> atencionmedicas, Set<Convenios> convenioses,
			Set<Prestadoreslugaresatencion> prestadoreslugaresatencions) {
		this.catcolonias = catcolonias;
		this.cattipoprestador = cattipoprestador;
		this.catestados = catestados;
		this.catmunicipios = catmunicipios;
		this.cattipospersonas = cattipospersonas;
		this.nombreRazonSocial = nombreRazonSocial;
		this.rfc = rfc;
		this.correoElectronico = correoElectronico;
		this.usarTablaPrestacionesSab = usarTablaPrestacionesSab;
		this.cp = cp;
		this.calle = calle;
		this.noExt = noExt;
		this.noInt = noInt;
		this.telefono = telefono;
		this.activo = activo;
		this.nombreContacto = nombreContacto;
		this.telefono2 = telefono2;
		this.menurols = menurols;
		this.prestacionprestadors = prestacionprestadors;
		this.agendas = agendas;
		this.prestadormedicos = prestadormedicos;
		this.documentosprestadors = documentosprestadors;
		this.atencionmedicas = atencionmedicas;
		this.convenioses = convenioses;
		this.prestadoreslugaresatencions = prestadoreslugaresatencions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PrestadorId", unique = true, nullable = false)
	public Integer getPrestadorId() {
		return this.prestadorId;
	}

	public void setPrestadorId(Integer prestadorId) {
		this.prestadorId = prestadorId;
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
	@JoinColumn(name = "TipoPrestadorId")
	public Cattipoprestador getCattipoprestador() {
		return this.cattipoprestador;
	}

	public void setCattipoprestador(Cattipoprestador cattipoprestador) {
		this.cattipoprestador = cattipoprestador;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoPersonaId")
	public Cattipospersonas getCattipospersonas() {
		return this.cattipospersonas;
	}

	public void setCattipospersonas(Cattipospersonas cattipospersonas) {
		this.cattipospersonas = cattipospersonas;
	}

	@Column(name = "NombreRazonSocial", length = 50)
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

	@Column(name = "CorreoElectronico", length = 155)
	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	@Column(name = "UsarTablaPrestacionesSAB", nullable = false)
	public Integer getUsarTablaPrestacionesSab() {
		return this.usarTablaPrestacionesSab;
	}

	public void setUsarTablaPrestacionesSab(Integer usarTablaPrestacionesSab) {
		this.usarTablaPrestacionesSab = usarTablaPrestacionesSab;
	}

	@Column(name = "CP", length = 10)
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

	@Column(name = "NoExt", length = 10)
	public String getNoExt() {
		return this.noExt;
	}

	public void setNoExt(String noExt) {
		this.noExt = noExt;
	}

	@Column(name = "NoInt", length = 10)
	public String getNoInt() {
		return this.noInt;
	}

	public void setNoInt(String noInt) {
		this.noInt = noInt;
	}

	@Column(name = "Telefono", length = 10)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prestadores")
	public Set<Menurol> getMenurols() {
		return this.menurols;
	}

	public void setMenurols(Set<Menurol> menurols) {
		this.menurols = menurols;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prestadores")
	public Set<Prestacionprestador> getPrestacionprestadors() {
		return this.prestacionprestadors;
	}

	public void setPrestacionprestadors(
			Set<Prestacionprestador> prestacionprestadors) {
		this.prestacionprestadors = prestacionprestadors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prestadores")
	public Set<Agenda> getAgendas() {
		return this.agendas;
	}

	public void setAgendas(Set<Agenda> agendas) {
		this.agendas = agendas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prestadores")
	public Set<Prestadormedico> getPrestadormedicos() {
		return this.prestadormedicos;
	}

	public void setPrestadormedicos(Set<Prestadormedico> prestadormedicos) {
		this.prestadormedicos = prestadormedicos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prestadores")
	public Set<Documentosprestador> getDocumentosprestadors() {
		return this.documentosprestadors;
	}

	public void setDocumentosprestadors(
			Set<Documentosprestador> documentosprestadors) {
		this.documentosprestadors = documentosprestadors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prestadores")
	public Set<Atencionmedica> getAtencionmedicas() {
		return this.atencionmedicas;
	}

	public void setAtencionmedicas(Set<Atencionmedica> atencionmedicas) {
		this.atencionmedicas = atencionmedicas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prestadores")
	public Set<Convenios> getConvenioses() {
		return this.convenioses;
	}

	public void setConvenioses(Set<Convenios> convenioses) {
		this.convenioses = convenioses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prestadores")
	public Set<Prestadoreslugaresatencion> getPrestadoreslugaresatencions() {
		return this.prestadoreslugaresatencions;
	}

	public void setPrestadoreslugaresatencions(
			Set<Prestadoreslugaresatencion> prestadoreslugaresatencions) {
		this.prestadoreslugaresatencions = prestadoreslugaresatencions;
	}

}