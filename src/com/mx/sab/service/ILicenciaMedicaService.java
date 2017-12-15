package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.LicenciaMedicaForm;
import com.mx.sab.model.Catlicmedicacaracteres;
import com.mx.sab.model.Catlicmedicamotivos;
import com.mx.sab.model.Catlicmedicatiposservicio;
import com.mx.sab.vo.FechaLicenciaMedica;

public interface ILicenciaMedicaService {

	Collection<Catlicmedicamotivos> getCatLicMedicaMotivos();

	Collection<Catlicmedicacaracteres> getCatLicMedicaCaracteres();

	Collection<Catlicmedicatiposservicio> getCatLicMedicaTiposServicio();

	void inicializaForm(LicenciaMedicaForm licenciaMedicaForm);

	FechaLicenciaMedica verificaFechas(String vigenteDesde, String vigenteHasta);

	void save(LicenciaMedicaForm licenciaMedicaForm);

	void getLicenciasMedicas(LicenciaMedicaForm licenciaMedicaForm);

}
