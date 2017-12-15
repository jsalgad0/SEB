package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.IRegistroLectoresDao;
import com.mx.sab.form.RegistroLectoresForm;
import com.mx.sab.model.Lectores;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Propietarioslector;
import com.mx.sab.service.IRegistroLectoresService;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.LectoresVo;
import com.mx.sab.vo.LugarAtencionVo;
import com.mx.sab.vo.PropietarioLectorVo;

@Service
@Log4j2
public class RegistroLectoresImpl implements IRegistroLectoresService {

	@Autowired
	private IRegistroLectoresDao registroLectoresDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Override
	public Collection<LectoresVo> getLectores(String busqueda, int page) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		Collection<LectoresVo> lectoresVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;
		Collection<Lectores> lectores = registroLectoresDao.getLectores(busqueda,inicio,fin);
		for (Lectores lector : lectores) {
			LectoresVo lectoresVo = new LectoresVo();
			lectoresVo.setNoDeSerie(lector.getNoDeSerie());
			lectoresVo.setLectorId(lector.getLectorId());
			LugarAtencionVo lugarAtencionVo = new LugarAtencionVo();
			lugarAtencionVo.setDescripcion(lector.getLugaresdeatencion().getDescripcion());
			lectoresVo.setLugarAtencion(lugarAtencionVo);
			PropietarioLectorVo propietarioLectorVo = new PropietarioLectorVo();
			propietarioLectorVo.setPropietarioLector(lector.getPropietarioslector().getPropietarioLector());
			lectoresVo.setPropietarioLector(propietarioLectorVo);
			lectoresVos.add(lectoresVo);
		}
		
