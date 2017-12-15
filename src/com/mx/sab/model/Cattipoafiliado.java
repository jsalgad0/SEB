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
 * Cattipoafiliado entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattipoafiliado", catalog = "my_db_rrg")
public class Cattipoafiliado implements java.io.Serializable {

	// Fields

	private Integer tipoAfiliadoId;
	private Aseguradores aseguradores;
	private String tipoAfiliado;
	private String tipoAfilClave;
	private String tipoClinicaClave;
	private Set<Afiliado> afiliados = new HashSet<Afiliado>(0);

	// Constructors

	/** default constructor */
	public Cattipoafiliado() {
	}

	/** minimal constructor */
	public Cattipoafiliado(String tipoAfilClave) {
		this.tipoAfilClave = tipoAfilClave;
	}

	/** full constructor */
	public Cattipoafiliado(Aseguradores aseguradores, String tipoAfiliado,
			String tipoAfilClave, String tipoClinicaClave,
			Set<Afiliado> afiliados) {
		this.aseguradores = aseguradores;
		this.tipoAfiliado = tipoAfiliado;
		this.tipoAfilClave = tipoAfilClave;
		this.tipoClinicaClave = tipoClinicaClave;
		this.afiliados = afiliados;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TipoAfiliadoId", unique = true, nullable = false)
	public Integer getTipoAfiliadoId() {
		return this.tipoAfiliadoId;
	}

	public void setTipoAfiliadoId(Integer tipoAfiliadoId) {
		this.tipoAfiliadoId = tipoAfiliadoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AseguradorId")
	public Aseguradores getAseguradores() {
		return this.aseguradores;
	}

	public void setAseguradores(Aseguradores aseguradores) {
		this.aseguradores = aseguradores;
	}

	@Column(name = "TipoAfiliado", length = 25)
	public String getTipoAfiliado() {
		return this.tipoAfiliado;
	}

	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	@Column(name = "TipoAfilClave", nullable = false, length = 10)
	public String getTipoAfilClave() {
		return this.tipoAfilClave;
	}

	public void setTipoAfilClave(String tipoAfilClave) {
		this.tipoAfilClave = tipoAfilClave;
	}

	@Column(name = "TipoClinicaClave", length = 10)
	public String getTipoClinicaClave() {
		return this.tipoClinicaClave;
	}

	public void setTipoClinicaClave(String tipoClinicaClave) {
		this.tipoClinicaClave = tipoClinicaClave;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cattipoafiliado")
	public Set<Afiliado> getAfiliados() {
		return this.afiliados;
	}

	public void setAfiliados(Set<Afiliado> afiliados) {
		this.afiliados = afiliados;
	}

}