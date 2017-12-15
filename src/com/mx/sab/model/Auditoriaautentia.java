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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Auditoriaautentia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "auditoriaautentia", catalog = "my_db_rrg")
public class Auditoriaautentia implements java.io.Serializable {

	// Fields

	private Integer auditoriaId;
	private Integer tipoAuditoriaId;
	private Integer usuarioId;
	private Integer afiliadoId;
	private Date fecha;
	private String numeroAuditoria;
	private String mensajeAuditoria;
	private Set<Atencionmedica> atencionmedicasForAuditoriaAutentiaMedicoId = new HashSet<Atencionmedica>(
			0);
	private Set<Atencionmedica> atencionmedicasForAuditoriaAutentiaPacienteId = new HashSet<Atencionmedica>(
			0);

	// Constructors

	/** default constructor */
	public Auditoriaautentia() {
	}

	/** full constructor */
	public Auditoriaautentia(Integer tipoAuditoriaId, Integer usuarioId,
			Integer afiliadoId, Date fecha, String numeroAuditoria,
			String mensajeAuditoria,
			Set<Atencionmedica> atencionmedicasForAuditoriaAutentiaMedicoId,
			Set<Atencionmedica> atencionmedicasForAuditoriaAutentiaPacienteId) {
		this.tipoAuditoriaId = tipoAuditoriaId;
		this.usuarioId = usuarioId;
		this.afiliadoId = afiliadoId;
		this.fecha = fecha;
		this.numeroAuditoria = numeroAuditoria;
		this.mensajeAuditoria = mensajeAuditoria;
		this.atencionmedicasForAuditoriaAutentiaMedicoId = atencionmedicasForAuditoriaAutentiaMedicoId;
		this.atencionmedicasForAuditoriaAutentiaPacienteId = atencionmedicasForAuditoriaAutentiaPacienteId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AuditoriaId", unique = true, nullable = false)
	public Integer getAuditoriaId() {
		return this.auditoriaId;
	}

	public void setAuditoriaId(Integer auditoriaId) {
		this.auditoriaId = auditoriaId;
	}

	@Column(name = "TipoAuditoriaId")
	public Integer getTipoAuditoriaId() {
		return this.tipoAuditoriaId;
	}

	public void setTipoAuditoriaId(Integer tipoAuditoriaId) {
		this.tipoAuditoriaId = tipoAuditoriaId;
	}

	@Column(name = "UsuarioId")
	public Integer getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Column(name = "AfiliadoId")
	public Integer getAfiliadoId() {
		return this.afiliadoId;
	}

	public void setAfiliadoId(Integer afiliadoId) {
		this.afiliadoId = afiliadoId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Fecha", length = 10)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "NumeroAuditoria", length = 30)
	public String getNumeroAuditoria() {
		return this.numeroAuditoria;
	}

	public void setNumeroAuditoria(String numeroAuditoria) {
		this.numeroAuditoria = numeroAuditoria;
	}

	@Column(name = "MensajeAuditoria", length = 150)
	public String getMensajeAuditoria() {
		return this.mensajeAuditoria;
	}

	public void setMensajeAuditoria(String mensajeAuditoria) {
		this.mensajeAuditoria = mensajeAuditoria;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auditoriaautentiaByAuditoriaAutentiaMedicoId")
	public Set<Atencionmedica> getAtencionmedicasForAuditoriaAutentiaMedicoId() {
		return this.atencionmedicasForAuditoriaAutentiaMedicoId;
	}

	public void setAtencionmedicasForAuditoriaAutentiaMedicoId(
			Set<Atencionmedica> atencionmedicasForAuditoriaAutentiaMedicoId) {
		this.atencionmedicasForAuditoriaAutentiaMedicoId = atencionmedicasForAuditoriaAutentiaMedicoId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auditoriaautentiaByAuditoriaAutentiaPacienteId")
	public Set<Atencionmedica> getAtencionmedicasForAuditoriaAutentiaPacienteId() {
		return this.atencionmedicasForAuditoriaAutentiaPacienteId;
	}

	public void setAtencionmedicasForAuditoriaAutentiaPacienteId(
			Set<Atencionmedica> atencionmedicasForAuditoriaAutentiaPacienteId) {
		this.atencionmedicasForAuditoriaAutentiaPacienteId = atencionmedicasForAuditoriaAutentiaPacienteId;
	}

}