package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Medicoespecialidadmedica entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "medicoespecialidadmedica", catalog = "my_db_rrg")
public class Medicoespecialidadmedica implements java.io.Serializable {

	// Fields

	private Integer medicoEspecialidadMedicaId;
	private Prestadormedico prestadormedico;
	private Catespecialidadesmedicas catespecialidadesmedicas;

	// Constructors

	/** default constructor */
	public Medicoespecialidadmedica() {
	}

	/** minimal constructor */
	public Medicoespecialidadmedica(Integer medicoEspecialidadMedicaId) {
		this.medicoEspecialidadMedicaId = medicoEspecialidadMedicaId;
	}

	/** full constructor */
	public Medicoespecialidadmedica(Integer medicoEspecialidadMedicaId,
			Prestadormedico prestadormedico,
			Catespecialidadesmedicas catespecialidadesmedicas) {
		this.medicoEspecialidadMedicaId = medicoEspecialidadMedicaId;
		this.prestadormedico = prestadormedico;
		this.catespecialidadesmedicas = catespecialidadesmedicas;
	}

	// Property accessors
	@Id
	@Column(name = "MedicoEspecialidadMedicaId", unique = true, nullable = false)
	public Integer getMedicoEspecialidadMedicaId() {
		return this.medicoEspecialidadMedicaId;
	}

	public void setMedicoEspecialidadMedicaId(Integer medicoEspecialidadMedicaId) {
		this.medicoEspecialidadMedicaId = medicoEspecialidadMedicaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PrestadorId")
	public Prestadormedico getPrestadormedico() {
		return this.prestadormedico;
	}

	public void setPrestadormedico(Prestadormedico prestadormedico) {
		this.prestadormedico = prestadormedico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EspecialidadMedicaId")
	public Catespecialidadesmedicas getCatespecialidadesmedicas() {
		return this.catespecialidadesmedicas;
	}

	public void setCatespecialidadesmedicas(
			Catespecialidadesmedicas catespecialidadesmedicas) {
		this.catespecialidadesmedicas = catespecialidadesmedicas;
	}

}