package com.mx.sab.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.SupervisorAtencionesPendientesForm;
import com.mx.sab.form.validator.SupervisorAtencionesPendientesValidator;
import com.mx.sab.service.ISupervisorAtencionesPendientesService;
import com.mx.sab.vo.PermisoEspecialVo;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/supervisor")
public class SupervisorAtencionesPendientesController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ISupervisorAtencionesPendientesService supervisorAtencionesPendientesService; 
	
	@InitBinder("supervisorAtencionesPendientesForm")
	protected void supervisorAtencionesPendientesFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new SupervisorAtencionesPendientesValidator());
	}
	
	@RequestMapping("/inicioAtencionesPendientes")
	public ModelAndView inicioAtencionesPendientes(SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm) {
		ModelAndView modelAndView = new ModelAndView("supervisorAtencionPendientes");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		
		session.setAttribute("atencionesPendientes", supervisorAtencionesPendientesService.getAtencionesPendientes(supervisorAtencionesPendientesForm,userInfo));
		modelAndView.addObject("supervisorAtencionesPendientesForm", supervisorAtencionesPendientesForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/paginador")
	public @ResponseBody Collection<PermisoEspecialVo> paginador(@RequestParam(value = "page") int page) {
		//log.info("paginador");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<PermisoEspecialVo> permisoEspecialVos = supervisorAtencionesPendientesService.getAtencionesPendientes(page, userInfo);
		session.setAttribute("atencionesPendientes", permisoEspecialVos);
		return permisoEspecialVos; 
	}
	
	@RequestMapping("/atencionesPendientesMotivo")
	public ModelAndView atencionesPendientesMotivo(@RequestParam(value = "idAtencionMedica") int idAtencionMedica,
												   @RequestParam(value = "tipoMotivo") int tipoMotivo,
												   @RequestParam(value = "idAfiliado") int idAfiliado, 
												   @RequestParam(value = "autorizarRechazar") int autorizarRechazar) {
		ModelAndView modelAndView = new ModelAndView("supervisorAtencionesPendientesMotivo");
		SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm = supervisorAtencionesPendientesService.inicializarAtencionesPendientesForm(idAtencionMedica, tipoMotivo, idAfiliado, autorizarRechazar);
		modelAndView.addObject("supervisorAtencionesPendientesForm", supervisorAtencionesPendientesForm);
		return modelAndView;
	}	
	
	@RequestMapping("/actualizarAtencionMedica")
	public ModelAndView actualizarAtencionMedica(SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm) {
		ModelAndView modelAndView = new ModelAndView("supervisorAtencionesPendientesMotivo");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		supervisorAtencionesPendientesService.actualizarAtencionMedica(supervisorAtencionesPendientesForm,userInfo);
		modelAndView.addObject("supervisorAtencionesPendientesForm", supervisorAtencionesPendientesForm);
		return modelAndView;
	}	
	
	@RequestMapping("/autorizacionIdentidad")
	public ModelAndView autorizacionIdentidad(SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm) {
		ModelAndView modelAndView = new ModelAndView("supervisorAutorizacionIdentidad");
		supervisorAtencionesPendientesService.inicializarAtencionesPendientesFormIdentidad(supervisorAtencionesPendientesForm);
		session.setAttribute("causas", supervisorAtencionesPendientesService.getCatCausas());
		modelAndView.addObject("supervisorAtencionesPendientesForm", supervisorAtencionesPendientesForm);
		return modelAndView;
	}
	
	@RequestMapping("/actualizarAtencionMedicaIdentidad")
	public ModelAndView actualizarAtencionMedicaIdentidad(@Valid SupervisorAtencionesPendientesForm supervisorAtencionesPendientesForm, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("supervisorAutorizacionIdentidad");
		if (!result.hasErrors()) {
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			supervisorAtencionesPendientesService.actualizarAtencionMedicaIdentidad(supervisorAtencionesPendientesForm,userInfo);			
		}
		modelAndView.addObject("supervisorAtencionesPendientesForm", supervisorAtencionesPendientesForm);
		return modelAndView;
	}
	

}
