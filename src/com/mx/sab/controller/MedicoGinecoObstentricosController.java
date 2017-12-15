package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.GinecoObstentricosForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.service.IMedicoGinecoObstentricosService;
import com.mx.sab.service.ISignosService;
import com.mx.sab.vo.UserInfo;


@Controller
@Log4j2
@RequestMapping("/medico")
public class MedicoGinecoObstentricosController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IMedicoGinecoObstentricosService medicoGinecoObstentricosService;
	
	@Autowired
	private ISignosService signosService;	
	
	@RequestMapping(value = "/inicioGinecoObstentricos")
	public ModelAndView inicioGinecoObstentricos(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		//log.info("inicioGinecoObstentricosForm ");	
		ModelAndView model = new ModelAndView("medicoGinecoObstentricos");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		session.setAttribute("medicoAtencionPacientesForm",medicoAtencionPacientesForm);
		GinecoObstentricosForm ginecoObstentricosForm = new GinecoObstentricosForm();
		ginecoObstentricosForm = medicoGinecoObstentricosService.getGinecoObstentricos(medicoAtencionPacientesForm, userInfo);
		model.addObject("medicoAtencionPacientesForm", medicoAtencionPacientesForm);
		model.addObject("ginecoObstentricosForm", ginecoObstentricosForm);
		model.addObject("mpfs", medicoGinecoObstentricosService.getCatMpf());
		model.addObject("tomaSignosVo", session.getAttribute("infoSignos"));
		return model;		
		
	}
	
	@RequestMapping("/guardarGinecoObstentricos")
	public @ResponseBody GinecoObstentricosForm guardarGinecoObstentricosForm(GinecoObstentricosForm ginecoObstentricosForm) {
		//log.info("guardarGinecoObstentricos");	
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		medicoGinecoObstentricosService.guardarGinecoObstentricos(ginecoObstentricosForm, userInfo);
		return ginecoObstentricosForm;
	}
	
}
