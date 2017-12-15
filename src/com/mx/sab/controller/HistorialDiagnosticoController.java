package com.mx.sab.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.HistorialPorDiagnosticoForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.service.IHistorialPorDiagnosticoService;
import com.mx.sab.vo.HistorialPorDiagnosticoVo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class HistorialDiagnosticoController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private IHistorialPorDiagnosticoService historialPorDiagnosticoService;

	@RequestMapping(value = "/inicioHistorialDiagnostico")
	public ModelAndView inicioHistorialDiagnostico(HistorialPorDiagnosticoForm historialPorDiagnosticoForm) {
		//log.info("inicioHistorialDiagnostico");
		ModelAndView model = new ModelAndView("historialPorDiagnostico");
		Collection<HistorialPorDiagnosticoVo> historialPorDiagnosticoVos = historialPorDiagnosticoService.getDiagnosticos(historialPorDiagnosticoForm);
		session.setAttribute("historialPorDiagnosticoVosAux", historialPorDiagnosticoForm.getHistorialPorDiagnosticoVos());
		model.addObject("historialPorDiagnosticoForm", historialPorDiagnosticoForm);
		model.addObject("historialPorDiagnosticoVos", historialPorDiagnosticoVos);
		return model;
	}
	
	@RequestMapping(value = "/paginadorHistorialDiagnostico")
	public @ResponseBody Collection<HistorialPorDiagnosticoVo> paginadorHistorialDiagnostico(@RequestParam(value = "page") int page) {
		//log.info("paginadorHistorialDiagnostico");
		List<HistorialPorDiagnosticoVo> historialPorDiagnosticoVosAux = (List<HistorialPorDiagnosticoVo>) session.getAttribute("historialPorDiagnosticoVosAux");
		Collection<HistorialPorDiagnosticoVo> historialPorDiagnosticoVos = historialPorDiagnosticoService.getDiagnosticos(historialPorDiagnosticoVosAux, page);
		return historialPorDiagnosticoVos;
	}
	
	@RequestMapping(value = "/historialDiagnosticoDetallado")
	public ModelAndView historialDiagnosticoDetallado(HistorialPorDiagnosticoForm historialPorDiagnosticoForm) {
		//log.info("inicioHistorialDiagnostico");
		ModelAndView model = new ModelAndView("historialDiagnosticoDetallado");
		MedicoAtencionPacientesForm medicoAtencionPacientesForm = new MedicoAtencionPacientesForm();
		Collection<HistorialPorDiagnosticoVo> historialPorDiagnosticoVos = historialPorDiagnosticoService.getDiagnosticosDetallados(historialPorDiagnosticoForm);
		medicoAtencionPacientesForm.setIdAtencion(historialPorDiagnosticoForm.getIdAtencion());
		model.addObject("medicoAtencionPacientesForm", medicoAtencionPacientesForm);
		model.addObject("historialPorDiagnosticoForm", historialPorDiagnosticoForm);
		model.addObject("historialPorDiagnosticoVos", historialPorDiagnosticoVos);
		return model;
	}
	
	@RequestMapping(value = "/paginadorHistorialDiagnosticoDetallado")
	public @ResponseBody Collection<HistorialPorDiagnosticoVo> paginadorHistorialDiagnosticoDetallado(@RequestParam(value = "idAfiliado") int idAfiliado,
																									  @RequestParam(value = "idDiagnostico") int idDiagnostico,
																									  @RequestParam(value = "page") int page) {
		//log.info("paginadorHistorialDiagnosticoDetallado");
		Collection<HistorialPorDiagnosticoVo> historialPorDiagnosticoVos = historialPorDiagnosticoService.getDiagnosticos(idAfiliado,idDiagnostico,page);
		return historialPorDiagnosticoVos;
	}	
	
	
}
