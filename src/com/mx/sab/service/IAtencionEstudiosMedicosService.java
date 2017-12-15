package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.AtencionEstudiosMedicosForm;
import com.mx.sab.vo.AutocompleteVo;

public interface IAtencionEstudiosMedicosService {

	void agregaEstudioMedico(AtencionEstudiosMedicosForm atencionEstudiosMedicosForm);

	void inicializaEstudiosMedicosForm(AtencionEstudiosMedicosForm atencionEstudiosMedicosForm);
	
	Collection<AutocompleteVo> getEstudios(int idAtencion, int idEstudios);

	Collection<AutocompleteVo> getEstudiosAsegurador(int idAtencion, int id);

}
