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

import com.mx.sab.form.ListaSignosForm;
import com.mx.sab.form.SignosVitalesForm;
import com.mx.sab.form.validator.SignosVitalesValidator;
import com.mx.sab.service.ISignosService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.vo.ListaSignosVo;
import com.mx.sab.vo.TomaSignosVo;
import com.mx.sab.vo.UserInfo;


@Controller
@Log4j2
@RequestMapping("/signos")
public class SignosController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired 
	private IUsuarioService usuarioService;
	
	@Autowired 
	private ISignosService signosService;
	
	@InitBinder("signosVitalesForm")
	protected void signosVitalesFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new SignosVitalesValidator());
	}
	
	@RequestMapping(value = "/listaPacientes")
	public ModelAndView lista(ListaSignosForm listaSignosForm) {
		//log.info("listaPAcientes");
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<ListaSignosVo> atencionmedicas = signosService.getAtenciones(listaSignosForm, userInfo);
		session.setAttribute("lista", atencionmedicas);
		modelAndView.addObject("lista", atencionmedicas);
		modelAndView.addObject("listaSignosForm", listaSignosForm);
		modelAndView.setViewName("listaSignos");
		return modelAndView; 
	}
	
	@RequestMapping(value = "/tomaSignos")
	public ModelAndView tomaSignos(@RequestParam(value = "idAtencion") int idAtencion) {
		//log.info("tomaSignos");
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		TomaSignosVo tomaSignosVo = new TomaSignosVo();
		tomaSignosVo = signosService.getTomaSignos(idAtencion);
		SignosVitalesForm signosVitalesForm = new SignosVitalesForm();
		modelAndView.addObject("infoSignos", tomaSignosVo);
		modelAndView.addObject("signosVitalesForm", signosVitalesForm);
		modelAndView.addObject("userInfo", userInfo);
		modelAndView.setViewName("tomaSignos");
		return modelAndView; 
	}
	
	@RequestMapping(value = "/nuevo")
	public ModelAndView tomaSignos(@Valid SignosVitalesForm signosVitalesForm, BindingResult result) {
		//log.info("nuevoSignos");
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		TomaSignosVo tomaSignosVo = new TomaSignosVo();
		tomaSignosVo = signosService.getTomaSignos(signosVitalesForm.getIdAtencion());
		if(result.hasErrors()){
			modelAndView.addObject("signosVitalesForm", signosVitalesForm);
		}else{
			try{
				signosService.save(signosVitalesForm, userInfo);
				tomaSignosVo.setGuardado(true);
				modelAndView.addObject("signosVitalesForm", signosVitalesForm);
			}catch(Exception exception){
				signosVitalesForm.setError("Error");
				modelAndView.addObject("signosVitalesForm", signosVitalesForm);
			}
		}
		modelAndView.addObject("infoSignos", tomaSignosVo);
		modelAndView.setViewName("tomaSignos");
		return modelAndView;
	}
	
	@RequestMapping(value = "/paginador")
	public @ResponseBody Collection<ListaSignosVo> paginador(@RequestParam(value = "page") int page) {
		//log.info("signos vitales");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		Collection<ListaSignosVo> atencionmedicas = signosService.getLista(page, userInfo);
		session.setAttribute("atencionmedicas", atencionmedicas);
		return atencionmedicas; 
	}
	
	@RequestMapping(value = "/editar")
	public ModelAndView editar(@Valid SignosVitalesForm signosVitalesForm, BindingResult result) {
		//log.info("editarSignos");
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if(result.hasErrors()){
			modelAndView.addObject("signosVitalesForm", signosVitalesForm);
		}else{
			try{
				signosService.update(signosVitalesForm, userInfo);
				modelAndView.addObject("signosVitalesForm", signosVitalesForm);
			}catch(Exception exception){
				signosVitalesForm.setError("Error");
				modelAndView.addObject("signosVitalesForm", signosVitalesForm);
			}
		}		
		modelAndView.setViewName("tomaSignos");
		return modelAndView;
	}

}
