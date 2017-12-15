package com.mx.sab.model;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Catcies10 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catcies10", catalog = "my_db_rrg")
public class Catcies10 implements java.io.Serializable {

	// Fields

	private Integer cie10id;
	private Catcies10 catcies10;
	private String codigo;
	private String descripcion;
	private Set<Catcies10> catcies10s = new HashSet<Catcies10>(0);
	private Set<NotamedicaCies10> notamedicaCies10s = new HashSet<NotamedicaCies10>(
			0);
	private Set<Contrareferencia> contrareferencias = new HashSet<Contrareferencia>(
			0);

	// Constructors

	/** default constructor */
	public Catcies10() {
	}

	/** minimal constructor */
	public Catcies10(Integer cie10id) {
		this.cie10id = cie10id;
	}

	/** full constructor */
	public Catcies10(Integer cie10id, Catcies10 catcies10, String codigo,
			String descripcion, Set<Catcies10> catcies10s,
			Set<NotamedicaCies10> notamedicaCies10s,
			Set<Contrareferencia> contrareferencias) {
		this.cie10id = cie10id;
		this.catcies10 = catcies10;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.catcies10s = catcies10s;
		this.notamedicaCies10s = notamedicaCies10s;
		this.contrareferencias = contrareferencias;
	}

	// Property accessors
	@Id
	@Column(name = "CIE10Id", unique = true, nullable = false)
	public Integer getCie10id() {
		return this.cie10id;
	}

	public void setCie10id(Integer cie10id) {
		this.cie10id = cie10id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CIE10ParentId")
	public Catcies10 getCatcies10() {
		return this.catcies10;
	}

	public void setCatcies10(Catcies10 catcies10) {
		this.catcies10 = catcies10;
	}

	@Column(name = "Codigo", length = 10)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "Descripcion")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catcies10")
	public Set<Catcies10> getCatcies10s() {
		return this.catcies10s;
	}

	public void setCatcies10s(Set<Catcies10> catcies10s) {
		this.catcies10s = catcies10s;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catcies10")
	public Set<NotamedicaCies10> getNotamedicaCies10s() {
		return this.notamedicaCies10s;
	}

	public void setNotamedicaCies10s(Set<NotamedicaCies10> notamedicaCies10s) {
		this.notamedicaCies10s = notamedicaCies10s;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catcies10")
	public Set<Contrareferencia> getContrareferencias() {
		return this.contrareferencias;
	}

	public void setContrareferencias(Set<Contrareferencia> contrareferencias) {
		this.contrareferencias = contrareferencias;
	}

}