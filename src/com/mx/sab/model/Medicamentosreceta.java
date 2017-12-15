package com.mx.sab.model;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Medicamentosreceta entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "medicamentosreceta", catalog = "my_db_rrg")
public class Medicamentosreceta implements java.io.Serializable {

	// Fields

	private MedicamentosrecetaId id;
	private Catviasdeadminmedicamento catviasdeadminmedicamento;
	private Recetas recetas;
	private Catunidadesdetiempo catunidadesdetiempoByDuracionUnidadDeTiempoId;
	private Catmedicamentos catmedicamentos;
	private Catunidadesdetiempo catunidadesdetiempoByFrecuenciaUnidadDeTiempoId;
	private Integer cantidadUnidades;
	private Integer frecuenciaDeToma;
	private Integer duracionDeToma;
	private String unidad;
	private String observaciones;
	private String dosisNo;
	private String dosis;
	private String folio;
	private Set<Medicamentorecetaentregas> medicamentorecetaentregases = new HashSet<Medicamentorecetaentregas>(
			0);

	// Constructors

	/** default constructor */
	public Medicamentosreceta() {
	}

	/** minimal constructor */
	public Medicamentosreceta(
			MedicamentosrecetaId id,
			Catviasdeadminmedicamento catviasdeadminmedicamento,
			Recetas recetas,
			Catunidadesdetiempo catunidadesdetiempoByDuracionUnidadDeTiempoId,
			Catmedicamentos catmedicamentos,
			Catunidadesdetiempo catunidadesdetiempoByFrecuenciaUnidadDeTiempoId,
			Integer cantidadUnidades, Integer frecuenciaDeToma,
			Integer duracionDeToma) {
		this.id = id;
		this.catviasdeadminmedicamento = catviasdeadminmedicamento;
		this.recetas = recetas;
		this.catunidadesdetiempoByDuracionUnidadDeTiempoId = catunidadesdetiempoByDuracionUnidadDeTiempoId;
		this.catmedicamentos = catmedicamentos;
		this.catunidadesdetiempoByFrecuenciaUnidadDeTiempoId = catunidadesdetiempoByFrecuenciaUnidadDeTiempoId;
		this.cantidadUnidades = cantidadUnidades;
		this.frecuenciaDeToma = frecuenciaDeToma;
		this.duracionDeToma = duracionDeToma;
	}

