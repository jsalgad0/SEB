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
 * HcGineco entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hc_gineco", catalog = "my_db_rrg")
public class HcGineco implements java.io.Serializable {

	// Fields

	private Integer ginecoId;
	private Historiaclinica historiaclinica;
	private Catmpf catmpf;
	private String menarca;
	private String ritmo;
	private String vsa;
	private String fur;
	private String g;
	private String p;
	private String a;
	private String c;

	// Constructors

	/** default constructor */
	public HcGineco() {
	}

	/** minimal constructor */
	public HcGineco(Historiaclinica historiaclinica) {
		this.historiaclinica = historiaclinica;
	}

	/** full constructor */
	public HcGineco(Historiaclinica historiaclinica, Catmpf catmpf,
			String menarca, String ritmo, String vsa, String fur, String g,
			String p, String a, String c) {
		this.historiaclinica = historiaclinica;
		this.catmpf = catmpf;
		this.menarca = menarca;
		this.ritmo = ritmo;
		this.vsa = vsa;
		this.fur = fur;
		this.g = g;
		this.p = p;
		this.a = a;
		this.c = c;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "GinecoId", unique = true, nullable = false)
	public Integer getGinecoId() {
		return this.ginecoId;
	}

	public void setGinecoId(Integer ginecoId) {
		this.ginecoId = ginecoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HistoriaClinicaId", nullable = false)
	public Historiaclinica getHistoriaclinica() {
		return this.historiaclinica;
	}

	public void setHistoriaclinica(Historiaclinica historiaclinica) {
		this.historiaclinica = historiaclinica;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MfpId")
	public Catmpf getCatmpf() {
		return this.catmpf;
	}

	public void setCatmpf(Catmpf catmpf) {
		this.catmpf = catmpf;
	}

	@Column(name = "menarca", length = 100)
	public String getMenarca() {
		return this.menarca;
	}

	public void setMenarca(String menarca) {
		this.menarca = menarca;
	}

	@Column(name = "Ritmo", length = 100)
	public String getRitmo() {
		return this.ritmo;
	}

	public void setRitmo(String ritmo) {
		this.ritmo = ritmo;
	}

	@Column(name = "VSA", length = 100)
	public String getVsa() {
		return this.vsa;
	}

	public void setVsa(String vsa) {
		this.vsa = vsa;
	}

	@Column(name = "FUR", length = 100)
	public String getFur() {
		return this.fur;
	}

	public void setFur(String fur) {
		this.fur = fur;
	}

	@Column(name = "G", length = 100)
	public String getG() {
		return this.g;
	}

	public void setG(String g) {
		this.g = g;
	}

	@Column(name = "P", length = 100)
	public String getP() {
		return this.p;
	}

	public void setP(String p) {
		this.p = p;
	}

	@Column(name = "A", length = 100)
	public String getA() {
		return this.a;
	}

	public void setA(String a) {
		this.a = a;
	}

	@Column(name = "C", length = 100)
	public String getC() {
		return this.c;
	}

	public void setC(String c) {
		this.c = c;
	}

}