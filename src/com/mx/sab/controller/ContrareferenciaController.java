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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.ContrareferenciaForm;
import com.mx.sab.form.validator.ContrareferenciaValidator;
import com.mx.sab.service.IContrareferenciaService;

@Controller
@Log4j2
@RequestMapping("/listaPacientes")
public class ContrareferenciaController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IContrareferenciaService contrareferenciaService;
	
	@InitBinder("contrareferenciaForm")
	protected void contrareferenciaFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new ContrareferenciaValidator());
	}
	
	public ModelAndView contrareferencia(ContrareferenciaForm contrareferenciaForm) {
		ModelAndView model = new ModelAndView("contrareferencia");
		contrareferenciaService.inicializaContrareferenciaForm(contrareferenciaForm);
		model.addObject("contrareferenciaForm", contrareferenciaForm);
		return model;
	}
	
	@RequestMapping(value = "/nuevoContrareferencia", method = RequestMethod.POST)
	public ModelAndView nuevoContrareferencia(@Valid ContrareferenciaForm contrareferenciaForm, BindingResult result) {
		//log.info("nuevo");
		ModelAndView model = new ModelAndView();
		model.setViewName("contrareferencia");
		if (!result.hasErrors()) {
			contrareferenciaService.save(contrareferenciaForm);
		}
		model.addObject("contrareferenciaForm", contrareferenciaForm);
		
		return model; 
	}
	
}
