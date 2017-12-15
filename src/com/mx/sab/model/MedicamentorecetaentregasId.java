package com.mx.sab.model;
// default package

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MedicamentorecetaentregasId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class MedicamentorecetaentregasId implements java.io.Serializable {

	// Fields

	private Integer recetaId;
	private Integer medicamentoId;
	private Date fechaEntrega;
	private Time horaEntrega;
	private Integer cantidadEntregada;
	private String auditoriaAutentiaPaciente;

	// Constructors

	/** default constructor */
	public MedicamentorecetaentregasId() {
	}

	/** minimal constructor */
	public MedicamentorecetaentregasId(Integer recetaId, Integer medicamentoId,
			Date fechaEntrega, Time horaEntrega, Integer cantidadEntregada) {
		this.recetaId = recetaId;
		this.medicamentoId = medicamentoId;
		this.fechaEntrega = fechaEntrega;
		this.horaEntrega = horaEntrega;
		this.cantidadEntregada = cantidadEntregada;
	}

	/** full constructor */
	public MedicamentorecetaentregasId(Integer recetaId, Integer medicamentoId,
			Date fechaEntrega, Time horaEntrega, Integer cantidadEntregada,
			String auditoriaAutentiaPaciente) {
		this.recetaId = recetaId;
		this.medicamentoId = medicamentoId;
		this.fechaEntrega = fechaEntrega;
		this.horaEntrega = horaEntrega;
		this.cantidadEntregada = cantidadEntregada;
		this.auditoriaAutentiaPaciente = auditoriaAutentiaPaciente;
	}

	// Property accessors

	@Column(name = "RecetaId", nullable = false)
	public Integer getRecetaId() {
		return this.recetaId;
	}

	public void setRecetaId(Integer recetaId) {
		this.recetaId = recetaId;
	}

	@Column(name = "MedicamentoId", nullable = false)
	public Integer getMedicamentoId() {
		return this.medicamentoId;
	}

	public void setMedicamentoId(Integer medicamentoId) {
		this.medicamentoId = medicamentoId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaEntrega", nullable = false, length = 10)
	public Date getFechaEntrega() {
		return this.fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	@Column(name = "HoraEntrega", nullable = false, length = 8)
	public Time getHoraEntrega() {
		return this.horaEntrega;
	}

	public void setHoraEntrega(Time horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	@Column(name = "CantidadEntregada", nullable = false)
	public Integer getCantidadEntregada() {
		return this.cantidadEntregada;
	}

	public void setCantidadEntregada(Integer cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}

	@Column(name = "AuditoriaAutentiaPaciente", length = 50)
	public String getAuditoriaAutentiaPaciente() {
		return this.auditoriaAutentiaPaciente;
	}

	public void setAuditoriaAutentiaPaciente(String auditoriaAutentiaPaciente) {
		this.auditoriaAutentiaPaciente = auditoriaAutentiaPaciente;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MedicamentorecetaentregasId))
			return false;
		MedicamentorecetaentregasId castOther = (MedicamentorecetaentregasId) other;

		return ((this.getRecetaId() == castOther.getRecetaId()) || (this
				.getRecetaId() != null && castOther.getRecetaId() != null && this
				.getRecetaId().equals(castOther.getRecetaId())))
				&& ((this.getMedicamentoId() == castOther.getMedicamentoId()) || (this
						.getMedicamentoId() != null
						&& castOther.getMedicamentoId() != null && this
						.getMedicamentoId()
						.equals(castOther.getMedicamentoId())))
				&& ((this.getFechaEntrega() == castOther.getFechaEntrega()) || (this
						.getFechaEntrega() != null
						&& castOther.getFechaEntrega() != null && this
						.getFechaEntrega().equals(castOther.getFechaEntrega())))
				&& ((this.getHoraEntrega() == castOther.getHoraEntrega()) || (this
						.getHoraEntrega() != null
						&& castOther.getHoraEntrega() != null && this
						.getHoraEntrega().equals(castOther.getHoraEntrega())))
				&& ((this.getCantidadEntregada() == castOther
						.getCantidadEntregada()) || (this
						.getCantidadEntregada() != null
						&& castOther.getCantidadEntregada() != null && this
						.getCantidadEntregada().equals(
								castOther.getCantidadEntregada())))
				&& ((this.getAuditoriaAutentiaPaciente() == castOther
						.getAuditoriaAutentiaPaciente()) || (this
						.getAuditoriaAutentiaPaciente() != null
						&& castOther.getAuditoriaAutentiaPaciente() != null && this
						.getAuditoriaAutentiaPaciente().equals(
								castOther.getAuditoriaAutentiaPaciente())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRecetaId() == null ? 0 : this.getRecetaId().hashCode());
		result = 37
				* result
				+ (getMedicamentoId() == null ? 0 : this.getMedicamentoId()
						.hashCode());
		result = 37
				* result
				+ (getFechaEntrega() == null ? 0 : this.getFechaEntrega()
						.hashCode());
		result = 37
				* result
				+ (getHoraEntrega() == null ? 0 : this.getHoraEntrega()
						.hashCode());
		result = 37
				* result
				+ (getCantidadEntregada() == null ? 0 : this
						.getCantidadEntregada().hashCode());
		result = 37
				* result
				+ (getAuditoriaAutentiaPaciente() == null ? 0 : this
						.getAuditoriaAutentiaPaciente().hashCode());
		return result;
	}

}