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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.MantenimientoUsuarioNuevoForm;
import com.mx.sab.form.validator.MantenimientoUsuarioNuevoValidator;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Roles;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.ILugarAtencionService;
import com.mx.sab.service.IMantenimientoUsuarioNuevoService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/mantenimientoUsuarios")
public class MantenimientoUsuarioNuevoController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IMantenimientoUsuarioNuevoService mantenimientoUsuarioNuevoService;
	
	@Autowired
	private ILugarAtencionService lugarAtencionService;
	
	@InitBinder("mantenimientoUsuarioNuevoForm")
	protected void mantenimientoUsuarioNuevoFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new MantenimientoUsuarioNuevoValidator());
	}
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@RequestMapping(value = "/inicioUsuarioNuevo")
	public ModelAndView inicioUsuarioNuevo() {
		//log.info("inicioUsuarioNuevo");	
		ModelAndView model = new ModelAndView();
		model.setViewName("mantenimientoUsuarioNuevo");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		session.setAttribute("catRoles", mantenimientoUsuarioNuevoService.getRoles(userInfo));
		session.setAttribute("especialidades", mantenimientoUsuarioNuevoService.getEspecialidades());
		MantenimientoUsuarioNuevoForm mantenimientoUsuarioNuevoForm = new MantenimientoUsuarioNuevoForm();
		mantenimientoUsuarioNuevoForm.setStatusInput(true);
		mantenimientoUsuarioNuevoForm.setStatusSelect(true);
		mantenimientoUsuarioNuevoForm.setIdEspecialidad1(1);
		model.addObject("mantenimientoUsuarioNuevoForm", mantenimientoUsuarioNuevoForm);
		return model; 
	}
	
	@RequestMapping(value = "/verificarRfc")
	public @ResponseBody MantenimientoUsuarioNuevoForm verificarRfc(MantenimientoUsuarioNuevoForm mantenimientoUsuarioNuevoForm) {
		//log.info("vigente");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<Roles> roles = (Collection<Roles>) session.getAttribute("catRoles");
		mantenimientoUsuarioNuevoService.verificarRfc(mantenimientoUsuarioNuevoForm, userInfo, roles);
		session.removeAttribute("mantenimientoUsuarioNuevoFormRegistrado");
		session.setAttribute("mantenimientoUsuarioNuevoFormRegistrado", mantenimientoUsuarioNuevoForm);
		return mantenimientoUsuarioNuevoForm;
	}
	
	@RequestMapping(value = "/agregarUsuarioNuevo")
	public ModelAndView agregarUsuarioNuevo(@Valid MantenimientoUsuarioNuevoForm mantenimientoUsuarioNuevoForm, BindingResult result) {
		//log.info("agregarUsuarioNuevo");	
		ModelAndView model = new ModelAndView();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		model.setViewName("mantenimientoUsuarioNuevo");
		if (!result.hasErrors()) {
			mantenimientoUsuarioNuevoService.saveUsuario(mantenimientoUsuarioNuevoForm, userInfo);
		}
		model.addObject("mantenimientoUsuarioNuevoForm", mantenimientoUsuarioNuevoForm);
		return model; 
	}
	
	@RequestMapping(value = "/updateUsuario")
	public @ResponseBody MantenimientoUsuarioNuevoForm updateUsuario(MantenimientoUsuarioNuevoForm mantenimientoUsuarioNuevoForm) {
		//log.info("updateUsuario");	
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		mantenimientoUsuarioNuevoService.updateUsuario(mantenimientoUsuarioNuevoForm,userInfo);
		return mantenimientoUsuarioNuevoForm; 
	}
	
	@RequestMapping(value = "/mostrarRegistrado")
	public ModelAndView mostrarRegistrado() {
		//log.info("mostrarRegistrado");	
		ModelAndView model = new ModelAndView();
		model.setViewName("mantenimientoUsuariosMostrarRegistrado");
		return model; 
	}	
	
	
}
