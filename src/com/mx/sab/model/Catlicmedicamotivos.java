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
 * Catlicmedicamotivos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catlicmedicamotivos", catalog = "my_db_rrg")
public class Catlicmedicamotivos implements java.io.Serializable {

	// Fields

	private Integer licMedicaMotivoId;
	private String licMedicaMotivo;
	private Integer activo;
	private Set<Licenciamedica> licenciamedicas = new HashSet<Licenciamedica>(0);

	// Constructors

	/** default constructor */
	public Catlicmedicamotivos() {
	}

	/** minimal constructor */
	public Catlicmedicamotivos(String licMedicaMotivo, Integer activo) {
		this.licMedicaMotivo = licMedicaMotivo;
		this.activo = activo;
	}

	/** full constructor */
	public Catlicmedicamotivos(String licMedicaMotivo, Integer activo,
			Set<Licenciamedica> licenciamedicas) {
		this.licMedicaMotivo = licMedicaMotivo;
		this.activo = activo;
		this.licenciamedicas = licenciamedicas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LicMedicaMotivoId", unique = true, nullable = false)
	public Integer getLicMedicaMotivoId() {
		return this.licMedicaMotivoId;
	}

	public void setLicMedicaMotivoId(Integer licMedicaMotivoId) {
		this.licMedicaMotivoId = licMedicaMotivoId;
	}

	@Column(name = "LicMedicaMotivo", nullable = false)
	public String getLicMedicaMotivo() {
		return this.licMedicaMotivo;
	}

	public void setLicMedicaMotivo(String licMedicaMotivo) {
		this.licMedicaMotivo = licMedicaMotivo;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catlicmedicamotivos")
	public Set<Licenciamedica> getLicenciamedicas() {
		return this.licenciamedicas;
	}

	public void setLicenciamedicas(Set<Licenciamedica> licenciamedicas) {
		this.licenciamedicas = licenciamedicas;
	}

}