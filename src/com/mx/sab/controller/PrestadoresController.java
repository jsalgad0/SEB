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
import com.mx.sab.form.PrestadorForm;
import com.mx.sab.form.validator.PrestadorValidator;
import com.mx.sab.service.IGenericService;
import com.mx.sab.service.IPrestadorService;
import com.mx.sab.vo.LugarAtencionVo;
import com.mx.sab.vo.PrestadoresVo;

@Controller
@Log4j2
@RequestMapping("/administrador")
public class PrestadoresController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IPrestadorService prestadorService;
	
	@Autowired
	private IGenericService genericService;
	
	@InitBinder("prestadorForm")
	protected void prestadorFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new PrestadorValidator());
	}
	
//	@RequestMapping(value = "/prestadores")
//	public ModelAndView prestadores(PrestadorForm prestadorForm) {
//		//log.info("prestadores");
//		ModelAndView model = new ModelAndView();
//		session.setAttribute("prestadores", prestadorService.getPrestadores(prestadorForm));
//		model.setViewName("prestadores");
//		model.addObject("prestadorForm", prestadorForm);
//		return model; 
//	}
//	
//	@RequestMapping(value = "/paginador")
//	public @ResponseBody Collection<PrestadoresVo> paginador(@RequestParam(value = "busqueda") String busqueda,@RequestParam(value = "page") int page) {
//		//log.info("prestadores");
//		Collection<PrestadoresVo> prestadores = prestadorService.getPrestadores(busqueda,page);
//		session.setAttribute("prestadores", prestadores);
//		return prestadores; 
//	}		
//	
//	@RequestMapping(value = "/borrar")
//	public ModelAndView borrar(PrestadorForm prestadorForm) {
//		//log.info("borrar");
//		ModelAndView model = new ModelAndView();
//		prestadorService.delete(prestadorForm);
//		session.setAttribute("prestadores", prestadorService.getPrestadores(prestadorForm));
//		model.setViewName("prestadores");		
//		model.addObject("prestadorForm", prestadorForm);
//		return model; 
//	}	
	
	public ModelAndView inicioPrestador() {
		//log.info("inicio");
		ModelAndView model = new ModelAndView();
		model.setViewName("prestadores");		
		return model;
	}	
	
	public ModelAndView nuevoPrestador() {
		//log.info("nuevoPrestador");
		ModelAndView model = new ModelAndView();
		PrestadorForm prestadorForm = new PrestadorForm();
		session.setAttribute("catEstados", genericService.getCatEstados());
		model.addObject("prestadorForm", prestadorForm);
		model.setViewName("prestadorNuevo");		
		return model;
	}
	
//	@RequestMapping(value = "/inicio")
//	public ModelAndView inicio() {
//		//log.info("inicio");
//		ModelAndView model = new ModelAndView();
//		PrestadorForm prestadorForm = new PrestadorForm();
//		session.setAttribute("catEstados", genericService.getCatEstados());
//		session.setAttribute("catTiposPersonas", prestadorService.getCatTiposPersonas());
//		session.setAttribute("catTipoPrestador", prestadorService.getCatTipoPrestador());
//		model.addObject("prestadorForm", prestadorForm);
//		model.setViewName("prestadorNuevo");		
//		return model;
//	}	
	
