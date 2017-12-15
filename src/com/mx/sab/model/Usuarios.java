package com.mx.sab.model;
// default package

import java.sql.Timestamp;
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
 * Usuarios entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuarios", catalog = "my_db_rrg")
public class Usuarios implements java.io.Serializable {

	// Fields

	private Integer usuarioId;
	private Catsexos catsexos;
	private Catestados catestados;
	private Catestatususuario catestatususuario;
	private Catpreguntasecreta catpreguntasecreta;
	private String nombreUsuario;
	private String psswd;
	private String rfc;
	private String claveUsuario;
	private Date fechaDeNacimiento;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String curp;
	private String mail;
	private Integer autorizacionEspecial;
	private Integer usuarioQueDioAlta;
	private Integer activo;
	private String tokenActivacion;
	private String telefono;
	private Timestamp fechaPsswdTemp;
	private String respuestaSecreta;
	private Integer intentosClave;
	private Integer intentosRespuesta;
	private String regSs;
	private String codAutentia;
	private Set<Usuariomenu> usuariomenus = new HashSet<Usuariomenu>(0);
	private Set<Usuariotipoidentificador> usuariotipoidentificadors = new HashSet<Usuariotipoidentificador>(
			0);
	private Set<Permisoespecial> permisoespecialsForUsuarioId = new HashSet<Permisoespecial>(
			0);
	private Set<Usuariorol> usuariorols = new HashSet<Usuariorol>(0);
	private Set<Notamedica> notamedicas = new HashSet<Notamedica>(0);
	private Set<Permisoespecial> permisoespecialsForUsuarioAutorizaId = new HashSet<Permisoespecial>(
			0);
	private Set<Permisoespecial> permisoespecialsForUsuarioSolicitaId = new HashSet<Permisoespecial>(
			0);
	private Set<Medicohorario> medicohorarios = new HashSet<Medicohorario>(0);
	private Set<Usuarioauditoria> usuarioauditoriasForUsuarioIdModificado = new HashSet<Usuarioauditoria>(
			0);
	private Set<Atencionmedica> atencionmedicasForUsuarioMedicoId = new HashSet<Atencionmedica>(
			0);
	private Set<Usuariolugaratencion> usuariolugaratencions = new HashSet<Usuariolugaratencion>(
			0);
	private Set<Usuarioespecialidades> usuarioespecialidadeses = new HashSet<Usuarioespecialidades>(
			0);
	private Set<Agenda> agendas = new HashSet<Agenda>(0);
	private Set<Historiaclinica> historiaclinicas = new HashSet<Historiaclinica>(
			0);
	private Set<Signosvitales> signosvitaleses = new HashSet<Signosvitales>(0);
	private Set<Usuarioasegurador> usuarioaseguradors = new HashSet<Usuarioasegurador>(
			0);
	private Set<Loginlog> loginlogs = new HashSet<Loginlog>(0);
	private Set<Usuariovigencialugaratencion> usuariovigencialugaratencions = new HashSet<Usuariovigencialugaratencion>(
			0);
	private Set<Usuarioauditoria> usuarioauditoriasForUsuarioId = new HashSet<Usuarioauditoria>(
			0);
	private Set<Atencionmedica> atencionmedicasForUsuarioRecibioId = new HashSet<Atencionmedica>(
			0);

	// Constructors

	/** default constructor */
	public Usuarios() {
	}

	/** minimal constructor */
	public Usuarios(Catsexos catsexos, Date fechaDeNacimiento, String nombre,
			String apellidoPaterno, String apellidoMaterno, Integer activo) {
		this.catsexos = catsexos;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.activo = activo;
	}

