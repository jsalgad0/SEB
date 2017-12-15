package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mx.sab.model.Aseguradores;
import com.mx.sab.model.Prestacionasegurador;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.model.Prestacionesprestadorequivalencias;
import com.mx.sab.model.Prestacionprestador;
import com.mx.sab.model.Prestadores;
import com.mx.sab.vo.EquivalenciasSabVo;

import lombok.Data;

@Data
public class PrestacionesForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = 7746185175185988004L;
	private Collection<Prestacionasegurador> prestacionaseguradors;
	private Collection<Prestacionesaseguradorequivalencias> prestacionesaseguradorequivalencias;
	private Collection<Prestacionesprestadorequivalencias> prestacionesprestadorequivalencias;
	private Collection<Prestacionprestador> prestacionprestadors;
	private Collection<EquivalenciasSabVo> equivalenciasSabVos;
	private Integer idPrestadores;
	private Prestadores prestadores;
	private Integer idAseguradores;
	private Aseguradores aseguradores;
	private String cargaPrestaciones = "A";
	private CommonsMultipartFile file;
	private String error;
	public Collection<Prestacionasegurador> getPrestacionaseguradors() {
		return prestacionaseguradors;
	}
	public void setPrestacionaseguradors(Collection<Prestacionasegurador> prestacionaseguradors) {
		this.prestacionaseguradors = prestacionaseguradors;
	}
	public Collection<Prestacionesaseguradorequivalencias> getPrestacionesaseguradorequivalencias() {
		return prestacionesaseguradorequivalencias;
	}
	public void setPrestacionesaseguradorequivalencias(
			Collection<Prestacionesaseguradorequivalencias> prestacionesaseguradorequivalencias) {
		this.prestacionesaseguradorequivalencias = prestacionesaseguradorequivalencias;
	}
	public Collection<Prestacionesprestadorequivalencias> getPrestacionesprestadorequivalencias() {
		return prestacionesprestadorequivalencias;
	}
	public void setPrestacionesprestadorequivalencias(
			Collection<Prestacionesprestadorequivalencias> prestacionesprestadorequivalencias) {
		this.prestacionesprestadorequivalencias = prestacionesprestadorequivalencias;
	}
	public Collection<Prestacionprestador> getPrestacionprestadors() {
		return prestacionprestadors;
	}
	public void setPrestacionprestadors(Collection<Prestacionprestador> prestacionprestadors) {
		this.prestacionprestadors = prestacionprestadors;
	}
	public Collection<EquivalenciasSabVo> getEquivalenciasSabVos() {
		return equivalenciasSabVos;
	}
	public void setEquivalenciasSabVos(Collection<EquivalenciasSabVo> equivalenciasSabVos) {
		this.equivalenciasSabVos = equivalenciasSabVos;
	}
	public Integer getIdPrestadores() {
		return idPrestadores;
	}
	public void setIdPrestadores(Integer idPrestadores) {
		this.idPrestadores = idPrestadores;
	}
	public Prestadores getPrestadores() {
		return prestadores;
	}
	public void setPrestadores(Prestadores prestadores) {
		this.prestadores = prestadores;
	}
	public Integer getIdAseguradores() {
		return idAseguradores;
	}
	public void setIdAseguradores(Integer idAseguradores) {
		this.idAseguradores = idAseguradores;
	}
	public Aseguradores getAseguradores() {
		return aseguradores;
	}
	public void setAseguradores(Aseguradores aseguradores) {
		this.aseguradores = aseguradores;
	}
	public String getCargaPrestaciones() {
		return cargaPrestaciones;
	}
	public void setCargaPrestaciones(String cargaPrestaciones) {
		this.cargaPrestaciones = cargaPrestaciones;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	
	
	
}
