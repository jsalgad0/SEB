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
 * Catestatuscitas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catestatuscitas", catalog = "my_db_rrg")
public class Catestatuscitas implements java.io.Serializable {

	// Fields

	private Integer estatusCitaId;
	private String abreviatura;
	private String estatatusCita;
	private Set<Atencionmedica> atencionmedicas = new HashSet<Atencionmedica>(0);
	private Set<Agenda> agendas = new HashSet<Agenda>(0);

	// Constructors

	/** default constructor */
	public Catestatuscitas() {
	}

	/** full constructor */
	public Catestatuscitas(String abreviatura, String estatatusCita,
			Set<Atencionmedica> atencionmedicas, Set<Agenda> agendas) {
		this.abreviatura = abreviatura;
		this.estatatusCita = estatatusCita;
		this.atencionmedicas = atencionmedicas;
		this.agendas = agendas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "EstatusCitaId", unique = true, nullable = false)
	public Integer getEstatusCitaId() {
		return this.estatusCitaId;
	}

	public void setEstatusCitaId(Integer estatusCitaId) {
		this.estatusCitaId = estatusCitaId;
	}

	@Column(name = "Abreviatura", length = 5)
	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	@Column(name = "EstatatusCita", length = 100)
	public String getEstatatusCita() {
		return this.estatatusCita;
	}

	public void setEstatatusCita(String estatatusCita) {
		this.estatatusCita = estatatusCita;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestatuscitas")
	public Set<Atencionmedica> getAtencionmedicas() {
		return this.atencionmedicas;
	}

	public void setAtencionmedicas(Set<Atencionmedica> atencionmedicas) {
		this.atencionmedicas = atencionmedicas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catestatuscitas")
	public Set<Agenda> getAgendas() {
		return this.agendas;
	}

	public void setAgendas(Set<Agenda> agendas) {
		this.agendas = agendas;
	}

}