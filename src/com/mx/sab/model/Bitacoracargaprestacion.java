package com.mx.sab.model;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Bitacoracargaprestacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bitacoracargaprestacion", catalog = "my_db_rrg")
public class Bitacoracargaprestacion implements java.io.Serializable {

	// Fields

	private Integer id;
	private Aseguradores aseguradores;
	private Catestatusarchivoprestaciones catestatusarchivoprestaciones;
	private Integer numPrestacionesCargadas;
	private Timestamp fechaHoraEtl;

	// Constructors

	/** default constructor */
	public Bitacoracargaprestacion() {
	}

	/** full constructor */
	public Bitacoracargaprestacion(Aseguradores aseguradores,
			Catestatusarchivoprestaciones catestatusarchivoprestaciones,
			Integer numPrestacionesCargadas, Timestamp fechaHoraEtl) {
		this.aseguradores = aseguradores;
		this.catestatusarchivoprestaciones = catestatusarchivoprestaciones;
		this.numPrestacionesCargadas = numPrestacionesCargadas;
		this.fechaHoraEtl = fechaHoraEtl;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AseguradorId")
	public Aseguradores getAseguradores() {
		return this.aseguradores;
	}

	public void setAseguradores(Aseguradores aseguradores) {
		this.aseguradores = aseguradores;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstatusArchivoId")
	public Catestatusarchivoprestaciones getCatestatusarchivoprestaciones() {
		return this.catestatusarchivoprestaciones;
	}

	public void setCatestatusarchivoprestaciones(
			Catestatusarchivoprestaciones catestatusarchivoprestaciones) {
		this.catestatusarchivoprestaciones = catestatusarchivoprestaciones;
	}

	@Column(name = "NumPrestacionesCargadas")
	public Integer getNumPrestacionesCargadas() {
		return this.numPrestacionesCargadas;
	}

	public void setNumPrestacionesCargadas(Integer numPrestacionesCargadas) {
		this.numPrestacionesCargadas = numPrestacionesCargadas;
	}

	@Column(name = "FechaHoraETL", length = 19)
	public Timestamp getFechaHoraEtl() {
		return this.fechaHoraEtl;
	}

	public void setFechaHoraEtl(Timestamp fechaHoraEtl) {
		this.fechaHoraEtl = fechaHoraEtl;
	}

}