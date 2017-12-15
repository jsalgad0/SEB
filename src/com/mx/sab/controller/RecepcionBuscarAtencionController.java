package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.RecepcionBuscarAtencionForm;
import com.mx.sab.service.IPrestacionesService;
import com.mx.sab.service.IRecepcionBuscarAtencionesService;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/recepcion")
public class RecepcionBuscarAtencionController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private IRecepcionBuscarAtencionesService recepcionBuscarAtencionesService; 
	
	@Autowired
	private IPrestacionesService prestacionesService;
	
	
	@RequestMapping("/recepcionBuscarAtencion")
	public ModelAndView inicioBuscarAtencion(RecepcionBuscarAtencionForm recepcionBuscarAtencionForm) {
		ModelAndView modelAndView = new ModelAndView("recepcionInicioBuscarAtencion");			
		modelAndView.addObject("buscarAtencionForm", recepcionBuscarAtencionForm);
		return modelAndView;
	}
	
	@RequestMapping("/buscarAtencion")
	public @ResponseBody RecepcionBuscarAtencionForm buscarAtencion(RecepcionBuscarAtencionForm recepcionBuscarAtencionForm) {
		//log.info("buscarAtencion");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		recepcionBuscarAtencionesService.getAtencionesHoy(recepcionBuscarAtencionForm, userInfo);	
		session.removeAttribute("recepcionBuscarAtencionFormAux");
		session.setAttribute("recepcionBuscarAtencionFormAux", recepcionBuscarAtencionForm);
		return recepcionBuscarAtencionForm;
	}
	

	@RequestMapping("/recepcionListaAtenciones")
	public ModelAndView getListaAtenciones() {
		ModelAndView modelAndView = new ModelAndView("recepcionListaAtenciones");		
		RecepcionBuscarAtencionForm recepcionBuscarAtencionForm = (RecepcionBuscarAtencionForm) session.getAttribute("recepcionBuscarAtencionFormAux");
		modelAndView.addObject("listaAtencionesForm", recepcionBuscarAtencionForm);
		return modelAndView;
	}
}
