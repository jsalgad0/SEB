package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;

import com.mx.sab.model.Licenciamedica;

import lombok.Data;

@Data
public class LicenciaMedicaForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = -5363521336481175041L;
	private int idAgenda;
	private String vigenteDesde;
	private String vigenteHasta;
	private int idLicMedicaMotivo;
	private int idLicMedicaCaracter;
	private int idLicMedicaTipoServicio;
	private int dias;
	private String diasLetra;
	private Collection<Licenciamedica> licenciamedicas;
	private boolean finalizoAtencionMedica;
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public String getVigenteDesde() {
		return vigenteDesde;
	}
	public void setVigenteDesde(String vigenteDesde) {
		this.vigenteDesde = vigenteDesde;
	}
	public String getVigenteHasta() {
		return vigenteHasta;
	}
	public void setVigenteHasta(String vigenteHasta) {
		this.vigenteHasta = vigenteHasta;
	}
	public int getIdLicMedicaMotivo() {
		return idLicMedicaMotivo;
	}
	public void setIdLicMedicaMotivo(int idLicMedicaMotivo) {
		this.idLicMedicaMotivo = idLicMedicaMotivo;
	}
	public int getIdLicMedicaCaracter() {
		return idLicMedicaCaracter;
	}
	public void setIdLicMedicaCaracter(int idLicMedicaCaracter) {
		this.idLicMedicaCaracter = idLicMedicaCaracter;
	}
	public int getIdLicMedicaTipoServicio() {
		return idLicMedicaTipoServicio;
	}
	public void setIdLicMedicaTipoServicio(int idLicMedicaTipoServicio) {
		this.idLicMedicaTipoServicio = idLicMedicaTipoServicio;
	}
	public int getDias() {
		return dias;
	}
	public void setDias(int dias) {
		this.dias = dias;
	}
	public String getDiasLetra() {
		return diasLetra;
	}
	public void setDiasLetra(String diasLetra) {
		this.diasLetra = diasLetra;
	}
	public Collection<Licenciamedica> getLicenciamedicas() {
		return licenciamedicas;
	}
	public void setLicenciamedicas(Collection<Licenciamedica> licenciamedicas) {
		this.licenciamedicas = licenciamedicas;
	}
	public boolean isFinalizoAtencionMedica() {
		return finalizoAtencionMedica;
	}
	public void setFinalizoAtencionMedica(boolean finalizoAtencionMedica) {
		this.finalizoAtencionMedica = finalizoAtencionMedica;
	}
	
	
	

}
