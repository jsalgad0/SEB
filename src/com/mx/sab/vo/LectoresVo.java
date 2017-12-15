package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class LectoresVo implements Serializable{

	private static final long serialVersionUID = -2371067978267653138L;
	private Integer lectorId;
	private String noDeSerie;
	private LugarAtencionVo lugarAtencion;
	private PropietarioLectorVo propietarioLector;
	public Integer getLectorId() {
		return lectorId;
	}
	public void setLectorId(Integer lectorId) {
		this.lectorId = lectorId;
	}
	public String getNoDeSerie() {
		return noDeSerie;
	}
	public void setNoDeSerie(String noDeSerie) {
		this.noDeSerie = noDeSerie;
	}
	public LugarAtencionVo getLugarAtencion() {
		return lugarAtencion;
	}
	public void setLugarAtencion(LugarAtencionVo lugarAtencion) {
		this.lugarAtencion = lugarAtencion;
	}
	public PropietarioLectorVo getPropietarioLector() {
		return propietarioLector;
	}
	public void setPropietarioLector(PropietarioLectorVo propietarioLector) {
		this.propietarioLector = propietarioLector;
	}
	
	
}