		return lectoresVos;
	}

	@Override
	public Collection<Lectores> getLectores(RegistroLectoresForm registroLectoresForm) {
		int filas = 7;
		int paginasTotal = 0;
		int inicio = 0;
		int fin = 0;
		int pagina = 1;
		Collection<Lectores> lectores = null;
		
		if (registroLectoresForm==null) {
			registroLectoresForm = new RegistroLectoresForm();
		}
		
		if (registroLectoresForm.getBusqueda()==null) {
			registroLectoresForm.setBusqueda("");	
		}
		int totalLectores = registroLectoresDao.getLectoresCount(registroLectoresForm.getBusqueda());
		if (totalLectores>0) {
			paginasTotal = totalLectores / filas;
			if (totalLectores % filas != 0) {
				paginasTotal++;
			}
			
			if (paginasTotal>7) {
				registroLectoresForm.setDisplay(7);
			}else {
				registroLectoresForm.setDisplay(paginasTotal);
			}
			
			registroLectoresForm.setCount(paginasTotal);
			inicio = (pagina-1)*7;
			fin = (pagina*7);
			
			lectores = registroLectoresDao.getLectores(registroLectoresForm.getBusqueda(),inicio,fin);
		}else {
			//log.info("No hay Lectores");
		}
	
		return lectores;
	}

	@Override
	public void save(RegistroLectoresForm registroLectoresForm) {
		//log.info("save");
		registroLectoresForm.setError("");
		registroLectoresForm.setExito("");
		try{
			Lectores lectores = registroLectoresDao.getLectorByNoSerie(registroLectoresForm.getNoDeSerieAux());
			if (lectores==null) {
				if (registroLectoresForm.getIdPropietarioLector()!=null && registroLectoresForm.getIdPropietarioLector()!=0) {
					if (registroLectoresForm.getIdLugarAtencion()!=null && registroLectoresForm.getIdLugarAtencion()!=0) {
						lectores = new Lectores();
						lectores.setLugaresdeatencion(lugarAtencionDao.getLugarAtencionById(registroLectoresForm.getIdLugarAtencion()));
						lectores.setNoDeSerie(registroLectoresForm.getNoDeSerieAux());
						lectores.setPropietarioslector(registroLectoresDao.getPropietariosLectorById(registroLectoresForm.getIdPropietarioLector()));
						registroLectoresDao.save(lectores);
						registroLectoresForm.setError("");
						registroLectoresForm.setExito("Lector registrado correctamente");	
					}else{
						registroLectoresForm.setError("Debe de seleccionar un Lugar de Atencion");
						registroLectoresForm.setExito("");						
					}
				}else{
					registroLectoresForm.setError("Debes de seleccionar un propietario");
					registroLectoresForm.setExito("");	
				}
			}else {
				registroLectoresForm.setError("El lector ya se encuentra registrado");
				registroLectoresForm.setExito("");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			registroLectoresForm.setExito("");
			registroLectoresForm.setError("Surgio un error");
		}
	}

	@Override
	public void update(RegistroLectoresForm registroLectoresForm) {
		//log.info("save");
		registroLectoresForm.setError("");
		registroLectoresForm.setExito("");
		try{
			Lectores lectores = registroLectoresDao.getLectorByNoSerie(registroLectoresForm.getNoDeSerieAux());
			if (lectores!=null) {
				if (registroLectoresForm.getIdPropietarioLector()!=null && registroLectoresForm.getIdPropietarioLector()!=0) {
					if (registroLectoresForm.getIdLugarAtencion()!=null && registroLectoresForm.getIdLugarAtencion()!=0) {
						lectores.setLugaresdeatencion(lugarAtencionDao.getLugarAtencionById(registroLectoresForm.getIdLugarAtencion()));
						lectores.setNoDeSerie(registroLectoresForm.getNoDeSerieAux());
						lectores.setPropietarioslector(registroLectoresDao.getPropietariosLectorById(registroLectoresForm.getIdPropietarioLector()));
						registroLectoresDao.update(lectores);
						registroLectoresForm.setError("");
						registroLectoresForm.setExito("Lector actualizado correctamente");	
					}else{
						registroLectoresForm.setError("Debe de seleccionar un Lugar de Atencion");
						registroLectoresForm.setExito("");						
					}
				}else{
					registroLectoresForm.setError("Debes de seleccionar un propietario");
					registroLectoresForm.setExito("");	
				}
			}else {
				if (registroLectoresForm.getIdPropietarioLector()!=null && registroLectoresForm.getIdPropietarioLector()!=0) {
					if (registroLectoresForm.getIdLugarAtencion()!=null && registroLectoresForm.getIdLugarAtencion()!=0) {
						lectores = new Lectores();
						lectores.setLugaresdeatencion(lugarAtencionDao.getLugarAtencionById(registroLectoresForm.getIdLugarAtencion()));
						lectores.setNoDeSerie(registroLectoresForm.getNoDeSerieAux());
						lectores.setPropietarioslector(registroLectoresDao.getPropietariosLectorById(registroLectoresForm.getIdPropietarioLector()));
						registroLectoresDao.save(lectores);
						registroLectoresForm.setError("");
						registroLectoresForm.setExito("Lector actualizado correctamente");	
					}else{
						registroLectoresForm.setError("Debe de seleccionar un Lugar de Atencion");
						registroLectoresForm.setExito("");						
					}
				}else{
					registroLectoresForm.setError("Debes de seleccionar un propietario");
					registroLectoresForm.setExito("");	
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			registroLectoresForm.setExito("");
			registroLectoresForm.setError("Surgio un error");
		}
	}

	@Override
	public void getLector(RegistroLectoresForm registroLectoresForm) {
//		Lectores lectores = registroLectoresDao.getLectorById(Integer.parseInt(registroLectoresForm.getIdLector()));
//		registroLectoresForm.setLectores(lectores);
//		registroLectoresForm.setCodigoLugarAtencion(lectores.getLugaresdeatencion().getCodigoLugarAtencion());
//		registroLectoresForm.setLugarAtencion(lectores.getLugaresdeatencion().getDescripcion());
//		registroLectoresForm.setIdLector(""+lectores.getLectorId());
//		registroLectoresForm.setPropietarioLector(lectores.getPropietarioslector().getPropietarioLector());
//		registroLectoresForm.setRfcPropietarioLector(lectores.getPropietarioslector().getRfc());
//		registroLectoresForm.setIdLugarAtencion(lectores.getLugaresdeatencion().getLugarDeAtencionId());
//		registroLectoresForm.setIdPropietarioLector(lectores.getPropietarioslector().getIdPropietarioLector());
//		registroLectoresForm.setEditar(true);
	}

	@Override
	public Collection<AutocompleteVo> getLugarAtencion(String busqueda) {
		Collection<Lugaresdeatencion> lugaresdeatencions = lugarAtencionDao.getLugarAtencionByDesc(busqueda);
		Collection<AutocompleteVo> lugaresAtencion = new ArrayList<>();
		for (Lugaresdeatencion lugaresdeatencion : lugaresdeatencions) {
			AutocompleteVo autocompleteVo = new AutocompleteVo();
			autocompleteVo.setLabel(lugaresdeatencion.getDescripcion());
			autocompleteVo.setValue(""+lugaresdeatencion.getLugarDeAtencionId());
			lugaresAtencion.add(autocompleteVo);
		}
		return lugaresAtencion;
	}

	@Override
	public Collection<PropietarioLectorVo> getPropietariosLector(String busqueda) {
		Collection<Propietarioslector> propietarioslectors = registroLectoresDao.getPropietariosLectorByDesc(busqueda);
		Collection<PropietarioLectorVo> propietariosLectorList = new ArrayList<>();
		for (Propietarioslector propietarioslector : propietarioslectors) {
			PropietarioLectorVo propietarioLectorVo = new PropietarioLectorVo();
			propietarioLectorVo.setPropietarioLector(propietarioslector.getPropietarioLector());
			propietarioLectorVo.setIdPropietarioLector(propietarioslector.getIdPropietarioLector());
			propietarioLectorVo.setRfc(propietarioslector.getRfc());
			propietariosLectorList.add(propietarioLectorVo);
		}
		return propietariosLectorList;
	}

	@Override
	public Collection<LugarAtencionVo> lugarAtencionByCodigo(String busqueda) {
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByCodigo(Integer.parseInt(busqueda));
		Collection<LugarAtencionVo> lugaresAtencion = new ArrayList<>();
		LugarAtencionVo lugarAtencionVo = new LugarAtencionVo();
		lugarAtencionVo.setLugarDeAtencionId(lugaresdeatencion.getLugarDeAtencionId());
		lugarAtencionVo.setDescripcion(lugaresdeatencion.getDescripcion());
		lugarAtencionVo.setCalleyNo(lugaresdeatencion.getCalleyNo());
		lugarAtencionVo.setCodigoLugarAtencion(lugaresdeatencion.getCodigoLugarAtencion());
		lugaresAtencion.add(lugarAtencionVo);
		return lugaresAtencion;
	}

	@Override
	public Collection<LugarAtencionVo> lugarAtencionById(String busqueda) {
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionById(Integer.parseInt(busqueda));
		Collection<LugarAtencionVo> lugaresAtencion = new ArrayList<>();
		LugarAtencionVo lugarAtencionVo = new LugarAtencionVo();
		lugarAtencionVo.setLugarDeAtencionId(lugaresdeatencion.getLugarDeAtencionId());
		lugarAtencionVo.setDescripcion(lugaresdeatencion.getDescripcion());
		lugarAtencionVo.setCalleyNo(lugaresdeatencion.getCalleyNo());
		lugarAtencionVo.setCodigoLugarAtencion(lugaresdeatencion.getCodigoLugarAtencion());
		lugaresAtencion.add(lugarAtencionVo);
		return lugaresAtencion;
	}

	@Override
	public Collection<PropietarioLectorVo> propietarioLectorById(String busqueda) {
		Propietarioslector propietarioslector = registroLectoresDao.getPropietariosLectorById(Integer.parseInt(busqueda));
		Collection<PropietarioLectorVo> propietariosLectorList = new ArrayList<>();
		PropietarioLectorVo propietarioLectorVo = new PropietarioLectorVo();
		propietarioLectorVo.setIdPropietarioLector(propietarioslector.getIdPropietarioLector());
		propietarioLectorVo.setPropietarioLector(propietarioslector.getPropietarioLector());
		propietarioLectorVo.setRfc(propietarioslector.getRfc());			
		propietariosLectorList.add(propietarioLectorVo);
		return propietariosLectorList;
	}

	@Override
	public Collection<PropietarioLectorVo> propietarioLectorByPropietario(String busqueda) {
		Propietarioslector propietarioslector = registroLectoresDao.getPropietariosLectorByPropietario(busqueda);
		Collection<PropietarioLectorVo> propietariosLectorList = new ArrayList<>();
		PropietarioLectorVo propietarioLectorVo = new PropietarioLectorVo();
		propietarioLectorVo.setIdPropietarioLector(propietarioslector.getIdPropietarioLector());
		propietarioLectorVo.setPropietarioLector(propietarioslector.getPropietarioLector());
		propietarioLectorVo.setRfc(propietarioslector.getRfc());			
		propietariosLectorList.add(propietarioLectorVo);
		return propietariosLectorList;
	}

	@Override
	public void delete(RegistroLectoresForm registroLectoresForm) {
		//log.info("delete");
		registroLectoresForm.setError("");
		registroLectoresForm.setExito("");
		try{
			Lectores lectores = registroLectoresDao.getLectorByNoSerie(registroLectoresForm.getNoDeSerieAux());
			if (lectores!=null) {
				registroLectoresForm.setError("");
				registroLectoresForm.setExito("Lector dado de baja correctamente");	
			}else {
				registroLectoresForm.setError("El lector no se encuentra registrado");
				registroLectoresForm.setExito("");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			registroLectoresForm.setExito("");
			registroLectoresForm.setError("Surgio un error");
		}
	}

	@Override
	public Propietarioslector getPropietariosLectorByRfc(String rfc) {
		return registroLectoresDao.getPropietariosLectorByRfc(rfc);
	}

	@Override
	public void savePropietarioLector(Propietarioslector propietarioslector) {
		registroLectoresDao.savePropietariosLector(propietarioslector);
		
	}

}
