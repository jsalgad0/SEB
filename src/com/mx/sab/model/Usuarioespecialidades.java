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
 * Usuarioespecialidades entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuarioespecialidades", catalog = "my_db_rrg")
public class Usuarioespecialidades implements java.io.Serializable {

	// Fields

	private Integer id;
	private Catespecialidadesmedicas catespecialidadesmedicas;
	private Usuarios usuarios;
	private String cedulaEspecialidad;
	private String institucionEducativaId;

	// Constructors

	/** default constructor */
	public Usuarioespecialidades() {
	}

	/** minimal constructor */
	public Usuarioespecialidades(
			Catespecialidadesmedicas catespecialidadesmedicas,
			Usuarios usuarios, String cedulaEspecialidad) {
		this.catespecialidadesmedicas = catespecialidadesmedicas;
		this.usuarios = usuarios;
		this.cedulaEspecialidad = cedulaEspecialidad;
	}

	/** full constructor */
	public Usuarioespecialidades(
			Catespecialidadesmedicas catespecialidadesmedicas,
			Usuarios usuarios, String cedulaEspecialidad,
			String institucionEducativaId) {
		this.catespecialidadesmedicas = catespecialidadesmedicas;
		this.usuarios = usuarios;
		this.cedulaEspecialidad = cedulaEspecialidad;
		this.institucionEducativaId = institucionEducativaId;
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
	@JoinColumn(name = "EspecialidadesId", nullable = false)
	public Catespecialidadesmedicas getCatespecialidadesmedicas() {
		return this.catespecialidadesmedicas;
	}

	public void setCatespecialidadesmedicas(
			Catespecialidadesmedicas catespecialidadesmedicas) {
		this.catespecialidadesmedicas = catespecialidadesmedicas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioId", nullable = false)
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@Column(name = "CedulaEspecialidad", nullable = false, length = 60)
	public String getCedulaEspecialidad() {
		return this.cedulaEspecialidad;
	}

	public void setCedulaEspecialidad(String cedulaEspecialidad) {
		this.cedulaEspecialidad = cedulaEspecialidad;
	}

	@Column(name = "InstitucionEducativaId", length = 100)
	public String getInstitucionEducativaId() {
		return this.institucionEducativaId;
	}

	public void setInstitucionEducativaId(String institucionEducativaId) {
		this.institucionEducativaId = institucionEducativaId;
	}

}