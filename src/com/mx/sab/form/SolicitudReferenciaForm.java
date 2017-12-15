package com.mx.sab.form;

import java.io.Serializable;

import com.mx.sab.model.Solicitudreferencia;

import lombok.Data;

@Data
public class SolicitudReferenciaForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = 7018484398522868594L;
	private int idAtencion;
	private int idCatSoliReferenciaMotivo;
	private int idCatSoliRefrerenciaPor;
	private int idLugarAtencion;
	private int idEspecialidad;
	private String motivo;
	private String presentacionCaso;
	private String resultadosLaboratorio;
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public int getIdCatSoliReferenciaMotivo() {
		return idCatSoliReferenciaMotivo;
	}
	public void setIdCatSoliReferenciaMotivo(int idCatSoliReferenciaMotivo) {
		this.idCatSoliReferenciaMotivo = idCatSoliReferenciaMotivo;
	}
	public int getIdCatSoliRefrerenciaPor() {
		return idCatSoliRefrerenciaPor;
	}
	public void setIdCatSoliRefrerenciaPor(int idCatSoliRefrerenciaPor) {
		this.idCatSoliRefrerenciaPor = idCatSoliRefrerenciaPor;
	}
	public int getIdLugarAtencion() {
		return idLugarAtencion;
	}
	public void setIdLugarAtencion(int idLugarAtencion) {
		this.idLugarAtencion = idLugarAtencion;
	}
	public int getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getPresentacionCaso() {
		return presentacionCaso;
	}
	public void setPresentacionCaso(String presentacionCaso) {
		this.presentacionCaso = presentacionCaso;
	}
	public String getResultadosLaboratorio() {
		return resultadosLaboratorio;
	}
	public void setResultadosLaboratorio(String resultadosLaboratorio) {
		this.resultadosLaboratorio = resultadosLaboratorio;
	}
	
	
	
}
