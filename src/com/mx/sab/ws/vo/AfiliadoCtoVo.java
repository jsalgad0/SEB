package com.mx.sab.ws.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class AfiliadoCtoVo implements Serializable{

	private static final long serialVersionUID = -7660254662428547443L;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String fecha;
	private String rfc_curp;
	private String numIssste;
	private int codEmpresa;
	private String codAfiliado;
	private int correlativo;
	private String codProducto;
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getRfc_curp() {
		return rfc_curp;
	}
	public void setRfc_curp(String rfc_curp) {
		this.rfc_curp = rfc_curp;
	}
	public String getNumIssste() {
		return numIssste;
	}
	public void setNumIssste(String numIssste) {
		this.numIssste = numIssste;
	}
	public int getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(int codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	public String getCodAfiliado() {
		return codAfiliado;
	}
	public void setCodAfiliado(String codAfiliado) {
		this.codAfiliado = codAfiliado;
	}
	public int getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(int correlativo) {
		this.correlativo = correlativo;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	
}
