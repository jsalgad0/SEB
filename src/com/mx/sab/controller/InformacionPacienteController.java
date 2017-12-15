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

import com.mx.sab.form.InformacionPacienteForm;
import com.mx.sab.form.validator.InformacionPacienteValidator;
import com.mx.sab.model.Catescolaridad;
import com.mx.sab.model.Catestadocivil;
import com.mx.sab.model.Catestados;
import com.mx.sab.model.Catgrupossanguineos;
import com.mx.sab.model.Catocupacion;
import com.mx.sab.service.IGenericService;
import com.mx.sab.service.IInformacionPacienteService;

@Controller
@Log4j2
@RequestMapping("/informacionPaciente")
public class InformacionPacienteController {

	@Autowired private HttpSession session;
	@Autowired IGenericService genericService;
	@Autowired IInformacionPacienteService informacionPacienteService;
	
	@InitBinder("informacionPacienteForm")
	protected void informacionPacienteBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new InformacionPacienteValidator());
	}

	@RequestMapping(value = "/inicio")
	public ModelAndView inicio(@RequestParam(value = "idAgenda") int idAgenda) {
		//log.info("informacion paciente");	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("informacionPaciente");
		InformacionPacienteForm informacionPacienteForm = new InformacionPacienteForm();
		informacionPacienteForm.setIdAgenda(idAgenda);
		informacionPacienteForm = informacionPacienteService.getForm(informacionPacienteForm);
		Collection<Catestados> estados = genericService.getCatEstados();
		Collection<Catestadocivil> estadoCivil = genericService.getEstadoCivil();
		Collection<Catescolaridad> escolaridad = genericService.getEscolaridad();
		Collection<Catocupacion> ocupacion = informacionPacienteService.getOcupacion();
		Collection<Catgrupossanguineos> grupoSanguineo = informacionPacienteService.getGrupoSanguineo();
		modelAndView.addObject("estados", estados);
		modelAndView.addObject("estadoCivil", estadoCivil);
		modelAndView.addObject("escolaridad", escolaridad);
		modelAndView.addObject("ocupacion", ocupacion);
		modelAndView.addObject("grupoSanguineo", grupoSanguineo);
		session.setAttribute("catEstados", estados);
		session.setAttribute("estadoCivil", estadoCivil);
		session.setAttribute("escolaridad", escolaridad);
		session.setAttribute("ocupacion", ocupacion);
		session.setAttribute("grupoSanguineo", grupoSanguineo);
		modelAndView.addObject("informacionPacienteForm", informacionPacienteForm);
		return modelAndView; 
	}
	
	@RequestMapping(value = "/registro")
	public ModelAndView registro(@Valid InformacionPacienteForm informacionPacienteForm, BindingResult result) {
		//log.info("registro informacion paciente");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("informacionPaciente");
//		if(result.hasErrors()){
//			
//		}else{
			informacionPacienteService.save(informacionPacienteForm);
//		}
		Collection<Catestados> estados = genericService.getCatEstados();
		Collection<Catestadocivil> estadoCivil = genericService.getEstadoCivil();
		Collection<Catescolaridad> escolaridad = genericService.getEscolaridad();
		Collection<Catocupacion> ocupacion = informacionPacienteService.getOcupacion();
		Collection<Catgrupossanguineos> grupoSanguineo = informacionPacienteService.getGrupoSanguineo();
		modelAndView.addObject("estados", estados);
		modelAndView.addObject("estadoCivil", estadoCivil);
		modelAndView.addObject("escolaridad", escolaridad);
		modelAndView.addObject("ocupacion", ocupacion);
		modelAndView.addObject("grupoSanguineo", grupoSanguineo);
		session.setAttribute("catEstados", estados);
		session.setAttribute("estadoCivil", estadoCivil);
		session.setAttribute("escolaridad", escolaridad);
		session.setAttribute("ocupacion", ocupacion);
		session.setAttribute("grupoSanguineo", grupoSanguineo);
		modelAndView.addObject("informacionPacienteForm", informacionPacienteForm);
		return modelAndView; 
	}
	
}
