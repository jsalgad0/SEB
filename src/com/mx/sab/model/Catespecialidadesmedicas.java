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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Catespecialidadesmedicas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catespecialidadesmedicas", catalog = "my_db_rrg")
public class Catespecialidadesmedicas implements java.io.Serializable {

	// Fields

	private Integer especialidadMedicaId;
	private String especialidadMedica;
	private Set<Usuarioespecialidades> usuarioespecialidadeses = new HashSet<Usuarioespecialidades>(
			0);
	private Set<Solicitudreferencia> solicitudreferencias = new HashSet<Solicitudreferencia>(
			0);
	private Set<Medicoespecialidadmedica> medicoespecialidadmedicas = new HashSet<Medicoespecialidadmedica>(
			0);

	// Constructors

	/** default constructor */
	public Catespecialidadesmedicas() {
	}

	/** minimal constructor */
	public Catespecialidadesmedicas(String especialidadMedica) {
		this.especialidadMedica = especialidadMedica;
	}

	/** full constructor */
	public Catespecialidadesmedicas(String especialidadMedica,
			Set<Usuarioespecialidades> usuarioespecialidadeses,
			Set<Solicitudreferencia> solicitudreferencias,
			Set<Medicoespecialidadmedica> medicoespecialidadmedicas) {
		this.especialidadMedica = especialidadMedica;
		this.usuarioespecialidadeses = usuarioespecialidadeses;
		this.solicitudreferencias = solicitudreferencias;
		this.medicoespecialidadmedicas = medicoespecialidadmedicas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "EspecialidadMedicaId", unique = true, nullable = false)
	public Integer getEspecialidadMedicaId() {
		return this.especialidadMedicaId;
	}

	public void setEspecialidadMedicaId(Integer especialidadMedicaId) {
		this.especialidadMedicaId = especialidadMedicaId;
	}

	@Column(name = "EspecialidadMedica", nullable = false, length = 150)
	public String getEspecialidadMedica() {
		return this.especialidadMedica;
	}

	public void setEspecialidadMedica(String especialidadMedica) {
		this.especialidadMedica = especialidadMedica;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catespecialidadesmedicas")
	public Set<Usuarioespecialidades> getUsuarioespecialidadeses() {
		return this.usuarioespecialidadeses;
	}

	public void setUsuarioespecialidadeses(
			Set<Usuarioespecialidades> usuarioespecialidadeses) {
		this.usuarioespecialidadeses = usuarioespecialidadeses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catespecialidadesmedicas")
	public Set<Solicitudreferencia> getSolicitudreferencias() {
		return this.solicitudreferencias;
	}

	public void setSolicitudreferencias(
			Set<Solicitudreferencia> solicitudreferencias) {
		this.solicitudreferencias = solicitudreferencias;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catespecialidadesmedicas")
	public Set<Medicoespecialidadmedica> getMedicoespecialidadmedicas() {
		return this.medicoespecialidadmedicas;
	}

	public void setMedicoespecialidadmedicas(
			Set<Medicoespecialidadmedica> medicoespecialidadmedicas) {
		this.medicoespecialidadmedicas = medicoespecialidadmedicas;
	}

}