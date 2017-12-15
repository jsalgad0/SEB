package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.service.IMedicoAtencionPacientesService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.AfiliadoVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class MedicoAtencionPacientesServiceImpl implements IMedicoAtencionPacientesService {
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Override
	public Collection<AfiliadoVo> getAfiliados(MedicoAtencionPacientesForm medicoAtencionPacientesForm,UserInfo userInfo) {
		int filas = 7;
		int paginasTotal = 0;
		int inicio = 0;
		int fin = 0;
		int pagina = 1;
		Collection<AfiliadoVo> afiliadoVos = new ArrayList<>();
		Collection<Atencionmedica> atencionmedicas = new ArrayList<>();
		
		if (medicoAtencionPacientesForm==null) {
			medicoAtencionPacientesForm = new MedicoAtencionPacientesForm();
		}
		
		if (medicoAtencionPacientesForm.getBusquedaApellidoPaterno()==null) {
			medicoAtencionPacientesForm.setBusquedaApellidoPaterno("");	
		}
		
		if (medicoAtencionPacientesForm.getBusquedaFecha()==null) {
			medicoAtencionPacientesForm.setBusquedaFecha(FormatUtil.getDate());	
		}
		
		if (medicoAtencionPacientesForm.getBusquedaidEstatus()==0){
			medicoAtencionPacientesForm.setBusquedaidEstatus(0);
		}
		
		int totalAfiliados = 0;
		
		totalAfiliados = agendaDao.getAtencionPacientesConDoctorCount(medicoAtencionPacientesForm.getBusquedaApellidoPaterno(), medicoAtencionPacientesForm.getBusquedaFecha(), userInfo.getLugaresdeatencion().getLugarDeAtencionId(), userInfo.getUsuarios().getUsuarioId(),medicoAtencionPacientesForm.getBusquedaidEstatus());
//		totalAfiliados = totalAfiliados + agendaDao.getAtencionPacientesSinDoctorCount(medicoAtencionPacientesForm.getBusquedaApellidoPaterno(), medicoAtencionPacientesForm.getBusquedaFecha(), userInfo.getLugaresdeatencion().getLugarDeAtencionId(),medicoAtencionPacientesForm.getBusquedaidEstatus());

		if (totalAfiliados>0) {
			paginasTotal = totalAfiliados / filas;
			if (totalAfiliados % filas != 0) {
				paginasTotal++;
			}
			
			if (paginasTotal>7) {
				medicoAtencionPacientesForm.setDisplay(7);
			}else {
				medicoAtencionPacientesForm.setDisplay(paginasTotal);
			}
			
			medicoAtencionPacientesForm.setCount(paginasTotal);
			inicio = (pagina-1)*7;
			fin = (pagina*7);
			
			Collection<Atencionmedica> atencionmedicasAux = agendaDao.getAtencionPacientesConDoctor(medicoAtencionPacientesForm.getBusquedaApellidoPaterno(), medicoAtencionPacientesForm.getBusquedaFecha(), inicio,fin, userInfo.getLugaresdeatencion().getLugarDeAtencionId(), userInfo.getUsuarios().getUsuarioId(),medicoAtencionPacientesForm.getBusquedaidEstatus());
			if (atencionmedicasAux!=null) {
				atencionmedicas.addAll(atencionmedicasAux);	
			}
//			atencionmedicasAux = agendaDao.getAtencionPacientesSinDoctor(medicoAtencionPacientesForm.getBusquedaApellidoPaterno(), medicoAtencionPacientesForm.getBusquedaFecha(), inicio,fin, userInfo.getLugaresdeatencion().getLugarDeAtencionId(),medicoAtencionPacientesForm.getBusquedaidEstatus());
//			if (atencionmedicasAux!=null) {
//				atencionmedicas.addAll(atencionmedicasAux);	
//			}
			
			for (Atencionmedica atencionmedica : atencionmedicas) {
				AfiliadoVo afiliadoVo = new AfiliadoVo();
				afiliadoVo.setAfiliadoId(atencionmedica.getAfiliado().getAfiliadoId());
				afiliadoVo.setNombre(atencionmedica.getAfiliado().getNombre());
				afiliadoVo.setApellidoPaterno(atencionmedica.getAfiliado().getApellidoPaterno());
				afiliadoVo.setApellidoMaterno(atencionmedica.getAfiliado().getApellidoMaterno());
				afiliadoVo.setHora(FormatUtil.getTime(atencionmedica.getHoraAsistio()));
				afiliadoVo.setAtencionId(atencionmedica.getAtencionMedicaId());
				if(atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId() == 1 || atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId() == 3 || atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId() == 5){
					afiliadoVo.setAtendido(true);
				}else{
					afiliadoVo.setAtendido(false);
				}
				
				if (FormatUtil.getDate(medicoAtencionPacientesForm.getBusquedaFecha()).compareTo(FormatUtil.getDate(FormatUtil.getDate(new Date()))) != 0) {
					afiliadoVo.setAtendido(true);
				}
				afiliadoVos.add(afiliadoVo);
			}
		}else {
			//log.info("No hay usuarios");
			medicoAtencionPacientesForm.setSinResultados(true);
		}
	
		return afiliadoVos;
	}

	@Override
	public Collection<AfiliadoVo> getAfiliados(String busquedaApellidoPaterno, String fecha, int idEstatus, int page, UserInfo userInfo) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		Collection<AfiliadoVo> afiliadoVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;
		Collection<Atencionmedica> atencionmedicas = new ArrayList<>();
		Collection<Atencionmedica> atencionmedicasAux = agendaDao.getAtencionPacientesConDoctor(busquedaApellidoPaterno, fecha, inicio,fin, userInfo.getLugaresdeatencion().getLugarDeAtencionId(), userInfo.getUsuarios().getUsuarioId(), idEstatus);
		if (atencionmedicasAux!=null) {
			atencionmedicas.addAll(atencionmedicasAux);	
		}
//		atencionmedicasAux = agendaDao.getAtencionPacientesSinDoctor(busquedaApellidoPaterno, fecha, inicio,fin, userInfo.getLugaresdeatencion().getLugarDeAtencionId(), idEstatus);
//		if (atencionmedicasAux!=null) {
//			atencionmedicas.addAll(atencionmedicasAux);	
//		}
		
		for (Atencionmedica atencionmedica : atencionmedicas) {
			AfiliadoVo afiliadoVo = new AfiliadoVo();
			afiliadoVo.setAfiliadoId(atencionmedica.getAfiliado().getAfiliadoId());
			afiliadoVo.setNombre(atencionmedica.getAfiliado().getNombre());
			afiliadoVo.setApellidoPaterno(atencionmedica.getAfiliado().getApellidoPaterno());
			afiliadoVo.setApellidoMaterno(atencionmedica.getAfiliado().getApellidoMaterno());
			afiliadoVo.setHora(FormatUtil.getTime(atencionmedica.getHoraAsistio()));
			afiliadoVo.setAtencionId(atencionmedica.getAtencionMedicaId());
			if(atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId() == 1 || atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId() == 3 || atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId() == 5){
				afiliadoVo.setAtendido(true);
			}else{
				afiliadoVo.setAtendido(false);
			}			
			if (FormatUtil.getDate(fecha).compareTo(FormatUtil.getDate(FormatUtil.getDate(new Date()))) != 0) {
				afiliadoVo.setAtendido(true);
			}
			afiliadoVos.add(afiliadoVo);
		}
		
		return afiliadoVos;
	}

}
