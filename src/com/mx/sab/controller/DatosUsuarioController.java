package com.mx.sab.controller;

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

import com.mx.sab.form.DatosUsuarioForm;
import com.mx.sab.form.validator.DatosUsuarioValidator;
import com.mx.sab.service.IDatosUsuarioService;
import com.mx.sab.service.ILoginService;
import com.mx.sab.vo.UserInfo;


@Controller
@Log4j2
@RequestMapping("/datosUsuario")
public class DatosUsuarioController {
	
	@Autowired
	private IDatosUsuarioService datosUsuarioService;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private HttpSession session;
	
	@InitBinder("datosUsuarioForm")
	protected void datosUsuarioFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new DatosUsuarioValidator());
	}	

	@RequestMapping(value = "/datosUsuario")
	public ModelAndView datosUsuario(DatosUsuarioForm datosUsuarioForm) {
		//log.info("datosUsuario");
		ModelAndView model = new ModelAndView("datosUsuario");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		datosUsuarioService.verificarUsuario(datosUsuarioForm,userInfo);
		model.addObject("datosUsuarioForm", datosUsuarioForm);
		return model;
	}	
	
	@RequestMapping(value = "/datosUsuarioClave")
	public ModelAndView datosUsuarioClave() {
		//log.info("datosUsuarioClave");
		ModelAndView model = new ModelAndView("datosUsuarioClave");
		model.addObject("datosUsuarioForm", new DatosUsuarioForm());
		return model;
	}
	
	@RequestMapping(value = "/actualizarDatosUsuarioClave")
	public ModelAndView actualizarDatosUsuarioClave(DatosUsuarioForm datosUsuarioForm) {
		//log.info("actualizarDatosUsuarioClave");
		ModelAndView model = new ModelAndView("datosUsuarioClave");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		datosUsuarioService.actualizarDatosUsuarioClave(datosUsuarioForm,userInfo);
		model.addObject("datosUsuarioForm", datosUsuarioForm);
		return model;
	}
	
	@RequestMapping(value = "/datosUsuarioPregunta")
	public ModelAndView datosUsuarioPregunta() {
		//log.info("datosUsuarioPregunta");
		ModelAndView model = new ModelAndView("datosUsuarioPregunta");
		session.setAttribute("preguntas", loginService.getPreguntas());
		model.addObject("datosUsuarioForm", new DatosUsuarioForm());
		return model;
	}	
	
	@RequestMapping(value = "/actualizarDatosUsuarioPregunta")
	public ModelAndView actualizarDatosUsuarioPregunta(@Valid DatosUsuarioForm datosUsuarioForm, BindingResult result) {
		//log.info("actualizarDatosUsuarioPregunta");
		ModelAndView model = new ModelAndView("datosUsuarioPregunta");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if (!result.hasErrors()) {
			datosUsuarioService.actualizarDatosUsuarioPregunta(datosUsuarioForm,userInfo);	
		}
		model.addObject("datosUsuarioForm", datosUsuarioForm);
		return model;
	}	
}
