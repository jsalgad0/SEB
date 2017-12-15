package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class DatosUsuarioForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = -1760992024782227819L;
	
	private boolean mostrarBotones; 
	private String especialidades;
	
	private String clave;
	private String nuevaClave;
	private String nuevaClaveConfirmacion;
	
	private String errorClave;
	private String errorNuevaClave;
	private String errorNuevaClaveConfirmacion;
	
	private int idPreguntaAntigua;
	private int idPregunta;
	private String respuestaAntigua;
	private String respuesta;
	
	private String errorIdPreguntaAntigua;
	private String errorRespuestaAntigua;
	
	private String exito;

	public boolean isMostrarBotones() {
		return mostrarBotones;
	}

	public void setMostrarBotones(boolean mostrarBotones) {
		this.mostrarBotones = mostrarBotones;
	}

	public String getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(String especialidades) {
		this.especialidades = especialidades;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNuevaClave() {
		return nuevaClave;
	}

	public void setNuevaClave(String nuevaClave) {
		this.nuevaClave = nuevaClave;
	}

	public String getNuevaClaveConfirmacion() {
		return nuevaClaveConfirmacion;
	}

	public void setNuevaClaveConfirmacion(String nuevaClaveConfirmacion) {
		this.nuevaClaveConfirmacion = nuevaClaveConfirmacion;
	}

	public String getErrorClave() {
		return errorClave;
	}

	public void setErrorClave(String errorClave) {
		this.errorClave = errorClave;
	}

	public String getErrorNuevaClave() {
		return errorNuevaClave;
	}

	public void setErrorNuevaClave(String errorNuevaClave) {
		this.errorNuevaClave = errorNuevaClave;
	}

	public String getErrorNuevaClaveConfirmacion() {
		return errorNuevaClaveConfirmacion;
	}

	public void setErrorNuevaClaveConfirmacion(String errorNuevaClaveConfirmacion) {
		this.errorNuevaClaveConfirmacion = errorNuevaClaveConfirmacion;
	}

	public int getIdPreguntaAntigua() {
		return idPreguntaAntigua;
	}

	public void setIdPreguntaAntigua(int idPreguntaAntigua) {
		this.idPreguntaAntigua = idPreguntaAntigua;
	}

	public int getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getRespuestaAntigua() {
		return respuestaAntigua;
	}

	public void setRespuestaAntigua(String respuestaAntigua) {
		this.respuestaAntigua = respuestaAntigua;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getErrorIdPreguntaAntigua() {
		return errorIdPreguntaAntigua;
	}

	public void setErrorIdPreguntaAntigua(String errorIdPreguntaAntigua) {
		this.errorIdPreguntaAntigua = errorIdPreguntaAntigua;
	}

	public String getErrorRespuestaAntigua() {
		return errorRespuestaAntigua;
	}

	public void setErrorRespuestaAntigua(String errorRespuestaAntigua) {
		this.errorRespuestaAntigua = errorRespuestaAntigua;
	}

	public String getExito() {
		return exito;
	}

	public void setExito(String exito) {
		this.exito = exito;
	}
	
	
	

}
