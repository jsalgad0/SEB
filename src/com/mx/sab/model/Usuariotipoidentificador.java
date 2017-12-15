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
 * Usuariotipoidentificador entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuariotipoidentificador", catalog = "my_db_rrg")
public class Usuariotipoidentificador implements java.io.Serializable {

	// Fields

	private Integer id;
	private Cattipoidentificador cattipoidentificador;
	private Usuarios usuarios;
	private String valor;

	// Constructors

	/** default constructor */
	public Usuariotipoidentificador() {
	}

	/** full constructor */
	public Usuariotipoidentificador(Cattipoidentificador cattipoidentificador,
			Usuarios usuarios, String valor) {
		this.cattipoidentificador = cattipoidentificador;
		this.usuarios = usuarios;
		this.valor = valor;
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
	@JoinColumn(name = "TipoIdentificadorId", nullable = false)
	public Cattipoidentificador getCattipoidentificador() {
		return this.cattipoidentificador;
	}

	public void setCattipoidentificador(
			Cattipoidentificador cattipoidentificador) {
		this.cattipoidentificador = cattipoidentificador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioId", nullable = false)
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@Column(name = "Valor", nullable = false, length = 50)
	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}