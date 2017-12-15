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

import com.mx.sab.form.LugarAtencionForm;
import com.mx.sab.form.validator.LugarAtencionValidator;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.service.IGenericService;
import com.mx.sab.service.ILugarAtencionService;
import com.mx.sab.vo.CatTipoUnidadVo;
import com.mx.sab.vo.LugarAtencionVo;


@Controller
@Log4j2
@RequestMapping("/administrador")
public class LugarAtencionController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private ILugarAtencionService lugarAtencionService;
	
	@Autowired
	private IGenericService genericService;
	
	@InitBinder("lugarAtencionForm")
	protected void lugarAtencionFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new LugarAtencionValidator());
	}	
	
//	@RequestMapping(value = "/borrar")
//	public ModelAndView borrar(LugarAtencionForm lugarAtencionForm) {
//		//log.info("borrar");
//		ModelAndView model = new ModelAndView();
//		lugarAtencionService.delete(lugarAtencionForm);
//		session.setAttribute("lugarAtencion", lugarAtencionService.getLugaresAtencion(lugarAtencionForm));
//		model.setViewName("lugarAtencion");		
//		model.addObject("lugarAtencionForm", lugarAtencionForm);
//		return model; 
//	}		
	
	public ModelAndView inicio() {
		//log.info("inicio");
		ModelAndView model = new ModelAndView();
		model.setViewName("lugarAtencion");		
		return model;
	}
	
	public ModelAndView nuevoLugarAtencion() {
		//log.info("nuevoLugarAtencion");
		ModelAndView model = new ModelAndView();
		LugarAtencionForm lugarAtencionForm = new LugarAtencionForm();
		session.setAttribute("catEstados", genericService.getCatEstados());
		lugarAtencionForm.setRolesLugarAtencionPrimero(lugarAtencionService.getRoles());
		model.addObject("lugarAtencionForm", lugarAtencionForm);
		model.setViewName("lugarAtencionNuevo");		
		return model;
	}
	
	@RequestMapping(value = "/inicioEditar")
	public ModelAndView inicioEditar() {
		//log.info("inicioEditar");
		ModelAndView model = new ModelAndView();
		LugarAtencionForm lugarAtencionForm = new LugarAtencionForm();
		model.addObject("lugarAtencionForm", lugarAtencionForm);
		model.setViewName("lugarAtencionEditar");		
		return model;
	}	
	
	@RequestMapping(value = "/buscarLugarAtencion", method = RequestMethod.POST)
	public ModelAndView buscarLugarAtencion(LugarAtencionForm lugarAtencionForm) {
		//log.info("buscarLugarAtencion");
		ModelAndView model = new ModelAndView();
		lugarAtencionService.getLugarAtencion(lugarAtencionForm);
		session.setAttribute("catEstados", genericService.getCatEstados());
		session.setAttribute("catColonias",genericService.getCatColonias(lugarAtencionForm.getIdMunicipio()));
		session.setAttribute("catMunicipios",genericService.getCatMunicipios(lugarAtencionForm.getIdEstado()));		
		model.addObject("lugarAtencionForm", lugarAtencionForm);
		model.setViewName("lugarAtencionEditar");		
		return model;
	}
	
	@RequestMapping(value = "/buscarLugarAtencionDescripcion")
	public @ResponseBody Collection<LugarAtencionVo> buscarLugarAtencionDescripcion(@RequestParam(value = "term") String busqueda) {
		//log.info("buscarLugarAtencion");
		Collection<LugarAtencionVo> lugarAtencionVos = lugarAtencionService.getLugaresAtencionByDescripcion(busqueda);		
		return lugarAtencionVos;
	}
	
	@RequestMapping(value = "/buscarLugarAtencionCodigo")
	public @ResponseBody Collection<LugarAtencionVo> buscarLugarAtencionCodigo(@RequestParam(value = "term") String busqueda) {
		//log.info("buscarLugarAtencion");
		Collection<LugarAtencionVo> lugarAtencionVos = lugarAtencionService.getLugaresAtencionByCodigo(busqueda);		
		return lugarAtencionVos;
	}	
	
	@RequestMapping(value = "/nuevoLugaratencion", method = RequestMethod.POST)
	public ModelAndView nuevo(@Valid LugarAtencionForm lugarAtencionForm, BindingResult result) {
		//log.info("nuevo");
		ModelAndView model = new ModelAndView();
		model.setViewName("lugarAtencionNuevo");
		if (!result.hasErrors()) {
			lugarAtencionService.save(lugarAtencionForm);	
		}else{
			lugarAtencionForm.setExito("");
			lugarAtencionForm.setError("");
		}
		lugarAtencionService.verificarRolesLugarAtencion(lugarAtencionForm);
		model.addObject("lugarAtencionForm", lugarAtencionForm);
		return model; 
	}
	
	@RequestMapping(value = "/editarLugarAtencion", method = RequestMethod.POST)
	public ModelAndView editarLugarAtencion(@Valid LugarAtencionForm lugarAtencionForm, BindingResult result) {
		//log.info("editarLugarAtencion");
		ModelAndView model = new ModelAndView();
		model.setViewName("lugarAtencionEditar");
		if (!result.hasErrors()) {
			lugarAtencionService.update(lugarAtencionForm);		
		}else{
			lugarAtencionForm.setExito("");
			lugarAtencionForm.setError("");
		}
		lugarAtencionService.verificarRolesLugarAtencion(lugarAtencionForm);
		model.addObject("lugarAtencionForm", lugarAtencionForm);
		return model; 
	}

	@RequestMapping(value = "/inicioEliminar")
	public ModelAndView inicioEliminar() {
		//log.info("inicioEliminar");
		ModelAndView model = new ModelAndView();
		LugarAtencionForm lugarAtencionForm = new LugarAtencionForm();
		model.addObject("lugarAtencionForm", lugarAtencionForm);
		model.setViewName("lugarAtencionEliminar");		
		return model;
	}
	
	@RequestMapping(value = "/buscarLugarAtencionEliminar", method = RequestMethod.POST)
	public ModelAndView buscarLugarAtencionEliminar(LugarAtencionForm lugarAtencionForm) {
		//log.info("buscarLugarAtencionEliminar");
		ModelAndView model = new ModelAndView();
		lugarAtencionService.getLugarAtencion(lugarAtencionForm);
		session.setAttribute("catEstados", genericService.getCatEstados());
		session.setAttribute("catColonias",genericService.getCatColonias(lugarAtencionForm.getIdMunicipio()));
		session.setAttribute("catMunicipios",genericService.getCatMunicipios(lugarAtencionForm.getIdEstado()));		
		model.addObject("lugarAtencionForm", lugarAtencionForm);
		model.setViewName("lugarAtencionEliminar");		
		return model;
	}
	
	
	@RequestMapping(value = "/eliminarLugarAtencion", method = RequestMethod.POST)
	public ModelAndView eliminarLugarAtencion(LugarAtencionForm lugarAtencionForm) {
		//log.info("eliminarLugarAtencion");
		ModelAndView model = new ModelAndView();
		model.setViewName("lugarAtencionEliminar");
		lugarAtencionService.delete(lugarAtencionForm);		
		model.addObject("lugarAtencionForm", lugarAtencionForm);
		return model; 
	}
	
		
}
