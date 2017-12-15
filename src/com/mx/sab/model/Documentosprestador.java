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
 * Documentosprestador entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "documentosprestador", catalog = "my_db_rrg")
public class Documentosprestador implements java.io.Serializable {

	// Fields

	private Integer documentoPrestadorId;
	private Prestadores prestadores;
	private Catdocumentosmedico catdocumentosmedico;
	private Catdocumentos catdocumentos;
	private Boolean autentiaMedico;
	private Boolean autentiaPaciente;
	private String ruta;

	// Constructors

	/** default constructor */
	public Documentosprestador() {
	}

	/** full constructor */
	public Documentosprestador(Prestadores prestadores,
			Catdocumentosmedico catdocumentosmedico,
			Catdocumentos catdocumentos, Boolean autentiaMedico,
			Boolean autentiaPaciente, String ruta) {
		this.prestadores = prestadores;
		this.catdocumentosmedico = catdocumentosmedico;
		this.catdocumentos = catdocumentos;
		this.autentiaMedico = autentiaMedico;
		this.autentiaPaciente = autentiaPaciente;
		this.ruta = ruta;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DocumentoPrestadorId", unique = true, nullable = false)
	public Integer getDocumentoPrestadorId() {
		return this.documentoPrestadorId;
	}

	public void setDocumentoPrestadorId(Integer documentoPrestadorId) {
		this.documentoPrestadorId = documentoPrestadorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PrestadorId")
	public Prestadores getPrestadores() {
		return this.prestadores;
	}

	public void setPrestadores(Prestadores prestadores) {
		this.prestadores = prestadores;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatDocMedicoId")
	public Catdocumentosmedico getCatdocumentosmedico() {
		return this.catdocumentosmedico;
	}

	public void setCatdocumentosmedico(Catdocumentosmedico catdocumentosmedico) {
		this.catdocumentosmedico = catdocumentosmedico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DocumentosId")
	public Catdocumentos getCatdocumentos() {
		return this.catdocumentos;
	}

	public void setCatdocumentos(Catdocumentos catdocumentos) {
		this.catdocumentos = catdocumentos;
	}

	@Column(name = "AutentiaMedico")
	public Boolean getAutentiaMedico() {
		return this.autentiaMedico;
	}

	public void setAutentiaMedico(Boolean autentiaMedico) {
		this.autentiaMedico = autentiaMedico;
	}

	@Column(name = "AutentiaPaciente")
	public Boolean getAutentiaPaciente() {
		return this.autentiaPaciente;
	}

	public void setAutentiaPaciente(Boolean autentiaPaciente) {
		this.autentiaPaciente = autentiaPaciente;
	}

	@Column(name = "Ruta", length = 200)
	public String getRuta() {
		return this.ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

}