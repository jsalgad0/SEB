package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.Catcolonias;
import com.mx.sab.model.Catescolaridad;
import com.mx.sab.model.Catestadocivil;
import com.mx.sab.model.Catestados;
import com.mx.sab.model.Catmunicipios;
import com.mx.sab.model.Catocupacion;
import com.mx.sab.model.Catsexos;
import com.mx.sab.model.Cattipoafiliado;
import com.mx.sab.model.Cattipoidentificador;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Roles;

public interface IGenericDao {

	Collection<Roles> getRoles(int i);
	Collection<Lugaresdeatencion> getLugarAtencion();
	Collection<Catestados> getEstados();	
	Collection<Catmunicipios> getCatMunicipios(int i);
	Collection<Catcolonias> getCatColonias(int parseInt);
	Catcolonias getColoniaById(int i);
	Catestados getEstadoById(int i);
	Catmunicipios getMunicipioById(int i);
	Collection<Cattipoidentificador> getTipoIdentificador();
	Collection<Cattipoidentificador> getTipoIdentificadorPersonaConfianza();
	Cattipoidentificador getCatTipoIdentificadorById(int idTipoIdentificador);
	Afiliadotipoidentificador getAfiliadoTipoIdentificador(Integer afiliadoId, int id);
	Collection<Cattipoidentificador> getTipoIdentificadorByAsegurador(int idAsegurador);
	Catsexos getCatSexos(int id);
	Cattipoafiliado getCatTipoAfiliadoByTipo(String parentesco, int idAsegurador);
	void saveAfiliadotipoIdentificar(Afiliadotipoidentificador afiliadotipoidentificador);
	Collection<Catestadocivil> getEstadoCivil();
	Collection<Catescolaridad> getEscolaridad();
	Catescolaridad getEscolaridadById(int id);
	Catestadocivil getEstadoCivilById(int id);
	Cattipoafiliado getCatTipoAfiliadoByClave(int derechohabiente,int idAsegurador);
	Collection<Catocupacion> getOcupacion();
}
