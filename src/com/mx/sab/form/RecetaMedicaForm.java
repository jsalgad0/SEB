package com.mx.sab.form;

import java.io.Serializable;

import com.mx.sab.model.Medicamentosreceta;

import lombok.Data;
import mx.gob.issste.consultamedicamentos.scm.services.ConsultaMedicamentosServiceStub.Medicamento;

@Data
public class RecetaMedicaForm extends ParentForm implements Serializable{

	private static final long serialVersionUID = 6856321060342670405L;
	private int idAgenda;
	private Medicamentosreceta medicamentosreceta;
	private int idMedicamento;
	private String medicamento;
	private int IdViaDeAdmonMedicamento;
	private int idUnidadTiempoFrecuencia;
	private int idUnidadTiempoDuracion;
	private boolean ventanaMedicamentos;
	
	//ISSSTE
	private Medicamento medicamentoWs;
	private int stock;
	private int idReceta;
	
	private boolean finalizoAtencionMedica;

	public int getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}

	public Medicamentosreceta getMedicamentosreceta() {
		return medicamentosreceta;
	}

	public void setMedicamentosreceta(Medicamentosreceta medicamentosreceta) {
		this.medicamentosreceta = medicamentosreceta;
	}

	public int getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public int getIdViaDeAdmonMedicamento() {
		return IdViaDeAdmonMedicamento;
	}

	public void setIdViaDeAdmonMedicamento(int idViaDeAdmonMedicamento) {
		IdViaDeAdmonMedicamento = idViaDeAdmonMedicamento;
	}

	public int getIdUnidadTiempoFrecuencia() {
		return idUnidadTiempoFrecuencia;
	}

	public void setIdUnidadTiempoFrecuencia(int idUnidadTiempoFrecuencia) {
		this.idUnidadTiempoFrecuencia = idUnidadTiempoFrecuencia;
	}

	public int getIdUnidadTiempoDuracion() {
		return idUnidadTiempoDuracion;
	}

	public void setIdUnidadTiempoDuracion(int idUnidadTiempoDuracion) {
		this.idUnidadTiempoDuracion = idUnidadTiempoDuracion;
	}

	public boolean isVentanaMedicamentos() {
		return ventanaMedicamentos;
	}

	public void setVentanaMedicamentos(boolean ventanaMedicamentos) {
		this.ventanaMedicamentos = ventanaMedicamentos;
	}

	public Medicamento getMedicamentoWs() {
		return medicamentoWs;
	}

	public void setMedicamentoWs(Medicamento medicamentoWs) {
		this.medicamentoWs = medicamentoWs;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(int idReceta) {
		this.idReceta = idReceta;
	}

	public boolean isFinalizoAtencionMedica() {
		return finalizoAtencionMedica;
	}

	public void setFinalizoAtencionMedica(boolean finalizoAtencionMedica) {
		this.finalizoAtencionMedica = finalizoAtencionMedica;
	}
	
	
	
}
