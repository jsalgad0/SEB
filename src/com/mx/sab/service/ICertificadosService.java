package com.mx.sab.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.mx.sab.form.CertificadosForm;
import com.mx.sab.form.ConstanciaAsistenciaForm;
import com.mx.sab.form.ConstanciaCuidadosMaternalesForm;
import com.mx.sab.form.ConstanciaSaludForm;

public interface ICertificadosService {

	void inicializaFrom(CertificadosForm certificadosForm);

	void constanciaDeAsistencia(ConstanciaAsistenciaForm constanciaAsistenciaForm);

	void inicializaConstanciaDeAsistencia(ConstanciaAsistenciaForm constanciaAsistenciaForm);

	void inicializaConstanciaDeSalud(ConstanciaSaludForm constanciaSaludForm);

	void constanciaDeSalud(ConstanciaSaludForm constanciaSaludForm);

	void constanciaCuidadosMaternales(
			ConstanciaCuidadosMaternalesForm constanciaCuidadosMaternalesForm);

	void recetaMedica(CertificadosForm certificadosForm);
	
	ByteArrayOutputStream recetaMedica(int idAgenda);

	void licenciaMedica(CertificadosForm certificadosForm);
	
	ByteArrayOutputStream licenciaMedica(int idAgenda);

	void estudiosLaboratorio(CertificadosForm certificadosForm);
	
	ByteArrayOutputStream estudiosLaboratorio(int idAgenda);

	void estudiosGabinete(CertificadosForm certificadosForm);
	
	ByteArrayOutputStream estudiosGabinete(int idAgenda);

	void estudiosOtros(CertificadosForm certificadosForm);
	
	ByteArrayOutputStream estudiosOtros(int idAgenda);

	void solicitudReferencia(CertificadosForm certificadosForm);
	
	ByteArrayOutputStream solicitudReferencia(int idAgenda);

	void solicitudContrareferencia(CertificadosForm certificadosForm);

	ByteArrayOutputStream solicitudContrareferencia(int idAgenda);

	void mediaccessOrdenGabinete(CertificadosForm certificadosForm);

	void mediaccessOrdenLaboratorio(CertificadosForm certificadosForm);

	void mediaccessAtencionConsultorio(CertificadosForm certificadosForm);

	void mediaccessRecetaMedica(CertificadosForm certificadosForm);

	void mediaccessOtrosApoyos(CertificadosForm certificadosForm);

	void mediaccessSolicitudReferencia(CertificadosForm certificadosForm);
	
	void doMerge(List<ByteArrayOutputStream> list, CertificadosForm certificadosForm);
}
