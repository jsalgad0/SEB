package com.mx.sab.controller;

import java.util.Collection;
import java.util.List;

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

import com.mx.sab.form.ContrareferenciaForm;
import com.mx.sab.form.LicenciaMedicaForm;
import com.mx.sab.form.ListaPacientesForm;
import com.mx.sab.form.RecetaMedicaForm;
import com.mx.sab.form.SolicitudReferenciaForm;
import com.mx.sab.form.validator.ListaPacientesValidator;
import com.mx.sab.service.IListaPacientesService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.vo.AgendaVo;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.UserInfo;

@Controller
@Log4j2
@RequestMapping("/listaPacientes")
public class ListaPacientesController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IListaPacientesService listaPacientesService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private RecetaMedicaController recetaMedicaController;
	
	@Autowired
	private LicenciaMedicaController licenciaMedicaController;	

	
	@Autowired
	private AtencionSolicitudReferenciaController solicitudReferenciaController;
	
	@Autowired
	private ContrareferenciaController contrareferenciaController;
	
	@InitBinder("listaPacientesForm")
	protected void listaPacientesFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new ListaPacientesValidator());
	}		
	
	@RequestMapping(value = "/listaPacientes")
	public ModelAndView listaPacientes(ListaPacientesForm listaPacientesForm) {
		//log.info("listaPacientes");	
		ModelAndView model = new ModelAndView();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		session.setAttribute("listaPacientes", listaPacientesService.getListaPacientes(listaPacientesForm, userInfo));
		model.setViewName("listaPacientes");
		model.addObject("listaPacientesForm", listaPacientesForm);
		return model; 
	}
	
	@RequestMapping(value = "/paginador")
	public @ResponseBody Collection<AgendaVo> paginador(@RequestParam(value = "busquedaA") String busquedaA,
														@RequestParam(value = "busquedaE") String busquedaE,
														@RequestParam(value = "page") int page) {
		//log.info("paginador");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<AgendaVo> agendaVos = listaPacientesService.getListaPacientes(busquedaA,busquedaE,page,userInfo);
		session.setAttribute("listaPacientes", agendaVos);
		return agendaVos; 
	}	
	
	@RequestMapping(value = "/atencionMedica")
	public ModelAndView atencionMedica(@RequestParam(value = "idAgenda") int idAgenda, ListaPacientesForm listaPacientesForm) {
		//log.info("atencionMedica");
		ModelAndView modelAndView = new ModelAndView("atencionMedica");
		session.setAttribute("idAgenda", idAgenda);
		listaPacientesForm.setIdAgenda(idAgenda);
		listaPacientesService.inicializaForm(listaPacientesForm);
		session.setAttribute("signosVitales", listaPacientesService.getSignosVitales(listaPacientesForm));
		modelAndView.addObject("listaPacientesForm", listaPacientesForm);
		session.setAttribute("listaPacientesForm", listaPacientesForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/cies10")
	public @ResponseBody Collection<AutocompleteVo> cies10(@RequestParam(value = "busqueda") String busqueda) {
		//log.info("registroLectores");
		Collection<AutocompleteVo> catCies10 = listaPacientesService.getCatCies10(busqueda);
		session.setAttribute("catCies10", catCies10);
		return catCies10; 
	}
	
	@RequestMapping(value = "/notaMedica")
	public ModelAndView notaMedica(@Valid ListaPacientesForm listaPacientesForm, BindingResult result) {
		//log.info("notaMedica");
		ModelAndView modelAndView = new ModelAndView("atencionMedica");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if(!result.hasErrors()){
			List<AutocompleteVo> autocompleteVos = (List<AutocompleteVo>) session.getAttribute("diagnosticos");
			boolean editarDiagnostico = false;
			if (session.getAttribute("editarDiagnostico")!=null) {
				editarDiagnostico = (boolean) session.getAttribute("editarDiagnostico");	
			}
			
			listaPacientesForm.setEditarDiagnostico(editarDiagnostico);
			if (autocompleteVos!=null) {
				listaPacientesForm.setAutocompleteVos(autocompleteVos);	
			}
			
			listaPacientesService.save(listaPacientesForm,userInfo);
		}
		
		modelAndView.addObject("listaPacientesForm", listaPacientesForm);
		return atencionMedica(listaPacientesForm.getIdAgenda(), listaPacientesForm);
	}
	
	@RequestMapping(value = "/diagnosticos")
	public ModelAndView diagnosticos(ListaPacientesForm listaPacientesForm) {
		//log.info("diagnosticos");
		ModelAndView modelAndView = new ModelAndView("diagnosticos");
		ListaPacientesForm listaPacientesFormAux = (ListaPacientesForm) session.getAttribute("listaPacientesForm");
		listaPacientesService.agregaDiagnosticos(listaPacientesForm, listaPacientesFormAux);
		session.setAttribute("listaPacientesForm", listaPacientesForm);
		modelAndView.addObject("listaPacientesForm", listaPacientesForm);
		return modelAndView;
	}	
	
	@RequestMapping(value = "/nuevoDiagnostico")
	public ModelAndView nuevoDiagnostico(ListaPacientesForm listaPacientesForm) {
		//log.info("nuevoDiagnostico");
		ListaPacientesForm listaPacientesFormAux = (ListaPacientesForm) session.getAttribute("listaPacientesForm");
		listaPacientesService.agregaDiagnosticos(listaPacientesFormAux,listaPacientesForm);
		session.setAttribute("diagnosticos", listaPacientesForm.getAutocompleteVos());
		session.setAttribute("editarDiagnostico", true);
		return atencionMedica(listaPacientesForm.getIdAgenda(), listaPacientesForm);
	}	
	
	@RequestMapping(value = "/prestaciones")
	public ModelAndView prestaciones(ListaPacientesForm listaPacientesForm) {
		//log.info("prestaciones");
		ModelAndView modelAndView = new ModelAndView("prestaciones");
		listaPacientesService.getPrestaciones(listaPacientesForm);
		session.setAttribute("listaPacientesForm", listaPacientesForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/catPrestaciones")
	public @ResponseBody Collection<AutocompleteVo> ccatPrestacionesies10(@RequestParam(value = "busqueda") String busqueda) {
		//log.info("registroLectores");
		int idAgenda = (int) session.getAttribute("idAgenda");
		Collection<AutocompleteVo> catPrestaciones = listaPacientesService.getCatPrestaciones(busqueda,idAgenda);
		session.setAttribute("catPrestaciones", catPrestaciones);
		return catPrestaciones; 
	}
	
	@RequestMapping(value = "/nuevoPrestacion")
	public ModelAndView nuevoPrestacion(ListaPacientesForm listaPacientesForm) {
		//log.info("nuevoPrestacion");
		listaPacientesService.agregaPrestaciones(listaPacientesForm);
		return atencionMedica(listaPacientesForm.getIdAgenda(), listaPacientesForm);
	}
	
	@RequestMapping(value = "/recetaMedica")
	public ModelAndView recetaMedica(ListaPacientesForm listaPacientesForm) {
		//log.info("recetaMedica");
		RecetaMedicaForm recetaMedicaForm = new RecetaMedicaForm();
		recetaMedicaForm.setIdAgenda(listaPacientesForm.getIdAgenda());
		return recetaMedicaController.recetaMedica(recetaMedicaForm);
	}
	
	@RequestMapping(value = "/regresarAtencionMedica")
	public ModelAndView regresarAtencionMedica(ListaPacientesForm listaPacientesForm) {
		return atencionMedica(listaPacientesForm.getIdAgenda(), listaPacientesForm);
	}
	
	@RequestMapping(value = "/licenciaMedica")
	public ModelAndView licenciaMedica(ListaPacientesForm listaPacientesForm) {
		LicenciaMedicaForm licenciaMedicaForm = new LicenciaMedicaForm();
		licenciaMedicaForm.setIdAgenda(listaPacientesForm.getIdAgenda());
		return licenciaMedicaController.licenciaMedica(licenciaMedicaForm);
	}
	
//	@RequestMapping(value = "/estudiosMedicos")
//	public ModelAndView estudiosMedicos(ListaPacientesForm listaPacientesForm) {
//		EstudiosMedicosForm estudiosMedicosForm = new EstudiosMedicosForm();
//		estudiosMedicosForm.setIdAgenda(listaPacientesForm.getIdAgenda());
//		estudiosMedicosForm.setIdEstudios(listaPacientesForm.getIdEstudios());
//		return estudiosMedicosController.estudiosMedicos(estudiosMedicosForm);
//	}
	
//	@RequestMapping(value = "/solicitudReferencia")
//	public ModelAndView solicitudReferencia(ListaPacientesForm listaPacientesForm) {
//		SolicitudReferenciaForm solicitudReferenciaForm = new SolicitudReferenciaForm();
//		solicitudReferenciaForm.setIdAgenda(listaPacientesForm.getIdAgenda());
//		return solicitudReferenciaController.solicitudReferencia(solicitudReferenciaForm);
//	}
//	
//	@RequestMapping(value = "/contrareferencia")
//	public ModelAndView contrareferencia(SolicitudReferenciaForm solicitudReferenciaForm) {
//		ContrareferenciaForm contrareferenciaForm = new ContrareferenciaForm();
//		contrareferenciaForm.setIdAgenda(solicitudReferenciaForm.getIdAgenda());
//		return contrareferenciaController.contrareferencia(contrareferenciaForm);
//	}
	
}
