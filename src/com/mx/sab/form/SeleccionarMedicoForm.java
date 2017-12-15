package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;

import com.mx.sab.model.Usuarios;
import com.mx.sab.vo.MedicosHorariosVo;

import lombok.Data;

@Data
public class SeleccionarMedicoForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = 3561209553491958387L;
	private Collection<Usuarios> usuarios;
	private Collection<String> tiempos;
	private String idTiempo;
	private Collection<MedicosHorariosVo> medicosHorariosVos;
	public Collection<Usuarios> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Collection<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}
	public Collection<String> getTiempos() {
		return tiempos;
	}
	public void setTiempos(Collection<String> tiempos) {
		this.tiempos = tiempos;
	}
	public String getIdTiempo() {
		return idTiempo;
	}
	public void setIdTiempo(String idTiempo) {
		this.idTiempo = idTiempo;
	}
	public Collection<MedicosHorariosVo> getMedicosHorariosVos() {
		return medicosHorariosVos;
	}
	public void setMedicosHorariosVos(Collection<MedicosHorariosVo> medicosHorariosVos) {
		this.medicosHorariosVos = medicosHorariosVos;
	}
	
	
}
