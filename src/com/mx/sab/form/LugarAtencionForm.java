package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.mx.sab.model.Roles;

import lombok.Data;

@Data
public class LugarAtencionForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = 1501203209489384906L;
	private int idLugarAtencion;
	private String lugarAtencion;
	private int idEstado;
	private int idMunicipio;
	private int idColonia;
	private String calle;
	private String numeroExterno;
	private String numeroInterno;
	private String cp;
	private String nombre;
	private String telefono1;
	private String telefono2;
	private String correoLugarAtencion;
	private String codigo;
	
	private int busqueda;
	
	private List<String> rolesSeleccionadosPrimero;
	private List<String> rolesSeleccionadosSegundo;
	private Collection<Roles> rolesLugarAtencionPrimero;
	private Collection<Roles> rolesLugarAtencionSegundo;
	public int getIdLugarAtencion() {
		return idLugarAtencion;
	}
	public void setIdLugarAtencion(int idLugarAtencion) {
		this.idLugarAtencion = idLugarAtencion;
	}
	public String getLugarAtencion() {
		return lugarAtencion;
	}
	public void setLugarAtencion(String lugarAtencion) {
		this.lugarAtencion = lugarAtencion;
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
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumeroExterno() {
		return numeroExterno;
	}
	public void setNumeroExterno(String numeroExterno) {
		this.numeroExterno = numeroExterno;
	}
	public String getNumeroInterno() {
		return numeroInterno;
	}
	public void setNumeroInterno(String numeroInterno) {
		this.numeroInterno = numeroInterno;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getCorreoLugarAtencion() {
		return correoLugarAtencion;
	}
	public void setCorreoLugarAtencion(String correoLugarAtencion) {
		this.correoLugarAtencion = correoLugarAtencion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getBusqueda() {
		return busqueda;
	}
	public void setBusqueda(int busqueda) {
		this.busqueda = busqueda;
	}
	public List<String> getRolesSeleccionadosPrimero() {
		return rolesSeleccionadosPrimero;
	}
	public void setRolesSeleccionadosPrimero(List<String> rolesSeleccionadosPrimero) {
		this.rolesSeleccionadosPrimero = rolesSeleccionadosPrimero;
	}
	public List<String> getRolesSeleccionadosSegundo() {
		return rolesSeleccionadosSegundo;
	}
	public void setRolesSeleccionadosSegundo(List<String> rolesSeleccionadosSegundo) {
		this.rolesSeleccionadosSegundo = rolesSeleccionadosSegundo;
	}
	public Collection<Roles> getRolesLugarAtencionPrimero() {
		return rolesLugarAtencionPrimero;
	}
	public void setRolesLugarAtencionPrimero(Collection<Roles> rolesLugarAtencionPrimero) {
		this.rolesLugarAtencionPrimero = rolesLugarAtencionPrimero;
	}
	public Collection<Roles> getRolesLugarAtencionSegundo() {
		return rolesLugarAtencionSegundo;
	}
	public void setRolesLugarAtencionSegundo(Collection<Roles> rolesLugarAtencionSegundo) {
		this.rolesLugarAtencionSegundo = rolesLugarAtencionSegundo;
	}
	
	
	
	
}
