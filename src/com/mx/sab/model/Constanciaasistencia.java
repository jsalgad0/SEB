package com.mx.sab.model;
// default package

import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Constanciaasistencia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "constanciaasistencia", catalog = "my_db_rrg")
public class Constanciaasistencia implements java.io.Serializable {

	// Fields

	private Integer constanciaAsistenciaId;
	private Integer atencionMedicaId;
	private Time horaInicioAsistencia;
	private Time horaFinAsistencia;

	// Constructors

	/** default constructor */
	public Constanciaasistencia() {
	}

	/** full constructor */
	public Constanciaasistencia(Integer atencionMedicaId,
			Time horaInicioAsistencia, Time horaFinAsistencia) {
		this.atencionMedicaId = atencionMedicaId;
		this.horaInicioAsistencia = horaInicioAsistencia;
		this.horaFinAsistencia = horaFinAsistencia;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ConstanciaAsistenciaId", unique = true, nullable = false)
	public Integer getConstanciaAsistenciaId() {
		return this.constanciaAsistenciaId;
	}

	public void setConstanciaAsistenciaId(Integer constanciaAsistenciaId) {
		this.constanciaAsistenciaId = constanciaAsistenciaId;
	}

	@Column(name = "AtencionMedicaId", nullable = false)
	public Integer getAtencionMedicaId() {
		return this.atencionMedicaId;
	}

	public void setAtencionMedicaId(Integer atencionMedicaId) {
		this.atencionMedicaId = atencionMedicaId;
	}

	@Column(name = "HoraInicioAsistencia", nullable = false, length = 8)
	public Time getHoraInicioAsistencia() {
		return this.horaInicioAsistencia;
	}

	public void setHoraInicioAsistencia(Time horaInicioAsistencia) {
		this.horaInicioAsistencia = horaInicioAsistencia;
	}

	@Column(name = "HoraFinAsistencia", nullable = false, length = 8)
	public Time getHoraFinAsistencia() {
		return this.horaFinAsistencia;
	}

	public void setHoraFinAsistencia(Time horaFinAsistencia) {
		this.horaFinAsistencia = horaFinAsistencia;
	}

}