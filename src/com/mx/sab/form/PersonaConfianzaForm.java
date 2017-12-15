package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

import com.mx.sab.model.Personasdeconfianza;

@Data
public class PersonaConfianzaForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = -6565675487505921202L;
	private Personasdeconfianza personasdeconfianza;
	private int idTipoIdentificador;
	private String identificador;
	private int idAgenda;
	private int idPersona;
	private int idAfiliado;
	private int idSexo;
	private String fechaNacimiento;
	
	public Personasdeconfianza getPersonasdeconfianza() {
		return personasdeconfianza;
	}
	public void setPersonasdeconfianza(Personasdeconfianza personasdeconfianza) {
		this.personasdeconfianza = personasdeconfianza;
	}
	public int getIdTipoIdentificador() {
		return idTipoIdentificador;
	}
	public void setIdTipoIdentificador(int idTipoIdentificador) {
		this.idTipoIdentificador = idTipoIdentificador;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
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
	public int getIdAfiliado() {
		return idAfiliado;
	}
	public void setIdAfiliado(int idAfiliado) {
		this.idAfiliado = idAfiliado;
	}
	public int getIdSexo() {
		return idSexo;
	}
	public void setIdSexo(int idSexo) {
		this.idSexo = idSexo;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
}
