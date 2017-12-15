package com.mx.sab.form;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import lombok.Data;

@Data
public class FinalizarAtencionForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = -3697626930478280488L;
	private int idAgenda;
	private int idEstudios;
	private String diagnosticos;
	private String prestaciones;
	private String medicamentos;
	private String estudiosLaboratorio;
	private String estudiosGabinete;
	private String otrosEstudios;
	private String licenciaMedica;
	private String referenciaEspecialista;
	private String contrareferenciaS;
	
	private String rfc;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private int sexo;
	private String fechaDeNacimiento;
	private int estado;
	private int idUsuario;
	
	private int idAuditoria;
	
	private boolean huellaAfiliado;
	private boolean huellaMedico;
	
	private int idAfiliado;
	private String nombreAfiliado;
	private String apellidoPaternoAfiliado;
	private String apellidoMaternoAfiliado;
	private int sexoAfiliado;
	private String fechaDeNacimientoAfiliado;
	private int estadoAfiliado;
	
	private boolean atencionFinalizada;
	private ByteArrayOutputStream file;
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public int getIdEstudios() {
		return idEstudios;
	}
	public void setIdEstudios(int idEstudios) {
		this.idEstudios = idEstudios;
	}
	public String getDiagnosticos() {
		return diagnosticos;
	}
	public void setDiagnosticos(String diagnosticos) {
		this.diagnosticos = diagnosticos;
	}
	public String getPrestaciones() {
		return prestaciones;
	}
	public void setPrestaciones(String prestaciones) {
		this.prestaciones = prestaciones;
	}
	public String getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(String medicamentos) {
		this.medicamentos = medicamentos;
	}
	public String getEstudiosLaboratorio() {
		return estudiosLaboratorio;
	}
	public void setEstudiosLaboratorio(String estudiosLaboratorio) {
		this.estudiosLaboratorio = estudiosLaboratorio;
	}
	public String getEstudiosGabinete() {
		return estudiosGabinete;
	}
	public void setEstudiosGabinete(String estudiosGabinete) {
		this.estudiosGabinete = estudiosGabinete;
	}
	public String getOtrosEstudios() {
		return otrosEstudios;
	}
	public void setOtrosEstudios(String otrosEstudios) {
		this.otrosEstudios = otrosEstudios;
	}
	public String getLicenciaMedica() {
		return licenciaMedica;
	}
	public void setLicenciaMedica(String licenciaMedica) {
		this.licenciaMedica = licenciaMedica;
	}
	public String getReferenciaEspecialista() {
		return referenciaEspecialista;
	}
	public void setReferenciaEspecialista(String referenciaEspecialista) {
		this.referenciaEspecialista = referenciaEspecialista;
	}
	public String getContrareferenciaS() {
		return contrareferenciaS;
	}
	public void setContrareferenciaS(String contrareferenciaS) {
		this.contrareferenciaS = contrareferenciaS;
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
	public int getSexo() {
		return sexo;
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdAuditoria() {
		return idAuditoria;
	}
	public void setIdAuditoria(int idAuditoria) {
		this.idAuditoria = idAuditoria;
	}
	public boolean isHuellaAfiliado() {
		return huellaAfiliado;
	}
	public void setHuellaAfiliado(boolean huellaAfiliado) {
		this.huellaAfiliado = huellaAfiliado;
	}
	public boolean isHuellaMedico() {
		return huellaMedico;
	}
	public void setHuellaMedico(boolean huellaMedico) {
		this.huellaMedico = huellaMedico;
	}
	public int getIdAfiliado() {
		return idAfiliado;
	}
	public void setIdAfiliado(int idAfiliado) {
		this.idAfiliado = idAfiliado;
	}
	public String getNombreAfiliado() {
		return nombreAfiliado;
	}
	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}
	public String getApellidoPaternoAfiliado() {
		return apellidoPaternoAfiliado;
	}
	public void setApellidoPaternoAfiliado(String apellidoPaternoAfiliado) {
		this.apellidoPaternoAfiliado = apellidoPaternoAfiliado;
	}
	public String getApellidoMaternoAfiliado() {
		return apellidoMaternoAfiliado;
	}
	public void setApellidoMaternoAfiliado(String apellidoMaternoAfiliado) {
		this.apellidoMaternoAfiliado = apellidoMaternoAfiliado;
	}
	public int getSexoAfiliado() {
		return sexoAfiliado;
	}
	public void setSexoAfiliado(int sexoAfiliado) {
		this.sexoAfiliado = sexoAfiliado;
	}
	public String getFechaDeNacimientoAfiliado() {
		return fechaDeNacimientoAfiliado;
	}
	public void setFechaDeNacimientoAfiliado(String fechaDeNacimientoAfiliado) {
		this.fechaDeNacimientoAfiliado = fechaDeNacimientoAfiliado;
	}
	public int getEstadoAfiliado() {
		return estadoAfiliado;
	}
	public void setEstadoAfiliado(int estadoAfiliado) {
		this.estadoAfiliado = estadoAfiliado;
	}
	public boolean isAtencionFinalizada() {
		return atencionFinalizada;
	}
	public void setAtencionFinalizada(boolean atencionFinalizada) {
		this.atencionFinalizada = atencionFinalizada;
	}
	public ByteArrayOutputStream getFile() {
		return file;
	}
	public void setFile(ByteArrayOutputStream file) {
		this.file = file;
	}
	
	
	
}
