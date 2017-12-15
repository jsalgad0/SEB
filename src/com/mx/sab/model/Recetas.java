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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Recetas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "recetas", catalog = "my_db_rrg")
public class Recetas implements java.io.Serializable {

	// Fields

	private Integer recetaId;
	private Catestatusrecetas catestatusrecetas;
	private Atencionmedica atencionmedica;
	private Integer folio;
	private Integer resurtible;
	private Integer cada;
	private Integer durante;
	private Set<Medicamentosreceta> medicamentosrecetas = new HashSet<Medicamentosreceta>(
			0);

	// Constructors

	/** default constructor */
	public Recetas() {
	}

	/** minimal constructor */
	public Recetas(Catestatusrecetas catestatusrecetas,
			Atencionmedica atencionmedica, Integer folio, Integer resurtible) {
		this.catestatusrecetas = catestatusrecetas;
		this.atencionmedica = atencionmedica;
		this.folio = folio;
		this.resurtible = resurtible;
	}

	/** full constructor */
	public Recetas(Catestatusrecetas catestatusrecetas,
			Atencionmedica atencionmedica, Integer folio, Integer resurtible,
			Integer cada, Integer durante,
			Set<Medicamentosreceta> medicamentosrecetas) {
		this.catestatusrecetas = catestatusrecetas;
		this.atencionmedica = atencionmedica;
		this.folio = folio;
		this.resurtible = resurtible;
		this.cada = cada;
		this.durante = durante;
		this.medicamentosrecetas = medicamentosrecetas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RecetaId", unique = true, nullable = false)
	public Integer getRecetaId() {
		return this.recetaId;
	}

	public void setRecetaId(Integer recetaId) {
		this.recetaId = recetaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstatusRecetaId", nullable = false)
	public Catestatusrecetas getCatestatusrecetas() {
		return this.catestatusrecetas;
	}

	public void setCatestatusrecetas(Catestatusrecetas catestatusrecetas) {
		this.catestatusrecetas = catestatusrecetas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AtencionMedicaId", nullable = false)
	public Atencionmedica getAtencionmedica() {
		return this.atencionmedica;
	}

	public void setAtencionmedica(Atencionmedica atencionmedica) {
		this.atencionmedica = atencionmedica;
	}

	@Column(name = "Folio", nullable = false)
	public Integer getFolio() {
		return this.folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	@Column(name = "Resurtible", nullable = false)
	public Integer getResurtible() {
		return this.resurtible;
	}

	public void setResurtible(Integer resurtible) {
		this.resurtible = resurtible;
	}

	@Column(name = "Cada")
	public Integer getCada() {
		return this.cada;
	}

	public void setCada(Integer cada) {
		this.cada = cada;
	}

	@Column(name = "Durante")
	public Integer getDurante() {
		return this.durante;
	}

	public void setDurante(Integer durante) {
		this.durante = durante;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "recetas")
	public Set<Medicamentosreceta> getMedicamentosrecetas() {
		return this.medicamentosrecetas;
	}

	public void setMedicamentosrecetas(
			Set<Medicamentosreceta> medicamentosrecetas) {
		this.medicamentosrecetas = medicamentosrecetas;
	}

}