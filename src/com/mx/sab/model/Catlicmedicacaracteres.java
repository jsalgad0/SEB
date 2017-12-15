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
 * Catlicmedicacaracteres entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catlicmedicacaracteres", catalog = "my_db_rrg")
public class Catlicmedicacaracteres implements java.io.Serializable {

	// Fields

	private Integer licMedicaCaracterId;
	private String licMedicaCaracter;
	private Integer activo;
	private Set<Licenciamedica> licenciamedicas = new HashSet<Licenciamedica>(0);

	// Constructors

	/** default constructor */
	public Catlicmedicacaracteres() {
	}

	/** minimal constructor */
	public Catlicmedicacaracteres(String licMedicaCaracter, Integer activo) {
		this.licMedicaCaracter = licMedicaCaracter;
		this.activo = activo;
	}

	/** full constructor */
	public Catlicmedicacaracteres(String licMedicaCaracter, Integer activo,
			Set<Licenciamedica> licenciamedicas) {
		this.licMedicaCaracter = licMedicaCaracter;
		this.activo = activo;
		this.licenciamedicas = licenciamedicas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LicMedicaCaracterId", unique = true, nullable = false)
	public Integer getLicMedicaCaracterId() {
		return this.licMedicaCaracterId;
	}

	public void setLicMedicaCaracterId(Integer licMedicaCaracterId) {
		this.licMedicaCaracterId = licMedicaCaracterId;
	}

	@Column(name = "LicMedicaCaracter", nullable = false)
	public String getLicMedicaCaracter() {
		return this.licMedicaCaracter;
	}

	public void setLicMedicaCaracter(String licMedicaCaracter) {
		this.licMedicaCaracter = licMedicaCaracter;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catlicmedicacaracteres")
	public Set<Licenciamedica> getLicenciamedicas() {
		return this.licenciamedicas;
	}

	public void setLicenciamedicas(Set<Licenciamedica> licenciamedicas) {
		this.licenciamedicas = licenciamedicas;
	}

}