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

import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.service.IMedicoAtencionPacientesService;
import com.mx.sab.vo.AfiliadoVo;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class MedicoAtencionPacientesController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IMedicoAtencionPacientesService medicoAtencionPacientesService;
	
	@RequestMapping(value = "/inicioAtencionPacientes")
	public ModelAndView inicioAtencionPacientes(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		//log.info("inicioAtencionPacientes");	
		ModelAndView model = new ModelAndView("medicoInicioAtencionPacientes");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		session.setAttribute("afiliados", medicoAtencionPacientesService.getAfiliados(medicoAtencionPacientesForm,userInfo));
		model.addObject("medicoAtencionPacientesForm", medicoAtencionPacientesForm);
		return model;
	}
	
	@RequestMapping(value = "/paginador")
	public @ResponseBody Collection<AfiliadoVo> paginador(@RequestParam(value = "busquedaApellidoPaterno") String busquedaApellidoPaterno,
														  @RequestParam(value = "fecha") String fecha,
														  @RequestParam(value = "idEstatus") int idEstatus,
													      @RequestParam(value = "page") int page) {
		//log.info("paginador");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<AfiliadoVo> afiliados = medicoAtencionPacientesService.getAfiliados(busquedaApellidoPaterno, fecha, idEstatus, page, userInfo);
		return afiliados; 
	}		
}
