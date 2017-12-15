package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Prestacionesportomar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "prestacionesportomar", catalog = "my_db_rrg")
public class Prestacionesportomar implements java.io.Serializable {

	// Fields

	private Integer id;
	private Catpaginasestudios catpaginasestudios;
	private Atencionmedica atencionmedicaByAtencionMedicaIdTomada;
	private Catprestacion catprestacion;
	private Atencionmedica atencionmedicaByAtencionMedicaId;
	private String orden;
	private Integer cantidad;

	// Constructors

	/** default constructor */
	public Prestacionesportomar() {
	}

	/** full constructor */
	public Prestacionesportomar(Catpaginasestudios catpaginasestudios,
			Atencionmedica atencionmedicaByAtencionMedicaIdTomada,
			Catprestacion catprestacion,
			Atencionmedica atencionmedicaByAtencionMedicaId, String orden,
			Integer cantidad) {
		this.catpaginasestudios = catpaginasestudios;
		this.atencionmedicaByAtencionMedicaIdTomada = atencionmedicaByAtencionMedicaIdTomada;
		this.catprestacion = catprestacion;
		this.atencionmedicaByAtencionMedicaId = atencionmedicaByAtencionMedicaId;
		this.orden = orden;
		this.cantidad = cantidad;
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
	@JoinColumn(name = "IdPagina")
	public Catpaginasestudios getCatpaginasestudios() {
		return this.catpaginasestudios;
	}

	public void setCatpaginasestudios(Catpaginasestudios catpaginasestudios) {
		this.catpaginasestudios = catpaginasestudios;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AtencionMedicaIdTomada")
	public Atencionmedica getAtencionmedicaByAtencionMedicaIdTomada() {
		return this.atencionmedicaByAtencionMedicaIdTomada;
	}

	public void setAtencionmedicaByAtencionMedicaIdTomada(
			Atencionmedica atencionmedicaByAtencionMedicaIdTomada) {
		this.atencionmedicaByAtencionMedicaIdTomada = atencionmedicaByAtencionMedicaIdTomada;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PrestacionId")
	public Catprestacion getCatprestacion() {
		return this.catprestacion;
	}

	public void setCatprestacion(Catprestacion catprestacion) {
		this.catprestacion = catprestacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AtencionMedicaId")
	public Atencionmedica getAtencionmedicaByAtencionMedicaId() {
		return this.atencionmedicaByAtencionMedicaId;
	}

	public void setAtencionmedicaByAtencionMedicaId(
			Atencionmedica atencionmedicaByAtencionMedicaId) {
		this.atencionmedicaByAtencionMedicaId = atencionmedicaByAtencionMedicaId;
	}

	@Column(name = "Orden", length = 20)
	public String getOrden() {
		return this.orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	@Column(name = "Cantidad")
	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}