	/** full constructor */
	public Medicamentosreceta(
			MedicamentosrecetaId id,
			Catviasdeadminmedicamento catviasdeadminmedicamento,
			Recetas recetas,
			Catunidadesdetiempo catunidadesdetiempoByDuracionUnidadDeTiempoId,
			Catmedicamentos catmedicamentos,
			Catunidadesdetiempo catunidadesdetiempoByFrecuenciaUnidadDeTiempoId,
			Integer cantidadUnidades, Integer frecuenciaDeToma,
			Integer duracionDeToma, String unidad, String observaciones,
			String dosisNo, String dosis, String folio,
			Set<Medicamentorecetaentregas> medicamentorecetaentregases) {
		this.id = id;
		this.catviasdeadminmedicamento = catviasdeadminmedicamento;
		this.recetas = recetas;
		this.catunidadesdetiempoByDuracionUnidadDeTiempoId = catunidadesdetiempoByDuracionUnidadDeTiempoId;
		this.catmedicamentos = catmedicamentos;
		this.catunidadesdetiempoByFrecuenciaUnidadDeTiempoId = catunidadesdetiempoByFrecuenciaUnidadDeTiempoId;
		this.cantidadUnidades = cantidadUnidades;
		this.frecuenciaDeToma = frecuenciaDeToma;
		this.duracionDeToma = duracionDeToma;
		this.unidad = unidad;
		this.observaciones = observaciones;
		this.dosisNo = dosisNo;
		this.dosis = dosis;
		this.folio = folio;
		this.medicamentorecetaentregases = medicamentorecetaentregases;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "recetaId", column = @Column(name = "RecetaId", nullable = false)),
			@AttributeOverride(name = "medicamentoId", column = @Column(name = "MedicamentoId", nullable = false)) })
	public MedicamentosrecetaId getId() {
		return this.id;
	}

	public void setId(MedicamentosrecetaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ViaDeAdmonMedicamentoId", nullable = false)
	public Catviasdeadminmedicamento getCatviasdeadminmedicamento() {
		return this.catviasdeadminmedicamento;
	}

	public void setCatviasdeadminmedicamento(
			Catviasdeadminmedicamento catviasdeadminmedicamento) {
		this.catviasdeadminmedicamento = catviasdeadminmedicamento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RecetaId", nullable = false, insertable = false, updatable = false)
	public Recetas getRecetas() {
		return this.recetas;
	}

	public void setRecetas(Recetas recetas) {
		this.recetas = recetas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DuracionUnidadDeTiempoId", nullable = false)
	public Catunidadesdetiempo getCatunidadesdetiempoByDuracionUnidadDeTiempoId() {
		return this.catunidadesdetiempoByDuracionUnidadDeTiempoId;
	}

	public void setCatunidadesdetiempoByDuracionUnidadDeTiempoId(
			Catunidadesdetiempo catunidadesdetiempoByDuracionUnidadDeTiempoId) {
		this.catunidadesdetiempoByDuracionUnidadDeTiempoId = catunidadesdetiempoByDuracionUnidadDeTiempoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MedicamentoId", nullable = false, insertable = false, updatable = false)
	public Catmedicamentos getCatmedicamentos() {
		return this.catmedicamentos;
	}

	public void setCatmedicamentos(Catmedicamentos catmedicamentos) {
		this.catmedicamentos = catmedicamentos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FrecuenciaUnidadDeTiempoId", nullable = false)
	public Catunidadesdetiempo getCatunidadesdetiempoByFrecuenciaUnidadDeTiempoId() {
		return this.catunidadesdetiempoByFrecuenciaUnidadDeTiempoId;
	}

	public void setCatunidadesdetiempoByFrecuenciaUnidadDeTiempoId(
			Catunidadesdetiempo catunidadesdetiempoByFrecuenciaUnidadDeTiempoId) {
		this.catunidadesdetiempoByFrecuenciaUnidadDeTiempoId = catunidadesdetiempoByFrecuenciaUnidadDeTiempoId;
	}

	@Column(name = "CantidadUnidades", nullable = false)
	public Integer getCantidadUnidades() {
		return this.cantidadUnidades;
	}

	public void setCantidadUnidades(Integer cantidadUnidades) {
		this.cantidadUnidades = cantidadUnidades;
	}

	@Column(name = "FrecuenciaDeToma", nullable = false)
	public Integer getFrecuenciaDeToma() {
		return this.frecuenciaDeToma;
	}

	public void setFrecuenciaDeToma(Integer frecuenciaDeToma) {
		this.frecuenciaDeToma = frecuenciaDeToma;
	}

	@Column(name = "DuracionDeToma", nullable = false)
	public Integer getDuracionDeToma() {
		return this.duracionDeToma;
	}

	public void setDuracionDeToma(Integer duracionDeToma) {
		this.duracionDeToma = duracionDeToma;
	}

	@Column(name = "Unidad", length = 30)
	public String getUnidad() {
		return this.unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	@Column(name = "Observaciones", length = 500)
	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Column(name = "DosisNo", length = 2)
	public String getDosisNo() {
		return this.dosisNo;
	}

	public void setDosisNo(String dosisNo) {
		this.dosisNo = dosisNo;
	}

	@Column(name = "Dosis", length = 30)
	public String getDosis() {
		return this.dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	@Column(name = "Folio", length = 11)
	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "medicamentosreceta")
	public Set<Medicamentorecetaentregas> getMedicamentorecetaentregases() {
		return this.medicamentorecetaentregases;
	}

	public void setMedicamentorecetaentregases(
			Set<Medicamentorecetaentregas> medicamentorecetaentregases) {
		this.medicamentorecetaentregases = medicamentorecetaentregases;
	}

}