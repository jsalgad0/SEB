package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;

import com.mx.sab.model.HcAntecedentesfamiliares;
import com.mx.sab.model.HcAntecedentespersonal;
import com.mx.sab.model.HcExploracionfisica;
import com.mx.sab.model.HcFasedelciclo;
import com.mx.sab.model.HcGineco;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.vo.TomaSignosVo;

@Data
public class AntecedentesFamiliaresForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = -816066458751698712L;
	private int afiliadoId;
	private boolean historiaMedicaId;
	private boolean diabeteMellitus;
	private boolean hipertensionArterial;
	private boolean neoplasias;
	private boolean cardiopatias;
	private boolean alergias;
	private boolean obesidad;
	private boolean tuberculosis;
	private boolean tabaquismo;
	private boolean alcoholismo;
	private boolean dependenciaADroga;
	private boolean dependenciaAMedicamentos;
	private boolean malformaciones;
	private boolean disfuncionesFamiliares;
	private boolean quirurgias;
	private String otras;
	private TomaSignosVo tomaSignosVo;
	private int idHistoriaClinica;
	private int idAntecedentesFamiliares;
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
	public boolean isDiabeteMellitus() {
		return diabeteMellitus;
	}
	public void setDiabeteMellitus(boolean diabeteMellitus) {
		this.diabeteMellitus = diabeteMellitus;
	}
	public boolean isHipertensionArterial() {
		return hipertensionArterial;
	}
	public void setHipertensionArterial(boolean hipertensionArterial) {
		this.hipertensionArterial = hipertensionArterial;
	}
	public boolean isNeoplasias() {
		return neoplasias;
	}
	public void setNeoplasias(boolean neoplasias) {
		this.neoplasias = neoplasias;
	}
	public boolean isCardiopatias() {
		return cardiopatias;
	}
	public void setCardiopatias(boolean cardiopatias) {
		this.cardiopatias = cardiopatias;
	}
	public boolean isAlergias() {
		return alergias;
	}
	public void setAlergias(boolean alergias) {
		this.alergias = alergias;
	}
	public boolean isObesidad() {
		return obesidad;
	}
	public void setObesidad(boolean obesidad) {
		this.obesidad = obesidad;
	}
	public boolean isTuberculosis() {
		return tuberculosis;
	}
	public void setTuberculosis(boolean tuberculosis) {
		this.tuberculosis = tuberculosis;
	}
	public boolean isTabaquismo() {
		return tabaquismo;
	}
	public void setTabaquismo(boolean tabaquismo) {
		this.tabaquismo = tabaquismo;
	}
	public boolean isAlcoholismo() {
		return alcoholismo;
	}
	public void setAlcoholismo(boolean alcoholismo) {
		this.alcoholismo = alcoholismo;
	}
	public boolean isDependenciaADroga() {
		return dependenciaADroga;
	}
	public void setDependenciaADroga(boolean dependenciaADroga) {
		this.dependenciaADroga = dependenciaADroga;
	}
	public boolean isDependenciaAMedicamentos() {
		return dependenciaAMedicamentos;
	}
	public void setDependenciaAMedicamentos(boolean dependenciaAMedicamentos) {
		this.dependenciaAMedicamentos = dependenciaAMedicamentos;
	}
	public boolean isMalformaciones() {
		return malformaciones;
	}
	public void setMalformaciones(boolean malformaciones) {
		this.malformaciones = malformaciones;
	}
	public boolean isDisfuncionesFamiliares() {
		return disfuncionesFamiliares;
	}
	public void setDisfuncionesFamiliares(boolean disfuncionesFamiliares) {
		this.disfuncionesFamiliares = disfuncionesFamiliares;
	}
	public boolean isQuirurgias() {
		return quirurgias;
	}
	public void setQuirurgias(boolean quirurgias) {
		this.quirurgias = quirurgias;
	}
	public String getOtras() {
		return otras;
	}
	public void setOtras(String otras) {
		this.otras = otras;
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
	public int getIdAntecedentesFamiliares() {
		return idAntecedentesFamiliares;
	}
	public void setIdAntecedentesFamiliares(int idAntecedentesFamiliares) {
		this.idAntecedentesFamiliares = idAntecedentesFamiliares;
	}
	
	
}
