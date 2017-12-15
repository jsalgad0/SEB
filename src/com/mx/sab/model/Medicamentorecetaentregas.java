package com.mx.sab.model;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Medicamentorecetaentregas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "medicamentorecetaentregas", catalog = "my_db_rrg")
public class Medicamentorecetaentregas implements java.io.Serializable {

	// Fields

	private MedicamentorecetaentregasId id;
	private Medicamentosreceta medicamentosreceta;

	// Constructors

	/** default constructor */
	public Medicamentorecetaentregas() {
	}

	/** full constructor */
	public Medicamentorecetaentregas(MedicamentorecetaentregasId id,
			Medicamentosreceta medicamentosreceta) {
		this.id = id;
		this.medicamentosreceta = medicamentosreceta;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "recetaId", column = @Column(name = "RecetaId", nullable = false)),
			@AttributeOverride(name = "medicamentoId", column = @Column(name = "MedicamentoId", nullable = false)),
			@AttributeOverride(name = "fechaEntrega", column = @Column(name = "FechaEntrega", nullable = false, length = 10)),
			@AttributeOverride(name = "horaEntrega", column = @Column(name = "HoraEntrega", nullable = false, length = 8)),
			@AttributeOverride(name = "cantidadEntregada", column = @Column(name = "CantidadEntregada", nullable = false)),
			@AttributeOverride(name = "auditoriaAutentiaPaciente", column = @Column(name = "AuditoriaAutentiaPaciente", length = 50)) })
	public MedicamentorecetaentregasId getId() {
		return this.id;
	}

	public void setId(MedicamentorecetaentregasId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "RecetaId", referencedColumnName = "RecetaId", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "MedicamentoId", referencedColumnName = "MedicamentoId", nullable = false, insertable = false, updatable = false) })
	public Medicamentosreceta getMedicamentosreceta() {
		return this.medicamentosreceta;
	}

	public void setMedicamentosreceta(Medicamentosreceta medicamentosreceta) {
		this.medicamentosreceta = medicamentosreceta;
	}

}