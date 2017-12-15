package com.mx.sab.form;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class InformacionPacienteForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = 3025760324971541112L;
	private int idAgenda;
	private int idAtencionMedica;
	private String calle;
	private String numInt;
	private String numExt;
	private String codigoPostal;
	private String telefono;
	private String mail;
	private Date fechaNacimiento;
	private int idSexo;
	private int idEstado;
	private int idMunicipio;
	private int idColonia;
	private int idEstadoCivil;
	private int idEscolaridad;
	private int idOcupacion;
	private String nacionalidad;
	private String religion;
	private String nivelSocioEconomico;
	private int idGrupoSanguineo;
	private int tipoSanguineo;
	private int idInmigrante;
	private int idIndigena;
	private String responsableNombre;
	private String responsableEdad;
	private String responsableParentezco;
	private String responsableDireccion;
	private String responsableLugar;
	private String responsableTelefono;
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public int getIdAtencionMedica() {
		return idAtencionMedica;
	}
	public void setIdAtencionMedica(int idAtencionMedica) {
		this.idAtencionMedica = idAtencionMedica;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumInt() {
		return numInt;
	}
	public void setNumInt(String numInt) {
		this.numInt = numInt;
	}
	public String getNumExt() {
		return numExt;
	}
	public void setNumExt(String numExt) {
		this.numExt = numExt;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getIdSexo() {
		return idSexo;
	}
	public void setIdSexo(int idSexo) {
		this.idSexo = idSexo;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public int getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public int getIdColonia() {
		return idColonia;
	}
	public void setIdColonia(int idColonia) {
		this.idColonia = idColonia;
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
	public int getIdOcupacion() {
		return idOcupacion;
	}
	public void setIdOcupacion(int idOcupacion) {
		this.idOcupacion = idOcupacion;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getNivelSocioEconomico() {
		return nivelSocioEconomico;
	}
	public void setNivelSocioEconomico(String nivelSocioEconomico) {
		this.nivelSocioEconomico = nivelSocioEconomico;
	}
	public int getIdGrupoSanguineo() {
		return idGrupoSanguineo;
	}
	public void setIdGrupoSanguineo(int idGrupoSanguineo) {
		this.idGrupoSanguineo = idGrupoSanguineo;
	}
	public int getTipoSanguineo() {
		return tipoSanguineo;
	}
	public void setTipoSanguineo(int tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}
	public int getIdInmigrante() {
		return idInmigrante;
	}
	public void setIdInmigrante(int idInmigrante) {
		this.idInmigrante = idInmigrante;
	}
	public int getIdIndigena() {
		return idIndigena;
	}
	public void setIdIndigena(int idIndigena) {
		this.idIndigena = idIndigena;
	}
	public String getResponsableNombre() {
		return responsableNombre;
	}
	public void setResponsableNombre(String responsableNombre) {
		this.responsableNombre = responsableNombre;
	}
	public String getResponsableEdad() {
		return responsableEdad;
	}
	public void setResponsableEdad(String responsableEdad) {
		this.responsableEdad = responsableEdad;
	}
	public String getResponsableParentezco() {
		return responsableParentezco;
	}
	public void setResponsableParentezco(String responsableParentezco) {
		this.responsableParentezco = responsableParentezco;
	}
	public String getResponsableDireccion() {
		return responsableDireccion;
	}
	public void setResponsableDireccion(String responsableDireccion) {
		this.responsableDireccion = responsableDireccion;
	}
	public String getResponsableLugar() {
		return responsableLugar;
	}
	public void setResponsableLugar(String responsableLugar) {
		this.responsableLugar = responsableLugar;
	}
	public String getResponsableTelefono() {
		return responsableTelefono;
	}
	public void setResponsableTelefono(String responsableTelefono) {
		this.responsableTelefono = responsableTelefono;
	}
	
	
	
}
