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
 * Licenciamedica entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "licenciamedica", catalog = "my_db_rrg")
public class Licenciamedica implements java.io.Serializable {

	// Fields

	private Integer licenciaMedicaId;
	private Catlicmedicamotivos catlicmedicamotivos;
	private Catlicmedicatiposservicio catlicmedicatiposservicio;
	private Atencionmedica atencionmedica;
	private Catlicmedicacaracteres catlicmedicacaracteres;
	private Date fechaEmision;
	private String folio;
	private Date fechaInicio;
	private Date fechaTermino;
	private Integer numDias;
	private String letraDias;
	private Set<Solicitudreferencia> solicitudreferencias = new HashSet<Solicitudreferencia>(
			0);

	// Constructors

	/** default constructor */
	public Licenciamedica() {
	}

	/** minimal constructor */
	public Licenciamedica(Catlicmedicamotivos catlicmedicamotivos,
			Catlicmedicatiposservicio catlicmedicatiposservicio,
			Atencionmedica atencionmedica,
			Catlicmedicacaracteres catlicmedicacaracteres, Date fechaEmision,
			String folio, Date fechaInicio, Date fechaTermino) {
		this.catlicmedicamotivos = catlicmedicamotivos;
		this.catlicmedicatiposservicio = catlicmedicatiposservicio;
		this.atencionmedica = atencionmedica;
		this.catlicmedicacaracteres = catlicmedicacaracteres;
		this.fechaEmision = fechaEmision;
		this.folio = folio;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
	}

	/** full constructor */
	public Licenciamedica(Catlicmedicamotivos catlicmedicamotivos,
			Catlicmedicatiposservicio catlicmedicatiposservicio,
			Atencionmedica atencionmedica,
			Catlicmedicacaracteres catlicmedicacaracteres, Date fechaEmision,
			String folio, Date fechaInicio, Date fechaTermino, Integer numDias,
			String letraDias, Set<Solicitudreferencia> solicitudreferencias) {
		this.catlicmedicamotivos = catlicmedicamotivos;
		this.catlicmedicatiposservicio = catlicmedicatiposservicio;
		this.atencionmedica = atencionmedica;
		this.catlicmedicacaracteres = catlicmedicacaracteres;
		this.fechaEmision = fechaEmision;
		this.folio = folio;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
		this.numDias = numDias;
		this.letraDias = letraDias;
		this.solicitudreferencias = solicitudreferencias;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LicenciaMedicaId", unique = true, nullable = false)
	public Integer getLicenciaMedicaId() {
		return this.licenciaMedicaId;
	}

	public void setLicenciaMedicaId(Integer licenciaMedicaId) {
		this.licenciaMedicaId = licenciaMedicaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MotivoId", nullable = false)
	public Catlicmedicamotivos getCatlicmedicamotivos() {
		return this.catlicmedicamotivos;
	}

	public void setCatlicmedicamotivos(Catlicmedicamotivos catlicmedicamotivos) {
		this.catlicmedicamotivos = catlicmedicamotivos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoServicioId", nullable = false)
	public Catlicmedicatiposservicio getCatlicmedicatiposservicio() {
		return this.catlicmedicatiposservicio;
	}

	public void setCatlicmedicatiposservicio(
			Catlicmedicatiposservicio catlicmedicatiposservicio) {
		this.catlicmedicatiposservicio = catlicmedicatiposservicio;
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
	@JoinColumn(name = "CaracterId", nullable = false)
	public Catlicmedicacaracteres getCatlicmedicacaracteres() {
		return this.catlicmedicacaracteres;
	}

	public void setCatlicmedicacaracteres(
			Catlicmedicacaracteres catlicmedicacaracteres) {
		this.catlicmedicacaracteres = catlicmedicacaracteres;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaEmision", nullable = false, length = 10)
	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	@Column(name = "Folio", nullable = false, length = 80)
	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaInicio", nullable = false, length = 10)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaTermino", nullable = false, length = 10)
	public Date getFechaTermino() {
		return this.fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	@Column(name = "NumDias")
	public Integer getNumDias() {
		return this.numDias;
	}

	public void setNumDias(Integer numDias) {
		this.numDias = numDias;
	}

	@Column(name = "LetraDias", length = 100)
	public String getLetraDias() {
		return this.letraDias;
	}

	public void setLetraDias(String letraDias) {
		this.letraDias = letraDias;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "licenciamedica")
	public Set<Solicitudreferencia> getSolicitudreferencias() {
		return this.solicitudreferencias;
	}

	public void setSolicitudreferencias(
			Set<Solicitudreferencia> solicitudreferencias) {
		this.solicitudreferencias = solicitudreferencias;
	}

}