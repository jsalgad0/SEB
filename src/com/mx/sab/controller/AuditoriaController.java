package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mx.sab.model.Auditoriaautentia;
import com.mx.sab.service.IAuditoriaService;

@Controller
@Log4j2
@RequestMapping("/auditoria")
public class AuditoriaController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IAuditoriaService auditoriaService;
	
	@RequestMapping(value = "/agregarAuditoria")
	public void agregarAuditoria(@RequestParam(value = "idUsuario") String idUsuario,
								 @RequestParam(value = "idAfiliado") String idAfiliado,
								 @RequestParam(value = "idAgenda") String idAgenda,
								 @RequestParam(value = "tipoDato") String tipoDato,
								 @RequestParam(value = "dato") String dato,
								 @RequestParam(value = "tipoAudit") String tipoAudit,
								 @RequestParam(value = "nroAudit") String nroAudit,
								 @RequestParam(value = "ercDesc") String ercDesc,
								 @RequestParam(value = "erc") String erc) {
		//log.info("agregarAuditoria");
		Auditoriaautentia auditoriaautentia = auditoriaService.agregarAuditoria(idUsuario, idAfiliado, idAgenda, tipoDato, dato, tipoAudit, nroAudit, ercDesc, erc);
//		session.removeAttribute("auditoriaId");
		session.setAttribute("auditoriaId", auditoriaautentia.getAuditoriaId());
	}	
}
