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

import com.mx.sab.form.MantenimientoModificarUsuarioForm;
import com.mx.sab.form.validator.MantenimientoModificarUsuarioValidator;
import com.mx.sab.model.Roles;
import com.mx.sab.service.ILugarAtencionService;
import com.mx.sab.service.IMantenimientoModificarUsuarioService;
import com.mx.sab.service.IMantenimientoUsuarioNuevoService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.vo.UserInfo;
import com.mx.sab.vo.UsuariosVo;

@Controller
@Log4j2
@RequestMapping("/mantenimientoUsuarios")
public class MantenimientoModificarUsuarioController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ILugarAtencionService lugarAtencionService;
	
	@Autowired
	private IMantenimientoModificarUsuarioService mantenimientoModificarUsuarioService;
	
	@Autowired
	private IMantenimientoUsuarioNuevoService mantenimientoUsuarioNuevoService;
	
	@InitBinder("mantenimientoModificarUsuarioForm")
	protected void mantenimientoModificarUsuarioFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new MantenimientoModificarUsuarioValidator());
	}
	
	@RequestMapping(value = "/inicioModificarUsuario")
	public ModelAndView inicioModificarUsuario(MantenimientoModificarUsuarioForm mantenimientoModificarUsuarioForm) {
		//log.info("inicioModificarUsuario");	
		ModelAndView model = new ModelAndView();
		model.setViewName("mantenimientoModificarUsuarios");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		session.setAttribute("usuarios", mantenimientoModificarUsuarioService.getUsuarios(mantenimientoModificarUsuarioForm,userInfo));
		model.addObject("mantenimientoModificarUsuarioForm", mantenimientoModificarUsuarioForm);
		return model;
	}
	
	@RequestMapping(value = "/paginador")
	public @ResponseBody Collection<UsuariosVo> paginador(@RequestParam(value = "busquedaRfc") String busquedaRfc,
														  @RequestParam(value = "busquedaNombre") String busquedaNombre,
														  @RequestParam(value = "busquedaApellidoPaterno") String busquedaApellidoPaterno,
														  @RequestParam(value = "busquedaApellidoMaterno") String busquedaApellidoMaterno,
													      @RequestParam(value = "page") int page) {
		//log.info("paginador");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<UsuariosVo> usuarios = mantenimientoModificarUsuarioService.getUsuarios(busquedaRfc, busquedaNombre, busquedaApellidoPaterno, busquedaApellidoMaterno, page, userInfo);
		session.setAttribute("usuarios", usuarios);
		return usuarios; 
	}	
	
	@RequestMapping(value = "/modificarUsuarios")
	public ModelAndView modificarUsuarios(MantenimientoModificarUsuarioForm mantenimientoModificarUsuarioForm) {
		//log.info("modificarUsuario");	
		ModelAndView model = new ModelAndView();
		model.setViewName("mantenimientoModificarUsuario");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<Roles> roles = mantenimientoUsuarioNuevoService.getRoles(userInfo);
		session.setAttribute("catRoles", roles);
		session.setAttribute("especialidades", mantenimientoUsuarioNuevoService.getEspecialidades());
		mantenimientoModificarUsuarioService.getUsuario(mantenimientoModificarUsuarioForm,roles,userInfo);
		session.setAttribute("mantenimientoModificarUsuarioFormAux", mantenimientoModificarUsuarioForm);
		model.addObject("mantenimientoModificarUsuarioForm", mantenimientoModificarUsuarioForm);
		return model;
	}
	
	@RequestMapping(value = "/updateModificarUsuarios")
	public ModelAndView updateModificarUsuarios(@Valid MantenimientoModificarUsuarioForm mantenimientoModificarUsuarioForm, BindingResult result) {
		//log.info("agregarUsuarioNuevo");	
		ModelAndView model = new ModelAndView();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		model.setViewName("mantenimientoModificarUsuario");
		if (!result.hasErrors()) {
			MantenimientoModificarUsuarioForm mantenimientoModificarUsuarioFormAux = (MantenimientoModificarUsuarioForm) session.getAttribute("mantenimientoModificarUsuarioFormAux");
			mantenimientoModificarUsuarioService.updateUsuario(mantenimientoModificarUsuarioForm, mantenimientoModificarUsuarioFormAux, userInfo);
			model.setViewName("mantenimientoUsuarios");
		}
		model.addObject("mantenimientoModificarUsuarioForm", mantenimientoModificarUsuarioForm);
		return model; 
	}
	
	
	
}
