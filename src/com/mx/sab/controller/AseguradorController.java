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
import com.mx.sab.form.CargaPrestacionesAseguradorForm;
import com.mx.sab.form.validator.AseguradorValidator;
import com.mx.sab.form.validator.CargaArchivoPrestacionesAseguradorValidator;
import com.mx.sab.model.Catestados;
import com.mx.sab.service.IAseguradorService;
import com.mx.sab.service.IGenericService;
import com.mx.sab.service.ICargaArchivoPrestacionesAseguradorService;
import com.mx.sab.vo.AseguradoresVo;



@Controller
@Log4j2
@RequestMapping("/administrador")
public class AseguradorController {
	
	@Autowired IAseguradorService aseguradorService;
	@Autowired ICargaArchivoPrestacionesAseguradorService cargaArchivoPrestacionesService;
	@Autowired IGenericService genericService;
	@Autowired private HttpSession session;
	
	@InitBinder("aseguradorForm")
	protected void altaAseguradorBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new AseguradorValidator());
	}
	
	@InitBinder("cargaPrestacionesAseguradorForm")
	protected void cargaArchivoPrestacionesBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new CargaArchivoPrestacionesAseguradorValidator());
	}
	
	
	@RequestMapping("/nuevo")
	public ModelAndView addPersona(){
		//log.info("asegurador");		
		ModelAndView modelAndView = new ModelAndView();
		Collection<Catestados> estados = genericService.getCatEstados();
		modelAndView.addObject("estados", estados);
		modelAndView.addObject("aseguradorForm", new AseguradorForm());
		session.setAttribute("catEstados", genericService.getCatEstados());
		modelAndView.setViewName("asegurador");
		return modelAndView;
	}
	
	@RequestMapping("/aseguradorNuevo")
	public ModelAndView aseguradorNuevo(@Valid AseguradorForm aseguradorForm, BindingResult result){
		//log.info("aseguradorNuevo");
		ModelAndView model = new ModelAndView();
		model.setViewName("aseguradorNuevo");
		if (!result.hasErrors()) {
			//log.info("Sin errores");
			aseguradorService.save(aseguradorForm);	
		}else{
			aseguradorForm.setExito("");
			aseguradorForm.setError("");
		}
		model.addObject("aseguradorForm", aseguradorForm);
		return model;
	}
	
