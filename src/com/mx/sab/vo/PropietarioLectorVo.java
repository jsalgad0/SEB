package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class PropietarioLectorVo implements Serializable{

	private static final long serialVersionUID = 608600367159186279L;
	private Integer idPropietarioLector;
	private String propietarioLector;
	private String rfc;
	public Integer getIdPropietarioLector() {
		return idPropietarioLector;
	}
	public void setIdPropietarioLector(Integer idPropietarioLector) {
		this.idPropietarioLector = idPropietarioLector;
	}
	public String getPropietarioLector() {
		return propietarioLector;
	}
	public void setPropietarioLector(String propietarioLector) {
		this.propietarioLector = propietarioLector;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	
}
