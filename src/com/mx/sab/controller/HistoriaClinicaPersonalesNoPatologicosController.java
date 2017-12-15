package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.HistoriaClinicaPersonalesNoPatologicosForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.model.Afiliadodemografico;
import com.mx.sab.service.IHistoriaClinicaPersonalesNoPatologicosService;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class HistoriaClinicaPersonalesNoPatologicosController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private IHistoriaClinicaPersonalesNoPatologicosService historiaClinicaPersonalesNoPatologicosService;

	@RequestMapping(value = "/inicioPersonalesNoPatologicos")
	public ModelAndView inicioPersonalesNoPatologicos(HistoriaClinicaPersonalesNoPatologicosForm historiaClinicaPersonalesNoPatologicosForm) {
		//log.info("inicioPersonalesNoPatologicos");	
		ModelAndView model = new ModelAndView("historiaClinicaPersonalesNoPatologicos");
		historiaClinicaPersonalesNoPatologicosService.inicializarForm(historiaClinicaPersonalesNoPatologicosForm);
		model.addObject("tiposSangre", historiaClinicaPersonalesNoPatologicosService.getTipoSangre());
		model.addObject("escolaridades", historiaClinicaPersonalesNoPatologicosService.getEscolaridades());
		model.addObject("estadosCiviles", historiaClinicaPersonalesNoPatologicosService.getEstadosCiviles());
		model.addObject("alimentaciones", historiaClinicaPersonalesNoPatologicosService.getAlimentaciones());
		model.addObject("higienePersonales", historiaClinicaPersonalesNoPatologicosService.getHigienePersonales());
		model.addObject("historiaClinicaPersonalesNoPatologicosForm", historiaClinicaPersonalesNoPatologicosForm);
		return model;
	}
	
	

	@RequestMapping(value = "/saveHistoriaClinicaPersonalesNoPatologicos")
	public @ResponseBody HistoriaClinicaPersonalesNoPatologicosForm saveHistoriaClinicaPersonalesNoPatologicos(HistoriaClinicaPersonalesNoPatologicosForm historiaClinicaPersonalesNoPatologicosForm) {
		//log.info("saveHistoriaClinicaPersonalesNoPatologicos");	
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		historiaClinicaPersonalesNoPatologicosService.saveHistoriaClinicaPersonalesNoPatologicos(historiaClinicaPersonalesNoPatologicosForm,userInfo);
		return historiaClinicaPersonalesNoPatologicosForm;
	}
}
