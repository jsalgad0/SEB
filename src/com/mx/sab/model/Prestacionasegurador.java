package com.mx.sab.model;
// default package

import java.sql.Timestamp;
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
import javax.persistence.UniqueConstraint;

/**
 * Prestacionasegurador entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "prestacionasegurador", catalog = "my_db_rrg", uniqueConstraints = @UniqueConstraint(columnNames = {
		"Codigo", "Subcodigo", "AseguradorId" }))
public class Prestacionasegurador implements java.io.Serializable {

	// Fields

	private Integer prestacionAseguradorId;
	private Aseguradores aseguradores;
	private String codigo;
	private String subcodigo;
	private String descripcion;
	private Timestamp fechaHoraEtl;
	private Integer servicioId;
	private Set<Prestacionesaseguradorequivalencias> prestacionesaseguradorequivalenciases = new HashSet<Prestacionesaseguradorequivalencias>(
			0);

	// Constructors

	/** default constructor */
	public Prestacionasegurador() {
	}

	/** minimal constructor */
	public Prestacionasegurador(Aseguradores aseguradores, String codigo,
			String descripcion, Timestamp fechaHoraEtl) {
		this.aseguradores = aseguradores;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.fechaHoraEtl = fechaHoraEtl;
	}

	/** full constructor */
	public Prestacionasegurador(
			Aseguradores aseguradores,
			String codigo,
			String subcodigo,
			String descripcion,
			Timestamp fechaHoraEtl,
			Integer servicioId,
			Set<Prestacionesaseguradorequivalencias> prestacionesaseguradorequivalenciases) {
		this.aseguradores = aseguradores;
		this.codigo = codigo;
		this.subcodigo = subcodigo;
		this.descripcion = descripcion;
		this.fechaHoraEtl = fechaHoraEtl;
		this.servicioId = servicioId;
		this.prestacionesaseguradorequivalenciases = prestacionesaseguradorequivalenciases;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PrestacionAseguradorId", unique = true, nullable = false)
	public Integer getPrestacionAseguradorId() {
		return this.prestacionAseguradorId;
	}

	public void setPrestacionAseguradorId(Integer prestacionAseguradorId) {
		this.prestacionAseguradorId = prestacionAseguradorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AseguradorId", nullable = false)
	public Aseguradores getAseguradores() {
		return this.aseguradores;
	}

	public void setAseguradores(Aseguradores aseguradores) {
		this.aseguradores = aseguradores;
	}

	@Column(name = "Codigo", nullable = false, length = 20)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "Subcodigo", length = 11)
	public String getSubcodigo() {
		return this.subcodigo;
	}

	public void setSubcodigo(String subcodigo) {
		this.subcodigo = subcodigo;
	}

	@Column(name = "Descripcion", nullable = false)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "FechaHoraETL", nullable = false, length = 19)
	public Timestamp getFechaHoraEtl() {
		return this.fechaHoraEtl;
	}

	public void setFechaHoraEtl(Timestamp fechaHoraEtl) {
		this.fechaHoraEtl = fechaHoraEtl;
	}

	@Column(name = "ServicioId")
	public Integer getServicioId() {
		return this.servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prestacionasegurador")
	public Set<Prestacionesaseguradorequivalencias> getPrestacionesaseguradorequivalenciases() {
		return this.prestacionesaseguradorequivalenciases;
	}

	public void setPrestacionesaseguradorequivalenciases(
			Set<Prestacionesaseguradorequivalencias> prestacionesaseguradorequivalenciases) {
		this.prestacionesaseguradorequivalenciases = prestacionesaseguradorequivalenciases;
	}

}