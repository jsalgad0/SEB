package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.model.Catcolonias;
import com.mx.sab.model.Catescolaridad;
import com.mx.sab.model.Catestadocivil;
import com.mx.sab.model.Catestados;
import com.mx.sab.model.Catmunicipios;
import com.mx.sab.model.Catocupacion;
import com.mx.sab.model.Cattipoidentificador;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.vo.CatColoniasVo;
import com.mx.sab.vo.CatMunicipiosVo;
import com.mx.sab.vo.CatTipoIdentificadorVo;
import com.mx.sab.vo.RolesVo;

public interface IGenericService {

	Collection<Catestados> getCatEstados();

	Collection<CatMunicipiosVo> getCatMunicipios(int id);
	
	Collection<RolesVo> getRoles(String id);

	Collection<CatColoniasVo> getCatColonias(int id);

	Catcolonias getColoniaById(int i);

	Catestados getEstadoById(int i);

	Catmunicipios getMunicipioById(int i);
	
	Collection<Lugaresdeatencion> getLugarAtencion();
	
	Collection<Cattipoidentificador> getTipoIdentificador();
	
	String getRfc(String nombre, String apellidoP, String apellidoM, String fecha);
	
	Collection<Cattipoidentificador> getTipoIdentificadorPersonaConfianza();

	Collection<CatTipoIdentificadorVo> getTipoIdentificadorByAsegurador(int idAsegurador);
	
	Collection<Catestadocivil> getEstadoCivil();
	
	Collection<Catescolaridad> getEscolaridad();
	
	Collection<Catocupacion> getOcupacion();

}
