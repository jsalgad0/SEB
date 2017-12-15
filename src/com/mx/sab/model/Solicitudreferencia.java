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
 * Solicitudreferencia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "solicitudreferencia", catalog = "my_db_rrg")
public class Solicitudreferencia implements java.io.Serializable {

	// Fields

	private Integer solicitudReferenciaId;
	private Lugaresdeatencion lugaresdeatencion;
	private Catsolirefrerenciapor catsolirefrerenciapor;
	private Catsolireferenciamotivos catsolireferenciamotivos;
	private Licenciamedica licenciamedica;
	private Atencionmedica atencionmedica;
	private Catespecialidadesmedicas catespecialidadesmedicas;
	private String motivoReferencia;
	private Integer numTrasladosEnAnio;
	private String motivoDelEnvio;
	private String resultadosLaboratorio;
	private String folio;
	private Date fecha;
	private Time hora;
	private Integer pendiente;
	private Set<Contrareferencia> contrareferencias = new HashSet<Contrareferencia>(
			0);

	// Constructors

	/** default constructor */
	public Solicitudreferencia() {
	}

	/** minimal constructor */
	public Solicitudreferencia(Lugaresdeatencion lugaresdeatencion,
			Catsolireferenciamotivos catsolireferenciamotivos,
			Atencionmedica atencionmedica,
			Catespecialidadesmedicas catespecialidadesmedicas,
			String motivoReferencia, Date fecha, Time hora) {
		this.lugaresdeatencion = lugaresdeatencion;
		this.catsolireferenciamotivos = catsolireferenciamotivos;
		this.atencionmedica = atencionmedica;
		this.catespecialidadesmedicas = catespecialidadesmedicas;
		this.motivoReferencia = motivoReferencia;
		this.fecha = fecha;
		this.hora = hora;
	}

	/** full constructor */
	public Solicitudreferencia(Lugaresdeatencion lugaresdeatencion,
			Catsolirefrerenciapor catsolirefrerenciapor,
			Catsolireferenciamotivos catsolireferenciamotivos,
			Licenciamedica licenciamedica, Atencionmedica atencionmedica,
			Catespecialidadesmedicas catespecialidadesmedicas,
			String motivoReferencia, Integer numTrasladosEnAnio,
			String motivoDelEnvio, String resultadosLaboratorio, String folio,
			Date fecha, Time hora, Integer pendiente,
			Set<Contrareferencia> contrareferencias) {
		this.lugaresdeatencion = lugaresdeatencion;
		this.catsolirefrerenciapor = catsolirefrerenciapor;
		this.catsolireferenciamotivos = catsolireferenciamotivos;
		this.licenciamedica = licenciamedica;
		this.atencionmedica = atencionmedica;
		this.catespecialidadesmedicas = catespecialidadesmedicas;
		this.motivoReferencia = motivoReferencia;
		this.numTrasladosEnAnio = numTrasladosEnAnio;
		this.motivoDelEnvio = motivoDelEnvio;
		this.resultadosLaboratorio = resultadosLaboratorio;
		this.folio = folio;
		this.fecha = fecha;
		this.hora = hora;
		this.pendiente = pendiente;
		this.contrareferencias = contrareferencias;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SolicitudReferenciaId", unique = true, nullable = false)
	public Integer getSolicitudReferenciaId() {
		return this.solicitudReferenciaId;
	}

	public void setSolicitudReferenciaId(Integer solicitudReferenciaId) {
		this.solicitudReferenciaId = solicitudReferenciaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UnidadMedicaId", nullable = false)
	public Lugaresdeatencion getLugaresdeatencion() {
		return this.lugaresdeatencion;
	}

	public void setLugaresdeatencion(Lugaresdeatencion lugaresdeatencion) {
		this.lugaresdeatencion = lugaresdeatencion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ReferenciaPorId")
	public Catsolirefrerenciapor getCatsolirefrerenciapor() {
		return this.catsolirefrerenciapor;
	}

	public void setCatsolirefrerenciapor(
			Catsolirefrerenciapor catsolirefrerenciapor) {
		this.catsolirefrerenciapor = catsolirefrerenciapor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MotivoId", nullable = false)
	public Catsolireferenciamotivos getCatsolireferenciamotivos() {
		return this.catsolireferenciamotivos;
	}

	public void setCatsolireferenciamotivos(
			Catsolireferenciamotivos catsolireferenciamotivos) {
		this.catsolireferenciamotivos = catsolireferenciamotivos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LicenciaMedicaId")
	public Licenciamedica getLicenciamedica() {
		return this.licenciamedica;
	}

	public void setLicenciamedica(Licenciamedica licenciamedica) {
		this.licenciamedica = licenciamedica;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AtencionMedicaId", nullable = false)
	public Atencionmedica getAtencionmedica() {
		return this.atencionmedica;
	}

	public void setAtencionmedica(Atencionmedica atencionmedica) {
		this.atencionmedica = atencionmedica;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EspecialidadId", nullable = false)
	public Catespecialidadesmedicas getCatespecialidadesmedicas() {
		return this.catespecialidadesmedicas;
	}

	public void setCatespecialidadesmedicas(
			Catespecialidadesmedicas catespecialidadesmedicas) {
		this.catespecialidadesmedicas = catespecialidadesmedicas;
	}

	@Column(name = "MotivoReferencia", nullable = false, length = 2000)
	public String getMotivoReferencia() {
		return this.motivoReferencia;
	}

	public void setMotivoReferencia(String motivoReferencia) {
		this.motivoReferencia = motivoReferencia;
	}

	@Column(name = "NumTrasladosEnAnio")
	public Integer getNumTrasladosEnAnio() {
		return this.numTrasladosEnAnio;
	}

	public void setNumTrasladosEnAnio(Integer numTrasladosEnAnio) {
		this.numTrasladosEnAnio = numTrasladosEnAnio;
	}

	@Column(name = "MotivoDelEnvio", length = 2000)
	public String getMotivoDelEnvio() {
		return this.motivoDelEnvio;
	}

	public void setMotivoDelEnvio(String motivoDelEnvio) {
		this.motivoDelEnvio = motivoDelEnvio;
	}

	@Column(name = "ResultadosLaboratorio", length = 2000)
	public String getResultadosLaboratorio() {
		return this.resultadosLaboratorio;
	}

	public void setResultadosLaboratorio(String resultadosLaboratorio) {
		this.resultadosLaboratorio = resultadosLaboratorio;
	}

	@Column(name = "Folio", length = 50)
	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Fecha", nullable = false, length = 10)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "Hora", nullable = false, length = 8)
	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	@Column(name = "Pendiente")
	public Integer getPendiente() {
		return this.pendiente;
	}

	public void setPendiente(Integer pendiente) {
		this.pendiente = pendiente;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "solicitudreferencia")
	public Set<Contrareferencia> getContrareferencias() {
		return this.contrareferencias;
	}

	public void setContrareferencias(Set<Contrareferencia> contrareferencias) {
		this.contrareferencias = contrareferencias;
	}

}