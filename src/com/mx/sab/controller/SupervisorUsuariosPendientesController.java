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

import com.mx.sab.form.SupervisorUsuariosPendientesForm;
import com.mx.sab.form.validator.SupervisorUsuariosPendientesValidator;
import com.mx.sab.service.ISupervisorUsuariosPendientesService;
import com.mx.sab.vo.UserInfo;
import com.mx.sab.vo.UsuariosVo;

@Controller
@Log4j2
@RequestMapping("/supervisor")
public class SupervisorUsuariosPendientesController {
	
	@Autowired
	private HttpSession session;
	
	@InitBinder("supervisorUsuariosPendientesForm")
	protected void supervisorUsuariosPendientesFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new SupervisorUsuariosPendientesValidator());
	}
	
	@Autowired
	private ISupervisorUsuariosPendientesService supervisorUsuariosPendientesService; 

	@RequestMapping("/inicioUsuariosPendientes")
	public ModelAndView inicioUsuariosPendientes(SupervisorUsuariosPendientesForm supervisorUsuariosPendientesForm) {
		ModelAndView modelAndView = new ModelAndView("supervisorUsuariosPendientes");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		session.setAttribute("usuariosPendientes", supervisorUsuariosPendientesService.getUsuariosPendientes(userInfo));
		Collection<UsuariosVo> usuarioLugarAtencion = supervisorUsuariosPendientesService.getUsuariosPendientes(userInfo);
		modelAndView.addObject("supervisorUsuariosPendientesForm", supervisorUsuariosPendientesForm);
		modelAndView.addObject("listaUsuarios", usuarioLugarAtencion);
		
		return modelAndView;
	}
	
	@RequestMapping("/agregarHuellaUsuarioPendiente")
	public ModelAndView huellaUsuarioPendiente(SupervisorUsuariosPendientesForm supervisorUsuariosPendientesForm) {
		//log.info("UsuarioPendiente");
		ModelAndView modelAndView = new ModelAndView("supervisorUsuariosPendientes");
		supervisorUsuariosPendientesService.updateEstatusUsuario(supervisorUsuariosPendientesForm);
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<UsuariosVo> usuarioLugarAtencion = supervisorUsuariosPendientesService.getUsuariosPendientes(userInfo);
		modelAndView.addObject("listaUsuarios", usuarioLugarAtencion);
		modelAndView.addObject("supervisorUsuariosPendientesForm", supervisorUsuariosPendientesForm);
		
		return modelAndView;
	}
	
	@RequestMapping("/asignarClave")
	public ModelAndView asignarPermisoUsuarioPendiente(SupervisorUsuariosPendientesForm supervisorUsuariosPendientesForm) {
		//log.info("Asignar Permiso");
		ModelAndView modelAndView = new ModelAndView("supervisorUsuarioIdentidad");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		//log.info(userInfo.getName());
		session.setAttribute("causas", supervisorUsuariosPendientesService.getCatCausas());
		SupervisorUsuariosPendientesForm form2 = supervisorUsuariosPendientesService.getInfoUsuario(supervisorUsuariosPendientesForm.getIdUsuario());
		modelAndView.addObject("supervisorUsuariosPendientesForm", form2);
		
		return modelAndView;
	}
	
	@RequestMapping("/actualizarClaveUsuarioIdentidad")
	public ModelAndView actualizarClaveUsuarioIdentidad(@Valid SupervisorUsuariosPendientesForm supervisorUsuariosPendientesForm, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("supervisorUsuarioIdentidad");
		SupervisorUsuariosPendientesForm form2 =  supervisorUsuariosPendientesForm;
		if (!result.hasErrors()) {
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			form2 = supervisorUsuariosPendientesService.updateClaveUsuario(supervisorUsuariosPendientesForm, userInfo);						
		}
		modelAndView.addObject("supervisorAtencionesPendientesForm", form2);
		return modelAndView;
	}
	
	@RequestMapping(value = "/paginador2")
	public @ResponseBody Collection<UsuariosVo> paginador(@RequestParam(value = "page") int page) {
		//log.info("paginador");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");		
		
		Collection<UsuariosVo> usuarioLugarAtencion = supervisorUsuariosPendientesService.getUsuariosPendientes(page, userInfo);
		session.setAttribute("usuariosPendientes", usuarioLugarAtencion);
		
		
		return usuarioLugarAtencion; 
	}

}
