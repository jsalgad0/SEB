package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.CicloVitalForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.service.IMedicoCicloVitalService;
import com.mx.sab.service.ISignosService;
import com.mx.sab.vo.UserInfo;


@Controller
@Log4j2
@RequestMapping("/medico")
public class MedicoCicloVitalController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IMedicoCicloVitalService medicoCicloVitalService;
	
	@Autowired
	private ISignosService signosService;	
	
	@RequestMapping(value = "/inicioCicloVital")
	public ModelAndView inicioCicloVital(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		//log.info("inicioCicloVitalForm ");	
		ModelAndView model = new ModelAndView("medicoCicloVital");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		session.setAttribute("medicoAtencionPacientesForm",medicoAtencionPacientesForm);
		CicloVitalForm cicloVitalForm = new CicloVitalForm();
		cicloVitalForm = medicoCicloVitalService.getCicloVital(medicoAtencionPacientesForm, userInfo);
		model.addObject("medicoAtencionPacientesForm", medicoAtencionPacientesForm);
		model.addObject("cicloVitalForm", cicloVitalForm);
		model.addObject("tipoDeFamilia", medicoCicloVitalService.getCatTipoFamilia());
		model.addObject("tomaSignosVo", session.getAttribute("infoSignos"));
		return model;		
		
	}
	
	@RequestMapping("/guardarCicloVital")
	public @ResponseBody CicloVitalForm guardarCicloVitalForm(CicloVitalForm cicloVitalForm) {
		//log.info("guardarCicloVital");	
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		medicoCicloVitalService.guardarCicloVital(cicloVitalForm, userInfo);
		return cicloVitalForm;
	}
	
}
