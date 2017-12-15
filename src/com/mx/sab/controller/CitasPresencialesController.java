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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.CitasPresencialesForm;
import com.mx.sab.form.OrdenServicioForm;
import com.mx.sab.form.PersonaConfianzaForm;
import com.mx.sab.form.SeleccionarMedicoForm;
import com.mx.sab.form.validator.CitasPresencialesValidator;
import com.mx.sab.model.Aseguradores;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.IAgendaService;
import com.mx.sab.service.ICitasPresencialesService;
import com.mx.sab.service.IConveniosService;
import com.mx.sab.service.IGenericService;
import com.mx.sab.service.IPrestacionesService;
import com.mx.sab.service.IPrestadorService;
import com.mx.sab.service.ISeleccionarMedicoService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.vo.AfiliadoIsssteVo;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.CatTipoIdentificadorVo;
import com.mx.sab.vo.ConveniosVo;
import com.mx.sab.vo.PrestadoresVo;
import com.mx.sab.vo.UserInfo;
import com.mx.sab.vo.UsuariosVo;

@Controller
@Log4j2
@RequestMapping("/recepcion")
public class CitasPresencialesController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ICitasPresencialesService citasPresencialesService;
	
	@Autowired
	private IAgendaService agendaService;
	
	@Autowired
	private IGenericService genericService;
	
	@Autowired
	private IConveniosService conveniosService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPrestadorService prestadorService;
	
	@Autowired
	private IPrestacionesService prestacionesService;
	
	@Autowired
	private ISeleccionarMedicoService seleccionarMedicoService;
	
	@InitBinder("citasPresencialesForm")
	protected void citasPresencialesFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new CitasPresencialesValidator());
	}
	
	@RequestMapping(value = "/recepcion")
	public ModelAndView recepcionPresencial(CitasPresencialesForm citasPresencialesForm) {
		//log.info("recepcion");
		ModelAndView modelAndView = new ModelAndView("recepcionCitasPresenciales");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		citasPresencialesService.inicializaCitasPresencialesForm(citasPresencialesForm, userInfo);
		session.setAttribute("aseguradores", citasPresencialesForm.getAseguradores());
		modelAndView.addObject("citasPresencialesForm", citasPresencialesForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/prestadores")
	public @ResponseBody Collection<PrestadoresVo> prestadores(@RequestParam(value = "id") int id) {
		//log.info("prestadores");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<Aseguradores> aseguradores = (Collection<Aseguradores>) session.getAttribute("aseguradores");
		Collection<PrestadoresVo> prestadoresVos = citasPresencialesService.getPrestadoresByAseguradorLugar(id, aseguradores);
		session.setAttribute("prestadores", prestadoresVos);
		return prestadoresVos;
	}	
	
	@RequestMapping(value = "/convenios")
	public @ResponseBody Collection<ConveniosVo> convenios(@RequestParam(value = "idAsegurador") int idAsegurador,
															 @RequestParam(value = "idPrestador") int idPrestador) {
		//log.info("convenios");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<ConveniosVo> conveniosVos = citasPresencialesService.getConvenios(idAsegurador, idPrestador, userInfo);
		session.setAttribute("convenios", conveniosVos);
		return conveniosVos;
	}
	
	@RequestMapping(value = "/identificadores")
	public @ResponseBody Collection<CatTipoIdentificadorVo> identificadores(@RequestParam(value = "idAsegurador") int idAsegurador) {
		//log.info("identificadores");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<CatTipoIdentificadorVo> catTipoIdentificadorVos = genericService.getTipoIdentificadorByAsegurador(idAsegurador);
		session.setAttribute("catTipoIdentificador", catTipoIdentificadorVos);
		return catTipoIdentificadorVos;
	}
	
	@RequestMapping(value = "/buscar")
	public @ResponseBody CitasPresencialesForm buscar(CitasPresencialesForm citasPresencialesForm) {
		//log.info("buscar");
		citasPresencialesService.getAfiliados(citasPresencialesForm);
		session.setAttribute("afiliadoCtoVos", citasPresencialesForm.getAfiliadoCtoVos());
		session.setAttribute("afiliadoIsssteVos", citasPresencialesForm.getAfiliadoIsssteVos());
		session.setAttribute("afiliadoSinAseguradorVos", citasPresencialesForm.getAfiliadoSinAseguradorVos());
		return citasPresencialesForm;
	}
	
	@RequestMapping(value = "/vigente")
	public @ResponseBody CitasPresencialesForm vigente(CitasPresencialesForm citasPresencialesForm) {
		//log.info("vigente");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		citasPresencialesForm.setAfiliadoIsssteVos((Collection<AfiliadoIsssteVo>) session.getAttribute("afiliadoIsssteVos"));
		citasPresencialesService.isVigente(citasPresencialesForm,userInfo);
		return citasPresencialesForm;
	}
	
	@RequestMapping(value = "/nuevoAfiliado")
	public @ResponseBody CitasPresencialesForm nuevoAfiliado(CitasPresencialesForm citasPresencialesForm) {
		//log.info("vigente");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		citasPresencialesForm.setAfiliadoIsssteVos((Collection<AfiliadoIsssteVo>) session.getAttribute("afiliadoIsssteVos"));
		citasPresencialesService.nuevoAfiliado(citasPresencialesForm,userInfo);
		return citasPresencialesForm;
	}
	
	@RequestMapping(value = "/inicioPersonaConfianza")
	public ModelAndView inicioPersonaConfianza(PersonaConfianzaForm personaConfianzaForm) {
		//log.info("inicioPersonaConfianza");
		ModelAndView model = new ModelAndView();
		session.setAttribute("tipoIdentificador", genericService.getTipoIdentificadorPersonaConfianza());
		session.setAttribute("personasConfianza", agendaService.getPersonasConfianza(personaConfianzaForm));
		model.addObject("personaConfianzaForm", personaConfianzaForm);
		model.setViewName("personaConfianzaNuevo");		
		return model;
	}	
	
	@RequestMapping(value = "/prestacionSAB")
	public @ResponseBody Collection<AutocompleteVo> prestacionSAB(@RequestParam(value = "idConvenio") int idConvenio, @RequestParam(value = "term") String busqueda) {
		//log.info("registroLectores");
		Collection<AutocompleteVo> prestacion = prestacionesService.getPrestacionesSAB(idConvenio, busqueda);
		return prestacion; 
	}
	
	@RequestMapping(value = "/prestacion")
	public @ResponseBody Collection<AutocompleteVo> prestacion(@RequestParam(value = "idConvenio") int idConvenio, @RequestParam(value = "term") String busqueda) {
		//log.info("registroLectores");
		Collection<AutocompleteVo> prestacion = prestacionesService.getPrestaciones(idConvenio, busqueda);
		return prestacion; 
	}
	
	@RequestMapping(value = "/seleccionarMedico")
	public ModelAndView seleccionarMedico() {
		//log.info("seleccionarMedico");
		ModelAndView model = new ModelAndView();
		try{
			SeleccionarMedicoForm seleccionarMedicoForm = new SeleccionarMedicoForm();
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			seleccionarMedicoService.inicializarSeleccionarMedicoForm(seleccionarMedicoForm, userInfo);
			model.setViewName("seleccionarMedico");
			model.addObject("seleccionarMedicoForm", seleccionarMedicoForm);			
		}catch(Exception ex){
			ex.printStackTrace();
			model.setViewName("errorPopUp");
		}

		return model;
	}	
	
	@RequestMapping(value = "/medicos")
	public @ResponseBody Collection<UsuariosVo> medicos(@RequestParam(value = "idTiempo") String idTiempo) {
		//log.info("medicos");
		Collection<Usuarios> usuarios = (Collection<Usuarios>) session.getAttribute("usuariosMedicos");
		Collection<UsuariosVo> usuariosVos = seleccionarMedicoService.getMedicos(idTiempo, usuarios);
		return usuariosVos; 
	}
	
	@RequestMapping(value = "/medicoInfo")
	public @ResponseBody UsuariosVo medicoInfo(@RequestParam(value = "idMedico") int idMedico) {
		//log.info("medicos");
		Collection<Usuarios> usuarios = (Collection<Usuarios>) session.getAttribute("usuariosMedicos");
		UsuariosVo usuariosVo = seleccionarMedicoService.getMedicoInfo(idMedico, usuarios);
		return usuariosVo; 
	}	
	
	@RequestMapping(value = "/nuevoAtencionMedica", method = RequestMethod.POST)
	public ModelAndView nuevoAtencionMedica(@Valid CitasPresencialesForm citasPresencialesForm, BindingResult result) {
		//log.info("recepcion");
		ModelAndView modelAndView = new ModelAndView("recepcionCitasPresenciales");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		citasPresencialesForm.setEnvio(true);
		
		if (!result.hasErrors()) {
			citasPresencialesService.save(citasPresencialesForm, userInfo);
			if (!citasPresencialesForm.isBanderaError()) {
				citasPresencialesForm = new CitasPresencialesForm();		
			}	
		}
		
		modelAndView.addObject("citasPresencialesForm", citasPresencialesForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/verificaAgenda")
	public @ResponseBody CitasPresencialesForm verificaAgenda(CitasPresencialesForm citasPresencialesForm) {
		//log.info("verificaAgenda");
		citasPresencialesService.verificaAgenda(citasPresencialesForm);
		return citasPresencialesForm;
	}
	
	@RequestMapping(value = "/verificaOrdenServicio")
	public @ResponseBody CitasPresencialesForm verificaOrdenServicio(CitasPresencialesForm citasPresencialesForm) {
		//log.info("verificaOrdenServicio");
		citasPresencialesService.verificaOrdenServicio(citasPresencialesForm);
		session.setAttribute("prestacionesPorTomarVos", citasPresencialesForm.getPrestacionesPorTomarVos());
		return citasPresencialesForm;
	}
	
	@RequestMapping(value = "/valorizar")
	public @ResponseBody CitasPresencialesForm valorizar(CitasPresencialesForm citasPresencialesForm) {
		//log.info("valorizar");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		citasPresencialesService.valorizar(citasPresencialesForm, userInfo);
		return citasPresencialesForm;
	}
	
	@RequestMapping(value = "/guardar")
	public @ResponseBody CitasPresencialesForm guardar(CitasPresencialesForm citasPresencialesForm) {
		//log.info("guardar");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		citasPresencialesService.guardar(citasPresencialesForm, userInfo);
		return citasPresencialesForm;
	}	
	
	@RequestMapping(value = "/ordenServicio")
	public ModelAndView ordenServicio(CitasPresencialesForm citasPresencialesForm) {
		//log.info("ordenServicio");
		ModelAndView modelAndView = new ModelAndView("ordenServicio");
		OrdenServicioForm ordenServicioForm = new OrdenServicioForm();
		ordenServicioForm.setOrdenServicio(citasPresencialesForm.getOrdenServicio());
		modelAndView.addObject("ordenServicioForm", ordenServicioForm);
		return modelAndView;
	}	
	
	@RequestMapping(value = "/autorizacionEspecial")
	public @ResponseBody CitasPresencialesForm autorizacionEspecial(CitasPresencialesForm citasPresencialesForm) {
		//log.info("vigente");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		citasPresencialesService.autorizacionEspecial(citasPresencialesForm,userInfo);
		return citasPresencialesForm;
	}	
}
