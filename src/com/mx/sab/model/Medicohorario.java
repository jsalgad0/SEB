package com.mx.sab.model;
// default package

import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Medicohorario entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "medicohorario", catalog = "my_db_rrg")
public class Medicohorario implements java.io.Serializable {

	// Fields

	private Integer usuarioId;
	private Usuarios usuarios;
	private Time horaEntrada;
	private Time horaSalida;
	private Integer esMedicoDesahogador;
	private Integer activo;

	// Constructors

	/** default constructor */
	public Medicohorario() {
	}

	/** full constructor */
	public Medicohorario(Integer usuarioId, Usuarios usuarios,
			Time horaEntrada, Time horaSalida, Integer esMedicoDesahogador,
			Integer activo) {
		this.usuarioId = usuarioId;
		this.usuarios = usuarios;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.esMedicoDesahogador = esMedicoDesahogador;
		this.activo = activo;
	}

	// Property accessors
	@Id
	@Column(name = "UsuarioId", unique = true, nullable = false)
	public Integer getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioId", unique = true, nullable = false, insertable = false, updatable = false)
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@Column(name = "HoraEntrada", nullable = false, length = 8)
	public Time getHoraEntrada() {
		return this.horaEntrada;
	}

	public void setHoraEntrada(Time horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	@Column(name = "HoraSalida", nullable = false, length = 8)
	public Time getHoraSalida() {
		return this.horaSalida;
	}

	public void setHoraSalida(Time horaSalida) {
		this.horaSalida = horaSalida;
	}

	@Column(name = "EsMedicoDesahogador", nullable = false)
	public Integer getEsMedicoDesahogador() {
		return this.esMedicoDesahogador;
	}

	public void setEsMedicoDesahogador(Integer esMedicoDesahogador) {
		this.esMedicoDesahogador = esMedicoDesahogador;
	}

	@Column(name = "Activo", nullable = false)
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

}