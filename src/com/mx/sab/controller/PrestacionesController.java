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

import com.mx.sab.form.PrestacionesForm;
import com.mx.sab.form.validator.PrestacionesValidator;
import com.mx.sab.service.IAseguradorService;
import com.mx.sab.service.IPrestacionesService;
import com.mx.sab.service.IPrestadorService;

@Controller
@Log4j2
@RequestMapping("/prestaciones")
public class PrestacionesController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IPrestacionesService prestacionesService;
	
	@Autowired
	private IPrestadorService prestadorService;	
	
	@Autowired
	private IAseguradorService aseguradorService;	
	
	@InitBinder("prestacionesForm")
	protected void prestacionesFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new PrestacionesValidator());
	}	
	
	@RequestMapping(value = "/inicio")
	public ModelAndView inicio() {
		//log.info("inicio");
		ModelAndView model = new ModelAndView();
		PrestacionesForm prestacionesForm = new PrestacionesForm();
		session.setAttribute("prestadores", prestadorService.getPrestadoresSinPrestaciones());
		session.setAttribute("aseguradores", aseguradorService.getAseguradoresSinPrestaciones());
		model.addObject("prestacionesForm", prestacionesForm);
		model.setViewName("prestacionesNuevo");		
		return model;
	}	
	
	@RequestMapping(value = "/nuevo", method = RequestMethod.POST)
	public ModelAndView nuevo(@Valid PrestacionesForm prestacionesForm, BindingResult result) {
		//log.info("nuevo");
		
		ModelAndView model = new ModelAndView();
		model.addObject("prestacionesForm", prestacionesForm);
		model.setViewName("prestacionesNuevo");
		
		if (!result.hasErrors()) {
			prestacionesService.save(prestacionesForm);	
			if (!prestacionesForm.isBanderaError()) {
				prestacionesForm = new PrestacionesForm();		
			}
		}
		
		return model; 
	}
}
