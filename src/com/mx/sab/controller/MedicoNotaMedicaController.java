package com.mx.sab.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.form.NotaMedicaForm;
import com.mx.sab.model.Cattipodiagnostico;
import com.mx.sab.service.IMedicoNotaMedicaService;
import com.mx.sab.vo.DiagnosticoVo;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class MedicoNotaMedicaController {

	@Autowired
	private HttpSession session;	
	
	@Autowired
	private IMedicoNotaMedicaService notaMedicaService;	
	
	@RequestMapping(value = "/inicioNotaMedica")
	public ModelAndView inicioNotaMedica(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		//log.info("inicioNotaMedica");	
		ModelAndView model = new ModelAndView("medicoInicioNotaMedica");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		session.setAttribute("medicoAtencionPacientesForm",medicoAtencionPacientesForm);
		NotaMedicaForm notaMedicaForm = notaMedicaService.getNotaMedica(medicoAtencionPacientesForm, userInfo);
		Collection<Cattipodiagnostico> cattipodiagnosticos = notaMedicaService.getCatTipoDiagnosticos();
		model.addObject("cattipodiagnosticos", cattipodiagnosticos);
		model.addObject("medicoAtencionPacientesForm", medicoAtencionPacientesForm);
		model.addObject("notaMedicaForm", notaMedicaForm);
		model.addObject("tomaSignosVo", session.getAttribute("infoSignos"));
		return model;
	}
	
	@RequestMapping("/guardarNotaMedica")
	public @ResponseBody NotaMedicaForm guardarNotaMedica(NotaMedicaForm notaMedicaForm) {

		//log.info("guardarNotaMedicaForm");	
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		notaMedicaService.guardarNotaMedica(notaMedicaForm, userInfo);
		return notaMedicaForm;
	}
	
	@RequestMapping(value = "/diagnosticoNotaMedica")
	public @ResponseBody Collection<DiagnosticoVo> diagnostico(@RequestParam(value = "term") String busqueda) {
		//log.info("diagnosticoNotaMedica");
		Collection<DiagnosticoVo> diagnosticoVos = notaMedicaService.getDiagnosticos(busqueda);
		return diagnosticoVos; 
	}	
	
}
