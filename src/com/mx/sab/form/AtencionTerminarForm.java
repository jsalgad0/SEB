package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;

import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.DiagnosticoVo;
import com.mx.sab.vo.LicenciaMedicaVo;
import com.mx.sab.vo.RecetasVo;

import lombok.Data;

@Data
public class AtencionTerminarForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = -6744168138024053914L;
	private int idAtencion;
	private Collection<DiagnosticoVo> diagnosticoVos;
	private Collection<RecetasVo> recetasVos;
	private Collection<AutocompleteVo> gabineteVos;
	private Collection<AutocompleteVo> laboratorioVos;
	private Collection<AutocompleteVo> otrosVos;
	private Collection<AutocompleteVo> consultorioVos;
	private LicenciaMedicaVo licenciaMedicaVo;
	
	private int idAfiliadoTerminar;
	private String nombreAfiliado;
	private String apellidoPaternoAfiliado;
	private String apellidoMaternoAfiliado;
	private int sexoAfiliado;
	private String fechaNacimientoAfiliado;
	private int estadoAfiliado;
	
	private int idUsuario;
	private String rfcUsuario;
	private String nombreUsuario;
	private String apellidoPaternoUsuario;
	private String apellidoMaternoUsuario;
	private int sexoUsuario;
	private String fechaNacimientoUsuario;
	private int estadoUsuario;
	
	private String mensajeAutentia;
	private String codigoAutentia;
	
	private int huellaAfiliado;
	private int huellaMedico;
	private int esNecesarioHuellaAfiliado;
	private int usarClave;
	private String clave;
	private int claveCorrecta;
	private int intentos;
	private int autorizacionMedico;//0 - huella correcta, 1 - huella incorrecta, 2 - clave correcta, 3 - clave incorrecta
	private int autorizacionAfiliado;//0 - no es necesario, 1 - huella correcta, 2 - huella incorrecta, 3 - permiso especial
	
	private boolean llenadoLicenciaMedica;
	private boolean llenadoReferencia;
	private boolean llenadoCuidadosMaternales;
	private boolean llenadoConstanciaSalud;
	
	
	private String especialidad;


	public int getIdAtencion() {
		return idAtencion;
	}


	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}


	public Collection<DiagnosticoVo> getDiagnosticoVos() {
		return diagnosticoVos;
	}


	public void setDiagnosticoVos(Collection<DiagnosticoVo> diagnosticoVos) {
		this.diagnosticoVos = diagnosticoVos;
	}


	public Collection<RecetasVo> getRecetasVos() {
		return recetasVos;
	}


	public void setRecetasVos(Collection<RecetasVo> recetasVos) {
		this.recetasVos = recetasVos;
	}


	public Collection<AutocompleteVo> getGabineteVos() {
		return gabineteVos;
	}


	public void setGabineteVos(Collection<AutocompleteVo> gabineteVos) {
		this.gabineteVos = gabineteVos;
	}


	public Collection<AutocompleteVo> getLaboratorioVos() {
		return laboratorioVos;
	}


	public void setLaboratorioVos(Collection<AutocompleteVo> laboratorioVos) {
		this.laboratorioVos = laboratorioVos;
	}


	public Collection<AutocompleteVo> getOtrosVos() {
		return otrosVos;
	}


	public void setOtrosVos(Collection<AutocompleteVo> otrosVos) {
		this.otrosVos = otrosVos;
	}


	public Collection<AutocompleteVo> getConsultorioVos() {
		return consultorioVos;
	}


	public void setConsultorioVos(Collection<AutocompleteVo> consultorioVos) {
		this.consultorioVos = consultorioVos;
	}


	public LicenciaMedicaVo getLicenciaMedicaVo() {
		return licenciaMedicaVo;
	}


	public void setLicenciaMedicaVo(LicenciaMedicaVo licenciaMedicaVo) {
		this.licenciaMedicaVo = licenciaMedicaVo;
	}


	public int getIdAfiliadoTerminar() {
		return idAfiliadoTerminar;
	}


	public void setIdAfiliadoTerminar(int idAfiliadoTerminar) {
		this.idAfiliadoTerminar = idAfiliadoTerminar;
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


	public String getFechaNacimientoAfiliado() {
		return fechaNacimientoAfiliado;
	}


	public void setFechaNacimientoAfiliado(String fechaNacimientoAfiliado) {
		this.fechaNacimientoAfiliado = fechaNacimientoAfiliado;
	}


	public int getEstadoAfiliado() {
		return estadoAfiliado;
	}


	public void setEstadoAfiliado(int estadoAfiliado) {
		this.estadoAfiliado = estadoAfiliado;
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getRfcUsuario() {
		return rfcUsuario;
	}


	public void setRfcUsuario(String rfcUsuario) {
		this.rfcUsuario = rfcUsuario;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public String getApellidoPaternoUsuario() {
		return apellidoPaternoUsuario;
	}


	public void setApellidoPaternoUsuario(String apellidoPaternoUsuario) {
		this.apellidoPaternoUsuario = apellidoPaternoUsuario;
	}


	public String getApellidoMaternoUsuario() {
		return apellidoMaternoUsuario;
	}


	public void setApellidoMaternoUsuario(String apellidoMaternoUsuario) {
		this.apellidoMaternoUsuario = apellidoMaternoUsuario;
	}


	public int getSexoUsuario() {
		return sexoUsuario;
	}


	public void setSexoUsuario(int sexoUsuario) {
		this.sexoUsuario = sexoUsuario;
	}


	public String getFechaNacimientoUsuario() {
		return fechaNacimientoUsuario;
	}


	public void setFechaNacimientoUsuario(String fechaNacimientoUsuario) {
		this.fechaNacimientoUsuario = fechaNacimientoUsuario;
	}


	public int getEstadoUsuario() {
		return estadoUsuario;
	}


	public void setEstadoUsuario(int estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}


	public String getMensajeAutentia() {
		return mensajeAutentia;
	}


	public void setMensajeAutentia(String mensajeAutentia) {
		this.mensajeAutentia = mensajeAutentia;
	}


	public String getCodigoAutentia() {
		return codigoAutentia;
	}


	public void setCodigoAutentia(String codigoAutentia) {
		this.codigoAutentia = codigoAutentia;
	}


	public int getHuellaAfiliado() {
		return huellaAfiliado;
	}


	public void setHuellaAfiliado(int huellaAfiliado) {
		this.huellaAfiliado = huellaAfiliado;
	}


	public int getHuellaMedico() {
		return huellaMedico;
	}


	public void setHuellaMedico(int huellaMedico) {
		this.huellaMedico = huellaMedico;
	}


	public int getEsNecesarioHuellaAfiliado() {
		return esNecesarioHuellaAfiliado;
	}


	public void setEsNecesarioHuellaAfiliado(int esNecesarioHuellaAfiliado) {
		this.esNecesarioHuellaAfiliado = esNecesarioHuellaAfiliado;
	}


	public int getUsarClave() {
		return usarClave;
	}


	public void setUsarClave(int usarClave) {
		this.usarClave = usarClave;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public int getClaveCorrecta() {
		return claveCorrecta;
	}


	public void setClaveCorrecta(int claveCorrecta) {
		this.claveCorrecta = claveCorrecta;
	}


	public int getIntentos() {
		return intentos;
	}


	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}


	public int getAutorizacionMedico() {
		return autorizacionMedico;
	}


	public void setAutorizacionMedico(int autorizacionMedico) {
		this.autorizacionMedico = autorizacionMedico;
	}


	public int getAutorizacionAfiliado() {
		return autorizacionAfiliado;
	}


	public void setAutorizacionAfiliado(int autorizacionAfiliado) {
		this.autorizacionAfiliado = autorizacionAfiliado;
	}


	public boolean isLlenadoLicenciaMedica() {
		return llenadoLicenciaMedica;
	}


	public void setLlenadoLicenciaMedica(boolean llenadoLicenciaMedica) {
		this.llenadoLicenciaMedica = llenadoLicenciaMedica;
	}


	public boolean isLlenadoReferencia() {
		return llenadoReferencia;
	}


	public void setLlenadoReferencia(boolean llenadoReferencia) {
		this.llenadoReferencia = llenadoReferencia;
	}


	public boolean isLlenadoCuidadosMaternales() {
		return llenadoCuidadosMaternales;
	}


	public void setLlenadoCuidadosMaternales(boolean llenadoCuidadosMaternales) {
		this.llenadoCuidadosMaternales = llenadoCuidadosMaternales;
	}


	public boolean isLlenadoConstanciaSalud() {
		return llenadoConstanciaSalud;
	}


	public void setLlenadoConstanciaSalud(boolean llenadoConstanciaSalud) {
		this.llenadoConstanciaSalud = llenadoConstanciaSalud;
	}


	public String getEspecialidad() {
		return especialidad;
	}


	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	
	
	
}
