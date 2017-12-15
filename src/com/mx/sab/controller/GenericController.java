package com.mx.sab.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.sab.service.IGenericService;
import com.mx.sab.service.IPrestadorService;
import com.mx.sab.vo.CatColoniasVo;
import com.mx.sab.vo.CatMunicipiosVo;
import com.mx.sab.vo.RolesVo;

@Controller
@Log4j2
@RequestMapping("/generic")
public class GenericController {

	@Autowired
	private HttpSession session;

	@Autowired
	private IPrestadorService prestadorService;

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private IGenericService genericService;

	@RequestMapping(value = "/municipios")
	public @ResponseBody
	Collection<CatMunicipiosVo> municipios(@RequestParam(value = "id") int id) {
		//log.info("municipios");
		Collection<CatMunicipiosVo> catMunicipios = genericService
				.getCatMunicipios(id);
		session.setAttribute("catMunicipios", catMunicipios);
		return catMunicipios;
	}

	@RequestMapping(value = "/colonias")
	public @ResponseBody
	Collection<CatColoniasVo> colonias(@RequestParam(value = "id") int id) {
		//log.info("colonias");
		Collection<CatColoniasVo> catColonias = genericService
				.getCatColonias(id);
		session.setAttribute("catColonias", catColonias);
		return catColonias;
	}

	@RequestMapping(value = "/roles")
	public @ResponseBody
	Collection<RolesVo> roles(@RequestParam(value = "id") String id) {
		//log.info("roles");
		Collection<RolesVo> roles = genericService.getRoles(id);
		session.setAttribute("roles", roles);
		return roles;
	}

	@RequestMapping(value = "/rfc")
	public @ResponseBody String rfc(@RequestParam(value = "nombre") String nombre, @RequestParam(value = "apellidoP") String apellidoP,	@RequestParam(value = "apellidoM") String apellidoM, @RequestParam(value = "fecha") String fecha) {
		String rfc = "";
		rfc = genericService.getRfc(nombre, apellidoP, apellidoM, fecha);		
		return rfc;
	}

}
