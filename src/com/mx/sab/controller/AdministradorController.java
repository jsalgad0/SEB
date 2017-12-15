package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private LugarAtencionController lugarAtencionController;
	
	@Autowired
	private RegistroLectoresController registroLectoresController;
	
	@Autowired
	private AseguradorController aseguradorController;
	
	@Autowired
	private PrestadoresController prestadoresController;
	
	@Autowired
	private ConveniosController conveniosController;
	
	@RequestMapping(value = "/lugarAtencion")
	public ModelAndView lugarAtencion() {
		session.removeAttribute("menu");
		return lugarAtencionController.inicio();
	}
	
	@RequestMapping(value = "/nuevoLugarAtencion")
	public ModelAndView nuevoLugarAtencion() {
		session.removeAttribute("menu");
		session.setAttribute("menu", 1);
		return lugarAtencionController.nuevoLugarAtencion();
	}	
	
	@RequestMapping(value = "/modificarLugarAtencion")
	public ModelAndView modificarLugarAtencion() {
		session.removeAttribute("menu");
		session.setAttribute("menu", 2);
		return lugarAtencionController.inicioEditar();
	}
	
	@RequestMapping(value = "/eliminarLugarAtencion")
	public ModelAndView eliminarLugarAtencion() {
		session.removeAttribute("menu");
		session.setAttribute("menu", 3);
		return lugarAtencionController.inicioEliminar();
	}
	
	@RequestMapping(value = "/registroLectores")
	public ModelAndView registroLectores() {
		session.removeAttribute("menu");
		return registroLectoresController.inicio();
	}
	
	@RequestMapping(value = "/nuevoRegistroLector")
	public ModelAndView nuevoRegistroLector() {
		session.removeAttribute("menu");
		session.setAttribute("menu", 1);
		return registroLectoresController.nuevoRegistroLector();
	}	
	
	@RequestMapping(value = "/modificarRegistroLector")
	public ModelAndView modificarRegistroLector() {
		session.removeAttribute("menu");
		session.setAttribute("menu", 2);
		return registroLectoresController.modificarRegistroLector();
	}	
	
	@RequestMapping(value = "/eliminarRegistroLector")
	public ModelAndView eliminarRegistroLector() {
		session.removeAttribute("menu");
		session.setAttribute("menu", 3);
		return registroLectoresController.eliminarRegistroLector();
	}	
	
	@RequestMapping(value = "/prestador")
	public ModelAndView prestadores() {
		session.removeAttribute("menu");
		return prestadoresController.inicioPrestador();
	}
	
	@RequestMapping(value = "/nuevoPrestador")
	public ModelAndView nuevoPrestador() {
		session.removeAttribute("menu");
		session.setAttribute("menu", 1);
		return prestadoresController.nuevoPrestador();
	}
	
	@RequestMapping(value = "/modificarPrestador")
	public ModelAndView modificarPrestador() {
		session.removeAttribute("menu");
		session.setAttribute("menu", 2);
		return prestadoresController.modificarPrestador();
	}	
	
	@RequestMapping(value = "/eliminarPrestador")
	public ModelAndView eliminarPrestador() {
		session.removeAttribute("menu");
		session.setAttribute("menu", 3);
		return prestadoresController.eliminarPrestador();
	}
	
	@RequestMapping(value = "/asegurador")
	public ModelAndView asegurador() {
		session.removeAttribute("menu");
		return aseguradorController.inicioAsegurador();
	}
	
	@RequestMapping(value = "/nuevoAsegurador")
	public ModelAndView nuevoAsegurador() {
		session.removeAttribute("menu");
		session.setAttribute("menu", 1);
		return aseguradorController.nuevoAsegurador();
	}
	
	@RequestMapping(value = "/modificarAsegurador")
	public ModelAndView modificarAsegurador() {
		session.removeAttribute("menu");
		session.setAttribute("menu", 2);
		return aseguradorController.modificarAsegurador();
	}	
	
	@RequestMapping(value = "/eliminarAsegurador")
	public ModelAndView eliminarAsegurador() {
		session.removeAttribute("menu");
		session.setAttribute("menu", 3);
		return aseguradorController.eliminarAsegurador();
	}
	
	@RequestMapping(value = "/convenio")
	public ModelAndView convenio() {
		session.removeAttribute("menu");
		return conveniosController.inicioConvenio();
	}
	
	@RequestMapping(value = "/nuevoConvenio")
	public ModelAndView nuevoConvenio() {
		session.removeAttribute("menu");
		session.setAttribute("menu", 1);
		return conveniosController.nuevoConvenio();
	}	
	
	@RequestMapping(value = "/modificarConvenio")
	public ModelAndView modificarConvenio() {
		session.removeAttribute("menu");
		session.setAttribute("menu", 2);
		return conveniosController.modificarConvenio();
	}		
}
