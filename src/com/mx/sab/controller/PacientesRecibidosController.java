package com.mx.sab.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.PacientesRecibidosForm;
import com.mx.sab.service.impl.PacientesRecibidosServiceImpl;
import com.mx.sab.vo.PacientesRecibidosVo;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/recepcion")
public class PacientesRecibidosController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private PacientesRecibidosServiceImpl pacientesRecibidosServiceImpl; 
	
	@RequestMapping(value = "/pacientesRecibidos")
	public ModelAndView inicioPersonaConfianza(PacientesRecibidosForm pacientesRecibidosForm) {
		//log.info("pacienteRecepcion");
		ModelAndView model = new ModelAndView();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<PacientesRecibidosVo> pacientesRecibidosVos = pacientesRecibidosServiceImpl.getLista(userInfo, pacientesRecibidosForm);
		model.addObject("pacientesRecibidosForm", pacientesRecibidosForm);
		model.addObject("lista", pacientesRecibidosVos);
		model.setViewName("pacientesRecibidos");		
		return model;
	}	
	
	@RequestMapping(value = "/paginadorPacientesRecibidos")
	public @ResponseBody Collection<PacientesRecibidosVo> paginador(@RequestParam(value = "nombre") String nombre, 
																	@RequestParam(value = "apellidoPaterno") String apellidoPaterno, 
																	@RequestParam(value = "apellidoMaterno") String apellidoMaterno, 
																	@RequestParam(value = "page") int page, 
																	@RequestParam(value = "todos") int todos ) {
		//log.info("paginador");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<PacientesRecibidosVo> permisoRecibidosVos = pacientesRecibidosServiceImpl.getListaPaginador(userInfo, page, nombre, apellidoPaterno, apellidoMaterno, todos);
		session.setAttribute("permisoRecibidosVos", permisoRecibidosVos);
		return permisoRecibidosVos; 
	}
	
}
