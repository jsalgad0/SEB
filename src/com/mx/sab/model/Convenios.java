package com.mx.sab.model;
// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Convenios entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "convenios", catalog = "my_db_rrg")
public class Convenios implements java.io.Serializable {

	// Fields

	private Integer convenioId;
	private Prestadores prestadores;
	private Lugaresdeatencion lugaresdeatencion;
	private Aseguradores aseguradores;
	private String identificador;
	private String convenio;
	private Integer activo;
	private Date vigenciaInicial;
	private Date vigenciaFinal;
	private Set<ConvenioCuadroprestaciones> convenioCuadroprestacioneses = new HashSet<ConvenioCuadroprestaciones>(
			0);
	private Set<Atencionmedica> atencionmedicas = new HashSet<Atencionmedica>(0);

	// Constructors

	/** default constructor */
	public Convenios() {
	}

	/** minimal constructor */
	public Convenios(String identificador) {
		this.identificador = identificador;
	}

	/** full constructor */
	public Convenios(Prestadores prestadores,
			Lugaresdeatencion lugaresdeatencion, Aseguradores aseguradores,
			String identificador, String convenio, Integer activo,
			Date vigenciaInicial, Date vigenciaFinal,
			Set<ConvenioCuadroprestaciones> convenioCuadroprestacioneses,
			Set<Atencionmedica> atencionmedicas) {
		this.prestadores = prestadores;
		this.lugaresdeatencion = lugaresdeatencion;
		this.aseguradores = aseguradores;
		this.identificador = identificador;
		this.convenio = convenio;
		this.activo = activo;
		this.vigenciaInicial = vigenciaInicial;
		this.vigenciaFinal = vigenciaFinal;
		this.convenioCuadroprestacioneses = convenioCuadroprestacioneses;
		this.atencionmedicas = atencionmedicas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ConvenioId", unique = true, nullable = false)
	public Integer getConvenioId() {
		return this.convenioId;
	}

	public void setConvenioId(Integer convenioId) {
		this.convenioId = convenioId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PrestadorId")
	public Prestadores getPrestadores() {
		return this.prestadores;
	}

	public void setPrestadores(Prestadores prestadores) {
		this.prestadores = prestadores;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LugarDeAtencionId")
	public Lugaresdeatencion getLugaresdeatencion() {
		return this.lugaresdeatencion;
	}

	public void setLugaresdeatencion(Lugaresdeatencion lugaresdeatencion) {
		this.lugaresdeatencion = lugaresdeatencion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AseguradorId")
	public Aseguradores getAseguradores() {
		return this.aseguradores;
	}

	public void setAseguradores(Aseguradores aseguradores) {
		this.aseguradores = aseguradores;
	}

	@Column(name = "Identificador", nullable = false, length = 50)
	public String getIdentificador() {
		return this.identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	@Column(name = "Convenio", length = 50)
	public String getConvenio() {
		return this.convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	@Column(name = "Activo")
	public Integer getActivo() {
		return this.activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VigenciaInicial", length = 10)
	public Date getVigenciaInicial() {
		return this.vigenciaInicial;
	}

	public void setVigenciaInicial(Date vigenciaInicial) {
		this.vigenciaInicial = vigenciaInicial;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VigenciaFinal", length = 10)
	public Date getVigenciaFinal() {
		return this.vigenciaFinal;
	}

	public void setVigenciaFinal(Date vigenciaFinal) {
		this.vigenciaFinal = vigenciaFinal;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "convenios")
	public Set<ConvenioCuadroprestaciones> getConvenioCuadroprestacioneses() {
		return this.convenioCuadroprestacioneses;
	}

	public void setConvenioCuadroprestacioneses(
			Set<ConvenioCuadroprestaciones> convenioCuadroprestacioneses) {
		this.convenioCuadroprestacioneses = convenioCuadroprestacioneses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "convenios")
	public Set<Atencionmedica> getAtencionmedicas() {
		return this.atencionmedicas;
	}

	public void setAtencionmedicas(Set<Atencionmedica> atencionmedicas) {
		this.atencionmedicas = atencionmedicas;
	}

}