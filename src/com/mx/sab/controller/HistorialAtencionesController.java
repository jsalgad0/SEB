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

import com.mx.sab.form.HistorialAtencionesForm;
import com.mx.sab.service.IHistorialAtencionesService;
import com.mx.sab.vo.HistorialAtencionesVo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class HistorialAtencionesController {
	
	@Autowired 
	private HttpSession session;
	
	@Autowired 
	private IHistorialAtencionesService historialAtencionesService;
	
	@RequestMapping(value = "/inicioHistorialAtenciones")
	public ModelAndView inicioHistorialAtenciones(HistorialAtencionesForm historialAtencionesForm) {
		//log.info("historialAtenciones");
		ModelAndView model = new ModelAndView("historialAtenciones");
		Collection<HistorialAtencionesVo> historialAtencionesVos = historialAtencionesService.getHistorial(historialAtencionesForm);
		model.addObject("historialAtencionesVos", historialAtencionesVos);
		model.addObject("historialAtencionesForm", historialAtencionesForm);
		return model; 
	}
	
	@RequestMapping(value = "/paginadorHistorialAtenciones")
	public @ResponseBody Collection<HistorialAtencionesVo> paginadorHistorialAtenciones(@RequestParam(value = "page") int page, @RequestParam(value = "idAfiliado") int idAfiliado) {
		//log.info("paginadorHistorialAtenciones");
		Collection<HistorialAtencionesVo> historialAtencionesVos = historialAtencionesService.getHistorial(idAfiliado, page);
		return historialAtencionesVos;
	}
	
}
