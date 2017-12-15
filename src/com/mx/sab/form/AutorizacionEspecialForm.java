package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;

import com.mx.sab.model.Catcausas;

import lombok.Data;

@Data
public class AutorizacionEspecialForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = 2880837391391106779L;
//	private Collection<Permisoespecialafiliado> permisoespecialafiliados;
	private Collection<Catcausas> catcausas;
	private int idPermisoEspecial;
	private int idTipoVigencia;
	private int idCatCausa;
	private String fechaFin;
	public Collection<Catcausas> getCatcausas() {
		return catcausas;
	}
	public void setCatcausas(Collection<Catcausas> catcausas) {
		this.catcausas = catcausas;
	}
	public int getIdPermisoEspecial() {
		return idPermisoEspecial;
	}
	public void setIdPermisoEspecial(int idPermisoEspecial) {
		this.idPermisoEspecial = idPermisoEspecial;
	}
	public int getIdTipoVigencia() {
		return idTipoVigencia;
	}
	public void setIdTipoVigencia(int idTipoVigencia) {
		this.idTipoVigencia = idTipoVigencia;
	}
	public int getIdCatCausa() {
		return idCatCausa;
	}
	public void setIdCatCausa(int idCatCausa) {
		this.idCatCausa = idCatCausa;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
