package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.Convenios;
import com.mx.sab.model.Prestacionprestador;

@Data
public class AtencionMedicaForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = -70052264366871416L;
	private int idAgenda;
	private int idPersona;
	private Agenda agenda;
	private Afiliadotipoidentificador afiliadotipoidentificador;
	private Collection<Convenios> convenios;
	private int idConvenio;
	private String tipoAfiliado;
	private Catprestacion catPrestacion;
	private Prestacionprestador prestacionprestador;
	private String fechaDeNacimiento;
	private String dato;
	private String tipoDato;
	private String rfc;
	private boolean menorDeEdad;
	
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	public Afiliadotipoidentificador getAfiliadotipoidentificador() {
		return afiliadotipoidentificador;
	}
	public void setAfiliadotipoidentificador(Afiliadotipoidentificador afiliadotipoidentificador) {
		this.afiliadotipoidentificador = afiliadotipoidentificador;
	}
	public Collection<Convenios> getConvenios() {
		return convenios;
	}
	public void setConvenios(Collection<Convenios> convenios) {
		this.convenios = convenios;
	}
	public int getIdConvenio() {
		return idConvenio;
	}
	public void setIdConvenio(int idConvenio) {
		this.idConvenio = idConvenio;
	}
	public String getTipoAfiliado() {
		return tipoAfiliado;
	}
	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}
	public Catprestacion getCatPrestacion() {
		return catPrestacion;
	}
	public void setCatPrestacion(Catprestacion catPrestacion) {
		this.catPrestacion = catPrestacion;
	}
	public Prestacionprestador getPrestacionprestador() {
		return prestacionprestador;
	}
	public void setPrestacionprestador(Prestacionprestador prestacionprestador) {
		this.prestacionprestador = prestacionprestador;
	}
	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public String getDato() {
		return dato;
	}
	public void setDato(String dato) {
		this.dato = dato;
	}
	public String getTipoDato() {
		return tipoDato;
	}
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public boolean isMenorDeEdad() {
		return menorDeEdad;
	}
	public void setMenorDeEdad(boolean menorDeEdad) {
		this.menorDeEdad = menorDeEdad;
	}
	
	
}
