package com.mx.sab.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IAseguradorDao;
import com.mx.sab.dao.IAuditoriaDao;
import com.mx.sab.dao.IGenericDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.form.UsuarioForm;
import com.mx.sab.model.Aseguradores;
import com.mx.sab.model.Auditoriaautentia;
import com.mx.sab.model.Catconfig;
import com.mx.sab.model.Catespecialidadesmedicas;
import com.mx.sab.model.Cattipoidentificador;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Menu;
import com.mx.sab.model.Menurol;
import com.mx.sab.model.Modulos;
import com.mx.sab.model.Roles;
import com.mx.sab.model.Usuarioasegurador;
import com.mx.sab.model.Usuarioespecialidades;
import com.mx.sab.model.Usuariolugaratencion;
import com.mx.sab.model.Usuariomenu;
import com.mx.sab.model.Usuariorol;
import com.mx.sab.model.Usuarios;
import com.mx.sab.model.Usuariotipoidentificador;
import com.mx.sab.service.IAuditoriaService;
import com.mx.sab.service.IUsuarioService;
import com.mx.sab.util.EnviaMail;
import com.mx.sab.vo.AseguradoresVo;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.EmailVo;
import com.mx.sab.vo.EspecialiadesVo;
import com.mx.sab.vo.RolesVo;
import com.mx.sab.vo.TipoIdentificadorVo;
import com.mx.sab.vo.UsuariosVo;

@Service
@Log4j2
@Repository("usuarioService")
@Transactional(readOnly = false)
public class UsuarioServceImpl implements IUsuarioService {
	
	@Autowired private IGenericDao genericDao;
	@Autowired private IUsuarioDao usuarioDao;
	@Autowired private IAseguradorDao aseguradorDao;
	@Autowired private ILugarAtencionDao lugarAtencionDao;
	@Autowired private IAuditoriaDao auditoriaDao;

