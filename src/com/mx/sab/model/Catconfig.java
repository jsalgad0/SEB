package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Catconfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catconfig", catalog = "my_db_rrg")
public class Catconfig implements java.io.Serializable {

	// Fields

	private Integer id;
	private String llave;
	private String valor;

	// Constructors

	/** default constructor */
	public Catconfig() {
	}

	/** full constructor */
	public Catconfig(String llave, String valor) {
		this.llave = llave;
		this.valor = valor;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Llave", length = 100)
	public String getLlave() {
		return this.llave;
	}

	public void setLlave(String llave) {
		this.llave = llave;
	}

	@Column(name = "Valor", length = 150)
	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}