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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.HistoriaClincaForm;
import com.mx.sab.form.validator.HistoriaClinicaValidator;
import com.mx.sab.model.Catalimentacion;
import com.mx.sab.model.Catescolaridad;
import com.mx.sab.model.Catestadocivil;
import com.mx.sab.model.Cathigiene;
import com.mx.sab.model.Catmpf;
import com.mx.sab.model.Cattipofamilia;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.service.IGenericService;
import com.mx.sab.service.IHistoriaClinicaService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/historiaClinica")
public class HistoriaClinicaController {

	@Autowired private HttpSession session;
	@Autowired IGenericService genericService;
	@Autowired IHistoriaClinicaService historiaClinicaService;
	@Autowired private IUsuarioService usuarioService;
	
	@InitBinder("historiaClincaForm")
	protected void historiaClinicaBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new HistoriaClinicaValidator());
	}

	@RequestMapping(value = "/inicio")
	public ModelAndView inicio(@RequestParam(value = "idAgenda") int idAgenda, HistoriaClincaForm historiaClincaForm) {
		//log.info("historia clinica");	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("historiaClinica");
		Collection<Catestadocivil> estadoCivil = genericService.getEstadoCivil();
		Collection<Catescolaridad> escolaridad = genericService.getEscolaridad();
		Collection<Catalimentacion> alimentacion = historiaClinicaService.getAlimentacion();
		Collection<Cathigiene> higiene = historiaClinicaService.getHigiene();
		Collection<Catmpf> mpf = historiaClinicaService.getMpf();
		Collection<Cattipofamilia> familia = historiaClinicaService.getFamilia();
		Signosvitales signosvitales = new Signosvitales();
		modelAndView.addObject("estadoCivil", estadoCivil);
		modelAndView.addObject("escolaridad", escolaridad);
		modelAndView.addObject("alimentacion", alimentacion);
		modelAndView.addObject("higiene", higiene);
		modelAndView.addObject("mpf", mpf);
		modelAndView.addObject("familia", familia);
		modelAndView.addObject("signosVitales", signosvitales);
		session.setAttribute("estadoCivil", estadoCivil);
		session.setAttribute("escolaridad", escolaridad);
		session.setAttribute("catalimentacion", alimentacion);
		session.setAttribute("higiene", higiene);
		session.setAttribute("mpf", mpf);
		session.setAttribute("familia", familia);
		session.setAttribute("signosVitales", signosvitales);
		historiaClincaForm = new HistoriaClincaForm();
		historiaClincaForm.setIdAgenda(idAgenda);
		historiaClincaForm = historiaClinicaService.getHistoria(historiaClincaForm);
		modelAndView.addObject("historiaClincaForm", historiaClincaForm);
		return modelAndView; 
	}
	
	@RequestMapping(value = "/registro")
	public ModelAndView registro(@Valid HistoriaClincaForm clincaForm, BindingResult result) {
		//log.info("registro historia clinica");
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
//		if(result.hasErrors()){
//			
//		}else{
			historiaClinicaService.saveHistoria(clincaForm, userInfo);
//		}
		modelAndView.setViewName("historiaClinica");
		Collection<Catestadocivil> estadoCivil = genericService.getEstadoCivil();
		Collection<Catescolaridad> escolaridad = genericService.getEscolaridad();
		Collection<Catalimentacion> alimentacion = historiaClinicaService.getAlimentacion();
		Collection<Cathigiene> higiene = historiaClinicaService.getHigiene();
		Collection<Catmpf> mpf = historiaClinicaService.getMpf();
		Collection<Cattipofamilia> familia = historiaClinicaService.getFamilia();
		modelAndView.addObject("estadoCivil", estadoCivil);
		modelAndView.addObject("escolaridad", escolaridad);
		modelAndView.addObject("alimentacion", alimentacion);
		modelAndView.addObject("higiene", higiene);
		modelAndView.addObject("mpf", mpf);
		modelAndView.addObject("familia", familia);
		session.setAttribute("estadoCivil", estadoCivil);
		session.setAttribute("escolaridad", escolaridad);
		session.setAttribute("catalimentacion", alimentacion);
		session.setAttribute("higiene", higiene);
		session.setAttribute("mpf", mpf);
		session.setAttribute("familia", familia);
		return modelAndView; 
	}
	
}
