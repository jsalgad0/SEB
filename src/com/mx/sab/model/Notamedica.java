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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Notamedica entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notamedica", catalog = "my_db_rrg")
public class Notamedica implements java.io.Serializable {

	// Fields

	private Integer notaMedicaId;
	private Usuarios usuarios;
	private Atencionmedica atencionmedica;
	private String antecedentes;
	private String sintomas;
	private String observaciones;
	private String planAseguir;
	private String folio;
	private Set<NotamedicaCies10> notamedicaCies10s = new HashSet<NotamedicaCies10>(
			0);

	// Constructors

	/** default constructor */
	public Notamedica() {
	}

	/** minimal constructor */
	public Notamedica(Usuarios usuarios, Atencionmedica atencionmedica) {
		this.usuarios = usuarios;
		this.atencionmedica = atencionmedica;
	}

	/** full constructor */
	public Notamedica(Usuarios usuarios, Atencionmedica atencionmedica,
			String antecedentes, String sintomas, String observaciones,
			String planAseguir, String folio,
			Set<NotamedicaCies10> notamedicaCies10s) {
		this.usuarios = usuarios;
		this.atencionmedica = atencionmedica;
		this.antecedentes = antecedentes;
		this.sintomas = sintomas;
		this.observaciones = observaciones;
		this.planAseguir = planAseguir;
		this.folio = folio;
		this.notamedicaCies10s = notamedicaCies10s;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "NotaMedicaId", unique = true, nullable = false)
	public Integer getNotaMedicaId() {
		return this.notaMedicaId;
	}

	public void setNotaMedicaId(Integer notaMedicaId) {
		this.notaMedicaId = notaMedicaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioMedicoId", nullable = false)
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AtencionMedicaId", nullable = false)
	public Atencionmedica getAtencionmedica() {
		return this.atencionmedica;
	}

	public void setAtencionmedica(Atencionmedica atencionmedica) {
		this.atencionmedica = atencionmedica;
	}

	@Column(name = "Antecedentes", length = 2000)
	public String getAntecedentes() {
		return this.antecedentes;
	}

	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}

	@Column(name = "Sintomas", length = 2000)
	public String getSintomas() {
		return this.sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	@Column(name = "Observaciones", length = 2000)
	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Column(name = "PlanASeguir", length = 2000)
	public String getPlanAseguir() {
		return this.planAseguir;
	}

	public void setPlanAseguir(String planAseguir) {
		this.planAseguir = planAseguir;
	}

	@Column(name = "Folio", length = 11)
	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "notamedica")
	public Set<NotamedicaCies10> getNotamedicaCies10s() {
		return this.notamedicaCies10s;
	}

	public void setNotamedicaCies10s(Set<NotamedicaCies10> notamedicaCies10s) {
		this.notamedicaCies10s = notamedicaCies10s;
	}

}