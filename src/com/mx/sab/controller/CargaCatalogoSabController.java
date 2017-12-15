package com.mx.sab.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.CargaCatalogoSabForm;
import com.mx.sab.form.CargaPrestacionesAseguradorForm;
import com.mx.sab.form.validator.CargaCatalogoSabValidator;
import com.mx.sab.service.IAseguradorService;
import com.mx.sab.service.ICargaArchivoPrestacionesAseguradorService;
import com.mx.sab.service.ICargaCatalogoSabService;
import com.mx.sab.service.IGenericService;



@Controller
@Log4j2
@RequestMapping("/administrador")
public class CargaCatalogoSabController {
	
	@Autowired IAseguradorService aseguradorService;
	@Autowired ICargaCatalogoSabService cargaCatalogoSabService;
	@Autowired IGenericService genericService;
	@Autowired private HttpSession session;
	
	@InitBinder("cargaCatalogoSabForm")
	protected void cargaCatalogoSabBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new CargaCatalogoSabValidator());
	}
	
	@RequestMapping("/inicioCatalogoSab")
	public ModelAndView inicioCatSab(){
		//log.info("inicioCargaCatalogoSab");
		ModelAndView model = new ModelAndView();
		model.setViewName("inicioCatalogoSab");
		return model;
	}
	
	@RequestMapping("/inicioCargaCatalogoSab")
	public ModelAndView inicioCargaCatSab(){
		//log.info("inicioCargaCatalogoSab");
		ModelAndView model = new ModelAndView();
		model.setViewName("cargaCatalogoSab");
		CargaCatalogoSabForm cargaCatalogoSabForm =new CargaCatalogoSabForm();
		model.addObject("cargaCatalogoSabForm", cargaCatalogoSabForm);
		return model;
	}
	
	@RequestMapping("/cargaCatalogoSab")
	public ModelAndView cargaArchivoPrestacionesAsegurador(@Valid CargaCatalogoSabForm cargaCatalogoSabForm, BindingResult result){
		//log.info("cargaPrestacionesAseguradorForm");
		ModelAndView model = new ModelAndView();
		model.setViewName("cargaCatalogoSab");
		if (!result.hasErrors()) {
			//log.info("Sin errores");
			cargaCatalogoSabService.save(cargaCatalogoSabForm);	
		}else{
			cargaCatalogoSabForm.setExito("");
			cargaCatalogoSabForm.setError("");
		}
		model.addObject("cargaPrestacionesAseguradorForm", cargaCatalogoSabForm);
		return model;
	}
	
	@RequestMapping(value = "/bitacoraSab")
	public ModelAndView mostrarBitacoraSab() {
		//log.info("Mostrar bitacora SAB");
		ModelAndView model = new ModelAndView();
		try{
			//CargaPrestacionesAseguradorForm cargaPrestacionesAseguradorForm = new CargaPrestacionesAseguradorForm();
			//cargaPrestacionesAseguradorForm.setRfc(rfc);
			model.setViewName("bitacoraSAB");
			//model.addObject("cargaPrestacionesAseguradorForm", cargaPrestacionesAseguradorForm);			
		}catch(Exception ex){
			ex.printStackTrace();
			model.setViewName("errorPopUp");
		}

		return model;
	}	
	
	
}
