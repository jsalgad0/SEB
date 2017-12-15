package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IPacientesRecibidosDao;
import com.mx.sab.form.PacientesRecibidosForm;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Usuarioespecialidades;
import com.mx.sab.service.IPacientesRecibidosService;
import com.mx.sab.vo.PacientesRecibidosVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class PacientesRecibidosServiceImpl implements IPacientesRecibidosService {
	
	@Autowired
	IPacientesRecibidosDao pacientesRecibidosDao;
	
	@Override	
	public Collection<PacientesRecibidosVo> getLista(UserInfo userInfo, PacientesRecibidosForm pacientesRecibidosForm) {
		//log.info("pacientes recibidos");
		int filas = 8;
		int paginasTotal = 0;
		int inicio = 0;
		int fin = 0;
		int pagina = 1;
		Collection<PacientesRecibidosVo> pacientesRecibidosVos = new ArrayList<PacientesRecibidosVo>();
		Collection<Atencionmedica> atencionmedicas = null;
		int totalPacientesRecibidos = 0;
		
		if(pacientesRecibidosForm.getNombre() == null || pacientesRecibidosForm.getNombre() == "null"){
			pacientesRecibidosForm.setNombre("");
		}
		if(pacientesRecibidosForm.getApellidoPaterno() == null || pacientesRecibidosForm.getApellidoPaterno() == "null"){
			pacientesRecibidosForm.setApellidoPaterno("");
		}
		if(pacientesRecibidosForm.getApellidoMaterno() == null || pacientesRecibidosForm.getApellidoMaterno() == "null"){
			pacientesRecibidosForm.setApellidoMaterno("");
		}
		
		if(pacientesRecibidosForm.getTodoRecepcion() == 0){
			totalPacientesRecibidos = pacientesRecibidosDao.getAtencionesCountByRecep(new Date(), userInfo.getUsuarios().getUsuarioId(), userInfo.getLugaresdeatencion().getLugarDeAtencionId(), pacientesRecibidosForm.getNombre(), pacientesRecibidosForm.getApellidoPaterno(), pacientesRecibidosForm.getApellidoMaterno());
		}else{
			totalPacientesRecibidos = pacientesRecibidosDao.getAtencionesCount(new Date(), userInfo.getLugaresdeatencion().getLugarDeAtencionId(), pacientesRecibidosForm.getNombre(), pacientesRecibidosForm.getApellidoPaterno(), pacientesRecibidosForm.getApellidoMaterno());
		}
		
		if (totalPacientesRecibidos>0) {
			paginasTotal = totalPacientesRecibidos / filas;
			if (totalPacientesRecibidos % filas != 0) {
				paginasTotal++;
			}
			
			if (paginasTotal>8) {
				pacientesRecibidosForm.setDisplay(8);
			}else {
				pacientesRecibidosForm.setDisplay(paginasTotal);
			}
			
			pacientesRecibidosForm.setCount(paginasTotal);
			inicio = (pagina-1)*8;
			fin = (pagina*8);
		}		
		
		if(pacientesRecibidosForm.getTodoRecepcion() == 0){
			atencionmedicas = pacientesRecibidosDao.getAtencionesByRecep(new Date(), userInfo.getUsuarios().getUsuarioId(), userInfo.getLugaresdeatencion().getLugarDeAtencionId(), inicio, fin, pacientesRecibidosForm.getNombre(), pacientesRecibidosForm.getApellidoPaterno(), pacientesRecibidosForm.getApellidoMaterno());
		}else{
			atencionmedicas = pacientesRecibidosDao.getAtenciones(new Date(), userInfo.getLugaresdeatencion().getLugarDeAtencionId(), inicio, fin, pacientesRecibidosForm.getNombre(), pacientesRecibidosForm.getApellidoPaterno(), pacientesRecibidosForm.getApellidoMaterno());
		}		
		
		for (Atencionmedica atencionmedica : atencionmedicas) {
			PacientesRecibidosVo pacientesRecibidosVo = new PacientesRecibidosVo();
			if(atencionmedica.getUsuariosByUsuarioMedicoId() != null){
				for (Usuarioespecialidades esp : atencionmedica.getUsuariosByUsuarioMedicoId().getUsuarioespecialidadeses()) {
					pacientesRecibidosVo.setEspecialidad(esp.getCatespecialidadesmedicas().getEspecialidadMedica());
				}
				if(atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId() == 1 || atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId() == 3 || atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId() == 5){
					pacientesRecibidosVo.setEstado(1);
				}else{
					pacientesRecibidosVo.setEstado(0);
				}
				pacientesRecibidosVo.setMedico(atencionmedica.getUsuariosByUsuarioMedicoId().getNombre()+" "+atencionmedica.getUsuariosByUsuarioMedicoId().getApellidoPaterno()+" "+atencionmedica.getUsuariosByUsuarioMedicoId().getApellidoMaterno());
			}else{
				pacientesRecibidosVo.setMedico("");
				pacientesRecibidosVo.setEspecialidad("");
				pacientesRecibidosVo.setEstado(0);
			}
			pacientesRecibidosVo.setHoraCita(atencionmedica.getHoraAsistio());
			pacientesRecibidosVo.setHoraLlegada(atencionmedica.getHoraAsistio());
			pacientesRecibidosVo.setPaciente(atencionmedica.getAfiliado().getNombre()+" "+atencionmedica.getAfiliado().getApellidoPaterno()+" "+atencionmedica.getAfiliado().getApellidoMaterno());
			pacientesRecibidosVos.add(pacientesRecibidosVo);		
		}
		
		return pacientesRecibidosVos;
	}	
	
	
	@Override
	public Collection<PacientesRecibidosVo> getListaPaginador(UserInfo userInfo, int page, String nombre, String apellidoPaterno, String apellidoMaterno, int todos) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		Collection<PacientesRecibidosVo> pacientesRecibidosVos = new ArrayList<PacientesRecibidosVo>();
		Collection<Atencionmedica> atencionmedicas = null;
		inicio = (pagina-1)*7;
		fin = 7;
		if(todos == 1){
			atencionmedicas = pacientesRecibidosDao.getAtenciones(new Date(), userInfo.getLugaresdeatencion().getLugarDeAtencionId(), inicio, fin, nombre, apellidoPaterno, apellidoMaterno);
		}else{
			atencionmedicas = pacientesRecibidosDao.getAtencionesByRecep(new Date(), userInfo.getUsuarios().getUsuarioId(), userInfo.getLugaresdeatencion().getLugarDeAtencionId(), inicio, fin, nombre, apellidoPaterno, apellidoMaterno);
		}
		for (Atencionmedica atencionmedica : atencionmedicas) {
			PacientesRecibidosVo pacientesRecibidosVo = new PacientesRecibidosVo();
			if(atencionmedica.getUsuariosByUsuarioMedicoId() != null){
				for (Usuarioespecialidades esp : atencionmedica.getUsuariosByUsuarioMedicoId().getUsuarioespecialidadeses()) {
					pacientesRecibidosVo.setEspecialidad(esp.getCatespecialidadesmedicas().getEspecialidadMedica());
				}
				if(atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId() == 1 || atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId() == 3 || atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId() == 5){
					pacientesRecibidosVo.setEstado(1);
				}else{
					pacientesRecibidosVo.setEstado(0);
				}
				pacientesRecibidosVo.setMedico(atencionmedica.getUsuariosByUsuarioMedicoId().getNombre()+" "+atencionmedica.getUsuariosByUsuarioMedicoId().getApellidoPaterno()+" "+atencionmedica.getUsuariosByUsuarioMedicoId().getApellidoMaterno());
			}else{
				pacientesRecibidosVo.setMedico("");
				pacientesRecibidosVo.setEspecialidad("");
				pacientesRecibidosVo.setEstado(0);
			}
			pacientesRecibidosVo.setHoraCita(atencionmedica.getHoraAsistio());
			pacientesRecibidosVo.setHoraLlegada(atencionmedica.getHoraAsistio());
			pacientesRecibidosVo.setPaciente(atencionmedica.getAfiliado().getNombre()+" "+atencionmedica.getAfiliado().getApellidoPaterno()+" "+atencionmedica.getAfiliado().getApellidoMaterno());
			pacientesRecibidosVos.add(pacientesRecibidosVo);		
		}
		
		return pacientesRecibidosVos;
	}

}
