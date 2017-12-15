package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;

import com.mx.sab.model.Usuariorol;
import com.mx.sab.model.Usuarios;

import lombok.Data;

@Data
public class LoginForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = -8611160628523392135L;
	private String rfc;
	private Usuarios usuarios;
	private int rol;
	private boolean autenticacionHuella;
	private boolean muestraRoles;
	private String nroAudit;
	private String ercDesc;
	private String tx_Marca;
	private String tx_Serie;
	private String tx_Modelo;
	private String tx_Fabric;
	
	private String la_Msg;
	private String la_Ver;
	
	private boolean lectorConectado;
	
	private boolean validarHuella;
	private boolean validarClave;
	
	private int idUsuario;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private int sexo;
	private String fechaNacimiento;
	private String clave;
	
	private boolean ingresarClave;
	
	private int idPregunta;
	private String respuesta;
	private String ingresaClave;
	private String confirmaClave;
	
	private String errorPregunta;
	private String errorRespuesta;
	private String errorClave;
	private String errorConfirmacionClave;
	
	private boolean terminoIngresarClave;
	private int intentosClave;
	private boolean irLogin;
	private boolean claveExitosa;
	private boolean respuestaExitosa;
	private String pregunta;
	
	private Collection<Usuariorol> usuariorols;

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public boolean isAutenticacionHuella() {
		return autenticacionHuella;
	}

	public void setAutenticacionHuella(boolean autenticacionHuella) {
		this.autenticacionHuella = autenticacionHuella;
	}

	public boolean isMuestraRoles() {
		return muestraRoles;
	}

	public void setMuestraRoles(boolean muestraRoles) {
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

	public String getTx_Marca() {
		return tx_Marca;
	}

	public void setTx_Marca(String tx_Marca) {
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

	public String getLa_Msg() {
		return la_Msg;
	}

	public void setLa_Msg(String la_Msg) {
		this.la_Msg = la_Msg;
	}

	public String getLa_Ver() {
		return la_Ver;
	}

	public void setLa_Ver(String la_Ver) {
		this.la_Ver = la_Ver;
	}

	public boolean isLectorConectado() {
		return lectorConectado;
	}

	public void setLectorConectado(boolean lectorConectado) {
		this.lectorConectado = lectorConectado;
	}

	public boolean isValidarHuella() {
		return validarHuella;
	}

	public void setValidarHuella(boolean validarHuella) {
		this.validarHuella = validarHuella;
	}

	public boolean isValidarClave() {
		return validarClave;
	}

	public void setValidarClave(boolean validarClave) {
		this.validarClave = validarClave;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isIngresarClave() {
		return ingresarClave;
	}

	public void setIngresarClave(boolean ingresarClave) {
		this.ingresarClave = ingresarClave;
	}

	public int getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getIngresaClave() {
		return ingresaClave;
	}

	public void setIngresaClave(String ingresaClave) {
		this.ingresaClave = ingresaClave;
	}

	public String getConfirmaClave() {
		return confirmaClave;
	}

	public void setConfirmaClave(String confirmaClave) {
		this.confirmaClave = confirmaClave;
	}

	public String getErrorPregunta() {
		return errorPregunta;
	}

	public void setErrorPregunta(String errorPregunta) {
		this.errorPregunta = errorPregunta;
	}

	public String getErrorRespuesta() {
		return errorRespuesta;
	}

	public void setErrorRespuesta(String errorRespuesta) {
		this.errorRespuesta = errorRespuesta;
	}

	public String getErrorClave() {
		return errorClave;
	}

	public void setErrorClave(String errorClave) {
		this.errorClave = errorClave;
	}

	public String getErrorConfirmacionClave() {
		return errorConfirmacionClave;
	}

	public void setErrorConfirmacionClave(String errorConfirmacionClave) {
		this.errorConfirmacionClave = errorConfirmacionClave;
	}

	public boolean isTerminoIngresarClave() {
		return terminoIngresarClave;
	}

	public void setTerminoIngresarClave(boolean terminoIngresarClave) {
		this.terminoIngresarClave = terminoIngresarClave;
	}

	public int getIntentosClave() {
		return intentosClave;
	}

	public void setIntentosClave(int intentosClave) {
		this.intentosClave = intentosClave;
	}

	public boolean isIrLogin() {
		return irLogin;
	}

	public void setIrLogin(boolean irLogin) {
		this.irLogin = irLogin;
	}

	public boolean isClaveExitosa() {
		return claveExitosa;
	}

	public void setClaveExitosa(boolean claveExitosa) {
		this.claveExitosa = claveExitosa;
	}

	public boolean isRespuestaExitosa() {
		return respuestaExitosa;
	}

	public void setRespuestaExitosa(boolean respuestaExitosa) {
		this.respuestaExitosa = respuestaExitosa;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Collection<Usuariorol> getUsuariorols() {
		return usuariorols;
	}

	public void setUsuariorols(Collection<Usuariorol> usuariorols) {
		this.usuariorols = usuariorols;
	}

	@Override
	public String toString() {
		return "LoginForm [rfc=" + rfc + ", usuarios=" + usuarios + ", rol=" + rol + ", autenticacionHuella="
				+ autenticacionHuella + ", muestraRoles=" + muestraRoles + ", nroAudit=" + nroAudit + ", ercDesc="
				+ ercDesc + ", tx_Marca=" + tx_Marca + ", tx_Serie=" + tx_Serie + ", tx_Modelo=" + tx_Modelo
				+ ", tx_Fabric=" + tx_Fabric + ", la_Msg=" + la_Msg + ", la_Ver=" + la_Ver + ", lectorConectado="
				+ lectorConectado + ", validarHuella=" + validarHuella + ", validarClave=" + validarClave
				+ ", idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", sexo=" + sexo + ", fechaNacimiento=" + fechaNacimiento
				+ ", clave=" + clave + ", ingresarClave=" + ingresarClave + ", idPregunta=" + idPregunta
				+ ", respuesta=" + respuesta + ", ingresaClave=" + ingresaClave + ", confirmaClave=" + confirmaClave
				+ ", errorPregunta=" + errorPregunta + ", errorRespuesta=" + errorRespuesta + ", errorClave="
				+ errorClave + ", errorConfirmacionClave=" + errorConfirmacionClave + ", terminoIngresarClave="
				+ terminoIngresarClave + ", intentosClave=" + intentosClave + ", irLogin=" + irLogin + ", claveExitosa="
				+ claveExitosa + ", respuestaExitosa=" + respuestaExitosa + ", pregunta=" + pregunta + ", usuariorols="
				+ usuariorols + "]";
	}
	
	
	
}