//	@RequestMapping(value = "/inicioEditar")
//	public ModelAndView inicioEditar(PrestadorForm prestadorForm) {
//		//log.info("editar");
//		ModelAndView model = new ModelAndView();
//		prestadorService.getPrestador(prestadorForm);
//		session.setAttribute("catEstados", genericService.getCatEstados());
//		session.setAttribute("catTiposPersonas", prestadorService.getCatTiposPersonas());
//		session.setAttribute("catTipoPrestador", prestadorService.getCatTipoPrestador());
//		session.setAttribute("catColonias",genericService.getCatColonias(prestadorForm.getIdMunicipio()));
//		session.setAttribute("catMunicipios",genericService.getCatMunicipios(prestadorForm.getIdEstado()));
//		model.addObject("prestadorForm", prestadorForm);
//		model.setViewName("prestadorNuevo");		
//		return model;
//	}	
	
	@RequestMapping(value = "/prestadorNuevo", method = RequestMethod.POST)
	public ModelAndView prestadorNuevo(@Valid PrestadorForm prestadorForm, BindingResult result) {
		//log.info("prestadorNuevo");
		ModelAndView model = new ModelAndView();
		model.setViewName("prestadorNuevo");
		if (!result.hasErrors()) {
			prestadorService.save(prestadorForm);	
		}else{
			prestadorForm.setExito("");
			prestadorForm.setError("");
		}
		model.addObject("prestadorForm", prestadorForm);
		return model;
	}
	
	@RequestMapping(value = "/editarPrestador", method = RequestMethod.POST)
	public ModelAndView editarPrestador(@Valid PrestadorForm prestadorForm, BindingResult result) {
		//log.info("editarPrestador");
		ModelAndView model = new ModelAndView();
		model.setViewName("prestadorEditar");
		if (!result.hasErrors()) {
			prestadorService.update(prestadorForm);		
		}else{
			prestadorForm.setExito("");
			prestadorForm.setError("");
		}
		model.addObject("prestadorForm", prestadorForm);
		return model; 
	}

	public ModelAndView modificarPrestador() {
		//log.info("modificarPrestador");
		ModelAndView model = new ModelAndView();
		PrestadorForm prestadorForm = new PrestadorForm();
		model.addObject("prestadorForm", prestadorForm);
		model.setViewName("prestadorEditar");		
		return model;
	}	
	
	@RequestMapping(value = "/buscarPrestador", method = RequestMethod.POST)
	public ModelAndView buscarPrestador(PrestadorForm prestadorForm) {
		//log.info("buscarPrestador");
		ModelAndView model = new ModelAndView();
		prestadorService.getPrestador(prestadorForm);
		session.setAttribute("catEstados", genericService.getCatEstados());
		session.setAttribute("catColonias",genericService.getCatColonias(prestadorForm.getIdMunicipio()));
		session.setAttribute("catMunicipios",genericService.getCatMunicipios(prestadorForm.getIdEstado()));
		model.addObject("prestadorForm", prestadorForm);
		model.setViewName("prestadorEditar");		
		return model;
	}
	
	@RequestMapping(value = "/buscarPrestadorDescripcion")
	public @ResponseBody Collection<PrestadoresVo> buscarPrestadorDescripcion(@RequestParam(value = "term") String busqueda) {
		//log.info("buscarLugarAtencion");
		Collection<PrestadoresVo> prestadoresVos = prestadorService.getPrestadoresByDescripcion(busqueda);		
		return prestadoresVos;
	}
	
	@RequestMapping(value = "/buscarPrestadorById")
	public @ResponseBody PrestadoresVo buscarPrestadorById(@RequestParam(value = "id") int id) {
		//log.info("buscarLugarAtencion");
		PrestadoresVo prestadoresVos = prestadorService.getPrestadoresById(id);		
		return prestadoresVos;
	}

	public ModelAndView eliminarPrestador() {
		//log.info("eliminarPrestador");
		ModelAndView model = new ModelAndView();
		PrestadorForm prestadorForm = new PrestadorForm();
		model.addObject("prestadorForm", prestadorForm);
		model.setViewName("prestadorEliminar");		
		return model;
	}
	
	@RequestMapping(value = "/buscarPrestadorEliminar", method = RequestMethod.POST)
	public ModelAndView buscarPrestadorEliminar(PrestadorForm prestadorForm) {
		//log.info("buscarPrestadorEliminar");
		ModelAndView model = new ModelAndView();
		prestadorService.getPrestador(prestadorForm);
		session.setAttribute("catEstados", genericService.getCatEstados());
		session.setAttribute("catColonias",genericService.getCatColonias(prestadorForm.getIdMunicipio()));
		session.setAttribute("catMunicipios",genericService.getCatMunicipios(prestadorForm.getIdEstado()));
		model.addObject("prestadorForm", prestadorForm);
		model.setViewName("prestadorEliminar");		
		return model;
	}
	
	
	@RequestMapping(value = "/eliminarPrestador", method = RequestMethod.POST)
	public ModelAndView eliminarPrestador(PrestadorForm prestadorForm) {
		//log.info("eliminarPrestador");
		ModelAndView model = new ModelAndView();
		model.setViewName("prestadorEliminar");
		prestadorService.delete(prestadorForm);		
		model.addObject("prestadorForm", prestadorForm);
		return model; 
	}	
}
