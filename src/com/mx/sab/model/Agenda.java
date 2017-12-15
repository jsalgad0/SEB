package com.mx.sab.model;
// default package

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Agenda entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agenda", catalog = "my_db_rrg")
public class Agenda implements java.io.Serializable {

	// Fields

	private Integer agendaId;
	private Prestadores prestadores;
	private Lugaresdeatencion lugaresdeatencion;
	private Catestatuscitas catestatuscitas;
	private Aseguradores aseguradores;
	private Cattipoidentificador cattipoidentificador;
	private Catagendadopor catagendadopor;
	private Usuarios usuarios;
	private Atencionmedica atencionmedica;
	private Afiliado afiliado;
	private String consultorio;
	private Date fechaCita;
	private Time horaCita;
	private Integer asistio;
	private String claveCita;
	private String consultorioOriginal;
	private String prestacion;

	// Constructors

	/** default constructor */
	public Agenda() {
	}

	/** minimal constructor */
	public Agenda(Prestadores prestadores, Lugaresdeatencion lugaresdeatencion,
			Aseguradores aseguradores,
			Cattipoidentificador cattipoidentificador, Date fechaCita,
			Integer asistio, String prestacion) {
		this.prestadores = prestadores;
		this.lugaresdeatencion = lugaresdeatencion;
		this.aseguradores = aseguradores;
		this.cattipoidentificador = cattipoidentificador;
		this.fechaCita = fechaCita;
		this.asistio = asistio;
		this.prestacion = prestacion;
	}

	/** full constructor */
	public Agenda(Prestadores prestadores, Lugaresdeatencion lugaresdeatencion,
			Catestatuscitas catestatuscitas, Aseguradores aseguradores,
			Cattipoidentificador cattipoidentificador,
			Catagendadopor catagendadopor, Usuarios usuarios,
			Atencionmedica atencionmedica, Afiliado afiliado,
			String consultorio, Date fechaCita, Time horaCita, Integer asistio,
			String claveCita, String consultorioOriginal, String prestacion) {
		this.prestadores = prestadores;
		this.lugaresdeatencion = lugaresdeatencion;
		this.catestatuscitas = catestatuscitas;
		this.aseguradores = aseguradores;
		this.cattipoidentificador = cattipoidentificador;
		this.catagendadopor = catagendadopor;
		this.usuarios = usuarios;
		this.atencionmedica = atencionmedica;
		this.afiliado = afiliado;
		this.consultorio = consultorio;
		this.fechaCita = fechaCita;
		this.horaCita = horaCita;
		this.asistio = asistio;
		this.claveCita = claveCita;
		this.consultorioOriginal = consultorioOriginal;
		this.prestacion = prestacion;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AgendaId", unique = true, nullable = false)
	public Integer getAgendaId() {
		return this.agendaId;
	}

	public void setAgendaId(Integer agendaId) {
		this.agendaId = agendaId;
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
	@JoinColumn(name = "EstatusCitaId")
	public Catestatuscitas getCatestatuscitas() {
		return this.catestatuscitas;
	}

	public void setCatestatuscitas(Catestatuscitas catestatuscitas) {
		this.catestatuscitas = catestatuscitas;
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
	@JoinColumn(name = "TipoIdentificadorId", nullable = false)
	public Cattipoidentificador getCattipoidentificador() {
		return this.cattipoidentificador;
	}

	public void setCattipoidentificador(
			Cattipoidentificador cattipoidentificador) {
		this.cattipoidentificador = cattipoidentificador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AgendadoPorId")
	public Catagendadopor getCatagendadopor() {
		return this.catagendadopor;
	}

	public void setCatagendadopor(Catagendadopor catagendadopor) {
		this.catagendadopor = catagendadopor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioMedicoId")
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AtencionMedicaId")
	public Atencionmedica getAtencionmedica() {
		return this.atencionmedica;
	}

	public void setAtencionmedica(Atencionmedica atencionmedica) {
		this.atencionmedica = atencionmedica;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AfiliadoId")
	public Afiliado getAfiliado() {
		return this.afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	@Column(name = "Consultorio", length = 50)
	public String getConsultorio() {
		return this.consultorio;
	}

	public void setConsultorio(String consultorio) {
		this.consultorio = consultorio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaCita", nullable = false, length = 10)
	public Date getFechaCita() {
		return this.fechaCita;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}

	@Column(name = "HoraCita", length = 8)
	public Time getHoraCita() {
		return this.horaCita;
	}

	public void setHoraCita(Time horaCita) {
		this.horaCita = horaCita;
	}

	@Column(name = "Asistio", nullable = false)
	public Integer getAsistio() {
		return this.asistio;
	}

	public void setAsistio(Integer asistio) {
		this.asistio = asistio;
	}

	@Column(name = "ClaveCita", length = 10)
	public String getClaveCita() {
		return this.claveCita;
	}

	public void setClaveCita(String claveCita) {
		this.claveCita = claveCita;
	}

	@Column(name = "ConsultorioOriginal", length = 3)
	public String getConsultorioOriginal() {
		return this.consultorioOriginal;
	}

	public void setConsultorioOriginal(String consultorioOriginal) {
		this.consultorioOriginal = consultorioOriginal;
	}

	@Column(name = "Prestacion", nullable = false, length = 100)
	public String getPrestacion() {
		return this.prestacion;
	}

	public void setPrestacion(String prestacion) {
		this.prestacion = prestacion;
	}

}