package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

import com.mx.sab.model.Usuarios;
import com.mx.sab.vo.EspecialiadesVo;
import com.mx.sab.vo.TipoIdentificadorVo;

@Data
public class UsuarioForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = -5920453740672738762L;
	private Usuarios usuario;
	private String idSexo;
	private String idModulo;
	private String fechaDeNacimiento;
	private Collection<String> roles;
	private Collection<String> idLugarAtencion;
	private Collection<String> idEspecialidades;
	private Collection<String> idTipoIdentificador;
	private EspecialiadesVo especialiadesVo;
	private TipoIdentificadorVo tipoIdentificadorVo;
	private String idEstado;
	private String adminInstitucion;
	private String error;
	private String idUsuario;
	private int idAuditoria;
	
	private String busqueda;

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public String getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(String idSexo) {
		this.idSexo = idSexo;
	}

	public String getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(String idModulo) {
		this.idModulo = idModulo;
	}

	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public Collection<String> getRoles() {
		return roles;
	}

	public void setRoles(Collection<String> roles) {
		this.roles = roles;
	}

	public Collection<String> getIdLugarAtencion() {
		return idLugarAtencion;
	}

	public void setIdLugarAtencion(Collection<String> idLugarAtencion) {
		this.idLugarAtencion = idLugarAtencion;
	}

	public Collection<String> getIdEspecialidades() {
		return idEspecialidades;
	}

	public void setIdEspecialidades(Collection<String> idEspecialidades) {
		this.idEspecialidades = idEspecialidades;
	}

	public Collection<String> getIdTipoIdentificador() {
		return idTipoIdentificador;
	}

	public void setIdTipoIdentificador(Collection<String> idTipoIdentificador) {
		this.idTipoIdentificador = idTipoIdentificador;
	}

	public EspecialiadesVo getEspecialiadesVo() {
		return especialiadesVo;
	}

	public void setEspecialiadesVo(EspecialiadesVo especialiadesVo) {
		this.especialiadesVo = especialiadesVo;
	}

	public TipoIdentificadorVo getTipoIdentificadorVo() {
		return tipoIdentificadorVo;
	}

	public void setTipoIdentificadorVo(TipoIdentificadorVo tipoIdentificadorVo) {
		this.tipoIdentificadorVo = tipoIdentificadorVo;
	}

	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public String getAdminInstitucion() {
		return adminInstitucion;
	}

	public void setAdminInstitucion(String adminInstitucion) {
		this.adminInstitucion = adminInstitucion;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdAuditoria() {
		return idAuditoria;
	}

	public void setIdAuditoria(int idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
	
	
	
	
}
