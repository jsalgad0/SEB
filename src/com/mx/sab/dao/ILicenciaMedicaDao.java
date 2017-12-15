package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Agenda;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catlicmedicacaracteres;
import com.mx.sab.model.Catlicmedicamotivos;
import com.mx.sab.model.Catlicmedicatiposservicio;
import com.mx.sab.model.Licenciamedica;

public interface ILicenciaMedicaDao {

	Collection<Catlicmedicamotivos> getCatLicMedicaMotivos();

	Collection<Catlicmedicacaracteres> getCatLicMedicaCaracteres();

	Collection<Catlicmedicatiposservicio> getCatLicMedicaTiposServicio();

	Catlicmedicamotivos getCatLicMedicaMotivosById(int idLicMedicaMotivo);

	Catlicmedicacaracteres getCatLicMedicaCaracteresById(int idLicMedicaCaracter);

	Catlicmedicatiposservicio getCatLicMedicaTiposServicioById(
			int idLicMedicaTipoServicio);

	void save(Licenciamedica licenciamedica);

	Collection<Agenda> getAtencionesMedicas(Integer afiliadoId);

	Collection<Licenciamedica> getLicenciasMedicas(Collection<Integer> atencionMedicaIds);

	Licenciamedica getLicenciaMedicaFolio();

	Licenciamedica getLicenciaMedicaByIdAgenda(int idAgenda);

	Licenciamedica getLicenciaMedicaByIdAtencionMedica(Integer atencionMedicaId);

	void update(Licenciamedica licenciamedica);

}
