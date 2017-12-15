package com.mx.sab.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.form.SignosVitalesForm;
import com.mx.sab.form.validator.SignosVitalesValidator;
import com.mx.sab.service.IHistoriaClinicaPersonalesNoPatologicosService;
import com.mx.sab.service.ISignosService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.vo.TomaSignosVo;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class AtencionSignosController {

	@Autowired
	private HttpSession session;
	
	@Autowired 
	private IUsuarioService usuarioService;
	
	@Autowired
	private IHistoriaClinicaPersonalesNoPatologicosService historiaClinicaPersonalesNoPatologicosService;
	
	@Autowired 
	private ISignosService signosService;
	
	@InitBinder("signosVitalesForm")
	protected void signosVitalesFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new SignosVitalesValidator());
	}
	
	@RequestMapping(value = "/tomaSignos")
	public ModelAndView inicioTomaSignos(SignosVitalesForm signosVitalesForm) {
		//log.info("inicioTomaSignos");
		ModelAndView modelAndView = new ModelAndView("atencionSignosVitales");
		signosService.getAtencionTomaSignos(signosVitalesForm);
		modelAndView.addObject("signosVitalesForm", signosVitalesForm);
		modelAndView.addObject("tiposSangre", historiaClinicaPersonalesNoPatologicosService.getTipoSangre());
		return modelAndView; 
	}
	
	@RequestMapping(value = "/nuevo")
	public ModelAndView tomaSignos(@Valid SignosVitalesForm signosVitalesForm, BindingResult result) {
		//log.info("nuevoSignos");
		ModelAndView modelAndView = new ModelAndView("atencionSignosVitales");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if(!result.hasErrors()){
			signosService.saveAtencionSignosVitales(signosVitalesForm, userInfo);
		}else{
			signosVitalesForm.setEditar(false);
		}
		modelAndView.addObject("signosVitalesForm", signosVitalesForm);
		MedicoAtencionPacientesForm medicoAtencionPacientesForm = new MedicoAtencionPacientesForm();
		medicoAtencionPacientesForm.setIdAtencion(signosVitalesForm.getIdAtencion());
		modelAndView.addObject("medicoAtencionPacientesForm", medicoAtencionPacientesForm);
		modelAndView.addObject("tiposSangre", historiaClinicaPersonalesNoPatologicosService.getTipoSangre());
		return modelAndView;
	}

}
