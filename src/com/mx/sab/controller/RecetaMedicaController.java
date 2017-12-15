package com.mx.sab.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.RecetaMedicaForm;
import com.mx.sab.form.validator.RecetaMedicaValidator;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.service.IRecetaMedicaService;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.SeleccionMedicamentoVo;

@Controller
@Log4j2
@RequestMapping("/listaPacientes")
public class RecetaMedicaController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IRecetaMedicaService recetaMedicaService;
	
	@Autowired
	private ListaPacientesController listaPacientesController;
	
	@InitBinder("recetaMedicaForm")
	protected void recetaMedicaFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new RecetaMedicaValidator());
	}

	public ModelAndView recetaMedica(RecetaMedicaForm recetaMedicaForm) {
		ModelAndView modelAndView = new ModelAndView("recetaMedica");
		recetaMedicaService.inicializaForm(recetaMedicaForm);
		modelAndView.addObject("recetaMedicaForm", recetaMedicaForm);
		session.setAttribute("recetaMedicaForm", recetaMedicaForm);
		session.setAttribute("catviasdeadminmedicamentos", recetaMedicaService.getCatViasDeAdiminMedicamento());
		session.setAttribute("catUnidadesDeTiempoMenorDia", recetaMedicaService.getCatUnidadesDeTiempoMenorDia());
		session.setAttribute("catUnidadesDeTiempoMayorDia", recetaMedicaService.getCatUnidadesDeTiempoMayorDia());
		session.setAttribute("medicamentosrecetas", recetaMedicaService.getMedicamentoRecetas(recetaMedicaForm));		
		return modelAndView;
	}
	
	@RequestMapping(value = "/medicamentos")
	public @ResponseBody Collection<AutocompleteVo> medicamentos(@RequestParam(value = "busqueda") String busqueda) {
		//log.info("registroLectores");
		RecetaMedicaForm recetaMedicaForm = (RecetaMedicaForm) session.getAttribute("recetaMedicaForm");
		Collection<AutocompleteVo> catMedicamentos = recetaMedicaService.getCatMedicamentos(busqueda,recetaMedicaForm);
		session.setAttribute("catMedicamentos", catMedicamentos);
		return catMedicamentos; 
	}
	
	@RequestMapping(value = "/nuevaRecetaMedica")
	public ModelAndView nuevaRecetaMedica(@Valid RecetaMedicaForm recetaMedicaForm, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("recetaMedica");
		if (!result.hasErrors()) {
			recetaMedicaService.save(recetaMedicaForm);	
			session.setAttribute("medicamentosrecetas", recetaMedicaService.getMedicamentoRecetas(recetaMedicaForm));
		}
		modelAndView.addObject("recetaMedicaForm", recetaMedicaForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/seleccionMedicamentos")
	public ModelAndView seleccionMedicamentos(@RequestParam(value = "idMedicamento") int idMedicamento) {
		//log.info("seleccionMedicamentos"+idMedicamento);
		ModelAndView modelAndView = new ModelAndView("seleccionMedicamentos");
		RecetaMedicaForm recetaMedicaForm = (RecetaMedicaForm) session.getAttribute("recetaMedicaForm");
		recetaMedicaForm.setIdMedicamento(idMedicamento);
		recetaMedicaService.getSeleccionMedicamentos(recetaMedicaForm);
		modelAndView.addObject("recetaMedicaForm", recetaMedicaForm);
		return modelAndView;		
	}
	
	@RequestMapping(value = "/seleccionMedicamentosRevision")
	public @ResponseBody SeleccionMedicamentoVo seleccionMedicamentosRevision(@RequestParam(value = "idMedicamento") int idMedicamento) {
		//log.info("seleccionMedicamentos"+idMedicamento);
		RecetaMedicaForm recetaMedicaForm = (RecetaMedicaForm) session.getAttribute("recetaMedicaForm");
		recetaMedicaForm.setIdMedicamento(idMedicamento);
		SeleccionMedicamentoVo seleccionMedicamentoVo = recetaMedicaService.getSeleccionMedicamentos(recetaMedicaForm);
		return seleccionMedicamentoVo;
	}
	
	@RequestMapping(value = "/borrar")
	public ModelAndView nuevaRecetaMedica(RecetaMedicaForm recetaMedicaForm) {
		ModelAndView modelAndView = new ModelAndView("recetaMedica");
		recetaMedicaService.deleteMedicamentoRecetas(recetaMedicaForm);
		session.setAttribute("medicamentosrecetas", recetaMedicaService.getMedicamentoRecetas(recetaMedicaForm));
		modelAndView.addObject("recetaMedicaForm", recetaMedicaForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/editar")
	public ModelAndView editar(RecetaMedicaForm recetaMedicaForm) {
		ModelAndView modelAndView = new ModelAndView("recetaMedica");
		recetaMedicaService.inicializaMedicamentoRecetas(recetaMedicaForm);
		modelAndView.addObject("recetaMedicaForm", recetaMedicaForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/editarReceta")
	public ModelAndView editarReceta(@Valid RecetaMedicaForm recetaMedicaForm, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("recetaMedica");
		if (!result.hasErrors()) {
			recetaMedicaService.update(recetaMedicaForm);	
			session.setAttribute("medicamentosrecetas", recetaMedicaService.getMedicamentoRecetas(recetaMedicaForm));
		}
		modelAndView.addObject("recetaMedicaForm", recetaMedicaForm);
		return modelAndView;
	}

}
