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
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.MantenimientoUsuarioBloqueadoForm;
import com.mx.sab.form.validator.MantenimientoUsuarioBloqueadoValidator;
import com.mx.sab.model.Roles;
import com.mx.sab.service.ILugarAtencionService;
import com.mx.sab.service.IMantenimientoUsuarioNuevoService;
import com.mx.sab.service.IMantenimientoUsuariosBloqueadosService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/mantenimientoUsuarios")
public class MantenimientoUsuariosBloqueadosController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ILugarAtencionService lugarAtencionService;	
	
	@Autowired
	private IMantenimientoUsuariosBloqueadosService mantenimientoUsuariosBloqueadosService;
	
	@Autowired
	private IMantenimientoUsuarioNuevoService mantenimientoUsuarioNuevoService;
	
	@InitBinder("mantenimientoUsuarioBloqueadoForm")
	protected void mantenimientoUsuarioBloqueadoFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new MantenimientoUsuarioBloqueadoValidator());
	}
	
	public ModelAndView inicioUsuariosBloqueados() {
		ModelAndView model = new ModelAndView();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		model.setViewName("mantenimientoUsuariosBloqueados");
		model.addObject("mantenimientoUsuarioBloqueadoForm",new MantenimientoUsuarioBloqueadoForm());
		model.addObject("usuariosBloqueados", mantenimientoUsuariosBloqueadosService.getUsuarios(userInfo));
		return model;
	}
	
	@RequestMapping(value = "/usuarioBloqueado")
	public ModelAndView usuarioBloqueado(MantenimientoUsuarioBloqueadoForm mantenimientoUsuarioBloqueadoForm) {
		//log.info("usuario bloqueado");
		ModelAndView model = new ModelAndView();
		model.setViewName("mantenimientoUsuarioBloqueado");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<Roles> roles = mantenimientoUsuarioNuevoService.getRoles(userInfo);
		session.setAttribute("especialidades", mantenimientoUsuarioNuevoService.getEspecialidades());
		session.setAttribute("catRoles", roles);
		mantenimientoUsuariosBloqueadosService.getUsuario(mantenimientoUsuarioBloqueadoForm, roles, userInfo);
		model.addObject("mantenimientoUsuarioBloqueadoForm",mantenimientoUsuarioBloqueadoForm);
		return model;
	}
	
	@RequestMapping(value = "/actualizarUsuarioBloqueado")
	public ModelAndView actualizarUsuarioBloqueado(@Valid MantenimientoUsuarioBloqueadoForm mantenimientoUsuarioBloqueadoForm, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.setViewName("mantenimientoUsuarioBloqueado");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if (!result.hasErrors()) {
			mantenimientoUsuariosBloqueadosService.getActualizarUsuario(mantenimientoUsuarioBloqueadoForm, userInfo);	
		}
		model.addObject("mantenimientoUsuarioBloqueadoForm",mantenimientoUsuarioBloqueadoForm);
		return model;
	}
}
