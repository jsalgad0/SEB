package com.mx.sab.model;
// default package

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
 * Permisoespecial entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "permisoespecial", catalog = "my_db_rrg")
public class Permisoespecial implements java.io.Serializable {

	// Fields

	private Integer permisoEspecialId;
	private Catcausas catcausas;
	private Cattipoautorizacion cattipoautorizacion;
	private Lugaresdeatencion lugaresdeatencion;
	private Aseguradores aseguradores;
	private Usuarios usuariosByUsuarioSolicitaId;
	private Usuarios usuariosByUsuarioAutorizaId;
	private Usuarios usuariosByUsuarioId;
	private Afiliado afiliado;
	private Integer duracion;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaAutorizacion;

	// Constructors

	/** default constructor */
	public Permisoespecial() {
	}

	/** full constructor */
	public Permisoespecial(Catcausas catcausas,
			Cattipoautorizacion cattipoautorizacion,
			Lugaresdeatencion lugaresdeatencion, Aseguradores aseguradores,
			Usuarios usuariosByUsuarioSolicitaId,
			Usuarios usuariosByUsuarioAutorizaId, Usuarios usuariosByUsuarioId,
			Afiliado afiliado, Integer duracion, Date fechaInicio,
			Date fechaFin, Date fechaAutorizacion) {
		this.catcausas = catcausas;
		this.cattipoautorizacion = cattipoautorizacion;
		this.lugaresdeatencion = lugaresdeatencion;
		this.aseguradores = aseguradores;
		this.usuariosByUsuarioSolicitaId = usuariosByUsuarioSolicitaId;
		this.usuariosByUsuarioAutorizaId = usuariosByUsuarioAutorizaId;
		this.usuariosByUsuarioId = usuariosByUsuarioId;
		this.afiliado = afiliado;
		this.duracion = duracion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaAutorizacion = fechaAutorizacion;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PermisoEspecialId", unique = true, nullable = false)
	public Integer getPermisoEspecialId() {
		return this.permisoEspecialId;
	}

	public void setPermisoEspecialId(Integer permisoEspecialId) {
		this.permisoEspecialId = permisoEspecialId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CausaId")
	public Catcausas getCatcausas() {
		return this.catcausas;
	}

	public void setCatcausas(Catcausas catcausas) {
		this.catcausas = catcausas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoAutorizacion")
	public Cattipoautorizacion getCattipoautorizacion() {
		return this.cattipoautorizacion;
	}

	public void setCattipoautorizacion(Cattipoautorizacion cattipoautorizacion) {
		this.cattipoautorizacion = cattipoautorizacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LugarDeAtencionId")
	public Lugaresdeatencion getLugaresdeatencion() {
		return this.lugaresdeatencion;
	}

	public void setLugaresdeatencion(Lugaresdeatencion lugaresdeatencion) {
		this.lugaresdeatencion = lugaresdeatencion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AseguradorId")
	public Aseguradores getAseguradores() {
		return this.aseguradores;
	}

	public void setAseguradores(Aseguradores aseguradores) {
		this.aseguradores = aseguradores;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioSolicitaId")
	public Usuarios getUsuariosByUsuarioSolicitaId() {
		return this.usuariosByUsuarioSolicitaId;
	}

	public void setUsuariosByUsuarioSolicitaId(
			Usuarios usuariosByUsuarioSolicitaId) {
		this.usuariosByUsuarioSolicitaId = usuariosByUsuarioSolicitaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioAutorizaId")
	public Usuarios getUsuariosByUsuarioAutorizaId() {
		return this.usuariosByUsuarioAutorizaId;
	}

	public void setUsuariosByUsuarioAutorizaId(
			Usuarios usuariosByUsuarioAutorizaId) {
		this.usuariosByUsuarioAutorizaId = usuariosByUsuarioAutorizaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioId")
	public Usuarios getUsuariosByUsuarioId() {
		return this.usuariosByUsuarioId;
	}

	public void setUsuariosByUsuarioId(Usuarios usuariosByUsuarioId) {
		this.usuariosByUsuarioId = usuariosByUsuarioId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AfiliadoId")
	public Afiliado getAfiliado() {
		return this.afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	@Column(name = "Duracion")
	public Integer getDuracion() {
		return this.duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaInicio", length = 10)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaFin", length = 10)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaAutorizacion", length = 10)
	public Date getFechaAutorizacion() {
		return this.fechaAutorizacion;
	}

	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}

}