package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.AtencionTerminarForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.service.IAtencionTerminarService;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class AtencionTerminarController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IAtencionTerminarService atencionTerminarService;
	
	@RequestMapping(value = "/atencionTerminar")
	public ModelAndView inicioTerminar(AtencionTerminarForm atencionTerminarForm) {
		//log.info("inicioTerminar");
		ModelAndView modelAndView = new ModelAndView("atencionTerminar");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		atencionTerminarService.inicializarForm(atencionTerminarForm,userInfo);
		modelAndView.addObject("atencionTerminarForm", atencionTerminarForm);
		session.setAttribute("atencionTerminarFormSession", atencionTerminarForm);
		
		MedicoAtencionPacientesForm medicoAtencionPacientesForm = new MedicoAtencionPacientesForm();
		medicoAtencionPacientesForm.setIdAtencion(atencionTerminarForm.getIdAtencion());
		modelAndView.addObject("medicoAtencionPacientesForm", medicoAtencionPacientesForm);
		modelAndView.addObject("tomaSignosVo", session.getAttribute("infoSignos"));
		return modelAndView; 
	}
	
	@RequestMapping(value = "/mostrarClave")
	public ModelAndView mostrarClave() {
		//log.info("mostrarClave");
		ModelAndView modelAndView = new ModelAndView("mostrarClave");
		modelAndView.addObject("atencionTerminarForm", new AtencionTerminarForm());
		return modelAndView; 
	}
	
	@RequestMapping(value = "/verificarClave")
	public ModelAndView verificarClave(AtencionTerminarForm atencionTerminarForm) {
		//log.info("verificarClave");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		atencionTerminarService.verificarClave(atencionTerminarForm, userInfo);
		ModelAndView modelAndView = new ModelAndView("mostrarClave");
		modelAndView.addObject("atencionTerminarForm", atencionTerminarForm);
		return modelAndView; 
	}	
	
	@RequestMapping(value = "/finTerminar")
	public ModelAndView finTerminar(AtencionTerminarForm atencionTerminarForm) {
		//log.info("inicioTerminar");
		ModelAndView modelAndView = new ModelAndView("finTerminar");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		AtencionTerminarForm atencionTerminarFormAux = (AtencionTerminarForm) session.getAttribute("atencionTerminarFormSession");
		atencionTerminarService.inicializarForm(atencionTerminarForm, userInfo, atencionTerminarFormAux);
		modelAndView.addObject("atencionTerminarForm", atencionTerminarForm);
		
		MedicoAtencionPacientesForm medicoAtencionPacientesForm = new MedicoAtencionPacientesForm();
		medicoAtencionPacientesForm.setIdAtencion(atencionTerminarForm.getIdAtencion());
		modelAndView.addObject("medicoAtencionPacientesForm", medicoAtencionPacientesForm);
		modelAndView.addObject("tomaSignosVo", session.getAttribute("infoSignos"));
		return modelAndView; 
	}	
}
