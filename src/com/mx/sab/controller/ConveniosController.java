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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.AseguradorForm;
import com.mx.sab.form.ConveniosForm;
import com.mx.sab.form.validator.ConveniosValidator;
import com.mx.sab.service.IAseguradorService;
import com.mx.sab.service.IConveniosService;
import com.mx.sab.service.ICuadroPrestacionesService;
import com.mx.sab.service.ILugarAtencionService;
import com.mx.sab.service.IPrestadorService;
import com.mx.sab.vo.ConveniosVo;

@Controller
@Log4j2
@RequestMapping("/administrador")
public class ConveniosController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IConveniosService conveniosService;
	
	@Autowired
	private IPrestadorService prestadorService;	
	
	@Autowired
	private ILugarAtencionService lugarAtencionService;
	
	@Autowired
	private IAseguradorService aseguradorService;
	
	@Autowired
	private ICuadroPrestacionesService cuadroPrestacionesService;
	
	@InitBinder("conveniosForm")
	protected void conveniosFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new ConveniosValidator());
	}
	
	public ModelAndView inicioConvenio() {
		//log.info("inicio");
		ModelAndView model = new ModelAndView();
		model.setViewName("convenios");		
		return model;
	}

	public ModelAndView nuevoConvenio() {
		//log.info("nuevoConvenio");
		ModelAndView model = new ModelAndView();
		ConveniosForm conveniosForm = new ConveniosForm();
		session.setAttribute("prestadores",prestadorService.getPrestadores());
		session.setAttribute("aseguradores",aseguradorService.getAseguradores());
		model.addObject("conveniosForm", conveniosForm);
		model.setViewName("convenioNuevo");		
		return model;
	}
	
	@RequestMapping(value = "/convenioNuevo", method = RequestMethod.POST)
	public ModelAndView convenioNuevo(@Valid ConveniosForm conveniosForm, BindingResult result) {
		//log.info("convenioNuevo");
		ModelAndView model = new ModelAndView();
		model.setViewName("convenioNuevo");
		if (!result.hasErrors()) {
			conveniosService.save(conveniosForm);	
		}else{
			conveniosForm.setExito("");
			conveniosForm.setError("");
		}
		model.addObject("conveniosForm", conveniosForm);
		return model; 
	}

	public ModelAndView modificarConvenio() {
		//log.info("modificarConvenio");
		ModelAndView model = new ModelAndView();
		ConveniosForm conveniosForm = new ConveniosForm();
		session.setAttribute("prestadores",prestadorService.getPrestadores());
		session.setAttribute("aseguradores",aseguradorService.getAseguradores());
		model.addObject("conveniosForm", conveniosForm);
		model.setViewName("convenioEditar");		
		return model;
	}
	
	@RequestMapping(value = "/buscarConvenios")
	public @ResponseBody Collection<ConveniosVo> buscarConvenios(ConveniosForm conveniosForm) {
		//log.info("buscarConvenios");
		Collection<ConveniosVo> convenios = conveniosService.getConvenios(conveniosForm);
		return convenios; 
	}	
	
	@RequestMapping(value = "/convenioEditar", method = RequestMethod.POST)
	public ModelAndView convenioEditar(@Valid ConveniosForm conveniosForm, BindingResult result) {
		//log.info("convenioEditar");
		ModelAndView model = new ModelAndView();
		model.setViewName("convenioEditar");
		if (!result.hasErrors()) {
			conveniosService.update(conveniosForm);	
		}else{
			conveniosForm.setExito("");
			conveniosForm.setError("");
		}
		model.addObject("conveniosForm", conveniosForm);
		return model; 
	}	
	
	@RequestMapping(value = "/convenioEliminar", method = RequestMethod.POST)
	public ModelAndView convenioEliminar(ConveniosForm conveniosForm) {
		//log.info("convenioEliminar");
		ModelAndView model = new ModelAndView();
		model.setViewName("convenioEditar");
		conveniosService.delete(conveniosForm);	
		model.addObject("conveniosForm", conveniosForm);
		return model; 
	}		
	
	
//	@RequestMapping(value = "/convenios")
//	public ModelAndView convenios(ConveniosForm conveniosForm) {
//		//log.info("convenios");
//		ModelAndView model = new ModelAndView();
//		session.setAttribute("convenios", conveniosService.getConvenios(conveniosForm));
//		model.setViewName("convenios");
//		model.addObject("conveniosForm", conveniosForm);
//		return model; 
//	}
//	
//	@RequestMapping(value = "/paginador")
//	public @ResponseBody Collection<ConveniosVo> paginador(@RequestParam(value = "busquedaP") String busquedaP,
//														   @RequestParam(value = "busquedaL") String busquedaL,
//														   @RequestParam(value = "busquedaA") String busquedaA,
//														   @RequestParam(value = "page") int page) {
//		//log.info("paginador");
//		Collection<ConveniosVo> convenios = conveniosService.getConvenios(busquedaP,busquedaL,busquedaA,page);
//		session.setAttribute("convenios", convenios);
//		return convenios; 
//	}	
	
//	@RequestMapping(value = "/borrar")
//	public ModelAndView borrar(ConveniosForm conveniosForm) {
//		//log.info("borrar");
//		ModelAndView model = new ModelAndView();
//		conveniosService.delete(conveniosForm);
//		session.setAttribute("convenios", conveniosService.getConvenios(conveniosForm));
//		model.setViewName("convenios");		
//		model.addObject("conveniosForm", conveniosForm);
//		return model; 
//	}	
//	
//	@RequestMapping(value = "/inicio")
//	public ModelAndView inicio() {
//		//log.info("inicio");
//		ModelAndView model = new ModelAndView();
//		ConveniosForm conveniosForm = new ConveniosForm();
//		conveniosForm.setOpcionCuadroPrestaciones("C");
//		session.setAttribute("prestadores", prestadorService.getPrestadoresConPrestaciones());
//		session.setAttribute("aseguradores", aseguradorService.getAseguradores());
//		session.setAttribute("lugaresatencion", lugarAtencionService.getLugaresAtencion());
//		session.setAttribute("cuadroPrestaciones", cuadroPrestacionesService.getCuadroPrestaciones());
//		model.addObject("conveniosForm", conveniosForm);
//		model.setViewName("conveniosNuevo");		
//		return model;
//	}	
//	
//	
//	@RequestMapping(value = "/generarNombre")
//	public @ResponseBody String generarNombre(@RequestParam(value = "idPrestadores") Integer idPrestadores,
//														   @RequestParam(value = "idAseguradores") Integer idAseguradores,
//														   @RequestParam(value = "idLugarDeAtencion") Integer idLugarDeAtencion) {
//		//log.info("generarNombre");
//		String nombre = conveniosService.getNombre(idAseguradores, idLugarDeAtencion, idPrestadores);
//		return nombre;
//	}


}
