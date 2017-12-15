package com.mx.sab.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.apache.axis2.databinding.types.soapencoding.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.CitasPresencialesForm;
import com.mx.sab.form.RecepcionAtencionesPendientesForm;
import com.mx.sab.form.RecuperarAtencionForm;
import com.mx.sab.service.IPrestacionesService;
import com.mx.sab.service.IRecepcionAtencionesPendientesService;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.PermisoEspecialVo;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/recepcion")
public class RecepcionAtencionesPendientesController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private IRecepcionAtencionesPendientesService recepcionAtencionesPendientesService; 
	
	@Autowired
	private IPrestacionesService prestacionesService;
	
	
	@RequestMapping("/recepcionAtencionesPendientes")
	public ModelAndView inicioAtencionesPendientes(RecepcionAtencionesPendientesForm recepcionAtencionesPendientesForm) {
		ModelAndView modelAndView = new ModelAndView("recepcionInicioAtencionesPendientes");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");		
		session.setAttribute("atencionesPendientes", recepcionAtencionesPendientesService.getAtencionesPendientes(recepcionAtencionesPendientesForm,userInfo));
		modelAndView.addObject("recepcionAtencionesPendientesForm", recepcionAtencionesPendientesForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/paginador")
	public @ResponseBody Collection<PermisoEspecialVo> paginador(@RequestParam(value = "page") int page) {
		//log.info("paginador");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<PermisoEspecialVo> permisoEspecialVos = recepcionAtencionesPendientesService.getAtencionesPendientes(page, userInfo);
		session.setAttribute("atencionesPendientes", permisoEspecialVos);
		return permisoEspecialVos; 
	}
	
	@RequestMapping("/recuperarAtencionesPendientes")
	public ModelAndView recuperarAtencion(RecepcionAtencionesPendientesForm recepcionAtencionesPendientesForm) {
		ModelAndView modelAndView = new ModelAndView("recuperarAtencionesPendientes");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		RecuperarAtencionForm recuperarForm = recepcionAtencionesPendientesService.getAtencionById(recepcionAtencionesPendientesForm.getIdAtencionMedica());
		session.setAttribute("recuperaAtencionForm", recuperarForm);
		modelAndView.addObject("recuperarAtencionForm", recuperarForm);		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/valorizarPendientes")
	public @ResponseBody RecuperarAtencionForm valorizar(RecuperarAtencionForm recuperarAtencionForm) {
		//log.info("valorizar");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		recepcionAtencionesPendientesService.valorizar(recuperarAtencionForm, userInfo);
		return recuperarAtencionForm;
	}
		
	@RequestMapping("/actualizarAtencionPendiente")
	public @ResponseBody RecuperarAtencionForm guardarAtencionPendiente(RecuperarAtencionForm recuperarAtencionForm) {
		//log.info("guardarAtencionPendiente");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		recepcionAtencionesPendientesService.updateAtencion(recuperarAtencionForm, userInfo);		
		return recuperarAtencionForm;
	}
	
	

}
