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
 * HcExploracionfisica entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hc_exploracionfisica", catalog = "my_db_rrg")
public class HcExploracionfisica implements java.io.Serializable {

	// Fields

	private Integer exploracionFisicaId;
	private Historiaclinica historiaclinica;
	private Boolean cabeza;
	private Boolean cuello;
	private Boolean torax;
	private Boolean abdomen;
	private Boolean extremidades;
	private Boolean sistemaNervioso;
	private Boolean sistemaCardio;
	private Boolean aparatoDigestivo;
	private Boolean sistemaMusculo;
	private String otrasEf;

	// Constructors

	/** default constructor */
	public HcExploracionfisica() {
	}

	/** minimal constructor */
	public HcExploracionfisica(Historiaclinica historiaclinica) {
		this.historiaclinica = historiaclinica;
	}

	/** full constructor */
	public HcExploracionfisica(Historiaclinica historiaclinica, Boolean cabeza,
			Boolean cuello, Boolean torax, Boolean abdomen,
			Boolean extremidades, Boolean sistemaNervioso,
			Boolean sistemaCardio, Boolean aparatoDigestivo,
			Boolean sistemaMusculo, String otrasEf) {
		this.historiaclinica = historiaclinica;
		this.cabeza = cabeza;
		this.cuello = cuello;
		this.torax = torax;
		this.abdomen = abdomen;
		this.extremidades = extremidades;
		this.sistemaNervioso = sistemaNervioso;
		this.sistemaCardio = sistemaCardio;
		this.aparatoDigestivo = aparatoDigestivo;
		this.sistemaMusculo = sistemaMusculo;
		this.otrasEf = otrasEf;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ExploracionFisicaId", unique = true, nullable = false)
	public Integer getExploracionFisicaId() {
		return this.exploracionFisicaId;
	}

	public void setExploracionFisicaId(Integer exploracionFisicaId) {
		this.exploracionFisicaId = exploracionFisicaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HistoriaClinicaId", nullable = false)
	public Historiaclinica getHistoriaclinica() {
		return this.historiaclinica;
	}

	public void setHistoriaclinica(Historiaclinica historiaclinica) {
		this.historiaclinica = historiaclinica;
	}

	@Column(name = "Cabeza")
	public Boolean getCabeza() {
		return this.cabeza;
	}

	public void setCabeza(Boolean cabeza) {
		this.cabeza = cabeza;
	}

	@Column(name = "Cuello")
	public Boolean getCuello() {
		return this.cuello;
	}

	public void setCuello(Boolean cuello) {
		this.cuello = cuello;
	}

	@Column(name = "Torax")
	public Boolean getTorax() {
		return this.torax;
	}

	public void setTorax(Boolean torax) {
		this.torax = torax;
	}

	@Column(name = "Abdomen")
	public Boolean getAbdomen() {
		return this.abdomen;
	}

	public void setAbdomen(Boolean abdomen) {
		this.abdomen = abdomen;
	}

	@Column(name = "Extremidades")
	public Boolean getExtremidades() {
		return this.extremidades;
	}

	public void setExtremidades(Boolean extremidades) {
		this.extremidades = extremidades;
	}

	@Column(name = "SistemaNervioso")
	public Boolean getSistemaNervioso() {
		return this.sistemaNervioso;
	}

	public void setSistemaNervioso(Boolean sistemaNervioso) {
		this.sistemaNervioso = sistemaNervioso;
	}

	@Column(name = "SistemaCardio")
	public Boolean getSistemaCardio() {
		return this.sistemaCardio;
	}

	public void setSistemaCardio(Boolean sistemaCardio) {
		this.sistemaCardio = sistemaCardio;
	}

	@Column(name = "AparatoDigestivo")
	public Boolean getAparatoDigestivo() {
		return this.aparatoDigestivo;
	}

	public void setAparatoDigestivo(Boolean aparatoDigestivo) {
		this.aparatoDigestivo = aparatoDigestivo;
	}

	@Column(name = "SistemaMusculo")
	public Boolean getSistemaMusculo() {
		return this.sistemaMusculo;
	}

	public void setSistemaMusculo(Boolean sistemaMusculo) {
		this.sistemaMusculo = sistemaMusculo;
	}

	@Column(name = "OtrasEF", length = 100)
	public String getOtrasEf() {
		return this.otrasEf;
	}

	public void setOtrasEf(String otrasEf) {
		this.otrasEf = otrasEf;
	}

}