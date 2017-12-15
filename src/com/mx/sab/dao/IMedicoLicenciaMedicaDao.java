package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.form.MedicoLicenciaMedicaForm;
import com.mx.sab.model.Catlicmedicacaracteres;
import com.mx.sab.model.Catlicmedicamotivos;
import com.mx.sab.model.Catlicmedicatiposservicio;
import com.mx.sab.model.Licenciamedica;
import com.mx.sab.model.NotamedicaCies10;

public interface IMedicoLicenciaMedicaDao {

	Collection<Catlicmedicacaracteres> getLicMedicaCaracteres();
	Catlicmedicacaracteres getLicMedicaCaracteresById(int idLicMedicaCaracter);
	Collection<Catlicmedicamotivos> getLicMedicaMotivos();
	Catlicmedicamotivos getLicMedicaMotivosById(int idLicMedicaMotivo);
	Collection<Catlicmedicatiposservicio> getLicMedicaTiposServicio();
	Catlicmedicatiposservicio getLicMedicaTiposServicioById(int idLicMedicaTipoServicio);
	Licenciamedica getLicenciaMedica(Integer atencionMedicaId);
	void updateLicenciaMedica(Licenciamedica licenciamedica);
	void saveLicenciaMedica(Licenciamedica licenciamedica);
}
