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
 * Prestacionprestador entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "prestacionprestador", catalog = "my_db_rrg", uniqueConstraints = @UniqueConstraint(columnNames = {
		"Codigo", "Subcodigo", "PrestadorId" }))
public class Prestacionprestador implements java.io.Serializable {

	// Fields

	private Integer prestacionPrestadorId;
	private Prestadores prestadores;
	private Timestamp fechaHoraEtl;
	private String codigo;
	private String subcodigo;
	private String descripcion;
	private Set<Prestacionesprestadorequivalencias> prestacionesprestadorequivalenciases = new HashSet<Prestacionesprestadorequivalencias>(
			0);

	// Constructors

	/** default constructor */
	public Prestacionprestador() {
	}

	/** minimal constructor */
	public Prestacionprestador(Prestadores prestadores, Timestamp fechaHoraEtl,
			String codigo, String descripcion) {
		this.prestadores = prestadores;
		this.fechaHoraEtl = fechaHoraEtl;
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	/** full constructor */
	public Prestacionprestador(
			Prestadores prestadores,
			Timestamp fechaHoraEtl,
			String codigo,
			String subcodigo,
			String descripcion,
			Set<Prestacionesprestadorequivalencias> prestacionesprestadorequivalenciases) {
		this.prestadores = prestadores;
		this.fechaHoraEtl = fechaHoraEtl;
		this.codigo = codigo;
		this.subcodigo = subcodigo;
		this.descripcion = descripcion;
		this.prestacionesprestadorequivalenciases = prestacionesprestadorequivalenciases;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PrestacionPrestadorId", unique = true, nullable = false)
	public Integer getPrestacionPrestadorId() {
		return this.prestacionPrestadorId;
	}

	public void setPrestacionPrestadorId(Integer prestacionPrestadorId) {
		this.prestacionPrestadorId = prestacionPrestadorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PrestadorId", nullable = false)
	public Prestadores getPrestadores() {
		return this.prestadores;
	}

	public void setPrestadores(Prestadores prestadores) {
		this.prestadores = prestadores;
	}

	@Column(name = "FechaHoraETL", nullable = false, length = 19)
	public Timestamp getFechaHoraEtl() {
		return this.fechaHoraEtl;
	}

	public void setFechaHoraEtl(Timestamp fechaHoraEtl) {
		this.fechaHoraEtl = fechaHoraEtl;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prestacionprestador")
	public Set<Prestacionesprestadorequivalencias> getPrestacionesprestadorequivalenciases() {
		return this.prestacionesprestadorequivalenciases;
	}

	public void setPrestacionesprestadorequivalenciases(
			Set<Prestacionesprestadorequivalencias> prestacionesprestadorequivalenciases) {
		this.prestacionesprestadorequivalenciases = prestacionesprestadorequivalenciases;
	}

}