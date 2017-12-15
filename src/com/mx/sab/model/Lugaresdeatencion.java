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
 * Lugaresdeatencion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lugaresdeatencion", catalog = "my_db_rrg")
public class Lugaresdeatencion implements java.io.Serializable {

	// Fields

	private Integer lugarDeAtencionId;
	private Catcolonias catcolonias;
	private Catzonacardinal catzonacardinal;
	private Catestados catestados;
	private Cattipounidad cattipounidad;
	private Catmunicipios catmunicipios;
	private String descripcion;
	private Integer codigoLugarAtencion;
	private String claveDeLaUnidad;
	private String calleyNo;
	private String cp;
	private String telefono;
	private Integer usoDeHuella;
	private Integer activo;
	private Integer sistemaConsultas;
	private String nombreContacto;
	private String telefono2;
	private String correo;
	private String numExterno;
	private String numInterno;
	private Set<Usuariovigencialugaratencion> usuariovigencialugaratencions = new HashSet<Usuariovigencialugaratencion>(
			0);
	private Set<Lectores> lectoreses = new HashSet<Lectores>(0);
	private Set<Usuariorol> usuariorols = new HashSet<Usuariorol>(0);
	private Set<Folio> folios = new HashSet<Folio>(0);
	private Set<Prestadoreslugaresatencion> prestadoreslugaresatencions = new HashSet<Prestadoreslugaresatencion>(
			0);
	private Set<Atencionmedica> atencionmedicas = new HashSet<Atencionmedica>(0);
	private Set<Solicitudreferencia> solicitudreferencias = new HashSet<Solicitudreferencia>(
			0);
	private Set<Convenios> convenioses = new HashSet<Convenios>(0);
	private Set<Permisoespecial> permisoespecials = new HashSet<Permisoespecial>(
			0);
	private Set<Lugaresdeatencionroles> lugaresdeatencionroleses = new HashSet<Lugaresdeatencionroles>(
			0);
	private Set<Agenda> agendas = new HashSet<Agenda>(0);
	private Set<Usuariolugaratencion> usuariolugaratencions = new HashSet<Usuariolugaratencion>(
			0);

	// Constructors

	/** default constructor */
	public Lugaresdeatencion() {
	}

	/** minimal constructor */
	public Lugaresdeatencion(Integer usoDeHuella, Integer activo,
			String nombreContacto, String correo, String numExterno,
			String numInterno) {
		this.usoDeHuella = usoDeHuella;
		this.activo = activo;
		this.nombreContacto = nombreContacto;
		this.correo = correo;
		this.numExterno = numExterno;
		this.numInterno = numInterno;
	}

