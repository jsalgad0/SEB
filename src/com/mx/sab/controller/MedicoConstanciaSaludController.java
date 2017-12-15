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
import com.mx.sab.form.MedicoLicenciaMedicaForm;
import com.mx.sab.form.NotaMedicaForm;
import com.mx.sab.model.Cattipodiagnostico;
import com.mx.sab.service.IMedicoLicenciaMedicaService;
import com.mx.sab.service.IMedicoNotaMedicaService;
import com.mx.sab.vo.DiagnosticoVo;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class MedicoConstanciaSaludController {

	@Autowired
	private HttpSession session;	
	
	@RequestMapping(value = "/inicioConstanciaSalud")
	public ModelAndView inicioConstanciaSalud(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		//log.info("inicioConstanciaSalud");	
		ModelAndView model = new ModelAndView("atencionConstanciaSalud");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		session.setAttribute("medicoAtencionPacientesForm",medicoAtencionPacientesForm);
		//MedicoLicenciaMedicaForm medicoLicenciaMedicaForm = medicoLicenciaMedicaService.getLicenciaMedica(medicoAtencionPacientesForm, userInfo);
		model.addObject("medicoAtencionPacientesForm", medicoAtencionPacientesForm);
		//model.addObject("medicoLicenciaMedicaForm", medicoLicenciaMedicaForm);
		model.addObject("tomaSignosVo", session.getAttribute("infoSignos"));
		return model;
	}
	
	/*@RequestMapping("/guardarConstanciaSalud")
	public @ResponseBody MedicoLicenciaMedicaForm guardarLicenciaMedica(MedicoLicenciaMedicaForm medicoLicenciaMedicaForm) {
		//log.info("guardarConstanciaSalud");	
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		medicoLicenciaMedicaService.guardarLicenciaMedica(medicoLicenciaMedicaForm, userInfo);
		return medicoLicenciaMedicaForm;
	}*/	
	
}
