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

import com.mx.sab.form.AtencionEstudiosMedicosForm;
import com.mx.sab.service.IAtencionEstudiosMedicosService;
import com.mx.sab.service.IPrestacionesService;
import com.mx.sab.vo.AutocompleteVo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class AtencionEstudiosMedicosController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IAtencionEstudiosMedicosService atencionEstudiosMedicosService;
	
	@Autowired
	private IPrestacionesService prestacionesService;
	
	public ModelAndView estudiosMedicos(AtencionEstudiosMedicosForm atencionEstudiosMedicosForm) {
		ModelAndView model = new ModelAndView("atencionEstudiosMedicos");
		atencionEstudiosMedicosService.inicializaEstudiosMedicosForm(atencionEstudiosMedicosForm);
		model.addObject("atencionEstudiosMedicosForm", atencionEstudiosMedicosForm);
		return model;
	}
	
	@RequestMapping(value = "/nuevoEstudioMedico")
	public @ResponseBody AtencionEstudiosMedicosForm nuevoEstudioMedico(AtencionEstudiosMedicosForm atencionEstudiosMedicosForm) {
		//log.info("nuevoEstudioMedico");
		atencionEstudiosMedicosService.agregaEstudioMedico(atencionEstudiosMedicosForm);
		return atencionEstudiosMedicosForm;
	}
	
	@RequestMapping(value = "/prestacionEstudios")
	public @ResponseBody Collection<AutocompleteVo> prestacionEstudios(@RequestParam(value = "idAtencion") int idAtencion, @RequestParam(value = "idServicio") int idServicio, @RequestParam(value = "term") String busqueda) {
		//log.info("prestacion");
		Collection<AutocompleteVo> prestacion = prestacionesService.getPrestacionesEstudios(idAtencion, busqueda, idServicio);
		return prestacion; 
	}
}
