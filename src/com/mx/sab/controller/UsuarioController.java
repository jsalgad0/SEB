package com.mx.sab.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
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

import com.mx.sab.form.UsuarioForm;
import com.mx.sab.form.validator.UsuarioValidator;
import com.mx.sab.model.Catespecialidadesmedicas;
import com.mx.sab.model.Catestados;
import com.mx.sab.model.Cattipoidentificador;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Modulos;
import com.mx.sab.service.IGenericService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.vo.AseguradoresVo;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.RolesVo;
import com.mx.sab.vo.UsuariosVo;


@Controller
@Log4j2
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired IGenericService genericService;
	@Autowired private HttpSession httpSession;
	@Autowired private IUsuarioService usuarioService;
	
	@InitBinder("usuarioForm")
	protected void usuarioBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new UsuarioValidator());
	}

	@RequestMapping("/alta")
	public ModelAndView addPersona(){
		//log.info("usuario");		
		ModelAndView modelAndView = new ModelAndView();
		Collection<Catestados> estados = genericService.getCatEstados();
		Collection<Modulos> modulos = usuarioService.getModulos();
		Collection<Lugaresdeatencion> lugarAtencion = genericService.getLugarAtencion();
		Collection<Catespecialidadesmedicas> especialidades = usuarioService.getEspecialidades();
		Collection<Cattipoidentificador> tipoIdentificador = genericService.getTipoIdentificador();
		modelAndView.addObject("estados", estados);
		modelAndView.addObject("modulos", modulos);
		modelAndView.addObject("lugarAtencion", lugarAtencion);
		modelAndView.addObject("usuarioForm", new UsuarioForm());
		httpSession.setAttribute("catEstados", estados);
		httpSession.setAttribute("catModulos", modulos);
		httpSession.setAttribute("lugarAtencion", lugarAtencion);
		httpSession.setAttribute("catEspecialidades", especialidades);
		httpSession.setAttribute("tipoIdentificador", tipoIdentificador);
		modelAndView.setViewName("usuario");
		return modelAndView;
	}
	
	@RequestMapping("/nuevo")
	public ModelAndView addUsuario(@Valid UsuarioForm usuarioForm, BindingResult result, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (result.hasErrors()) {
				modelAndView.addObject("usuarioForm", usuarioForm);
				modelAndView.setViewName("usuario");
			}else{
				usuarioForm.setIdAuditoria((int) httpSession.getAttribute("auditoriaId"));
				usuarioService.save(usuarioForm);
				modelAndView.setViewName("usuario");
			}
		} catch (Exception e) {
			e.printStackTrace();
			usuarioForm.setError("Error de validacion");
			modelAndView.addObject("usuarioForm", usuarioForm);
			modelAndView.setViewName("usuario");
		}
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/inicio")
	public ModelAndView usuarios(UsuarioForm usuarioForm) {
		//log.info("usuarios");
		ModelAndView model = new ModelAndView();
		httpSession.setAttribute("usuarios", usuarioService.getUsuarios(usuarioForm));
		model.setViewName("usuarios");		
		return model; 
	}	
	
	@RequestMapping(value = "/inicioEditarUsuario")
	public ModelAndView inicioEditar(UsuarioForm usuarioForm) {
		//log.info("editar");
		ModelAndView modelAndView = new ModelAndView();
		try {
			usuarioService.getUsuarioForm(usuarioForm);		
			Collection<Catestados> estados = genericService.getCatEstados();
			Collection<Modulos> modulos = usuarioService.getModulos();
			Collection<Lugaresdeatencion> lugarAtencion = genericService.getLugarAtencion();
			Collection<Catespecialidadesmedicas> especialidades = usuarioService.getEspecialidades();
			Collection<Cattipoidentificador> tipoIdentificador = genericService.getTipoIdentificador();
			modelAndView.addObject("estados", estados);
			modelAndView.addObject("modulos", modulos);
			modelAndView.addObject("lugarAtencion", lugarAtencion);
			modelAndView.addObject("idUsuario", usuarioForm.getUsuario().getUsuarioId());
			httpSession.setAttribute("catEstados", estados);
			httpSession.setAttribute("catModulos", modulos);
			httpSession.setAttribute("lugarAtencion", lugarAtencion);
			httpSession.setAttribute("catEspecialidades", especialidades);
			httpSession.setAttribute("tipoIdentificador", tipoIdentificador);
			modelAndView.addObject("usuarioForm", usuarioForm);
			modelAndView.setViewName("usuario");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/paginador")
	public @ResponseBody Collection<UsuariosVo> paginador(@RequestParam(value = "busqueda") String busqueda,@RequestParam(value = "page") int page) {
		//log.info("usuarios");
		Collection<UsuariosVo> usuarios = usuarioService.getUsuarios(busqueda, page);
		httpSession.setAttribute("usuarios", usuarios);
		return usuarios; 
	}	
	
	@RequestMapping(value = "/rolesSeleccionado")
	public @ResponseBody
	Collection<RolesVo> rolesSeleccionado(@RequestParam(value = "id") String id, @RequestParam(value = "idUsuario") String idUsuario) {
		//log.info("roles");
		Collection<RolesVo> roles = usuarioService.getRolesUsuario(id, idUsuario);
		httpSession.setAttribute("roles", roles);
		return roles;
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public ModelAndView editar(@Valid UsuarioForm usuarioForm, BindingResult result) {
		//log.info("editar");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("usuario");
		try{
			if (result.hasErrors()) {
				//usuarioService.getUsuarioForm(usuarioForm);
				usuarioForm.setEditar(true);
				Collection<Catestados> estados = genericService.getCatEstados();
				Collection<Modulos> modulos = usuarioService.getModulos();
				Collection<Lugaresdeatencion> lugarAtencion = genericService.getLugarAtencion();
				Collection<Catespecialidadesmedicas> especialidades = usuarioService.getEspecialidades();
				Collection<Cattipoidentificador> tipoIdentificador = genericService.getTipoIdentificador();
				modelAndView.addObject("estados", estados);
				modelAndView.addObject("modulos", modulos);
				modelAndView.addObject("lugarAtencion", lugarAtencion);
				modelAndView.addObject("idUsuario", usuarioForm.getUsuario().getUsuarioId());
				httpSession.setAttribute("catEstados", estados);
				httpSession.setAttribute("catModulos", modulos);
				httpSession.setAttribute("lugarAtencion", lugarAtencion);
				httpSession.setAttribute("catEspecialidades", especialidades);
				httpSession.setAttribute("tipoIdentificador", tipoIdentificador);
				modelAndView.addObject("usuarioForm", usuarioForm);
				modelAndView.setViewName("usuario");	
			}else{
				usuarioService.update(usuarioForm);
				Collection<Catestados> estados = genericService.getCatEstados();
				Collection<Modulos> modulos = usuarioService.getModulos();
				Collection<Lugaresdeatencion> lugarAtencion = genericService.getLugarAtencion();
				Collection<Catespecialidadesmedicas> especialidades = usuarioService.getEspecialidades();
				Collection<Cattipoidentificador> tipoIdentificador = genericService.getTipoIdentificador();
				modelAndView.addObject("estados", estados);
				modelAndView.addObject("modulos", modulos);
				modelAndView.addObject("lugarAtencion", lugarAtencion);
				modelAndView.addObject("usuarioForm", new UsuarioForm());
				httpSession.setAttribute("catEstados", estados);
				httpSession.setAttribute("catModulos", modulos);
				httpSession.setAttribute("lugarAtencion", lugarAtencion);
				httpSession.setAttribute("catEspecialidades", especialidades);
				httpSession.setAttribute("tipoIdentificador", tipoIdentificador);
				modelAndView.setViewName("usuario");
			}
		}catch(Exception e){
			e.printStackTrace();
			modelAndView.setViewName("error");
		}
		return modelAndView; 
	}	
	
	@RequestMapping(value = "/adminInstitucion")
	public @ResponseBody Collection<AutocompleteVo> aseguradores(@RequestParam(value = "busqueda") String busqueda) {
		//log.info("aseguradores");
		Collection<AutocompleteVo> aseguradores = usuarioService.aseguradores(busqueda);
		httpSession.setAttribute("aseguradores", aseguradores);
		return aseguradores; 
	}
	
	@RequestMapping(value = "/aseguradorById")
	public @ResponseBody Collection<AseguradoresVo> aseguradorById(@RequestParam(value = "busqueda") String busqueda) {
		//log.info("esguradorById");
		Collection<AseguradoresVo> aseguradoresVos = usuarioService.aseguradoresById(busqueda);
		httpSession.setAttribute("aseguradoresVos", aseguradoresVos);
		return aseguradoresVos; 
	}
	
	@RequestMapping(value = "/borrar")
	public ModelAndView borrar(UsuarioForm usuarioForm) {
		//log.info("borrar");
		ModelAndView modelAndView = new ModelAndView();
		usuarioService.delete(usuarioForm);
		Collection<Catestados> estados = genericService.getCatEstados();
		Collection<Modulos> modulos = usuarioService.getModulos();
		Collection<Lugaresdeatencion> lugarAtencion = genericService.getLugarAtencion();
		Collection<Catespecialidadesmedicas> especialidades = usuarioService.getEspecialidades();
		Collection<Cattipoidentificador> tipoIdentificador = genericService.getTipoIdentificador();
		modelAndView.addObject("estados", estados);
		modelAndView.addObject("modulos", modulos);
		modelAndView.addObject("lugarAtencion", lugarAtencion);
		modelAndView.addObject("usuarioForm", new UsuarioForm());
		httpSession.setAttribute("catEstados", estados);
		httpSession.setAttribute("catModulos", modulos);
		httpSession.setAttribute("lugarAtencion", lugarAtencion);
		httpSession.setAttribute("catEspecialidades", especialidades);
		httpSession.setAttribute("tipoIdentificador", tipoIdentificador);
		modelAndView.setViewName("usuario");
		return modelAndView; 
	}	

}
