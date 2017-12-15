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

import com.mx.sab.form.AutorizacionEspecialForm;
import com.mx.sab.form.validator.AutorizacionEspecialValidator;
import com.mx.sab.service.IAutorizacionEspecialService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/autorizacionEspecial")
public class AutorizacionEspecialController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IAutorizacionEspecialService autorizacionEspecialService;
	
	@InitBinder("autorizacionEspecialForm")
	protected void autorizacionEspecialFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new AutorizacionEspecialValidator());
	}
	
	@RequestMapping(value = "/autorizacionEspecial")
	public ModelAndView autorizacionEspecial(AutorizacionEspecialForm autorizacionEspecialForm) {
		//log.info("autorizacionEspecial");
		ModelAndView modelAndView = new ModelAndView("autorizacionEspecial");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		autorizacionEspecialService.inicializaAutorizacionEspecialForm(autorizacionEspecialForm, userInfo);
		session.setAttribute("catCausas", autorizacionEspecialForm.getCatcausas());
//		session.setAttribute("permisosEspeciales", autorizacionEspecialForm.getPermisoespecialafiliados());
		modelAndView.addObject("autorizacionEspecialForm", autorizacionEspecialForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/autorizarPermisoEspecialVigencia")
	public ModelAndView autorizarPermisoEspecialVigencia(AutorizacionEspecialForm autorizacionEspecialForm) {
		//log.info("autorizarPermisoEspecialVigencia");
		ModelAndView modelAndView = new ModelAndView("autorizacionEspecial");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		autorizacionEspecialService.autorizarPermisoEspecialVigencia(autorizacionEspecialForm, userInfo);
		autorizacionEspecialService.inicializaAutorizacionEspecialForm(autorizacionEspecialForm, userInfo);
		session.setAttribute("catCausas", autorizacionEspecialForm.getCatcausas());
//		session.setAttribute("permisosEspeciales", autorizacionEspecialForm.getPermisoespecialafiliados());
		modelAndView.addObject("autorizacionEspecialForm", autorizacionEspecialForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/huella")
	public ModelAndView huella(AutorizacionEspecialForm autorizacionEspecialForm) {
		//log.info("huella");
		ModelAndView modelAndView = new ModelAndView("autorizacionEspecialHuella");
		modelAndView.addObject("autorizacionEspecialForm", autorizacionEspecialForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/autorizarPermisoEspecialHuella")
	public ModelAndView autorizarPermisoEspecialHuella(@Valid AutorizacionEspecialForm autorizacionEspecialForm, BindingResult result) {
		//log.info("autorizarPermisoEspecialHuella");
		ModelAndView modelAndView = new ModelAndView("autorizacionEspecialHuella");
		if (!result.hasErrors()) {
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			autorizacionEspecialService.autorizarPermisoEspecialHuella(autorizacionEspecialForm, userInfo);
			return autorizacionEspecial(autorizacionEspecialForm);
		}
		modelAndView.addObject("autorizacionEspecialForm", autorizacionEspecialForm);
		return modelAndView;
	}
	
}
