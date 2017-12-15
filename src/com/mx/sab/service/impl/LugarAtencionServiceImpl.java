package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.form.LugarAtencionForm;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catnivel;
import com.mx.sab.model.Cattipounidad;
import com.mx.sab.model.Catzonacardinal;
import com.mx.sab.model.Codigolugaratencion;
import com.mx.sab.model.Convenios;
import com.mx.sab.model.Lectores;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Lugaresdeatencionroles;
import com.mx.sab.model.Roles;
import com.mx.sab.model.Usuariolugaratencion;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.IGenericService;
import com.mx.sab.service.ILugarAtencionService;
import com.mx.sab.vo.CatTipoUnidadVo;
import com.mx.sab.vo.DiagnosticoVo;
import com.mx.sab.vo.LugarAtencionVo;

@Service
@Log4j2
public class LugarAtencionServiceImpl implements ILugarAtencionService{

	@Autowired
	private IGenericService genericService;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;

	@Override
	public void delete(LugarAtencionForm lugarAtencionForm) {
		lugarAtencionForm.setExito("");
		lugarAtencionForm.setError("");
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionById(lugarAtencionForm.getIdLugarAtencion());
		if (lugaresdeatencion!=null) {
			Iterator<Convenios> iteratorConvenios = lugaresdeatencion.getConvenioses().iterator();
			if (iteratorConvenios.hasNext()) {
				lugarAtencionForm.setExito("");
				lugarAtencionForm.setError("Hay Convenios vigentes para este Lugar de Atención, no se puede Eliminar");
			}else{
				Iterator<Atencionmedica> iteratorAtencionMedica = lugaresdeatencion.getAtencionmedicas().iterator();
				if (iteratorAtencionMedica.hasNext()) {
					lugarAtencionForm.setExito("");
					lugarAtencionForm.setError("Hay Atenciones médicas registradas para este Lugar de Atención, no se puede Eliminar");			
				}else{
					Iterator<Lectores> iteratorLectores = lugaresdeatencion.getLectoreses().iterator();
					if (iteratorLectores.hasNext()) {
						lugarAtencionForm.setExito("");
						lugarAtencionForm.setError("Hay lectores asignados al lugar de Atención, no se puede Eliminar");								
					}else{
						lugarAtencionDao.delete(lugaresdeatencion);
						lugarAtencionForm.setExito("Se ha eliminado correctamente el Lugar de atencion");
						lugarAtencionForm.setError("");
						lugarAtencionForm.setEditar(false);
						lugarAtencionForm.setBusqueda(0);
						lugarAtencionForm.setIdLugarAtencion(0);
						lugarAtencionForm.setCodigo("");
						lugarAtencionForm.setLugarAtencion("");
						lugarAtencionForm.setCalle("");
						lugarAtencionForm.setNumeroExterno("");
						lugarAtencionForm.setNumeroInterno("");
						lugarAtencionForm.setIdEstado(-1);
						lugarAtencionForm.setIdColonia(-1);
						lugarAtencionForm.setIdMunicipio(-1);
						lugarAtencionForm.setCp("");
						lugarAtencionForm.setNombre("");
						lugarAtencionForm.setTelefono1("");
						lugarAtencionForm.setTelefono2("");
						lugarAtencionForm.setCorreoLugarAtencion("");						
					}
				}	
			}	
		}else{
			lugarAtencionForm.setError("Debe de buscar un Lugar de atencion");
			lugarAtencionForm.setExito("");
		}
		
	}

	@Override
	public Collection<CatTipoUnidadVo> getCatTipoUnidad(String idNivel) {
		Collection<Cattipounidad> cattipounidads = lugarAtencionDao.getCatTipoUnidad(Integer.parseInt(idNivel));
		Collection<CatTipoUnidadVo> catTipoUnidadVos= new ArrayList<>();
		for (Cattipounidad cattipounidad : cattipounidads) {
			CatTipoUnidadVo catTipoUnidadVo = new CatTipoUnidadVo();
			catTipoUnidadVo.setTiposUnidadId(cattipounidad.getTiposUnidadId());
			catTipoUnidadVo.setUnidad(cattipounidad.getUnidad());
			catTipoUnidadVos.add(catTipoUnidadVo);
		}
		return catTipoUnidadVos;
	}
	
