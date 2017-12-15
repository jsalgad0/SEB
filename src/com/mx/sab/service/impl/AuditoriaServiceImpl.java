package com.mx.sab.service.impl;

import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IAuditoriaDao;
import com.mx.sab.enums.CatTipoAuditoriaEnum;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Auditoriaautentia;
import com.mx.sab.service.IAuditoriaService;

@Service
@Log4j2
public class AuditoriaServiceImpl implements IAuditoriaService{
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private IAuditoriaDao auditoriaDao;
	
	@Override
	public Auditoriaautentia agregarAuditoria(String idUsuario, String idAfiliado, String idAgenda,
			String tipoDato, String dato, String tipoAudit, String nroAudit,
			String ercDesc, String erc) {
		//log.info("auditoria");
		System.out.println(idAfiliado);
		System.out.println(idAgenda);
		System.out.println(tipoDato);
		System.out.println(dato);
		System.out.println(tipoAudit);
		System.out.println(nroAudit);
		System.out.println(ercDesc);
		System.out.println(erc);
		int agendaId = Integer.parseInt(idAgenda);
		int afiliadoId = Integer.parseInt(idAfiliado);
		int usuarioId = Integer.parseInt(idUsuario);
		
		Auditoriaautentia auditoriaautentia = new Auditoriaautentia();
		
		int idTipoAuditoria = Integer.parseInt(tipoAudit);
		if (idTipoAuditoria == 0) {
			if (ercDesc.indexOf("enrolamiento")!=-1) {
				idTipoAuditoria = CatTipoAuditoriaEnum.ENROLAMIENTO.getId();
			}else{
				idTipoAuditoria = CatTipoAuditoriaEnum.CERTIFICACION.getId();
			}	
		}
		
		if (agendaId!=0) {
			Agenda agenda = agendaDao.getAgendaById(agendaId);
			auditoriaautentia.setAfiliadoId(agenda.getAfiliado().getAfiliadoId());
			auditoriaautentia.setFecha(new Date());
			auditoriaautentia.setMensajeAuditoria(erc);
			auditoriaautentia.setNumeroAuditoria(ercDesc);
			auditoriaautentia.setTipoAuditoriaId(idTipoAuditoria);
		}else if (afiliadoId!=0) {
			auditoriaautentia.setAfiliadoId(afiliadoId);
			auditoriaautentia.setFecha(new Date());
			auditoriaautentia.setMensajeAuditoria(erc);
			auditoriaautentia.setNumeroAuditoria(ercDesc);
			auditoriaautentia.setTipoAuditoriaId(idTipoAuditoria);			
		}else if (usuarioId!=0) {
			auditoriaautentia.setUsuarioId(usuarioId);
			auditoriaautentia.setFecha(new Date());
			auditoriaautentia.setMensajeAuditoria(erc);
			auditoriaautentia.setNumeroAuditoria(ercDesc);
			auditoriaautentia.setTipoAuditoriaId(idTipoAuditoria);			
		}else{
			auditoriaautentia.setFecha(new Date());
			auditoriaautentia.setMensajeAuditoria(erc);
			auditoriaautentia.setNumeroAuditoria(ercDesc);
			auditoriaautentia.setTipoAuditoriaId(idTipoAuditoria);	
		}

		auditoriaDao.save(auditoriaautentia);
		return auditoriaautentia;
	}

}
