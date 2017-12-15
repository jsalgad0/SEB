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

import com.mx.sab.form.AtencionOtrosDiagnosticosForm;
import com.mx.sab.model.Cattipodiagnostico;
import com.mx.sab.service.IAtencionOtrosDiagnosticosService;
import com.mx.sab.vo.DiagnosticoVo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class AtencionOtrosDiagnosticosController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IAtencionOtrosDiagnosticosService atencionOtrosDiagnosticosService;

	public ModelAndView inicioAtencionOtrosDiagnosticos(AtencionOtrosDiagnosticosForm atencionOtrosDiagnosticosForm) {
		//log.info("inicioHistorialDiagnostico");
		ModelAndView model = new ModelAndView("atencionOtrosDiagnosticos");
		Collection<DiagnosticoVo> diagnosticoVos = atencionOtrosDiagnosticosService.getDiagnosticos(atencionOtrosDiagnosticosForm);
		Collection<Cattipodiagnostico> cattipodiagnosticos = atencionOtrosDiagnosticosService.getCatTipoDiagnosticos();
		session.setAttribute("diagnosticoVosAux", diagnosticoVos);
		model.addObject("cattipodiagnosticos", cattipodiagnosticos);
		model.addObject("atencionOtrosDiagnosticosForm", atencionOtrosDiagnosticosForm);
		model.addObject("diagnosticoVos", diagnosticoVos);
		return model;
	}
	
	@RequestMapping(value = "/diagnostico")
	public @ResponseBody Collection<DiagnosticoVo> diagnostico(@RequestParam(value = "term") String busqueda) {
		//log.info("diagnostico");
		Collection<DiagnosticoVo> diagnosticoVos = atencionOtrosDiagnosticosService.getDiagnosticos(busqueda);
		return diagnosticoVos; 
	}	
	
	@RequestMapping(value = "/saveDiagnosticos")
	public @ResponseBody AtencionOtrosDiagnosticosForm saveDiagnosticos(AtencionOtrosDiagnosticosForm atencionOtrosDiagnosticosForm) {
		//log.info("saveDiagnostico");
		Collection<DiagnosticoVo> diagnosticoVos = (Collection<DiagnosticoVo>) session.getAttribute("diagnosticoVosAux");
		atencionOtrosDiagnosticosService.saveDiagnosticos(atencionOtrosDiagnosticosForm, diagnosticoVos);
		return atencionOtrosDiagnosticosForm; 
	}		
}