	@Override
	public Collection<Modulos> getModulos() {
		//log.info("modulos");
		Collection<Modulos> modulos = new ArrayList<Modulos>();
		modulos = usuarioDao.getModulos();
		return modulos;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Collection<Catespecialidadesmedicas> getEspecialidades() {
		//log.info("especialidades");
		Collection<Catespecialidadesmedicas> especialidades = new ArrayList<Catespecialidadesmedicas>();
		especialidades = usuarioDao.getEspecialidades();
		return especialidades;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(UsuarioForm usuarioForm) throws Exception {
		//log.info("save Usuarios");
		try{
			Usuarios usuarioValidacion = usuarioDao.getValidacionRfc(usuarioForm.getUsuario().getRfc());
			if(usuarioValidacion==null){
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date date = formatter.parse(usuarioForm.getFechaDeNacimiento());
				Usuarios usuarios = (Usuarios)usuarioForm.getUsuario();
				usuarios.setFechaDeNacimiento(date);
				usuarios.setActivo(1);
				usuarios.setNombreUsuario(usuarioForm.getUsuario().getRfc());
				usuarios.setCatsexos(usuarioDao.getSexoId(Integer.parseInt(usuarioForm.getIdSexo())));
				usuarios.setCatestados(genericDao.getEstadoById(Integer.parseInt(usuarioForm.getIdEstado())));
				usuarioDao.save(usuarios);
				
				altaUsuarios(usuarioForm, usuarios);
				
				Auditoriaautentia auditoriaautentia = auditoriaDao.getAuditoriAutentiaById(usuarioForm.getIdAuditoria());
				auditoriaautentia.setUsuarioId(usuarios.getUsuarioId());
				auditoriaDao.update(auditoriaautentia);
				
				envioMail(usuarioForm);
			}else{
				throw new Exception("El usuario ya fue dado de alta");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("El usuario ya fue dado de alta");
		}
		
	}
	
	public void saveMedico(UsuarioForm usuarioForm, Usuarios usuarios){
		
		EspecialiadesVo especialidades = usuarioForm.getEspecialiadesVo();
		int i = 0;
		int j = 0;
		for(String esp : especialidades.getId()){
			j=0;
			for (String mat : especialidades.getMatricula()) {
				if(i==j){
					Catespecialidadesmedicas catespecialidadesmedicas = usuarioDao.getEspecialidadesId(Integer.parseInt(esp));
					Usuarioespecialidades usuarioespecialidades = new Usuarioespecialidades();
					usuarioespecialidades.setUsuarios(usuarios);
					usuarioespecialidades.setCatespecialidadesmedicas(catespecialidadesmedicas);
					usuarioespecialidades.setCedulaEspecialidad(mat);
					usuarioDao.saveUsuarioEspecialidad(usuarioespecialidades);
				}
				j++;
			}
			i++;
		}
		TipoIdentificadorVo tipoIdentificadorVo = usuarioForm.getTipoIdentificadorVo();
		i = 0;
		j = 0;
		for(String tipo : tipoIdentificadorVo.getId()){
			j=0;
			for (String mat : tipoIdentificadorVo.getValor()) {
				if(i==j){
					Cattipoidentificador cattipoidentificador = usuarioDao.getTipoIdentificador(Integer.parseInt(tipo));
					Usuariotipoidentificador usuariotipoidentificador = new Usuariotipoidentificador();
					usuariotipoidentificador.setCattipoidentificador(cattipoidentificador);
					usuariotipoidentificador.setUsuarios(usuarios);
					usuariotipoidentificador.setValor(mat);
					usuarioDao.saveUsuarioTipoIdentidicador(usuariotipoidentificador);
				}
				j++;
			}
			i++;
		}
	}
	
	public void envioMail(UsuarioForm usuarioForm){
		Collection<Catconfig> catconfigs = usuarioDao.getConfiguracion();
		EmailVo emailVo = new EmailVo();
		for (Catconfig catconfig : catconfigs) {
			if(catconfig.getLlave().equals("email.smtp.hostname")){
				emailVo.setHostname(catconfig.getValor());
			}else if(catconfig.getLlave().equals("email.smtp.user")){
				emailVo.setUsuario(catconfig.getValor());
			}else if(catconfig.getLlave().equals("email.smtp.pass")){
				emailVo.setPassword(catconfig.getValor());
			}else if(catconfig.getLlave().equals("email.smtp.port")){
				emailVo.setPuerto(catconfig.getValor());
			}if(catconfig.getLlave().equals("email.smtp.from")){
				emailVo.setForm(catconfig.getValor());
			} 
		}
		emailVo.setCorreo(usuarioForm.getUsuario().getMail());
		EnviaMail enviaMail = new EnviaMail();
		enviaMail.enviar(emailVo);
	}
	
	@Override
	public Collection<Usuarios> getUsuarios(UsuarioForm usuarioForm) {
		int filas = 7;
		int paginasTotal = 0;
		int inicio = 0;
		int fin = 0;
		int pagina = 1;
		Collection<Usuarios> usuarios = null;
		
		if (usuarioForm==null) {
			usuarioForm = new UsuarioForm();
		}
		
		if (usuarioForm.getBusqueda()==null) {
			usuarioForm.setBusqueda("");	
		}
		int totalUsuarios = usuarioDao.getUsuariosCount(usuarioForm.getBusqueda());
		if (totalUsuarios>0) {
			paginasTotal = totalUsuarios / filas;
			if (totalUsuarios % filas != 0) {
				paginasTotal++;
			}
			
			if (paginasTotal>7) {
				usuarioForm.setDisplay(7);
			}else {
				usuarioForm.setDisplay(paginasTotal);
			}
			
			usuarioForm.setCount(paginasTotal);
			inicio = (pagina-1)*7;
			fin = (pagina*7);
			
			usuarios = usuarioDao.getUsuarios(usuarioForm.getBusqueda(),inicio,fin);
		}else {
			//log.info("No hay aseguradores");
		}
	
		return usuarios;
	}
	
	@Override
	public void getUsuarioForm(UsuarioForm usuarioForm) throws Exception {
		Usuarios usuarios = usuarioDao.getUsuarioById(Integer.parseInt(usuarioForm.getIdUsuario()));

		usuarioForm.setUsuario(usuarios);
		
		String fechaNacimiento = usuarios.getFechaDeNacimiento().toString().substring(8,10)+"-"+usuarios.getFechaDeNacimiento().toString().substring(5,7)+"-"+usuarios.getFechaDeNacimiento().toString().substring(0,4);
		usuarioForm.setFechaDeNacimiento(fechaNacimiento);
		usuarioForm.setIdEstado(""+usuarios.getCatestados().getEstadoId());
		
		Iterator<Usuariorol> usuarioRolIterator = usuarios.getUsuariorols().iterator();
		Usuariorol usuariorol = null;
		while (usuarioRolIterator.hasNext()) {
			usuariorol = (Usuariorol) usuarioRolIterator.next();
		}
		usuarioForm.setIdModulo(""+usuariorol.getRoles().getModulos().getModuloId());
		
		Collection<String> lugares = new ArrayList<String>();
		Iterator<Usuariolugaratencion> usuLugar = usuarios.getUsuariolugaratencions().iterator();
		while (usuLugar.hasNext()) {
			Usuariolugaratencion usuariolugaratencion = usuLugar.next();
			lugares.add(""+usuariolugaratencion.getLugaresdeatencion().getLugarDeAtencionId());
		}
		usuarioForm.setIdLugarAtencion(lugares);
		
		EspecialiadesVo especialiadesVo = new EspecialiadesVo();
		Collection<String> idEsp = new ArrayList<String>();
		Collection<String> esp = new ArrayList<String>();
		Iterator<Usuarioespecialidades> usuEsp = usuarios.getUsuarioespecialidadeses().iterator();
		while (usuEsp.hasNext()) {
			Usuarioespecialidades usu = usuEsp.next();
			idEsp.add(""+usu.getCatespecialidadesmedicas().getEspecialidadMedicaId());
			esp.add(usu.getCedulaEspecialidad());
		}
		especialiadesVo.setId(idEsp);
		especialiadesVo.setMatricula(esp);
		usuarioForm.setEspecialiadesVo(especialiadesVo);
		
		TipoIdentificadorVo identificadorVo = new TipoIdentificadorVo();
		Collection<String> idTipo = new ArrayList<String>();
		Collection<String> tipo = new ArrayList<String>();
		Iterator<Usuariotipoidentificador> identificador = usuarios.getUsuariotipoidentificadors().iterator();
		while(identificador.hasNext()){
			Usuariotipoidentificador usu = identificador.next();
			idTipo.add(""+usu.getCattipoidentificador().getTipoIdentificadorId());
			tipo.add(usu.getValor());
		}
		identificadorVo.setId(idTipo);
		identificadorVo.setValor(tipo);
		usuarioForm.setTipoIdentificadorVo(identificadorVo);
		
		Collection<String> roles = new ArrayList<String>();
		Iterator<Usuariorol> rol = usuarios.getUsuariorols().iterator();
		while(rol.hasNext()){
			Usuariorol r = rol.next();
			roles.add(""+r.getRoles().getRolId());
		}
		usuarioForm.setRoles(roles);
		
		usuarioForm.setIdSexo(""+usuarios.getCatsexos().getSexoId());
		usuarioForm.setEditar(true);
		
		Iterator<Usuarioasegurador> usuAseg = usuarios.getUsuarioaseguradors().iterator();
		while (usuAseg.hasNext()) {
			Usuarioasegurador usuarioasegurador = usuAseg.next();
			usuarioForm.setAdminInstitucion(usuarioasegurador.getAseguradores().getNombreRazonSocial());
		}
		
	}
	
	@Override
	public Usuarios getUsuarioById(int idUsuario) {
		Usuarios usuarios = usuarioDao.getUsuarioById(idUsuario);
		return usuarios;
	}
	
	@Override
	public Collection<UsuariosVo> getUsuarios(String busqueda, int page) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		Collection<UsuariosVo> usuariosVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;
		Collection<Usuarios> usuarios = usuarioDao.getUsuarios(busqueda, inicio, fin);
		for (Usuarios usu : usuarios) {
			UsuariosVo usuariosVo = new UsuariosVo();
			usuariosVo.setNombre(usu.getNombre());
			usuariosVo.setApellidoPaterno(usu.getApellidoPaterno());
			usuariosVo.setApellidoMaterno(usu.getApellidoMaterno());
			usuariosVo.setRfc(usu.getRfc());
			usuariosVo.setUsuarioId(usu.getUsuarioId());
			usuariosVos.add(usuariosVo);
		}
		
		return usuariosVos;
	}
	
	@Override
	public Collection<RolesVo> getRolesUsuario(String id, String idUsuario) {
		Collection<RolesVo> rol = new ArrayList<>();
		Usuarios usuarios = usuarioDao.getUsuarioById(Integer.parseInt(idUsuario));
		Collection<String> rolesUsu = new ArrayList<String>();
		Iterator<Usuariorol> rolUsuario = usuarios.getUsuariorols().iterator();	
		while(rolUsuario.hasNext()){
			Usuariorol r = rolUsuario.next();
			rolesUsu.add(""+r.getRoles().getRolId());
		}
		Collection<Roles> roles = genericDao.getRoles(Integer.parseInt(id));			
		for (Roles r : roles) {
			RolesVo ro = new RolesVo();
			ro.setRolesId(r.getRolId());
			ro.setRol(r.getRol());			
			for (String string : rolesUsu) {
				if(string.equals(ro.getRolesId().toString())){
					ro.setSeleccionado("1");
				}
			}
			if(ro.getSeleccionado()==null || ro.getSeleccionado().toString().equals("null")){
				ro.setSeleccionado("0");
			}
			rol.add(ro);			
		}
		return rol;
	}

	@Override
	public void update(UsuarioForm usuarioForm) throws Exception {
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = formatter.parse(usuarioForm.getFechaDeNacimiento());		
			
			Usuarios usuarios = usuarioDao.getUsuarioById(Integer.parseInt(usuarioForm.getIdUsuario()));
			usuarios.setNombre(usuarioForm.getUsuario().getNombre());
			usuarios.setApellidoPaterno(usuarioForm.getUsuario().getApellidoPaterno());
			usuarios.setApellidoMaterno(usuarioForm.getUsuario().getApellidoMaterno());
			usuarios.setMail(usuarioForm.getUsuario().getMail());
			usuarios.setCatsexos(usuarioDao.getSexoId(Integer.parseInt(usuarioForm.getIdSexo())));
			usuarios.setCatestados(genericDao.getEstadoById(Integer.parseInt(usuarioForm.getIdEstado())));
			usuarios.setCurp(usuarioForm.getUsuario().getCurp());
			usuarios.setFechaDeNacimiento(date);
			usuarioDao.update(usuarios);
			usuarioDao.delete(usuarios.getUsuarioId(), usuarioForm.getIdModulo(), usuarioForm.getRoles());
			altaUsuarios(usuarioForm, usuarios);			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void altaUsuarios(UsuarioForm usuarioForm, Usuarios usuarios)throws Exception{
		try {
			if(usuarioForm.getIdModulo().equals("2")){
				usuarioForm.getAdminInstitucion();
				Collection<Aseguradores> aseguradores = aseguradorDao.getAseguradores();
				boolean cont = true;
				Aseguradores asegurador = null;
				for (Aseguradores aseg : aseguradores) {
					if(aseg.getNombreRazonSocial().equals(usuarioForm.getAdminInstitucion())){
						cont = false;
						asegurador = aseg;
					}
				}
				if(cont){
					throw new Exception("Asegurador no encontrado");
				}
				Usuarioasegurador usuarioasegurador = new Usuarioasegurador();
				usuarioasegurador.setAseguradores(asegurador);
				usuarioasegurador.setUsuarios(usuarios);
				usuarioDao.saveUsuarioAsegurador(usuarioasegurador);
			}else if(usuarioForm.getIdModulo().equals("3")){
				if(usuarioForm.getRoles().contains("14")){
					saveMedico(usuarioForm, usuarios);	
				}	
				for (String lugar : usuarioForm.getIdLugarAtencion()) {
					Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionById(Integer.parseInt(lugar));
					Usuariolugaratencion usuariolugaratencion = new Usuariolugaratencion();
					usuariolugaratencion.setLugaresdeatencion(lugaresdeatencion);
					usuariolugaratencion.setUsuarios(usuarios);
					usuarioDao.saveUsuarioLugarAtencion(usuariolugaratencion);
				}
			}else if(usuarioForm.getIdModulo().equals("4")){
				for (String lugar : usuarioForm.getIdLugarAtencion()) {
					Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionById(Integer.parseInt(lugar));
					Usuariolugaratencion usuariolugaratencion = new Usuariolugaratencion();
					usuariolugaratencion.setLugaresdeatencion(lugaresdeatencion);
					usuariolugaratencion.setUsuarios(usuarios);
					usuarioDao.saveUsuarioLugarAtencion(usuariolugaratencion);
				}
			}
			for (String rol : usuarioForm.getRoles()) {
				Collection<Menurol> menurols = usuarioDao.getMenurol(Integer.parseInt(rol));
				for (Menurol menurol : menurols) {
					Menu menu = usuarioDao.getMenuId(menurol.getMenu().getMenuId());
					Usuariomenu usuariomenu = new Usuariomenu();
					usuariomenu.setMenu(menu);
					usuariomenu.setUsuarios(usuarios);
					usuarioDao.saveUsuarioMenu(usuariomenu);
				}				
			}
			for (String rol : usuarioForm.getRoles()) {
				Roles roles = usuarioDao.getRolesId(Integer.parseInt(rol));
				Usuariorol usuariorol = new Usuariorol();
				usuariorol.setRoles(roles);
				usuariorol.setUsuarios(usuarios);
				usuarioDao.saveUsuarioRol(usuariorol);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Collection<AseguradoresVo> aseguradoresById(String busqueda) {
		Aseguradores aseguradores = aseguradorDao.getAseguradorById(Integer.parseInt(busqueda));
		Collection<AseguradoresVo> aseguradoresVos = new ArrayList<>();
		AseguradoresVo aseguradoresVo = new AseguradoresVo();
		aseguradoresVo.setAseguradorId(aseguradores.getAseguradorId());
		aseguradoresVo.setNombreRazonSocial(aseguradores.getNombreRazonSocial());
		aseguradoresVos.add(aseguradoresVo);
		return aseguradoresVos;
	}

	@Override
	public Collection<AutocompleteVo> aseguradores(String busqueda) {
		Collection<Aseguradores> aseguradores = aseguradorDao.getAseguradoresByDesc(busqueda);
		Collection<AutocompleteVo> asegurador = new ArrayList<>();
		for (Aseguradores aseg : aseguradores) {
			AutocompleteVo autocompleteVo = new AutocompleteVo();
			autocompleteVo.setLabel(aseg.getNombreRazonSocial());
			autocompleteVo.setValue(""+aseg.getAseguradorId());
			asegurador.add(autocompleteVo);
		}
		return asegurador;
	}

	@Override
	public void delete(UsuarioForm usuarioForm) {
		Usuarios usuarios = usuarioDao.getUsuarioById(Integer.parseInt(usuarioForm.getIdUsuario()));
		usuarios.setActivo(0);
		usuarioDao.save(usuarios);
	}
}