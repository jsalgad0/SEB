package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAtencionMedicaDao;
import com.mx.sab.dao.INotasNoFirmadasDao;
import com.mx.sab.enums.CatMotivosEnum;
import com.mx.sab.form.SupervisorNotasNoFirmadasForm;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catestatusfirma;
import com.mx.sab.model.Motivos;
import com.mx.sab.model.Notamedica;
import com.mx.sab.service.INotasNoFirmadasService;
import com.mx.sab.vo.NotasNoFirmadasVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class NotasNoFirmadasServceImpl implements INotasNoFirmadasService {
	
	@Autowired private INotasNoFirmadasDao notasNoFirmadasDao;
	@Autowired private IAtencionMedicaDao atencionMedicaDao;

	@Override
	public Collection<NotasNoFirmadasVo> getNotas(int idLugarAtencion) {
		//log.info("notas");
		Collection<NotasNoFirmadasVo> noFirmadasVos = new ArrayList<NotasNoFirmadasVo>();
		Collection<Notamedica> notamedicas = notasNoFirmadasDao.getAtenciones(new Date(), idLugarAtencion);
		if(notamedicas != null && notamedicas.size() > 0){
			for (Notamedica notamedica : notamedicas) {
				NotasNoFirmadasVo notasNoFirmadasVo = new NotasNoFirmadasVo();
				notasNoFirmadasVo.setPaciente(notamedica.getAtencionmedica().getAfiliado().getNombre()+" "+notamedica.getAtencionmedica().getAfiliado().getApellidoPaterno()+" "+notamedica.getAtencionmedica().getAfiliado().getApellidoMaterno());
				notasNoFirmadasVo.setIdNota(notamedica.getAtencionmedica().getAtencionMedicaId());
				notasNoFirmadasVo.setMedico(notamedica.getAtencionmedica().getUsuariosByUsuarioMedicoId().getNombre()+" "+notamedica.getAtencionmedica().getUsuariosByUsuarioMedicoId().getApellidoPaterno()+" "+notamedica.getAtencionmedica().getUsuariosByUsuarioMedicoId().getApellidoMaterno());
				notasNoFirmadasVo.setIdAfiliado(notamedica.getAtencionmedica().getAfiliado().getAfiliadoId());
				int firmaPaciente = notasNoFirmadasDao.getFirmaPaciente(notamedica.getAtencionmedica().getAseguradores().getAseguradorId());
				if(firmaPaciente == 1){
					if(notamedica.getAtencionmedica().getCatestatusfirmaByEstatusFirmaMedico().getId() == 4){
						notasNoFirmadasVo.setMedicoAfiliado(1);
					}else{
						notasNoFirmadasVo.setMedicoAfiliado(2);
					}					
				}else{
					notasNoFirmadasVo.setMedicoAfiliado(1);
				}
				noFirmadasVos.add(notasNoFirmadasVo);
			}
		}
		return noFirmadasVos;
	}

	@Override
	public SupervisorNotasNoFirmadasForm inicializarNotasNoFirmadasForm(int idAtencionMedica, int tipoMotivo, int idAfiliado, int autorizarRechazar, int medicoAfiliado) {
		SupervisorNotasNoFirmadasForm supervisorNotasNoFirmadasForm = new SupervisorNotasNoFirmadasForm();
		supervisorNotasNoFirmadasForm.setIdAtencionMedica(idAtencionMedica);
		supervisorNotasNoFirmadasForm.setIdAfiliado(idAfiliado);
		supervisorNotasNoFirmadasForm.setAutorizarRechazar(autorizarRechazar);
		supervisorNotasNoFirmadasForm.setTipoMotivo(tipoMotivo);
		supervisorNotasNoFirmadasForm.setMedicoAfiliado(medicoAfiliado);
		return supervisorNotasNoFirmadasForm;
	}
	
	@Override
	public void actualizarAtencionMedica(SupervisorNotasNoFirmadasForm supervisorNotasNoFirmadasForm, UserInfo userInfo) {
		if (supervisorNotasNoFirmadasForm.getMotivo().trim().length()>0) {
			Atencionmedica atencionmedica = atencionMedicaDao.getAtencionMedicaById(supervisorNotasNoFirmadasForm.getIdAtencionMedica());
			Motivos motivos = new Motivos();
			if(supervisorNotasNoFirmadasForm.getMedicoAfiliado() == 1){
				Catestatusfirma catestatusfirma = notasNoFirmadasDao.getEstatusFirma(5);
				atencionmedica.setCatestatusfirmaByEstatusFirmaMedico(catestatusfirma);
				motivos.setCatmotivos(atencionMedicaDao.getCatMotivo(CatMotivosEnum.IDENTIDAD_MEDICO.getId()));
			}else{
				Catestatusfirma catestatusfirma = notasNoFirmadasDao.getEstatusFirma(5);
				if(atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId() == 4){
					atencionmedica.setCatestatusfirmaByEstatusFirmaMedico(catestatusfirma);
					motivos.setCatmotivos(atencionMedicaDao.getCatMotivo(CatMotivosEnum.IDENTIDAD_MEDICO.getId()));
				}else if(atencionmedica.getCatestatusfirmaByEstatusFirmaPaciente().getId() == 4){
					atencionmedica.setCatestatusfirmaByEstatusFirmaPaciente(catestatusfirma);
					motivos.setCatmotivos(atencionMedicaDao.getCatMotivo(CatMotivosEnum.IDENTIDAD_PACIENTE.getId()));
				}
			}
			atencionMedicaDao.update(atencionmedica);
			motivos.setAtencionmedica(atencionmedica);
			motivos.setMotivo(supervisorNotasNoFirmadasForm.getMotivo());
			atencionMedicaDao.saveMotivo(motivos);
			supervisorNotasNoFirmadasForm.setFinalizo(true);
		}else {
			supervisorNotasNoFirmadasForm.setError("Ingrese el motivo");
		}
	}
	
}
