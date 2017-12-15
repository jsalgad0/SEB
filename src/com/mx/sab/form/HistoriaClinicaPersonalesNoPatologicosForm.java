package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class HistoriaClinicaPersonalesNoPatologicosForm extends ParentForm implements Serializable {
	
	private static final long serialVersionUID = -6825893315537429535L;
	
	private int idPersonalNoPatologicos;
	private int idDemografico;
	private int idAtencion;
	private int idAfiliado;
	private String fechaPersonalesPatologicos;
	private int idTipoSangre;
	private int idPositivoNegativo;
	private int idEscolaridad;
	private int idEstadoCivil;
	private int idAlimentacion;
	private int idHigienePersonal;
	public int getIdPersonalNoPatologicos() {
		return idPersonalNoPatologicos;
	}
	public void setIdPersonalNoPatologicos(int idPersonalNoPatologicos) {
		this.idPersonalNoPatologicos = idPersonalNoPatologicos;
	}
	public int getIdDemografico() {
		return idDemografico;
	}
	public void setIdDemografico(int idDemografico) {
		this.idDemografico = idDemografico;
	}
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public int getIdAfiliado() {
		return idAfiliado;
	}
	public void setIdAfiliado(int idAfiliado) {
		this.idAfiliado = idAfiliado;
	}
	public String getFechaPersonalesPatologicos() {
		return fechaPersonalesPatologicos;
	}
	public void setFechaPersonalesPatologicos(String fechaPersonalesPatologicos) {
		this.fechaPersonalesPatologicos = fechaPersonalesPatologicos;
	}
	public int getIdTipoSangre() {
		return idTipoSangre;
	}
	public void setIdTipoSangre(int idTipoSangre) {
		this.idTipoSangre = idTipoSangre;
	}
	public int getIdPositivoNegativo() {
		return idPositivoNegativo;
	}
	public void setIdPositivoNegativo(int idPositivoNegativo) {
		this.idPositivoNegativo = idPositivoNegativo;
	}
	public int getIdEscolaridad() {
		return idEscolaridad;
	}
	public void setIdEscolaridad(int idEscolaridad) {
		this.idEscolaridad = idEscolaridad;
	}
	public int getIdEstadoCivil() {
		return idEstadoCivil;
	}
	public void setIdEstadoCivil(int idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}
	public int getIdAlimentacion() {
		return idAlimentacion;
	}
	public void setIdAlimentacion(int idAlimentacion) {
		this.idAlimentacion = idAlimentacion;
	}
	public int getIdHigienePersonal() {
		return idHigienePersonal;
	}
	public void setIdHigienePersonal(int idHigienePersonal) {
		this.idHigienePersonal = idHigienePersonal;
	}

}
