package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.MantenimientoModificarUsuarioForm;
import com.mx.sab.form.MantenimientoUsuariosForm;

@Controller
@Log4j2
@RequestMapping("/mantenimientoUsuarios")
public class MantenimientoUsuariosController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private MantenimientoUsuarioNuevoController mantenimientoUsuarioNuevoController;
	
	@Autowired
	private MantenimientoModificarUsuarioController mantenimientoModificarUsuarioController;
	
	@Autowired
	private MantenimientoUsuarioPendienteClaveController mantenimientoUsuarioPendienteClaveController;
	
	@Autowired
	private MantenimientoUsuarioDeclaracionPendienteController mantenimientoUsuarioDeclaracionPendienteController;
	
	@Autowired
	private MantenimientoUsuariosBloqueadosController mantenimientoUsuariosBloqueadosController;
	
	@RequestMapping(value = "/inicio")
	public ModelAndView inicio() {
		//log.info("inicio");	
		ModelAndView model = new ModelAndView();
		model.setViewName("mantenimientoUsuarios");
		model.addObject("mantenimientoUsuariosForm", new MantenimientoUsuariosForm());
		return model; 
	}
	
	@RequestMapping(value = "/nuevoUsuario")
	public ModelAndView nuevoUsuario(MantenimientoUsuariosForm mantenimientoUsuariosForm) {
		return mantenimientoUsuarioNuevoController.inicioUsuarioNuevo();
	}
	
	@RequestMapping(value = "/modificarUsuario")
	public ModelAndView modificarUsuario(MantenimientoUsuariosForm mantenimientoUsuariosForm) {
		return mantenimientoModificarUsuarioController.inicioModificarUsuario(new MantenimientoModificarUsuarioForm());
	}
	
	@RequestMapping(value = "/pendientesClave")
	public ModelAndView pendientesClave(MantenimientoUsuariosForm mantenimientoUsuariosForm) {
		return mantenimientoUsuarioPendienteClaveController.inicioPendientesClave();
	}
	
	@RequestMapping(value = "/declaracionPendiente")
	public ModelAndView declaracionPendiente(MantenimientoUsuariosForm mantenimientoUsuariosForm) {
		return mantenimientoUsuarioDeclaracionPendienteController.inicioDeclaracionPendiente();
	}	
	
	@RequestMapping(value = "/bloqueados")
	public ModelAndView bloqueados(MantenimientoUsuariosForm mantenimientoUsuariosForm) {
		return mantenimientoUsuariosBloqueadosController.inicioUsuariosBloqueados();
	}	
}
