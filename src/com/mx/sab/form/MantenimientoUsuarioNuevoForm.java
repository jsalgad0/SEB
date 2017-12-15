package com.mx.sab.form;

import java.io.Serializable;

import com.mx.sab.model.Usuarios;

import lombok.Data;

@Data
public class MantenimientoUsuarioNuevoForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = 8531683608103136601L;
	private Usuarios usuarios;
	private int idUsuario;
	private String rfc;
	private String rfcAux;
	private String fechaNacimiento;
	private int sexo = 1;
	private String[] roles;
	
	private int idEspecialidad1;
	private String cedulaProfesional1;
	private String idInstitucion1;
	
	private int idEspecialidad2;
	private String cedulaProfesional2;
	private String idInstitucion2;
	
	private int idEspecialidad3;
	private String cedulaProfesional3;
	private String idInstitucion3;
	
	private String claveInterna;
	
	private int funcionamiento;
	
	private boolean mostrarDeclaracion;
	private int estadoUsuario;
	private boolean validarEspecialidades;
	private boolean termino;
	private boolean statusInput;
	private boolean statusSelect;
	
	private String rfcRegistrado;
	private String nombreRegistrado;
	private String lugaresRegistrado;
	private String lugarRegistrado;
	public Usuarios getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getRfcAux() {
		return rfcAux;
	}
	public void setRfcAux(String rfcAux) {
		this.rfcAux = rfcAux;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getSexo() {
		return sexo;
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	public int getIdEspecialidad1() {
		return idEspecialidad1;
	}
	public void setIdEspecialidad1(int idEspecialidad1) {
		this.idEspecialidad1 = idEspecialidad1;
	}
	public String getCedulaProfesional1() {
		return cedulaProfesional1;
	}
	public void setCedulaProfesional1(String cedulaProfesional1) {
		this.cedulaProfesional1 = cedulaProfesional1;
	}
	public String getIdInstitucion1() {
		return idInstitucion1;
	}
	public void setIdInstitucion1(String idInstitucion1) {
		this.idInstitucion1 = idInstitucion1;
	}
	public int getIdEspecialidad2() {
		return idEspecialidad2;
	}
	public void setIdEspecialidad2(int idEspecialidad2) {
		this.idEspecialidad2 = idEspecialidad2;
	}
	public String getCedulaProfesional2() {
		return cedulaProfesional2;
	}
	public void setCedulaProfesional2(String cedulaProfesional2) {
		this.cedulaProfesional2 = cedulaProfesional2;
	}
	public String getIdInstitucion2() {
		return idInstitucion2;
	}
	public void setIdInstitucion2(String idInstitucion2) {
		this.idInstitucion2 = idInstitucion2;
	}
	public int getIdEspecialidad3() {
		return idEspecialidad3;
	}
	public void setIdEspecialidad3(int idEspecialidad3) {
		this.idEspecialidad3 = idEspecialidad3;
	}
	public String getCedulaProfesional3() {
		return cedulaProfesional3;
	}
	public void setCedulaProfesional3(String cedulaProfesional3) {
		this.cedulaProfesional3 = cedulaProfesional3;
	}
	public String getIdInstitucion3() {
		return idInstitucion3;
	}
	public void setIdInstitucion3(String idInstitucion3) {
		this.idInstitucion3 = idInstitucion3;
	}
	public String getClaveInterna() {
		return claveInterna;
	}
	public void setClaveInterna(String claveInterna) {
		this.claveInterna = claveInterna;
	}
	public int getFuncionamiento() {
		return funcionamiento;
	}
	public void setFuncionamiento(int funcionamiento) {
		this.funcionamiento = funcionamiento;
	}
	public boolean isMostrarDeclaracion() {
		return mostrarDeclaracion;
	}
	public void setMostrarDeclaracion(boolean mostrarDeclaracion) {
		this.mostrarDeclaracion = mostrarDeclaracion;
	}
	public int getEstadoUsuario() {
		return estadoUsuario;
	}
	public void setEstadoUsuario(int estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}
	public boolean isValidarEspecialidades() {
		return validarEspecialidades;
	}
	public void setValidarEspecialidades(boolean validarEspecialidades) {
		this.validarEspecialidades = validarEspecialidades;
	}
	public boolean isTermino() {
		return termino;
	}
	public void setTermino(boolean termino) {
		this.termino = termino;
	}
	public boolean isStatusInput() {
		return statusInput;
	}
	public void setStatusInput(boolean statusInput) {
		this.statusInput = statusInput;
	}
	public boolean isStatusSelect() {
		return statusSelect;
	}
	public void setStatusSelect(boolean statusSelect) {
		this.statusSelect = statusSelect;
	}
	public String getRfcRegistrado() {
		return rfcRegistrado;
	}
	public void setRfcRegistrado(String rfcRegistrado) {
		this.rfcRegistrado = rfcRegistrado;
	}
	public String getNombreRegistrado() {
		return nombreRegistrado;
	}
	public void setNombreRegistrado(String nombreRegistrado) {
		this.nombreRegistrado = nombreRegistrado;
	}
	public String getLugaresRegistrado() {
		return lugaresRegistrado;
	}
	public void setLugaresRegistrado(String lugaresRegistrado) {
		this.lugaresRegistrado = lugaresRegistrado;
	}
	public String getLugarRegistrado() {
		return lugarRegistrado;
	}
	public void setLugarRegistrado(String lugarRegistrado) {
		this.lugarRegistrado = lugarRegistrado;
	}
	
	
	
	
}
