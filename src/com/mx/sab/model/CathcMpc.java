package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CathcMpc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cathc_mpc", catalog = "my_db_rrg")
public class CathcMpc implements java.io.Serializable {

	// Fields

	private Integer mpcId;
	private String descripcion;

	// Constructors

	/** default constructor */
	public CathcMpc() {
	}

	/** full constructor */
	public CathcMpc(String descripcion) {
		this.descripcion = descripcion;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mpcId", unique = true, nullable = false)
	public Integer getMpcId() {
		return this.mpcId;
	}

	public void setMpcId(Integer mpcId) {
		this.mpcId = mpcId;
	}

	@Column(name = "Descripcion", nullable = false, length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}