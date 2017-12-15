package com.mx.sab.vo;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Menu;
import com.mx.sab.model.Usuarios;

import lombok.Data;

@Data
public class UserInfo implements Principal, Serializable{

	private static final long serialVersionUID = -2356205702659425532L;
	private String autenticacionHuella; 
	private String muestraRoles;
	private String nroAudit;
	private String ercDesc;
	private String rol;
	private String name;
	private List<Menu> menus;
	private int tx_Marca;
	private String tx_Serie;
	private String tx_Modelo;
	private String tx_Fabric;
	private String rolNombre;
	private Usuarios usuarios;
	private Lugaresdeatencion lugaresdeatencion;
	public String getAutenticacionHuella() {
		return autenticacionHuella;
	}
	public void setAutenticacionHuella(String autenticacionHuella) {
		this.autenticacionHuella = autenticacionHuella;
	}
	public String getMuestraRoles() {
		return muestraRoles;
	}
	public void setMuestraRoles(String muestraRoles) {
		this.muestraRoles = muestraRoles;
	}
	public String getNroAudit() {
		return nroAudit;
	}
	public void setNroAudit(String nroAudit) {
		this.nroAudit = nroAudit;
	}
	public String getErcDesc() {
		return ercDesc;
	}
	public void setErcDesc(String ercDesc) {
		this.ercDesc = ercDesc;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public int getTx_Marca() {
		return tx_Marca;
	}
	public void setTx_Marca(int tx_Marca) {
		this.tx_Marca = tx_Marca;
	}
	public String getTx_Serie() {
		return tx_Serie;
	}
	public void setTx_Serie(String tx_Serie) {
		this.tx_Serie = tx_Serie;
	}
	public String getTx_Modelo() {
		return tx_Modelo;
	}
	public void setTx_Modelo(String tx_Modelo) {
		this.tx_Modelo = tx_Modelo;
	}
	public String getTx_Fabric() {
		return tx_Fabric;
	}
	public void setTx_Fabric(String tx_Fabric) {
		this.tx_Fabric = tx_Fabric;
	}
	public String getRolNombre() {
		return rolNombre;
	}
	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}
	public Usuarios getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}
	public Lugaresdeatencion getLugaresdeatencion() {
		return lugaresdeatencion;
	}
	public void setLugaresdeatencion(Lugaresdeatencion lugaresdeatencion) {
		this.lugaresdeatencion = lugaresdeatencion;
	}
    	
	

}
