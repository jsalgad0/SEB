package com.mx.sab.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.LoginForm;
import com.mx.sab.model.Menu;
import com.mx.sab.service.ILoginService;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
public class LoginController {
	
	@Autowired
	private ILoginService loginService;	
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		////log.info("Login");
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;
 
	}
 
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is protected page - Admin Page!");
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Comparator<Menu> comparator = new Comparator<Menu>() {
			@Override
		    public int compare(Menu c1, Menu c2) {
		        return c2.getMenuId() - c1.getMenuId();
		    }
		};
		Collections.sort(userInfo.getMenus(), comparator);
		
		session.setAttribute("userInfo", userInfo);		
//		if (Integer.parseInt(userInfo.getRol()) == RolesEnum.ADMINISTRADOR_SAB.getId()) {
//			model.setViewName("menuAdminSab");			
//		}else if(Integer.parseInt(userInfo.getRol()) == RolesEnum.RECEPCIONISTA.getId()) {
//			model.setViewName("menuRecepcionista");
//		}else{
			model.setViewName("menu");
//		}
		loginService.saveLoginLog(userInfo);
		return model; 
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() { 
		ModelAndView model = new ModelAndView();	
		model.setViewName("login");
		model.addObject("loginForm", new LoginForm());
		return model;
	}		
	
	@RequestMapping(value = "/loginVerificacion", method = RequestMethod.POST)
	public ModelAndView loginVerificacion(LoginForm loginForm) {
		loginForm = loginService.validaRfc(loginForm);
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		model.addObject("loginForm", loginForm);
		session.setAttribute("usuarioRols", loginForm.getUsuariorols());		
		return model;
	}	
	
	@RequestMapping(value = "/ingresarClave")
	public ModelAndView ingresarClave(LoginForm loginForm) {
		ModelAndView model = new ModelAndView();
		model.setViewName("ingresarClave");
		loginForm = (LoginForm) session.getAttribute("loginFormSession");
		session.setAttribute("preguntas", loginService.getPreguntas());
		loginForm.setErrorClave("");
		model.addObject("loginForm", loginForm);
		return model;
	}
	
	@RequestMapping(value = "/validarClave")
	public ModelAndView validarClave(LoginForm loginForm) {
		ModelAndView model = new ModelAndView();
		loginService.validarClave(loginForm);
		model.setViewName("ingresarClave");
		model.addObject("loginForm", loginForm);
		return model;
	}
	
	@RequestMapping(value = "/loginVerificacionClave", method = RequestMethod.POST)
	public ModelAndView loginVerificacionClave(LoginForm loginForm) {
		loginForm = loginService.validaClave(loginForm);
		ModelAndView model = new ModelAndView();
		model.setViewName("login"); 
		session.setAttribute("loginFormSession", loginForm);
		model.addObject("loginForm", loginForm);
		return model;
	}
	
	@RequestMapping(value = "/loginSecurity", method = RequestMethod.POST)
	public String loginSecurity(Model model, @ModelAttribute LoginForm loginForm) {
		model.addAttribute("loginForm", loginForm);
		return "loginSecurity";
	}	
	
	@RequestMapping(value = "/olvidoClave")
	public ModelAndView olvidoClave(LoginForm loginForm) {
		ModelAndView model = new ModelAndView();
		model.setViewName("olvidoClave");
		loginService.getUsuarioOlvidoClave(loginForm);
		model.addObject("loginForm", loginForm);
		return model;
	}
	
	@RequestMapping(value = "/validarRespuesta")
	public ModelAndView validarRespuesta(LoginForm loginForm) {
		ModelAndView model = new ModelAndView();
		loginService.validarRespuesta(loginForm);
		model.setViewName("olvidoClave");
		model.addObject("loginForm", loginForm);
		return model;
	}	
	
	@RequestMapping(value = "/validarClaveNueva")
	public ModelAndView validarClaveNueva(LoginForm loginForm) {
		ModelAndView model = new ModelAndView();
		loginService.validarClaveNueva(loginForm);
		model.setViewName("olvidoClave");
		model.addObject("loginForm", loginForm);
		return model;
	}	
}
