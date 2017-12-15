package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.AntecedentesPersonalesForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.service.IMedicoAntecedentesPersonalesService;
import com.mx.sab.service.ISignosService;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class MedicoAntecedentesPersonalesController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IMedicoAntecedentesPersonalesService medicoAntecedentesPersonalesService;
	
	@Autowired
	private ISignosService signosService;
	
	@RequestMapping(value = "/inicioAntecedentesPersonales")
	public ModelAndView inicioAntecedentesPersonales(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		//log.info("inicioAntecedentesPersonalesForm ");	
		ModelAndView model = new ModelAndView("medicoAntecedentesPersonales");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		session.setAttribute("medicoAtencionPacientesForm",medicoAtencionPacientesForm);
		AntecedentesPersonalesForm antecedentesPersonalesForm = new AntecedentesPersonalesForm();
		antecedentesPersonalesForm = medicoAntecedentesPersonalesService.getAntecedentesPersonales(medicoAtencionPacientesForm, userInfo);
		model.addObject("medicoAtencionPacientesForm", medicoAtencionPacientesForm);
		model.addObject("antecedentesPersonalesForm", antecedentesPersonalesForm);
		model.addObject("tomaSignosVo", session.getAttribute("infoSignos"));
		
		return model;
	}
	
	@RequestMapping("/guardarAntecedentesPersonales")
	public @ResponseBody AntecedentesPersonalesForm guardarAntecedentesPersonales(AntecedentesPersonalesForm antecedentesPersonalesForm) {

		//log.info("guardarAntecedentesPersonales");	
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		medicoAntecedentesPersonalesService.guardarAntecedentesPersonales(antecedentesPersonalesForm, userInfo);
		return antecedentesPersonalesForm;
	}
	
}
