package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CargaAgendaVo implements Serializable{

	private static final long serialVersionUID = 3859389331960372454L;
	private String prestador;
	private String claveInst;
	private String cveMedico;
	private String consultorio;
	private String consultorioOriginal;
	private String estatusCita;
	private String claveCita;
	private String identificadorMedico;
	private String asegurador;
	private String tipoIden;
	private String afiliado;
	private String convenio;
	private String fecha;
	private String hora;
	private String beneficiario;
	private String codigo;
	private String subCodigo;
	private String descripcionCodigo;
	public String getPrestador() {
		return prestador;
	}
	public void setPrestador(String prestador) {
		this.prestador = prestador;
	}
	public String getClaveInst() {
		return claveInst;
	}
	public void setClaveInst(String claveInst) {
		this.claveInst = claveInst;
	}
	public String getCveMedico() {
		return cveMedico;
	}
	public void setCveMedico(String cveMedico) {
		this.cveMedico = cveMedico;
	}
	public String getConsultorio() {
		return consultorio;
	}
	public void setConsultorio(String consultorio) {
		this.consultorio = consultorio;
	}
	public String getConsultorioOriginal() {
		return consultorioOriginal;
	}
	public void setConsultorioOriginal(String consultorioOriginal) {
		this.consultorioOriginal = consultorioOriginal;
	}
	public String getEstatusCita() {
		return estatusCita;
	}
	public void setEstatusCita(String estatusCita) {
		this.estatusCita = estatusCita;
	}
	public String getClaveCita() {
		return claveCita;
	}
	public void setClaveCita(String claveCita) {
		this.claveCita = claveCita;
	}
	public String getIdentificadorMedico() {
		return identificadorMedico;
	}
	public void setIdentificadorMedico(String identificadorMedico) {
		this.identificadorMedico = identificadorMedico;
	}
	public String getAsegurador() {
		return asegurador;
	}
	public void setAsegurador(String asegurador) {
		this.asegurador = asegurador;
	}
	public String getTipoIden() {
		return tipoIden;
	}
	public void setTipoIden(String tipoIden) {
		this.tipoIden = tipoIden;
	}
	public String getAfiliado() {
		return afiliado;
	}
	public void setAfiliado(String afiliado) {
		this.afiliado = afiliado;
	}
	public String getConvenio() {
		return convenio;
	}
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getSubCodigo() {
		return subCodigo;
	}
	public void setSubCodigo(String subCodigo) {
		this.subCodigo = subCodigo;
	}
	public String getDescripcionCodigo() {
		return descripcionCodigo;
	}
	public void setDescripcionCodigo(String descripcionCodigo) {
		this.descripcionCodigo = descripcionCodigo;
	}
	
	
	
}
