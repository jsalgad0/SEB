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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.LicenciaMedicaForm;
import com.mx.sab.form.validator.LicenciaMedicaValidator;
import com.mx.sab.service.ILicenciaMedicaService;
import com.mx.sab.vo.FechaLicenciaMedica;

@Controller
@Log4j2
@RequestMapping("/listaPacientes")
public class LicenciaMedicaController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private ILicenciaMedicaService licenciaMedicaService;
	
	@InitBinder("licenciaMedicaForm")
	protected void licenciaMedicaFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new LicenciaMedicaValidator());
	}

	public ModelAndView licenciaMedica(LicenciaMedicaForm licenciaMedicaForm) {
		session.setAttribute("catLicMedicaMotivos", licenciaMedicaService.getCatLicMedicaMotivos());
		session.setAttribute("catLicMedicaCaracteres", licenciaMedicaService.getCatLicMedicaCaracteres());
		session.setAttribute("catLicMedicaTiposServicio", licenciaMedicaService.getCatLicMedicaTiposServicio());
		ModelAndView model = new ModelAndView("licenciaMedica");
		licenciaMedicaService.inicializaForm(licenciaMedicaForm);
		model.addObject("licenciaMedicaForm", licenciaMedicaForm);
		return model;
	}
	
	@RequestMapping(value = "/verificaFechas")
	public @ResponseBody FechaLicenciaMedica verificaFechas(@RequestParam(value = "vigenteDesde") String vigenteDesde,
									   						@RequestParam(value = "vigenteHasta") String vigenteHasta) {
		//log.info("verificaFechas");	
		FechaLicenciaMedica fechaLicenciaMedica = licenciaMedicaService.verificaFechas(vigenteDesde,vigenteHasta);

		return fechaLicenciaMedica;
	}
	
	@RequestMapping(value = "/nuevaLicenciaMedica", method = RequestMethod.POST)
	public ModelAndView nuevaLicenciaMedica(@Valid LicenciaMedicaForm licenciaMedicaForm, BindingResult result) {
		//log.info("nuevo");
		ModelAndView model = new ModelAndView();
		model.setViewName("licenciaMedica");
		if (!result.hasErrors()) {
			licenciaMedicaService.save(licenciaMedicaForm);
		}
		model.addObject("licenciaMedicaForm", licenciaMedicaForm);
		
		return model; 
	}
	
	@RequestMapping(value = "/licenciasMedicas")
	public ModelAndView licenciasMedicas(LicenciaMedicaForm licenciaMedicaForm) {
		//log.info("verificaFechas");	
		licenciaMedicaService.getLicenciasMedicas(licenciaMedicaForm);
		ModelAndView model = new ModelAndView();
		model.setViewName("licenciasMedicas");
		model.addObject("licenciaMedicaForm", licenciaMedicaForm);
		return model;
	}
}
