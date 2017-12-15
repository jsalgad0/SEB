package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

import com.mx.sab.model.HcAntecedentesfamiliares;
import com.mx.sab.model.HcAntecedentespersonal;
import com.mx.sab.model.HcExploracionfisica;
import com.mx.sab.model.HcFasedelciclo;
import com.mx.sab.model.HcGineco;
import com.mx.sab.model.Signosvitales;

@Data
public class HistoriaClincaForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = -816066458751698712L;
	private int idAtencion;
	private int idAgenda;
	private HcAntecedentesfamiliares hcAntecedentesfamiliares;
	private int idEstadoCivil;
	private int idEscolaridad;
	private int idAlimentacion;
	private int idHigiene;
	private HcAntecedentespersonal hcAntecedentespersonal;
	private int idMpf;
	private HcGineco hcGineco;
	private Signosvitales signosvitales;
	private HcExploracionfisica hcExploracionfisica;
	private HcFasedelciclo hcFasedelciclo;
	private int idTipoFamilia;
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public HcAntecedentesfamiliares getHcAntecedentesfamiliares() {
		return hcAntecedentesfamiliares;
	}
	public void setHcAntecedentesfamiliares(HcAntecedentesfamiliares hcAntecedentesfamiliares) {
		this.hcAntecedentesfamiliares = hcAntecedentesfamiliares;
	}
	public int getIdEstadoCivil() {
		return idEstadoCivil;
	}
	public void setIdEstadoCivil(int idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}
	public int getIdEscolaridad() {
		return idEscolaridad;
	}
	public void setIdEscolaridad(int idEscolaridad) {
		this.idEscolaridad = idEscolaridad;
	}
	public int getIdAlimentacion() {
		return idAlimentacion;
	}
	public void setIdAlimentacion(int idAlimentacion) {
		this.idAlimentacion = idAlimentacion;
	}
	public int getIdHigiene() {
		return idHigiene;
	}
	public void setIdHigiene(int idHigiene) {
		this.idHigiene = idHigiene;
	}
	public HcAntecedentespersonal getHcAntecedentespersonal() {
		return hcAntecedentespersonal;
	}
	public void setHcAntecedentespersonal(HcAntecedentespersonal hcAntecedentespersonal) {
		this.hcAntecedentespersonal = hcAntecedentespersonal;
	}
	public int getIdMpf() {
		return idMpf;
	}
	public void setIdMpf(int idMpf) {
		this.idMpf = idMpf;
	}
	public HcGineco getHcGineco() {
		return hcGineco;
	}
	public void setHcGineco(HcGineco hcGineco) {
		this.hcGineco = hcGineco;
	}
	public Signosvitales getSignosvitales() {
		return signosvitales;
	}
	public void setSignosvitales(Signosvitales signosvitales) {
		this.signosvitales = signosvitales;
	}
	public HcExploracionfisica getHcExploracionfisica() {
		return hcExploracionfisica;
	}
	public void setHcExploracionfisica(HcExploracionfisica hcExploracionfisica) {
		this.hcExploracionfisica = hcExploracionfisica;
	}
	public HcFasedelciclo getHcFasedelciclo() {
		return hcFasedelciclo;
	}
	public void setHcFasedelciclo(HcFasedelciclo hcFasedelciclo) {
		this.hcFasedelciclo = hcFasedelciclo;
	}
	public int getIdTipoFamilia() {
		return idTipoFamilia;
	}
	public void setIdTipoFamilia(int idTipoFamilia) {
		this.idTipoFamilia = idTipoFamilia;
	} 
	
	
	
	
}
