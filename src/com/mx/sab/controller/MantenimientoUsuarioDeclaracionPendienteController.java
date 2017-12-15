package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.MantenimientoUsuarioNuevoForm;
import com.mx.sab.service.ILugarAtencionService;
import com.mx.sab.service.IMantenimientoUsuarioDeclaracionPendienteService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/mantenimientoUsuarios")
public class MantenimientoUsuarioDeclaracionPendienteController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IUsuarioService usuarioService;	
	
	@Autowired
	private ILugarAtencionService lugarAtencionService;
	
	@Autowired
	private IMantenimientoUsuarioDeclaracionPendienteService mantenimientoUsuarioDeclaracionPendienteService;

	public ModelAndView inicioDeclaracionPendiente() {
		ModelAndView model = new ModelAndView();
		try{
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			model.setViewName("mantenimientoUsuariosDeclaracionPendiente");
			model.addObject("usuariosDeclaracionPendiente", mantenimientoUsuarioDeclaracionPendienteService.getUsuarios(userInfo));
			model.addObject("mantenimientoUsuarioNuevoForm", new MantenimientoUsuarioNuevoForm());			
		}catch(Exception ex){
			ex.printStackTrace();
			model.setViewName("errorPopUp");
		}
		return model;
	}
	
	@RequestMapping(value = "/mostrarDeclaracionPendiente")
	public ModelAndView mostrarDeclaracionPendiente(@RequestParam(value = "idUsuario") int idUsuario) {
		ModelAndView model = new ModelAndView();
		try{
			model.addObject("usuario", mantenimientoUsuarioDeclaracionPendienteService.getUsuario(idUsuario));
			model.setViewName("mantenimientoUsuariosMostrarDeclaracion");			
		}catch(Exception ex){
			ex.printStackTrace();
			model.setViewName("errorPopUp");
		}

		return model;
	}

}
