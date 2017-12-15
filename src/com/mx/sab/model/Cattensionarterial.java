package com.mx.sab.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cattensionarterial entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cattensionarterial", catalog = "my_db_rrg")
public class Cattensionarterial implements java.io.Serializable {

	// Fields

	private Integer tensionArterialId;
	private Double sistolicaMin;
	private Double sistolicaMax;
	private String operador;
	private Double distolicaMin;
	private Double distolicaMax;
	private String clasificacion;
	private Integer estatusOk;

	// Constructors

	/** default constructor */
	public Cattensionarterial() {
	}

	/** full constructor */
	public Cattensionarterial(Double sistolicaMin, Double sistolicaMax,
			String operador, Double distolicaMin, Double distolicaMax,
			String clasificacion, Integer estatusOk) {
		this.sistolicaMin = sistolicaMin;
		this.sistolicaMax = sistolicaMax;
		this.operador = operador;
		this.distolicaMin = distolicaMin;
		this.distolicaMax = distolicaMax;
		this.clasificacion = clasificacion;
		this.estatusOk = estatusOk;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TensionArterialId", unique = true, nullable = false)
	public Integer getTensionArterialId() {
		return this.tensionArterialId;
	}

	public void setTensionArterialId(Integer tensionArterialId) {
		this.tensionArterialId = tensionArterialId;
	}

	@Column(name = "SistolicaMin", nullable = false, precision = 8)
	public Double getSistolicaMin() {
		return this.sistolicaMin;
	}

	public void setSistolicaMin(Double sistolicaMin) {
		this.sistolicaMin = sistolicaMin;
	}

	@Column(name = "SistolicaMax", nullable = false, precision = 8)
	public Double getSistolicaMax() {
		return this.sistolicaMax;
	}

	public void setSistolicaMax(Double sistolicaMax) {
		this.sistolicaMax = sistolicaMax;
	}

	@Column(name = "Operador", nullable = false, length = 10)
	public String getOperador() {
		return this.operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	@Column(name = "DistolicaMin", nullable = false, precision = 8)
	public Double getDistolicaMin() {
		return this.distolicaMin;
	}

	public void setDistolicaMin(Double distolicaMin) {
		this.distolicaMin = distolicaMin;
	}

	@Column(name = "DistolicaMax", nullable = false, precision = 8)
	public Double getDistolicaMax() {
		return this.distolicaMax;
	}

	public void setDistolicaMax(Double distolicaMax) {
		this.distolicaMax = distolicaMax;
	}

	@Column(name = "Clasificacion", nullable = false, length = 100)
	public String getClasificacion() {
		return this.clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	@Column(name = "EstatusOK", nullable = false)
	public Integer getEstatusOk() {
		return this.estatusOk;
	}

	public void setEstatusOk(Integer estatusOk) {
		this.estatusOk = estatusOk;
	}

}