package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Afiliadoadicionales;
import com.mx.sab.model.Afiliadodemografico;
import com.mx.sab.model.Catcolonias;
import com.mx.sab.model.Catestados;
import com.mx.sab.model.Catgrupossanguineos;
import com.mx.sab.model.Catmunicipios;
import com.mx.sab.model.Catocupacion;
import com.mx.sab.model.Catsexos;


public interface IInformacionPacienteDao {

	Collection<Catocupacion> getOcupacion();
	Collection<Catgrupossanguineos> getGrupoSanguineo();
	void updateAfiliado(Afiliado afiliado);
	void saveDemografico(Afiliadodemografico afiliadodemografico);
	void saveAdicional(Afiliadoadicionales afiliadoadicionales);
	void updateDemografico(Afiliadodemografico afiliadodemografico);
	void updateAdicional(Afiliadoadicionales afiliadoadicionales);
	Catgrupossanguineos getGrupoSanguineoById(int id);
	Catocupacion getOcupacionById(int id);
	Catsexos getSexo(int id);
	Catestados getEstadosById(int id);
	Catmunicipios getMunicipiosById(int id);
	Catcolonias getColoniasById(int id);
	Afiliadodemografico getDemografico(Afiliado afiliado);
	Afiliadoadicionales getAdicionales(Afiliado afiliado);
	Afiliado getAfiliadoById(int idAfiliado);
	
}
