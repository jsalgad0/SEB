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

import com.mx.sab.form.AtencionRecetaForm;
import com.mx.sab.service.IAtencionRecetaService;
import com.mx.sab.vo.CatMedicamentosVo;
import com.mx.sab.vo.DiagnosticoVo;
import com.mx.sab.vo.RecetasVo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class AtencionRecetaController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private IAtencionRecetaService atencionRecetaService;

	public ModelAndView receta(AtencionRecetaForm atencionRecetaForm) {
		ModelAndView model = new ModelAndView("atencionReceta");
		atencionRecetaService.inicializaAtencionRecetaForm(atencionRecetaForm); 
		session.setAttribute("recetaVosAux", atencionRecetaForm.getRecetasVos());
		model.addObject("catviasdeadminmedicamentos", atencionRecetaService.getCatViaAdminMedicamento());
		model.addObject("catunidadesdetiemposMenorDia", atencionRecetaService.getCatUnidadesTiempoMenorDia());
		model.addObject("catunidadesdetiemposMayorDia", atencionRecetaService.getCatUnidadesTiempoMayorDia());
		model.addObject("atencionRecetaForm", atencionRecetaForm);
		session.setAttribute("idAtencionReceta", atencionRecetaForm.getIdAtencion());
		return model;
	}
	
	@RequestMapping(value = "/medicamento")
	public @ResponseBody Collection<CatMedicamentosVo> medicamentos(@RequestParam(value = "term") String busqueda) {
		//log.info("medicamento");
		int idAtencion = (int) session.getAttribute("idAtencionReceta");
		Collection<CatMedicamentosVo> medicamentosVos = atencionRecetaService.medicamentos(busqueda, idAtencion);
		return medicamentosVos; 
	}
	
	@RequestMapping(value = "/saveReceta")
	public @ResponseBody AtencionRecetaForm saveReceta(AtencionRecetaForm atencionRecetaForm) {
		//log.info("saveDiagnostico");
		Collection<RecetasVo> recetasVos = (Collection<RecetasVo>) session.getAttribute("recetaVosAux");
		atencionRecetaService.saveReceta(atencionRecetaForm, recetasVos); 
		session.removeAttribute("recetaVosAux");
		session.setAttribute("recetaVosAux", atencionRecetaForm.getRecetasVos());
		return atencionRecetaForm; 
	}
	
	
}
