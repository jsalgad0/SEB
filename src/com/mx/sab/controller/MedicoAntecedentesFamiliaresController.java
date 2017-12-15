package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.AntecedentesFamiliaresForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.service.IMedicoAntecedentesFamiliaresService;
import com.mx.sab.service.ISignosService;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class MedicoAntecedentesFamiliaresController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IMedicoAntecedentesFamiliaresService medicoAntecedentesFamiliaresService;
	
	@Autowired
	private ISignosService signosService;
	
	@RequestMapping(value = "/inicioAntecedentesFamiliares")
	public ModelAndView inicioAntecedentesFamiliares(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		//log.info("inicioAntecedentesFamiliaresForm");	
		ModelAndView model = new ModelAndView("medicoAntecedentesFamiliares");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		session.setAttribute("medicoAtencionPacientesForm",medicoAtencionPacientesForm);
		AntecedentesFamiliaresForm antecedentesFamiliaresForm = new AntecedentesFamiliaresForm();
		antecedentesFamiliaresForm = medicoAntecedentesFamiliaresService.getAntecedentesFamiliares(medicoAtencionPacientesForm, userInfo);
		model.addObject("medicoAtencionPacientesForm", medicoAtencionPacientesForm);
		model.addObject("antecedentesFamiliaresForm", antecedentesFamiliaresForm);
		model.addObject("tomaSignosVo", session.getAttribute("infoSignos"));
		
		return model;
	}
	
	@RequestMapping("/guardarAntecedentesFamiliares")
	public @ResponseBody AntecedentesFamiliaresForm guardarAntecedentesFamiliares(AntecedentesFamiliaresForm antecedentesFamiliaresForm) {

		//log.info("guardarAntecedentesFamiliares");	
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		medicoAntecedentesFamiliaresService.guardarAntecedentesFamiliares(antecedentesFamiliaresForm, userInfo);
		return antecedentesFamiliaresForm;
	}
	
}
