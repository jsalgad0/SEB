package com.mx.sab.form;

import java.io.Serializable;
import java.util.List;

import com.mx.sab.vo.AutocompleteVo;

import lombok.Data;

@Data
public class RecepcionAtencionesPendientesForm extends ParentForm implements Serializable{
	
	private static final long serialVersionUID = 6140076486880860658L;
	private String motivo;
	private int idAfiliado;
	private int idAtencionMedica;
	private int tipoMotivo;
	private boolean finalizo;
	private String rfc;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	
	private AutocompleteVo autocompleteVo;
	private List<AutocompleteVo> autocompleteVos;

	private String[] autorizo;

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public int getIdAfiliado() {
		return idAfiliado;
	}

	public void setIdAfiliado(int idAfiliado) {
		this.idAfiliado = idAfiliado;
	}

	public int getIdAtencionMedica() {
		return idAtencionMedica;
	}

	public void setIdAtencionMedica(int idAtencionMedica) {
		this.idAtencionMedica = idAtencionMedica;
	}

	public int getTipoMotivo() {
		return tipoMotivo;
	}

	public void setTipoMotivo(int tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}

	public boolean isFinalizo() {
		return finalizo;
	}

	public void setFinalizo(boolean finalizo) {
		this.finalizo = finalizo;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
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

	public AutocompleteVo getAutocompleteVo() {
		return autocompleteVo;
	}

	public void setAutocompleteVo(AutocompleteVo autocompleteVo) {
		this.autocompleteVo = autocompleteVo;
	}

	public List<AutocompleteVo> getAutocompleteVos() {
		return autocompleteVos;
	}

	public void setAutocompleteVos(List<AutocompleteVo> autocompleteVos) {
		this.autocompleteVos = autocompleteVos;
	}

	public String[] getAutorizo() {
		return autorizo;
	}

	public void setAutorizo(String[] autorizo) {
		this.autorizo = autorizo;
	}
	
	
}
