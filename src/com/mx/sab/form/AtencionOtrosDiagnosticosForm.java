package com.mx.sab.form;

import java.io.Serializable;
import java.util.List;

import com.mx.sab.vo.DiagnosticoVo;

import lombok.Data;

@Data
public class AtencionOtrosDiagnosticosForm extends ParentForm implements Serializable {

	private static final long serialVersionUID = 4174396376866043613L;
	private int idAtencion;
	private List<DiagnosticoVo> diagnosticoVos;
	private int idTipoDiagnostico;
	private int idDiagnosticoPrincipal;
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public List<DiagnosticoVo> getDiagnosticoVos() {
		return diagnosticoVos;
	}
	public void setDiagnosticoVos(List<DiagnosticoVo> diagnosticoVos) {
		this.diagnosticoVos = diagnosticoVos;
	}
	public int getIdTipoDiagnostico() {
		return idTipoDiagnostico;
	}
	public void setIdTipoDiagnostico(int idTipoDiagnostico) {
		this.idTipoDiagnostico = idTipoDiagnostico;
	}
	public int getIdDiagnosticoPrincipal() {
		return idDiagnosticoPrincipal;
	}
	public void setIdDiagnosticoPrincipal(int idDiagnosticoPrincipal) {
		this.idDiagnosticoPrincipal = idDiagnosticoPrincipal;
	}
	
	

}
