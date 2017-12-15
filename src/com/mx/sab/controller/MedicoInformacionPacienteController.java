package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.InformacionPacienteForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.service.IGenericService;
import com.mx.sab.service.IMedicoInformacionPacienteService;

@Controller
@Log4j2
@RequestMapping("/medico")
public class MedicoInformacionPacienteController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private IMedicoInformacionPacienteService medicoInformacionPacienteService;
	
	@Autowired
	private IGenericService genericService;
	
	@RequestMapping(value = "/inicioInformacionPaciente")
	public ModelAndView inicioInformacionPaciente(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		//log.info("inicioInformacionPaciente");
//		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		InformacionPacienteForm informacionPacienteForm = new InformacionPacienteForm();
		informacionPacienteForm.setIdAtencionMedica(medicoAtencionPacientesForm.getIdAtencion());
		ModelAndView model = new ModelAndView("medicoInicioInformacionPaciente");
		Atencionmedica atencionmedica = medicoInformacionPacienteService.getAtencion(medicoAtencionPacientesForm.getIdAtencion());
		session.setAttribute("catEstados", genericService.getCatEstados());
		if(atencionmedica.getAfiliado().getCatestadosByEstadoId() != null){
			session.setAttribute("catMunicipios",genericService.getCatMunicipios(atencionmedica.getAfiliado().getCatestadosByEstadoId().getEstadoId()));
		}
		if(atencionmedica.getAfiliado().getCatmunicipios() != null){
			session.setAttribute("catColonias",genericService.getCatColonias(atencionmedica.getAfiliado().getCatmunicipios().getMunicipioId()));
		}		
		session.setAttribute("catOcupacion",genericService.getOcupacion());
		session.setAttribute("informacionPaciente", medicoInformacionPacienteService.getInformacionPaciente(medicoAtencionPacientesForm.getIdAtencion()));
		model.addObject("informacionPacienteForm", informacionPacienteForm);
		model.addObject("atencion", atencionmedica);		
		session.setAttribute("medicoAtencionPacientesForm",medicoAtencionPacientesForm); 
		model.addObject("signos", medicoInformacionPacienteService.getSignos(medicoAtencionPacientesForm.getIdAtencion()));
		return model;
	}
	
	
	@RequestMapping(value = "/actualizaInformacionPaciente")
	public ModelAndView actualizaInformacionPaciente(InformacionPacienteForm informacionPacienteForm) {
		ModelAndView model = new ModelAndView("medicoInicioInformacionPaciente");
		medicoInformacionPacienteService.updateInformacionPaciente(informacionPacienteForm);
		Atencionmedica atencionmedica = medicoInformacionPacienteService.getAtencion(informacionPacienteForm.getIdAtencionMedica());
		session.setAttribute("catEstados", genericService.getCatEstados());
		if(atencionmedica.getAfiliado().getCatestadosByEstadoId() != null){
			session.setAttribute("catMunicipios",genericService.getCatMunicipios(atencionmedica.getAfiliado().getCatestadosByEstadoId().getEstadoId()));
		}
		if(atencionmedica.getAfiliado().getCatmunicipios() != null){
			session.setAttribute("catColonias",genericService.getCatColonias(atencionmedica.getAfiliado().getCatmunicipios().getMunicipioId()));
		}		
		session.setAttribute("catOcupacion",genericService.getOcupacion());
		session.setAttribute("informacionPaciente", medicoInformacionPacienteService.getInformacionPaciente(informacionPacienteForm.getIdAtencionMedica()));
		model.addObject("informacionPacienteForm", informacionPacienteForm);
		model.addObject("atencion", atencionmedica);		
		model.addObject("medicoAtencionPacientesForm", (MedicoAtencionPacientesForm)session.getAttribute("medicoAtencionPacientesForm"));
		model.addObject("signos", medicoInformacionPacienteService.getSignos(informacionPacienteForm.getIdAtencionMedica()));
		return model;
	}
	
	
}