	/** full constructor */
	public Lugaresdeatencion(Catcolonias catcolonias,
			Catzonacardinal catzonacardinal, Catestados catestados,
			Cattipounidad cattipounidad, Catmunicipios catmunicipios,
			String descripcion, Integer codigoLugarAtencion,
			String claveDeLaUnidad, String calleyNo, String cp,
			String telefono, Integer usoDeHuella, Integer activo,
			Integer sistemaConsultas, String nombreContacto, String telefono2,
			String correo, String numExterno, String numInterno,
			Set<Usuariovigencialugaratencion> usuariovigencialugaratencions,
			Set<Lectores> lectoreses, Set<Usuariorol> usuariorols,
			Set<Folio> folios,
			Set<Prestadoreslugaresatencion> prestadoreslugaresatencions,
			Set<Atencionmedica> atencionmedicas,
			Set<Solicitudreferencia> solicitudreferencias,
			Set<Convenios> convenioses, Set<Permisoespecial> permisoespecials,
			Set<Lugaresdeatencionroles> lugaresdeatencionroleses,
			Set<Agenda> agendas, Set<Usuariolugaratencion> usuariolugaratencions) {
		this.catcolonias = catcolonias;
		this.catzonacardinal = catzonacardinal;
		this.catestados = catestados;
		this.cattipounidad = cattipounidad;
		this.catmunicipios = catmunicipios;
		this.descripcion = descripcion;
		this.codigoLugarAtencion = codigoLugarAtencion;
		this.claveDeLaUnidad = claveDeLaUnidad;
		this.calleyNo = calleyNo;
		this.cp = cp;
		this.telefono = telefono;
		this.usoDeHuella = usoDeHuella;
		this.activo = activo;
		this.sistemaConsultas = sistemaConsultas;
		this.nombreContacto = nombreContacto;
		this.telefono2 = telefono2;
		this.correo = correo;
		this.numExterno = numExterno;
		this.numInterno = numInterno;
		this.usuariovigencialugaratencions = usuariovigencialugaratencions;
		this.lectoreses = lectoreses;
		this.usuariorols = usuariorols;
		this.folios = folios;
		this.prestadoreslugaresatencions = prestadoreslugaresatencions;
		this.atencionmedicas = atencionmedicas;
		this.solicitudreferencias = solicitudreferencias;
		this.convenioses = convenioses;
		this.permisoespecials = permisoespecials;
		this.lugaresdeatencionroleses = lugaresdeatencionroleses;
		this.agendas = agendas;
		this.usuariolugaratencions = usuariolugaratencions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LugarDeAtencionId", unique = true, nullable = false)
	public Integer getLugarDeAtencionId() {
		return this.lugarDeAtencionId;
	}

	public void setLugarDeAtencionId(Integer lugarDeAtencionId) {
		this.lugarDeAtencionId = lugarDeAtencionId;
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
	@JoinColumn(name = "ZonaCardinalId")
	public Catzonacardinal getCatzonacardinal() {
		return this.catzonacardinal;
	}

	public void setCatzonacardinal(Catzonacardinal catzonacardinal) {
		this.catzonacardinal = catzonacardinal;
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
	@JoinColumn(name = "TipoDeUnidadId")
	public Cattipounidad getCattipounidad() {
		return this.cattipounidad;
	}

	public void setCattipounidad(Cattipounidad cattipounidad) {
		this.cattipounidad = cattipounidad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MunicipioId")
	public Catmunicipios getCatmunicipios() {
		return this.catmunicipios;
	}

	public void setCatmunicipios(Catmunicipios catmunicipios) {
		this.catmunicipios = catmunicipios;
	}

	@Column(name = "Descripcion", length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "CodigoLugarAtencion")
	public Integer getCodigoLugarAtencion() {
		return this.codigoLugarAtencion;
	}

	public void setCodigoLugarAtencion(Integer codigoLugarAtencion) {
		this.codigoLugarAtencion = codigoLugarAtencion;
	}

	@Column(name = "ClaveDeLaUnidad", length = 10)
	public String getClaveDeLaUnidad() {
		return this.claveDeLaUnidad;
	}

	public void setClaveDeLaUnidad(String claveDeLaUnidad) {
		this.claveDeLaUnidad = claveDeLaUnidad;
	}

	@Column(name = "CalleyNo", length = 100)
	public String getCalleyNo() {
		return this.calleyNo;
	}

	public void setCalleyNo(String calleyNo) {
		this.calleyNo = calleyNo;
	}

	@Column(name = "CP", length = 5)
	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@Column(name = "Telefono", length = 50)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "UsoDeHuella", nullable = false)
	public Integer getUsoDeHuella() {
		return this.usoDeHuella;
	}

	public void setUsoDeHuella(Integer usoDeHuella) {
		this.usoDeHuella = usoDeHuella;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@Column(name = "SistemaConsultas")
	public Integer getSistemaConsultas() {
		return this.sistemaConsultas;
	}

	public void setSistemaConsultas(Integer sistemaConsultas) {
		this.sistemaConsultas = sistemaConsultas;
	}

	@Column(name = "NombreContacto", nullable = false, length = 100)
	public String getNombreContacto() {
		return this.nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	@Column(name = "Telefono2", length = 50)
	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	@Column(name = "Correo", nullable = false, length = 50)
	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "NumExterno", nullable = false, length = 10)
	public String getNumExterno() {
		return this.numExterno;
	}

	public void setNumExterno(String numExterno) {
		this.numExterno = numExterno;
	}

	@Column(name = "NumInterno", nullable = false, length = 10)
	public String getNumInterno() {
		return this.numInterno;
	}

	public void setNumInterno(String numInterno) {
		this.numInterno = numInterno;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugaresdeatencion")
	public Set<Usuariovigencialugaratencion> getUsuariovigencialugaratencions() {
		return this.usuariovigencialugaratencions;
	}

	public void setUsuariovigencialugaratencions(
			Set<Usuariovigencialugaratencion> usuariovigencialugaratencions) {
		this.usuariovigencialugaratencions = usuariovigencialugaratencions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugaresdeatencion")
	public Set<Lectores> getLectoreses() {
		return this.lectoreses;
	}

	public void setLectoreses(Set<Lectores> lectoreses) {
		this.lectoreses = lectoreses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugaresdeatencion")
	public Set<Usuariorol> getUsuariorols() {
		return this.usuariorols;
	}

	public void setUsuariorols(Set<Usuariorol> usuariorols) {
		this.usuariorols = usuariorols;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugaresdeatencion")
	public Set<Folio> getFolios() {
		return this.folios;
	}

	public void setFolios(Set<Folio> folios) {
		this.folios = folios;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugaresdeatencion")
	public Set<Prestadoreslugaresatencion> getPrestadoreslugaresatencions() {
		return this.prestadoreslugaresatencions;
	}

	public void setPrestadoreslugaresatencions(
			Set<Prestadoreslugaresatencion> prestadoreslugaresatencions) {
		this.prestadoreslugaresatencions = prestadoreslugaresatencions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugaresdeatencion")
	public Set<Atencionmedica> getAtencionmedicas() {
		return this.atencionmedicas;
	}

	public void setAtencionmedicas(Set<Atencionmedica> atencionmedicas) {
		this.atencionmedicas = atencionmedicas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugaresdeatencion")
	public Set<Solicitudreferencia> getSolicitudreferencias() {
		return this.solicitudreferencias;
	}

	public void setSolicitudreferencias(
			Set<Solicitudreferencia> solicitudreferencias) {
		this.solicitudreferencias = solicitudreferencias;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugaresdeatencion")
	public Set<Convenios> getConvenioses() {
		return this.convenioses;
	}

	public void setConvenioses(Set<Convenios> convenioses) {
		this.convenioses = convenioses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugaresdeatencion")
	public Set<Permisoespecial> getPermisoespecials() {
		return this.permisoespecials;
	}

	public void setPermisoespecials(Set<Permisoespecial> permisoespecials) {
		this.permisoespecials = permisoespecials;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugaresdeatencion")
	public Set<Lugaresdeatencionroles> getLugaresdeatencionroleses() {
		return this.lugaresdeatencionroleses;
	}

	public void setLugaresdeatencionroleses(
			Set<Lugaresdeatencionroles> lugaresdeatencionroleses) {
		this.lugaresdeatencionroleses = lugaresdeatencionroleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugaresdeatencion")
	public Set<Agenda> getAgendas() {
		return this.agendas;
	}

	public void setAgendas(Set<Agenda> agendas) {
		this.agendas = agendas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugaresdeatencion")
	public Set<Usuariolugaratencion> getUsuariolugaratencions() {
		return this.usuariolugaratencions;
	}

	public void setUsuariolugaratencions(
			Set<Usuariolugaratencion> usuariolugaratencions) {
		this.usuariolugaratencions = usuariolugaratencions;
	}

}