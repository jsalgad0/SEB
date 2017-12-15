package com.mx.sab.service;

import java.util.Collection;



import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.form.MedicoLicenciaMedicaForm;
import com.mx.sab.vo.CatLicMedicaCaracteresVo;
import com.mx.sab.vo.CatLicMedicaMotivosVo;
import com.mx.sab.vo.CatLicMedicaTipoServicioVo;
import com.mx.sab.vo.LicenciaMedicaVo;
import com.mx.sab.vo.UserInfo;

public interface IMedicoLicenciaMedicaService {
	
	void guardarLicenciaMedica(MedicoLicenciaMedicaForm medicoLicenciaMedicaForm, UserInfo userInfo);
	MedicoLicenciaMedicaForm getLicenciaMedica(MedicoAtencionPacientesForm medicoAtencionPacientesForm, UserInfo userInfo);
	Collection<CatLicMedicaCaracteresVo> getCatLicMedicaCaracteres();
	Collection<CatLicMedicaMotivosVo> getCatLicMedicaMotivos();
	Collection<CatLicMedicaTipoServicioVo> getCatLicMedicaTipoServicio();
	LicenciaMedicaVo getLicenciaMedica(int idAtencion);
	void calcularFechaFinal(MedicoLicenciaMedicaForm medicoLicenciaMedicaForm);

}
