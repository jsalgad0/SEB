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
 * Catdocumentos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catdocumentos", catalog = "my_db_rrg")
public class Catdocumentos implements java.io.Serializable {

	// Fields

	private Integer documentosId;
	private String descripcion;
	private Set<Documentosprestador> documentosprestadors = new HashSet<Documentosprestador>(
			0);

	// Constructors

	/** default constructor */
	public Catdocumentos() {
	}

	/** full constructor */
	public Catdocumentos(String descripcion,
			Set<Documentosprestador> documentosprestadors) {
		this.descripcion = descripcion;
		this.documentosprestadors = documentosprestadors;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DocumentosId", unique = true, nullable = false)
	public Integer getDocumentosId() {
		return this.documentosId;
	}

	public void setDocumentosId(Integer documentosId) {
		this.documentosId = documentosId;
	}

	@Column(name = "Descripcion", length = 40)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catdocumentos")
	public Set<Documentosprestador> getDocumentosprestadors() {
		return this.documentosprestadors;
	}

	public void setDocumentosprestadors(
			Set<Documentosprestador> documentosprestadors) {
		this.documentosprestadors = documentosprestadors;
	}

}