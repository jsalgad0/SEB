package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.ExploracionFisicaForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.service.IMedicoExploracionFisicaService;
import com.mx.sab.service.ISignosService;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class MedicoExploracionFisicaController {

	@Autowired
	private HttpSession session;	
	
	@Autowired
	private IMedicoExploracionFisicaService medicoExploracionFisicaService;
	
	@Autowired
	private ISignosService signosService;
	
	@RequestMapping(value = "/inicioExploracionFisica")
	public ModelAndView inicioExploracionFisica(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		//log.info("inicioAntecedentesPersonalesForm ");	
		ModelAndView model = new ModelAndView("medicoExploracionFisica");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		session.setAttribute("medicoAtencionPacientesForm",medicoAtencionPacientesForm);
		ExploracionFisicaForm exploracionFisicaForm = new ExploracionFisicaForm();
		exploracionFisicaForm  = medicoExploracionFisicaService.getExploracionFisica(medicoAtencionPacientesForm, userInfo);
		model.addObject("medicoAtencionPacientesForm", medicoAtencionPacientesForm);
		model.addObject("exploracionFisicaForm", exploracionFisicaForm);
		model.addObject("tomaSignosVo", session.getAttribute("infoSignos"));
		return model;
	}
	
	@RequestMapping("/guardarExploracionFisica")
	public @ResponseBody ExploracionFisicaForm guardarExploracionFisica(ExploracionFisicaForm exploracionFisicaForm) {

		//log.info("guardarExploracionFisicaForm");	
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		medicoExploracionFisicaService.guardarExploracionFisica(exploracionFisicaForm, userInfo);
		return exploracionFisicaForm;
	}
	
}
