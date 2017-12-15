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
 * Catviasdeadminmedicamento entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catviasdeadminmedicamento", catalog = "my_db_rrg")
public class Catviasdeadminmedicamento implements java.io.Serializable {

	// Fields

	private Integer viaDeAdmonMedicamentoId;
	private String viaDeAdmonMedicamento;
	private String descripcion;
	private String abrev;
	private Integer activo;
	private Set<Medicamentosreceta> medicamentosrecetas = new HashSet<Medicamentosreceta>(
			0);

	// Constructors

	/** default constructor */
	public Catviasdeadminmedicamento() {
	}

	/** minimal constructor */
	public Catviasdeadminmedicamento(String viaDeAdmonMedicamento,
			Integer activo) {
		this.viaDeAdmonMedicamento = viaDeAdmonMedicamento;
		this.activo = activo;
	}

	/** full constructor */
	public Catviasdeadminmedicamento(String viaDeAdmonMedicamento,
			String descripcion, String abrev, Integer activo,
			Set<Medicamentosreceta> medicamentosrecetas) {
		this.viaDeAdmonMedicamento = viaDeAdmonMedicamento;
		this.descripcion = descripcion;
		this.abrev = abrev;
		this.activo = activo;
		this.medicamentosrecetas = medicamentosrecetas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ViaDeAdmonMedicamentoId", unique = true, nullable = false)
	public Integer getViaDeAdmonMedicamentoId() {
		return this.viaDeAdmonMedicamentoId;
	}

	public void setViaDeAdmonMedicamentoId(Integer viaDeAdmonMedicamentoId) {
		this.viaDeAdmonMedicamentoId = viaDeAdmonMedicamentoId;
	}

	@Column(name = "ViaDeAdmonMedicamento", nullable = false, length = 80)
	public String getViaDeAdmonMedicamento() {
		return this.viaDeAdmonMedicamento;
	}

	public void setViaDeAdmonMedicamento(String viaDeAdmonMedicamento) {
		this.viaDeAdmonMedicamento = viaDeAdmonMedicamento;
	}

	@Column(name = "Descripcion", length = 2000)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "Abrev", length = 5)
	public String getAbrev() {
		return this.abrev;
	}

	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catviasdeadminmedicamento")
	public Set<Medicamentosreceta> getMedicamentosrecetas() {
		return this.medicamentosrecetas;
	}

	public void setMedicamentosrecetas(
			Set<Medicamentosreceta> medicamentosrecetas) {
		this.medicamentosrecetas = medicamentosrecetas;
	}

}