package com.mx.sab.model;
// default package

import java.sql.Time;
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
 * Atencionmedica entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "atencionmedica", catalog = "my_db_rrg")
public class Atencionmedica implements java.io.Serializable {

	// Fields

	private Integer atencionMedicaId;
	private Aseguradores aseguradores;
	private Catestatuscitas catestatuscitas;
	private Catestatusrecepcion catestatusrecepcion;
	private Personasdeconfianza personasdeconfianza;
	private Tipoatencionmedica tipoatencionmedica;
	private Convenios convenios;
	private Catestatusatencionvigencia catestatusatencionvigencia;
	private Prestadores prestadores;
	private Lugaresdeatencion lugaresdeatencion;
	private Catestatusfirma catestatusfirmaByEstatusFirmaPaciente;
	private Cattipoidentificador cattipoidentificador;
	private Catagendadopor catagendadopor;
	private Usuarios usuariosByUsuarioMedicoId;
	private Auditoriaautentia auditoriaautentiaByAuditoriaAutentiaPacienteId;
	private Catestatusfirma catestatusfirmaByEstatusFirmaMedico;
	private Auditoriaautentia auditoriaautentiaByAuditoriaAutentiaMedicoId;
	private Usuarios usuariosByUsuarioRecibioId;
	private Catestatusatencionidentidad catestatusatencionidentidad;
	private Afiliado afiliado;
	private Date fechaAsistio;
	private Time horaAsistio;
	private Integer autorizEspMostroDoc;
	private Integer lugarSinHuellaMostroDoc;
	private Date fechaAtendidoEnfermeria;
	private Time horaAtendidoEnfermeria;
	private Time horaInicioConsulta;
	private Date fechaAtendidoMedico;
	private Time horaAtendidoMedico;
	private Integer enrolamiento;
	private String folio;
	private Set<Licenciamedica> licenciamedicas = new HashSet<Licenciamedica>(0);
	private Set<Prestacionesportomar> prestacionesportomarsForAtencionMedicaId = new HashSet<Prestacionesportomar>(
			0);
	private Set<Motivos> motivoses = new HashSet<Motivos>(0);
	private Set<Prestacionesportomar> prestacionesportomarsForAtencionMedicaIdTomada = new HashSet<Prestacionesportomar>(
			0);
	private Set<Solicitudreferencia> solicitudreferencias = new HashSet<Solicitudreferencia>(
			0);
	private Set<Signosvitales> signosvitaleses = new HashSet<Signosvitales>(0);
	private Set<Recetas> recetases = new HashSet<Recetas>(0);
	private Set<Notamedica> notamedicas = new HashSet<Notamedica>(0);
	private Set<Agenda> agendas = new HashSet<Agenda>(0);
	private Set<Atencionprestacion> atencionprestacions = new HashSet<Atencionprestacion>(
			0);

	// Constructors

	/** default constructor */
	public Atencionmedica() {
	}

	/** minimal constructor */
	public Atencionmedica(Aseguradores aseguradores,
			Catestatuscitas catestatuscitas, Convenios convenios,
			Prestadores prestadores, Lugaresdeatencion lugaresdeatencion,
			Cattipoidentificador cattipoidentificador,
			Catagendadopor catagendadopor, Afiliado afiliado) {
		this.aseguradores = aseguradores;
		this.catestatuscitas = catestatuscitas;
		this.convenios = convenios;
		this.prestadores = prestadores;
		this.lugaresdeatencion = lugaresdeatencion;
		this.cattipoidentificador = cattipoidentificador;
		this.catagendadopor = catagendadopor;
		this.afiliado = afiliado;
	}

