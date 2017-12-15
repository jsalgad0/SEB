package com.mx.sab.model;
// default package

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Loginlog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "loginlog", catalog = "my_db_rrg")
public class Loginlog implements java.io.Serializable {

	// Fields

	private Integer loginId;
	private Usuarios usuarios;
	private Date fecha;
	private Time hora;
	private String auditoriaAutentia;
	private Integer conAutorizacionEspecial;
	private Integer sinHuella;

	// Constructors

	/** default constructor */
	public Loginlog() {
	}

	/** minimal constructor */
	public Loginlog(Integer conAutorizacionEspecial, Integer sinHuella) {
		this.conAutorizacionEspecial = conAutorizacionEspecial;
		this.sinHuella = sinHuella;
	}

	/** full constructor */
	public Loginlog(Usuarios usuarios, Date fecha, Time hora,
			String auditoriaAutentia, Integer conAutorizacionEspecial,
			Integer sinHuella) {
		this.usuarios = usuarios;
		this.fecha = fecha;
		this.hora = hora;
		this.auditoriaAutentia = auditoriaAutentia;
		this.conAutorizacionEspecial = conAutorizacionEspecial;
		this.sinHuella = sinHuella;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LoginId", unique = true, nullable = false)
	public Integer getLoginId() {
		return this.loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioId")
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Fecha", length = 10)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "Hora", length = 8)
	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	@Column(name = "AuditoriaAutentia", length = 50)
	public String getAuditoriaAutentia() {
		return this.auditoriaAutentia;
	}

	public void setAuditoriaAutentia(String auditoriaAutentia) {
		this.auditoriaAutentia = auditoriaAutentia;
	}

	@Column(name = "ConAutorizacionEspecial", nullable = false)
	public Integer getConAutorizacionEspecial() {
		return this.conAutorizacionEspecial;
	}

	public void setConAutorizacionEspecial(Integer conAutorizacionEspecial) {
		this.conAutorizacionEspecial = conAutorizacionEspecial;
	}

	@Column(name = "SinHuella", nullable = false)
	public Integer getSinHuella() {
		return this.sinHuella;
	}

	public void setSinHuella(Integer sinHuella) {
		this.sinHuella = sinHuella;
	}

}