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
 * Catdocumentosmedico entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catdocumentosmedico", catalog = "my_db_rrg")
public class Catdocumentosmedico implements java.io.Serializable {

	// Fields

	private Integer catDocMedicoId;
	private String descripcion;
	private Set<Documentosprestador> documentosprestadors = new HashSet<Documentosprestador>(
			0);

	// Constructors

	/** default constructor */
	public Catdocumentosmedico() {
	}

	/** full constructor */
	public Catdocumentosmedico(String descripcion,
			Set<Documentosprestador> documentosprestadors) {
		this.descripcion = descripcion;
		this.documentosprestadors = documentosprestadors;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CatDocMedicoId", unique = true, nullable = false)
	public Integer getCatDocMedicoId() {
		return this.catDocMedicoId;
	}

	public void setCatDocMedicoId(Integer catDocMedicoId) {
		this.catDocMedicoId = catDocMedicoId;
	}

	@Column(name = "Descripcion", length = 50)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catdocumentosmedico")
	public Set<Documentosprestador> getDocumentosprestadors() {
		return this.documentosprestadors;
	}

	public void setDocumentosprestadors(
			Set<Documentosprestador> documentosprestadors) {
		this.documentosprestadors = documentosprestadors;
	}

}