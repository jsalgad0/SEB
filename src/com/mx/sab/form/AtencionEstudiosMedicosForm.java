package com.mx.sab.form;

import java.io.Serializable;
import java.util.List;

import com.mx.sab.vo.AutocompleteVo;

import lombok.Data;

@Data
public class AtencionEstudiosMedicosForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = 5432084556733168391L;
	private int idAtencion;
	private int idEstudios;
	private int idPrestacion;
	private String codigo;
	private String prestacion;
	private String orden;
	private List<AutocompleteVo> prestacionesAutocompleteVos;
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public int getIdEstudios() {
		return idEstudios;
	}
	public void setIdEstudios(int idEstudios) {
		this.idEstudios = idEstudios;
	}
	public int getIdPrestacion() {
		return idPrestacion;
	}
	public void setIdPrestacion(int idPrestacion) {
		this.idPrestacion = idPrestacion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getPrestacion() {
		return prestacion;
	}
	public void setPrestacion(String prestacion) {
		this.prestacion = prestacion;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public List<AutocompleteVo> getPrestacionesAutocompleteVos() {
		return prestacionesAutocompleteVos;
	}
	public void setPrestacionesAutocompleteVos(List<AutocompleteVo> prestacionesAutocompleteVos) {
		this.prestacionesAutocompleteVos = prestacionesAutocompleteVos;
	}
	
	
	
}
