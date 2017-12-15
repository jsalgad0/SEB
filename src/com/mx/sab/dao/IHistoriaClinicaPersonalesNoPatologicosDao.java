package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Afiliadodemografico;
import com.mx.sab.model.Catalimentacion;
import com.mx.sab.model.Catescolaridad;
import com.mx.sab.model.Catestadocivil;
import com.mx.sab.model.Catgrupossanguineos;
import com.mx.sab.model.Cathigiene;
import com.mx.sab.model.HcPersonalnopatologicos;
import com.mx.sab.model.Historiaclinica;

public interface IHistoriaClinicaPersonalesNoPatologicosDao {

	Collection<Catgrupossanguineos> getTipoSangre();

	Collection<Catescolaridad> getEscolaridades();

	Collection<Catalimentacion> getAlimentaciones();

	Collection<Cathigiene> getHigienePersonales();

	Collection<Catestadocivil> getEstadosCiviles();

	Historiaclinica getHistoriaClinicaByIdAfiliado(int idAfiliado);

	Afiliadodemografico getAfiliadoDemograficoByIdAfiliado(int i);

	Catalimentacion getCatAlimentacionById(int idAlimentacion);

	Catescolaridad getCatEscolaridadById(int idEscolaridad);

	Cathigiene getCatHigieneById(int idHigienePersonal);

	Catestadocivil getCatEstadoCivilById(int idEstadoCivil);

	void updateHcPersonalnopatologicos(
			HcPersonalnopatologicos hcPersonalnopatologicos);

	void saveHistoriaClinica(Historiaclinica historiaclinica);

	void saveHcPersonalesNoPatologicos(
			HcPersonalnopatologicos hcPersonalnopatologicos);

	Catgrupossanguineos getCatGrupoSanguineo(int idTipoSangre);

	void updateAfiliadoDemografico(Afiliadodemografico afiliadodemografico);

	void saveAfiliadoDemografico(Afiliadodemografico afiliadodemografico);

	HcPersonalnopatologicos getHcPersonalNoPatologicosById(
			int idPersonalNoPatologicos);

}
