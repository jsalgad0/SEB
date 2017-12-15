package com.mx.sab.dao;

import java.util.Collection;
import java.util.Date;

import com.mx.sab.model.Catestatusfirma;
import com.mx.sab.model.Notamedica;



public interface INotasNoFirmadasDao {

	Collection<Notamedica> getAtenciones(Date fecha, int idLugarAtencion);
	int getFirmaPaciente(int idAsegurador);
	Catestatusfirma getEstatusFirma(int idEstatusFirma);
	
}
