package com.mx.sab.form;

import java.io.Serializable;

import lombok.Data;



import com.mx.sab.vo.TomaSignosVo;

@Data
public class GinecoObstentricosForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = -816066458751698712L;
	private int afiliadoId;
	private boolean historiaMedicaId;
	private int idMpf;
	private String menarca;
	private String ritmo;
	private String vsa;
	private String fur;
	private String g;
	private String p;
	private String a;
	private String c;
	private TomaSignosVo tomaSignosVo;
	private int idHistoriaClinica;
	private int idGinecoObstentricos;
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
	public int getIdMpf() {
		return idMpf;
	}
	public void setIdMpf(int idMpf) {
		this.idMpf = idMpf;
	}
	public String getMenarca() {
		return menarca;
	}
	public void setMenarca(String menarca) {
		this.menarca = menarca;
	}
	public String getRitmo() {
		return ritmo;
	}
	public void setRitmo(String ritmo) {
		this.ritmo = ritmo;
	}
	public String getVsa() {
		return vsa;
	}
	public void setVsa(String vsa) {
		this.vsa = vsa;
	}
	public String getFur() {
		return fur;
	}
	public void setFur(String fur) {
		this.fur = fur;
	}
	public String getG() {
		return g;
	}
	public void setG(String g) {
		this.g = g;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
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
	public int getIdGinecoObstentricos() {
		return idGinecoObstentricos;
	}
	public void setIdGinecoObstentricos(int idGinecoObstentricos) {
		this.idGinecoObstentricos = idGinecoObstentricos;
	}
	
	
	
}
