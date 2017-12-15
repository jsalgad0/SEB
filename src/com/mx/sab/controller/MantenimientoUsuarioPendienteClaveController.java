package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.MantenimientoUsuariosForm;
import com.mx.sab.service.ILugarAtencionService;
import com.mx.sab.service.IMantenimientoUsuarioPendienteClaveService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/mantenimientoUsuarios")
public class MantenimientoUsuarioPendienteClaveController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ILugarAtencionService lugarAtencionService;	
	
	@Autowired
	private IMantenimientoUsuarioPendienteClaveService mantenimientoUsuarioPendienteClaveService;

	public ModelAndView inicioPendientesClave() {
		ModelAndView model = new ModelAndView();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		model.setViewName("mantenimientoUsuariosPendientesClave");
		model.addObject("usuariosPendienteClave", mantenimientoUsuarioPendienteClaveService.getUsuarios(userInfo));
		return model;
	}
	
}
