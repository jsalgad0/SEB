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
 * Catlicmedicatiposservicio entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catlicmedicatiposservicio", catalog = "my_db_rrg")
public class Catlicmedicatiposservicio implements java.io.Serializable {

	// Fields

	private Integer licMedicaTipoServicioId;
	private String licMedicaTipoServicio;
	private Integer activo;
	private Set<Licenciamedica> licenciamedicas = new HashSet<Licenciamedica>(0);

	// Constructors

	/** default constructor */
	public Catlicmedicatiposservicio() {
	}

	/** minimal constructor */
	public Catlicmedicatiposservicio(String licMedicaTipoServicio,
			Integer activo) {
		this.licMedicaTipoServicio = licMedicaTipoServicio;
		this.activo = activo;
	}

	/** full constructor */
	public Catlicmedicatiposservicio(String licMedicaTipoServicio,
			Integer activo, Set<Licenciamedica> licenciamedicas) {
		this.licMedicaTipoServicio = licMedicaTipoServicio;
		this.activo = activo;
		this.licenciamedicas = licenciamedicas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LicMedicaTipoServicioId", unique = true, nullable = false)
	public Integer getLicMedicaTipoServicioId() {
		return this.licMedicaTipoServicioId;
	}

	public void setLicMedicaTipoServicioId(Integer licMedicaTipoServicioId) {
		this.licMedicaTipoServicioId = licMedicaTipoServicioId;
	}

	@Column(name = "LicMedicaTipoServicio", nullable = false)
	public String getLicMedicaTipoServicio() {
		return this.licMedicaTipoServicio;
	}

	public void setLicMedicaTipoServicio(String licMedicaTipoServicio) {
		this.licMedicaTipoServicio = licMedicaTipoServicio;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catlicmedicatiposservicio")
	public Set<Licenciamedica> getLicenciamedicas() {
		return this.licenciamedicas;
	}

	public void setLicenciamedicas(Set<Licenciamedica> licenciamedicas) {
		this.licenciamedicas = licenciamedicas;
	}

}