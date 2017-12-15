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
 * Motivos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "motivos", catalog = "my_db_rrg")
public class Motivos implements java.io.Serializable {

	// Fields

	private Integer id;
	private Atencionmedica atencionmedica;
	private Catmotivos catmotivos;
	private String motivo;

	// Constructors

	/** default constructor */
	public Motivos() {
	}

	/** full constructor */
	public Motivos(Atencionmedica atencionmedica, Catmotivos catmotivos,
			String motivo) {
		this.atencionmedica = atencionmedica;
		this.catmotivos = catmotivos;
		this.motivo = motivo;
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
	@JoinColumn(name = "IdAtencionMedica")
	public Atencionmedica getAtencionmedica() {
		return this.atencionmedica;
	}

	public void setAtencionmedica(Atencionmedica atencionmedica) {
		this.atencionmedica = atencionmedica;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCatMotivo")
	public Catmotivos getCatmotivos() {
		return this.catmotivos;
	}

	public void setCatmotivos(Catmotivos catmotivos) {
		this.catmotivos = catmotivos;
	}

	@Column(name = "motivo", length = 200)
	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}