	/** full constructor */
	public Usuarios(Catsexos catsexos, Catestados catestados,
			Catestatususuario catestatususuario,
			Catpreguntasecreta catpreguntasecreta, String nombreUsuario,
			String psswd, String rfc, String claveUsuario,
			Date fechaDeNacimiento, String nombre, String apellidoPaterno,
			String apellidoMaterno, String curp, String mail,
			Integer autorizacionEspecial, Integer usuarioQueDioAlta,
			Integer activo, String tokenActivacion, String telefono,
			Timestamp fechaPsswdTemp, String respuestaSecreta,
			Integer intentosClave, Integer intentosRespuesta, String regSs,
			String codAutentia, Set<Usuariomenu> usuariomenus,
			Set<Usuariotipoidentificador> usuariotipoidentificadors,
			Set<Permisoespecial> permisoespecialsForUsuarioId,
			Set<Usuariorol> usuariorols, Set<Notamedica> notamedicas,
			Set<Permisoespecial> permisoespecialsForUsuarioAutorizaId,
			Set<Permisoespecial> permisoespecialsForUsuarioSolicitaId,
			Set<Medicohorario> medicohorarios,
			Set<Usuarioauditoria> usuarioauditoriasForUsuarioIdModificado,
			Set<Atencionmedica> atencionmedicasForUsuarioMedicoId,
			Set<Usuariolugaratencion> usuariolugaratencions,
			Set<Usuarioespecialidades> usuarioespecialidadeses,
			Set<Agenda> agendas, Set<Historiaclinica> historiaclinicas,
			Set<Signosvitales> signosvitaleses,
			Set<Usuarioasegurador> usuarioaseguradors, Set<Loginlog> loginlogs,
			Set<Usuariovigencialugaratencion> usuariovigencialugaratencions,
			Set<Usuarioauditoria> usuarioauditoriasForUsuarioId,
			Set<Atencionmedica> atencionmedicasForUsuarioRecibioId) {
		this.catsexos = catsexos;
		this.catestados = catestados;
		this.catestatususuario = catestatususuario;
		this.catpreguntasecreta = catpreguntasecreta;
		this.nombreUsuario = nombreUsuario;
		this.psswd = psswd;
		this.rfc = rfc;
		this.claveUsuario = claveUsuario;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.curp = curp;
		this.mail = mail;
		this.autorizacionEspecial = autorizacionEspecial;
		this.usuarioQueDioAlta = usuarioQueDioAlta;
		this.activo = activo;
		this.tokenActivacion = tokenActivacion;
		this.telefono = telefono;
		this.fechaPsswdTemp = fechaPsswdTemp;
		this.respuestaSecreta = respuestaSecreta;
		this.intentosClave = intentosClave;
		this.intentosRespuesta = intentosRespuesta;
		this.regSs = regSs;
		this.codAutentia = codAutentia;
		this.usuariomenus = usuariomenus;
		this.usuariotipoidentificadors = usuariotipoidentificadors;
		this.permisoespecialsForUsuarioId = permisoespecialsForUsuarioId;
		this.usuariorols = usuariorols;
		this.notamedicas = notamedicas;
		this.permisoespecialsForUsuarioAutorizaId = permisoespecialsForUsuarioAutorizaId;
		this.permisoespecialsForUsuarioSolicitaId = permisoespecialsForUsuarioSolicitaId;
		this.medicohorarios = medicohorarios;
		this.usuarioauditoriasForUsuarioIdModificado = usuarioauditoriasForUsuarioIdModificado;
		this.atencionmedicasForUsuarioMedicoId = atencionmedicasForUsuarioMedicoId;
		this.usuariolugaratencions = usuariolugaratencions;
		this.usuarioespecialidadeses = usuarioespecialidadeses;
		this.agendas = agendas;
		this.historiaclinicas = historiaclinicas;
		this.signosvitaleses = signosvitaleses;
		this.usuarioaseguradors = usuarioaseguradors;
		this.loginlogs = loginlogs;
		this.usuariovigencialugaratencions = usuariovigencialugaratencions;
		this.usuarioauditoriasForUsuarioId = usuarioauditoriasForUsuarioId;
		this.atencionmedicasForUsuarioRecibioId = atencionmedicasForUsuarioRecibioId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "UsuarioId", unique = true, nullable = false)
	public Integer getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
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
	@JoinColumn(name = "EstadoId")
	public Catestados getCatestados() {
		return this.catestados;
	}

