package com.mx.sab.model;
// default package

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Historiaclinica entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "historiaclinica", catalog = "my_db_rrg")
public class Historiaclinica implements java.io.Serializable {

	// Fields

	private Integer historiaClinicaId;
	private Usuarios usuarios;
	private Afiliado afiliado;
	private Date fechaCreacion;
	private Set<HcAntecedentesfamiliares> hcAntecedentesfamiliareses = new HashSet<HcAntecedentesfamiliares>(
			0);
	private Set<HcExploracionfisica> hcExploracionfisicas = new HashSet<HcExploracionfisica>(
			0);
	private Set<HcFasedelciclo> hcFasedelciclos = new HashSet<HcFasedelciclo>(0);
	private Set<HcGineco> hcGinecos = new HashSet<HcGineco>(0);
	private Set<HcPersonalnopatologicos> hcPersonalnopatologicoses = new HashSet<HcPersonalnopatologicos>(
			0);
	private Set<HcAntecedentespersonal> hcAntecedentespersonals = new HashSet<HcAntecedentespersonal>(
			0);

	// Constructors

	/** default constructor */
	public Historiaclinica() {
	}

	/** minimal constructor */
	public Historiaclinica(Usuarios usuarios, Afiliado afiliado,
			Date fechaCreacion) {
		this.usuarios = usuarios;
		this.afiliado = afiliado;
		this.fechaCreacion = fechaCreacion;
	}

	/** full constructor */
	public Historiaclinica(Usuarios usuarios, Afiliado afiliado,
			Date fechaCreacion,
			Set<HcAntecedentesfamiliares> hcAntecedentesfamiliareses,
			Set<HcExploracionfisica> hcExploracionfisicas,
			Set<HcFasedelciclo> hcFasedelciclos, Set<HcGineco> hcGinecos,
			Set<HcPersonalnopatologicos> hcPersonalnopatologicoses,
			Set<HcAntecedentespersonal> hcAntecedentespersonals) {
		this.usuarios = usuarios;
		this.afiliado = afiliado;
		this.fechaCreacion = fechaCreacion;
		this.hcAntecedentesfamiliareses = hcAntecedentesfamiliareses;
		this.hcExploracionfisicas = hcExploracionfisicas;
		this.hcFasedelciclos = hcFasedelciclos;
		this.hcGinecos = hcGinecos;
		this.hcPersonalnopatologicoses = hcPersonalnopatologicoses;
		this.hcAntecedentespersonals = hcAntecedentespersonals;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "HistoriaClinicaId", unique = true, nullable = false)
	public Integer getHistoriaClinicaId() {
		return this.historiaClinicaId;
	}

	public void setHistoriaClinicaId(Integer historiaClinicaId) {
		this.historiaClinicaId = historiaClinicaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioMedicoId", nullable = false)
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AfiliadoId", nullable = false)
	public Afiliado getAfiliado() {
		return this.afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaCreacion", nullable = false, length = 10)
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "historiaclinica")
	public Set<HcAntecedentesfamiliares> getHcAntecedentesfamiliareses() {
		return this.hcAntecedentesfamiliareses;
	}

	public void setHcAntecedentesfamiliareses(
			Set<HcAntecedentesfamiliares> hcAntecedentesfamiliareses) {
		this.hcAntecedentesfamiliareses = hcAntecedentesfamiliareses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "historiaclinica")
	public Set<HcExploracionfisica> getHcExploracionfisicas() {
		return this.hcExploracionfisicas;
	}

	public void setHcExploracionfisicas(
			Set<HcExploracionfisica> hcExploracionfisicas) {
		this.hcExploracionfisicas = hcExploracionfisicas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "historiaclinica")
	public Set<HcFasedelciclo> getHcFasedelciclos() {
		return this.hcFasedelciclos;
	}

	public void setHcFasedelciclos(Set<HcFasedelciclo> hcFasedelciclos) {
		this.hcFasedelciclos = hcFasedelciclos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "historiaclinica")
	public Set<HcGineco> getHcGinecos() {
		return this.hcGinecos;
	}

	public void setHcGinecos(Set<HcGineco> hcGinecos) {
		this.hcGinecos = hcGinecos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "historiaclinica")
	public Set<HcPersonalnopatologicos> getHcPersonalnopatologicoses() {
		return this.hcPersonalnopatologicoses;
	}

	public void setHcPersonalnopatologicoses(
			Set<HcPersonalnopatologicos> hcPersonalnopatologicoses) {
		this.hcPersonalnopatologicoses = hcPersonalnopatologicoses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "historiaclinica")
	public Set<HcAntecedentespersonal> getHcAntecedentespersonals() {
		return this.hcAntecedentespersonals;
	}

	public void setHcAntecedentespersonals(
			Set<HcAntecedentespersonal> hcAntecedentespersonals) {
		this.hcAntecedentespersonals = hcAntecedentespersonals;
	}

}