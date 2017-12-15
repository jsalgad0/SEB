package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.SolicitudReferenciaForm;
import com.mx.sab.service.ILugarAtencionService;
import com.mx.sab.service.IAtencionSolicitudReferenciaService;
import com.mx.sab.service.IUsuarioService;

@Controller
@Log4j2
@RequestMapping("/medico")
public class AtencionSolicitudReferenciaController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IAtencionSolicitudReferenciaService atencionSolicitudReferenciaService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired 
	private ILugarAtencionService lugarAtencionService;
	
	public ModelAndView inicioSolicitudReferencia(SolicitudReferenciaForm solicitudReferenciaForm) {
		ModelAndView model = new ModelAndView("atencionSolicitudReferencia");
		atencionSolicitudReferenciaService.inicializaSolicitudReferenciaForm(solicitudReferenciaForm);
		session.setAttribute("catSoliReferenciaMotivos", atencionSolicitudReferenciaService.getCatSoliReferenciaMotivos());
		session.setAttribute("catSoliRefrerenciaPor", atencionSolicitudReferenciaService.getCatSoliRefrerenciaPor());
		session.setAttribute("lugaresAtencion", lugarAtencionService.getLugaresAtencion());
		session.setAttribute("especialidades", usuarioService.getEspecialidades());
		model.addObject("solicitudReferenciaForm", solicitudReferenciaForm);
		return model;
	}
	
	@RequestMapping(value = "/saveSolicitudReferencia")
	public @ResponseBody SolicitudReferenciaForm saveSolicitudReferencia(SolicitudReferenciaForm solicitudReferenciaForm) {
		atencionSolicitudReferenciaService.save(solicitudReferenciaForm);
		return solicitudReferenciaForm; 
	}
	
	
}