	public void setCatestados(Catestados catestados) {
		this.catestados = catestados;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstatusUsuarioId")
	public Catestatususuario getCatestatususuario() {
		return this.catestatususuario;
	}

	public void setCatestatususuario(Catestatususuario catestatususuario) {
		this.catestatususuario = catestatususuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PreguntaSecretaId")
	public Catpreguntasecreta getCatpreguntasecreta() {
		return this.catpreguntasecreta;
	}

	public void setCatpreguntasecreta(Catpreguntasecreta catpreguntasecreta) {
		this.catpreguntasecreta = catpreguntasecreta;
	}

	@Column(name = "NombreUsuario", length = 60)
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Column(name = "Psswd", length = 45)
	public String getPsswd() {
		return this.psswd;
	}

	public void setPsswd(String psswd) {
		this.psswd = psswd;
	}

	@Column(name = "RFC", length = 45)
	public String getRfc() {
		return this.rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	@Column(name = "ClaveUsuario")
	public String getClaveUsuario() {
		return this.claveUsuario;
	}

	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaDeNacimiento", nullable = false, length = 10)
	public Date getFechaDeNacimiento() {
		return this.fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	@Column(name = "Nombre", nullable = false, length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "ApellidoPaterno", nullable = false, length = 45)
	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	@Column(name = "ApellidoMaterno", nullable = false, length = 45)
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	@Column(name = "curp", length = 45)
	public String getCurp() {
		return this.curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	@Column(name = "Mail", length = 100)
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column(name = "AutorizacionEspecial")
	public Integer getAutorizacionEspecial() {
		return this.autorizacionEspecial;
	}

	public void setAutorizacionEspecial(Integer autorizacionEspecial) {
		this.autorizacionEspecial = autorizacionEspecial;
	}

	@Column(name = "UsuarioQueDioAlta")
	public Integer getUsuarioQueDioAlta() {
		return this.usuarioQueDioAlta;
	}

	public void setUsuarioQueDioAlta(Integer usuarioQueDioAlta) {
		this.usuarioQueDioAlta = usuarioQueDioAlta;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@Column(name = "TokenActivacion", length = 100)
	public String getTokenActivacion() {
		return this.tokenActivacion;
	}

	public void setTokenActivacion(String tokenActivacion) {
		this.tokenActivacion = tokenActivacion;
	}

	@Column(name = "Telefono", length = 20)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "FechaPsswdTemp", length = 19)
	public Timestamp getFechaPsswdTemp() {
		return this.fechaPsswdTemp;
	}

	public void setFechaPsswdTemp(Timestamp fechaPsswdTemp) {
		this.fechaPsswdTemp = fechaPsswdTemp;
	}

	@Column(name = "RespuestaSecreta", length = 100)
	public String getRespuestaSecreta() {
		return this.respuestaSecreta;
	}

	public void setRespuestaSecreta(String respuestaSecreta) {
		this.respuestaSecreta = respuestaSecreta;
	}

	@Column(name = "IntentosClave")
	public Integer getIntentosClave() {
		return this.intentosClave;
	}

	public void setIntentosClave(Integer intentosClave) {
		this.intentosClave = intentosClave;
	}

	@Column(name = "IntentosRespuesta")
	public Integer getIntentosRespuesta() {
		return this.intentosRespuesta;
	}

	public void setIntentosRespuesta(Integer intentosRespuesta) {
		this.intentosRespuesta = intentosRespuesta;
	}

	@Column(name = "RegSS", length = 50)
	public String getRegSs() {
		return this.regSs;
	}

	public void setRegSs(String regSs) {
		this.regSs = regSs;
	}

	@Column(name = "CodAutentia", length = 50)
	public String getCodAutentia() {
		return this.codAutentia;
	}

	public void setCodAutentia(String codAutentia) {
		this.codAutentia = codAutentia;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarios")
	public Set<Usuariomenu> getUsuariomenus() {
		return this.usuariomenus;
	}

	public void setUsuariomenus(Set<Usuariomenu> usuariomenus) {
		this.usuariomenus = usuariomenus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarios")
	public Set<Usuariotipoidentificador> getUsuariotipoidentificadors() {
		return this.usuariotipoidentificadors;
	}

	public void setUsuariotipoidentificadors(
			Set<Usuariotipoidentificador> usuariotipoidentificadors) {
		this.usuariotipoidentificadors = usuariotipoidentificadors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuariosByUsuarioId")
	public Set<Permisoespecial> getPermisoespecialsForUsuarioId() {
		return this.permisoespecialsForUsuarioId;
	}

	public void setPermisoespecialsForUsuarioId(
			Set<Permisoespecial> permisoespecialsForUsuarioId) {
		this.permisoespecialsForUsuarioId = permisoespecialsForUsuarioId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarios")
	public Set<Usuariorol> getUsuariorols() {
		return this.usuariorols;
	}

	public void setUsuariorols(Set<Usuariorol> usuariorols) {
		this.usuariorols = usuariorols;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarios")
	public Set<Notamedica> getNotamedicas() {
		return this.notamedicas;
	}

	public void setNotamedicas(Set<Notamedica> notamedicas) {
		this.notamedicas = notamedicas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuariosByUsuarioAutorizaId")
	public Set<Permisoespecial> getPermisoespecialsForUsuarioAutorizaId() {
		return this.permisoespecialsForUsuarioAutorizaId;
	}

	public void setPermisoespecialsForUsuarioAutorizaId(
			Set<Permisoespecial> permisoespecialsForUsuarioAutorizaId) {
		this.permisoespecialsForUsuarioAutorizaId = permisoespecialsForUsuarioAutorizaId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuariosByUsuarioSolicitaId")
	public Set<Permisoespecial> getPermisoespecialsForUsuarioSolicitaId() {
		return this.permisoespecialsForUsuarioSolicitaId;
	}

	public void setPermisoespecialsForUsuarioSolicitaId(
			Set<Permisoespecial> permisoespecialsForUsuarioSolicitaId) {
		this.permisoespecialsForUsuarioSolicitaId = permisoespecialsForUsuarioSolicitaId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarios")
	public Set<Medicohorario> getMedicohorarios() {
		return this.medicohorarios;
	}

	public void setMedicohorarios(Set<Medicohorario> medicohorarios) {
		this.medicohorarios = medicohorarios;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuariosByUsuarioIdModificado")
	public Set<Usuarioauditoria> getUsuarioauditoriasForUsuarioIdModificado() {
		return this.usuarioauditoriasForUsuarioIdModificado;
	}

	public void setUsuarioauditoriasForUsuarioIdModificado(
			Set<Usuarioauditoria> usuarioauditoriasForUsuarioIdModificado) {
		this.usuarioauditoriasForUsuarioIdModificado = usuarioauditoriasForUsuarioIdModificado;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuariosByUsuarioMedicoId")
	public Set<Atencionmedica> getAtencionmedicasForUsuarioMedicoId() {
		return this.atencionmedicasForUsuarioMedicoId;
	}

	public void setAtencionmedicasForUsuarioMedicoId(
			Set<Atencionmedica> atencionmedicasForUsuarioMedicoId) {
		this.atencionmedicasForUsuarioMedicoId = atencionmedicasForUsuarioMedicoId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarios")
	public Set<Usuariolugaratencion> getUsuariolugaratencions() {
		return this.usuariolugaratencions;
	}

	public void setUsuariolugaratencions(
			Set<Usuariolugaratencion> usuariolugaratencions) {
		this.usuariolugaratencions = usuariolugaratencions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarios")
	public Set<Usuarioespecialidades> getUsuarioespecialidadeses() {
		return this.usuarioespecialidadeses;
	}

	public void setUsuarioespecialidadeses(
			Set<Usuarioespecialidades> usuarioespecialidadeses) {
		this.usuarioespecialidadeses = usuarioespecialidadeses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarios")
	public Set<Agenda> getAgendas() {
		return this.agendas;
	}

	public void setAgendas(Set<Agenda> agendas) {
		this.agendas = agendas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarios")
	public Set<Historiaclinica> getHistoriaclinicas() {
		return this.historiaclinicas;
	}

	public void setHistoriaclinicas(Set<Historiaclinica> historiaclinicas) {
		this.historiaclinicas = historiaclinicas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarios")
	public Set<Signosvitales> getSignosvitaleses() {
		return this.signosvitaleses;
	}

	public void setSignosvitaleses(Set<Signosvitales> signosvitaleses) {
		this.signosvitaleses = signosvitaleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarios")
	public Set<Usuarioasegurador> getUsuarioaseguradors() {
		return this.usuarioaseguradors;
	}

	public void setUsuarioaseguradors(Set<Usuarioasegurador> usuarioaseguradors) {
		this.usuarioaseguradors = usuarioaseguradors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarios")
	public Set<Loginlog> getLoginlogs() {
		return this.loginlogs;
	}

	public void setLoginlogs(Set<Loginlog> loginlogs) {
		this.loginlogs = loginlogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarios")
	public Set<Usuariovigencialugaratencion> getUsuariovigencialugaratencions() {
		return this.usuariovigencialugaratencions;
	}

	public void setUsuariovigencialugaratencions(
			Set<Usuariovigencialugaratencion> usuariovigencialugaratencions) {
		this.usuariovigencialugaratencions = usuariovigencialugaratencions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuariosByUsuarioId")
	public Set<Usuarioauditoria> getUsuarioauditoriasForUsuarioId() {
		return this.usuarioauditoriasForUsuarioId;
	}

	public void setUsuarioauditoriasForUsuarioId(
			Set<Usuarioauditoria> usuarioauditoriasForUsuarioId) {
		this.usuarioauditoriasForUsuarioId = usuarioauditoriasForUsuarioId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuariosByUsuarioRecibioId")
	public Set<Atencionmedica> getAtencionmedicasForUsuarioRecibioId() {
		return this.atencionmedicasForUsuarioRecibioId;
	}

	public void setAtencionmedicasForUsuarioRecibioId(
			Set<Atencionmedica> atencionmedicasForUsuarioRecibioId) {
		this.atencionmedicasForUsuarioRecibioId = atencionmedicasForUsuarioRecibioId;
	}

}