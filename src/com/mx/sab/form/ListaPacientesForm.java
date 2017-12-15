package com.mx.sab.form;

import java.io.Serializable;
import java.util.List;

import com.mx.sab.model.Notamedica;
import com.mx.sab.vo.AutocompleteVo;

import lombok.Data;

@Data
public class ListaPacientesForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = 5826841384465763925L;
	private int idAgenda;
	private String busquedaA;
	private String busquedaE;
	private Notamedica notamedica;
	private String diagnostico;
	private int idDiagnostico;
	private List<AutocompleteVo> autocompleteVos;
	private String diagnosticoAux;
	private int idDiagnosticoAux;
	private String prestacionAux;
	private int idPrestacionAux;	
	private int idEstudios;
	private boolean editarDiagnostico;
	
	
	private List<AutocompleteVo> prestacionesAutocompleteVos;
	
	private boolean finalizarAtencion;
	private boolean finalizoAtencionMedica;
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public String getBusquedaA() {
		return busquedaA;
	}
	public void setBusquedaA(String busquedaA) {
		this.busquedaA = busquedaA;
	}
	public String getBusquedaE() {
		return busquedaE;
	}
	public void setBusquedaE(String busquedaE) {
		this.busquedaE = busquedaE;
	}
	public Notamedica getNotamedica() {
		return notamedica;
	}
	public void setNotamedica(Notamedica notamedica) {
		this.notamedica = notamedica;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public int getIdDiagnostico() {
		return idDiagnostico;
	}
	public void setIdDiagnostico(int idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}
	public List<AutocompleteVo> getAutocompleteVos() {
		return autocompleteVos;
	}
	public void setAutocompleteVos(List<AutocompleteVo> autocompleteVos) {
		this.autocompleteVos = autocompleteVos;
	}
	public String getDiagnosticoAux() {
		return diagnosticoAux;
	}
	public void setDiagnosticoAux(String diagnosticoAux) {
		this.diagnosticoAux = diagnosticoAux;
	}
	public int getIdDiagnosticoAux() {
		return idDiagnosticoAux;
	}
	public void setIdDiagnosticoAux(int idDiagnosticoAux) {
		this.idDiagnosticoAux = idDiagnosticoAux;
	}
	public String getPrestacionAux() {
		return prestacionAux;
	}
	public void setPrestacionAux(String prestacionAux) {
		this.prestacionAux = prestacionAux;
	}
	public int getIdPrestacionAux() {
		return idPrestacionAux;
	}
	public void setIdPrestacionAux(int idPrestacionAux) {
		this.idPrestacionAux = idPrestacionAux;
	}
	public int getIdEstudios() {
		return idEstudios;
	}
	public void setIdEstudios(int idEstudios) {
		this.idEstudios = idEstudios;
	}
	public boolean isEditarDiagnostico() {
		return editarDiagnostico;
	}
	public void setEditarDiagnostico(boolean editarDiagnostico) {
		this.editarDiagnostico = editarDiagnostico;
	}
	public List<AutocompleteVo> getPrestacionesAutocompleteVos() {
		return prestacionesAutocompleteVos;
	}
	public void setPrestacionesAutocompleteVos(List<AutocompleteVo> prestacionesAutocompleteVos) {
		this.prestacionesAutocompleteVos = prestacionesAutocompleteVos;
	}
	public boolean isFinalizarAtencion() {
		return finalizarAtencion;
	}
	public void setFinalizarAtencion(boolean finalizarAtencion) {
		this.finalizarAtencion = finalizarAtencion;
	}
	public boolean isFinalizoAtencionMedica() {
		return finalizoAtencionMedica;
	}
	public void setFinalizoAtencionMedica(boolean finalizoAtencionMedica) {
		this.finalizoAtencionMedica = finalizoAtencionMedica;
	}
	
	
	
}
