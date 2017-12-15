package com.mx.sab.form;

import java.io.Serializable;
import java.util.List;

import com.mx.sab.vo.HistorialPorDiagnosticoVo;

import lombok.Data;

@Data
public class HistorialPorDiagnosticoForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = 5263326525693160597L;
	private int idAfiliado;
	private int idAtencion;
	private int idDiagnostico;
	private String diagnostico;
	private List<HistorialPorDiagnosticoVo> historialPorDiagnosticoVos;
	public int getIdAfiliado() {
		return idAfiliado;
	}
	public void setIdAfiliado(int idAfiliado) {
		this.idAfiliado = idAfiliado;
	}
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public int getIdDiagnostico() {
		return idDiagnostico;
	}
	public void setIdDiagnostico(int idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public List<HistorialPorDiagnosticoVo> getHistorialPorDiagnosticoVos() {
		return historialPorDiagnosticoVos;
	}
	public void setHistorialPorDiagnosticoVos(List<HistorialPorDiagnosticoVo> historialPorDiagnosticoVos) {
		this.historialPorDiagnosticoVos = historialPorDiagnosticoVos;
	}

	
	
}
