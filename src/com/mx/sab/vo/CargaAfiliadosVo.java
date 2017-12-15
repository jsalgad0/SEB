package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CargaAfiliadosVo implements Serializable{

	private static final long serialVersionUID = 4664761943500396179L;
	private String linea;
	private String numeroIssste;
	private String numeroBeneficiario;
	private String rfc;
	private String curp;
	private String derechohabiente;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String sexo;
	private String fecha;
	
	private String fechaNacimiento;
	private int estado;
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public String getNumeroIssste() {
		return numeroIssste;
	}
	public void setNumeroIssste(String numeroIssste) {
		this.numeroIssste = numeroIssste;
	}
	public String getNumeroBeneficiario() {
		return numeroBeneficiario;
	}
	public void setNumeroBeneficiario(String numeroBeneficiario) {
		this.numeroBeneficiario = numeroBeneficiario;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getDerechohabiente() {
		return derechohabiente;
	}
	public void setDerechohabiente(String derechohabiente) {
		this.derechohabiente = derechohabiente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	
}

