package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.SupervisorAtencionesPendientesForm;
import com.mx.sab.form.SupervisorUsuariosPendientesForm;

@Controller
@Log4j2
@RequestMapping("/supervisor")
public class SupervisorController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private SupervisorAtencionesPendientesController supervisorAtencionesPendientesController;
	
	@Autowired
	private SupervisorNotasNoFirmadasController supervisorNotasNoFirmadasController;
	
	@Autowired
	private SupervisorUsuariosPendientesController supervisorUsuariosPendientesController;
	
	@RequestMapping(value = "/atencionesPendientes")
	public ModelAndView atencionesPendientes() {
		return supervisorAtencionesPendientesController.inicioAtencionesPendientes(new SupervisorAtencionesPendientesForm());
	}
	
	@RequestMapping(value = "/notasNoFirmadas")
	public ModelAndView notasNoFirmadas() {
		//log.info("notas");
		return supervisorNotasNoFirmadasController.notasNoFirmadas();
	}
	
	@RequestMapping(value = "/usuariosPendientes")
	public ModelAndView usuariosPendientes() {
		return supervisorUsuariosPendientesController.inicioUsuariosPendientes(new SupervisorUsuariosPendientesForm());
	}
	
}
