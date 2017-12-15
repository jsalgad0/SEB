package com.mx.sab.controller;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.CitasPresencialesForm;
import com.mx.sab.form.RecepcionAtencionesPendientesForm;
import com.mx.sab.form.RecepcionBuscarAtencionForm;

@Controller
@Log4j2
@RequestMapping("/recepcion")
public class RecepcionController {
	
	@Autowired
	private CitasPresencialesController citasPresencialesController;

	@Autowired
	private RecepcionAtencionesPendientesController recepcionAtencionesPendientesController; 
	
	@Autowired
	private RecepcionBuscarAtencionController recepcionBuscarAtencionController; 
	
	@RequestMapping(value = "/inicioRecepcion")
	public ModelAndView inicioRecepcion() {
		//log.info("inicio recepcion");
		return citasPresencialesController.recepcionPresencial(new CitasPresencialesForm());
	}
	
	@RequestMapping(value = "/inicioRecepcionAtencionesPendientes")
	public ModelAndView atencionesPendientes() {
		return recepcionAtencionesPendientesController.inicioAtencionesPendientes(new RecepcionAtencionesPendientesForm());
	}
	
	//recepcionInicioAtencionesPendientes
	@RequestMapping(value = "/inicioRecepcionBuscarAtencion")
	public ModelAndView buscarAtencion() {
		return recepcionBuscarAtencionController.inicioBuscarAtencion(new RecepcionBuscarAtencionForm());
	}
}
