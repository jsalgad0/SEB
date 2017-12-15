package com.mx.sab.model;
// default package

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
 * Lectores entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lectores", catalog = "my_db_rrg")
public class Lectores implements java.io.Serializable {

	// Fields

	private Integer lectorId;
	private Lugaresdeatencion lugaresdeatencion;
	private Propietarioslector propietarioslector;
	private String noDeSerie;

	// Constructors

	/** default constructor */
	public Lectores() {
	}

	/** full constructor */
	public Lectores(Lugaresdeatencion lugaresdeatencion,
			Propietarioslector propietarioslector, String noDeSerie) {
		this.lugaresdeatencion = lugaresdeatencion;
		this.propietarioslector = propietarioslector;
		this.noDeSerie = noDeSerie;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LectorId", unique = true, nullable = false)
	public Integer getLectorId() {
		return this.lectorId;
	}

	public void setLectorId(Integer lectorId) {
		this.lectorId = lectorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LugarDeAtencionId")
	public Lugaresdeatencion getLugaresdeatencion() {
		return this.lugaresdeatencion;
	}

	public void setLugaresdeatencion(Lugaresdeatencion lugaresdeatencion) {
		this.lugaresdeatencion = lugaresdeatencion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PropietarioLectorId")
	public Propietarioslector getPropietarioslector() {
		return this.propietarioslector;
	}

	public void setPropietarioslector(Propietarioslector propietarioslector) {
		this.propietarioslector = propietarioslector;
	}

	@Column(name = "NoDeSerie", length = 45)
	public String getNoDeSerie() {
		return this.noDeSerie;
	}

	public void setNoDeSerie(String noDeSerie) {
		this.noDeSerie = noDeSerie;
	}

}