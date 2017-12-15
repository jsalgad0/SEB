package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Catimc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catimc", catalog = "my_db_rrg")
public class Catimc implements java.io.Serializable {

	// Fields

	private Integer imcid;
	private Double valorMin;
	private Double valorMax;
	private String clasificacion;
	private Integer estatusOk;

	// Constructors

	/** default constructor */
	public Catimc() {
	}

	/** full constructor */
	public Catimc(Double valorMin, Double valorMax, String clasificacion,
			Integer estatusOk) {
		this.valorMin = valorMin;
		this.valorMax = valorMax;
		this.clasificacion = clasificacion;
		this.estatusOk = estatusOk;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IMCId", unique = true, nullable = false)
	public Integer getImcid() {
		return this.imcid;
	}

	public void setImcid(Integer imcid) {
		this.imcid = imcid;
	}

	@Column(name = "ValorMin", nullable = false, precision = 5)
	public Double getValorMin() {
		return this.valorMin;
	}

	public void setValorMin(Double valorMin) {
		this.valorMin = valorMin;
	}

	@Column(name = "ValorMax", nullable = false, precision = 5)
	public Double getValorMax() {
		return this.valorMax;
	}

	public void setValorMax(Double valorMax) {
		this.valorMax = valorMax;
	}

	@Column(name = "Clasificacion", nullable = false)
	public String getClasificacion() {
		return this.clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	@Column(name = "EstatusOk", nullable = false)
	public Integer getEstatusOk() {
		return this.estatusOk;
	}

	public void setEstatusOk(Integer estatusOk) {
		this.estatusOk = estatusOk;
	}

}