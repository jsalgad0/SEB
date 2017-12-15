package com.mx.sab.model;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Videntisa entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "videntisa", catalog = "my_db_rrg")
public class Videntisa implements java.io.Serializable {

	// Fields

	private Integer numIssste;
	private String rfcTra;
	private String curpTra;
	private String tipoDh;
	private String apaTra;
	private String amaTra;
	private String nomTra;
	private String dtoEstadoTra;
	private String sexoTra;
	private Date fecNacTra;
	private String calleTra;
	private Integer numExtTra;
	private Integer numIntTra;
	private Integer codPostalTra;
	private Integer itoId;
	private String curp;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombre;
	private Integer parentescoCve;
	private String itoEstado;
	private String parentesco;

	// Constructors

	/** default constructor */
	public Videntisa() {
	}

	/** minimal constructor */
	public Videntisa(Integer numIssste) {
		this.numIssste = numIssste;
	}

	/** full constructor */
	public Videntisa(Integer numIssste, String rfcTra, String curpTra,
			String tipoDh, String apaTra, String amaTra, String nomTra,
			String dtoEstadoTra, String sexoTra, Date fecNacTra,
			String calleTra, Integer numExtTra, Integer numIntTra,
			Integer codPostalTra, Integer itoId, String curp,
			String apellidoPaterno, String apellidoMaterno, String nombre,
			Integer parentescoCve, String itoEstado, String parentesco) {
		this.numIssste = numIssste;
		this.rfcTra = rfcTra;
		this.curpTra = curpTra;
		this.tipoDh = tipoDh;
		this.apaTra = apaTra;
		this.amaTra = amaTra;
		this.nomTra = nomTra;
		this.dtoEstadoTra = dtoEstadoTra;
		this.sexoTra = sexoTra;
		this.fecNacTra = fecNacTra;
		this.calleTra = calleTra;
		this.numExtTra = numExtTra;
		this.numIntTra = numIntTra;
		this.codPostalTra = codPostalTra;
		this.itoId = itoId;
		this.curp = curp;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.nombre = nombre;
		this.parentescoCve = parentescoCve;
		this.itoEstado = itoEstado;
		this.parentesco = parentesco;
	}

	// Property accessors
	@Id
	@Column(name = "num_issste", unique = true, nullable = false)
	public Integer getNumIssste() {
		return this.numIssste;
	}

	public void setNumIssste(Integer numIssste) {
		this.numIssste = numIssste;
	}

	@Column(name = "rfc_tra", length = 100)
	public String getRfcTra() {
		return this.rfcTra;
	}

	public void setRfcTra(String rfcTra) {
		this.rfcTra = rfcTra;
	}

	@Column(name = "curp_tra", length = 100)
	public String getCurpTra() {
		return this.curpTra;
	}

	public void setCurpTra(String curpTra) {
		this.curpTra = curpTra;
	}

	@Column(name = "tipo_dh", length = 100)
	public String getTipoDh() {
		return this.tipoDh;
	}

	public void setTipoDh(String tipoDh) {
		this.tipoDh = tipoDh;
	}

	@Column(name = "apa_tra", length = 100)
	public String getApaTra() {
		return this.apaTra;
	}

	public void setApaTra(String apaTra) {
		this.apaTra = apaTra;
	}

	@Column(name = "ama_tra", length = 100)
	public String getAmaTra() {
		return this.amaTra;
	}

	public void setAmaTra(String amaTra) {
		this.amaTra = amaTra;
	}

	@Column(name = "nom_tra", length = 100)
	public String getNomTra() {
		return this.nomTra;
	}

	public void setNomTra(String nomTra) {
		this.nomTra = nomTra;
	}

	@Column(name = "dto_estado_tra", length = 100)
	public String getDtoEstadoTra() {
		return this.dtoEstadoTra;
	}

	public void setDtoEstadoTra(String dtoEstadoTra) {
		this.dtoEstadoTra = dtoEstadoTra;
	}

	@Column(name = "sexo_tra", length = 100)
	public String getSexoTra() {
		return this.sexoTra;
	}

	public void setSexoTra(String sexoTra) {
		this.sexoTra = sexoTra;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fec_nac_tra", length = 10)
	public Date getFecNacTra() {
		return this.fecNacTra;
	}

	public void setFecNacTra(Date fecNacTra) {
		this.fecNacTra = fecNacTra;
	}

	@Column(name = "calle_tra", length = 100)
	public String getCalleTra() {
		return this.calleTra;
	}

	public void setCalleTra(String calleTra) {
		this.calleTra = calleTra;
	}

	@Column(name = "num_ext_tra")
	public Integer getNumExtTra() {
		return this.numExtTra;
	}

	public void setNumExtTra(Integer numExtTra) {
		this.numExtTra = numExtTra;
	}

	@Column(name = "num_int_tra")
	public Integer getNumIntTra() {
		return this.numIntTra;
	}

	public void setNumIntTra(Integer numIntTra) {
		this.numIntTra = numIntTra;
	}

	@Column(name = "cod_postal_tra")
	public Integer getCodPostalTra() {
		return this.codPostalTra;
	}

	public void setCodPostalTra(Integer codPostalTra) {
		this.codPostalTra = codPostalTra;
	}

	@Column(name = "ito_id")
	public Integer getItoId() {
		return this.itoId;
	}

	public void setItoId(Integer itoId) {
		this.itoId = itoId;
	}

	@Column(name = "curp", length = 100)
	public String getCurp() {
		return this.curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	@Column(name = "apellido_paterno", length = 100)
	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	@Column(name = "apellido_materno", length = 100)
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	@Column(name = "nombre", length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "parentesco_cve")
	public Integer getParentescoCve() {
		return this.parentescoCve;
	}

	public void setParentescoCve(Integer parentescoCve) {
		this.parentescoCve = parentescoCve;
	}

	@Column(name = "ito_estado", length = 100)
	public String getItoEstado() {
		return this.itoEstado;
	}

	public void setItoEstado(String itoEstado) {
		this.itoEstado = itoEstado;
	}

	@Column(name = "parentesco", length = 100)
	public String getParentesco() {
		return this.parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

}