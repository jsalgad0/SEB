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
 * Tipoatencionmedica entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tipoatencionmedica", catalog = "my_db_rrg")
public class Tipoatencionmedica implements java.io.Serializable {

	// Fields

	private Integer tipoAtencionMedicaId;
	private String descripcion;
	private Set<Atencionmedica> atencionmedicas = new HashSet<Atencionmedica>(0);

	// Constructors

	/** default constructor */
	public Tipoatencionmedica() {
	}

	/** full constructor */
	public Tipoatencionmedica(String descripcion,
			Set<Atencionmedica> atencionmedicas) {
		this.descripcion = descripcion;
		this.atencionmedicas = atencionmedicas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TipoAtencionMedicaId", unique = true, nullable = false)
	public Integer getTipoAtencionMedicaId() {
		return this.tipoAtencionMedicaId;
	}

	public void setTipoAtencionMedicaId(Integer tipoAtencionMedicaId) {
		this.tipoAtencionMedicaId = tipoAtencionMedicaId;
	}

	@Column(name = "Descripcion", length = 20)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoatencionmedica")
	public Set<Atencionmedica> getAtencionmedicas() {
		return this.atencionmedicas;
	}

	public void setAtencionmedicas(Set<Atencionmedica> atencionmedicas) {
		this.atencionmedicas = atencionmedicas;
	}

}