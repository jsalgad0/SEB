package com.mx.sab.model;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Signosvitales entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "signosvitales", catalog = "my_db_rrg")
public class Signosvitales implements java.io.Serializable {

	// Fields

	private Integer signosVitalesId;
	private Usuarios usuarios;
	private Atencionmedica atencionmedica;
	private Afiliado afiliado;
	private Double peso;
	private Double altura;
	private Double cintura;
	private Double imc;
	private String tensionArterial;
	private Double glucosa;
	private Double temperatura;
	private Double pulso;
	private Integer primeraVez;
	private Double resp;
	private String numFolio;
	private Date fechaSignos;
	private String saturacionOxigeno;
	private Integer signosVitalesAdicionealesId;
	private String observaciones;

	// Constructors

	/** default constructor */
	public Signosvitales() {
	}

	/** minimal constructor */
	public Signosvitales(Usuarios usuarios, Atencionmedica atencionmedica) {
		this.usuarios = usuarios;
		this.atencionmedica = atencionmedica;
	}

	/** full constructor */
	public Signosvitales(Usuarios usuarios, Atencionmedica atencionmedica,
			Afiliado afiliado, Double peso, Double altura, Double cintura,
			Double imc, String tensionArterial, Double glucosa,
			Double temperatura, Double pulso, Integer primeraVez, Double resp,
			String numFolio, Date fechaSignos, String saturacionOxigeno,
			Integer signosVitalesAdicionealesId, String observaciones) {
		this.usuarios = usuarios;
		this.atencionmedica = atencionmedica;
		this.afiliado = afiliado;
		this.peso = peso;
		this.altura = altura;
		this.cintura = cintura;
		this.imc = imc;
		this.tensionArterial = tensionArterial;
		this.glucosa = glucosa;
		this.temperatura = temperatura;
		this.pulso = pulso;
		this.primeraVez = primeraVez;
		this.resp = resp;
		this.numFolio = numFolio;
		this.fechaSignos = fechaSignos;
		this.saturacionOxigeno = saturacionOxigeno;
		this.signosVitalesAdicionealesId = signosVitalesAdicionealesId;
		this.observaciones = observaciones;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SignosVitalesId", unique = true, nullable = false)
	public Integer getSignosVitalesId() {
		return this.signosVitalesId;
	}

	public void setSignosVitalesId(Integer signosVitalesId) {
		this.signosVitalesId = signosVitalesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioProfesionalId", nullable = false)
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AtencionMedicaId", nullable = false)
	public Atencionmedica getAtencionmedica() {
		return this.atencionmedica;
	}

	public void setAtencionmedica(Atencionmedica atencionmedica) {
		this.atencionmedica = atencionmedica;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AfiliadoId")
	public Afiliado getAfiliado() {
		return this.afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	@Column(name = "Peso", precision = 8)
	public Double getPeso() {
		return this.peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Column(name = "Altura", precision = 8)
	public Double getAltura() {
		return this.altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	@Column(name = "Cintura", precision = 8)
	public Double getCintura() {
		return this.cintura;
	}

	public void setCintura(Double cintura) {
		this.cintura = cintura;
	}

	@Column(name = "IMC", precision = 8)
	public Double getImc() {
		return this.imc;
	}

	public void setImc(Double imc) {
		this.imc = imc;
	}

	@Column(name = "TensionArterial", length = 12)
	public String getTensionArterial() {
		return this.tensionArterial;
	}

	public void setTensionArterial(String tensionArterial) {
		this.tensionArterial = tensionArterial;
	}

	@Column(name = "Glucosa", precision = 8)
	public Double getGlucosa() {
		return this.glucosa;
	}

	public void setGlucosa(Double glucosa) {
		this.glucosa = glucosa;
	}

	@Column(name = "Temperatura", precision = 8)
	public Double getTemperatura() {
		return this.temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	@Column(name = "Pulso", precision = 8)
	public Double getPulso() {
		return this.pulso;
	}

	public void setPulso(Double pulso) {
		this.pulso = pulso;
	}

	@Column(name = "PrimeraVez")
	public Integer getPrimeraVez() {
		return this.primeraVez;
	}

	public void setPrimeraVez(Integer primeraVez) {
		this.primeraVez = primeraVez;
	}

	@Column(name = "Resp", precision = 22, scale = 0)
	public Double getResp() {
		return this.resp;
	}

	public void setResp(Double resp) {
		this.resp = resp;
	}

	@Column(name = "NumFolio", length = 9)
	public String getNumFolio() {
		return this.numFolio;
	}

	public void setNumFolio(String numFolio) {
		this.numFolio = numFolio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechaSignos", length = 10)
	public Date getFechaSignos() {
		return this.fechaSignos;
	}

	public void setFechaSignos(Date fechaSignos) {
		this.fechaSignos = fechaSignos;
	}

	@Column(name = "SaturacionOxigeno", length = 10)
	public String getSaturacionOxigeno() {
		return this.saturacionOxigeno;
	}

	public void setSaturacionOxigeno(String saturacionOxigeno) {
		this.saturacionOxigeno = saturacionOxigeno;
	}

	@Column(name = "SignosVitalesAdicionealesId")
	public Integer getSignosVitalesAdicionealesId() {
		return this.signosVitalesAdicionealesId;
	}

	public void setSignosVitalesAdicionealesId(
			Integer signosVitalesAdicionealesId) {
		this.signosVitalesAdicionealesId = signosVitalesAdicionealesId;
	}

	@Column(name = "Observaciones", length = 250)
	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}