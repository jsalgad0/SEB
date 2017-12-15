package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catsignosvitalesadicionales;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.model.Signosvitalesadicionales;



public interface ISignosDao {

	Collection<Atencionmedica> getAtenciones(String fecha, int idLugarAtencion, String busquedaApellido, int inicio, int fin);
	Collection<Atencionmedica> getAtencionesAtendidos(String fecha, int idLugarAtencion, String busquedaApellido, int inicio, int fin);
	Collection<Atencionmedica> getAtencionesPorAtender(String fecha, int idLugarAtencion, String busquedaApellido, int inicio, int fin);
	Atencionmedica getAtencionMedicaById(int idAtencion);
	void save(Signosvitales signosvitales);
	long getFolio() throws NullPointerException;
	Signosvitales getAtencionAnterior(int idAfiliado);
	Signosvitales getSignosByAtencion(int idAtencion);
	int getAtencionesCount(String fecha, int idLugarAtencion, String busquedaApellido);
	int getAtencionesAtendidosCount(String fecha, int idLugarAtencion, String busquedaApellido);
	int getAtencionesPorAtenderCount(String fecha, int idLugarAtencion, String busquedaApellido);
	void update(Signosvitales signosvitales);
	Object getUltimosSignos(int idAfiliado);
	Signosvitales getSignosById(int idSignos);
	Collection<Signosvitalesadicionales> getSignosVitalesAdicionales(Integer signosVitalesId);
	Collection<Catsignosvitalesadicionales> getCatSignosVitalesAdicionalesByIdAsegurador(Integer idAsegurador);
	void updateSignosVitalesAdicionales(Signosvitalesadicionales signosvitalesadicional);
	void saveSignosVitalesAdicionales(Signosvitalesadicionales signosvitalesadicional);
	void deleteSignosVitalesAdicionales(Signosvitalesadicionales signosvitalesadicional);
}
