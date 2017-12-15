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
 * Usuarioauditoria entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuarioauditoria", catalog = "my_db_rrg")
public class Usuarioauditoria implements java.io.Serializable {

	// Fields

	private Integer id;
	private Usuarios usuariosByUsuarioIdModificado;
	private Catmodificacionusuario catmodificacionusuario;
	private Usuarios usuariosByUsuarioId;
	private Date fechaModificacion;
	private String valorAnterior;
	private String valorNuevo;

	// Constructors

	/** default constructor */
	public Usuarioauditoria() {
	}

	/** full constructor */
	public Usuarioauditoria(Usuarios usuariosByUsuarioIdModificado,
			Catmodificacionusuario catmodificacionusuario,
			Usuarios usuariosByUsuarioId, Date fechaModificacion,
			String valorAnterior, String valorNuevo) {
		this.usuariosByUsuarioIdModificado = usuariosByUsuarioIdModificado;
		this.catmodificacionusuario = catmodificacionusuario;
		this.usuariosByUsuarioId = usuariosByUsuarioId;
		this.fechaModificacion = fechaModificacion;
		this.valorAnterior = valorAnterior;
		this.valorNuevo = valorNuevo;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioIdModificado")
	public Usuarios getUsuariosByUsuarioIdModificado() {
		return this.usuariosByUsuarioIdModificado;
	}

	public void setUsuariosByUsuarioIdModificado(
			Usuarios usuariosByUsuarioIdModificado) {
		this.usuariosByUsuarioIdModificado = usuariosByUsuarioIdModificado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatModificacionUsuarioId")
	public Catmodificacionusuario getCatmodificacionusuario() {
		return this.catmodificacionusuario;
	}

	public void setCatmodificacionusuario(
			Catmodificacionusuario catmodificacionusuario) {
		this.catmodificacionusuario = catmodificacionusuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioId")
	public Usuarios getUsuariosByUsuarioId() {
		return this.usuariosByUsuarioId;
	}

	public void setUsuariosByUsuarioId(Usuarios usuariosByUsuarioId) {
		this.usuariosByUsuarioId = usuariosByUsuarioId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaModificacion", length = 10)
	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	@Column(name = "ValorAnterior", length = 200)
	public String getValorAnterior() {
		return this.valorAnterior;
	}

	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	@Column(name = "ValorNuevo", length = 200)
	public String getValorNuevo() {
		return this.valorNuevo;
	}

	public void setValorNuevo(String valorNuevo) {
		this.valorNuevo = valorNuevo;
	}

}