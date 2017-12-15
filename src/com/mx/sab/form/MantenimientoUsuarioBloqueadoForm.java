package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class MantenimientoUsuarioBloqueadoForm extends ParentForm implements Serializable{
	
	private static final long serialVersionUID = 3800498162099393788L;
	private int idUsuario;
	private String apellidoMaterno;
	private String apellidoPaterno;
	private String curp;
	private String fechaNacimiento;
	private String nombre;
	private String rfc;
	private int sexo;
	private String telefono;
	private String mail;
	
	private int idEspecialidad1;
	private String idInstitucion1;
	private String cedulaProfesional1;
	private int idEspecialidad2;
	private String idInstitucion2;
	private String cedulaProfesional2;
	private int idEspecialidad3;
	private String idInstitucion3;
	private String cedulaProfesional3;
	
	private String claveInterna;
	private String regSs;
	private int vigencia;
	private String[] roles;
	
	private int idUsuarioAdministrador;
	private String rfcAdministrador;
	private String nombreAdministrador;
	private String apellidoPaternoAdministrador;
	private String apellidoMaternoAdministrador;
	private int sexoAdministrador;
	private String fechaNacimientoAdministrador;
	
	private boolean termino;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
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

	public int getIdEspecialidad1() {
		return idEspecialidad1;
	}

	public void setIdEspecialidad1(int idEspecialidad1) {
		this.idEspecialidad1 = idEspecialidad1;
	}

	public String getIdInstitucion1() {
		return idInstitucion1;
	}

	public void setIdInstitucion1(String idInstitucion1) {
		this.idInstitucion1 = idInstitucion1;
	}

	public String getCedulaProfesional1() {
		return cedulaProfesional1;
	}

	public void setCedulaProfesional1(String cedulaProfesional1) {
		this.cedulaProfesional1 = cedulaProfesional1;
	}

	public int getIdEspecialidad2() {
		return idEspecialidad2;
	}

	public void setIdEspecialidad2(int idEspecialidad2) {
		this.idEspecialidad2 = idEspecialidad2;
	}

	public String getIdInstitucion2() {
		return idInstitucion2;
	}

	public void setIdInstitucion2(String idInstitucion2) {
		this.idInstitucion2 = idInstitucion2;
	}

	public String getCedulaProfesional2() {
		return cedulaProfesional2;
	}

	public void setCedulaProfesional2(String cedulaProfesional2) {
		this.cedulaProfesional2 = cedulaProfesional2;
	}

	public int getIdEspecialidad3() {
		return idEspecialidad3;
	}

	public void setIdEspecialidad3(int idEspecialidad3) {
		this.idEspecialidad3 = idEspecialidad3;
	}

	public String getIdInstitucion3() {
		return idInstitucion3;
	}

	public void setIdInstitucion3(String idInstitucion3) {
		this.idInstitucion3 = idInstitucion3;
	}

	public String getCedulaProfesional3() {
		return cedulaProfesional3;
	}

	public void setCedulaProfesional3(String cedulaProfesional3) {
		this.cedulaProfesional3 = cedulaProfesional3;
	}

	public String getClaveInterna() {
		return claveInterna;
	}

	public void setClaveInterna(String claveInterna) {
		this.claveInterna = claveInterna;
	}

	public String getRegSs() {
		return regSs;
	}

	public void setRegSs(String regSs) {
		this.regSs = regSs;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public int getIdUsuarioAdministrador() {
		return idUsuarioAdministrador;
	}

	public void setIdUsuarioAdministrador(int idUsuarioAdministrador) {
		this.idUsuarioAdministrador = idUsuarioAdministrador;
	}

	public String getRfcAdministrador() {
		return rfcAdministrador;
	}

	public void setRfcAdministrador(String rfcAdministrador) {
		this.rfcAdministrador = rfcAdministrador;
	}

	public String getNombreAdministrador() {
		return nombreAdministrador;
	}

	public void setNombreAdministrador(String nombreAdministrador) {
		this.nombreAdministrador = nombreAdministrador;
	}

	public String getApellidoPaternoAdministrador() {
		return apellidoPaternoAdministrador;
	}

	public void setApellidoPaternoAdministrador(String apellidoPaternoAdministrador) {
		this.apellidoPaternoAdministrador = apellidoPaternoAdministrador;
	}

	public String getApellidoMaternoAdministrador() {
		return apellidoMaternoAdministrador;
	}

	public void setApellidoMaternoAdministrador(String apellidoMaternoAdministrador) {
		this.apellidoMaternoAdministrador = apellidoMaternoAdministrador;
	}

	public int getSexoAdministrador() {
		return sexoAdministrador;
	}

	public void setSexoAdministrador(int sexoAdministrador) {
		this.sexoAdministrador = sexoAdministrador;
	}

	public String getFechaNacimientoAdministrador() {
		return fechaNacimientoAdministrador;
	}

	public void setFechaNacimientoAdministrador(String fechaNacimientoAdministrador) {
		this.fechaNacimientoAdministrador = fechaNacimientoAdministrador;
	}

	public boolean isTermino() {
		return termino;
	}

	public void setTermino(boolean termino) {
		this.termino = termino;
	}
	
	
	
}

