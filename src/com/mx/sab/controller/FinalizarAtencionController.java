package com.mx.sab.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.FinalizarAtencionForm;
import com.mx.sab.service.ICertificadosService;
import com.mx.sab.service.IFinalizarAtencionService;

@Controller
@Log4j2
@RequestMapping("/finalizarAtencion")
public class FinalizarAtencionController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IFinalizarAtencionService finalizarAtencionService;
	
	@Autowired
	private ICertificadosService certificadosService;
	
	@Autowired
	private HttpServletResponse response;	
	
	@RequestMapping(value = "/finalizarAtencion")
	public ModelAndView finalizarAtencion(FinalizarAtencionForm finalizarAtencionForm) {
		//log.info("finalizarAtencion");	
		ModelAndView model = new ModelAndView();
		finalizarAtencionService.inicializarForm(finalizarAtencionForm);
		model.setViewName("finalizarAtencion");
		model.addObject("finalizarAtencionForm", finalizarAtencionForm);
		return model; 
	}
	
	@RequestMapping(value = "/finalizar")
	public ModelAndView finalizar(FinalizarAtencionForm finalizarAtencionForm) {
		//log.info("lugarAtencion");	
		ModelAndView model = new ModelAndView();
		int idAuditoria = (int) session.getAttribute("auditoriaId");
		finalizarAtencionForm.setIdAuditoria(idAuditoria);
		finalizarAtencionService.finalizar(finalizarAtencionForm);
		model.setViewName("finalizarAtencion");
		model.addObject("finalizarAtencionForm", finalizarAtencionForm);
		return model; 
	}
	
	@RequestMapping(value = "/imprimirTodo")
	public ModelAndView imprimirTodo(FinalizarAtencionForm finalizarAtencionForm) {
		//log.info("imprimirTodo");	
		try{
			finalizarAtencionService.imprimirTodo(finalizarAtencionForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=ConstanciaDeSalud");
			response.setContentLength(finalizarAtencionForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(finalizarAtencionForm.getFile().toByteArray());	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
