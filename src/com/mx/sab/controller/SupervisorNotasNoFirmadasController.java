package com.mx.sab.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.SupervisorNotasNoFirmadasForm;
import com.mx.sab.service.impl.NotasNoFirmadasServceImpl;
import com.mx.sab.vo.NotasNoFirmadasVo;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/supervisor")
public class SupervisorNotasNoFirmadasController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private NotasNoFirmadasServceImpl notasNoFirmadasServceImpl;
	
	public ModelAndView notasNoFirmadas() {
		//log.info("notas");
		ModelAndView modelAndView = new ModelAndView("notasNoFirmadas");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<NotasNoFirmadasVo> notasNoFirmadasVos = notasNoFirmadasServceImpl.getNotas(userInfo.getLugaresdeatencion().getLugarDeAtencionId());
		modelAndView.addObject("notas", notasNoFirmadasVos);
		session.setAttribute("notas", notasNoFirmadasVos);
		return modelAndView;
	}
	
	@RequestMapping("/notasNoFirmadasMotivo")
	public ModelAndView atencionesPendientesMotivo(@RequestParam(value = "idAtencionMedica") int idAtencionMedica,
												   @RequestParam(value = "tipoMotivo") int tipoMotivo,
												   @RequestParam(value = "idAfiliado") int idAfiliado, 
												   @RequestParam(value = "autorizarRechazar") int autorizarRechazar,
												   @RequestParam(value = "medicoAfiliado") int medicoAfiliado) {
		ModelAndView modelAndView = new ModelAndView("supervisorNotasNoFirmadasMotivo");
		SupervisorNotasNoFirmadasForm supervisorNotasNoFirmadasForm = notasNoFirmadasServceImpl.inicializarNotasNoFirmadasForm(idAtencionMedica, tipoMotivo, idAfiliado, autorizarRechazar, medicoAfiliado); 
		modelAndView.addObject("supervisorNotasNoFirmadasForm", supervisorNotasNoFirmadasForm);
		return modelAndView;
	}	
	
	@RequestMapping("/actualizarNotaNoFirmada")
	public ModelAndView actualizarAtencionMedica(SupervisorNotasNoFirmadasForm supervisorNotasNoFirmadasForm) {
		ModelAndView modelAndView = new ModelAndView("supervisorNotasNoFirmadasMotivo");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		notasNoFirmadasServceImpl.actualizarAtencionMedica(supervisorNotasNoFirmadasForm, userInfo);
		modelAndView.addObject("supervisorNotasNoFirmadasForm", supervisorNotasNoFirmadasForm);
		return modelAndView;
	}	

}
