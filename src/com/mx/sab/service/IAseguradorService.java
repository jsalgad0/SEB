package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.AseguradorForm;
import com.mx.sab.model.Aseguradores;
import com.mx.sab.vo.AseguradoresVo;

public interface IAseguradorService {

	void save(AseguradorForm altaAseguradorForm);

	Collection<Aseguradores> getAseguradores();

	Collection<Aseguradores> getAseguradoresSinPrestaciones();
	
	void getAsegurador(AseguradorForm aseguradorForm);
	
	void update(AseguradorForm aseguradorForm);
	
	public void delete(AseguradorForm aseguradorForm);
	
	public Collection<Aseguradores> getAseguradores(AseguradorForm aseguradorForm);

	Collection<AseguradoresVo> getAseguradoresByDescripcion(String busqueda);
	
}
