package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Codigolugaratencion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "codigolugaratencion", catalog = "my_db_rrg")
public class Codigolugaratencion implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer semilla;

	// Constructors

	/** default constructor */
	public Codigolugaratencion() {
	}

	/** full constructor */
	public Codigolugaratencion(Integer semilla) {
		this.semilla = semilla;
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

	@Column(name = "Semilla", nullable = false)
	public Integer getSemilla() {
		return this.semilla;
	}

	public void setSemilla(Integer semilla) {
		this.semilla = semilla;
	}

}