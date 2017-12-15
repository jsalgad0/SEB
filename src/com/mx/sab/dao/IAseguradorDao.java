package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.form.AseguradorForm;
import com.mx.sab.model.Aseguradores;
import com.mx.sab.model.Prestacionasegurador;


public interface IAseguradorDao {

	void save(Aseguradores aseguradores);

	Aseguradores getAseguradorById(Integer aseguradorId);

	Collection<Aseguradores> getAseguradores();

	Collection<Aseguradores> getAseguradoresSinPrestaciones();
	
	Collection<Aseguradores> getAseguradoresByDesc(String busqueda);
	
	Aseguradores getAseguradorByRfc(String rfc);
	
	void update(Aseguradores aseguradores);
	
	int getAseguradoresCount(String string);
	
	Collection<Aseguradores> getAseguradores(String busqueda, int inicio, int fin);

	Aseguradores existeDireccionAsegurador(AseguradorForm aseguradorForm);

	Aseguradores getAseguradoresByNombre(String asegurador);

	void delete(Aseguradores aseguradores);
	
}
