package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;



import com.mx.sab.vo.TomaSignosVo;

@Data
public class CicloVitalForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = -816066458751698712L;
	private int afiliadoId;
	private boolean historiaMedicaId;
	private boolean matrimonio;
	private boolean expansion;
	private boolean dispersion;
	private boolean independencia;
	private boolean retiroymuerte;
	private int idTipoFamilia;
	private TomaSignosVo tomaSignosVo;
	private int idHistoriaClinica;
	private int idCicloVital;
	public int getAfiliadoId() {
		return afiliadoId;
	}
	public void setAfiliadoId(int afiliadoId) {
		this.afiliadoId = afiliadoId;
	}
	public boolean isHistoriaMedicaId() {
		return historiaMedicaId;
	}
	public void setHistoriaMedicaId(boolean historiaMedicaId) {
		this.historiaMedicaId = historiaMedicaId;
	}
	public boolean isMatrimonio() {
		return matrimonio;
	}
	public void setMatrimonio(boolean matrimonio) {
		this.matrimonio = matrimonio;
	}
	public boolean isExpansion() {
		return expansion;
	}
	public void setExpansion(boolean expansion) {
		this.expansion = expansion;
	}
	public boolean isDispersion() {
		return dispersion;
	}
	public void setDispersion(boolean dispersion) {
		this.dispersion = dispersion;
	}
	public boolean isIndependencia() {
		return independencia;
	}
	public void setIndependencia(boolean independencia) {
		this.independencia = independencia;
	}
	public boolean isRetiroymuerte() {
		return retiroymuerte;
	}
	public void setRetiroymuerte(boolean retiroymuerte) {
		this.retiroymuerte = retiroymuerte;
	}
	public int getIdTipoFamilia() {
		return idTipoFamilia;
	}
	public void setIdTipoFamilia(int idTipoFamilia) {
		this.idTipoFamilia = idTipoFamilia;
	}
	public TomaSignosVo getTomaSignosVo() {
		return tomaSignosVo;
	}
	public void setTomaSignosVo(TomaSignosVo tomaSignosVo) {
		this.tomaSignosVo = tomaSignosVo;
	}
	public int getIdHistoriaClinica() {
		return idHistoriaClinica;
	}
	public void setIdHistoriaClinica(int idHistoriaClinica) {
		this.idHistoriaClinica = idHistoriaClinica;
	}
	public int getIdCicloVital() {
		return idCicloVital;
	}
	public void setIdCicloVital(int idCicloVital) {
		this.idCicloVital = idCicloVital;
	}
	
	
	
}
