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
 * Contrareferencia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contrareferencia", catalog = "my_db_rrg")
public class Contrareferencia implements java.io.Serializable {

	// Fields

	private Integer contrareferenciaId;
	private Catcies10 catcies10;
	private Solicitudreferencia solicitudreferencia;
	private Date fechaContrareferencia;
	private String motivoContrareferencia;
	private Integer numInterConsultas;
	private Integer numConsultas;
	private String resultadosValoracion;
	private String indicaciones;
	private Date fecha;
	private Time hora;

	// Constructors

	/** default constructor */
	public Contrareferencia() {
	}

	/** minimal constructor */
	public Contrareferencia(Catcies10 catcies10,
			Solicitudreferencia solicitudreferencia,
			String motivoContrareferencia, Integer numInterConsultas,
			Integer numConsultas, String resultadosValoracion,
			String indicaciones, Date fecha, Time hora) {
		this.catcies10 = catcies10;
		this.solicitudreferencia = solicitudreferencia;
		this.motivoContrareferencia = motivoContrareferencia;
		this.numInterConsultas = numInterConsultas;
		this.numConsultas = numConsultas;
		this.resultadosValoracion = resultadosValoracion;
		this.indicaciones = indicaciones;
		this.fecha = fecha;
		this.hora = hora;
	}

	/** full constructor */
	public Contrareferencia(Catcies10 catcies10,
			Solicitudreferencia solicitudreferencia,
			Date fechaContrareferencia, String motivoContrareferencia,
			Integer numInterConsultas, Integer numConsultas,
			String resultadosValoracion, String indicaciones, Date fecha,
			Time hora) {
		this.catcies10 = catcies10;
		this.solicitudreferencia = solicitudreferencia;
		this.fechaContrareferencia = fechaContrareferencia;
		this.motivoContrareferencia = motivoContrareferencia;
		this.numInterConsultas = numInterConsultas;
		this.numConsultas = numConsultas;
		this.resultadosValoracion = resultadosValoracion;
		this.indicaciones = indicaciones;
		this.fecha = fecha;
		this.hora = hora;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ContrareferenciaId", unique = true, nullable = false)
	public Integer getContrareferenciaId() {
		return this.contrareferenciaId;
	}

	public void setContrareferenciaId(Integer contrareferenciaId) {
		this.contrareferenciaId = contrareferenciaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DiagnosticoContrareferencia", nullable = false)
	public Catcies10 getCatcies10() {
		return this.catcies10;
	}

	public void setCatcies10(Catcies10 catcies10) {
		this.catcies10 = catcies10;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SolicitudReferenciaId", nullable = false)
	public Solicitudreferencia getSolicitudreferencia() {
		return this.solicitudreferencia;
	}

	public void setSolicitudreferencia(Solicitudreferencia solicitudreferencia) {
		this.solicitudreferencia = solicitudreferencia;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaContrareferencia", length = 10)
	public Date getFechaContrareferencia() {
		return this.fechaContrareferencia;
	}

	public void setFechaContrareferencia(Date fechaContrareferencia) {
		this.fechaContrareferencia = fechaContrareferencia;
	}

	@Column(name = "MotivoContrareferencia", nullable = false, length = 2000)
	public String getMotivoContrareferencia() {
		return this.motivoContrareferencia;
	}

	public void setMotivoContrareferencia(String motivoContrareferencia) {
		this.motivoContrareferencia = motivoContrareferencia;
	}

	@Column(name = "NumInterConsultas", nullable = false)
	public Integer getNumInterConsultas() {
		return this.numInterConsultas;
	}

	public void setNumInterConsultas(Integer numInterConsultas) {
		this.numInterConsultas = numInterConsultas;
	}

	@Column(name = "NumConsultas", nullable = false)
	public Integer getNumConsultas() {
		return this.numConsultas;
	}

	public void setNumConsultas(Integer numConsultas) {
		this.numConsultas = numConsultas;
	}

	@Column(name = "ResultadosValoracion", nullable = false, length = 2000)
	public String getResultadosValoracion() {
		return this.resultadosValoracion;
	}

	public void setResultadosValoracion(String resultadosValoracion) {
		this.resultadosValoracion = resultadosValoracion;
	}

	@Column(name = "Indicaciones", nullable = false, length = 2000)
	public String getIndicaciones() {
		return this.indicaciones;
	}

	public void setIndicaciones(String indicaciones) {
		this.indicaciones = indicaciones;
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

}