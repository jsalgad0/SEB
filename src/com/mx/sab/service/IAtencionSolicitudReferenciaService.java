package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.SolicitudReferenciaForm;
import com.mx.sab.model.Catsolireferenciamotivos;
import com.mx.sab.model.Catsolirefrerenciapor;

public interface IAtencionSolicitudReferenciaService {

	Collection<Catsolireferenciamotivos> getCatSoliReferenciaMotivos();

	Collection<Catsolirefrerenciapor> getCatSoliRefrerenciaPor();

	void inicializaSolicitudReferenciaForm(
			SolicitudReferenciaForm solicitudReferenciaForm);

	void save(SolicitudReferenciaForm solicitudReferenciaForm);

}