	/** full constructor */
	public Atencionmedica(
			Aseguradores aseguradores,
			Catestatuscitas catestatuscitas,
			Catestatusrecepcion catestatusrecepcion,
			Personasdeconfianza personasdeconfianza,
			Tipoatencionmedica tipoatencionmedica,
			Convenios convenios,
			Catestatusatencionvigencia catestatusatencionvigencia,
			Prestadores prestadores,
			Lugaresdeatencion lugaresdeatencion,
			Catestatusfirma catestatusfirmaByEstatusFirmaPaciente,
			Cattipoidentificador cattipoidentificador,
			Catagendadopor catagendadopor,
			Usuarios usuariosByUsuarioMedicoId,
			Auditoriaautentia auditoriaautentiaByAuditoriaAutentiaPacienteId,
			Catestatusfirma catestatusfirmaByEstatusFirmaMedico,
			Auditoriaautentia auditoriaautentiaByAuditoriaAutentiaMedicoId,
			Usuarios usuariosByUsuarioRecibioId,
			Catestatusatencionidentidad catestatusatencionidentidad,
			Afiliado afiliado,
			Date fechaAsistio,
			Time horaAsistio,
			Integer autorizEspMostroDoc,
			Integer lugarSinHuellaMostroDoc,
			Date fechaAtendidoEnfermeria,
			Time horaAtendidoEnfermeria,
			Time horaInicioConsulta,
			Date fechaAtendidoMedico,
			Time horaAtendidoMedico,
			Integer enrolamiento,
			String folio,
			Set<Licenciamedica> licenciamedicas,
			Set<Prestacionesportomar> prestacionesportomarsForAtencionMedicaId,
			Set<Motivos> motivoses,
			Set<Prestacionesportomar> prestacionesportomarsForAtencionMedicaIdTomada,
			Set<Solicitudreferencia> solicitudreferencias,
			Set<Signosvitales> signosvitaleses, Set<Recetas> recetases,
			Set<Notamedica> notamedicas, Set<Agenda> agendas,
			Set<Atencionprestacion> atencionprestacions) {
		this.aseguradores = aseguradores;
		this.catestatuscitas = catestatuscitas;
		this.catestatusrecepcion = catestatusrecepcion;
		this.personasdeconfianza = personasdeconfianza;
		this.tipoatencionmedica = tipoatencionmedica;
		this.convenios = convenios;
		this.catestatusatencionvigencia = catestatusatencionvigencia;
		this.prestadores = prestadores;
		this.lugaresdeatencion = lugaresdeatencion;
		this.catestatusfirmaByEstatusFirmaPaciente = catestatusfirmaByEstatusFirmaPaciente;
		this.cattipoidentificador = cattipoidentificador;
		this.catagendadopor = catagendadopor;
		this.usuariosByUsuarioMedicoId = usuariosByUsuarioMedicoId;
		this.auditoriaautentiaByAuditoriaAutentiaPacienteId = auditoriaautentiaByAuditoriaAutentiaPacienteId;
		this.catestatusfirmaByEstatusFirmaMedico = catestatusfirmaByEstatusFirmaMedico;
		this.auditoriaautentiaByAuditoriaAutentiaMedicoId = auditoriaautentiaByAuditoriaAutentiaMedicoId;
		this.usuariosByUsuarioRecibioId = usuariosByUsuarioRecibioId;
		this.catestatusatencionidentidad = catestatusatencionidentidad;
		this.afiliado = afiliado;
		this.fechaAsistio = fechaAsistio;
		this.horaAsistio = horaAsistio;
		this.autorizEspMostroDoc = autorizEspMostroDoc;
		this.lugarSinHuellaMostroDoc = lugarSinHuellaMostroDoc;
		this.fechaAtendidoEnfermeria = fechaAtendidoEnfermeria;
		this.horaAtendidoEnfermeria = horaAtendidoEnfermeria;
		this.horaInicioConsulta = horaInicioConsulta;
		this.fechaAtendidoMedico = fechaAtendidoMedico;
		this.horaAtendidoMedico = horaAtendidoMedico;
		this.enrolamiento = enrolamiento;
		this.folio = folio;
		this.licenciamedicas = licenciamedicas;
		this.prestacionesportomarsForAtencionMedicaId = prestacionesportomarsForAtencionMedicaId;
		this.motivoses = motivoses;
		this.prestacionesportomarsForAtencionMedicaIdTomada = prestacionesportomarsForAtencionMedicaIdTomada;
		this.solicitudreferencias = solicitudreferencias;
		this.signosvitaleses = signosvitaleses;
		this.recetases = recetases;
		this.notamedicas = notamedicas;
		this.agendas = agendas;
		this.atencionprestacions = atencionprestacions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AtencionMedicaId", unique = true, nullable = false)
	public Integer getAtencionMedicaId() {
		return this.atencionMedicaId;
	}

	public void setAtencionMedicaId(Integer atencionMedicaId) {
		this.atencionMedicaId = atencionMedicaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AseguradorId", nullable = false)
	public Aseguradores getAseguradores() {
		return this.aseguradores;
	}

	public void setAseguradores(Aseguradores aseguradores) {
		this.aseguradores = aseguradores;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstatusCitaId", nullable = false)
	public Catestatuscitas getCatestatuscitas() {
		return this.catestatuscitas;
	}

	public void setCatestatuscitas(Catestatuscitas catestatuscitas) {
		this.catestatuscitas = catestatuscitas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstatusRecepcionId")
	public Catestatusrecepcion getCatestatusrecepcion() {
		return this.catestatusrecepcion;
	}

	public void setCatestatusrecepcion(Catestatusrecepcion catestatusrecepcion) {
		this.catestatusrecepcion = catestatusrecepcion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PersonaConfianzaId")
	public Personasdeconfianza getPersonasdeconfianza() {
		return this.personasdeconfianza;
	}

	public void setPersonasdeconfianza(Personasdeconfianza personasdeconfianza) {
		this.personasdeconfianza = personasdeconfianza;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoAtencionMedicaId")
	public Tipoatencionmedica getTipoatencionmedica() {
		return this.tipoatencionmedica;
	}

	public void setTipoatencionmedica(Tipoatencionmedica tipoatencionmedica) {
		this.tipoatencionmedica = tipoatencionmedica;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ConvenioId", nullable = false)
	public Convenios getConvenios() {
		return this.convenios;
	}

	public void setConvenios(Convenios convenios) {
		this.convenios = convenios;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstatusVigenciaId")
	public Catestatusatencionvigencia getCatestatusatencionvigencia() {
		return this.catestatusatencionvigencia;
	}

	public void setCatestatusatencionvigencia(
			Catestatusatencionvigencia catestatusatencionvigencia) {
		this.catestatusatencionvigencia = catestatusatencionvigencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PrestadorId", nullable = false)
	public Prestadores getPrestadores() {
		return this.prestadores;
	}

	public void setPrestadores(Prestadores prestadores) {
		this.prestadores = prestadores;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LugarDeAtencionId", nullable = false)
	public Lugaresdeatencion getLugaresdeatencion() {
		return this.lugaresdeatencion;
	}

	public void setLugaresdeatencion(Lugaresdeatencion lugaresdeatencion) {
		this.lugaresdeatencion = lugaresdeatencion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstatusFirmaPaciente")
	public Catestatusfirma getCatestatusfirmaByEstatusFirmaPaciente() {
		return this.catestatusfirmaByEstatusFirmaPaciente;
	}

	public void setCatestatusfirmaByEstatusFirmaPaciente(
			Catestatusfirma catestatusfirmaByEstatusFirmaPaciente) {
		this.catestatusfirmaByEstatusFirmaPaciente = catestatusfirmaByEstatusFirmaPaciente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoIdentificadorId", nullable = false)
	public Cattipoidentificador getCattipoidentificador() {
		return this.cattipoidentificador;
	}

	public void setCattipoidentificador(
			Cattipoidentificador cattipoidentificador) {
		this.cattipoidentificador = cattipoidentificador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AgendadoPorId", nullable = false)
	public Catagendadopor getCatagendadopor() {
		return this.catagendadopor;
	}

	public void setCatagendadopor(Catagendadopor catagendadopor) {
		this.catagendadopor = catagendadopor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioMedicoId")
	public Usuarios getUsuariosByUsuarioMedicoId() {
		return this.usuariosByUsuarioMedicoId;
	}

	public void setUsuariosByUsuarioMedicoId(Usuarios usuariosByUsuarioMedicoId) {
		this.usuariosByUsuarioMedicoId = usuariosByUsuarioMedicoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AuditoriaAutentiaPacienteId")
	public Auditoriaautentia getAuditoriaautentiaByAuditoriaAutentiaPacienteId() {
		return this.auditoriaautentiaByAuditoriaAutentiaPacienteId;
	}

	public void setAuditoriaautentiaByAuditoriaAutentiaPacienteId(
			Auditoriaautentia auditoriaautentiaByAuditoriaAutentiaPacienteId) {
		this.auditoriaautentiaByAuditoriaAutentiaPacienteId = auditoriaautentiaByAuditoriaAutentiaPacienteId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstatusFirmaMedico")
	public Catestatusfirma getCatestatusfirmaByEstatusFirmaMedico() {
		return this.catestatusfirmaByEstatusFirmaMedico;
	}

	public void setCatestatusfirmaByEstatusFirmaMedico(
			Catestatusfirma catestatusfirmaByEstatusFirmaMedico) {
		this.catestatusfirmaByEstatusFirmaMedico = catestatusfirmaByEstatusFirmaMedico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AuditoriaAutentiaMedicoId")
	public Auditoriaautentia getAuditoriaautentiaByAuditoriaAutentiaMedicoId() {
		return this.auditoriaautentiaByAuditoriaAutentiaMedicoId;
	}

	public void setAuditoriaautentiaByAuditoriaAutentiaMedicoId(
			Auditoriaautentia auditoriaautentiaByAuditoriaAutentiaMedicoId) {
		this.auditoriaautentiaByAuditoriaAutentiaMedicoId = auditoriaautentiaByAuditoriaAutentiaMedicoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioRecibioId")
	public Usuarios getUsuariosByUsuarioRecibioId() {
		return this.usuariosByUsuarioRecibioId;
	}

	public void setUsuariosByUsuarioRecibioId(
			Usuarios usuariosByUsuarioRecibioId) {
		this.usuariosByUsuarioRecibioId = usuariosByUsuarioRecibioId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstatusIdentidadId")
	public Catestatusatencionidentidad getCatestatusatencionidentidad() {
		return this.catestatusatencionidentidad;
	}

	public void setCatestatusatencionidentidad(
			Catestatusatencionidentidad catestatusatencionidentidad) {
		this.catestatusatencionidentidad = catestatusatencionidentidad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AfiliadoId", nullable = false)
	public Afiliado getAfiliado() {
		return this.afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaAsistio", length = 10)
	public Date getFechaAsistio() {
		return this.fechaAsistio;
	}

	public void setFechaAsistio(Date fechaAsistio) {
		this.fechaAsistio = fechaAsistio;
	}

	@Column(name = "HoraAsistio", length = 8)
	public Time getHoraAsistio() {
		return this.horaAsistio;
	}

	public void setHoraAsistio(Time horaAsistio) {
		this.horaAsistio = horaAsistio;
	}

	@Column(name = "AutorizEspMostroDoc")
	public Integer getAutorizEspMostroDoc() {
		return this.autorizEspMostroDoc;
	}

	public void setAutorizEspMostroDoc(Integer autorizEspMostroDoc) {
		this.autorizEspMostroDoc = autorizEspMostroDoc;
	}

	@Column(name = "LugarSinHuellaMostroDoc")
	public Integer getLugarSinHuellaMostroDoc() {
		return this.lugarSinHuellaMostroDoc;
	}

	public void setLugarSinHuellaMostroDoc(Integer lugarSinHuellaMostroDoc) {
		this.lugarSinHuellaMostroDoc = lugarSinHuellaMostroDoc;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaAtendidoEnfermeria", length = 10)
	public Date getFechaAtendidoEnfermeria() {
		return this.fechaAtendidoEnfermeria;
	}

	public void setFechaAtendidoEnfermeria(Date fechaAtendidoEnfermeria) {
		this.fechaAtendidoEnfermeria = fechaAtendidoEnfermeria;
	}

	@Column(name = "HoraAtendidoEnfermeria", length = 8)
	public Time getHoraAtendidoEnfermeria() {
		return this.horaAtendidoEnfermeria;
	}

	public void setHoraAtendidoEnfermeria(Time horaAtendidoEnfermeria) {
		this.horaAtendidoEnfermeria = horaAtendidoEnfermeria;
	}

	@Column(name = "HoraInicioConsulta", length = 8)
	public Time getHoraInicioConsulta() {
		return this.horaInicioConsulta;
	}

	public void setHoraInicioConsulta(Time horaInicioConsulta) {
		this.horaInicioConsulta = horaInicioConsulta;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaAtendidoMedico", length = 10)
	public Date getFechaAtendidoMedico() {
		return this.fechaAtendidoMedico;
	}

	public void setFechaAtendidoMedico(Date fechaAtendidoMedico) {
		this.fechaAtendidoMedico = fechaAtendidoMedico;
	}

	@Column(name = "HoraAtendidoMedico", length = 8)
	public Time getHoraAtendidoMedico() {
		return this.horaAtendidoMedico;
	}

	public void setHoraAtendidoMedico(Time horaAtendidoMedico) {
		this.horaAtendidoMedico = horaAtendidoMedico;
	}

	@Column(name = "Enrolamiento")
	public Integer getEnrolamiento() {
		return this.enrolamiento;
	}

	public void setEnrolamiento(Integer enrolamiento) {
		this.enrolamiento = enrolamiento;
	}

	@Column(name = "Folio", length = 15)
	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "atencionmedica")
	public Set<Licenciamedica> getLicenciamedicas() {
		return this.licenciamedicas;
	}

	public void setLicenciamedicas(Set<Licenciamedica> licenciamedicas) {
		this.licenciamedicas = licenciamedicas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "atencionmedicaByAtencionMedicaId")
	public Set<Prestacionesportomar> getPrestacionesportomarsForAtencionMedicaId() {
		return this.prestacionesportomarsForAtencionMedicaId;
	}

	public void setPrestacionesportomarsForAtencionMedicaId(
			Set<Prestacionesportomar> prestacionesportomarsForAtencionMedicaId) {
		this.prestacionesportomarsForAtencionMedicaId = prestacionesportomarsForAtencionMedicaId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "atencionmedica")
	public Set<Motivos> getMotivoses() {
		return this.motivoses;
	}

	public void setMotivoses(Set<Motivos> motivoses) {
		this.motivoses = motivoses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "atencionmedicaByAtencionMedicaIdTomada")
	public Set<Prestacionesportomar> getPrestacionesportomarsForAtencionMedicaIdTomada() {
		return this.prestacionesportomarsForAtencionMedicaIdTomada;
	}

	public void setPrestacionesportomarsForAtencionMedicaIdTomada(
			Set<Prestacionesportomar> prestacionesportomarsForAtencionMedicaIdTomada) {
		this.prestacionesportomarsForAtencionMedicaIdTomada = prestacionesportomarsForAtencionMedicaIdTomada;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "atencionmedica")
	public Set<Solicitudreferencia> getSolicitudreferencias() {
		return this.solicitudreferencias;
	}

	public void setSolicitudreferencias(
			Set<Solicitudreferencia> solicitudreferencias) {
		this.solicitudreferencias = solicitudreferencias;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "atencionmedica")
	public Set<Signosvitales> getSignosvitaleses() {
		return this.signosvitaleses;
	}

	public void setSignosvitaleses(Set<Signosvitales> signosvitaleses) {
		this.signosvitaleses = signosvitaleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "atencionmedica")
	public Set<Recetas> getRecetases() {
		return this.recetases;
	}

	public void setRecetases(Set<Recetas> recetases) {
		this.recetases = recetases;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "atencionmedica")
	public Set<Notamedica> getNotamedicas() {
		return this.notamedicas;
	}

	public void setNotamedicas(Set<Notamedica> notamedicas) {
		this.notamedicas = notamedicas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "atencionmedica")
	public Set<Agenda> getAgendas() {
		return this.agendas;
	}

	public void setAgendas(Set<Agenda> agendas) {
		this.agendas = agendas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "atencionmedica")
	public Set<Atencionprestacion> getAtencionprestacions() {
		return this.atencionprestacions;
	}

	public void setAtencionprestacions(
			Set<Atencionprestacion> atencionprestacions) {
		this.atencionprestacions = atencionprestacions;
	}

}