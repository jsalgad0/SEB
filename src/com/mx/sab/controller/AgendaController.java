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

import com.mx.sab.form.AgendaForm;
import com.mx.sab.form.AtencionMedicaForm;
import com.mx.sab.form.PersonaConfianzaForm;
import com.mx.sab.form.validator.PersonaConfianzaValidator;
import com.mx.sab.service.IAgendaService;
import com.mx.sab.service.IConveniosService;
import com.mx.sab.service.IGenericService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.vo.AgendaVo;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/agenda")
public class AgendaController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IAgendaService agendaService;
	
	@Autowired
	private IGenericService genericService;
	
	@Autowired
	private IConveniosService conveniosService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@InitBinder("personaConfianzaForm")
	protected void personaConfianzaFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new PersonaConfianzaValidator());
	}

	@RequestMapping(value = "/agenda")
	public ModelAndView agenda(AgendaForm agendaForm) {
		//log.info("lugarAtencion");	
		ModelAndView model = new ModelAndView();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		session.setAttribute("agendas", agendaService.getAgenda(agendaForm, userInfo));
		session.setAttribute("medicos", agendaService.getMedicos(userInfo));
		model.setViewName("agenda");
		model.addObject("agendaForm", agendaForm);
		return model; 
	}

	@RequestMapping(value = "/paginador")
	public @ResponseBody Collection<AgendaVo> paginador(@RequestParam(value = "busquedaM") String busquedaM,
														@RequestParam(value = "busquedaA") String busquedaA,
														@RequestParam(value = "busquedaF") String busquedaF,
														@RequestParam(value = "busquedaE") String busquedaE,
														@RequestParam(value = "page") int page) {
		//log.info("paginador");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<AgendaVo> agendaVos = agendaService.getAgenda(busquedaM,busquedaA,busquedaF,busquedaE,page,userInfo);
		session.setAttribute("agendas", agendaVos);
		return agendaVos; 
	}	
	
	@RequestMapping(value = "/inicioPersonaConfianza")
	public ModelAndView inicioPersonaConfianza(PersonaConfianzaForm personaConfianzaForm) {
		//log.info("inicioPersonaConfianza");
		ModelAndView model = new ModelAndView();
		session.setAttribute("tipoIdentificador", genericService.getTipoIdentificadorPersonaConfianza());
		session.setAttribute("personasConfianza", agendaService.getPersonasConfianza(personaConfianzaForm));
		model.addObject("personaConfianzaForm", personaConfianzaForm);
		model.setViewName("personaConfianzaNuevo");		
		return model;
	}	
	
	@RequestMapping(value = "/nuevoPersonaConfianza", method = RequestMethod.POST)
	public ModelAndView nuevoPersonaConfianza(@Valid PersonaConfianzaForm personaConfianzaForm, BindingResult result) {
		//log.info("nuevo");
		
		ModelAndView model = new ModelAndView();
		
		model.setViewName("personaConfianzaNuevo");
		
		if (!result.hasErrors()) {
			agendaService.save(personaConfianzaForm);	
			if (!personaConfianzaForm.isBanderaError()) {
				int idAgenda = personaConfianzaForm.getIdAgenda();
				int idPersona = personaConfianzaForm.getPersonasdeconfianza().getPersonaId();
				int idAfiliado = personaConfianzaForm.getIdAfiliado();
				personaConfianzaForm = new PersonaConfianzaForm();
				personaConfianzaForm.setIdAgenda(idAgenda);
				personaConfianzaForm.setIdPersona(idPersona);
				personaConfianzaForm.setIdAfiliado(idAfiliado);
				session.setAttribute("personasConfianza", agendaService.getPersonasConfianza(personaConfianzaForm));
			}
		}
		
		model.addObject("personaConfianzaForm", personaConfianzaForm);
		
		return model; 
	}
	
	@RequestMapping(value = "/recepcion")
	public ModelAndView recepcion(@RequestParam(value = "idAgenda") int idAgenda,
								  @RequestParam(value = "idPersona") int idPersona) {
		//log.info("recepcion");
		ModelAndView modelAndView = new ModelAndView("recepcion");
		AtencionMedicaForm atencionMedicaForm = new AtencionMedicaForm();
		atencionMedicaForm.setIdPersona(idPersona);
		atencionMedicaForm.setIdAgenda(idAgenda);
		agendaService.inicializaAtencionMedicaForm(atencionMedicaForm); 
		session.setAttribute("catPrestacion", atencionMedicaForm.getCatPrestacion());
		session.setAttribute("prestacionPrestador", atencionMedicaForm.getPrestacionprestador());
		session.setAttribute("afiliadotipoidentificador", atencionMedicaForm.getAfiliadotipoidentificador());
		session.setAttribute("agenda", atencionMedicaForm.getAgenda());
		session.setAttribute("convenios", atencionMedicaForm.getConvenios());
		modelAndView.addObject("atencionMedicaForm", atencionMedicaForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/recepcionPresencial")
	public ModelAndView recepcionPresencial(AgendaForm agendaForm) {
		//log.info("recepcion");
		ModelAndView modelAndView = new ModelAndView("recepcionPresencial");
		AtencionMedicaForm atencionMedicaForm = new AtencionMedicaForm();
		atencionMedicaForm.setIdAgenda(agendaForm.getIdAgenda());
		agendaService.inicializaAtencionMedicaForm(atencionMedicaForm); 
		session.setAttribute("catPrestacion", atencionMedicaForm.getCatPrestacion());
		session.setAttribute("prestacionPrestador", atencionMedicaForm.getPrestacionprestador());
		session.setAttribute("afiliadotipoidentificador", atencionMedicaForm.getAfiliadotipoidentificador());
		session.setAttribute("agenda", atencionMedicaForm.getAgenda());
		session.setAttribute("convenios", atencionMedicaForm.getConvenios());
		modelAndView.addObject("atencionMedicaForm", atencionMedicaForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/nuevoAtencionMedica", method = RequestMethod.POST)
	public ModelAndView nuevoAtencionMedica(AtencionMedicaForm atencionMedicaForm) {
		//log.info("recepcion");
		ModelAndView modelAndView = new ModelAndView("recepcion");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		agendaService.save(atencionMedicaForm, userInfo);
		if (!atencionMedicaForm.isBanderaError()) {
			atencionMedicaForm = new AtencionMedicaForm();		
		}
		modelAndView.addObject("atencionMedicaForm", atencionMedicaForm);
		return modelAndView;
	}
	
}