	@Override
	public void save(LugarAtencionForm lugarAtencionForm) {
		//log.info("save");
		try{
			if (lugarAtencionForm.getRolesSeleccionadosSegundo().size() != 0 ) {
				if (lugarAtencionDao.existeDireccionLugarAtencion(lugarAtencionForm)==null) {
					Lugaresdeatencion lugaresdeatencion = new Lugaresdeatencion();
					lugaresdeatencion.setActivo(1);
					lugaresdeatencion.setCalleyNo(lugarAtencionForm.getCalle());
					lugaresdeatencion.setCatcolonias(genericService.getColoniaById(lugarAtencionForm.getIdColonia()));
					lugaresdeatencion.setCatestados(genericService.getEstadoById(lugarAtencionForm.getIdEstado()));
					lugaresdeatencion.setCatmunicipios(genericService.getMunicipioById(lugarAtencionForm.getIdMunicipio()));
					String codigo = getCodigo();
					lugaresdeatencion.setClaveDeLaUnidad(codigo);
					lugaresdeatencion.setCodigoLugarAtencion(Integer.parseInt(codigo));
					lugaresdeatencion.setCp(lugarAtencionForm.getCp());
					lugaresdeatencion.setDescripcion(lugarAtencionForm.getLugarAtencion());
					lugaresdeatencion.setTelefono(lugarAtencionForm.getTelefono1());
					lugaresdeatencion.setCorreo(lugarAtencionForm.getCorreoLugarAtencion());
					lugaresdeatencion.setNumInterno(lugarAtencionForm.getNumeroInterno());
					lugaresdeatencion.setNumExterno(lugarAtencionForm.getNumeroExterno());
					lugaresdeatencion.setTelefono2(lugarAtencionForm.getTelefono2());
					lugaresdeatencion.setNombreContacto(lugarAtencionForm.getNombre());
					
					lugaresdeatencion.setUsoDeHuella(1);
					lugarAtencionDao.save(lugaresdeatencion);
					lugarAtencionForm.setCodigo(lugaresdeatencion.getClaveDeLaUnidad());
					
					Lugaresdeatencionroles lugaresdeatencionroles = new Lugaresdeatencionroles();
					for (String rol : lugarAtencionForm.getRolesSeleccionadosSegundo()) {
						lugaresdeatencionroles = new Lugaresdeatencionroles();
						lugaresdeatencionroles.setLugaresdeatencion(lugarAtencionDao.getLugarAtencionById(lugaresdeatencion.getLugarDeAtencionId()));
						lugaresdeatencionroles.setRoles(lugarAtencionDao.getRolById(Integer.parseInt(rol)));
						lugarAtencionDao.saveLugaresAtencionRoles(lugaresdeatencionroles);
						
					}
					lugarAtencionForm.setExito("Se agrego el lugar de atencion con el codigo: "+lugaresdeatencion.getClaveDeLaUnidad());
				}else {
					lugarAtencionForm.setError("No se puede repetir la direccion");
					lugarAtencionForm.setExito("");
				}	
			}else{
				lugarAtencionForm.setError("Debe de seleccionar al menos un Rol");
				lugarAtencionForm.setExito("");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			lugarAtencionForm.setError("Surgio un error");
			lugarAtencionForm.setExito("");
		}
	}

	private String getCodigo() {
		String codigoS = "";
		int codigoI = 0;
		try{
			Object codigo = lugarAtencionDao.getCodigo();	
			if (codigo!=null) {
				codigoI = Integer.parseInt((String)codigo);
				codigoI++;
				codigoS = ""+codigoI;
			}else{
				Codigolugaratencion codigolugaratencion = lugarAtencionDao.getCodigoLugarAtencion();
				codigoS = ""+codigolugaratencion.getSemilla();
			}
			Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(codigoS);
			if(lugaresdeatencion!=null) {
				while(lugaresdeatencion!=null){
					codigoI = Integer.parseInt(codigoS);
					codigoI++;
					codigoS = ""+codigoI;
					lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(codigoS);	
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return codigoS;
	}

	@Override
	public void update(LugarAtencionForm lugarAtencionForm) {
		//log.info("update");
		boolean actualizar = false;
		try{
			if (lugarAtencionForm.isEditar()) {
				if (lugarAtencionForm.getRolesSeleccionadosSegundo().size() != 0 ) {
					Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionById(lugarAtencionForm.getIdLugarAtencion());
					Lugaresdeatencion lugaresdeatencionAux = lugarAtencionDao.existeDireccionLugarAtencion(lugarAtencionForm);
					if (lugaresdeatencionAux == null) {
						actualizar = true;
					}else {
						if (lugaresdeatencionAux.getLugarDeAtencionId() == lugaresdeatencion.getLugarDeAtencionId()) {
							actualizar = true;
						}else{
							lugarAtencionForm.setError("No se puede repetir la direccion");
							lugarAtencionForm.setExito("");	
						}
					}
					if (actualizar) {
						lugaresdeatencion.setCalleyNo(lugarAtencionForm.getCalle());
						lugaresdeatencion.setCatcolonias(genericService.getColoniaById(lugarAtencionForm.getIdColonia()));
						lugaresdeatencion.setCatestados(genericService.getEstadoById(lugarAtencionForm.getIdEstado()));
						lugaresdeatencion.setCatmunicipios(genericService.getMunicipioById(lugarAtencionForm.getIdMunicipio()));
						lugaresdeatencion.setCp(lugarAtencionForm.getCp());
						lugaresdeatencion.setDescripcion(lugarAtencionForm.getLugarAtencion());
						lugaresdeatencion.setTelefono(lugarAtencionForm.getTelefono1());
						lugaresdeatencion.setCorreo(lugarAtencionForm.getCorreoLugarAtencion());
						lugaresdeatencion.setNumInterno(lugarAtencionForm.getNumeroInterno());
						lugaresdeatencion.setNumExterno(lugarAtencionForm.getNumeroExterno());
						lugaresdeatencion.setTelefono2(lugarAtencionForm.getTelefono2());
						lugaresdeatencion.setNombreContacto(lugarAtencionForm.getNombre());
						lugarAtencionDao.update(lugaresdeatencion);
						lugarAtencionForm.setCodigo(lugaresdeatencion.getClaveDeLaUnidad());
						
						Lugaresdeatencionroles lugaresdeatencionroles = new Lugaresdeatencionroles();
						lugarAtencionDao.deleteLugaresAtencionRoles(lugaresdeatencion.getLugarDeAtencionId());
						for (String rol : lugarAtencionForm.getRolesSeleccionadosSegundo()) {
							lugaresdeatencionroles = new Lugaresdeatencionroles();
							lugaresdeatencionroles.setLugaresdeatencion(lugarAtencionDao.getLugarAtencionById(lugaresdeatencion.getLugarDeAtencionId()));
							lugaresdeatencionroles.setRoles(lugarAtencionDao.getRolById(Integer.parseInt(rol)));
							lugarAtencionDao.saveLugaresAtencionRoles(lugaresdeatencionroles);
							
						}
						
						Iterator<Lugaresdeatencionroles> lugaresDeAtencionRolesIterator = lugaresdeatencion.getLugaresdeatencionroleses().iterator();
						Collection<Roles> rolesPrimero = new ArrayList<>();
						Collection<Roles> rolesSegundo = new ArrayList<>();
						while (lugaresDeAtencionRolesIterator.hasNext()) {
							lugaresdeatencionroles = (Lugaresdeatencionroles) lugaresDeAtencionRolesIterator.next();
							rolesSegundo.add(lugaresdeatencionroles.getRoles());
						}
						lugarAtencionForm.setRolesLugarAtencionSegundo(rolesSegundo);
						Collection<Roles> roles = getRoles();
						boolean existe = false;
						for (Roles rol : roles) {
							existe = false;
							for (Roles rolPrimero : lugarAtencionForm.getRolesLugarAtencionSegundo()) {
								if (rolPrimero.getRolId() == rol.getRolId()) {
									existe = true;
								}
							}
							if (!existe) {
								rolesPrimero.add(rol);
							}
						}
						lugarAtencionForm.setRolesLugarAtencionPrimero(rolesPrimero);						
						
						lugarAtencionForm.setExito("Se actualizo correctamente el lugar de atencion");					
					}
				}else{
					lugarAtencionForm.setError("Debe de seleccionar al menos un Rol");
					lugarAtencionForm.setExito("");
				}
			}else{
				lugarAtencionForm.setError("Debe de buscar un Lugar de atencion");
				lugarAtencionForm.setExito("");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			lugarAtencionForm.setError("Surgio un error");
			lugarAtencionForm.setExito("");
		}
	}

	@Override
	public void getLugarAtencion(LugarAtencionForm lugarAtencionForm) {
		lugarAtencionForm.setExito("");
		lugarAtencionForm.setError("");
		if (lugarAtencionForm.getCodigo().trim().length()!=0 || lugarAtencionForm.getIdLugarAtencion()!=0) {
			Lugaresdeatencion lugaresdeatencion = null;
			if (lugarAtencionForm.getBusqueda()==0){
				lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(lugarAtencionForm.getCodigo());
			}else{
				lugaresdeatencion = lugarAtencionDao.getLugarAtencionById(lugarAtencionForm.getIdLugarAtencion());	
			}
			lugarAtencionForm.setBusqueda(0);
			if (lugaresdeatencion!=null) {
				lugarAtencionForm.setLugarAtencion(lugaresdeatencion.getDescripcion());
				lugarAtencionForm.setIdLugarAtencion(lugaresdeatencion.getLugarDeAtencionId());
				lugarAtencionForm.setCalle(lugaresdeatencion.getCalleyNo());
				lugarAtencionForm.setNumeroExterno(lugaresdeatencion.getNumExterno());
				lugarAtencionForm.setNumeroInterno(lugaresdeatencion.getNumInterno());
				lugarAtencionForm.setIdColonia(lugaresdeatencion.getCatcolonias().getColoniaId());
				lugarAtencionForm.setIdEstado(lugaresdeatencion.getCatestados().getEstadoId());
				lugarAtencionForm.setIdMunicipio(lugaresdeatencion.getCatmunicipios().getMunicipioId());
				lugarAtencionForm.setCp(lugaresdeatencion.getCp());
				lugarAtencionForm.setNombre(lugaresdeatencion.getNombreContacto());
				lugarAtencionForm.setTelefono1(lugaresdeatencion.getTelefono());
				lugarAtencionForm.setTelefono2(lugaresdeatencion.getTelefono2());
				lugarAtencionForm.setCorreoLugarAtencion(lugaresdeatencion.getCorreo());
				lugarAtencionForm.setCodigo(lugaresdeatencion.getClaveDeLaUnidad());
				lugarAtencionForm.setEditar(true);
				lugarAtencionForm.setError("");
				Iterator<Lugaresdeatencionroles> lugaresDeAtencionRolesIterator = lugaresdeatencion.getLugaresdeatencionroleses().iterator();
				Collection<Roles> rolesPrimero = new ArrayList<>();
				Collection<Roles> rolesSegundo = new ArrayList<>();
				while (lugaresDeAtencionRolesIterator.hasNext()) {
					Lugaresdeatencionroles lugaresdeatencionroles = (Lugaresdeatencionroles) lugaresDeAtencionRolesIterator.next();
					rolesSegundo.add(lugaresdeatencionroles.getRoles());
				}
				lugarAtencionForm.setRolesLugarAtencionSegundo(rolesSegundo);
				Collection<Roles> roles = getRoles();
				boolean existe = false;
				for (Roles rol : roles) {
					existe = false;
					for (Roles rolPrimero : lugarAtencionForm.getRolesLugarAtencionSegundo()) {
						if (rolPrimero.getRolId() == rol.getRolId()) {
							existe = true;
						}
					}
					if (!existe) {
						rolesPrimero.add(rol);
					}
				}
				lugarAtencionForm.setRolesLugarAtencionPrimero(rolesPrimero);
				
			}else{
				lugarAtencionForm.setError("Lugar de Atención no registrado en el sistema");
			}
		}else{
			lugarAtencionForm.setError("Ingrese el codigo o la descripcion");
		}

	}

	@Override
	public Collection<Lugaresdeatencion> getLugaresAtencion() {
		Collection<Lugaresdeatencion> lugaresdeatencions = lugarAtencionDao.getLugaresAtencion();
		return lugaresdeatencions;
	}

	@Override
	public Lugaresdeatencion getLugaresAtencionByClave(int tx_Marca) {
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+tx_Marca); 
		return lugaresdeatencion;
	}

	@Override
	public Collection<LugarAtencionVo> getLugaresAtencionByDescripcion(String busqueda) {
		Collection<Lugaresdeatencion> lugaresdeatencions = lugarAtencionDao.getLugarAtencionByDesc(busqueda);
		Collection<LugarAtencionVo> lugarAtencionVos = new ArrayList<>();
		LugarAtencionVo lugarAtencionVo = null;
		for (Lugaresdeatencion lugaresdeatencion : lugaresdeatencions) {
			lugarAtencionVo = new LugarAtencionVo();
			lugarAtencionVo.setDescripcion(lugaresdeatencion.getDescripcion());
			lugarAtencionVo.setLugarDeAtencionId(lugaresdeatencion.getLugarDeAtencionId());
			lugarAtencionVo.setCodigoLugarAtencion(lugaresdeatencion.getCodigoLugarAtencion());
			lugarAtencionVos.add(lugarAtencionVo);
		}
		if (lugarAtencionVos.size()==0) {
			lugarAtencionVo = new LugarAtencionVo();
			lugarAtencionVo.setDescripcion("No hay lugares de atencion coincidentes");
			lugarAtencionVo.setLugarDeAtencionId(-1);
			lugarAtencionVos.add(lugarAtencionVo);
		}
		return lugarAtencionVos;
	}

	@Override
	public Collection<LugarAtencionVo> getLugaresAtencionByCodigo(String busqueda) {
		Collection<Lugaresdeatencion> lugaresdeatencions = lugarAtencionDao.getLugarAtencionByCodigos(busqueda);
		Collection<LugarAtencionVo> lugarAtencionVos = new ArrayList<>();
		LugarAtencionVo lugarAtencionVo = null;
		for (Lugaresdeatencion lugaresdeatencion : lugaresdeatencions) {
			lugarAtencionVo = new LugarAtencionVo();
			lugarAtencionVo.setDescripcion(lugaresdeatencion.getDescripcion());
			lugarAtencionVo.setLugarDeAtencionId(lugaresdeatencion.getLugarDeAtencionId());
			lugarAtencionVo.setCodigoLugarAtencion(lugaresdeatencion.getCodigoLugarAtencion());
			lugarAtencionVos.add(lugarAtencionVo);
		}
		if (lugarAtencionVos.size()==0) {
			lugarAtencionVo = new LugarAtencionVo();
			lugarAtencionVo.setDescripcion("No hay lugares de atencion coincidentes");
			lugarAtencionVo.setLugarDeAtencionId(-1);
			lugarAtencionVos.add(lugarAtencionVo);
		}
		return lugarAtencionVos;
	}

	@Override
	public Collection<Roles> getRoles() {
		return lugarAtencionDao.getRoles();
	}

	@Override
	public void verificarRolesLugarAtencion(LugarAtencionForm lugarAtencionForm) {
		Collection<Roles> roles = getRoles();
		Collection<Roles> rolesPrimero = new ArrayList<>();
		Collection<Roles> rolesSegundo = new ArrayList<>();
		for (Roles rol : roles) {
			for (String rolPrimero : lugarAtencionForm.getRolesSeleccionadosPrimero()) {
				if (rolPrimero.equals(""+rol.getRolId())) {
					rolesPrimero.add(rol);		
				}
			}
		}
		
		for (Roles rol : roles) {
			for (String rolSegundo : lugarAtencionForm.getRolesSeleccionadosSegundo()) {
				if (rolSegundo.equals(""+rol.getRolId())) {
					rolesSegundo.add(rol);		
				}
			}
		}
		
		lugarAtencionForm.setRolesLugarAtencionPrimero(rolesPrimero);
		lugarAtencionForm.setRolesLugarAtencionSegundo(rolesSegundo);
	}

}
