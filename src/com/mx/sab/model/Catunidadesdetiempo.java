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
 * Catunidadesdetiempo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catunidadesdetiempo", catalog = "my_db_rrg")
public class Catunidadesdetiempo implements java.io.Serializable {

	// Fields

	private Integer unidadTiempoId;
	private String unidadTiempo;
	private String abrev;
	private Integer esMenorAunDia;
	private Integer activo;
	private Set<Medicamentosreceta> medicamentosrecetasForFrecuenciaUnidadDeTiempoId = new HashSet<Medicamentosreceta>(
			0);
	private Set<Medicamentosreceta> medicamentosrecetasForDuracionUnidadDeTiempoId = new HashSet<Medicamentosreceta>(
			0);

	// Constructors

	/** default constructor */
	public Catunidadesdetiempo() {
	}

	/** minimal constructor */
	public Catunidadesdetiempo(String unidadTiempo, String abrev,
			Integer esMenorAunDia, Integer activo) {
		this.unidadTiempo = unidadTiempo;
		this.abrev = abrev;
		this.esMenorAunDia = esMenorAunDia;
		this.activo = activo;
	}

	/** full constructor */
	public Catunidadesdetiempo(
			String unidadTiempo,
			String abrev,
			Integer esMenorAunDia,
			Integer activo,
			Set<Medicamentosreceta> medicamentosrecetasForFrecuenciaUnidadDeTiempoId,
			Set<Medicamentosreceta> medicamentosrecetasForDuracionUnidadDeTiempoId) {
		this.unidadTiempo = unidadTiempo;
		this.abrev = abrev;
		this.esMenorAunDia = esMenorAunDia;
		this.activo = activo;
		this.medicamentosrecetasForFrecuenciaUnidadDeTiempoId = medicamentosrecetasForFrecuenciaUnidadDeTiempoId;
		this.medicamentosrecetasForDuracionUnidadDeTiempoId = medicamentosrecetasForDuracionUnidadDeTiempoId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "UnidadTiempoId", unique = true, nullable = false)
	public Integer getUnidadTiempoId() {
		return this.unidadTiempoId;
	}

	public void setUnidadTiempoId(Integer unidadTiempoId) {
		this.unidadTiempoId = unidadTiempoId;
	}

	@Column(name = "UnidadTiempo", nullable = false, length = 50)
	public String getUnidadTiempo() {
		return this.unidadTiempo;
	}

	public void setUnidadTiempo(String unidadTiempo) {
		this.unidadTiempo = unidadTiempo;
	}

	@Column(name = "Abrev", nullable = false, length = 10)
	public String getAbrev() {
		return this.abrev;
	}

	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}

	@Column(name = "EsMenorAUnDia", nullable = false)
	public Integer getEsMenorAunDia() {
		return this.esMenorAunDia;
	}

	public void setEsMenorAunDia(Integer esMenorAunDia) {
		this.esMenorAunDia = esMenorAunDia;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catunidadesdetiempoByFrecuenciaUnidadDeTiempoId")
	public Set<Medicamentosreceta> getMedicamentosrecetasForFrecuenciaUnidadDeTiempoId() {
		return this.medicamentosrecetasForFrecuenciaUnidadDeTiempoId;
	}

	public void setMedicamentosrecetasForFrecuenciaUnidadDeTiempoId(
			Set<Medicamentosreceta> medicamentosrecetasForFrecuenciaUnidadDeTiempoId) {
		this.medicamentosrecetasForFrecuenciaUnidadDeTiempoId = medicamentosrecetasForFrecuenciaUnidadDeTiempoId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catunidadesdetiempoByDuracionUnidadDeTiempoId")
	public Set<Medicamentosreceta> getMedicamentosrecetasForDuracionUnidadDeTiempoId() {
		return this.medicamentosrecetasForDuracionUnidadDeTiempoId;
	}

	public void setMedicamentosrecetasForDuracionUnidadDeTiempoId(
			Set<Medicamentosreceta> medicamentosrecetasForDuracionUnidadDeTiempoId) {
		this.medicamentosrecetasForDuracionUnidadDeTiempoId = medicamentosrecetasForDuracionUnidadDeTiempoId;
	}

}