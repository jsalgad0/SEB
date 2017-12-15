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
 * Catagendadopor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catagendadopor", catalog = "my_db_rrg")
public class Catagendadopor implements java.io.Serializable {

	// Fields

	private Integer agendadoPorId;
	private String agendadoPor;
	private String agendadoPorAbr;
	private Set<Agenda> agendas = new HashSet<Agenda>(0);
	private Set<Atencionmedica> atencionmedicas = new HashSet<Atencionmedica>(0);

	// Constructors

	/** default constructor */
	public Catagendadopor() {
	}

	/** full constructor */
	public Catagendadopor(String agendadoPor, String agendadoPorAbr,
			Set<Agenda> agendas, Set<Atencionmedica> atencionmedicas) {
		this.agendadoPor = agendadoPor;
		this.agendadoPorAbr = agendadoPorAbr;
		this.agendas = agendas;
		this.atencionmedicas = atencionmedicas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AgendadoPorId", unique = true, nullable = false)
	public Integer getAgendadoPorId() {
		return this.agendadoPorId;
	}

	public void setAgendadoPorId(Integer agendadoPorId) {
		this.agendadoPorId = agendadoPorId;
	}

	@Column(name = "AgendadoPor", length = 50)
	public String getAgendadoPor() {
		return this.agendadoPor;
	}

	public void setAgendadoPor(String agendadoPor) {
		this.agendadoPor = agendadoPor;
	}

	@Column(name = "AgendadoPorAbr", length = 20)
	public String getAgendadoPorAbr() {
		return this.agendadoPorAbr;
	}

	public void setAgendadoPorAbr(String agendadoPorAbr) {
		this.agendadoPorAbr = agendadoPorAbr;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catagendadopor")
	public Set<Agenda> getAgendas() {
		return this.agendas;
	}

	public void setAgendas(Set<Agenda> agendas) {
		this.agendas = agendas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catagendadopor")
	public Set<Atencionmedica> getAtencionmedicas() {
		return this.atencionmedicas;
	}

	public void setAtencionmedicas(Set<Atencionmedica> atencionmedicas) {
		this.atencionmedicas = atencionmedicas;
	}

}