//	@RequestMapping(value = "/inicio")
//	public ModelAndView aseguradores(AseguradorForm aseguradorForm) {
//		//log.info("aseguradores");
//		ModelAndView model = new ModelAndView();
//		session.setAttribute("aseguradores", aseguradorService.getAseguradores(aseguradorForm));
//		model.setViewName("aseguradores");		
//		model.addObject("aseguradorForm", aseguradorForm);
//		return model; 
//	}	
//	
//	@RequestMapping(value = "/inicioEditar")
//	public ModelAndView inicioEditar(AseguradorForm aseguradorForm) {
//		//log.info("editar");
//		ModelAndView model = new ModelAndView();
//		aseguradorService.getAsegurador(aseguradorForm);
//		session.setAttribute("catEstados", genericService.getCatEstados());
//		session.setAttribute("catColonias",genericService.getCatColonias(aseguradorForm.getIdMunicipio()));
//		session.setAttribute("catMunicipios",genericService.getCatMunicipios(aseguradorForm.getIdEstado()));
//		model.addObject("aseguradorForm", aseguradorForm);
//		model.setViewName("asegurador");		
//		return model;
//	}
	
	@RequestMapping(value = "/editarAsegurador", method = RequestMethod.POST)
	public ModelAndView editarAsegurador(@Valid AseguradorForm aseguradorForm, BindingResult result) {
		//log.info("editarAsegurador");
		ModelAndView model = new ModelAndView();
		model.setViewName("aseguradorEditar");
		if (!result.hasErrors()) {
			aseguradorService.update(aseguradorForm);		
		}else{
			aseguradorForm.setExito("");
			aseguradorForm.setError("");
		}
		model.addObject("aseguradorForm", aseguradorForm);
		return model;
	}
	
	@RequestMapping(value = "/buscarAseguradorDescripcion")
	public @ResponseBody Collection<AseguradoresVo> buscarAseguradorDescripcion(@RequestParam(value = "term") String busqueda) {
		//log.info("buscarLugarAtencion");
		Collection<AseguradoresVo> aseguradoresVos = aseguradorService.getAseguradoresByDescripcion(busqueda);		
		return aseguradoresVos;
	}	
	
	@RequestMapping(value = "/buscarAsegurador", method = RequestMethod.POST)
	public ModelAndView buscarAsegurador(AseguradorForm aseguradorForm) {
		//log.info("buscarAsegurador");
		ModelAndView model = new ModelAndView();
		aseguradorService.getAsegurador(aseguradorForm);
		session.setAttribute("catEstados", genericService.getCatEstados());
		session.setAttribute("catColonias",genericService.getCatColonias(aseguradorForm.getIdMunicipio()));
		session.setAttribute("catMunicipios",genericService.getCatMunicipios(aseguradorForm.getIdEstado()));
		model.addObject("aseguradorForm", aseguradorForm);
		model.setViewName("aseguradorEditar");		
		return model;
	}
	
	@RequestMapping(value = "/buscarAseguradorEliminar", method = RequestMethod.POST)
	public ModelAndView buscarAseguradorEliminar(AseguradorForm aseguradorForm) {
		//log.info("buscarAseguradorEliminar");
		ModelAndView model = new ModelAndView();
		aseguradorService.getAsegurador(aseguradorForm);
		session.setAttribute("catEstados", genericService.getCatEstados());
		session.setAttribute("catColonias",genericService.getCatColonias(aseguradorForm.getIdMunicipio()));
		session.setAttribute("catMunicipios",genericService.getCatMunicipios(aseguradorForm.getIdEstado()));
		model.addObject("aseguradorForm", aseguradorForm);
		model.setViewName("aseguradorEliminar");		
		return model;
	}
	
	@RequestMapping(value = "/eliminarAsegurador", method = RequestMethod.POST)
	public ModelAndView eliminarAsegurador(AseguradorForm aseguradorForm) {
		//log.info("eliminarAsegurador");
		ModelAndView model = new ModelAndView();
		model.setViewName("aseguradorEliminar");
		aseguradorService.delete(aseguradorForm);		
		model.addObject("aseguradorForm", aseguradorForm);
		return model; 
	}	

	public ModelAndView inicioAsegurador() {
		//log.info("inicio");
		ModelAndView model = new ModelAndView();
		model.setViewName("aseguradores");		
		return model;
	}

	public ModelAndView nuevoAsegurador() {
		//log.info("nuevoAsegurador");
		ModelAndView model = new ModelAndView();
		AseguradorForm aseguradorForm = new AseguradorForm();
		session.setAttribute("catEstados", genericService.getCatEstados());
		model.addObject("aseguradorForm", aseguradorForm);
		model.setViewName("aseguradorNuevo");		
		return model;
	}

	public ModelAndView modificarAsegurador() {
		//log.info("modificarAsegurador");
		ModelAndView model = new ModelAndView();
		AseguradorForm aseguradorForm = new AseguradorForm();
		model.addObject("aseguradorForm", aseguradorForm);
		model.setViewName("aseguradorEditar");		
		return model;
	}

	public ModelAndView eliminarAsegurador() {
		//log.info("eliminarAsegurador");
		ModelAndView model = new ModelAndView();
		AseguradorForm aseguradorForm = new AseguradorForm();
		model.addObject("aseguradorForm", aseguradorForm);
		model.setViewName("aseguradorEliminar");		
		return model;
	}	
	
	@RequestMapping(value = "/inicioCargarPrestacionesAsegurador")
	public ModelAndView cargarPrestacionesAsegurador(@RequestParam(value = "rfc") String rfc) {
		//log.info("Carga prestaciones Asegurador");
		ModelAndView model = new ModelAndView();
		try{
			CargaPrestacionesAseguradorForm cargaPrestacionesAseguradorForm = new CargaPrestacionesAseguradorForm();
			cargaPrestacionesAseguradorForm.setRfc(rfc);
			model.setViewName("cargaCuadroPrestacionesAsegurador");
			model.addObject("cargaPrestacionesAseguradorForm", cargaPrestacionesAseguradorForm);			
		}catch(Exception ex){
			ex.printStackTrace();
			model.setViewName("errorPopUp");
		}

		return model;
	}	
	
	
	@RequestMapping("/cargaArchivoPrestacionesAsegurador")
	public ModelAndView cargaArchivoPrestacionesAsegurador(@Valid CargaPrestacionesAseguradorForm cargaPrestacionesAseguradorForm, BindingResult result){
		//log.info("cargaPrestacionesAseguradorForm");
		ModelAndView model = new ModelAndView();
		model.setViewName("cargaCuadroPrestacionesAsegurador");
		if (!result.hasErrors()) {
			//log.info("Sin errores");
			cargaArchivoPrestacionesService.save(cargaPrestacionesAseguradorForm);	
		}else{
			cargaPrestacionesAseguradorForm.setExito("");
			cargaPrestacionesAseguradorForm.setError("");
		}
		model.addObject("cargaPrestacionesAseguradorForm", cargaPrestacionesAseguradorForm);
		return model;
	}
	
}
