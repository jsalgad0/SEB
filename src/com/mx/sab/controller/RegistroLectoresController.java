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

import com.mx.sab.form.RegistroLectoresForm;
import com.mx.sab.form.validator.RegistroLectoresValidator;
import com.mx.sab.service.IRegistroLectoresService;
import com.mx.sab.vo.LugarAtencionVo;
import com.mx.sab.vo.PropietarioLectorVo;

@Controller
@Log4j2
@RequestMapping("/administrador")
public class RegistroLectoresController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IRegistroLectoresService registroLectoresService;
	
	@InitBinder("registroLectoresForm")
	protected void registroLectoresFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new RegistroLectoresValidator());
	}
	
//	@RequestMapping(value = "/registroLectores")
//	public ModelAndView registroLectores(RegistroLectoresForm registroLectoresForm) {
//		//log.info("registroLectores");
//		ModelAndView model = new ModelAndView();
//		session.setAttribute("registroLectores", registroLectoresService.getLectores(registroLectoresForm));
//		model.setViewName("registroLectores");
//		model.addObject("registroLectoresForm", registroLectoresForm);
//		return model; 
//	}
//	
//	@RequestMapping(value = "/paginador")
//	public @ResponseBody Collection<LectoresVo> paginador(@RequestParam(value = "busqueda") String busqueda,@RequestParam(value = "page") int page) {
//		//log.info("registroLectores");
//		Collection<LectoresVo> lectores = registroLectoresService.getLectores(busqueda,page);
//		session.setAttribute("registroLectores", lectores);
//		return lectores; 
//	}	
//	
//	@RequestMapping(value = "/lugarAtencion")
//	public @ResponseBody Collection<AutocompleteVo> lugarAtencion(@RequestParam(value = "busqueda") String busqueda) {
//		//log.info("registroLectores");
//		Collection<AutocompleteVo> lugaresAtencion = registroLectoresService.getLugarAtencion(busqueda);
//		session.setAttribute("lugaresAtencion", lugaresAtencion);
//		return lugaresAtencion; 
//	}
	
	@RequestMapping(value = "/lugarAtencionByCodigo")
	public @ResponseBody Collection<LugarAtencionVo> lugarAtencionByCodigo(@RequestParam(value = "busqueda") String busqueda) {
		//log.info("lugarAtencionByCodigo");
		Collection<LugarAtencionVo> lugarAtencionVos = registroLectoresService.lugarAtencionByCodigo(busqueda);
		session.setAttribute("lugarAtencionVos", lugarAtencionVos);
		return lugarAtencionVos; 
	}
	
//	@RequestMapping(value = "/lugarAtencionById")
//	public @ResponseBody Collection<LugarAtencionVo> lugarAtencionById(@RequestParam(value = "busqueda") String busqueda) {
//		//log.info("lugarAtencionById");
//		Collection<LugarAtencionVo> lugarAtencionVos = registroLectoresService.lugarAtencionById(busqueda);
//		session.setAttribute("lugarAtencionVos", lugarAtencionVos);
//		return lugarAtencionVos; 
//	}
	
//	@RequestMapping(value = "/propietarioLectorById")
//	public @ResponseBody Collection<PropietarioLectorVo> propietarioLectorById(@RequestParam(value = "busqueda") String busqueda) {
//		//log.info("propietarioLectorById");
//		Collection<PropietarioLectorVo> propietarioLectorVos = registroLectoresService.propietarioLectorById(busqueda);
//		session.setAttribute("propietarioLectorVos", propietarioLectorVos);
//		return propietarioLectorVos; 
//	}
	
	@RequestMapping(value = "/propietarioLectorByPropietario")
	public @ResponseBody Collection<PropietarioLectorVo> propietarioLectorByPropietario(@RequestParam(value = "busqueda") String busqueda) {
		//log.info("registroLectores");
		Collection<PropietarioLectorVo> propietarioLectorVos = registroLectoresService.propietarioLectorByPropietario(busqueda);
		session.setAttribute("propietarioLectorVos", propietarioLectorVos);
		return propietarioLectorVos; 
	}
	
	public ModelAndView inicio() {
		//log.info("inicio");
		ModelAndView model = new ModelAndView();
		model.setViewName("registroLectores");		
		return model;
	}
	
	public ModelAndView nuevoRegistroLector() {
		//log.info("nuevoRegistroLector");
		ModelAndView model = new ModelAndView();
		RegistroLectoresForm registroLectoresForm = new RegistroLectoresForm();
		model.addObject("registroLectoresForm", registroLectoresForm);
		model.setViewName("registroLectorNuevo");		
		return model;
	}	
	
	public ModelAndView modificarRegistroLector() {
		//log.info("modificarRegistroLector");
		ModelAndView model = new ModelAndView();
		RegistroLectoresForm registroLectoresForm = new RegistroLectoresForm();
		model.addObject("registroLectoresForm", registroLectoresForm);
		model.setViewName("registroLectorEditar");		
		return model;
	}	
	
	public ModelAndView eliminarRegistroLector() {
		//log.info("eliminarRegistroLector");
		ModelAndView model = new ModelAndView();
		RegistroLectoresForm registroLectoresForm = new RegistroLectoresForm();
		model.addObject("registroLectoresForm", registroLectoresForm);
		model.setViewName("registroLectorEliminar");		
		return model;
	}
	
	@RequestMapping(value = "/buscarPropietarioLector")
	public @ResponseBody Collection<PropietarioLectorVo> buscarPropietarioLector(@RequestParam(value = "term") String busqueda) {
		//log.info("buscarPropietarioLector");
		Collection<PropietarioLectorVo> propietarioLectorVos = registroLectoresService.getPropietariosLector(busqueda);		
		return propietarioLectorVos;
	}	
	
	@RequestMapping(value = "/guardarRegistroLector", method = RequestMethod.POST)
	public ModelAndView guardarRegistroLector(@Valid RegistroLectoresForm registroLectoresForm, BindingResult result) {
		//log.info("guardarRegistroLector");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("registroLectorNuevo");
		
		if (!result.hasErrors()) {
			registroLectoresService.save(registroLectoresForm);	
		}else{
			registroLectoresForm.setExito("");
			registroLectoresForm.setError("");
		}
		
		model.addObject("registroLectoresForm", registroLectoresForm);
		return model; 
	}
	
	@RequestMapping(value = "/editarRegistroLector", method = RequestMethod.POST)
	public ModelAndView editarRegistroLector(@Valid RegistroLectoresForm registroLectoresForm, BindingResult result) {
		//log.info("editarRegistroLector");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("registroLectorEditar");
		
		if (!result.hasErrors()) {
			registroLectoresService.update(registroLectoresForm);
		}else{
			registroLectoresForm.setExito("");
			registroLectoresForm.setError("");
		}
		
		model.addObject("registroLectoresForm", registroLectoresForm);
		return model; 
	}	
	
	@RequestMapping(value = "/borrarRegistroLector", method = RequestMethod.POST)
	public ModelAndView borrarRegistroLector(@Valid RegistroLectoresForm registroLectoresForm, BindingResult result) {
		//log.info("borrarRegistroLector");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("registroLectorEliminar");
		if (!result.hasErrors()) {
			registroLectoresService.delete(registroLectoresForm);	
		}else{
			registroLectoresForm.setExito("");
			registroLectoresForm.setError("");
		}
		
		model.addObject("registroLectoresForm", registroLectoresForm);
		return model; 
	}	
}
