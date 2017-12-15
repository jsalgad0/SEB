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
 * Catsolireferenciamotivos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catsolireferenciamotivos", catalog = "my_db_rrg")
public class Catsolireferenciamotivos implements java.io.Serializable {

	// Fields

	private Integer soliReferenciaMotivoId;
	private String soliReferenciaMotivo;
	private Integer activo;
	private Set<Solicitudreferencia> solicitudreferencias = new HashSet<Solicitudreferencia>(
			0);

	// Constructors

	/** default constructor */
	public Catsolireferenciamotivos() {
	}

	/** minimal constructor */
	public Catsolireferenciamotivos(String soliReferenciaMotivo, Integer activo) {
		this.soliReferenciaMotivo = soliReferenciaMotivo;
		this.activo = activo;
	}

	/** full constructor */
	public Catsolireferenciamotivos(String soliReferenciaMotivo,
			Integer activo, Set<Solicitudreferencia> solicitudreferencias) {
		this.soliReferenciaMotivo = soliReferenciaMotivo;
		this.activo = activo;
		this.solicitudreferencias = solicitudreferencias;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SoliReferenciaMotivoId", unique = true, nullable = false)
	public Integer getSoliReferenciaMotivoId() {
		return this.soliReferenciaMotivoId;
	}

	public void setSoliReferenciaMotivoId(Integer soliReferenciaMotivoId) {
		this.soliReferenciaMotivoId = soliReferenciaMotivoId;
	}

	@Column(name = "SoliReferenciaMotivo", nullable = false, length = 150)
	public String getSoliReferenciaMotivo() {
		return this.soliReferenciaMotivo;
	}

	public void setSoliReferenciaMotivo(String soliReferenciaMotivo) {
		this.soliReferenciaMotivo = soliReferenciaMotivo;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catsolireferenciamotivos")
	public Set<Solicitudreferencia> getSolicitudreferencias() {
		return this.solicitudreferencias;
	}

	public void setSolicitudreferencias(
			Set<Solicitudreferencia> solicitudreferencias) {
		this.solicitudreferencias = solicitudreferencias;
	}

}