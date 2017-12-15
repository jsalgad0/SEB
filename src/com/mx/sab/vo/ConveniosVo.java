package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class ConveniosVo implements Serializable{

	private static final long serialVersionUID = -5223511295101509557L;
	private Integer convenioId;
	private String convenio;
	private LugarAtencionVo lugaresdeatencion;
	private PrestadoresVo prestadores;
	private AseguradoresVo aseguradores;
	private String identificador;
	private String vigencia;
	private int idAsegurador;
	private int idLugarAtencion;
	private String lugarAtencion;
	private String codigoLugarAtencion;
	private int idPrestador;
	private String prestador;
	private String vigenciaDesde;
	private String vigenciaHasta;
	public Integer getConvenioId() {
		return convenioId;
	}
	public void setConvenioId(Integer convenioId) {
		this.convenioId = convenioId;
	}
	public String getConvenio() {
		return convenio;
	}
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	public LugarAtencionVo getLugaresdeatencion() {
		return lugaresdeatencion;
	}
	public void setLugaresdeatencion(LugarAtencionVo lugaresdeatencion) {
		this.lugaresdeatencion = lugaresdeatencion;
	}
	public PrestadoresVo getPrestadores() {
		return prestadores;
	}
	public void setPrestadores(PrestadoresVo prestadores) {
		this.prestadores = prestadores;
	}
	public AseguradoresVo getAseguradores() {
		return aseguradores;
	}
	public void setAseguradores(AseguradoresVo aseguradores) {
		this.aseguradores = aseguradores;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getVigencia() {
		return vigencia;
	}
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	public int getIdAsegurador() {
		return idAsegurador;
	}
	public void setIdAsegurador(int idAsegurador) {
		this.idAsegurador = idAsegurador;
	}
	public int getIdLugarAtencion() {
		return idLugarAtencion;
	}
	public void setIdLugarAtencion(int idLugarAtencion) {
		this.idLugarAtencion = idLugarAtencion;
	}
	public String getLugarAtencion() {
		return lugarAtencion;
	}
	public void setLugarAtencion(String lugarAtencion) {
		this.lugarAtencion = lugarAtencion;
	}
	public String getCodigoLugarAtencion() {
		return codigoLugarAtencion;
	}
	public void setCodigoLugarAtencion(String codigoLugarAtencion) {
		this.codigoLugarAtencion = codigoLugarAtencion;
	}
	public int getIdPrestador() {
		return idPrestador;
	}
	public void setIdPrestador(int idPrestador) {
		this.idPrestador = idPrestador;
	}
	public String getPrestador() {
		return prestador;
	}
	public void setPrestador(String prestador) {
		this.prestador = prestador;
	}
	public String getVigenciaDesde() {
		return vigenciaDesde;
	}
	public void setVigenciaDesde(String vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}
	public String getVigenciaHasta() {
		return vigenciaHasta;
	}
	public void setVigenciaHasta(String vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}
	
	
}
